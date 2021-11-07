package com.example.ejercicio08_bbdd;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class ConsultarActivity extends AppCompatActivity {
    SQLiteDatabase baseDatos = MainActivity.db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);


        listar();

    }

    public void listar(){
        ArrayAdapter<String> adaptador;
        List<String> lista = new ArrayList<String>();
        Cursor c = baseDatos.rawQuery("SELECT * FROM MisDiscos", null);

        if(c.getCount() == 0){
            lista.add("No hay registros");
        }
        else{
            while(c.moveToNext()){
                lista.add(c.getString(0) + " - " + c.getString(1));
            }
        }
        //adaptador = new ArrayAdapter<String>(getApplicationContext(), R.layout.fila, lista);
        //listaDiscos.setAdapter(adaptador);
        c.close();
    }
}