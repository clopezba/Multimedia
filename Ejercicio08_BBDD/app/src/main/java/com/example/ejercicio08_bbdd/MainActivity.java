package com.example.ejercicio08_bbdd;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button anadir, eliminar, consultar;
    Intent int_Anadir, int_Eliminar, int_Consultar;
    ActivityResultLauncher<Intent> someActivityResultLauncher;

    protected static SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anadir = (Button) findViewById(R.id.anadir);
        eliminar = (Button) findViewById(R.id.eliminar);
        consultar = (Button) findViewById(R.id.consultar);

        cambiarActividad(someActivityResultLauncher);

        db = openOrCreateDatabase("MisDiscos",Context.MODE_PRIVATE, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS MisDiscos(Grupo VARCHAR, Disco VARCHAR);");



    }
    public void cambiarActividad(ActivityResultLauncher<Intent> ResultLauncher){
        int_Anadir = new Intent(this, AnadirActivity.class);
        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(int_Anadir);
            }
        });
        int_Eliminar = new Intent(this, EliminarActivity.class);
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(int_Eliminar);
            }
        });
        int_Consultar = new Intent(this, ConsultarActivity.class);
        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(int_Consultar);
            }
        });
    }

}