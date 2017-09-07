package com.example.customviewpractice03;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Scroller;

public class SlideLayout extends FrameLayout {

    private View mMainView;
    private View mSubView;
    private View mSubView2;
    private Scroller mScroller;
    private float mStartX;

    private static final String TAG = "SlideLayout";

    public SlideLayout(@NonNull Context context) {
        this(context, null);
    }

    public SlideLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void initView() {
        mScroller = new Scroller(getContext());
        FrameLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            if (i == 0) {
                mMainView = getChildAt(i);
                measureChild(mMainView, widthMeasureSpec, heightMeasureSpec);
            } else if (i == 1) {
                mSubView = getChildAt(i);
                measureChild(mSubView, widthMeasureSpec, heightMeasureSpec);
            } else if (i == 2) {
                mSubView2 = getChildAt(i);
                measureChild(mSubView2, widthMeasureSpec, heightMeasureSpec);
            }
        }

        if (widthMode == MeasureSpec.AT_MOST || widthMode == MeasureSpec.UNSPECIFIED) {
            width = mMainView.getLayoutParams().width + mSubView.getMeasuredWidth();
            if (mSubView2 != null) {
                width += mSubView2.getMeasuredWidth();
            }
        } else {
            Log.d(TAG, "onMeasure: widthMode : EXACTLY_MODE");
        }

        if (heightMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.UNSPECIFIED) {
            height = Math.max(mMainView.getMeasuredHeight(), mSubView.getMeasuredHeight());
        } else {
            Log.d(TAG, "onMeasure: heightMode : EXACTLY_MODE");
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        mMainView.layout(0, 0, mMainView.getMeasuredWidth(), mMainView.getMeasuredHeight());

        mSubView.layout(mMainView.getMeasuredWidth(), 0, mMainView.getMeasuredWidth() + mSubView.getMeasuredWidth(), mSubView.getMeasuredHeight());

        if (mSubView2 != null) {
            mSubView2.layout(mMainView.getMeasuredWidth() + mSubView.getMeasuredWidth(), 0, mMainView.getMeasuredWidth() + mSubView.getMeasuredWidth() + mSubView2.getMeasuredWidth(), mSubView2.getMeasuredHeight());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int subViewWidth = mSubView.getWidth() + (mSubView2 == null ? 0 : mSubView2.getWidth());

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = event.getX();
                return true;
            case MotionEvent.ACTION_MOVE:
                int distanceX = (int) (mStartX - event.getX());
                mStartX = event.getX();

                /** Only when silde view appear, can the view slide */
                if (getScrollX() <= subViewWidth && getScrollX() >= 0) {
                    /** slide to left */
                    if (distanceX >= 0) {
                        if (distanceX + getScrollX() > subViewWidth) {
                            distanceX = subViewWidth - getScrollX();
                        }
                    } else { /** slide to right */
                        if (distanceX + getScrollX() < 0) {
                            distanceX = -getScrollX();
                        }
                    }
                    scrollBy(distanceX, 0);
                }

                return true;
            case MotionEvent.ACTION_UP:
                if (getScrollX() > subViewWidth / 2) {
                    smoothScrollTo(subViewWidth, 0);
                } else {
                    smoothScrollTo(0, 0);
                }

                return true;
        }

        return super.onTouchEvent(event);
    }

    private void smoothScrollTo(int finalX, int finalY) {
        smoothScrollBy(finalX - getScrollX(), finalY);
    }

    private void smoothScrollBy(int dx, int dy) {
        mScroller.startScroll(getScrollX(), 0, dx, dy);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), 0);
            postInvalidate();
        }
    }
}
