package com.example.dyploma.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dyploma.Lobby;
import com.example.dyploma.R;
import com.example.dyploma.activities.fragments.ChooseRoleFragment;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LobbyActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private Button btnStartLobby;
    private EditText etLobbyName;
    private FirebaseListAdapter<Lobby> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        btnStartLobby = findViewById(R.id.btn_start_new_lobby);
        etLobbyName = findViewById(R.id.et_lobby_name);
        displayChatMessages();
        btnStartLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewLobby();
            }
        });


    }


    void startNewLobby() {
        database = FirebaseDatabase.getInstance();
        etLobbyName = findViewById(R.id.et_lobby_name);
        Lobby lobby = new Lobby
                (etLobbyName.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getUid());
        if (!etLobbyName.getText().toString().equals(null)) {
            myRef = database.getReference().child("Lobbies").child(etLobbyName.getText().toString());
            myRef.setValue(lobby);
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Lobby value = dataSnapshot.getValue(Lobby.class);
                    Log.d("qwe", "Value is: " + value.toString());
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("qweq", "Failed to read value.", error.toException());

                }
            });

        } else Toast.makeText(this, "Имя лобби не может быть пустым", Toast.LENGTH_LONG);
    }

    void displayChatMessages() {
        ListView listOfMessages = (ListView) findViewById(R.id.list_lobby);
        adapter = new FirebaseListAdapter<Lobby>(this, Lobby.class,
                R.layout.lobby_list, FirebaseDatabase.getInstance().getReference().child("Lobbies")) {
            @Override
            protected void populateView(View v, Lobby model, int position) {
                // Get references to the views of message.xml
                TextView lobbyName = (TextView) v.findViewById(R.id.tv_creator_name);
                Button button = v.findViewById(R.id.btn_connect);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ChooseRoleFragment dialog = new ChooseRoleFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("LobbyName", model.getLobbyName());
                        dialog.setArguments(bundle);
                        dialog.show(getSupportFragmentManager(), "MyCustomDialog");
                    }
                });
                lobbyName.setText(model.getLobbyName());
            }
        };

        listOfMessages.setAdapter(adapter);
    }
}
