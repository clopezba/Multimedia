package com.example.ud3_multimediavideoview;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    VideoView videoView;
   // Button btnPlay, btnPause;
    int posicion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = (VideoView) findViewById(R.id.videoview);
     //   btnPlay = (Button) findViewById(R.id.btnPlay);
     //   btnPause = (Button) findViewById(R.id.btnPause);

        String videoPath = "android.resource://" + getPackageName()
                + "/" + R.raw.video;

        Uri videoUri = Uri.parse(videoPath);
        videoView.setVideoURI(videoUri);

       /* btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.seekTo(posicion);
                videoView.start();

            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.pause();
                posicion = videoView.getCurrentPosition();
            }
        });*/

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                videoView.start();
                Log.d("videoView", "ready to play");
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Log.d("videoView", "completed");
            }
        });
    }
}