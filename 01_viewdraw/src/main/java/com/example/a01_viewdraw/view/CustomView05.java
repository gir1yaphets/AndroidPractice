package com.example.a01_viewdraw.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.example.a01_viewdraw.R;

/**
 * Created by pengxiaolve on 17/8/14.
 */

public class CustomView05 extends View {
    int mTouchX;
    int mTouchY;

    public CustomView05(Context context) {
        super(context);
    }

    public CustomView05(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView05(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchX = (int) event.getX();
                mTouchY = (int) event.getY();
                invalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                mTouchX = (int) event.getX();
                mTouchY = (int) event.getY();
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
                return true;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.kobe_bryant_invincible);
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        int width = windowManager.getDefaultDisplay().getWidth();
        int height = windowManager.getDefaultDisplay().getHeight();
        Bitmap bitmapBg = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas cavasBg = new Canvas(bitmapBg);
        cavasBg.drawBitmap(bitmap, null, new Rect(0, 0, getWidth(), getHeight()), new Paint());

        Shader shader = new BitmapShader(bitmapBg, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);
        canvas.drawCircle(mTouchX, mTouchY, 100, paint);
    }
}
