package com.cfsuman.me.kidoni;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Point;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;

import java.util.Random;


public class GradientManager {
    private Random mRandom = new Random();
    private Context mContext;
    private Point mSize;

    public GradientManager(Context context, Point size){
        this.mContext = context;
        this.mSize = size;
    }

    // Generate a random LinearGradient object
    protected LinearGradient getRandomLinearGradient(){
        LinearGradient gradient = new LinearGradient(
                0,
                0,
                mSize.x,
                mSize.y,
                getRandomColorArray(), // Colors to draw the gradient
                null, // No position defined
                getRandomShaderTileMode() // Shader tiling mode
        );
        // Return the LinearGradient
        return gradient;
    }

    // Generate a random RadialGradient object
    protected RadialGradient getRandomRadialGradient(){
        RadialGradient gradient = new RadialGradient(
                mRandom.nextInt(mSize.x),
                mRandom.nextInt(mSize.y),
                mRandom.nextInt(mSize.x),
                getRandomColorArray(),
                null, // Stops position is undefined
                getRandomShaderTileMode() // Shader tiling mode

        );
        // Return the RadialGradient
        return gradient;
    }

    // Generate a random SweepGradient object
    protected SweepGradient getRandomSweepGradient(){
        SweepGradient gradient = new SweepGradient(
                mRandom.nextInt(mSize.x),
                mRandom.nextInt(mSize.y),
                getRandomColorArray(), // Colors to draw gradient
                null // Position is undefined
        );
        // Return the SweepGradient
        return gradient;
    }

    // Generate random Shader TileMode
    protected Shader.TileMode getRandomShaderTileMode(){
        Shader.TileMode mode;
        int indicator = mRandom.nextInt(3);
        if(indicator==0){
            mode = Shader.TileMode.CLAMP;
        }else if(indicator==1){
            mode = Shader.TileMode.MIRROR;
        }else {
            mode = Shader.TileMode.REPEAT;
        }
        // Return the random Shader TileMode
        return mode;
    }

    // Custom method to generate random color array
    protected int[] getRandomColorArray(){
        int length = mRandom.nextInt(16-3)+3;
        int[] colors = new int[length];
        for (int i=0; i<length;i++){
            colors[i]=getRandomHSVColor();
        }
        // Return the color array
        return colors;
    }

    // Custom method to generate random HSV color
    protected int getRandomHSVColor(){
        /*
            Hue is the variation of color
            Hue range 0 to 360

            Saturation is the depth of color
            Range is 0.0 to 1.0 float value
            1.0 is 100% solid color

            Value/Black is the lightness of color
            Range is 0.0 to 1.0 float value
            1.0 is 100% bright less of a color that means black
        */

        // Generate a random hue value between 0 to 360
        int hue = mRandom.nextInt(361);

        // We make the color depth full
        float saturation = 1.0f;

        // We make a full bright color
        float value = 1.0f;

        // We avoid color transparency
        int alpha = 255;

        // Finally, generate the color
        int color = Color.HSVToColor(alpha,new float[]{hue,saturation,value});

        // Return the color
        return color;
    }
}