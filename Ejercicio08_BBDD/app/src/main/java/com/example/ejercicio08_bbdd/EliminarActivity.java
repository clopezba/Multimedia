/**
 * Permite eliminar un registro de la base de datos.
 */
package com.example.ejercicio08_bbdd;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EliminarActivity extends AppCompatActivity {

    Button btnEliminar, btnVolver;
    EditText editGrupo, editDisco;

    /**
     * Método al que se llama cuando se inicia la pantalla (Activity).
     * En este método se pone la mayoría de los elementos para la inicialización (funciones, varaibles, etc.).
     * @param savedInstanceState Permite volver a un estado anterior de la activiad cuando se vuelve a reiniciar. Admite valor nulo.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        btnVolver = (Button) findViewById(R.id.btnVolver2);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        editGrupo = (EditText) findViewById(R.id.editGrupo);
        editDisco = (EditText) findViewById(R.id.editDisco);

        /**
         * Al pulsar el botón 'Eliminar' borra el registro que se corresponde con el grupo y disco
         * indicados en los 'EditView' de grupo y disco. Muestra un mensaje emergente indicando el disco eliminado.
         */
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.db.execSQL("DELETE FROM MisDiscos WHERE Grupo = '" +
                        editGrupo.getText().toString() + "' AND Disco='" +
                        editDisco.getText().toString() + "'");
                Toast.makeText(view.getContext(), getString(R.string.mensajeEliminar) + " "
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