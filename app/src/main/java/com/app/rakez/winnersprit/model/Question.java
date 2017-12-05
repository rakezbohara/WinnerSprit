package com.app.rakez.winnersprit.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by RAKEZ on 12/1/2017.
 */

public class Question {
    private String question_id;
    private String question;
    private String correct;
    private String level;
    private List<String> answers;

    public Question() {
    }

    public Question(String question_id, String question, String correct, String level, Map<String, String> answers) {
        this.question_id = question_id;
        this.question = question;
        this.correct = correct;
        this.level = level;
        this.answers = new ArrayList<String>(answers.values());
    }

    public String getQuestionId() {
        return question_id;
    }

    public void setQuestionId(String question_id) {
        this.question_id = question_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<String, String> answers) {
        this.answers = new ArrayList<String>(answers.values());
    }
}
