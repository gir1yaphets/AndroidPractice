package com.example.a02_animation.animation;

import android.animation.ValueAnimator;

/**
 * Created by pengxiaolve on 17/8/17.
 */

public class AnimatorExecutor<T> {
    protected T mTargetObject;
    private ValueAnimator mValueAnimator;

    public AnimatorExecutor(T t) {
        mTargetObject = t;
    }

    protected void setValueAnimator(ValueAnimator v) {
        this.mValueAnimator = v;
    }

    public void onStartAnimation() {
        if (mValueAnimator != null) {
            mValueAnimator.start();
        }
    }
}
