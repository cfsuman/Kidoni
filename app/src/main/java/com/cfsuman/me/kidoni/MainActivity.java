package com.cfsuman.me.kidoni;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private Activity mActivity;

    private CoordinatorLayout mCLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the application context
        mContext = getApplicationContext();

        // Get the activity
        mActivity = MainActivity.this;

        // Get the widgets reference from XML layout
        mCLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // Specify a layout for RecyclerView
        mLayoutManager = new GridLayoutManager(mContext,2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Initialize a new TinyAppsXmlPullParser instance
        TinyAppsXmlPullParser pullParser = new TinyAppsXmlPullParser();
        // Get the titny apps list from xml data
        List<TinyApp> tinyapps = pullParser.parseXMLData(mContext);

        mAdapter = new MainActivityRecyclerViewAdapter(mContext,tinyapps);

        // Set an adapter for RecyclerView
        mRecyclerView.setAdapter(mAdapter);
    }
}
