package com.example.a01_viewdraw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by pengxiaolve on 17/7/18.
 */

public class CustomView01 extends View {
    public CustomView01(Context context) {
        super(context);
    }

    public CustomView01(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView01(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /** 空心圆 */
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        canvas.drawCircle(100, 100, 50, paint);

        /** 空心圆环 */
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.CYAN);
        paint.setStrokeWidth(20);
        paint.setAntiAlias(true);
        canvas.drawCircle(300, 100, 50, paint);

        /** 渐变 */
        Shader shader = new LinearGradient(50, 200, 150, 200,
                Color.parseColor("#E91E63"), Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(100, 200, 50, paint);
    }
}
