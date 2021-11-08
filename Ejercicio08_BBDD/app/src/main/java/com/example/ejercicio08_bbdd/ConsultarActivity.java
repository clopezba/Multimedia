package com.example.ejercicio08_bbdd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ConsultarActivity extends AppCompatActivity {

    private RecyclerView reciclador;
    private RecyclerView.Adapter adaptador;
    private RecyclerView.LayoutManager gestor;
    Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        reciclador = (RecyclerView) findViewById(R.id.recycler);
        btnVolver = (Button) findViewById(R.id.btnVolver);

        List<Encapsulador> datos = new ArrayList<>();

        Cursor c = MainActivity.db.rawQuery("SELECT * FROM MisDiscos", null);

        if(c.getCount() == 0){
            datos.add(new Encapsulador("No hay registros", ""));
        }
        else{
            while(c.moveToNext()){
                datos.add(new Encapsulador((c.getString(0)), c.getString(1)));
            }
        }
        reciclador.setHasFixedSize(false);
        gestor = new LinearLayoutManager(this);
        reciclador.setLayoutManager(gestor);
        adaptador = new Adaptador(datos);
        reciclador.setAdapter(adaptador);
        c.close();

        reciclador.addOnItemTouchListener(new RecyclerView.OnItemTouchListener(){
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                View hijo = rv.findChildViewUnder(e.getX(), e.getY());
                if (hijo != null & gestureDetector.onTouchEvent(e)){
                    int position = rv.getChildAdapterPosition(hijo);
                    String grupo = datos.get(position).getGrupo();
                    String disco = datos.get(position).getDisco();

                    Intent intent = new Intent(getApplicationContext(), ActualizarActivity.class);
                    intent.putExtra("gr", grupo);
                    intent.putExtra("cd", disco);
                    startActivity(intent);

                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        Intent volver = new Intent(getApplicationContext(), MainActivity.class);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(volver);
            }
        });

    }

}