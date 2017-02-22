package com.cfsuman.me.kidoni;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class AdditionActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private Context mContext;
    private Activity mActivity;

    private Random mRandom = new Random();

    private RelativeLayout mRootLayout;
    private TextView mTextViewQuestion;
    private Button mButtonStart;

    private TextView mTVAnser1;
    private TextView mTVAnser2;
    private TextView mTVAnser3;
    private TextView mTVAnser4;

    private int[] mAnswersArray;
    private int mTotalQuestions;
    private int mRightAnswer;
    private int mRightAnswerPosition;

    //private int mRightAnswerColor = Color.parseColor("#FF1B9367");
    private int mRightAnswerColor = Color.BLACK;
    private int mWrongAnswerColor = Color.BLACK;

    private int mPresentScore;

    private GradientDrawableManager mGradientDrawableManager;
    private GradientManager mGradientManager;
    private Point mSize = new Point();


    private CardView mCVNext;


    private TextToSpeech tts;
    private boolean mSoundIsOn;
    private String mTextToSpeak;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Set an action bar for the activity
        //requestWindowFeature(Window.FEATURE_ACTION_BAR);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        // Set the action bar title
        getSupportActionBar().setTitle("Addition");

        // Get the application context
        mContext = getApplicationContext();
        mActivity = AdditionActivity.this;
        tts = new TextToSpeech(mContext,this);

        // Set the point x y values
        mSize.x = ScreenManager.getScreenWidthInPixels(mContext);
        mSize.y = ScreenManager.getScreenHeightInPixels(mContext);

        // Initialize a new instance of GradientManager class
        mGradientManager = new GradientManager(mContext,mSize);
        mGradientDrawableManager = new GradientDrawableManager(mContext,mSize);

        // Get the widget reference from XML layout
        mRootLayout = (RelativeLayout) findViewById(R.id.rl_root);
        mTextViewQuestion = (TextView) findViewById(R.id.tv_question);
        mButtonStart = (Button) findViewById(R.id.btn_start);

        mCVNext = (CardView) findViewById(R.id.cv_next);

        mCVNext.setCardBackgroundColor(StaticDrawable.getRandomDarkerHSVColor());

        mTVAnser1 = (TextView) findViewById(R.id.tv_answer_1);
        mTVAnser2 = (TextView) findViewById(R.id.tv_answer_2);
        mTVAnser3 = (TextView) findViewById(R.id.tv_answer_3);
        mTVAnser4 = (TextView) findViewById(R.id.tv_answer_4);

        mTVAnser1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedAnswer = Integer.valueOf(mTVAnser1.getText().toString());
                afterAnswerClicked(selectedAnswer);
            }
        });
        mTVAnser2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedAnswer = Integer.valueOf(mTVAnser2.getText().toString());
                afterAnswerClicked(selectedAnswer);
            }
        });
        mTVAnser3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedAnswer = Integer.valueOf(mTVAnser3.getText().toString());
                afterAnswerClicked(selectedAnswer);
            }
        });
        mTVAnser4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedAnswer = Integer.valueOf(mTVAnser4.getText().toString());
                afterAnswerClicked(selectedAnswer);
            }
        });


        // Chan4e the status bar colo3
        //Scree4Manager.changeStatusB4rColor(mActivity, StaticDrawable.getRandomDarkerHSVColor());
        ScreenManager.changeStatusBarColor(mActivity, Color.parseColor("#FF008DB9"));

        // Set a click listener for start button
        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] questionArray = QuestionManager.generateAdditionQuestion(20,10);

                mTextViewQuestion.setTextColor(Color.BLACK);
                mCVNext.setVisibility(View.INVISIBLE);

                int rightAnswer = questionArray[6];
                mRightAnswer = rightAnswer;

                //mTextViewQuestion.setText("Add the two numbers\n");
                mTextViewQuestion.setText("");
                mTextViewQuestion.setText(mTextViewQuestion.getText() + "" +
                                questionArray[4] + "\n+  "
                                + questionArray[5] // + " \n---------------"
                );

                mTVAnser1.setText(""+questionArray[0]);
                mTVAnser2.setText(""+questionArray[1]);
                mTVAnser3.setText(""+questionArray[2]);
                mTVAnser4.setText(""+questionArray[3]);

                mTextToSpeak = questionArray[4]+"+"+questionArray[5];
                speakNow(mTextToSpeak);

                //mRightAnswerPosition = GetRightAnswerPosition();
                mButtonStart.setEnabled(false);

                // Set the root layout background
                //mRootLayout.setBackground(mGradientDrawableManager.getSweepGradientDrawable());
            }
        });

        // Start the exam
        mButtonStart.performClick();
        mButtonStart.setEnabled(false);
        //mCVNext.setVisibility(View.VISIBLE);

    }

    protected void afterAnswerClicked(int selectedAnswer){
        if(selectedAnswer == mRightAnswer){
            Toast.makeText(mContext,"Right",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(mContext,"Wrong",Toast.LENGTH_SHORT).show();
        }
        mButtonStart.setEnabled(true);
        mCVNext.setVisibility(View.VISIBLE);
    }

    protected void performBGAnimation(View v, int bgColor){
        ColorDrawable[] color = {new ColorDrawable(Color.parseColor("#c5e8ff")), new ColorDrawable(bgColor)};
        TransitionDrawable trans = new TransitionDrawable(color);
        v.setBackground(trans);
        trans.startTransition(3000); // duration 3 seconds
    }

    @Override
    public void onResume(){
        super.onResume();
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

    // Custom method to speak the text
    private void speakNow(String textToSpeak) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            tts.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null,null);
        }else {
            tts.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }
}
