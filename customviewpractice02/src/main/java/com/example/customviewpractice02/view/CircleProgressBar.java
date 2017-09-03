package com.example.customviewpractice02.view;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.customviewpractice02.R;

/**
 * Created by pengxiaolve on 17/8/19.
 */

public class CircleProgressBar extends View {

    private int mRedius;
    private int mCircleWidth;
    private int mCircleColor;
    private int mTextSize;
    private int mTextColor;

    private int mSweepAngle;
    private String mProgressText;

    private Paint mPaint;
    private Paint mPaintText;
    private Shader mColorShader;
    private float mLineWidth;
    private static final int[] SWEEP_GRADIENT_COLORS = new int[]{0xffff4639, 0xffCDD513, 0xff3CDF5F};

    private static final String TAG = "CircleProgressBar";

    public CircleProgressBar(Context context) {
        this(context, null);
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void setDefaultValue() {
        mRedius = 150;
        mCircleWidth = 10;
        mCircleColor = Color.RED;
        mTextSize = 15;
        mTextColor = Color.BLACK;

        mSweepAngle = 0;
        mProgressText = 0 + "%";

        mLineWidth = 40;
    }

    private void initView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        setDefaultValue();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar);

        if (a != null) {
            for (int i = 0; i < a.length(); i++) {
                int attr = a.getIndex(i);
                switch (attr) {
                    case R.styleable.CircleProgressBar_circleRadius:
                        mRedius = (int) a.getDimension(i, mRedius);
                        break;
                    case R.styleable.CircleProgressBar_circleWidth:
                        mCircleWidth = (int) a.getDimension(i, mCircleWidth);
                        break;
                    case R.styleable.CircleProgressBar_circleColor:
                        mCircleColor = a.getColor(i, mCircleColor);
                        break;
                    case R.styleable.CircleProgressBar_textSize:
                        mTextSize = a.getDimensionPixelSize(i, (int) TypedValue.applyDimension(TypedValue
                                        .COMPLEX_UNIT_SP, mTextSize,
                                getResources().getDisplayMetrics()));
                        break;
                    case R.styleable.CircleProgressBar_textColor:
                        mTextColor = a.getColor(i, mTextColor);
                        break;
                    default:
                        break;
                }
            }
            a.recycle();
        }

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mLineWidth);
        mPaint.setColor(mCircleColor);
        mColorShader = new SweepGradient(200, 200, SWEEP_GRADIENT_COLORS, null);

        mPaintText = new Paint();
        mPaint.setAntiAlias(true);
        mPaintText.setColor(mTextColor);
        mPaintText.setTextSize(mTextSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setShader(mColorShader);
        canvas.drawArc(new RectF(200 - mRedius + mLineWidth / 2, 200 - mRedius + mLineWidth / 2, 200 + mRedius -
                        mLineWidth / 2, 200 + mRedius - mLineWidth / 2), -90, mSweepAngle,
                false, mPaint);
        mPaint.setShader(null);

        canvas.save();
        canvas.translate(200, 200);
        Paint paintLine = new Paint();
        paintLine.setColor(Color.parseColor("#FFFFFFF0"));
        paintLine.setAntiAlias(true);
        for (int i = 0; i < 60; i++) {
            canvas.drawLine(-mRedius, 0, -mRedius + mLineWidth, 0, paintLine);
            canvas.rotate(6);

        }
        canvas.restore();


        Rect rectText = new Rect();
        mPaintText.getTextBounds(mProgressText, 0, mProgressText.length(), rectText);
        canvas.drawText(mProgressText, 50 + mRedius - rectText.width() / 2, 200 + rectText.height() / 2,
                mPaintText);

    }

    public void startProgress() {
        ValueAnimator arcAnimator = ValueAnimator.ofInt(0, 360);
        arcAnimator.setInterpolator(new LinearInterpolator());
        arcAnimator.setDuration(5000);
        arcAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int angle = (int) animation.getAnimatedValue();
                mSweepAngle = angle;
                invalidate();
            }
        });


        ValueAnimator progressAnimator = ValueAnimator.ofInt(0, 100);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.setDuration(5000);
        progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int progress = (int) animation.getAnimatedValue();
                mProgressText = progress + "%";
            }
        });

        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet.Builder builder = animatorSet.play(arcAnimator);
        builder.with(progressAnimator);
        animatorSet.start();

        ValueAnimator colorAnimator = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            colorAnimator = ValueAnimator.ofArgb(Color.parseColor("#ff0000"), Color.parseColor("#00ff00"));
            colorAnimator.setDuration(5000);
            colorAnimator.setEvaluator(new ArgbEvaluator());
            colorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int color = (int) animation.getAnimatedValue();
                    mPaint.setColor(color);
                }
            });
//            colorAnimator.start();
        }
    }
}
