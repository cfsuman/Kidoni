package com.cfsuman.me.kidoni;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Random;


public class ColorManagement {
    //Global variables of ColorManagement class
    public static Random rand = new Random();

    // Get a random color integer value
    public static int getColorMaxMin(int max, int min){
        int intR = rand.nextInt() * (max - min) + min;
        int intG = rand.nextInt() * (max - min) + min;
        int intB = rand.nextInt() * (max - min) + min;
        int color = Color.argb(255, intR, intG, intB);
        return  color;
    }

    public static int getRandomColor(){
        int max = 255;
        int min = 0;
        int intR = rand.nextInt() * (max - min) + min;
        int intG = rand.nextInt() * (max - min) + min;
        int intB = rand.nextInt() * (max - min) + min;
        int color = Color.argb(255, intR, intG, intB);
        return  color;
    }

    public static int generateRandomColor(){
        float max = 360;
        float min = 0;
        /*
            Hue is the variation of color
            Hue range 0 to 360
         */
        float hue = rand.nextFloat() * (max - min) + min;
        /*
            Saturation is the depth of color
            Range is 0.0 to 1.0 float value
            1.0 is 100% solid color
        */
        float saturation = rand.nextFloat() * (1 - 0) + 0;
        /*
            Black is the lightness of color
            Range is 0.0 to 1.0 float value
            1.0 is 100% bright less of a color that means black
        */
        float black = rand.nextFloat() * (1 - 0.8f) + 0.8f;
        int color = Color.HSVToColor(255, new float[]{hue, saturation, black});
        return  color;
    }

    public static ArrayList getColorArrayList(int num){
        ArrayList<Integer> al = new ArrayList<Integer>();
        for(int i=0;i<num;i++)
        {
            al.add(generateRandomColor());
        }
        //Collections.sort(al);
        return al;
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
        int color = Color.HSVToColor(alpha,new float[]{hue,saturation,value});
        // Return the color
        return color;
    }
}
