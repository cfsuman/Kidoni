package com.cfsuman.me.kidoni;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;

import java.util.HashMap;
import java.util.Locale;

public class AlphabetActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{
    private Context mContext;
    private Activity mActivity;
    private Integer mGridView_Column_Width=100; // Column width in DPs
    private HashMap<String,Integer> mColorsMap= new MapManager().getHashMap();

    private RelativeLayout mRelativeLayout;
    private GridView mGridView;

    private TextToSpeech tts;
    private boolean mSoundIsOn=true;
    private String mTextToSpeak;


    private int mThemeColor = StaticDrawable.getRandomHSVColorBySaturation(0.9f);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);

        // Set the context and activity
        mContext = getApplicationContext();
        mActivity = AlphabetActivity.this;

        tts = new TextToSpeech(mContext,this);

        // Set the action bar title
        getSupportActionBar().setTitle("Alphabet");

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

        // Get the widgets reference from XML layout
        mRelativeLayout = (RelativeLayout) findViewById(R.id.rl);
        mGridView = (GridView) findViewById(R.id.gv);


        // Set the widgets background
        mRelativeLayout.setBackground(StaticDrawable.RLDrawable());
        mGridView.setBackgroundColor(Color.parseColor("#25FFFFFF"));

        // Set GridView adapter
        mGridView.setAdapter(new AlphabetBaseAdapter(getApplicationContext()));

        // Define the GridView number of columns
        setGridViewNumberOfColumns(mGridView);

        // Set click listener for GridView items
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(mActivity, AlphabetDetailsActivity.class);
                intent.putExtra("position", i);
                //startActivity(intent);

                // Speak out the text
                if (mSoundIsOn) { // If the sound is enabled
                    mTextToSpeak = (String)adapterView.getAdapter().getItem(i);
                    speakNow(mTextToSpeak);
                }

            }
        });

    }

    // Custom method to calculate GridView number of columns
    private void setGridViewNumberOfColumns(GridView gv){
        //Set the GridView number of columns
        int num_columns = Math.round(getScreenWidthInDPs()/mGridView_Column_Width);
        if(num_columns <=2){
            gv.setNumColumns(GridView.AUTO_FIT);
        }
        else {
            gv.setNumColumns(num_columns);
        }
    }

    // Custom method to get the screen width in pixels
    private int getScreenWidthInDPs(){
        WindowManager wm = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        //Display dimensions in pixels
        display.getSize(size);
        int width = size.x;
        //int height = size.y;
        return getDPsFromPixels(width);
    }

    // Method for converting pixels value to dps
    private int getDPsFromPixels(int pixels){
        Resources r = mContext.getResources();
        int  dps = Math.round(pixels/(r.getDisplayMetrics().densityDpi/160f));
        return dps;
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
