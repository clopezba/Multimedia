package com.example.practica2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Nivel: 0 = Principiante, 1 = Amateur, 2 = Avanzado
    int nivel = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        LinearLayout ll = (LinearLayout) findViewById(R.id.linear);

        TableLayout etiquetaTabla = dibujarTabla(8,8);
        ll.addView(etiquetaTabla);
    }

    //--------------- MENÚ -------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.instruc:
                crearDialogoInstrucciones().show();
                return true;
            case R.id.nuevo:

                //CARGAR JUEGO!!!!

            case R.id.config:
                crearDialogoConfig().show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public Dialog crearDialogoInstrucciones(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.instrucciones)
                .setTitle(R.string.instrucTitulo);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        return builder.create();
    }
    public Dialog crearDialogoConfig(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.configTitulo)
                .setSingleChoiceItems(R.array.configNivel, nivel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInt, int item) {
                        switch(item){
                            case 0:
                                nivel = 0;
                                
                                break;
                            case 1:
                                nivel = 1;
                                setContentView(R.layout.activity_main);
                                break;
                            case 2:
                                nivel = 2;
                                break;
                        }
                    }
                });
        builder.setPositiveButton(R.string.volver, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        return builder.create();
    }
    public TableLayout dibujarTabla(int numeroFilas, int numeroColumnas){
        TableLayout tabla = new TableLayout(this);
        tabla.setGravity(Gravity.CENTER);

        TableRow fila = new TableRow(this);
        int numeroCeldas = numeroFilas * numeroColumnas;
        int contadorColumnas = 0;
        int contadorFilas = 0;

        for (int i = 0; i <= numeroCeldas ; i++) {
            if(contadorColumnas == numeroColumnas){
                tabla.addView(fila);
                fila = new TableRow(this);
                contadorColumnas = 0;
                contadorFilas++;
            }
            RelativeLayout borde = new RelativeLayout(this);
            borde.setPadding(2, 2, 0, 0);
            if(contadorColumnas == numeroColumnas-1){
                borde.setPadding(2,2,2,0);
            }
            if(contadorFilas == numeroFilas-1){
                borde.setPadding(2,2,0,2);
                if(contadorColumnas == numeroColumnas-1){
                    borde.setPadding(2,2,2,2);
                }
            }
            borde.setBackgroundColor(Color.parseColor("#FF00FF"));

            Button btn = new Button(this);
            btn.setLayoutParams(new LinearLayout.LayoutParams(130, 190));
            btn.setPadding(2,2,2,2);
            btn.setBackgroundColor(Color.parseColor("#663377"));
            borde.addView(btn);
            fila.addView(borde);
            contadorColumnas++;

        }
        return tabla;
    }
}