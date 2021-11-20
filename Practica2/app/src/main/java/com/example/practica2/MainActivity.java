package com.example.practica2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    LinearLayout ll, fil, col;
    Button btn;
    ImageButton imgBtn;

    //Nivel: 0 = Principiante, 1 = Amateur, 2 = Avanzado
    int nivel = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crearTabla(8,8, 10);
        recorrer(8,8,10);

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
                switch (nivel){
                    case 0:
                        setContentView(R.layout.activity_main);
                        crearTabla(8,8, 10);
                        break;
                    case 1:
                        setContentView(R.layout.activity_main);
                        crearTabla(12,12, 30);
                        break;
                    case 2:
                        setContentView(R.layout.activity_main);
                        crearTabla(16,16, 60);
                        break;
                }
                return true;
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
                                setContentView(R.layout.activity_main);
                                crearTabla(8,8, 10);
                                break;
                            case 1:
                                nivel = 1;
                                setContentView(R.layout.activity_main);
                                crearTabla(12,12, 30);
                                break;
                            case 2:
                                nivel = 2;
                                setContentView(R.layout.activity_main);
                                crearTabla(16,16, 60);
                                break;
                        }
                    }
                });
        builder.setPositiveButton(R.string.volver, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        return builder.create();
    }
    public void crearTabla(int filas, int columnas, int bombas){
        ll = (LinearLayout) findViewById(R.id.linear);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lp.weight = 1.0F;

        ArrayList<Integer> listaNumeros = numAleatorios(filas*columnas);
        int cont = 0;

        for (int i = 0; i < filas; i++) {
            fil = new LinearLayout(this);
            fil.setLayoutParams(lp);
            fil.setOrientation(LinearLayout.HORIZONTAL);
            fil.setBackgroundColor(getColor(R.color.black));

            for (int j = 0; j < columnas; j++) {
                col = new  LinearLayout(this);
                col.setLayoutParams(lp);
                col.setPadding(1,1,1,1);
                col.setBackgroundColor(getColor(R.color.white));
                col.setTag(listaNumeros.get(cont));
                cont++;

                if((Integer)col.getTag() < bombas){
                    imgBtn = new ImageButton(this);
                    imgBtn.setBackgroundColor(getColor(R.color.teal_700));
                    imgBtn.setLayoutParams(lp);
                    col.addView(imgBtn);
                }
                else {
                    btn = new Button(this);
                    btn.setBackgroundColor(getColor(R.color.teal_700));
                    btn.setLayoutParams(lp);
                    btn.setTag("0");
                    col.addView(btn);
                }
                fil.addView(col);
            }
            ll.addView(fil);
        }
    }
    public ArrayList<Integer> numAleatorios(int celdas){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<celdas; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        return list;
    }
    public void recorrer(int filas, int columnas, int bombas){
        View matriz[][] = new View[filas][columnas];
        for (int i = 0; i < ll.getChildCount(); i++) {
            for (int j = 0; j < fil.getChildCount(); j++) {
                matriz[i][j] = fil.getChildAt(j);

            }
        }
    }

}
