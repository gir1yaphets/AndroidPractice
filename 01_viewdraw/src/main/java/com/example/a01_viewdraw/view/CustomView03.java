package com.example.a01_viewdraw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by pengxiaolve on 17/7/26.
 */

public class CustomView03 extends View {
    public CustomView03(Context context) {
        super(context);
    }

    public CustomView03(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView03(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        Path path = new Path();
        path.addArc(100, 100, 200, 200, -225, 225);
        path.arcTo(200, 100, 300, 200, -180, 225, false);
        path.lineTo(200,285);
        path.close();

        canvas.drawPath(path, paint);
    }
}
