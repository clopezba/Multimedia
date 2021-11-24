/**
 * Aplicación juego BuscAliens, similar al buscaminas. Encontrar en una matriz todos los aliens escondidos sin equivocarse.
 * @author Cristina López
 * @version 1.0
 */
package com.example.practica2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    LinearLayout ll, fil;
    Button btn;
    int filas, columnas, aliens, contAliens;
    int nivel;  //Nivel: 0 = Principiante, 1 = Amateur, 2 = Avanzado

    /**
     * Método al que se llama cuando se inicia la pantalla (Activity).
     * En este método se pone la mayoría de los elementos para la inicialización (funciones, varaibles, etc.).
     * @param savedInstanceState Permite volver a un estado anterior de la activiad cuando se vuelve a reiniciar. Admite valor nulo.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Inicia por defecto en el nivel principiante (0).
        seleccionarDificultad(nivel);
    }
    //--------------- MENÚ -------------------
    /**
     * Método para crear un menú de opciones y añadirle los ítems que contendrá.
     * @param menu El menú de opciones donde se colocarán los items.
     * @return Valor  de tipo booleano: 'True' para que se muestre el menú, 'False' para que no se muestre.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    /**
     * Método al que se llama cuando se selecciona un ítem del menú
     * @param item El ítem que ha sido seleccionado. No puede ser nulo.
     * @return valor de tipo boolean
     */
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
                        seleccionarDificultad(nivel);
                        break;
                    case 1:
                        seleccionarDificultad(nivel);
                        break;
                    case 2:
                        seleccionarDificultad(nivel);
                        break;
                }
                return true;
            case R.id.config:
                crearDialogoConfig().show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * Método para crear un cuadro de diálogo que incluye las instrucciones del juego.
     * Texto con un botón para cerrarlo.
     * @return Devuelve el cuadro de diálogo para que aparezca por pantalla cuando se le llame.
     */
    public Dialog crearDialogoInstrucciones(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.instrucciones)
                .setTitle(R.string.instrucTitulo);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Se cierra el cuadro de diálogo
            }
        });
        return builder.create();
    }
    /**
     * Método para crear un cuadro de diálogo que permite la configuración del juego en tres niveles.
     * Consta de tres RadioButtons con los que se selecciona el nivel del juego. Cada nivel aplica una configuración diferente.
     * @return Devuelve el cuadro de diálogo para que aparezca por pantalla cuando se le llame.
     */
    public Dialog crearDialogoConfig(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.configTitulo)
                .setSingleChoiceItems(R.array.configNivel, nivel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInt, int item) {
                        switch(item){
                            case 0:
                                seleccionarDificultad(0);
                                break;
                            case 1:
                                seleccionarDificultad(1);
                                break;
                            case 2:
                                seleccionarDificultad(2);
                                break;
                        }
                    }
                });
        builder.setPositiveButton(R.string.volver, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Se cierra el cuadro de diálogo
            }
        });
        return builder.create();
    }

    /**
     * Método para establecer los valores de configuración del juego según el nivel de dificultad seleccionado.
     * @param dificultad Recibe un valor correspondiente a la dificultad seleccionada.
     */
    public void seleccionarDificultad(int dificultad){
        switch(dificultad){
            case 0:
                nivel = 0;
                filas = 8;
                columnas = 8;
                aliens = 10;
                contAliens = aliens;
                setContentView(R.layout.activity_main); //Reinicio la vista para que no sume
                crearTabla(filas,columnas);
                break;
            case 1:
                nivel = 1;
                filas = 12;
                columnas = 12;
                aliens = 30;
                contAliens = aliens;
                setContentView(R.layout.activity_main);
                crearTabla(filas,columnas);
                break;
            case 2:
                nivel = 2;
                filas = 16;
                columnas = 16;
                aliens = 60;
                contAliens = aliens;
                setContentView(R.layout.activity_main);
                crearTabla(filas,columnas);
                break;
        }
    }
    //---------------- CREAR Y RELLENAR MATIZ ---------------
    /**
     * Crea la matriz sobre la que se añadirán los botones. A estos se le añade un número aleatorio único que determinará
     * si hay alien o no.
     * @param filas Filas que tendrá la matriz
     * @param columnas Columnas que tendrá la matriz
     */
    public void crearTabla(int filas, int columnas){
        ll = (LinearLayout) findViewById(R.id.linear);
        ll.setBackgroundColor(getColor(R.color.white));

        //Parámetros comunes en LinearLayout fila y botones
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);

        ArrayList<Integer> listaNumeros = numAleatorios(filas*columnas);
        int cont = 0;

        //Creación de filas con LinearLayout
        for (int i = 0; i < filas; i++) {
            fil = new LinearLayout(this);
            fil.setLayoutParams(lp);
            fil.setOrientation(LinearLayout.HORIZONTAL);
            fil.setBackgroundColor(getColor(R.color.black));

            //Creación de columnas con botones
            for (int j = 0; j < columnas; j++) {
                btn = new Button(this);
                btn.setBackground(getDrawable(R.drawable.border));
                btn.setLayoutParams(lp);
                btn.setId(cont); //Id único autoincrementable
                btn.setTag(listaNumeros.get(cont)); //Número único aleatorio, saber si hay alien o no
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
     * @param celdas Número de celdas de la matriz
     * @return Lista de números (de 0 a celdas) aleatorizada
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
     * Crea una matriz del tamaño de la cuadrícula y la inicia con todos sus valores a 0
      * @param filas Número de filas de la matriz
     * @param columnas Número de columnas de la matriz
     * @return Devuelve una matriz de tamaño similar al de la vista creada
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
     * A partir de la matriz generada, recorre los elementos que contiene el layout. Si el elemento tiene un alien (según el valor de la 'Tag')
     * pone un número negativo en su respectiva posición de la matriz. Recorre los elementos adyacentes y aumenta su valor coreespondiente que
     * será la pista que indica los aliens que hay a su alrededor.
     * @param filas Número de filas de la matriz
     * @param columnas Número de columnas de la matriz
     * @param aliens Número de aliens que pondremos
     * @return Lista (ArrayList) con las pistas que ayudan a descubrir los aliens
     */
    public ArrayList<Integer> recorrer(int filas, int columnas, int aliens){
        int matriz[][] = crearMatriz(filas, columnas);
        ArrayList<Integer> pistasAliens = new ArrayList<Integer>();
        LinearLayout lin;
        View v, v2;
        for (int i = 0; i < ll.getChildCount(); i++) {
            v = ll.getChildAt(i);
            lin = (LinearLayout) v;

            for (int j = 0; j < lin.getChildCount(); j++) {
                v2 = lin.getChildAt(j);
                if ((Integer)v2.getTag() < aliens) {
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
        //Guarda todos los nuevos valores de la matriz en una lista (ArrayList)
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                pistasAliens.add(matriz[i][j]);
            }
        }
        return pistasAliens;
    }
    //------------------- MÉTODOS PULSACIONES -----------------------
    /**
     * Define la acción o acciones que se llevarán a cabo cuando se pulse sobre una vista de la aplicación (click corto)
     * @param view Elemento o vista que ha sido pulsado
     */
    @Override
    public void onClick(View view) {
        ArrayList<Integer> valores = recorrer(filas, columnas, aliens);
        //Guarda en un entero el valor del tag del botón (indica si hay alien o no)
        int numCelda = Integer.parseInt(((Button)view).getTag().toString());

        //Si es un alien
        if(numCelda < aliens){
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
    /**
     * Define la acción o acciones que se llevarán a cabo cuando se pulse con un click largo sobre una vista de la aplicación
     * @param view Elemento o vista que ha sido pulsado
     */
    @Override
    public boolean onLongClick(View view) {
        ArrayList<Integer> valores = recorrer(filas, columnas, aliens);
        int numCelda = Integer.parseInt(((Button)view).getTag().toString());

        if(numCelda < aliens){
            Button b = (Button) view;
            b.setBackground(getDrawable(R.drawable.bien));
            b.setOnLongClickListener(null);
            b.setOnClickListener(null);
            contAliens--;
            //Si no es el último alien
            if(contAliens > 0) {
                String texto = getString(R.string.longClick_bien1) + " "+ contAliens + " " + getString(R.string.longClick_bien2);
                Toast.makeText(this, texto, Toast.LENGTH_LONG).show();
                b.setEnabled(false);
            }
            //Si es el último alien
            else {
                Toast.makeText(this, getString(R.string.longClick_ganado), Toast.LENGTH_LONG).show();
                deshabilitarCeldas();
            }
        }
        //Si no escondía un alien
        else{
            Button b = (Button) view;
            Toast.makeText(this, getString(R.string.longClick_mal), Toast.LENGTH_LONG).show();
            b.setText(valores.get(view.getId()).toString());
            deshabilitarCeldas();
        }
        return false;
    }

    /**
     * Recorre todas las celdas de la matriz y las deshabilita para que no puedan ser pulsadas.
     */
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