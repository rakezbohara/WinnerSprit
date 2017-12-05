package com.app.rakez.winnersprit.model;

import java.util.List;
import java.util.Map;

/**
 * Created by RAKEZ on 12/1/2017.
 */

public class QuestionPush {
    private String question_id;
    private String question;
    private Integer correct;
    private Integer level;
    private Map<String, String> answers;

    public QuestionPush() {
    }

    public QuestionPush(String question_id, String question, Integer correct, Integer level, Map<String, String> answers) {
        this.question_id = question_id;
        this.question = question;
        this.correct = correct;
        this.level = level;
        this.answers = answers;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getCorrect() {
        return correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Map<String, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<String, String> answers) {
        this.answers = answers;
    }
}
