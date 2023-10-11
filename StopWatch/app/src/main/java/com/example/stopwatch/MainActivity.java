package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.text.MessageFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    MaterialButton reset, start, stop;
    int seconds, minutes, milliSeconds;
    long millisecondTime, startTime, timeBuff, updateTime = 0L ;
    Handler handler;


    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            millisecondTime = SystemClock.uptimeMillis() - startTime;
            updateTime = timeBuff + millisecondTime;
            seconds = (int) (updateTime / 1000);
            minutes = seconds / 60;
            seconds = seconds % 60;
            milliSeconds = (int) (updateTime % 1000);

            textView.setText(MessageFormat.format("{0}:{1}:{2}", minutes, String.format(Locale.getDefault(),
                    "%02d", seconds), String.format(Locale.getDefault(),"%01d", milliSeconds)));
            handler.postDelayed(this, 0);
        }
    };

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.time);
        reset = findViewById(R.id.reset_button);
        start = findViewById(R.id.start_button);
        stop = findViewById(R.id.stop_button);

        handler = new Handler(Looper.getMainLooper());

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);
                reset.setEnabled(false);
                stop.setEnabled(true);
                start.setEnabled(false);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeBuff += millisecondTime;
                handler.removeCallbacks(runnable);
                reset.setEnabled(true);
                stop.setEnabled(false);
                start.setEnabled(true);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                millisecondTime = 0L ;
                startTime = 0L ;
                timeBuff = 0L ;
                updateTime = 0L ;
                seconds = 0 ;
                minutes = 0 ;
                milliSeconds = 0 ;
                textView.setText("00:00:00");
            }
        });

        textView.setText("00:00:00");
    }
}