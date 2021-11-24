package com.example.ud2_4ejercicios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /**
     * Método al que se llama cuando se inicia la pantalla (Activity).
     * En este método se pone la mayoría de los elementos para la inicialización (funciones, varaibles, etc.).
     * @param savedInstanceState Permite volver a un estado anterior de la activiad cuando se vuelve a reiniciar. Admite valor nulo.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txt = (TextView) findViewById(R.id.txtOpciones);
        registerForContextMenu(txt);
    }
    /**
     * Método para crear un menú de opciones y añadirle los ítems que contendrá.
     * @param menu El menú de opciones donde se colocarán los items.
     * @return Valor  de tipo booleano: 'True' para que se muestre el menú, 'False' para que no se muestre.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    /**
     * Método al que se llama cuando se selecciona un ítem del menú
     * @param item El ítem que ha sido seleccionado. No puede ser nulo.
     * @return valor de tipo boolean. True si se procesó correctamente el click del menú.
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.datosPersonales:
                Toast.makeText(getApplicationContext(),
                        "Se ha pulsado Datos Personales", Toast.LENGTH_LONG).show();
                return true;
            case R.id.datosIdent:
                Toast.makeText(getApplicationContext(),
                        "Se ha pulsado Datos Identificación", Toast.LENGTH_LONG).show();
                return true;
            case R.id.datosContacto:
                Toast.makeText(getApplicationContext(),
                        "Se ha pulsado Datos de contacto", Toast.LENGTH_LONG).show();
                return true;
            case R.id.datosProfes:
                Toast.makeText(getApplicationContext(),
                        "Se ha pulsado Datos Profesionales", Toast.LENGTH_LONG).show();
                return true;
            case R.id.estudios:
                Toast.makeText(getApplicationContext(),
                        "Se ha pulsado Información Académica", Toast.LENGTH_LONG).show();
                return true;
            case R.id.trabajo:
                Toast.makeText(getApplicationContext(),
                        "Se ha pulsado Experiencia Laboral", Toast.LENGTH_LONG).show();
                return true;
            case R.id.cursos:
                Toast.makeText(getApplicationContext(),
                        "Se ha pulsado Otros Cursos", Toast.LENGTH_LONG).show();
                return true;
            case R.id.ayuda:
                Toast.makeText(getApplicationContext(),
                        "Se ha pulsado Ayuda", Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Método para crear un menú contextual, se le da la estructura con un recurso 'menu'
     * @param menu_c El menú contextual que se construye
     * @param v Vista donde se construirá el menú
     * @param menuInfo Información adicional sobre el item para el que se muestra el menú contextual.
     *                 Depende de la clase de la vista (v).
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu_c, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menu = getMenuInflater();
        menu.inflate(R.menu.menu_contextual, menu_c);
        super.onCreateContextMenu(menu_c, v, menuInfo);
    }

    /**
     * Método al que se llama cuando se selecciona un ítem del menú
     * @param item El ítem que ha sido seleccionado. No puede ser nulo.
     * @return valor de tipo boolean. True si se procesó correctamente el click del menú.
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.copiar:
                Toast.makeText(getApplicationContext(),
                        "Has copiado el texto", Toast.LENGTH_LONG).show();
                return true;
            case R.id.cortar:
                Toast.makeText(getApplicationContext(),
                        "Has cortado el texto", Toast.LENGTH_LONG).show();
                return true;
            case R.id.pegar:
                Toast.makeText(getApplicationContext(),
                        "Has pegado el texto", Toast.LENGTH_LONG).show();
                return true;
        }

        return super.onContextItemSelected(item);
    }
}