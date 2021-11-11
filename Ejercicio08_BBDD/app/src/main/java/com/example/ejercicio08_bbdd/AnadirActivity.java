/**
 * Permite añadir discos a la base de datos (grupo y disco).
 */
package com.example.ejercicio08_bbdd;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AnadirActivity extends AppCompatActivity {

    Button btnAnadir, btnVolver;
    EditText editGrupo, editDisco;

    /**
     * Método al que se llama cuando se inicia la pantalla (Activity).
     * En este método se pone la mayoría de los elementos para la inicialización (funciones, varaibles, etc.).
     * @param savedInstanceState Permite volver a un estado anterior de la activiad cuando se vuelve a reiniciar. Admite valor nulo.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir);
       
        btnVolver = (Button) findViewById(R.id.btnVolver1);
        btnAnadir = (Button) findViewById(R.id.btnAnadir);
        editGrupo = (EditText) findViewById(R.id.editGrupo);
        editDisco = (EditText) findViewById(R.id.editDisco);

        /**
         * Cuando se pulsa el botón 'Añadir', añade a la base de datos creada al iniciarse la aplicación el
         * grupo y disco indicados en los correspondientes 'EditText'.
         * Indica con un mensaje emergente el disco añadido.
         */
        btnAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.db.execSQL("INSERT INTO MisDiscos VALUES('" +
                        editGrupo.getText().toString() + "','" +
                        editDisco.getText().toString() + "');");
                Toast.makeText(view.getContext(), getString(R.string.mensajeAnadir) + " "
                        + editDisco.getText().toString(), Toast.LENGTH_LONG).show();

                editGrupo.setText("");
                editDisco.setText("");
            }
        });
        /**
         * Cuando se pulsa el botón 'Volver' se vuelve a la pantalla principal de la aplicación.
         */
        Intent volver = new Intent(getApplicationContext(), MainActivity.class);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(volver);
            }
        });
    }

}