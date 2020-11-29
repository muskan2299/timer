package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    SeekBar seekBar;
    Boolean timer = false;
    Button go;
    CountDownTimer countDownTimer;

    public void go(View view){

        if (timer){
            textView.setText("00:30");
            seekBar.setProgress(30);
            timer = false;
            countDownTimer.cancel();
            go.setText("GO!");
            seekBar.setEnabled(true);
        }
        else {

            timer = true;
            seekBar.setEnabled(false);
            go.setText("STOP!");

            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {
                    Update((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();

                    textView.setText("00:30");
                    seekBar.setProgress(30);
                    timer = false;
                    countDownTimer.cancel();
                    seekBar.setEnabled(true);
                    go.setText("GO!");
                }
            }.start();
        }
    }

    public void Update(int i){
        int mins = i/60;
        int secs = i - (mins * 60);

        String s = Integer.toString(secs);
        if(secs<=9)
            s = "0"+s;

        textView.setText(Integer.toString(mins)+":"+s);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);
        go = findViewById(R.id.button2);

        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Update(i);
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