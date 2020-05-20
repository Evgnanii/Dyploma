package com.example.dyploma.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dyploma.R;
import com.example.dyploma.activities.fragments.ProfileFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {
    private Button authorisationButton;
    private Button settingsButton;
    private Button newGameButton;
    DatabaseReference mRef;
    private Button logOutButton;
    private Button profileButton;


    public void fail() {
        Toast.makeText(this, "qweq", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("qwe", "qweq");
        profileButton= findViewById(R.id.profile);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileFragment dialog = new ProfileFragment();

                dialog.show(getSupportFragmentManager(), "MyCustomDialog");
            }
        });
        logOutButton=findViewById(R.id.log_out);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent= new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        newGameButton = findViewById(R.id.newGame);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                    Toast.makeText(getApplicationContext(), "Вы не авторизованы", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), FirebaseAuth.getInstance().getCurrentUser().getUid().toString(), Toast.LENGTH_LONG);
                    Intent intent = new Intent(MainActivity.this, LobbyActivity.class);
                    startActivity(intent);
                }
            }
        });
        settingsButton = (Button) findViewById(R.id.settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                    fail();
                } else {
                    Log.d("qw", FirebaseAuth.getInstance().getCurrentUser().getUid());

                    Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(settingsIntent);
                }
            }

        });
        authorisationButton = (Button) findViewById(R.id.btn_create_pack);
        authorisationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(MainActivity.this, CreatePackActivity.class);
                startActivity(settingsIntent);
                ;
            }
        });
    }
}
