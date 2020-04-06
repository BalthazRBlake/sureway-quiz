package org.dev.fhhf.surewayquiz.service;

import org.dev.fhhf.surewayquiz.model.Quiz;

import java.util.List;

public interface QuizService {

    List<Quiz> getAllQuestions();

    Long countTotalQuestions();

    Quiz getQuestionById(int questionId);

    Quiz insertQuestion(Quiz quiz);
}
