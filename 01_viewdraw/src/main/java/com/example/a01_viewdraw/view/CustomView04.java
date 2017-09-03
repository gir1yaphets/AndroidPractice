package com.example.a01_viewdraw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by pengxiaolve on 17/8/5.
 */

public class CustomView04 extends View {
    public CustomView04(Context context) {
        super(context);
    }

    public CustomView04(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView04(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.GRAY);
        float lines[] = new float[] {50, 0, 50, 300, 50, 300, 500, 300};
        canvas.drawLines(lines, paint);

        Rect rect = new Rect();
        rect.set(60, 295, 120, 300);
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(rect, paint);

        Paint paintText = new Paint();
        paintText.setTextSize(12);
        paintText.setColor(Color.BLACK);
        canvas.drawText("Froyo", 80, 320, paintText);

        rect.set(150, 200, 210, 300);
        canvas.drawRect(rect, paint);
        canvas.drawText("CB", 170, 320, paintText);
    }
}
