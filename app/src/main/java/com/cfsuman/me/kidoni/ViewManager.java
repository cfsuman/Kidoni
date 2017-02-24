package com.cfsuman.me.kidoni;

import android.view.View;
import android.view.ViewGroup;

public class ViewManager {

    // Custom method to change a view size
    public static void setNewSize(View v, int width, int height){
        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) v.getLayoutParams();
        params.width = width;
        params.height = height;
        v.setLayoutParams(params);
    }

    // Custom method to set a view width and height as container
    public static void viewMatchLayoutSize(View v){
        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) v.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height =ViewGroup.LayoutParams.MATCH_PARENT;
        v.setLayoutParams(params);
    }


}
