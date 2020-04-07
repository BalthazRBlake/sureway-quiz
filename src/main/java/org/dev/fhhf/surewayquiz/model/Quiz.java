package org.dev.fhhf.surewayquiz.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "quiz")
public class Quiz implements Serializable, Comparable<Quiz> {

    private static final long serialVersionUID = 1234L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "quiz_id", nullable = false)
    private Integer quizId;
    private String question;
    @Column(name = "right_answer")
    private String rightAnswer;
    @ElementCollection
    @Column(name = "wrong_answers")
    private List<String> wrongAnswers;
    private Integer questionNumber;
    @Transient
    private String selectedAnswer;

    public Quiz() {
    }

    public Quiz(List<String> wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    public Quiz(Integer quizId, String question, String rightAnswer, List<String> wrongAnswers) {
        this.quizId = quizId;
        this.question = question;
        this.rightAnswer = rightAnswer;
        this.wrongAnswers = wrongAnswers;
    }

    @Override
    public int compareTo(Quiz o) {
        return this.getQuizId().compareTo(o.getQuizId());
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

    public List<String> getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(List<String> wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(Integer questionNumber) {
        this.questionNumber = questionNumber;
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
