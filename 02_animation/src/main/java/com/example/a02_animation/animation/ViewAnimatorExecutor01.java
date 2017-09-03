package com.example.a02_animation.animation;

import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

/**
 * Created by pengxiaolve on 17/8/17.
 */

public class ViewAnimatorExecutor01 extends AnimatorExecutor<TextView> {

    private ValueAnimator mAnimator;

    public ViewAnimatorExecutor01(TextView textView) {
        super(textView);
        mAnimator = ValueAnimator.ofInt(100, 500);
        mAnimator.setDuration(3000);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int height = (int) animation.getAnimatedValue();
                mTargetObject.layout(mTargetObject.getLeft(), height, mTargetObject.getRight(), mTargetObject.getHeight() + height);
            }
        });

        setValueAnimator(mAnimator);
    }
}
