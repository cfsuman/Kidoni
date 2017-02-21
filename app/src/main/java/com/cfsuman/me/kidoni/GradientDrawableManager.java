package com.cfsuman.me.kidoni;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;

import java.util.Random;

public class GradientDrawableManager {
    private Context mContext;
    private Point mSize;
    private Random mRandom = new Random();

    GradientDrawableManager(Context context, Point size){
        this.mContext = context;
        this.mSize = size;
    }

    protected GradientDrawable getRadialGradientDrawable(){
        GradientDrawable drawable = new GradientDrawable();
        drawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        drawable.setColors(getRandomColorArray());
        drawable.setDither(true);
        //drawable.setGradientRadius(mSize.x / 2);
        //drawable.setGradientCenter(mRandom.nextInt(mSize.x), mRandom.nextInt(mSize.y));
        drawable.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
        float gradientRadius = mRandom.nextFloat() *(360-0)+0;
        drawable.setGradientRadius(gradientRadius);

        return drawable;
    }

    protected GradientDrawable getSweepGradientDrawable(){
        GradientDrawable drawable = new GradientDrawable();
        drawable.setGradientType(GradientDrawable.SWEEP_GRADIENT);
        drawable.setColors(getRandomColorArray());
        drawable.setDither(true);
        //drawable.setGradientRadius(mSize.x / 2);
        //drawable.setGradientCenter(mRandom.nextInt(mSize.x), mRandom.nextInt(mSize.y));
        drawable.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
        float gradientRadius = mRandom.nextFloat() *(360-0)+0;
        drawable.setGradientRadius(gradientRadius);

        return drawable;
    }

    protected GradientDrawable getLinearGradientDrawable(){
        GradientDrawable drawable = new GradientDrawable();
        drawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        drawable.setColors(getRandomColorArray());
        drawable.setDither(true);
        //drawable.setGradientRadius(mSize.x / 2);
        //drawable.setGradientCenter(mRandom.nextInt(mSize.x), mRandom.nextInt(mSize.y));
        drawable.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
        float gradientRadius = mRandom.nextFloat() *(360-0)+0;
        drawable.setGradientRadius(gradientRadius);

        return drawable;
    }

    // Custom method to generate random color array
    protected int[] getRandomColorArray(){
        int length = mRandom.nextInt(10-3)+3;
        int[] colors = new int[length];
        for (int i=0; i<length;i++){
            colors[i]=getRandomHSVColor();
        }
        // Return the color array
        return colors;
    }

    // Custom method to generate random HSV color
    protected int getRandomHSVColor(){
        // Generate a random hue value between 0 to 360
        int hue = mRandom.nextInt(361);
        // We make the color depth full
        float saturation = 1.0f;
        // We make a full bright color
        float value = 1.0f;
        // We avoid color transparency
        int alpha = 255;
        // Finally, generate the color
        int color = Color.HSVToColor(alpha, new float[]{hue, saturation, value});
        // Return the color
        return color;
    }
}
