package com.example.ud2_9sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

public class MySmSReceiver extends BroadcastReceiver {

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
