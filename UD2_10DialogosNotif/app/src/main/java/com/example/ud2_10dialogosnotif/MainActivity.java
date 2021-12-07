package com.example.ud2_10dialogosnotif;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnIngr, btnSuperm, btnFecha, btnHora, btnNotif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIngr = (Button) findViewById(R.id.btnIng);
        btnSuperm = (Button) findViewById(R.id.btnSupermer);
        btnFecha = (Button) findViewById(R.id.btnFecha);
        btnHora = (Button) findViewById(R.id.btnHora);
        btnNotif = (Button) findViewById(R.id.btnNotif);

        btnIngr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ArrayList<String> listaIngre = new ArrayList<String>();
                builder.setTitle(R.string.eligeIng)
                        .setMultiChoiceItems(R.array.ingredientes, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                String [] opciones = getResources().getStringArray(R.array.ingredientes);
                                if(isChecked){
                                    listaIngre.add(opciones[which]);
                                }
                                else if (!isChecked){
                                    if (listaIngre.contains(opciones[which])){
                                        listaIngre.remove(opciones[which]);
                                    }
                                }
                            }
                        })
                        .setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                for (String ingr: listaIngre) {
                                    Log.d("consola", ingr);
                                }
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        btnSuperm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertD = new AlertDialog.Builder(view.getContext());
                alertD.setTitle(R.string.textSuper)
                        .setSingleChoiceItems(R.array.supermercados, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int item) {
                                String [] items = getResources().getStringArray(R.array.supermercados);
                                Log.d("consola", items[item]);
                            }
                        })
                        .setPositiveButton("Seleccionar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                AlertDialog dialogo = alertD.create();
                dialogo.show();
            }
        });
        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), FechaActivity.class);
                startActivity(intent);
            }
        });
        btnHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), HoraActivity.class);
                startActivity(intent);
            }
        });

        btnNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificacion();
            }
        });
    }

    public void notificacion(){
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent notifyIntent = new Intent(this, AlertDetailsActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent notifyPendingIntent = PendingIntent.getActivity(this, 0,
                notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        if(Build.VERSION.SDK_INT >= 26){
            String id = "channel_1";
            String description = "143";
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel channel = new NotificationChannel(id, description, importance);
            manager.createNotificationChannel(channel);
            Notification notificacion = new Notification.Builder(MainActivity.this, id)
                    .setCategory(Notification.CATEGORY_MESSAGE)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Nueva notificación")
                    .setContentText("Notificación enviada correctamente")
                    .setContentIntent(notifyPendingIntent)
                    .setAutoCancel(true)
                    .build();
            manager.notify(1, notificacion);
        } else {
            Notification notificacion = new NotificationCompat.Builder(MainActivity.this)
                    .setContentTitle("Nueva notificación")
                    .setContentText("Notificación enviada correctamente")
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentIntent(notifyPendingIntent)
                    .setAutoCancel(true)
                    .build();
            manager.notify(1, notificacion);
        }


    }

}