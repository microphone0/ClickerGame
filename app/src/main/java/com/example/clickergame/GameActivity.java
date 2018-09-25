package com.example.clickergame;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private TextView tv_time, tv_clicks;
    private Button b_click, b_start, bonus;

    private CountDownTimer timer;
    private int time = 60;

    private int clicks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tv_time = findViewById(R.id.tv_time);
        tv_clicks = findViewById(R.id.tv_clicks);
        b_click = findViewById(R.id.b_click);
        b_start = findViewById(R.id.b_start);
        bonus = findViewById(R.id.bonus);

        b_start.setEnabled(true);
        b_click.setEnabled(false);
        bonus.setEnabled(false);

        timer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time--;
                tv_time.setText("Time: " + time);
            }

            @Override
            public void onFinish() {
                b_start.setEnabled(true);
                b_click.setEnabled(false);
                tv_time.setText("Time: 0");
            }
        };

        b_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicks++;
                tv_clicks.setText("Clicks: " + clicks);
            }
        });

        b_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
                b_start.setEnabled(false);
                b_click.setEnabled(true);
                clicks = 0;
                time = 30;
                tv_time.setText("Time: " + time);
                tv_clicks.setText("Clicks: " + clicks);
            }
        });
    }
}
