/**
 * Muestra todos los datos contenidos en la base de datos. A través de un 'RecyclerView' se listan los
 * grupos y sus respectivos discos. Al pulsar sobre un elemento se accede a la pantalla de actualización.
 */
package com.example.ejercicio08_bbdd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

public class ConsultarActivity extends AppCompatActivity {

    private RecyclerView reciclador;
    private RecyclerView.Adapter adaptador;
    private RecyclerView.LayoutManager gestor;
    Button btnVolver;

    /**
     * Método al que se llama cuando se inicia la pantalla (Activity).
     * En este método se pone la mayoría de los elementos para la inicialización (funciones, varaibles, etc.).
     * @param savedInstanceState Permite volver a un estado anterior de la activiad cuando se vuelve a reiniciar. Admite valor nulo.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        reciclador = (RecyclerView) findViewById(R.id.recycler);
        btnVolver = (Button) findViewById(R.id.btnVolver);

        List<Encapsulador> datos = new ArrayList<>();

        //Con el cursor se recorren los datos que devuelve la consulta para añadirlos al 'ArrayList'
        // y mostrarlos en el 'RecyclerView'.
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

        /**
         * Cuando se pulsa sobre uno de los elementos o items del listado, se accede a la pantalla de actualización.
         * Recoge los datos (grupo y disco) del elemento pulsado y los envía a través del 'Intent'
         */
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

        /**
         * Cuando se pulsa el botón 'Volver' se vuelve a la pantalla principal de la aplicación.
         */
        Intent volver = new Intent(getApplicationContext(), MainActivity.class);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(volver);
            }
        });

    }

}