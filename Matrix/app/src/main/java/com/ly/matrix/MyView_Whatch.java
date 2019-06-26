package com.ly.mywhatch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ly on 2019/6/26 14:55
 * <p>
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
public class MyView extends View {
    Paint mPaint;
    int width, height;

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

    public MyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        float fx = width / 2;
        float fy = height / 2;
        canvas.drawCircle(fx, fy, 300, mPaint);
        //画表盘
        for (int i = 0; i <= 60; i++) {
            int lenth = 0;
            if (i % 5 == 0) {
                lenth = 20;
                mPaint.setStrokeWidth(10);
            } else {
                lenth = 10;
                mPaint.setStrokeWidth(5);
            }
            canvas.drawLine((float) (fx + 300 * Math.cos(Math.toRadians(i * 6))), (float) (fy - 300 * Math.sin(Math.toRadians(i * 6))), (float) (fx + (300 - lenth) * Math.cos(Math.toRadians(i * 6))), (float) (fy - (300 - lenth) * Math.sin(Math.toRadians(i * 6))), mPaint);
        }
        //画时间字
        Paint textP = new Paint();
        textP.setTextSize(40);
        textP.setAntiAlias(true);
        canvas.drawText(3 + "", fx + 300 - 30 - textP.measureText(3 + ""), fy + textP.measureText(3 + "") / 2, textP);
        canvas.drawText(9 + "", fx - 300 + 30, fy + textP.measureText(9 + "") / 2, textP);

        canvas.drawText(12 + "", fx - textP.measureText(12 + "") / 2, fy - 300 + 30 + textP.measureText(12 + "") * 2 / 3, textP);
        canvas.drawText(6 + "", fx - textP.measureText(6 + "") / 2, fy + 300 - 30, textP);
//        for (int i = 1; i <= 12; i++) {
//            canvas.drawText(i + "", (float) (fx + (300 - 30-textP.measureText(i+"")/2) * Math.cos(Math.toRadians(90 - i * 30))), (float) (fy - (300 - 30-textP.measureText(i+"")/2) * Math.sin(Math.toRadians(90 - i * 30))), textP);
//        }
        //画时分秒
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setStrokeWidth(15);
        canvas.drawLine(fx, fy, fx + 100, fy + 100, p);
        canvas.rotate(30, fx, fy);
        p.setStrokeWidth(10);
        canvas.drawLine(fx, fy, fx + 150, fy + 150, p);
        canvas.rotate(60, fx, fy);
        p.setStrokeWidth(5);
        p.setColor(Color.RED);
        canvas.drawLine(fx, fy, fx + 200, fy + 200, p);
        canvas.rotate(90, fx, fy);

        //画一个点
        Paint p1 = new Paint();
        p1.setAntiAlias(true);
        p1.setStrokeWidth(10);
        canvas.drawCircle(fx,fy,10,p1);
    }
}
