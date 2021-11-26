package com.example.camposdegolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditarActivity extends AppCompatActivity {

    EditText editId, editNombre;
    Button btnActualizar, btnEliminar, btnVotar;

    /**
     * Método al que se llama cuando se inicia la pantalla (Activity).
     * En este método se pone la mayoría de los elementos para la inicialización (funciones, varaibles, etc.).
     * @param savedInstanceState Permite volver a un estado anterior de la activiad cuando se vuelve a reiniciar. Admite valor nulo.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        editId = (EditText) findViewById(R.id.editId);
        editNombre = (EditText) findViewById(R.id.editNombre);
        btnActualizar = (Button) findViewById(R.id.btnActualizar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnVotar = (Button) findViewById(R.id.btnVotar);

        //Obtenermos el nombre del Id y Nombre seleccionados en la pantalla de consulta
        Intent intent = getIntent();
        String Id = intent.getStringExtra("id");
        String Nombre = intent.getStringExtra("nombre");

        editId.setText(Id);
        editNombre.setText(Nombre);

        /**
         * Cuando se pulsa el botón, ejecuta la modificación del nombre del Nombre sobre la base de datos
         * que se creó al iniciar la aplicación. Muestra un mensaje emergente con el cambio realizado.
         * Vuelve automáticamente a la pantalla de consulta.
         * @param view
         */
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Listado.db.execSQL("UPDATE CamposGolf SET nombreCampo='" +
                        editNombre.getText().toString() + "' WHERE id='" +
                        editId.getText().toString() + "';");

                Intent intent2 = new Intent(getApplicationContext(), Listado.class);
                startActivity(intent2);
            }
        });
        /**
         * Al pulsar el botón 'Eliminar' borra el registro que se corresponde con el grupo y disco
         * indicados en los 'EditView' de grupo y disco. Muestra un mensaje emergente indicando el disco eliminado.
         */
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Listado.db.execSQL("DELETE FROM CamposGolf WHERE id = '" +
                        editId.getText().toString() + "' AND nombreCampo='" +
                        editNombre.getText().toString() + "'");
                Intent intent3 = new Intent(getApplicationContext(), Listado.class);
                startActivity(intent3);
            }
        });
    }
}