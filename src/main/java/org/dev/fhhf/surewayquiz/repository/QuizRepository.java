package org.dev.fhhf.surewayquiz.repository;

import org.dev.fhhf.surewayquiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
}
