package com.example.aplicacion1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv, tv2;
    Button boton;
    int sum = 0, res = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.txtSumar);
        tv2 = (TextView) findViewById(R.id.txtRestar);

    }
    public void clic(View v){
        sum++;
        tv.setText(String.valueOf(sum));
        res--;
        tv2.setText(String.valueOf(res));
    }

}