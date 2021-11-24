package com.example.ud2_8sockets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {
    Button abrir, cerrar, cliente;
    ServerSocket serverSocket;
    TextView mensaje;

    /**
     * Método al que se llama cuando se inicia la pantalla (Activity).
     * En este método se pone la mayoría de los elementos para la inicialización (funciones, varaibles, etc.).
     * @param savedInstanceState Permite volver a un estado anterior de la activiad cuando se vuelve a reiniciar. Admite valor nulo.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        abrir = (Button) findViewById(R.id.btnAbrir);
        cerrar = (Button) findViewById(R.id.btnCerrar);
        cliente = (Button)  findViewById(R.id.btnCliente);
        mensaje = (TextView) findViewById(R.id.txtMensaje);

        //Añade los eventos click de los botones
        abrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                encenderServidor();
            }
        });
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    serverSocket.close(); //Cierra el servidor
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                peticionCliente();
            }
        });

    }

    /**
     * Método para encender el servidor. Crea un hilo para no trabajar directamente con el
     * hilo principal. Implementa la interfaz 'Runnable' para ejecutar el hijo.
     */
    private void encenderServidor(){
        Thread hilo = new Thread(new Runnable() {
            /**
             * Indica la acción que realizará el hilo al lanzarse.
             */
            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(2212);
                    Socket socket;
                    while(true) {
                        socket = serverSocket.accept(); //Queda a la escucha de peticiones
                        BufferedReader entrada = new BufferedReader(
                                new InputStreamReader(socket.getInputStream()));
                        String datos = entrada.readLine();
                        mensaje.setText(datos);
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        hilo.start(); //Lanza el hilo
    }

    /**
     * Método que realiza la petición de un cliente para conectarse al servidor.
     * Crea un segundo hilo para trabajar
     */
    private void peticionCliente(){
        Thread hilo2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socketCliente = new Socket("127.0.0.1", 2212); //Crea un socket con la IP del servidor y el puerto
                    BufferedWriter escribir = new BufferedWriter(new OutputStreamWriter(
                            socketCliente.getOutputStream()));
                    escribir.write("Texto del cliente");
                    escribir.flush(); //Fuerza la escritura
                    escribir.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        hilo2.start(); //Lanza el hilo
    }

}