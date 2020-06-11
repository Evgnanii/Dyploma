package com.example.dyploma.logic;

import java.io.Serializable;
import java.util.ArrayList;

public class Topic  implements Serializable {
    String topicName;
    ArrayList<Question> questionList;
    public String getTopicName() {
        return topicName;

    }

    public Topic(String topicName) {
        this.topicName = topicName;
        this.questionList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            questionList.add(new Question("Вопрос " + i, "Ответ " + i, null
                    , 100 * i));
        }
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
