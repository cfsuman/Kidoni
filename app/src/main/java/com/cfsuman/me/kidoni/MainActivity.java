package com.cfsuman.me.kidoni;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.view.Gravity;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private Activity mActivity;

    private CoordinatorLayout mCLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    //------------------- firebase + ad setup 1 --------------------
    private Random mRandom = new Random();
    private FirebaseAnalytics mFirebaseAnalytics;
    private Bundle mBundle;
    InterstitialAd mInterstitialAd;
    //------------------- firebase + ad setup 1 --------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //------------------- firebase + ad setup 2 --------------------
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        //------------------- firebase + ad setup 2 --------------------

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the application context
        mContext = getApplicationContext();
        // Get the activity
        mActivity = MainActivity.this;

        Slide slideBottom = new Slide(Gravity.BOTTOM);
        slideBottom.setDuration(2000);
        getWindow().setExitTransition(slideBottom);


        Slide slideTop = new Slide(Gravity.TOP);
        slideTop.setDuration(2000);
        getWindow().setEnterTransition(slideTop);

        //------------------- firebase + ad setup 3 --------------------
        MobileAds.initialize(getApplicationContext(),getString(R.string.admob_app_id));

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.admob_interstitial_ad_unit_id));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                //beginPlayingGame();
            }
        });

        requestNewInterstitial();

        // How to show ad
        /*if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            //beginPlayingGame();
        }*/
        //------------------- firebase + ad setup 3 --------------------


        // Get the widgets reference from XML layout
        mCLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // Specify a layout for RecyclerView
        mLayoutManager = new GridLayoutManager(mContext,2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Initialize a new TinyAppsXmlPullParser instance
        TinyAppsXmlPullParser pullParser = new TinyAppsXmlPullParser();
        // Get the tiny apps list from xml data
        List<TinyApp> tinyapps = pullParser.parseXMLData(mContext);

        mAdapter = new TinyAppsRecyclerViewAdapter(mContext,mActivity,tinyapps);

        // Set an adapter for RecyclerView
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    //------------------- firebase + ad setup 5 --------------------
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
    //------------------- firebase + ad setup 5 --------------------
}
