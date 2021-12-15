package com.example.ud3_multimediavideomediarecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SurfaceHolder.Callback{
    File videoFile = null;
    MediaRecorder recorder;
    SurfaceHolder holder;
    Button btnVer;
    boolean recording = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO}, 0);
        }
        ContextWrapper cw = new ContextWrapper(this);
        videoFile = new File(cw.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "video.mp4");

        //Inicializaremos la pantalla para que la ponga en horizontal
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //inicializaremos el MediaRecorder para poder hacer la grabación
        recorder = new MediaRecorder();
        initRecorder();

        setContentView(R.layout.activity_main);
        btnVer = (Button) findViewById(R.id.btnVideo);

        //Usaremos el SurfaceView/SurfaceHolder para poder visualizar lo que se está grabando.
        SurfaceView cameraView = (SurfaceView) findViewById(R.id.superficie);
        holder = cameraView.getHolder();
        holder.addCallback(this);

        //Podremos hacer clic en la superficie para comenzar a grabar o parar de grabar
        cameraView.setClickable(true);
        cameraView.setOnClickListener(this);

        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ruta = videoFile.getAbsolutePath();
                Intent intent = new Intent(getApplicationContext(), VideoActivity.class);
                intent.putExtra("file", ruta);
                startActivity(intent);
            }
        });

    }
    private void initRecorder() {
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

        CamcorderProfile cpHigh = CamcorderProfile
                .get(CamcorderProfile.QUALITY_HIGH);
        recorder.setProfile(cpHigh);
        recorder.setOutputFile(videoFile.getAbsolutePath());
    }
    private void prepareRecorder() {
        //conseguimos visualizar en directo lo que estamos grabando
        recorder.setPreviewDisplay(holder.getSurface());

        try {
            recorder.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            finish();
        } catch (IOException e) {
            e.printStackTrace();
            finish();
        }
    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        prepareRecorder();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        if (recording) {
            recorder.stop();
            recording = false;
        }
        recorder.release();
        finish();
    }

    @Override
    public void onClick(View view) {
        if (recording) {
            recorder.stop();
            recording = false;

        } else {
            recording = true;
            recorder.start();
        }
    }
}