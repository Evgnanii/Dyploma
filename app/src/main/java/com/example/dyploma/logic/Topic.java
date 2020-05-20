package com.example.dyploma.logic;

import com.example.dyploma.logic.Question;

import java.io.Serializable;
import java.util.ArrayList;

public class Topic  implements Serializable {
    String topicName;
    ArrayList<Question> questionList;
    public String getTopicName() {
        return topicName;

    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public ArrayList<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(ArrayList<Question> questionList) {
        this.questionList = questionList;
    }

    public Topic(String topicName, ArrayList<Question> questionList) {
        this.topicName = topicName;
        this.questionList = questionList;
    }


    @Override
    public String toString() {
        return "Topic{" +
                "topicName='" + topicName + '\'' +
                ", questionList=" + questionList +
                '}';
    }
}
