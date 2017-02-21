package com.cfsuman.me.kidoni;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

import java.util.Locale;

public class AlphabetDetailsActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private Context mContext;

    private TextToSpeech tts;
    private boolean mSoundIsOn=true;
    private String mStringToSplit = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";;
    private String[] mDataArray = new String[]{};
    private Activity mActivity;

    private PagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private RelativeLayout mRelativeLayout;
    private ToggleButton mToggleButtonSound;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private String mTextToSpeak;

    private int mThemeColor = StaticDrawable.getRandomHSVColorBySaturation(0.9f);


    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            int position = intent.getIntExtra("position", -1);
            //int position = 0;
            mTextToSpeak = mDataArray[position];

            // Speak out after closing ad
            if (mSoundIsOn) { // If the sound is enabled
                speakNow(mTextToSpeak);
            }
            //Toast.makeText(mContext,mTextToSpeak,Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet_details);

        // Receive the local broadcast
        LocalBroadcastManager.getInstance(this).registerReceiver(
                mMessageReceiver,
                new IntentFilter("SEND_CLICK_POSITION")
        );


        // Set the context, speech and activity
        mContext = getApplicationContext();
        tts = new TextToSpeech(mContext,this);
        mActivity = AlphabetDetailsActivity.this;

        // Set the action bar title
        getSupportActionBar().setTitle("");

        // Set a random deep color for status bar
        ScreenManager.changeStatusBarColor(
                mActivity, StaticDrawable.getDeepColorBySaturation(
                        mThemeColor, 0.8f
                )
        );

        // Set the action bar background color
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(
                        StaticDrawable.getDeepColorBySaturation(mThemeColor, 0.9f)
                )
        );

        // get the widgets reference from Xml Layout
        mRelativeLayout = (RelativeLayout) findViewById(R.id.rl);
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mToggleButtonSound = (ToggleButton) findViewById(R.id.tb_sound);

        // Set the RelativeLayout background
        mRelativeLayout.setBackground(StaticDrawable.getLayoutDrawable());

        mPagerAdapter = new AlphabetPagerAdapter(mContext);
        mViewPager.setAdapter(mPagerAdapter);

        // Set the PagerView page transformer
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        //vp.setPageTransformer(true,new DepthPageTransformer());

        //int position = getIntent().getIntExtra("position",-1);
        int position = 0;
        mViewPager.setCurrentItem(position);
        mDataArray = mStringToSplit.split("(?!^)");

        // Set initial title of action bar
        mTextToSpeak = mDataArray[position];
        getSupportActionBar().setTitle(mTextToSpeak);

        // Speak out the text
        if (mSoundIsOn) { // If the sound is enabled
            speakNow(mTextToSpeak);
        }

        // Set a page change listener for ViewPager object
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Toast.makeText(mContext, "Scroll : "+mDataArray[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageSelected(int position) {
                mTextToSpeak = mDataArray[position];

                // Speak out after closing ad
                //mSoundIsOn = mSharedPreferences.getBoolean(getString(R.string.sound_is_on), true);
                if (mSoundIsOn) { // If the sound is enabled
                    speakNow(mTextToSpeak);
                }
                getSupportActionBar().setTitle(mTextToSpeak);
                //Toast.makeText(mContext, "Page : " + mTextToSpeak, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
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
    public void onInit(int speechStatus){
        if(speechStatus == TextToSpeech.SUCCESS)
        {
            int speechResult = tts.setLanguage(Locale.US);
            if(speechResult == TextToSpeech.LANG_MISSING_DATA){
                Log.e("TTS", "Language not supported");
            } else {
                //speakNow();
                Log.e("TTS", "Language is ok.");
            }
        }else{
            Log.e("TTS", "Failed to initialize TextToSpeech service.");
            //Toast.makeText(this, "Failed to speak.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    // Custom method to speak the text
    private void speakNow(String textToSpeak) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            tts.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null,null);
        }else {
            tts.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null);
        }
    }
}
