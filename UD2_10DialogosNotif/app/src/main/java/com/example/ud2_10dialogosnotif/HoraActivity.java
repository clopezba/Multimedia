package com.example.ud2_10dialogosnotif;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class HoraActivity extends AppCompatActivity {
    EditText editHora;
    Button btnHor;
    TextView txtHora;
    TimePickerDialog picker = null;
    int hora = 0;
    int minuto = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hora);

        editHora = (EditText) findViewById(R.id.editHora);
        btnHor = (Button) findViewById(R.id.btnHor);
        txtHora = (TextView) findViewById(R.id.txtHora);

        editHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePicker timePicker = new TimePicker(HoraActivity.this);
                /*if (hora == 0){
                    hora = timePicker.getHour();
                    minuto = timePicker.getMinute();
                }*/

                picker = new TimePickerDialog(HoraActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        editHora.setText(hourOfDay + ":" + minute);
                    }
                }, hora, minuto, true);
               picker.show();
            }
        });
        btnHor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtHora.setText("Selected Date: "+ editHora.getText());
            }
        });

    }
}