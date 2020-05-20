package com.example.dyploma.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dyploma.R;

public class SettingsActivity extends AppCompatActivity {
    Button backButton;
    Button timeSettingsButton;
    Button gameSettingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        timeSettingsButton = (Button) findViewById(R.id.timeSettingsButton);
        timeSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(SettingsActivity.this, TimeSettingsActivity.class);
                startActivity(settingsIntent);
            }
        });
        gameSettingsButton = (Button) findViewById(R.id.gameSettingsButton);
        gameSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, GameSettingsActivity.class);
                startActivity(intent);
            }
        });

    }
}
