package com.example.ud3_multimediasoundpool;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnAudio1, btnAudio2, btnPausa;
    private SoundPool soundPool;
    private int sound1, sound2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAudio1 = (Button) findViewById(R.id.btnAudio1);
        btnAudio2 = (Button) findViewById(R.id.btnAudio2);
        btnPausa = (Button) findViewById(R.id.btnPausa);

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
            .setMaxStreams(1) //Número de sonidos que se pueden reproducir a la vez
            .setAudioAttributes(audioAttributes)
            .build();

        sound1 = soundPool.load(this, R.raw.sound1, 1);
        sound2 = soundPool.load(this, R.raw.sound2, 1);

        btnAudio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(sound1, 1, 1, 0, 2, 1);
            }
        });
        btnAudio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(sound2, 1, 1, 0, -1, 1);
            }
        });
        btnPausa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.autoPause();
            }
        });

    }

}