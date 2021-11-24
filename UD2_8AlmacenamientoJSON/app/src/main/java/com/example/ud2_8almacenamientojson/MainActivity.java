package com.example.ud2_8almacenamientojson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity {

    ConnectivityManager conn;
    NetworkInfo netInfo;
    ListView list;
    ArrayAdapter<String> adaptador;

    /**
     * Método al que se llama cuando se inicia la pantalla (Activity).
     * En este método se pone la mayoría de los elementos para la inicialización (funciones, varaibles, etc.).
     * @param savedInstanceState Permite volver a un estado anterior de la activiad cuando se vuelve a reiniciar. Admite valor nulo.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conn = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        netInfo = conn.getActiveNetworkInfo();
        String url = "https://servicios.ine.es/wstempus/js/es/DATOS_TABLA/43491?tip=AM";

        if(netInfo != null && netInfo.isConnected() && netInfo.isAvailable()){
            new MyTask().execute(url);
        }
        list = (ListView) findViewById(R.id.lista);

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
                Log.d("consola", String.valueOf(codResp));
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
            Log.d("consola", result);

            ArrayList<String> elementos = new ArrayList<String>();

            try {
                //El JSON es un Array de objetos
                JSONArray listado = new JSONArray(result);

                //Recorro todos los objetos del array
                for (int i = 0; i < listado.length(); i++) {
                    //Creo el objeto i del array general
                    JSONObject obj = listado.getJSONObject(i);
                    //Obtengo un valor del objeto
                    String nombre = obj.getString("Nombre");
                    //Obtengo un array contenido en el objeto
                    JSONArray data = obj.getJSONArray("Data");
                    //Guargo el objeto 0 (unico) del array Data
                    JSONObject obj2 = data.getJSONObject(0);
                    //Obtengo un valor del objeto
                    String valor = obj2.getString("Valor");

                    Log.d("consola", nombre);
                    Log.d("consola", valor);
                    String cadena = nombre + "\n\t\t" + valor;
                    elementos.add(cadena);
                }
                adaptador = new ArrayAdapter<>(getApplicationContext(), R.layout.fila, elementos);
                list.setAdapter(adaptador);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

}