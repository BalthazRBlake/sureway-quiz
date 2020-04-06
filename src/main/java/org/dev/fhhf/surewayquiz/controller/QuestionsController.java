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
import java.util.List;

@Controller
@RequestMapping("/agent/quiz")
public class QuestionsController {

    @Autowired
    private QuizService quizService;
    @Autowired
    private AgentService agentService;

    @GetMapping("/{id}/question")
    public String question(@PathVariable("id") int id, Model model, Principal principal){

        System.out.println(principal);
        Quiz question = quizService.getQuestionById(id);
        List<String> answers = new ArrayList<>();

        answers.add(question.getRightAnswer());
        answers.addAll(question.getWrongAnswers());
        Collections.shuffle(answers);

        model.addAttribute("question", question);
        model.addAttribute("answers", answers);

        return "question";
    }

    @PostMapping("/{id}/question")
    public String nextQuestion(@PathVariable("id") int id, Quiz quiz, Principal principal) {

        Agent agent = agentService.findAgentByName(principal.getName());
        if(id == 1) {agent.setScore(0);}

        String selectedAnswer = quiz.getSelectedAnswer();
        Quiz question = quizService.getQuestionById(id);

        if(question.getRightAnswer().equals(selectedAnswer)){
            agent.increaseScore(agent.getScore());
        }
        agentService.saveAgent(agent);

        if(id == quizService.countTotalQuestions()){
            return "redirect:/agent/quiz/result";
        }
        id++;
        return "redirect:/agent/quiz/"+ id +"/question";
    }

    @GetMapping("/result")
    public String result(Principal principal, Model model, HttpServletRequest request, HttpServletResponse response){
        Agent agent = agentService.findAgentByName(principal.getName());

        Long result = agent.getScore() * 100 / quizService.countTotalQuestions();
        String message = "Well done!!!";

        if(result < 90){
            message = "Try again";
            model.addAttribute("result", result);
            model.addAttribute("message", message);
            return "result";
        }

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
