package com.example.dyploma.logic;

import java.io.Serializable;

public class Question  implements Serializable {
    String question;
    String answer;
    String description;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Question(String question, String answer, String description, int cost) {
        this.question = question;
        this.answer = answer;
        this.description = description;
        this.cost = cost;
    }

    int cost;
}
