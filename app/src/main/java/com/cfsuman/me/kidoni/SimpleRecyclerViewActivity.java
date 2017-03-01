package com.cfsuman.me.kidoni;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.analytics.FirebaseAnalytics;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SimpleRecyclerViewActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{
    private static final String TAG = SimpleRecyclerViewActivity.class.getSimpleName();
    private Context mContext;
    private Activity mActivity;

    private CoordinatorLayout mCLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    //------------------- firebase + ad setup 1 --------------------
    private FirebaseAnalytics mFirebaseAnalytics;
    //------------------- firebase + ad setup 1 --------------------

    private String currentTag = null;
    private static final String XML_ROOT_STRING="countries";;
    private static final String STRING_TO_GRAB="country";;
    private List<String> mList = new ArrayList<>();

    private BroadcastReceiver mTextValueReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String textValue = intent.getStringExtra("TextValue");
            speakNow(textValue);
        }
    };

    private TextToSpeech tts;
    private String mTextToSpeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_recycler_view);

        //------------------- firebase + ad setup 2 --------------------
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        //------------------- firebase + ad setup 2 --------------------

        // Get the application context
        mContext = getApplicationContext();
        // Get the activity
        mActivity = SimpleRecyclerViewActivity.this;
        tts = new TextToSpeech(mContext,this);

        // Set the activity title
        getSupportActionBar().setTitle("Country");

        // Register the local broadcast receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(
                mTextValueReceiver,new IntentFilter("BROADCAST_TEXT_VALUE")
        );

        // Get the widgets reference from XML layout
        mCLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // Specify a layout for RecyclerView
        mLayoutManager = new GridLayoutManager(mContext,2);
        mRecyclerView.setLayoutManager(mLayoutManager);


        try{
            // Initialize a new XmlPullParserFactory instance
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();

            // Get the input stream from xml file data
            InputStream stream = getResources().openRawResource(R.raw.countries);

            // Specify the input stream for the xml pull parser
            parser.setInput(stream,null);

            // Get the event type from xml pull parser
            int eventType = parser.getEventType();

            // Loop through the xml data
            while(eventType!=XmlPullParser.END_DOCUMENT){
                if(eventType == XmlPullParser.START_TAG){
                    if(parser.getName().equals(XML_ROOT_STRING)){
                    }else {
                        currentTag = parser.getName();
                    }
                }else if(eventType == XmlPullParser.END_TAG){
                    currentTag = null;
                }else if(eventType == XmlPullParser.TEXT){
                    if(currentTag!=null){
                        String textValue = parser.getText();
                        if(textValue.length()>0){
                            mList.add(textValue);
                        }
                    }
                }
                eventType = parser.next();
            }
        }catch(XmlPullParserException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

        // Specify the RecyclerView adapter
        mAdapter = new SimpleTextBaseAdapter(mContext,mList);
        mRecyclerView.setAdapter(mAdapter);
    }

    // Custom method to speak a text
    private void speakNow(String textToSpeak){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            tts.speak(textToSpeak,TextToSpeech.QUEUE_FLUSH,null,null);
        }else {
            tts.speak(textToSpeak,TextToSpeech.QUEUE_FLUSH,null);
        }
    }

    @Override
    public void onInit(int speechStatus){
        if(speechStatus==TextToSpeech.SUCCESS){
            int speechResult = tts.setLanguage(Locale.US);
            if(speechResult == TextToSpeech.LANG_MISSING_DATA){
                Log.e(TAG,"TTS error");
            }else {
                Log.e(TAG,"TTS success");
            }
        }
    }

    @Override
    public void onDestroy(){
        if(tts!=null){
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }
}
