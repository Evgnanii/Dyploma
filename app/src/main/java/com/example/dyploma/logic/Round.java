package com.example.dyploma.logic;

import java.util.ArrayList;
import java.util.List;

public class Round {
    String roundName;
    ArrayList<Topic> topicsList;

    public List<Topic> getTopicsList() {
        return topicsList;
    }

    public Round() {
    }

    public void setTopicsList(ArrayList<Topic> topicsList) {
        this.topicsList = topicsList;
    }

    public String getRoundName() {
        return roundName;
    }

    public void setRoundName(String roundName) {
        this.roundName = roundName;
    }

    public Round(String roundName, ArrayList<Topic> topicsList) {
        this.roundName = roundName;
        this.topicsList = topicsList;
    }

    public Round(String roundName) {
        this.roundName = roundName;
        this.topicsList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            this.topicsList.add(new Topic("Тема" + i));
        }
    }
}
