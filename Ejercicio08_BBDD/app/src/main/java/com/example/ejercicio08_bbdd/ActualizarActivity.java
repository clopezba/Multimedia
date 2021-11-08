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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);

        editGrupo = (EditText) findViewById(R.id.editGrupo);
        editDisco = (EditText) findViewById(R.id.editDisco);
        btnActualizar = (Button) findViewById(R.id.btnAnadir);

        Intent intent = getIntent();
        String grupo = intent.getStringExtra("gr");
        String disco = intent.getStringExtra("cd");

        editGrupo.setText(grupo);
        editDisco.setText(disco);

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