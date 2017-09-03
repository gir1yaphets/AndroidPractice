package com.example.a02_animation.animation;

import android.animation.ValueAnimator;
import android.view.animation.BounceInterpolator;

import com.example.a02_animation.animation.view.Circle;
import com.example.a02_animation.animation.view.CircleView;

/**
 * Created by pengxiaolve on 17/8/17.
 */

public class ViewAnimatorExecutor03 extends AnimatorExecutor<CircleView> {

    private ValueAnimator mAnimator;

    public ViewAnimatorExecutor03(CircleView circleView) {
        super(circleView);
        mAnimator = ValueAnimator.ofObject(new CircleEvaluator(), new Circle(20), new Circle(80));
        mAnimator.setDuration(5000);
        mAnimator.setInterpolator(new BounceInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Circle circle = (Circle) animation.getAnimatedValue();
                mTargetObject.setCircle(circle);
                mTargetObject.invalidate();
            }
        });
        setValueAnimator(mAnimator);
    }
}
