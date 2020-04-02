package org.dev.fhhf.surewayquiz.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "quiz")
public class Quiz implements Serializable {

    private static final long serialVersionUID = 1234L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "quiz_id", nullable = false)
    private Integer quizId;
    private String question;
    @Column(name = "right_answer")
    private String rightAnswer;
    @Column(name = "wrong_answers")
    private String wrongAnswers;

    public Quiz() {
    }

    public Quiz(Integer quizId, String question, String rightAnswer, String wrongAnswers) {
        this.quizId = quizId;
        this.question = question;
        this.rightAnswer = rightAnswer;
        this.wrongAnswers = wrongAnswers;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(String wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "quizId=" + quizId +
                ", question='" + question + '\'' +
                ", rightAnswer='" + rightAnswer + '\'' +
                ", wrongAnswers=" + wrongAnswers +
                '}';
    }
}
