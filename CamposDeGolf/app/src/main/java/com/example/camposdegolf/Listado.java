package com.example.camposdegolf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Listado extends AppCompatActivity {

    private RecyclerView reciclador;
    private RecyclerView.Adapter adaptador;
    private RecyclerView.LayoutManager gestor;
    Intent int_Anadir, int_Verificar;
    protected static SQLiteDatabase db;

    ConnectivityManager conn;
    NetworkInfo netInfo;
    List<Encapsulador> datos;


    /**
     * Método al que se llama cuando se inicia la pantalla (Activity).
     * En este método se pone la mayoría de los elementos para la inicialización (funciones, varaibles, etc.).
     * @param savedInstanceState Permite volver a un estado anterior de la activiad cuando se vuelve a reiniciar. Admite valor nulo.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        reciclador = (RecyclerView) findViewById(R.id.recycler);
        datos = new ArrayList<>();
        Cursor c = null;

        Intent intent = getIntent();
        String txt = intent.getStringExtra("boton");
        if(txt.equals("baseDatos")){
            //Creación o apertura de base de datos
            db = openOrCreateDatabase("CamposGolf", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS CamposGolf(id VARCHAR, nombreCampo VARCHAR);");

            //Con el cursor se recorren los datos que devuelve la consulta para añadirlos al 'ArrayList'
            // y mostrarlos en el 'RecyclerView'.
            c = db.rawQuery("SELECT * FROM CamposGolf", null);
            if(c.getCount() == 0){
                datos.add(new Encapsulador("No hay registros", ""));
            }
            else{
                while(c.moveToNext()){
                    datos.add(new Encapsulador((c.getString(0)), c.getString(1)));
                }
            }
        }
        if(txt.equals("Web")){
            conn = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            netInfo = conn.getActiveNetworkInfo();
            String url = "https://nexo.carm.es/nexo/archivos/recursos/opendata/json/CamposdeGolf.json";

            if(netInfo != null && netInfo.isConnected() && netInfo.isAvailable()){
                new MyTask().execute(url);
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
                    String id = datos.get(position).getid();
                    String nombreCampo = datos.get(position).getnombreCampo();

                    Intent intent = new Intent(getApplicationContext(), EditarActivity.class);
                    intent.putExtra("id", id);
                    intent.putExtra("nombre", nombreCampo);
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
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.insertar:
                int_Anadir = new Intent(this, AnadirActivity.class);
                startActivity(int_Anadir);
                return true;

            case R.id.verificar:
                int_Verificar = new Intent(this, VerificarActivity.class);
                startActivity(int_Verificar);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * Creación de otra clase que hereda de AsyncTask para realizar la conexión en otro
     * hilo separado del principal.
     * Primer parámetro indica el tipo de parámentros que recibe la tarea para ejecutar.
     * Segundo parámetro indica el tipo de unidades de progreso publicadas durante la computación en segundo plano.
     *      En este caso es 'void' porque no se usará este parámetro.
     * Tercer parámetro indica el tipo de resultado de la computación en segundo plano
     */
    private class MyTask extends AsyncTask<String, Void, String> {
        StringBuilder result = null;
        HttpURLConnection urlConnection = null;

        /**
         * Ejecuta un hilo en segundo plano. Se conecta a la URL y recoge los datos
         * @param string Recibe los parámetros de la tarea
         * @return Devuelve un resultado definido por la subclase de la tarea
         */
        @Override
        protected String doInBackground(String... string) {
            try {
                URL url = new URL(string[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int codResp = urlConnection.getResponseCode();

                urlConnection.setConnectTimeout(20000);
                urlConnection.setReadTimeout(5000);

                if (codResp == 200) {
                    urlConnection.setRequestMethod("GET");
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    String linea;
                    result = new StringBuilder();
                    while ((linea = reader.readLine()) != null) {
                        result.append(linea);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            urlConnection.disconnect();
            return result.toString(); //Devuelve todo lo que veo cuando accedo a la web
        }

        /**
         * Se ejecuta en el hilo principal después del método anterior ('doInBackground()').
         * @param result El resultado de la operación realizada en 'doInBackground()'
         */
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            String id = null;
            String nombreCampo = null;
            try {
                //El JSON es un Array de objetos
                JSONArray listado = new JSONArray(result);

                //Recorro todos los objetos del array
                for (int i = 0; i < listado.length(); i++) {
                    //Creo el objeto i del array general
                    JSONObject obj = listado.getJSONObject(i);
                    //Obtengo un valor del objeto
                    id = obj.getString("Código");
                    nombreCampo = obj.getString("Nombre");
                }
            datos.add(new Encapsulador(id, nombreCampo));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}