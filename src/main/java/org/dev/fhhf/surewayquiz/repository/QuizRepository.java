package org.dev.fhhf.surewayquiz.repository;

import org.dev.fhhf.surewayquiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {

    Optional<Quiz> findByQuestionNumber(int questionNumber);
}
