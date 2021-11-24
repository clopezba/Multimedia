package com.example.ud2_9sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Clase que hereda de 'BroadcastReceiver', es una clase base que recibe y maneja 'intents' de transmisión.
 */
public class MySmSReceiver extends BroadcastReceiver {

    /**
     * Método llamado cuando el BroadcastReceiver recibe un Intent de transmisión, un mensaje.
     * @param context El contexto en el que está siendo ejecutado el 'receiver'
     * @param intent El 'intent' que se recibe
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        String textoSMS = "";
        String remitente = "";
        if(intent.getAction() == Telephony.Sms.Intents.SMS_RECEIVED_ACTION){
            SmsMessage[] sms =  Telephony.Sms.Intents.getMessagesFromIntent(intent);
            for (SmsMessage mensaje: sms) {
                textoSMS += mensaje.getMessageBody();
                remitente += mensaje.getOriginatingAddress();
            }
        }

        MainActivity.texto.setText("Mensaje\nDe: " + remitente + "\nTexto:\n\n" + textoSMS);
        Log.d("consola", textoSMS);
    }
}
