package com.cfsuman.me.kidoni;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class AdditionActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private Context mContext;
    private Activity mActivity;

    private RelativeLayout mRootLayout;
    private TextView mTextViewQuestion;
    private Button mButtonStart;
    private GridLayout mGridLayout;
    private LinearLayout mLLFirstRow;
    private LinearLayout mLLSecondRow;

    private TextView mTVAnser1;
    private TextView mTVAnser2;
    private TextView mTVAnser3;
    private TextView mTVAnser4;
    private View[] mTVAnswerArray;

    private int mRightAnswer;

    private GradientDrawableManager mGradientDrawableManager;
    private GradientManager mGradientManager;
    private Point mSize = new Point();

    private TextToSpeech tts;
    private String mTextToSpeak;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        mGridLayout = (GridLayout) findViewById(R.id.gl);
        mLLFirstRow = (LinearLayout) findViewById(R.id.ll_first_row);
        mLLSecondRow = (LinearLayout) findViewById(R.id.ll_second_row);

        mTVAnser1 = (TextView) findViewById(R.id.tv_answer_1);
        mTVAnser2 = (TextView) findViewById(R.id.tv_answer_2);
        mTVAnser3 = (TextView) findViewById(R.id.tv_answer_3);
        mTVAnser4 = (TextView) findViewById(R.id.tv_answer_4);

        mTVAnswerArray= new View[]{mTVAnser1,mTVAnser2,mTVAnser3,mTVAnser4};

        mTVAnser1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                afterAnswerClicked(mTVAnser1);
            }
        });
        mTVAnser2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                afterAnswerClicked(mTVAnser2);
            }
        });
        mTVAnser3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                afterAnswerClicked(mTVAnser3);
            }
        });
        mTVAnser4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                afterAnswerClicked(mTVAnser4);
            }
        });


        // Chan4e the status bar colo3
        //Scree4Manager.changeStatusB4rColor(mActivity, StaticDrawable.getRandomDarkerHSVColor());
        ScreenManager.changeStatusBarColor(mActivity, Color.parseColor("#FF008DB9"));

        // Set a click listener for start button
        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Question question = QuestionManager.generateAdditionQuestion(20,10);
                GenerateTransition.backgroundInitialColorTransition(mTVAnswerArray);
                ChangeViewProperty.enabledViews(mTVAnswerArray);

                mTextViewQuestion.setTextColor(Color.BLACK);
                mButtonStart.setVisibility(View.INVISIBLE);

                int rightAnswer = question.getResult();
                mRightAnswer = rightAnswer;

                //mTextViewQuestion.setText("Add the two numbers\n");
                mTextViewQuestion.setText("");
                mTextViewQuestion.setText(mTextViewQuestion.getText() + "" +
                                question.getNum1() + "\n+  "
                                + question.getNum2() // + " \n---------------"
                );

                mTVAnser1.setText(""+question.getA());
                mTVAnser2.setText(""+question.getB());
                mTVAnser3.setText(""+question.getC());
                mTVAnser4.setText(""+question.getD());

                mTextToSpeak = question.getNum1()+"+"+question.getNum2();
                speakNow(mTextToSpeak);

                mButtonStart.setEnabled(false);
            }
        });

        // Start the exam
        mButtonStart.performClick();
        mButtonStart.setEnabled(false);
        //mCVNext.setVisibility(View.VISIBLE);

    }
    protected void afterAnswerClicked(TextView v){
        int selectedAnswer = Integer.valueOf(v.getText().toString());

        int a = Integer.valueOf(mTVAnser1.getText().toString());
        int b = Integer.valueOf(mTVAnser2.getText().toString());
        int c = Integer.valueOf(mTVAnser3.getText().toString());
        int d = Integer.valueOf(mTVAnser4.getText().toString());

        if(selectedAnswer == mRightAnswer){
            speakNow("Yes");
            GenerateTransition.backgroundPositiveColorTransition(v);
        }else {
            GenerateTransition.backgroundNegativeColorTransition(v);
            if(a==mRightAnswer){
                GenerateTransition.backgroundPositiveColorTransition(mTVAnser1);
            }else if(b==mRightAnswer){
                GenerateTransition.backgroundPositiveColorTransition(mTVAnser2);
            }else if(c==mRightAnswer){
                GenerateTransition.backgroundPositiveColorTransition(mTVAnser3);
            }else if(d==mRightAnswer){
                GenerateTransition.backgroundPositiveColorTransition(mTVAnser4);
            }
            speakNow("No");
        }

        mButtonStart.setEnabled(true);
        mButtonStart.setVisibility(View.VISIBLE);
        ChangeViewProperty.disabledViews(mTVAnswerArray);
        TransitionManager.beginDelayedTransition(mRootLayout);
        //hideViews(mTVAnser1,mTVAnser2,mTVAnser3,mTVAnser4,mLLFirstRow,mLLSecondRow);
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
