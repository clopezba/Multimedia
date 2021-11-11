/**
 * Aplicación que permite gestionar una base de datos. Permite insertar, eliminar, consultar y actualizar discos de música.
 * @author Cristina López
 * @version 1.0
 */
package com.example.ejercicio08_bbdd;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button anadir, eliminar, consultar;
    Intent int_Anadir, int_Eliminar, int_Consultar;
    protected static SQLiteDatabase db;

    /**
     * Método al que se llama cuando se inicia la pantalla (Activity).
     * En este método se pone la mayoría de los elementos para la inicialización (funciones, varaibles, etc.).
     * @param savedInstanceState Permite volver a un estado anterior de la activiad cuando se vuelve a reiniciar. Admite valor nulo.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anadir = (Button) findViewById(R.id.anadir);
        eliminar = (Button) findViewById(R.id.eliminar);
        consultar = (Button) findViewById(R.id.consultar);

        cambiarActividad();

        //Creación o apertura de base de datos
        db = openOrCreateDatabase("MisDiscos",Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS MisDiscos(Grupo VARCHAR, Disco VARCHAR);");
    }

    /**
     * Gestiona el cambio de pantalla según el botón presionado. Se establecen los 'intents'
     * para las opciones de insertar, eliminar y consultar datos de la base de datos.
     */
    public void cambiarActividad(){
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