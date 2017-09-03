package com.example.customviewpractice.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.example.customviewpractice.R;

/**
 * Created by pengxiaolve on 17/8/4.
 */

public class CategoryView extends View {

    private String mCategoryText;
    private int mTextSize;
    private float mCategoryRadius;
    private int mCategoryColor;
    private int mTextColor;

    private Rect mBounds;

    private static final String TAG = "CategoryView";

    public CategoryView(Context context) {
        this(context, null);
    }

    public CategoryView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CategoryView(Context context, @Nullable
            AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        if (attrs != null) {
            mCategoryRadius = 10.0f;
            mCategoryColor = Color.WHITE;
            mTextColor = Color.BLUE;

            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CategoryView);

            mCategoryRadius = a.getDimension(R.styleable.CategoryView_categoryradius, mCategoryRadius);
            mCategoryColor = a.getColor(R.styleable.CategoryView_backgrougdcolor, mCategoryColor);
            mCategoryText = a.getString(R.styleable.CategoryView_categorytext);
            mTextSize = a.getDimensionPixelSize(R.styleable.CategoryView_textsize, (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));

            mTextColor = a.getColor(R.styleable.CategoryView_textcolor, mTextColor);
            a.recycle();
        }

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(mTextSize);
        mBounds = new Rect();
        paint.getTextBounds(mCategoryText, 0, mCategoryText.length(), mBounds);

        Log.d(TAG, "initView: mBounds.width = " + mBounds.width() + " mBounds.height = " + mBounds.height());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = widthMode == MeasureSpec.EXACTLY ? measureWidth : mBounds.width();
        int height = heightMode == MeasureSpec.EXACTLY ? measureHeight : mBounds.height();

        width += getPaddingLeft() + getPaddingRight();
        height += getPaddingTop() + getPaddingBottom();

        Log.d(TAG, "onMeasure() called with: width = [" + width + "], height = [" + height + "]");
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(mCategoryColor);
        paint.setAntiAlias(true);
        canvas.drawRoundRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mCategoryRadius, mCategoryRadius, paint);

        paint.setColor(mTextColor);
        paint.setTextSize(mTextSize);
        Log.d(TAG, "onDraw: getWidth = " + getWidth() + " mBounds.width() = " + mBounds.width());
        Log.d(TAG, "onDraw: getHeight() = " + getHeight() + " mBounds.height() = " + mBounds.height());
        canvas.drawText(mCategoryText, getWidth() / 2 - mBounds.width() / 2, getHeight() / 2 + mBounds.height() / 2, paint);
    }
}
