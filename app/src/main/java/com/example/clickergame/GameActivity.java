package com.example.clickergame;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TextView tv_time, tv_clicks;
    private Button b_click, b_start, bonus, trap;

    private CountDownTimer timer;
    private int time = 60;

    private int clicks = 0;
    private int bonusnum = 50;
    private Random rng = new Random();

    private MediaPlayer appleeating;
    private MediaPlayer pigsnorting;
    private MediaPlayer pigsqueal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tv_time = findViewById(R.id.tv_time);
        tv_clicks = findViewById(R.id.tv_clicks);
        b_click = findViewById(R.id.b_click);
        b_start = findViewById(R.id.b_start);
        bonus = findViewById(R.id.bonus);
        trap = findViewById(R.id.trap);
        appleeating = MediaPlayer.create(GameActivity.this, R.raw.applebite);
        appleeating.setVolume(1000,1000);
        pigsnorting = MediaPlayer.create(GameActivity.this, R.raw.snorting);
        pigsnorting.setVolume(50,50);
        pigsqueal = MediaPlayer.create(GameActivity.this, R.raw.squeal);

        b_start.setEnabled(true);
        b_click.setEnabled(false);
        bonus.setEnabled(false);
        trap.setEnabled(false);

        timer = new CountDownTimer(60000, 1000) {
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
                bonusnum = 50;
                appleeating.release();
                pigsnorting.release();
                pigsqueal.release();
            }
        };

        b_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomnumber = rng.nextInt(bonusnum);
                if (randomnumber <= clicks) {
                    randomnumber= clicks+randomnumber+1;
                }
                clicks++;
                pigsnorting.start();
                tv_clicks.setText("Clicks: " + clicks);
                if (clicks == bonusnum) {
                    bonus.setVisibility(View.VISIBLE);
                    bonus.setEnabled(true);
                }
                if (clicks == randomnumber) {
                    trap.setVisibility(View.VISIBLE);
                    trap.setEnabled(true);
                }
            }
        });

        bonus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appleeating.start();
                bonusnum*=3;
                clicks*=2;
                bonus.setEnabled(false);
                bonus.setVisibility(View.INVISIBLE);
            }
        });

        trap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pigsqueal.start();
                clicks = 0;
                bonusnum = 50;
                trap.setEnabled(false);
                trap.setVisibility(View.INVISIBLE);
            }
        });

        b_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
                b_start.setEnabled(false);
                b_click.setEnabled(true);
                clicks = 0;
                time = 60;
                tv_time.setText("Time: " + time);
                tv_clicks.setText("Clicks: " + clicks);
            }
        });
    }
}
