package com.example.dyploma.activities;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dyploma.R;
import com.example.dyploma.activities.adapters.PackListAdapter;
import com.example.dyploma.logic.Pack;
import com.example.dyploma.logic.Question;
import com.example.dyploma.logic.Round;
import com.example.dyploma.logic.Topic;

import java.util.ArrayList;


public class CreatePackActivity extends AppCompatActivity {
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        Bundle topic = data.getExtras();
        Topic editedTopic = (Topic) topic.getSerializable(Topic.class.getSimpleName());
        pack.getRoundList().get(0).getTopicsList().set(0, editedTopic);
    }

    private EditText packName;
    private ListView listView;
    private Button btnNewRound;
    ExpandableListView expList;
    Pack pack;


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pack);
        expList = findViewById(R.id.exListView);
        Topic topic = new Topic("Кино 2000х", new ArrayList<Question>());
        Topic topic1 = new Topic("Кино 2010x", new ArrayList<Question>());
        topic.getQuestionList().add(new Question("qweq", "qweq", "qweqwe", 100));
        topic.getQuestionList().add(new Question("qweq", "qweq", "qweqwe", 100));
        topic.getQuestionList().add(new Question("qweq", "qweq", "qweqwe", 100));
        topic.getQuestionList().add(new Question("qweq", "qweq", "qweqwe", 100));
        topic.getQuestionList().add(new Question("qweq", "qweq", "qweqwe", 100));

        ArrayList topicList = new ArrayList();
        topicList.add(topic);
        topicList.add(topic1);
        Round round = new Round("qwe", topicList);
        Round round2 = new Round("qwe", topicList);
        ArrayList rounds = new ArrayList();
        rounds.add(round);
        rounds.add(round2);

        pack = new Pack("Custom Pack", rounds);
        PackListAdapter adapter = new PackListAdapter(this, pack.getRoundList());

        expList.setAdapter(adapter);
        expList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getBaseContext(), pack.getRoundList().get(groupPosition).getRoundName().toString() + " " + pack.getRoundList().get(groupPosition).getTopicsList().get(childPosition).getTopicName(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CreatePackActivity.this, EditTopicActivity.class);
                intent.putExtra(Topic.class.getSimpleName(), pack.getRoundList().get(groupPosition).getTopicsList().get(childPosition));
                startActivityForResult(intent, groupPosition * 10 + childPosition);
                return true;
            }
        });
    }
}



