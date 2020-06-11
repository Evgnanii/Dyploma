package com.example.dyploma.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
    Long lobbyCount;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private Button btnStartLobby;
    private EditText etLobbyName;
    private FirebaseListAdapter<Lobby> adapter;
    private EditText packageName, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        btnStartLobby = findViewById(R.id.btn_start_new_lobby);
        etLobbyName = findViewById(R.id.et_lobby_name);
        displayChatMessages();
        password = findViewById(R.id.edit_text_password);
        packageName = findViewById(R.id.et_package_name);
        btnStartLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etLobbyName.getText().toString().equals("") && !packageName.getText().toString().equals("")) {
                    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference userNameRef = rootRef.child("Lobbies").child(etLobbyName.getText().toString());
                    ValueEventListener eventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (!dataSnapshot.exists()) {
                                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                                DatabaseReference userNameRef = rootRef.child("Packages").child(packageName.getText().toString());
                                ValueEventListener eventListener = new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            startNewLobby();
                                        } else
                                            Toast.makeText(getBaseContext(), "Пакет с таким именем не находится на сервере", Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        Log.d("qweq", databaseError.getMessage()); //Don't ignore errors!
                                    }
                                };
                                userNameRef.addListenerForSingleValueEvent(eventListener);
                            } else
                                Toast.makeText(getBaseContext(), "Лобби с таким именем уже существует", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.d("qweq", databaseError.getMessage()); //Don't ignore errors!
                        }
                    };
                    userNameRef.addListenerForSingleValueEvent(eventListener);
                } else {
                    Toast.makeText(getBaseContext(), "Имя пакета или лобби не должно быть пустым", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    void startNewLobby() {
        database = FirebaseDatabase.getInstance();
        etLobbyName = findViewById(R.id.et_lobby_name);
        Lobby lobby = new Lobby
                (etLobbyName.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getUid(), packageName.getText().toString(), password.getText().toString());
        if (!etLobbyName.getText().toString().equals(null)) {
            myRef = database.getReference().child("Lobbies").child(etLobbyName.getText().toString());
            myRef.setValue(lobby);
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    lobbyCount = dataSnapshot.getChildrenCount();
                    Log.d("Count", String.valueOf(lobbyCount));
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
