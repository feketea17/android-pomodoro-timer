package com.example.pomodorotimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private static final long startTimeMillisecond = 600000; // timer start time in milliseconds

    private TextView TextViewCountDown;
    private Button ButtonPomodoro;
    private Button ButtonPause;
    private Button ButtonStop;

    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private boolean timerPaused;

    private long timeLeftMillisecond = startTimeMillisecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView TextViewCountDown = findViewById(R.id.tw_timer);

        Button ButtonPomodoro = findViewById(R.id.bt_pomodoro);
        Button ButtonPause = findViewById(R.id.bt_pause);
        Button ButtonStop = findViewById(R.id.bt_stop);

        ButtonPomodoro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startTimer();
            }
        });

        ButtonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        ButtonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftMillisecond, 1000) {
            // countDownInterval 1000 means TextView will refresh every second
            @Override
            public void onTick(long millisUntilFinished) {

                timeLeftMillisecond = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {

            }
        }.start();

        timerRunning = true;
    }

    private void pauseTimer() {}

    private void resetTimer() {}

    private void updateCountDownText() {
        int minutes = (int) (timeLeftMillisecond / 1000) / 60;
        int seconds = (int) (timeLeftMillisecond / 1000) % 60;

        String timeLeftFormat = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);

        TextViewCountDown.setText(timeLeftFormat);
    }
}