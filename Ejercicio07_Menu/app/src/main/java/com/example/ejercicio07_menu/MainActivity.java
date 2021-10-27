package com.example.ejercicio07_menu;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
     @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuItem item = menu.add("Ajustes");
            MenuItem item3 = menu.add("Información");
            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    Toast.makeText(getApplicationContext(),
                            "Se ha pulsado Ajustes", Toast.LENGTH_LONG).show();
                    return true;
                }
            });

            return true;
        }
}