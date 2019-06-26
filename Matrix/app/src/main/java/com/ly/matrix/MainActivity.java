package com.ly.matrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    MyView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myView = findViewById(R.id.iv);
    }

    public void hf(View view) {
        myView.hf(0, 0);//恢复
    }

    public void py(View view) {
        myView.py(100, 500);//平移
    }

    public void sf(View view) {
        myView.sf(2f, 2f);
    }

    public void xz(View view) {
        myView.xz(360);
    }

    public void cq(View view) {
        myView.cq(0.5f, 0f);
    }

    public void ts(View view) {
        myView.ts();
    }

    public void dc(View view) {
        myView.dc();
    }
}
