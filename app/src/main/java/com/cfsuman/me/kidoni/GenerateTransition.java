package com.cfsuman.me.kidoni;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.transition.AutoTransition;
import android.transition.Fade;
import android.transition.Explode;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;

public class GenerateTransition {

    public static void backgroundInitialColorTransition(View... views){
        for(View v:views){
            ColorDrawable[] colors = {new ColorDrawable(Color.TRANSPARENT), new ColorDrawable(Color.BLUE)};
            TransitionDrawable transitionDrawable = new TransitionDrawable(colors);
            v.setBackground(transitionDrawable);
            transitionDrawable.startTransition(1000);
        }
    }
    public static void backgroundNegativeColorTransition(View... views){
        for(View v:views){
            ColorDrawable[] colors = {new ColorDrawable(Color.BLUE), new ColorDrawable(Color.RED)};
            TransitionDrawable transitionDrawable = new TransitionDrawable(colors);
            v.setBackground(transitionDrawable);
            transitionDrawable.startTransition(1000);
        }
    }
    public static void backgroundPositiveColorTransition(View... views){
        for(View v:views){
            ColorDrawable[] colors = {new ColorDrawable(Color.BLUE), new ColorDrawable(Color.GREEN)};
            TransitionDrawable transitionDrawable = new TransitionDrawable(colors);
            v.setBackground(transitionDrawable);
            transitionDrawable.startTransition(1000);
        }
    }

    public static Fade makeFadeTransition(){
        Fade fade = new Fade();
        fade.setDuration(1000);
        fade.setInterpolator(new AccelerateInterpolator());
        return fade;
    }

    public static Slide makeSlideTransition(){
        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.RIGHT);
        slide.setInterpolator(new AccelerateInterpolator());
        slide.setDuration(1000);
        return slide;
    }

    public static Explode makeExplodeTransition(){
        Explode explode = new Explode();
        explode.setDuration(1000);
        explode.setInterpolator(new AnticipateOvershootInterpolator());
        return explode;
    }

    public static AutoTransition makeAutoTransition(){
        AutoTransition autoTransition = new AutoTransition();
        autoTransition.setDuration(1000);
        autoTransition.setInterpolator(new BounceInterpolator());
        return autoTransition;
    }
}
