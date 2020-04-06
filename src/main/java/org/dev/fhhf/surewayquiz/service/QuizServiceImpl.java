package org.dev.fhhf.surewayquiz.service;

import org.dev.fhhf.surewayquiz.model.Quiz;
import org.dev.fhhf.surewayquiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService{

    @Autowired
    private QuizRepository quizRepo;

    @Override
    public List<Quiz> getAllQuestions() {
        return quizRepo.findAll();
    }

    @Override
    public Long countTotalQuestions() {
        return quizRepo.count();
    }

    @Override
    public Quiz getQuestionById(int questionId) {
        return quizRepo.findById(questionId).get();
    }

    @Override
    public Quiz insertQuestion(Quiz quiz) {
        return quizRepo.save(quiz);
    }
}
