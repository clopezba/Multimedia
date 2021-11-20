package com.example.practica2;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    LinearLayout ll, fil;
    Button btn;
    int filas = 8, columnas = 8, bombas = 10;
    int contBombas = bombas;

    //Nivel: 0 = Principiante, 1 = Amateur, 2 = Avanzado
    int nivel = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crearTabla(filas,columnas);

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
                        crearTabla(filas,columnas);
                        break;
                    case 1:
                        setContentView(R.layout.activity_main);
                        crearTabla(filas,columnas);
                        break;
                    case 2:
                        setContentView(R.layout.activity_main);
                        crearTabla(filas,columnas);
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
                                filas = 8;
                                columnas = 8;
                                bombas = 10;
                                contBombas = bombas;
                                setContentView(R.layout.activity_main);
                                crearTabla(filas,columnas);

                                break;
                            case 1:
                                nivel = 1;
                                filas = 12;
                                columnas = 12;
                                bombas = 30;
                                contBombas = bombas;
                                setContentView(R.layout.activity_main);
                                crearTabla(filas,columnas);
                                break;
                            case 2:
                                nivel = 2;
                                filas = 16;
                                columnas = 16;
                                bombas = 60;
                                contBombas = bombas;
                                setContentView(R.layout.activity_main);
                                crearTabla(filas,columnas);
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

    /**
     * Crea la matriz sobre la que se añadirán los botones. A éstos se le añade un número aleatorio único que determinará
     * si hay bomba o no.
     * @param filas
     * @param columnas
     */
    public void crearTabla(int filas, int columnas){
        ll = (LinearLayout) findViewById(R.id.linear);
        ll.setBackgroundColor(getColor(R.color.white));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);

        ArrayList<Integer> listaNumeros = numAleatorios(filas*columnas);
        int cont = 0;

        for (int i = 0; i < filas; i++) {
            fil = new LinearLayout(this);
            fil.setLayoutParams(lp);
            fil.setOrientation(LinearLayout.HORIZONTAL);
            fil.setGravity(Gravity.CENTER);
            fil.setBackgroundColor(getColor(R.color.black));


            for (int j = 0; j < columnas; j++) {
                Log.d("consola", listaNumeros.get(cont).toString());
                btn = new Button(this);
                btn.setBackground(getDrawable(R.drawable.border));
                btn.setId(cont);
                btn.setLayoutParams(lp);
                btn.setTag(listaNumeros.get(cont));
                if(filas == 16)
                    btn.setTextSize(11);
                else
                    btn.setTextSize(18);
                btn.setOnClickListener(this);
                btn.setOnLongClickListener(this);
                cont++;
                fil.addView(btn);
            }
            ll.addView(fil);
        }
    }

    /**
     * Genera un ArrayList con tantos números aleatorios como celdas haya en la matriz.
     * @param celdas
     * @return
     */
    public ArrayList<Integer> numAleatorios(int celdas){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<celdas; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        return list;
    }

    /**
     * Crea una matriz del tamaño de la cuadrícula y resetea los valores a 0
      * @param filas
     * @param columnas
     * @return
     */
    public int[][] crearMatriz(int filas, int columnas){
        int matriz[][] = new int [filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = 0;
            }
        }
        return matriz;
    }

    /**
     * A partir de la matriz generada, recorre los elementos que contiene el layout. Si el elemento tiene una bomba (según el valor de la 'Tag')
     * pone un número negativo en su respectiva posición de la matriz. Recorre los elementos adyacentes y aumenta su valor coreespondiente que
     * será la pista que indica las bombas que hay a su alrededor.
     * @param filas
     * @param columnas
     * @param bombas
     * @return
     */
    public ArrayList<Integer> recorrer(int filas, int columnas, int bombas){
        int matriz[][] = crearMatriz(filas, columnas);
        ArrayList<Integer> pistasBombas = new ArrayList<Integer>();
        LinearLayout lin;
        View v, v2;
        for (int i = 0; i < ll.getChildCount(); i++) {
            v = ll.getChildAt(i);
            lin = (LinearLayout) v;

            for (int j = 0; j < lin.getChildCount(); j++) {
                v2 = lin.getChildAt(j);
                if ((Integer)v2.getTag() < bombas) {
                    matriz[i][j] = -10;
                    if (i == 0 && j==0) { //Arriba - izquierda
                       matriz[i][j+1] += 1;
                       matriz[i+1][j] += 1;
                       matriz[i+1][j+1] +=1;
                    }
                    else if(i==0 && j==(columnas-1)){ //Arriba - derecha
                        matriz[i][j-1] += 1;
                        matriz[i+1][j-1] += 1;
                        matriz[i+1][j] += 1;
                    }
                    else if(i==(filas-1) && j==0){ //Abajo - izquierda
                        matriz[i-1][j] += 1;
                        matriz[i-1][j+1] += 1;
                        matriz[i][j+1] += 1;
                    }
                    else if(i==(filas-1) && j==(columnas-1)){ // Abajo - derecha
                        matriz[i][j-1] += 1;
                        matriz[i-1][j-1] += 1;
                        matriz[i-1][j] += 1;
                    }
                    else if(i==0 && j>0 && j<(columnas-1)){ //Fila arriba
                        matriz[i][j-1] += 1;
                        matriz[i+1][j-1] += 1;
                        matriz[i+1][j] += 1;
                        matriz[i+1][j+1] += 1;
                        matriz[i][j+1] += 1;
                    }
                    else if(j==0 && i>0 && i<(filas-1)){ //Fila izquierda
                        matriz[i-1][j] += 1;
                        matriz[i-1][j+1] += 1;
                        matriz[i][j+1] += 1;
                        matriz[i+1][j] += 1;
                        matriz[i+1][j+1] += 1;
                    }
                    else if(i==(filas-1) && j>0 && j<(columnas-1)){ //Fila abajo
                        matriz[i][j-1] += 1;
                        matriz[i-1][j-1] += 1;
                        matriz[i-1][j] += 1;
                        matriz[i-1][j+1] += 1;
                        matriz[i][j+1] += 1;
                    }
                    else if(j==(columnas-1) && i>0 && i<(filas-1)){ //Fila derecha
                        matriz[i-1][j] += 1;
                        matriz[i-1][j-1] += 1;
                        matriz[i][j-1] += 1;
                        matriz[i+1][j-1] += 1;
                        matriz[i+1][j] += 1;
                    }
                    else if(i>0 && i<(filas-1) && j>0 && j<(columnas-1)){ //Centro
                        matriz[i-1][j-1] += 1;
                        matriz[i-1][j] += 1;
                        matriz[i-1][j+1] += 1;
                        matriz[i][j-1] += 1;
                        matriz[i][j+1] += 1;
                        matriz[i+1][j-1] += 1;
                        matriz[i+1][j] += 1;
                        matriz[i+1][j+1] += 1;
                    }
                }
            }
        }
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                pistasBombas.add(matriz[i][j]);
            }
        }
        return pistasBombas;
    }
    @Override
    public void onClick(View view) {
        ArrayList<Integer> valores = recorrer(filas, columnas, bombas);
        int numCelda = Integer.parseInt(((Button)view).getTag().toString());

        if(numCelda < bombas){
            Button b = (Button) view;
            b.setBackground(getDrawable(R.drawable.mal));
            deshabilitarCeldas();
            Toast.makeText(this, getString(R.string.click_mal), Toast.LENGTH_LONG).show();
        }
        else{
            Button b = (Button) view;
            b.setText(valores.get(view.getId()).toString());

            b.setOnClickListener(null);
            b.setOnLongClickListener(null);
        }
    }


    @Override
    public boolean onLongClick(View view) {
        ArrayList<Integer> valores = recorrer(filas, columnas, bombas);
        int numCelda = Integer.parseInt(((Button)view).getTag().toString());

        if(numCelda < bombas){
            Button b = (Button) view;
            b.setBackground(getDrawable(R.drawable.bien));
            b.setOnLongClickListener(null);
            b.setOnClickListener(null);
            contBombas--;
            if(contBombas > 0) {
                String texto = getString(R.string.longClick_bien1) + " "+ contBombas + " " + getString(R.string.longClick_bien2);
                Toast.makeText(this, texto, Toast.LENGTH_LONG).show();
                b.setEnabled(false);
            }
            else
                Toast.makeText(this, getString(R.string.longClick_ganado), Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, getString(R.string.longClick_mal), Toast.LENGTH_LONG).show();
            deshabilitarCeldas();
        }

        return false;
    }
    public void deshabilitarCeldas() {
        LinearLayout lin;
        View v, v2;
        for (int i = 0; i < ll.getChildCount(); i++) {
            v = ll.getChildAt(i);
            lin = (LinearLayout) v;

            for (int j = 0; j < lin.getChildCount(); j++) {
                v2 = lin.getChildAt(j);
                v2.setEnabled(false);
            }
        }
    }

}
