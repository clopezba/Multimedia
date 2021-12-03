package com.example.ud2_10dialogosnotif;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnIngr, btnSuperm, btnFecha, btnHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIngr = (Button) findViewById(R.id.btnIng);
        btnSuperm = (Button) findViewById(R.id.btnSupermer);
        btnFecha = (Button) findViewById(R.id.btnFecha);
        btnHora = (Button) findViewById(R.id.btnHora);

        btnIngr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ArrayList<String> listaIngre = new ArrayList<String>();
                builder.setTitle(R.string.eligeIng)
                        .setMultiChoiceItems(R.array.ingredientes, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                String [] opciones = getResources().getStringArray(R.array.ingredientes);
                                if(isChecked){
                                    listaIngre.add(opciones[which]);
                                }
                                else if (!isChecked){
                                    if (listaIngre.contains(opciones[which])){
                                        listaIngre.remove(opciones[which]);
                                    }
                                }
                            }
                        })
                        .setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                for (String ingr: listaIngre) {
                                    Log.d("consola", ingr);
                                }
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        btnSuperm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertD = new AlertDialog.Builder(view.getContext());
                alertD.setTitle(R.string.textSuper)
                        .setSingleChoiceItems(R.array.supermercados, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int item) {
                                String [] items = getResources().getStringArray(R.array.supermercados);
                                Log.d("consola", items[item]);
                            }
                        })
                        .setPositiveButton("Seleccionar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                AlertDialog dialogo = alertD.create();
                dialogo.show();
            }
        });
        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), FechaActivity.class);
                startActivity(intent);


            }
        });
        btnHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), HoraActivity.class);
                startActivity(intent);


            }
        });

    }

}