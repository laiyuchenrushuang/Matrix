package com.ly.matrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by ly on 2019/6/26 11:01
 * <p>
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
public class MyView extends View {

    Matrix matrix = new Matrix();
    Bitmap bitmap;
    Paint paint = new Paint();

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.abc);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Log.d("lylog", " w = " + w + " h = " + h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("lylog", " onDraw bitmap = " + bitmap);
        canvas.drawBitmap(bitmap, matrix, paint);
    }

    public void py(final int x, final int y) {
        new Thread(new Runnable() {
            int px = 0, py = 0;

            @Override
            public void run() {
                while (px <= x && py <= y) {
                    matrix.setTranslate(px, py);
                    postInvalidate();
                    sleep(500);
                    px += 10;
                    py += 50;
                }
            }
        }).start();
    }

    public void hf(int x, int y) {
        matrix.setTranslate(x, y);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.abc);
        paint = new Paint();
        invalidate();
    }

    public void sf(final float x, final float y) {
        new Thread(new Runnable() {
            float px = 0, py = 0;

            @Override
            public void run() {
                while (px <= 2) {
                    matrix.setScale(px, py);//倍数
                    postInvalidate();
                    sleep(500);
                    px += 0.2f;
                    py += 0.2f;
                }
                sleep(1000);
                while (px >= 0) {
                    matrix.setScale(px, py);//倍数
                    postInvalidate();
                    sleep(500);
                    px -= 0.2f;
                    py -= 0.2f;
                }
            }
        }).start();

//        matrix.setScale(x, y, 50, 100);//倍数，围绕的坐标
        invalidate();
    }

    public void xz(float degrees) {

        new Thread(new Runnable() {
            int degree = 0;

            @Override
            public void run() {
                while (degree <= 360) {
                    matrix.setRotate(degree, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
                    postInvalidate();
                    sleep(500);
                    degree += 30;
                }
            }
        }).start();

    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void cq(float i, float i1) {
        matrix.setSkew(i, i1);
        invalidate();
    }

    public void ts() {
        int bw = bitmap.getWidth();
        int bh = bitmap.getHeight();
        float[] src = {0, 0, 0, bh, bw, bh, bw, 0};
        int DX = 100;
        float[] dst = {0 + DX, 0, 0, bh, bw, bh, bw - DX, 0};
        matrix.setPolyToPoly(src, 0, dst, 0, 4);
        postInvalidate();
    }


    public void dc() {
        float matrixValues[] = {0f, -1f, 0f, -1f, 0f, 0f, 0f, 0f, 1f};// x = y轴

//        float matrixValues[] = {-1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f};// y轴
//        float matrixValues[] = { 1f, 0f, 0f, 0f, -1f, 0f, 0f, 0f, 1f }; // x轴
        matrix.setValues(matrixValues);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        matrix.postTranslate(height, width);
        postInvalidate();
    }
}
