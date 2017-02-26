package com.cfsuman.me.kidoni;


import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

public class AnimationManager {

    public static void startReverseScaleAnimation(View v){
        // Initialize a new ObjectAnimator
        ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(
                v, // Target object
                "scaleX", // Property
                0.0f // ending value
        );

        // Initialize another new ObjectAnimator
        ObjectAnimator scaleYAnimation = ObjectAnimator.ofFloat(
                v, // Target object
                "scaleY", // Property
                0.0f // ending value
        );

        // Set the animation duration in milliseconds
        // 1000 milliseconds = 1 second
        scaleXAnimation.setDuration(300);
        scaleYAnimation.setDuration(300);

        scaleXAnimation.setEvaluator(new FloatEvaluator());
        scaleYAnimation.setEvaluator(new FloatEvaluator());

        // Set the animation interpolator
        scaleXAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleYAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        scaleXAnimation.setRepeatCount(1);
        scaleYAnimation.setRepeatCount(1);
        scaleXAnimation.setRepeatMode(ValueAnimator.REVERSE);
        scaleYAnimation.setRepeatMode(ValueAnimator.REVERSE);

        AnimatorSet scaleDown = new AnimatorSet();
        scaleDown.play(scaleXAnimation).with(scaleYAnimation);
        scaleDown.start();
    }
}
