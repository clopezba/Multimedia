package com.example.ud2_3ejercicios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Activity2 extends AppCompatActivity {
    View v;
    Button but;
    LinearLayout l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Intent intent = getIntent();
        String txt = intent.getStringExtra("texto");
        int num = intent.getIntExtra("entero", -1);
        double dec = intent.getDoubleExtra("real", -1);
        float comaf = intent.getFloatExtra("coma", -1);
        Coche coche = (Coche) intent.getSerializableExtra("coches");

        Log.d("consola", txt);
        Log.d("consola", Integer.toString(num));
        Log.d("consola", Double.toString(dec));
        Log.d("consola", Float.toString(comaf));
        Log.d("consola", coche.toString());

        l = (LinearLayout) findViewById(R.id.linear2);
        but = (Button) findViewById(R.id.btnAct2);
        but.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}