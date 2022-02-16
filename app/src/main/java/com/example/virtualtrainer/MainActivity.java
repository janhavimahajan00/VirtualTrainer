  package com.example.virtualtrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

  public class MainActivity extends AppCompatActivity {

          private static final long START_TIME_IN_MILLIS = 120000;
          private TextView mTextViewCountDown;
          private Button mButtonStartPause;
          private Button mButtonReset;
          private  MediaPlayer mediaplayer;
          private CountDownTimer mCountDownTimer;
          private boolean mTimerRunning;
          private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

  mediaplayer = MediaPlayer.create(this,R.raw.bt);
        mediaplayer.start();
       
        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.button_reset);

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
        updateCountDownText();
    }
      private void startTimer() {
          mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
              @Override
              public void onTick(long millisUntilFinished) {
                  mTimeLeftInMillis = millisUntilFinished;
                  updateCountDownText();
              }
              @Override
              public void onFinish() {
                  mTimerRunning = false;
                  mButtonStartPause.setText("Start");
                  mButtonStartPause.setVisibility(View.INVISIBLE);
                  mButtonReset.setVisibility(View.VISIBLE);
              }
          }.start();
          Toast.makeText(this, "Let's start", Toast.LENGTH_SHORT).show();
          mTimerRunning = true;
          mButtonStartPause.setText("pause");
          mButtonReset.setVisibility(View.INVISIBLE);
      }
      private void pauseTimer() {
          mCountDownTimer.cancel();
          mTimerRunning = false;
          mButtonStartPause.setText("Start");
          mButtonReset.setVisibility(View.VISIBLE);
      }
      private void resetTimer() {
          mTimeLeftInMillis = START_TIME_IN_MILLIS;
          updateCountDownText();
          mButtonReset.setVisibility(View.INVISIBLE);
          mButtonStartPause.setVisibility(View.VISIBLE);
      }
      private void updateCountDownText() {
          int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
          int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
          String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
          mTextViewCountDown.setText(timeLeftFormatted);
      }

      public void mainactivity(View v){
          Toast.makeText(this, "opening next", Toast.LENGTH_SHORT).show();
          Intent intent=new Intent(this, MainActivity2.class);
          startActivity(intent);

      }


}
