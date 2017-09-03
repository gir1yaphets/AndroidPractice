package com.example.customviewpractice.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by pengxiaolve on 17/8/5.
 */

public class FlowLayout extends ViewGroup {

    private static final String TAG = "FlowLayout";
    private static final int DEFAULT_MARGIN = 4;

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width;
        int height;

        int lineWidth = DEFAULT_MARGIN;
        int lineHeight = DEFAULT_MARGIN;
        int row = 1;

        if (heightMode == MeasureSpec.AT_MOST) {
            int childCount = getChildCount();

            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                measureChild(childView, widthMeasureSpec, heightMeasureSpec);
                if (childView.getLayoutParams() instanceof MarginLayoutParams) {
                    MarginLayoutParams mlp = (MarginLayoutParams) childView.getLayoutParams();

                    lineHeight = getOneChildTotalHeight(childView);
                    /** 若行宽度加上下一个子view的宽度超出了最大宽度，则需要换行， 行宽度重新计算 */
                    if (lineWidth + getOneChildTotalWidth(childView) > measureWidth) {
                        row += 1;
                        lineWidth = DEFAULT_MARGIN;
                    } else {
                        lineWidth += getOneChildTotalWidth(childView);
                    }
                }
            }

            width = measureWidth;
            height = row * lineHeight;

            Log.d(TAG, "onMeasure: width = " + width + "height = " + height);
            setMeasuredDimension(width, height);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();

        int parentWidth = getMeasuredWidth();
        int lineWidth = DEFAULT_MARGIN;
        int lineHeight = DEFAULT_MARGIN;

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);

            /** 当前宽度＋下一个子view宽度超过父布局宽度，需要换行摆放 */
            if (lineWidth + getOneChildTotalWidth(childView) >= parentWidth) {
                lineWidth = DEFAULT_MARGIN;
                lineHeight += getOneChildTotalHeight(childView);
            }
            childView.layout(lineWidth, lineHeight,
                    lineWidth + childView.getMeasuredWidth(), lineHeight + childView.getMeasuredHeight());
            lineWidth += getOneChildTotalWidth(childView);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return super.generateDefaultLayoutParams();
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    /**
     * 获取某一个view的总宽度，包括左右margin
     * @param childView
     * @return
     */
    private int getOneChildTotalWidth(View childView) {
        int childWidth = childView.getMeasuredWidth();
        if (childView.getLayoutParams() instanceof MarginLayoutParams) {
            MarginLayoutParams mlp = (MarginLayoutParams) childView.getLayoutParams();
            childWidth += mlp.leftMargin + mlp.rightMargin;
        }

        return childWidth;
    }

    /**
     * 获取某一个view的总高度，包括上下margin
     * @param childView
     * @return
     */
    private int getOneChildTotalHeight(View childView) {
        int childHeight = childView.getMeasuredHeight();
        if (childView.getLayoutParams() instanceof MarginLayoutParams) {
            MarginLayoutParams mlp = (MarginLayoutParams) childView.getLayoutParams();
            childHeight += mlp.topMargin + mlp.bottomMargin;
        }

        return childHeight;
    }
}
