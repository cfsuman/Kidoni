package com.cfsuman.me.kidoni;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private Activity mActivity;

    private CoordinatorLayout mCLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    //------------------- firebase + ad setup 1 --------------------
    private FirebaseAnalytics mFirebaseAnalytics;
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

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            getWindow().setEnterTransition(GenerateTransition.makeSlideTransition());
            getWindow().setExitTransition(GenerateTransition.makeFadeTransition());
        }

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
}
