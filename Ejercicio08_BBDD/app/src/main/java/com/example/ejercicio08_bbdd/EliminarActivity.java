package com.example.ejercicio08_bbdd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EliminarActivity extends AppCompatActivity {

    Button btnEliminar;
    EditText editGrupo, editDisco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        editGrupo = (EditText) findViewById(R.id.editGrupo);
        editDisco = (EditText) findViewById(R.id.editDisco);

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.db.execSQL("DELETE FROM MisDiscos WHERE Grupo = '" +
                        editGrupo.getText().toString() + "' AND Disco='" +
                        editDisco.getText().toString() + "'");
                Toast.makeText(view.getContext(), getString(R.string.mensajeEliminar) + editDisco.getText().toString(), Toast.LENGTH_LONG).show();

                editGrupo.setText("");
                editDisco.setText("");
            }
        });
    }
}