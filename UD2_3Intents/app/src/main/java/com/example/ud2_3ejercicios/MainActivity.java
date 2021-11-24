package com.example.ud2_3ejercicios;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    Button b;
    LinearLayout l;
    Intent ejemplo;
    ActivityResultLauncher<Intent> someActivityResultLauncher;

    /**
     * Método al que se llama cuando se inicia la pantalla (Activity).
     * En este método se pone la mayoría de los elementos para la inicialización (funciones, varaibles, etc.).
     * @param savedInstanceState Permite volver a un estado anterior de la activiad cuando se vuelve a reiniciar. Admite valor nulo.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cambiarActividad(someActivityResultLauncher);
        obtenerResultado(ejemplo);
    }

    /**
     * A tavés de un Intent se cambia a la actividad indicada en una nueva pantalla.
     * Con el evento onClick enviamos datos a la actividad.
     * @param ResultLauncher
     */
    public void cambiarActividad(ActivityResultLauncher<Intent> ResultLauncher){
        ejemplo = new Intent(this, Activity2.class);
        Coche car = new Coche("Ford", "Focus", 21500.50);
        l = (LinearLayout) findViewById(R.id.linear);
        b = (Button) findViewById(R.id.btn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ejemplo.putExtra("texto", getString(R.string.txtExtra));
                ejemplo.putExtra("entero", 23);
                ejemplo.putExtra("real", 16.32);
                ejemplo.putExtra("coma", 25.83f);
                ejemplo.putExtra("coches", car);
                //startActivity(ejemplo); --> Si pedimos que devuelva un resultado, no ponemos este método
                someActivityResultLauncher.launch(ejemplo);
            }
        });
    }

    /**
     * Recibimos el resultado de la otra actividad
     * @param in Recibe como parámetro el intent que recibe la repsuesta
     */
    public void obtenerResultado(Intent in){
        someActivityResultLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            /**
             * Comprueba que la información que le llega tiene el código correcto para que reciba la información.
             * @param result Resibe como parámentro el resultado de la actividad
             */
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK){
                    Log.d("consola", getString(R.string.ResultadoOK));

                    String txt = ejemplo.getStringExtra("texto");
                    int num = ejemplo.getIntExtra("entero", -1);
                    Coche coche = (Coche) ejemplo.getSerializableExtra("coches");

                    Log.d("consola", txt);
                    Log.d("consola", Integer.toString(num));
                    Log.d("consola", coche.toString());
                }
            }
        });
    }
}