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
    private TextView mTVQuestion;
    private Button mButtonStart;
    private GridLayout mGridLayout;
    private LinearLayout mLLFirstRow;
    private LinearLayout mLLSecondRow;

    private TextView mTVAnswer1;
    private TextView mTVAnswer2;
    private TextView mTVAnswer3;
    private TextView mTVAnswer4;
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
        mTVQuestion = (TextView) findViewById(R.id.tv_question);
        mButtonStart = (Button) findViewById(R.id.btn_start);
        mGridLayout = (GridLayout) findViewById(R.id.gl);
        mLLFirstRow = (LinearLayout) findViewById(R.id.ll_first_row);
        mLLSecondRow = (LinearLayout) findViewById(R.id.ll_second_row);

        mTVAnswer1 = (TextView) findViewById(R.id.tv_answer_1);
        mTVAnswer2 = (TextView) findViewById(R.id.tv_answer_2);
        mTVAnswer3 = (TextView) findViewById(R.id.tv_answer_3);
        mTVAnswer4 = (TextView) findViewById(R.id.tv_answer_4);

        mTVAnswerArray= new View[]{mTVAnswer1,mTVAnswer2,mTVAnswer3,mTVAnswer4};

        mTVAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                afterAnswerClicked(mTVAnswer1);
            }
        });
        mTVAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                afterAnswerClicked(mTVAnswer2);
            }
        });
        mTVAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                afterAnswerClicked(mTVAnswer3);
            }
        });
        mTVAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                afterAnswerClicked(mTVAnswer4);
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

                mTVQuestion.setTextColor(Color.BLACK);
                mButtonStart.setVisibility(View.INVISIBLE);

                int rightAnswer = question.getResult();
                mRightAnswer = rightAnswer;

                //mTextViewQuestion.setText("Add the two numbers\n");
                mTVQuestion.setText("");
                mTVQuestion.setText(mTVQuestion.getText() + "" +
                                question.getNum1() + "\n+  "
                                + question.getNum2() // + " \n---------------"
                );

                mTVAnswer1.setText(""+question.getA());
                mTVAnswer2.setText(""+question.getB());
                mTVAnswer3.setText(""+question.getC());
                mTVAnswer4.setText(""+question.getD());

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

        int a = Integer.valueOf(mTVAnswer1.getText().toString());
        int b = Integer.valueOf(mTVAnswer2.getText().toString());
        int c = Integer.valueOf(mTVAnswer3.getText().toString());
        int d = Integer.valueOf(mTVAnswer4.getText().toString());

        if(selectedAnswer == mRightAnswer){
            speakNow("Yes");
            GenerateTransition.backgroundPositiveColorTransition(v);
        }else {
            GenerateTransition.backgroundNegativeColorTransition(v);
            if(a==mRightAnswer){
                GenerateTransition.backgroundPositiveColorTransition(mTVAnswer1);
            }else if(b==mRightAnswer){
                GenerateTransition.backgroundPositiveColorTransition(mTVAnswer2);
            }else if(c==mRightAnswer){
                GenerateTransition.backgroundPositiveColorTransition(mTVAnswer3);
            }else if(d==mRightAnswer){
                GenerateTransition.backgroundPositiveColorTransition(mTVAnswer4);
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
