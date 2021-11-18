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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        abrir = (Button) findViewById(R.id.btnAbrir);
        cerrar = (Button) findViewById(R.id.btnCerrar);
        cliente = (Button)  findViewById(R.id.btnCliente);
        mensaje = (TextView) findViewById(R.id.txtMensaje);

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
                    serverSocket.close();
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

    private void encenderServidor(){
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(2212);
                    Socket socket;
                    while(true) {
                        socket = serverSocket.accept();
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
        hilo.start();
    }
    private void peticionCliente(){
        Thread hilo2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socketCliente = new Socket("127.0.0.1", 2212);
                    BufferedWriter escribir = new BufferedWriter(new OutputStreamWriter(
                            socketCliente.getOutputStream()));
                    escribir.write("Texto del cliente");
                    escribir.flush();
                    escribir.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        hilo2.start();
    }

}