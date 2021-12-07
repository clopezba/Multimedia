package com.example.ud2_10dialogosnotif;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class FechaActivity extends AppCompatActivity {
    EditText editFec;
    Button btnFec;
    TextView txtFecha;
    int day = 0;
    int month = 0;
    int year = 0;
    DatePickerDialog picker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fecha);

        editFec = (EditText) findViewById(R.id.editFecha);
        btnFec = (Button) findViewById(R.id.btnFec);
        txtFecha = (TextView) findViewById(R.id.txtFecha);



        editFec.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();

                if(day == 0) {
                    day = cldr.get(Calendar.DAY_OF_MONTH);
                    month = cldr.get(Calendar.MONTH);
                    year = cldr.get(Calendar.YEAR);
                }else{
                    day = picker.getDatePicker().getDayOfMonth();
                    month = picker.getDatePicker().getMonth();
                    year = picker.getDatePicker().getYear();
                }

                picker = new DatePickerDialog(FechaActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        editFec.setText(dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
                    }
                }, year, month, day);
                picker.show(); //IMPORTANTE! No olvidar

            }
        });
        btnFec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtFecha.setText("Selected Date: "+ editFec.getText());
            }
        });
    }
}