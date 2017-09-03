package com.example.a02_animation.animation;

import android.animation.TypeEvaluator;

import com.example.a02_animation.animation.view.Circle;

/**
 * Created by pengxiaolve on 17/8/17.
 */

public class CircleEvaluator implements TypeEvaluator<Circle> {
    @Override
    public Circle evaluate(float fraction, Circle startValue, Circle endValue) {
        float start = startValue.getRedius();
        float end = endValue.getRedius();
        float result = start + fraction * (end - start);
        return new Circle(result);
    }
}
