package com.example.dyploma.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.dyploma.R;
import com.example.dyploma.Settings;

public class GameSettingsActivity extends AppCompatActivity {
    Button backButton;
    CheckBox cbIsOral;
    CheckBox cbIsMinus;
    CheckBox cbIsFastStartOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_settings);
        cbIsOral = findViewById(R.id.checkboxOral);
        cbIsMinus = findViewById(R.id.checkboxMinus);
        cbIsFastStartOn = findViewById(R.id.checkboxFalseStart);
        cbIsMinus.setChecked(Settings.isMinus);
        cbIsFastStartOn.setChecked(Settings.isFastStarOn);
        cbIsOral.setChecked(Settings.isOral);
        cbIsFastStartOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Settings.isOral = cbIsFastStartOn.isChecked();
            }
        });
        cbIsMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Settings.isMinus = cbIsMinus.isChecked();
            }
        });
        cbIsOral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Settings.isMinus = cbIsOral.isChecked();
            }
        });

        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
