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
    
    @GetMapping("/saveNew")
    public String home(Model model, Quiz quiz) {

        //this.quiz = quizService.getAllQuestions();
        //Collections.shuffle(this.quiz);

        ///   To init Form   ///
        List<String> wrongAnswers = new ArrayList<>();
        wrongAnswers.add("");wrongAnswers.add("");wrongAnswers.add("");
        model.addAttribute("initQ", new Quiz(wrongAnswers));
        ///***   ***///

        return "editQuestion";
    }

    @PostMapping("/addQuestion")
    public String addQuestion(Quiz quiz, Model model){
        quizService.insertQuestion(quiz);
        return "redirect:/";
    }
}
