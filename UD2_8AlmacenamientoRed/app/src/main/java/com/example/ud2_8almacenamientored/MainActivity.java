package com.example.ud2_8almacenamientored;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ConnectivityManager conect;
    NetworkInfo info;
    TextView datos;

    /**
     * Método al que se llama cuando se inicia la pantalla (Activity).
     * En este método se pone la mayoría de los elementos para la inicialización (funciones, varaibles, etc.).
     * @param savedInstanceState Permite volver a un estado anterior de la activiad cuando se vuelve a reiniciar. Admite valor nulo.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conect = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        info = conect.getActiveNetworkInfo();
        datos = (TextView) findViewById(R.id.txtDatos);
        String url = "https://www.google.com/humans.txt";

        if(info != null && info.isConnected() && info.isAvailable()){
            new MyTask().execute(url);
        }

    }

    /**
     * Creación de otra clase que hereda de AsyncTask para realizar la conexión en otro
     * hilo separado del principal.
     * Primer parámetro indica el tipo de parámentros que recibe la tarea para ejecutar.
     * Segundo parámetro indica el tipo de unidades de progreso publicadas durante la computación en segundo plano.
     *      En este caso es 'void' porque no se usará este parámetro.
     * Tercer parámetro indica el tipo de resultado de la computación en segundo plano
     */
    private class MyTask extends AsyncTask<String, Void, String>{
        int codResp;

        /**
         * Ejecuta un hilo en segundo plano. Se conecta a la URL y recoge los datos
         * @param string Recibe los parámetros de la tarea
         * @return Devuelve un resultado definido por la subclase de la tarea
         */
        @Override
        protected String doInBackground(String... string) {
            HttpURLConnection urlConnection = null;
            StringBuilder result = null;

            try {
                URL url = new URL(string[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                codResp = urlConnection.getResponseCode();

                if(codResp == 200) {
                    urlConnection.setConnectTimeout(20000);
                    urlConnection.setReadTimeout(5000);

                    urlConnection.setRequestMethod("GET");
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    String line;
                    result = new StringBuilder();

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                urlConnection.disconnect();
                return result.toString();
            }

        }

        /**
         * Se ejecuta en el hilo principal después del método anterior ('doInBackground()').
         * @param result El resultado de la operación realizada en 'doInBackground()'
         */
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            datos.setText(result);
            Toast.makeText(getApplicationContext(), String.valueOf(codResp),
                    Toast.LENGTH_LONG).show();

        }
    }
}
