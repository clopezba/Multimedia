package com.example.ejercicio08_bbdd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AnadirActivity extends AppCompatActivity {

    Button btnAnadir;
    EditText editGrupo, editDisco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir);

        btnAnadir = (Button) findViewById(R.id.btnAnadir);
        editGrupo = (EditText) findViewById(R.id.editGrupo);
        editDisco = (EditText) findViewById(R.id.editDisco);

        btnAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.db.execSQL("INSERT INTO MisDiscos VALUES('" +
                        editGrupo.getText().toString() + "','" +
                        editDisco.getText().toString() + "');");
                Toast.makeText(view.getContext(), getString(R.string.mensajeAnadir) + editDisco.getText().toString(), Toast.LENGTH_LONG).show();

                editGrupo.setText("");
                editDisco.setText("");
            }
        });
    }

}