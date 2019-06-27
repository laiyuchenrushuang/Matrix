package com.ly.mywhatch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by ly on 2019/6/27 9:37
 * <p>
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
public class ShiliView extends View {
    int mWidth, mHeight;
    float focusX, focusY;

    int radius = 300;
    private Canvas mCanvans;
    private int[] result;

    public ShiliView(Context context) {
        super(context);
        init();
    }

    public ShiliView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShiliView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mCanvans = new Canvas();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        Log.d("lylog", "w = " + mWidth + " h =" + mHeight);
        focusY = (float) mHeight / 2;
        focusX = (float) mWidth / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint linePaint = new Paint();
        linePaint.setAntiAlias(true);//抗锯齿
        linePaint.setStrokeWidth(2);

        canvas.drawLine(focusX - radius, focusY, focusX + radius, focusY, linePaint);

        canvas.rotate(60, focusX, focusY);
        canvas.drawLine(focusX - radius, focusY, focusX + radius, focusY, linePaint);

        canvas.rotate(60, focusX, focusY);
        canvas.drawLine(focusX - radius, focusY, focusX + radius, focusY, linePaint);
        canvas.save();
        canvas.restore();

        canvas.rotate(240, focusX, focusY);
        for (int i = 0; i < 6; i++) {
            int r = radius - 50 * i;
            canvas.drawLine(focusX - r, focusY, focusX - r + r * (float) Math.cos(Math.PI / 3), focusY - r * (float) Math.sin(Math.PI / 3), linePaint);
            canvas.drawLine(focusX - r + r * (float) Math.cos(Math.PI / 3), focusY - r * (float) Math.sin(Math.PI / 3), focusX - r + r * (float) Math.cos(Math.PI / 3) + r, focusY - r * (float) Math.sin(Math.PI / 3), linePaint);
            canvas.drawLine(focusX - r + r * (float) Math.cos(Math.PI / 3) + r, focusY - r * (float) Math.sin(Math.PI / 3), focusX + r, focusY, linePaint);

            canvas.drawLine(focusX + r, focusY, focusX + r - r * (float) Math.cos(Math.PI / 3), focusY + r * (float) Math.sin(Math.PI / 3), linePaint);
            canvas.drawLine(focusX + r - r * (float) Math.cos(Math.PI / 3), focusY + r * (float) Math.sin(Math.PI / 3), focusX + r - r * (float) Math.cos(Math.PI / 3) - r, focusY + r * (float) Math.sin(Math.PI / 3), linePaint);
            canvas.drawLine(focusX + r - r * (float) Math.cos(Math.PI / 3) - r, focusY + r * (float) Math.sin(Math.PI / 3), focusX - r, focusY, linePaint);
        }

        canvas.save();
        canvas.restore();

        Paint textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setStrokeWidth(3);
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(30);
        float size = textPaint.measureText("力量");
        Log.d("lylog", " size = " + size);//size/2 是一个字的宽高  size/4 一个字的一半
        canvas.drawText("力量", focusX - radius - size, focusY + size / 4, textPaint);

        canvas.drawText("敏捷", focusX - radius + radius * (float) Math.cos(Math.PI / 3) - size / 2, focusY - radius * (float) Math.sin(Math.PI / 3) - 5, textPaint);

        canvas.drawText("法术", focusX - radius - size / 2 + radius * (float) Math.cos(Math.PI / 3) + radius, focusY - radius * (float) Math.sin(Math.PI / 3) - 5, textPaint);

        canvas.drawText("容错", focusX + radius, focusY + size / 4, textPaint);

        canvas.drawText("爆发", focusX + radius - radius * (float) Math.cos(Math.PI / 3) - size / 2, focusY + radius * (float) Math.sin(Math.PI / 3) + size / 2, textPaint);

        canvas.drawText("控制", focusX + radius - radius * (float) Math.cos(Math.PI / 3) - radius - size / 2, focusY + radius * (float) Math.sin(Math.PI / 3) + size / 2, textPaint);

        drawShili(canvas);
    }

    private void drawShili(Canvas canvas) {
        if (result == null || result.length != 6) {
            return;
        }
        Path path = new Path();
        Paint powPait = new Paint();
        powPait.setAntiAlias(true);

        powPait.setColor(getResources().getColor(R.color.shili));
        powPait.setAlpha(200);
        powPait.setStyle(Paint.Style.FILL_AND_STROKE);;

        for (int i = 0; i < result.length; i++) {
            int len = result[i];
            if (i == 0){
                path.moveTo(focusX - radius*(float)Math.cos(Math.PI/3*i)*len/6,focusY-radius*(float)Math.sin(Math.PI/3*i)*len/6);
            }else {
                path.lineTo(focusX - radius*(float)Math.cos(Math.PI/3*i)*len/6,focusY-radius*(float)Math.sin(Math.PI/3*i)*len/6);
            }
        }
        path.close();
        canvas.drawPath(path, powPait);
    }

    public void setPower(int[] result) {
        this.result = result;
        postInvalidate();
    }
}
