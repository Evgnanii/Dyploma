package com.example.dyploma.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dyploma.R;
import com.example.dyploma.activities.adapters.TopicListAdapter;
import com.example.dyploma.logic.Topic;

public class EditTopicActivity extends AppCompatActivity {
    EditText editText;
    ExpandableListView expandableListView;
    Button qwe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_round);
        qwe = findViewById(R.id.qwe);
        editText = findViewById(R.id.edit_text_topic_name);

        expandableListView = findViewById(R.id.exListViewTopic);
        editText = findViewById(R.id.edit_text_topic_name);
        Bundle topic = getIntent().getExtras();
        Topic editedTopic = (Topic) topic.getSerializable(Topic.class.getSimpleName());
        editText.setText(editedTopic.getTopicName());
        Log.d("qwe", editedTopic.toString());
        TopicListAdapter topicListAdapter = new TopicListAdapter(this, editedTopic.getQuestionList());

        qwe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editedTopic.setTopicName(editText.getText().toString());
                Intent intent = new Intent();
                intent.putExtra(Topic.class.getSimpleName(), editedTopic);
                Log.d("qwe", editedTopic.getQuestionList().get(0).getQuestion());
                setResult(RESULT_OK
                        , intent);
                finish();
            }
        });
        expandableListView.setAdapter(topicListAdapter);

    }
}
