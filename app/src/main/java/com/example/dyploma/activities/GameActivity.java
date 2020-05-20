package com.example.dyploma.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.dyploma.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GameActivity extends AppCompatActivity implements ValueEventListener{
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Bundle args = getIntent().getExtras();
        String role = args.get("Role").toString();
        String lobbyName = args.get("LobbyName").toString();
        mRef = FirebaseDatabase.getInstance().getReference().child(lobbyName);
        if (role.equals("Presenter")){
        mRef.child("presenterName").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());}

        mRef.child("Player1").addValueEventListener(this);
        Log.d("Here",mRef.getKey());


    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        switch (dataSnapshot.getKey()){
            case "PresenterName":

        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
