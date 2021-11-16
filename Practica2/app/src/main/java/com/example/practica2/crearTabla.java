package com.example.practica2;

import android.graphics.Color;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;


public class crearTabla {
   private TableLayout table;

    public crearTabla() {

    }

    /*@Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);

            LinearLayout ll = (LinearLayout) findViewById(R.id.linear);

            TableLayout etiquetaTabla = dibujarTabla(1, 2);
        }*/
    public TableLayout dibujarTabla(int numeroFilas, int numeroColumnas){
        TableLayout tabla = new TableLayout(table.getContext());
        tabla.setGravity(Gravity.CENTER);

        TableRow fila = new TableRow(table.getContext());
        int numeroCeldas = numeroFilas * numeroColumnas;
        int contadorColumnas = 0;
        int contadorFilas = 0;

        for (int i = 0; i <= numeroCeldas ; i++) {
            if(contadorColumnas == numeroColumnas){
                tabla.addView(fila);
                fila = new TableRow(table.getContext());
                contadorColumnas = 0;
                contadorFilas++;
        }
            RelativeLayout borde = new RelativeLayout(table.getContext());
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

            Button btn = new Button(table.getContext());
            btn.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT));
            btn.setWidth(1);
            btn.setPadding(2,2,2,2);
            btn.setBackgroundColor(Color.parseColor("Grey"));
            borde.addView(btn);
            fila.addView(borde);
            contadorColumnas++;

        }
    return tabla;
    }
}
