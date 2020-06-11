package com.example.dyploma.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.dyploma.logic.Round;
import com.example.dyploma.logic.Topic;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class CreatePackActivity extends AppCompatActivity {
    ExpandableListView expList;
    Pack pack;
    int i;
    PackListAdapter adapter;
    private FirebaseDatabase database;
    private EditText packName;
    private ListView listView;
    private Button btnNewRound;
    private Button btnSavePack;
    private DatabaseReference myRef;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        Bundle topic = data.getExtras();
        Topic editedTopic = (Topic) topic.getSerializable(Topic.class.getSimpleName());
        pack.getRoundList().get((int) requestCode / 10).getTopicsList().set(requestCode - ((int) (requestCode / 10)), editedTopic);
        adapter.notifyDataSetInvalidated();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        i = 1;
        setContentView(R.layout.activity_create_pack);
        packName = findViewById(R.id.edit_text_pack_name);
        expList = findViewById(R.id.exListView);
        btnNewRound = findViewById(R.id.btn_add_round);
        btnNewRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pack.getRoundList().add(new Round("Раунд" + i));
                i++;
                adapter.notifyDataSetInvalidated();
            }
        });
        btnSavePack = findViewById(R.id.btn_save_pack);
        btnSavePack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                if (!packName.getText().toString().equals("")) {
                    packName.setText(packName.getText());
                    myRef = database.getReference().child("Packages").child(packName.getText().toString());
                    myRef.setValue(pack);
                    Toast.makeText(getBaseContext(), "Пакет отправлен на сервер", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "Задайте имя пакета", Toast.LENGTH_LONG).show();
                }
            }
        });
        pack = new Pack("Custom Pack", new ArrayList<Round>());
        adapter = new PackListAdapter(this, pack.getRoundList());

        expList.setAdapter(adapter);
        expList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getBaseContext(), pack.getRoundList().get(groupPosition).getRoundName().toString() + " " + pack.getRoundList().get(groupPosition).getTopicsList().get(childPosition).getTopicName(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CreatePackActivity.this, EditTopicActivity.class);
                intent.putExtra(Topic.class.getSimpleName(), pack.getRoundList().get(groupPosition).getTopicsList().get(childPosition));
                startActivityForResult(intent, groupPosition * 10 + childPosition);
                Log.d("qwe", groupPosition + " " + childPosition + " " + groupPosition * 10 + childPosition);
                return true;
            }
        });
    }
}



