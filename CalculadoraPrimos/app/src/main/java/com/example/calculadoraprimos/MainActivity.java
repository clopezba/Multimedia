package com.example.calculadoraprimos;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView titulo, enunciado, posicion, resultado;
    EditText intPos;
    ArrayList<Integer> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titulo = findViewById(R.id.txtTitulo);
        enunciado = findViewById(R.id.txtEnunciado);
        intPos = findViewById(R.id.cuadrotxt);
        posicion = findViewById(R.id.txtPosicion);
        resultado = findViewById(R.id.txtResultado);

    }
    public void clic(View v){
        int leer = Integer.parseInt(intPos.getText().toString());
        if(leer<=0){
            resultado.setText(R.string.resultadoError);
        }
        else
            resultado.setText(getString(R.string.resultadoParte1) + " " + leer + " " + getString(R.string.resultadoParte2) + " " + primo(leer));
    }

    public int primo(int pos) {
       int num;
        boolean esPrimo=true;

        if(lista.isEmpty()){
            num =2;
        }
        else {
            num = lista.get(lista.size()-1)+1;
            for (int i = 2; i <= (int)Math.sqrt(num); i++) {
                if (num % i == 0) {
                    esPrimo = false;
                    break;
                }
            }
        }
        while(lista.size()<pos) {
            if(esPrimo) {
                lista.add(num);
            }
            num++;
            esPrimo=true;
            for (int i = 2; i <= (int)Math.sqrt(num); i++) {
                if(num % i == 0) {
                    esPrimo = false;
                    break;
                }
            }
        }
        return lista.get(pos-1);
    }
}