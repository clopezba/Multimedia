package com.example.ud2_ejercicios;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.chip.ChipGroup;

public class MainActivity extends AppCompatActivity {
    View v;
    Button btnInicial, btnNuevo;
    TextView txt;
    LinearLayout l;

    /**
     * Método al que se llama cuando se inicia la pantalla (Activity).
     * En este método se pone la mayoría de los elementos para la inicialización (funciones, varaibles, etc.).
     * @param savedInstanceState Permite volver a un estado anterior de la activiad cuando se vuelve a reiniciar. Admite valor nulo.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recorrer();
        anadeHijos();
        cambiarVisibilidad();
    }

    /**
     * Recorre todos los ítems que contiene la vista (linearLayout) y aplica diferente texto según sea un
     * Button o un TextView.
     */
    public void recorrer(){

        l = (LinearLayout) findViewById(R.id.linear);
        for(int i =0; i <l.getChildCount(); i++)
        {
            v=l.getChildAt(i);
            Log.d("consola", "objeto: "+ v.toString());
            Log.d("consola", v.getClass().getSimpleName());
            if (v.getClass().getSimpleName().equals("MaterialButton")){
                btnInicial = (Button) v;
                btnInicial.setText(R.string.botonNuevo);
            }
            else if (v.getClass().getSimpleName().equals("MaterialTextView")){
                txt = (TextView) v;
                txt.setText(R.string.nuevoTexto);
            }
        }
    }

    /**
     * Añade de forma programática elementos a la vista (linearLayout).
     * Añade dos botones y tres CheckBox y les asigna sus respectivos parámetros.
     */
    public void anadeHijos(){
        l = (LinearLayout) findViewById(R.id.linear);
        for (int i=0; i<2; i++){
            btnNuevo = new Button(this);
            btnNuevo.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
                btnNuevo.setText("Boton " + i);
                btnNuevo.setId(View.generateViewId());
                btnNuevo.setOnClickListener(new View.OnClickListener(){
                   public void onClick(View v){
                       txt.setText(R.string.saludo);
                    }
                });
                l.addView(btnNuevo);
        }
        //Crear checkboxes y su correspondiente listener
        CheckBox chck;
        for (int i = 0; i < 3; i++) {
            chck = new CheckBox(this);
            chck.setText(R.string.chck);
            chck.setId(View.generateViewId());
            chck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               public void onCheckedChanged (CompoundButton chck, boolean isChecked){
                    txt.setText(R.string.chck);
                }
            });
            l.addView(chck);
        }
    }

    /**
     * Cambia la visibilidad de un Button
     */
    public void cambiarVisibilidad(){
        btnInicial.setVisibility(View.INVISIBLE);
        Log.d("consola", String.valueOf(btnInicial.getVisibility()));
    }
}