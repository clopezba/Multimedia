/**
 * Permite la modificación del nombre del disco de un grupo que fue seleccionado en la pantalla de consulta.
 */
package com.example.ejercicio08_bbdd;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActualizarActivity extends AppCompatActivity {
    EditText editGrupo, editDisco;
    Button btnActualizar;

    /**
     * Método al que se llama cuando se inicia la pantalla (Activity).
     * En este método se pone la mayoría de los elementos para la inicialización (funciones, varaibles, etc.).
     * @param savedInstanceState Permite volver a un estado anterior de la activiad cuando se vuelve a reiniciar. Admite valor nulo.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);

        editGrupo = (EditText) findViewById(R.id.editGrupo);
        editDisco = (EditText) findViewById(R.id.editDisco);
        btnActualizar = (Button) findViewById(R.id.btnAnadir);

        //Obtenermos el nombre del grupo y disco seleccionados en la pantalla de consulta
        Intent intent = getIntent();
        String grupo = intent.getStringExtra("gr");
        String disco = intent.getStringExtra("cd");

        editGrupo.setText(grupo);
        editDisco.setText(disco);

        /**
         * Cuando se pulsa el botón, ejecuta la modificación del nombre del disco sobre la base de datos
         * que se creó al iniciar la aplicación. Muestra un mensaje emergente con el cambio realizado.
         * Vuelve automáticamente a la pantalla de consulta.
         * @param view
         */
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.db.execSQL("UPDATE MisDiscos SET Disco='" +
                        editDisco.getText().toString() + "' WHERE Grupo='" +
                        editGrupo.getText().toString() + "';");
                Toast.makeText(view.getContext(), getString(R.string.mensajeActualizar) + " "
                        + editDisco.getText().toString(), Toast.LENGTH_LONG).show();

                Intent intent2 = new Intent(getApplicationContext(), ConsultarActivity.class);
                startActivity(intent2);
            }
        });
    }
}