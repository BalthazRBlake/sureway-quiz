package org.dev.fhhf.surewayquiz.controller;

import org.dev.fhhf.surewayquiz.model.Agent;
import org.dev.fhhf.surewayquiz.model.Quiz;
import org.dev.fhhf.surewayquiz.service.AgentService;
import org.dev.fhhf.surewayquiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/agent/quiz")
public class QuestionsController {

    @Autowired
    private QuizService quizService;
    @Autowired
    private AgentService agentService;

    @GetMapping("/start")
    public String initQuiz(Principal principal){

        Agent agent = agentService.findAgentByName(principal.getName());
        int attempts = agent.getAttempts();

        if (attempts < 1) {
            attempts++;
            agent.setScore(0);
            agent.setAttempts(attempts);
            agent.setAnswers("A:");
            agentService.saveAgent(agent);
            return "redirect:/agent/quiz/1/question";
        }

        return "redirect:/agent/quiz/result";
    }

    @GetMapping("/{id}/question")
    public String question(@PathVariable("id") int questionNumber, Model model, Principal principal){
        //System.out.println(principal);
        /*if(id == 1) {
            Date currentTime = new Date();
            model.addAttribute("currentTime", currentTime);
        }*/
        Quiz question = quizService.getByQuestionNumber(questionNumber);
        List<String> answers = new ArrayList<>();

        answers.add(question.getRightAnswer());
        answers.addAll(question.getWrongAnswers());
        Collections.shuffle(answers);

        model.addAttribute("question", question);
        model.addAttribute("answers", answers);

        return "question";
    }

    @PostMapping("/{id}/question")
    public String nextQuestion(@PathVariable("id") int questionNumber, Quiz quiz, Principal principal) {

        Agent agent = agentService.findAgentByName(principal.getName());

        String selectedAnswer = quiz.getSelectedAnswer();
        Quiz question = quizService.getByQuestionNumber(questionNumber);

        String answers = agent.getAnswers();
        String ans = "0 " + questionNumber + " " + selectedAnswer;
        if(question.getRightAnswer().equals(selectedAnswer)) {
            agent.increaseScore(agent.getScore());
            ans = "1 " + questionNumber + " " + selectedAnswer;
        }
        if(answers.contains("0 " + questionNumber) || answers.contains("1 " + questionNumber)){
            //System.out.println("YA RESPONDIO");
            questionNumber++;
            return "redirect:/agent/quiz/" + questionNumber + "/question";
        }
        answers = answers.concat(ans + ",");
        agent.setAnswers(answers);

        agentService.saveAgent(agent);

        if (questionNumber == quizService.countTotalQuestions()) {
            return "redirect:/agent/quiz/result";
        }
        questionNumber++;
        return "redirect:/agent/quiz/" + questionNumber + "/question";
    }

    @GetMapping("/result")
    public String result(Principal principal, Model model, HttpServletRequest request, HttpServletResponse response){

        Agent agent = agentService.findAgentByName(principal.getName());
        Long result = agent.getScore() * 100 / quizService.countTotalQuestions();
        String message = result < 90 ? "Sorry, you haven't passed" : "Well done!!!";

        /*if(result < 90){
            //message = "Try again";
            //model.addAttribute("result", result);
            //model.addAttribute("message", message);
            //return "result";
        }*/
        model.addAttribute("result", result);
        model.addAttribute("message", message);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        SecurityContextHolder.getContext().setAuthentication(null);
        return "result";
    }
}
