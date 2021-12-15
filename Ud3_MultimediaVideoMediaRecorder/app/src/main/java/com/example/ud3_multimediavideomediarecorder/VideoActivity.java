package com.example.ud3_multimediavideomediarecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.File;
import java.io.IOException;

public class VideoActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    private MediaPlayer media;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        media = new MediaPlayer();
        SurfaceView superficie = (SurfaceView)findViewById(R.id.superficie);
        // Obteniendo el objeto SurfaceHolder a partir del SurfaceView
        SurfaceHolder holder = superficie.getHolder();
        holder.addCallback(this);

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        Intent intent = getIntent();
        File direcorio = (File) intent.getExtras().get("file");

        try {
            media.setDisplay(holder);
            media.setDataSource(getApplicationContext(), Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video));
            media.prepare();
            media.start();
        } catch (IllegalArgumentException e) {
            Log.d("MEDIA_PLAYER", e.getMessage());
        } catch (IllegalStateException e) {
            Log.d("MEDIA_PLAYER", e.getMessage());
        } catch (IOException e) {
            Log.d("MEDIA_PLAYER", e.getMessage());
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }
}