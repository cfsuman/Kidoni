package com.cfsuman.me.kidoni;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.firebase.analytics.FirebaseAnalytics;

public class KidoniApplication extends MultiDexApplication {
    @Override
    public void onCreate(){
        super.onCreate();
        // Initialize the Fresco class
        Fresco.initialize(this);

        // Obtain the FirebaseAnalytics instance.
        //FirebaseAnalytics.getInstance(this);
    }
}