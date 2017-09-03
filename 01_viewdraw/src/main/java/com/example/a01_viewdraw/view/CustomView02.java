package com.example.a01_viewdraw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by pengxiaolve on 17/7/23.
 */

public class CustomView02 extends View {
    public CustomView02(Context context) {
        super(context);
    }

    public CustomView02(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView02(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
//        canvas.drawCircle(200, 200, 100, paint);
        canvas.drawOval(100, 100, 300, 200, paint);
    }
}
