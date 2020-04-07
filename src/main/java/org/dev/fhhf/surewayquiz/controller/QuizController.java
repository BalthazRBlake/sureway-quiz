package org.dev.fhhf.surewayquiz.controller;

import org.dev.fhhf.surewayquiz.model.Quiz;
import org.dev.fhhf.surewayquiz.service.AgentService;
import org.dev.fhhf.surewayquiz.service.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/sw/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;
    @Autowired
    private AgentService agentService;

    @GetMapping()
    public  String getAllQuestions(Model model){
        List<Quiz> quiz = quizService.getAllQuestions();
        Collections.sort(quiz);
        model.addAttribute("quiz", quiz);
        //Collections.shuffle(this.quiz);
        return "questions";
    }
    @GetMapping("/{id}/initEditQ")
    public String initEditQ(@PathVariable("id") int id, Model model) {
        Quiz quiz;
        if(id == 0){
            ///   To init Form   ///
            List<String> wrongAnswers = new ArrayList<>();
            wrongAnswers.add("");wrongAnswers.add("");wrongAnswers.add("");
            quiz = new Quiz(0, "", "", wrongAnswers);
            ///***   ***///
        }else {
            quiz = quizService.getQuestionById(id);
        }
        model.addAttribute("initQ", quiz);
        return "editQuestion";
    }

    @PostMapping("/{id}/editQuestion")
    public String editQuestion(Quiz quiz, Model model){
        quizService.insertQuestion(quiz);
        return "redirect:/sw/quiz";
    }
}
