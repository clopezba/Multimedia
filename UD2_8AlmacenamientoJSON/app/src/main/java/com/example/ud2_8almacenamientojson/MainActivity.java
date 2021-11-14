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
    private class MyTask extends AsyncTask<String, Void, String> {
        StringBuilder result = null;
        HttpURLConnection urlConnection = null;

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
            return result.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d("consola", result);

            ArrayList<String> elementos = new ArrayList<String>();

            try {
                JSONArray listado = new JSONArray(result);
                String valor = null;
                for (int i = 0; i < listado.length(); i++) {
                    JSONObject obj = listado.getJSONObject(i);
                    String nombre = obj.getString("Nombre");
                    JSONArray data = obj.getJSONArray("Data");
                    for (int j = 0; j < data.length(); j++){
                       JSONObject obj2 = data.getJSONObject(j);
                       valor = obj2.getString("Valor");
                    }
                    Log.d("consola", nombre);
                    Log.d("consola", valor);
                    elementos.add(nombre);
                    elementos.add(valor);
                }
                adaptador = new ArrayAdapter<>(getApplicationContext(), R.layout.fila, elementos);
                list.setAdapter(adaptador);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

}