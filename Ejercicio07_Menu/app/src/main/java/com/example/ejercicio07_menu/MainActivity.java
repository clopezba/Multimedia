/**
 * @author Cristina López
 * @version 1.0
 */
package com.example.ejercicio07_menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.ActionMenuView;
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

    }

    /**
     * Método para crear un menú de opciones y añadirle los ítems que contendrá.
     * @param menu El menú de opciones donde se colocarán los items (de tipo Menu).
     * @return Valor  de tipo booleano: 'True' para que se muestre el menú, 'False' para que no se muestre.
     */
     @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            SubMenu ajustes = menu.addSubMenu(R.string.ajustes);
            MenuItem cambiarColor = ajustes.add(R.string.cambiarColor);
            MenuItem cambiarLetra = ajustes.add(R.string.cambiaLetra);
            MenuItem informacion = menu.add(R.string.info);

            return true;
        }

}