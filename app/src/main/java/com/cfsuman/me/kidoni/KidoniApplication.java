package com.cfsuman.me.kidoni;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class KidoniApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        // Initialize the Fresco class
        Fresco.initialize(this);
    }
}