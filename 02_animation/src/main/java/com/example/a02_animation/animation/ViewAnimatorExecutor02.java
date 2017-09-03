package com.example.a02_animation.animation;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

/**
 * Created by pengxiaolve on 17/8/17.
 */

public class ViewAnimatorExecutor02 extends AnimatorExecutor<TextView> {

    private ValueAnimator mAnimator;

    public ViewAnimatorExecutor02(TextView textView) {
        super(textView);

        mAnimator = ValueAnimator.ofInt(0xffff0000, 0xff0000ff);
        mAnimator.setDuration(3000);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setEvaluator(new ArgbEvaluator());

        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int color = (int) animation.getAnimatedValue();
                mTargetObject.setBackgroundColor(color);
            }
        });

        setValueAnimator(mAnimator);
    }
}
