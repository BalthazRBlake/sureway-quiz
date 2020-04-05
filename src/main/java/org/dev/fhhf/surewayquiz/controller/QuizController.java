package org.dev.fhhf.surewayquiz.controller;

import org.dev.fhhf.surewayquiz.model.Quiz;
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
    private int score = 0;

    @GetMapping()
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

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") int id, Model model){

        Quiz question = quizService.getQuestionById(id);
        List<String> answers = new ArrayList<>();

        answers.add(question.getRightAnswer());
        answers.addAll(question.getWrongAnswers());
        Collections.shuffle(answers);

        model.addAttribute("question", question);
        model.addAttribute("answers", answers);

        return "question";
    }

    @PostMapping("/question/{id}")
    public String nextQuestion(@PathVariable("id") int id, Quiz quiz) {

        String selectedAnswer = quiz.getSelectedAnswer();
        Quiz question = quizService.getQuestionById(id);

        System.out.println("RA   :::   " + quiz.getSelectedAnswer());
        if(question.getRightAnswer().equals(selectedAnswer)){
            this.score++;
        }
        System.out.println("TOTAL   :::   " + this.score);
        id++;
        return "redirect:/question/" + id;
    }

    @PostMapping("/addQuestion")
    public String addQuestion(Quiz quiz, Model model){
        quizService.insertQuestion(quiz);
        return "redirect:/";
    }
}
