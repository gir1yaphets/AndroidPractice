package com.example.a02_animation.animation;

import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

/**
 * Created by pengxiaolve on 17/8/17.
 */

public class ViewAnimatorExecutor04 extends AnimatorExecutor<TextView> {
    private ValueAnimator mAnimator;

    public ViewAnimatorExecutor04(TextView textView) {
        super(textView);
        mAnimator = ValueAnimator.ofInt(0, 180, 0);
        mAnimator.setDuration(3000);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int angle = (int) animation.getAnimatedValue();
                mTargetObject.setRotationX(angle);
            }
        });

        setValueAnimator(mAnimator);
    }
}
