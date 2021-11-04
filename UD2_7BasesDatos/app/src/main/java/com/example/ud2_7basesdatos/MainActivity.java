package com.example.ud2_7basesdatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView lblGrupo, lblDisco;
    EditText txtGrupo, txtDisco;
    Button btnAnnadir, btnEliminar;
    ListView listaDiscos;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblGrupo = (TextView) findViewById(R.id.lblGrupo);
        lblDisco = (TextView) findViewById(R.id.lblDisco);
        txtGrupo = (EditText) findViewById(R.id.txtGrupo);
        txtDisco = (EditText) findViewById(R.id.txtDisco);
        btnAnnadir = (Button) findViewById(R.id.btnAnnadir);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        listaDiscos = (ListView) findViewById(R.id.listaDiscos);

        db = openOrCreateDatabase("MisDiscos", Context.MODE_PRIVATE, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS MisDiscos(Grupo VARCHAR, Disco VARCHAR);");
        listar();
    }

    public void annadir(View v){
        db.execSQL("INSERT INTO MisDiscos VALUES('" +
                txtGrupo.getText().toString() + "','" +
                txtDisco.getText().toString()+"');");
        Toast.makeText(this, "Se añadió el disco" +
                txtDisco.getText().toString(), Toast.LENGTH_LONG).show();
        listar();

    }
    public void borrar (View v){
        db.execSQL("DELETE FROM MisDiscos WHERE Grupo = '" +
                txtGrupo.getText().toString() + "' AND Disco='" +
                txtDisco.getText().toString() + "'");
        Toast.makeText(this, "Se borró el disco" +
                txtDisco.getText().toString(), Toast.LENGTH_LONG).show();
        listar();

    }
    public void listar(){
        ArrayAdapter<String> adaptador;
        List<String> lista = new ArrayList<String>();
        Cursor c = db.rawQuery("SELECT * FROM MisDiscos", null);

        if(c.getCount() == 0){
            lista.add("No hay registros");
        }
        else{
            while(c.moveToNext()){
                lista.add(c.getString(0) + " - " + c.getString(1));
            }
        }
        adaptador = new ArrayAdapter<String>(getApplicationContext(), R.layout.fila, lista);
        listaDiscos.setAdapter(adaptador);
        c.close();
    }
}