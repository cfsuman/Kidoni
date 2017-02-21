package com.cfsuman.me.kidoni;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import java.util.Random;


public class StaticDrawable {
    public static Random rand = new Random();

    public static Drawable getLayoutDrawable(){
        GradientDrawable gradientD = new GradientDrawable();
        gradientD.setShape(GradientDrawable.RECTANGLE);
        gradientD.setColors(new int[]{
                Color.WHITE,
                //ColorManagement.getRandomColor(),
                ColorManagement.getRandomColor()
        });
        gradientD.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        //gradientD.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        //gradientD.setGradientType(GradientDrawable.SWEEP_GRADIENT);

        //gradientD.setStroke(2,ColorManagement.getRandomColor());
        float gradientRadius = rand.nextFloat() *(360-0)+0;
        //gradientD.setGradientRadius(gradientRadius);
        gradientD.setGradientRadius(360);
        return gradientD;
    }

    public static Drawable GVCellDrawable(){
        GradientDrawable gradientD = new GradientDrawable();
        gradientD.setShape(GradientDrawable.RECTANGLE);
        gradientD.setColors(new int[]{
                ColorManagement.getRandomColor(),
                ColorManagement.getRandomColor(),
                //ColorManagement.getRandomColor(),
                //ColorManagement.getRandomColor(),
                //ColorManagement.getRandomColor(),
                //ColorManagement.getRandomColor(),
                ColorManagement.getRandomColor()
        });
        //gradientD.setGradientType(GradientDrawable.SWEEP_GRADIENT);
        gradientD.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        //gradientD.setGradientType(GradientDrawable.SWEEP_GRADIENT);

        //gradientD.setStroke(2,ColorManagement.getRandomColor());
        gradientD.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
        float gradientRadius = rand.nextFloat() *(360-100)+100;
        gradientD.setGradientRadius(gradientRadius);
        //gradientD.setGradientRadius(360);
        return gradientD;
    }

    public static Drawable RLDrawable(){
        GradientDrawable gradientD = new GradientDrawable();
        gradientD.setShape(GradientDrawable.RECTANGLE);
        gradientD.setColors(new int[] {
                ColorManagement.getRandomColor(),
                //Color.parseColor("#FFFFFF"),
                ColorManagement.getRandomColor()
        });

        gradientD.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        //gradientD.setStroke(2,ColorManagement.getRandomColor());
        gradientD.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
        float gradientRadius = rand.nextFloat() *(360-0)+0;
        gradientD.setGradientRadius(250);
        return gradientD;
    }

    // Custom method to generate random lighter HSV color
    public static int getRandomLighterHSVColor(){
        // Generate a random hue value between 0 to 360
        int hue = rand.nextInt(361);
        // We make the color depth full
        float saturation = 0.2f;
        // We make a full bright color
        float value = 1.0f;
        // We avoid color transparency
        int alpha = 255;
        // Finally, generate the color
        int color = Color.HSVToColor(alpha, new float[]{hue, saturation, value});
        // Return the color
        return color;
    }

    // Custom method to generate random HSV color
    // Lower saturation value make brighter color to deep color
    public static int getRandomHSVColorBySaturation(float saturation){
        // Generate a random hue value between 0 to 360
        int hue = rand.nextInt(361);
        // We make the color depth full
        //float saturation = 0.2f;
        // We make a full bright color
        float value = 0.8f;
        // We avoid color transparency
        int alpha = 255;
        // Finally, generate the color
        int color = Color.HSVToColor(alpha, new float[]{hue, saturation, value});
        // Return the color
        return color;
    }

    // Custom method to get a darker color
    public static int getDeepColorBySaturation(int color, float saturation){
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        //hsv[2] = 0.8f *hsv[2];
        hsv[2] = saturation *hsv[2]; // more darker
        return Color.HSVToColor(hsv);
    }

    // Custom method to generate random darker HSV color
    public static int getRandomDarkerHSVColor(){
        // Generate a random hue value between 0 to 360
        int hue = rand.nextInt(361);
        // We make the color depth full
        float saturation = 1.0f;
        // We make a full bright color
        float value = 0.8f;
        // We avoid color transparency
        int alpha = 255;
        // Finally, generate the color
        int color = Color.HSVToColor(alpha, new float[]{hue, saturation, value});
        // Return the color
        return color;
    }

    // Custom method to generate random HSV color
    public static int getRandomHSVColor(){
        // Generate a random hue value between 0 to 360
        int hue = rand.nextInt(361);
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

    // Custom method to get a lighter color
    public static int getLighterColor(int color){
        float[] hsv = new float[3];
        Color.colorToHSV(color,hsv);
        hsv[2] = 0.2f + 0.8f * hsv[2];
        return Color.HSVToColor(hsv);
    }

    // Custom method to get reverse color
    public static int getReverseColor(int color){
        float[] hsv = new float[3];
        Color.RGBToHSV(
                Color.red(color), // Red value
                Color.green(color), // Green value
                Color.blue(color), // Blue value
                hsv
        );
        hsv[0] = (hsv[0] + 180) % 360;
        return Color.HSVToColor(hsv);
    }

}
