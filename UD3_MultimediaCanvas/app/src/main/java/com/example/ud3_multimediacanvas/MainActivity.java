package com.example.ud3_multimediacanvas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }

    public class MyView extends View {
        Paint paint = null;
        public MyView (Context context){
            super(context);
            paint = new Paint();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            int x = getWidth();
            int y = getHeight();
            int radius;

            radius = 500;
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            canvas.drawPaint(paint);

            paint.setColor(Color.parseColor("#CD5C5C"));
            canvas.drawCircle(x/2, y/2, radius, paint);
            paint.setColor(Color.parseColor("#0040C5"));
            paint.setStrokeWidth(18);
            canvas.drawLine(0,0,500,500, paint);
            paint.setColor(Color.parseColor("#445566"));
            canvas.drawOval(750, 680, 550, 200, paint);
        }
    }
}