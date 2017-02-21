package com.cfsuman.me.kidoni;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public class ScreenManager {
    private Context mContext;

    ScreenManager(Context context){
        this.mContext = context;
    }

    // Custom method to get screen density using Context object
    protected float getScreenDensity(){
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) mContext.getSystemService(mContext.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        return density;
    }

    // Method for converting pixels value to dps
    protected int getDPsFromPixels(int pixels){
        Resources r = mContext.getResources();
        int  dps = Math.round(pixels/(r.getDisplayMetrics().densityDpi/160f));
        return dps;
    }


    // Method for converting Pixels value to actual DPs (return float value)
    protected float getActualDPsFromPixels(int pixels){
        Resources r = mContext.getResources();

        // Math.round() return the nearest whole number
        float  dps = pixels/(r.getDisplayMetrics().densityDpi/160f);
        return dps;
    }

    // Custom method to get screen height in pixels using Context object
    protected Point getScreenSizeInPixels(){
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) mContext.getSystemService(mContext.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        Point size = new Point(dm.widthPixels,dm.heightPixels);
        return size;
    }

    // Custom method to get screen height in dp/dip using Context object
    protected Point getScreenSizeInDPs(){
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) mContext.getSystemService(mContext.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        int widthInDP = Math.round(dm.widthPixels / dm.density);
        int heightInDP = Math.round(dm.heightPixels / dm.density);
        Point size = new Point(widthInDP,heightInDP);
        return size;
    }

    // Custom method to get screen current orientation
    protected String getScreenOrientation() {
        String orientation = "";
        int currentOrientation = mContext.getResources().getConfiguration().orientation;

        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            orientation = "Landscape";
        }

        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            orientation = "Portrait";
        }
        return orientation;
    }

    // Custom method to change Status Bar color
    protected static void changeStatusBarColor(Activity activity, int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //Do what you need for this SDK
            Window window = activity.getWindow();
            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            // finally change the color
            window.setStatusBarColor(color);
        };
    }

    // Custom method to get screen height in pixels using Context object
    public static int getScreenWidthInPixels(Context context){
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        return width;
    }

    // Custom method to get screen height in pixels using Context object
    public static int getScreenHeightInPixels(Context context){
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        int width = dm.heightPixels;
        return width;
    }
}
