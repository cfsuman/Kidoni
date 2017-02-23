package com.cfsuman.me.kidoni;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.view.View;

public class GenerateTransition {

    public static void backgroundInitialColorTransition(View... views){
        for(View v:views){
            ColorDrawable[] colors = {new ColorDrawable(Color.TRANSPARENT), new ColorDrawable(Color.BLUE)};
            TransitionDrawable transitionDrawable = new TransitionDrawable(colors);
            v.setBackground(transitionDrawable);
            transitionDrawable.startTransition(2000);
        }
    }
    public static void backgroundNegativeColorTransition(View... views){
        for(View v:views){
            ColorDrawable[] colors = {new ColorDrawable(Color.BLUE), new ColorDrawable(Color.RED)};
            TransitionDrawable transitionDrawable = new TransitionDrawable(colors);
            v.setBackground(transitionDrawable);
            transitionDrawable.startTransition(2000);
        }
    }
    public static void backgroundPositiveColorTransition(View... views){
        for(View v:views){
            ColorDrawable[] colors = {new ColorDrawable(Color.BLUE), new ColorDrawable(Color.GREEN)};
            TransitionDrawable transitionDrawable = new TransitionDrawable(colors);
            v.setBackground(transitionDrawable);
            transitionDrawable.startTransition(2000);
        }
    }
}
