package com.example.camposdegolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button baseDatos, web;
    Intent intbd, intweb;
    LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baseDatos = (Button) findViewById(R.id.btnBD);
        web = (Button) findViewById(R.id.btnWeb);
        linear = (LinearLayout) findViewById(R.id.linear);



        cambiarActividad();
    }

    public void cambiarActividad(){
            intbd = new Intent(this, Listado.class);
            baseDatos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intbd.putExtra("boton", "baseDatos");
                    startActivity(intbd);

                }
            });

            intweb = new Intent(this, Listado.class);
            web.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intweb.putExtra("boton", "Web");
                    startActivity(intweb);
                }
            });
        }

}