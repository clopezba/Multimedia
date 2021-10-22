package com.example.ud2_3ejercicios;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    Button b;
    LinearLayout l;
    Intent ejemplo;
    ActivityResultLauncher<Intent> someActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cambiarActividad(someActivityResultLauncher);
        obtenerResultado(ejemplo);
    }
    public void cambiarActividad(ActivityResultLauncher<Intent> ResultLauncher){
        ejemplo = new Intent(this, Activity2.class);
        Coche car = new Coche("Ford", "Focus", 21500.50);
        l = (LinearLayout) findViewById(R.id.linear);
        b = (Button) findViewById(R.id.btn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ejemplo.putExtra("texto", getString(R.string.txtExtra));
                ejemplo.putExtra("entero", 23);
                ejemplo.putExtra("real", 16.32);
                ejemplo.putExtra("coma", 25.83f);
                ejemplo.putExtra("coches", car);
                //startActivity(ejemplo); --> Si pedimos que devuelva un resultado, no ponemos este método
                someActivityResultLauncher.launch(ejemplo);
            }
        });
    }
    public void obtenerResultado(Intent in){
        someActivityResultLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK){
                    Log.d("consola", getString(R.string.ResultadoOK));

                    String txt = ejemplo.getStringExtra("texto");
                    int num = ejemplo.getIntExtra("entero", -1);
                    Coche coche = (Coche) ejemplo.getSerializableExtra("coches");

                    Log.d("consola", txt);
                    Log.d("consola", Integer.toString(num));
                    Log.d("consola", coche.toString());
                }
            }
        });
    }
}