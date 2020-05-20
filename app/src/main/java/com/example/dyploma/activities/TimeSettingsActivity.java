package com.example.dyploma.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;


import androidx.appcompat.app.AppCompatActivity;

import com.example.dyploma.R;
import com.example.dyploma.Settings;


public class TimeSettingsActivity extends AppCompatActivity {
    Button backButton;
    private SeekBar sekBarFinal;
    private SeekBar sekBarAnswer;
    private SeekBar sekBarClick;
    private EditText textViewFinal;
    private EditText textViewAnswer;
    private EditText textViewClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_settings);
        sekBarAnswer = (SeekBar) findViewById(R.id.timeToAnswer);
        sekBarFinal = (SeekBar) findViewById(R.id.timeToFinal);
        sekBarClick = (SeekBar) findViewById(R.id.timeToClick);
        textViewAnswer = (EditText) findViewById(R.id.editTextTimeToAnswer);
        textViewFinal = (EditText) findViewById(R.id.editTextTimeToFinal);
        textViewClick = (EditText) findViewById(R.id.editTextTimeToClick);
        backButton = (Button) findViewById(R.id.backButton);
        sekBarAnswer.setProgress(Settings.timeToAnswer);
        sekBarFinal.setProgress(Settings.timeToFinal);
        sekBarClick.setProgress(Settings.timeToClick);
        textViewClick.setText(String.valueOf(Settings.timeToClick));
        textViewFinal.setText(String.valueOf(Settings.timeToFinal));
        textViewAnswer.setText(String.valueOf(Settings.timeToAnswer));
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        textViewAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sekBarAnswer.setProgress(Integer.parseInt(String.valueOf(textViewAnswer.getText())));
                Settings.timeToAnswer = sekBarAnswer.getProgress();
            }
        });
        textViewFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sekBarFinal.setProgress(Integer.parseInt(String.valueOf(textViewFinal.getText())));
                Settings.timeToFinal = sekBarFinal.getProgress();
            }
        });
        textViewClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sekBarClick.setProgress(Integer.parseInt(String.valueOf(textViewClick.getText())));
                Settings.timeToClick = sekBarClick.getProgress();
            }
        });
        sekBarClick.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewClick.setText(String.valueOf(progress));
                Settings.timeToClick = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sekBarFinal.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewFinal.setText(String.valueOf(progress));
                Settings.timeToFinal = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sekBarAnswer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewAnswer.setText(String.valueOf(progress));
                Settings.timeToAnswer = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}
