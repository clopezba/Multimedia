package com.example.camposdegolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AnadirActivity extends AppCompatActivity {

    Button btnAnadir;
    EditText editId, editNombreCampo;

    /**
     * Método al que se llama cuando se inicia la pantalla (Activity).
     * En este método se pone la mayoría de los elementos para la inicialización (funciones, varaibles, etc.).
     * @param savedInstanceState Permite volver a un estado anterior de la activiad cuando se vuelve a reiniciar. Admite valor nulo.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir);

        btnAnadir = (Button) findViewById(R.id.btnAnadir);
        editId = (EditText) findViewById(R.id.editId);
        editNombreCampo = (EditText) findViewById(R.id.editNombre);

        /**
         * Cuando se pulsa el botón 'Añadir', añade a la base de datos creada al iniciarse la aplicación el
         * Id y NombreCampo indicados en los correspondientes 'EditText'.
         * Indica con un mensaje emergente el NombreCampo añadido.
         */
        btnAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Listado.db.execSQL("INSERT INTO CamposGolf VALUES('" +
                        editId.getText().toString() + "','" +
                        editNombreCampo.getText().toString() + "');");

                editId.setText("");
                editNombreCampo.setText("");
                Intent volver = new Intent(getApplicationContext(), Listado.class);
                startActivity(volver);
            }
        });
        
    }

}