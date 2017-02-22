package com.cfsuman.me.kidoni;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.Locale;
import java.util.Random;

public class SubtractionActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private Context mContext;
    private Activity mActivity;

    private Random mRandom = new Random();

    private RelativeLayout mRootLayout;
    //private TextView mTVBanner;
    private TextView mTextViewQuestion;
    //private TextView mTextViewScore;
    //private ImageButton mButtonHome;
    private Button mButtonStart;

    private CheckBox mCheckBox1;
    private CheckBox mCheckBox2;
    private CheckBox mCheckBox3;
    private CheckBox mCheckBox4;

    private int[] mAnswersArray;
    private int mTotalQuestions;
    private int mRightAnswer;
    private int mRightAnswerPosition;

    //private int mRightAnswerColor = Color.parseColor("#FF1B9367");
    private int mRightAnswerColor = Color.BLACK;
    private int mWrongAnswerColor = Color.BLACK;

    private Drawable drawableRightAnswer;
    private Drawable drawableWrongAnswer;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

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
        setContentView(R.layout.activity_subtraction);

        // Set the action bar title
        getSupportActionBar().setTitle("Subtraction");

        // Get the application context
        mContext = getApplicationContext();
        mActivity = SubtractionActivity.this;
        tts = new TextToSpeech(mContext,this);

        // Set the point x y values
        mSize.x = ScreenManager.getScreenWidthInPixels(mContext);
        mSize.y = ScreenManager.getScreenHeightInPixels(mContext);

        // Initialize a new instance of GradientManager class
        mGradientManager = new GradientManager(mContext,mSize);
        mGradientDrawableManager = new GradientDrawableManager(mContext,mSize);

        // Get the widget reference from XML layout
        mRootLayout = (RelativeLayout) findViewById(R.id.rl_root);
        //mTVBanner = (TextView) findViewById(R.id.tv_banner);
        mTextViewQuestion = (TextView) findViewById(R.id.tv_question);
        //mTextViewScore = (TextView) findViewById(R.id.tv_score);
        //mButtonHome = (ImageButton) findViewById(R.id.ib_home);
        mButtonStart = (Button) findViewById(R.id.btn_start);

        mCVNext = (CardView) findViewById(R.id.cv_next);

        //mCVNext.setCardBackgroundColor(StaticDrawable.getRandomDarkerHSVColor());

        mCheckBox1 = (CheckBox) findViewById(R.id.checkbox_1);
        mCheckBox2 = (CheckBox) findViewById(R.id.checkbox_2);
        mCheckBox3 = (CheckBox) findViewById(R.id.checkbox_3);
        mCheckBox4 = (CheckBox) findViewById(R.id.checkbox_4);

        // Set the root layout background
        //mRootLayout.setBackground(mGradientDrawableManager.getSweepGradientDrawable());
        //mRootLayout.setBackgroundColor(Color.parseColor("#86A5E5"));
        //mRootLayout.setBackground(StaticDrawable.RLDrawable());

        // Change the status bar color
        //ScreenManager.changeStatusBarColor(mActivity, StaticDrawable.getRandomDarkerHSVColor());
        ScreenManager.changeStatusBarColor(mActivity, Color.parseColor("#FF008DB9"));

        //-------------- Design the banner
        // Initialize a new Typeface instance
        Typeface typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
        //mTVBanner.setTypeface(typeface);
        //mTVBanner.setTextColor(StaticDrawable.getRandomDarkerHSVColor());
        //mTVBanner.setTextColor(Color.parseColor("#ECF2FF"));

        // Define the blur effect radius
        //float radius = mTVBanner.getTextSize()/10;

        // Initialize a new BlurMaskFilter instance
        //BlurMaskFilter filter = new BlurMaskFilter(radius, BlurMaskFilter.Blur.SOLID);
        // Set the TextView layer type
        //mTVBanner.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        // Finally, apply the blur effect on TextView text
        //mTVBanner.getPaint().setMaskFilter(filter);





        // Set a click listener for start button
        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextViewQuestion.setTextColor(Color.BLACK);
                mCVNext.setVisibility(View.INVISIBLE);

                int num1 = mRandom.nextInt(25);
                int num2 = mRandom.nextInt(11);

                int num3 = num1;
                if(num1 < num2){
                    num1 = num2;
                    num2 = num3;
                }

                int rightAnswer = num1 - num2;
                mRightAnswer = rightAnswer;

                // Random range minimum inclusive and maximum exclusive
                int random1 = mRandom.nextInt(5 - 1) + 1;
                int random2 = mRandom.nextInt(10 - 5) + 5;
                int random3 = mRandom.nextInt(5 - 1) + 1;

                int wrongAnswer1 = rightAnswer + random1;
                int wrongAnswer2 = rightAnswer + random2;
                int wrongAnswer3 = rightAnswer - random3;

                // Initialize a new Array of possible answers
                mAnswersArray = new int[]{wrongAnswer1,wrongAnswer2,wrongAnswer3,rightAnswer};

                // Shuffle the answers
                ShuffleArray(mAnswersArray);

                //mTextViewQuestion.setText("Add the two numbers\n");
                mTextViewQuestion.setText("");
                mTextViewQuestion.setText(mTextViewQuestion.getText() + "" +
                        num1 + "\n-  "
                        + num2 // + " \n---------------"
                );

                mTextToSpeak = num1+" minus "+num2;
                speakNow(mTextToSpeak);

                // Enable the CheckBoxes
                enableDisableCheckBoxes(true);
                // Refresh the CheckBoxes
                refreshCheckBoxes();

                // Set the CheckBoxes text
                setCheckBoxesText(mAnswersArray[0], mAnswersArray[1], mAnswersArray[2], mAnswersArray[3]);

                mRightAnswerPosition = GetRightAnswerPosition();
                mButtonStart.setEnabled(false);

                // Set the root layout background
                //mRootLayout.setBackground(mGradientDrawableManager.getSweepGradientDrawable());
            }


        });


        // Start the exam
        mButtonStart.performClick();
        mButtonStart.setEnabled(false);

    }


    // Custom method get checked item
    public void onCheckBoxClicked(View v){
        mButtonStart.setEnabled(true);
        mCVNext.setVisibility(View.VISIBLE);

        //Is the view (Clicked CheckBox) now checked
        boolean checked = ((CheckBox) v).isChecked();

        enableDisableCheckBoxes(false);

        //So, check which CheckBox was Clicked and generated a Click event
        switch(v.getId()){ //get the id of clicked CheckBox
            case R.id.checkbox_1:
                if (checked){
                    mCheckBox1.setTypeface(mCheckBox1.getTypeface(), Typeface.BOLD_ITALIC);
                    if(mRightAnswer == Integer.parseInt(mCheckBox1.getText().toString())){
                        mTextViewQuestion.setTextColor(mRightAnswerColor);
                        updateUIAfterAnswer(true);
                        performBGAnimation(mCheckBox1, Color.GREEN);
                    }else {
                        mTextViewQuestion.setTextColor(mWrongAnswerColor);
                        updateUIAfterAnswer(false);
                        performBGAnimation(mCheckBox1, Color.RED);
                    }
                }
                break;
            case R.id.checkbox_2:
                if (checked){
                    mCheckBox2.setTypeface(mCheckBox2.getTypeface(), Typeface.BOLD_ITALIC);
                    if(mRightAnswer == Integer.parseInt(mCheckBox2.getText().toString())){
                        mTextViewQuestion.setTextColor(mRightAnswerColor);
                        updateUIAfterAnswer(true);
                        performBGAnimation(mCheckBox2, Color.GREEN);
                    }else {
                        mTextViewQuestion.setTextColor(mWrongAnswerColor);
                        updateUIAfterAnswer(false);
                        performBGAnimation(mCheckBox2, Color.RED);
                    }
                }
                break;
            case R.id.checkbox_3:
                if (checked){
                    mCheckBox3.setTypeface(mCheckBox3.getTypeface(), Typeface.BOLD_ITALIC);
                    if(mRightAnswer == Integer.parseInt(mCheckBox3.getText().toString())){
                        mTextViewQuestion.setTextColor(mRightAnswerColor);
                        updateUIAfterAnswer(true);
                        performBGAnimation(mCheckBox3, Color.GREEN);
                    }else {
                        mTextViewQuestion.setTextColor(mWrongAnswerColor);
                        updateUIAfterAnswer(false);
                        performBGAnimation(mCheckBox3, Color.RED);
                    }
                }
                break;
            case R.id.checkbox_4:
                if (checked){
                    mCheckBox4.setTypeface(mCheckBox4.getTypeface(), Typeface.BOLD_ITALIC);
                    if(mRightAnswer == Integer.parseInt(mCheckBox4.getText().toString())){
                        mTextViewQuestion.setTextColor(mRightAnswerColor);
                        updateUIAfterAnswer(true);
                        performBGAnimation(mCheckBox4, Color.GREEN);
                    }else {
                        mTextViewQuestion.setTextColor(mWrongAnswerColor);
                        updateUIAfterAnswer(false);
                        performBGAnimation(mCheckBox4, Color.RED);
                    }
                }
                break;
        }
    }

    protected void enableDisableCheckBoxes(Boolean state){
        mCheckBox1.setEnabled(state);
        mCheckBox2.setEnabled(state);
        mCheckBox3.setEnabled(state);
        mCheckBox4.setEnabled(state);
    }

    protected void setCheckBoxesText(int a,int b, int c, int d){
        mCheckBox1.setText("" + a);
        mCheckBox2.setText("" + b);
        mCheckBox3.setText("" + c);
        mCheckBox4.setText("" + d);
    }

    protected void refreshCheckBoxes(){
        mCheckBox1.setChecked(false);
        mCheckBox2.setChecked(false);
        mCheckBox3.setChecked(false);
        mCheckBox4.setChecked(false);
        //if CheckBox unchecked, changed its text to normal state
        mCheckBox1.setTypeface(null, Typeface.NORMAL);
        mCheckBox2.setTypeface(null, Typeface.NORMAL);
        mCheckBox3.setTypeface(null, Typeface.NORMAL);
        mCheckBox4.setTypeface(null, Typeface.NORMAL);

        mCheckBox1.setBackgroundColor(Color.parseColor("#c5e8ff"));
        mCheckBox2.setBackgroundColor(Color.parseColor("#c5e8ff"));
        mCheckBox3.setBackgroundColor(Color.parseColor("#c5e8ff"));
        mCheckBox4.setBackgroundColor(Color.parseColor("#c5e8ff"));

        // Set the left drawable image for button widget programmatically
        mTextViewQuestion.setCompoundDrawables(
                null, // Drawable left
                null, // Drawable top
                null, // Drawable right
                null // Drawable bottom
        );
    }

    protected int[] ShuffleArray(int[] array)
    {
        int index, temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
        return array;
    }

    protected int GetRightAnswerPosition(){
        int result = 0;
        if(mAnswersArray[0]== mRightAnswer){
            result = 0;
        }else if((mAnswersArray[1]== mRightAnswer)){
            result = 1;
        }else if((mAnswersArray[2]== mRightAnswer)){
            result = 2;
        }else if((mAnswersArray[3]== mRightAnswer)){
            result = 3;
        }
        return result;
    }

    protected void updateUIAfterAnswer(boolean answer){
        if(answer){
            // Set the left drawable image for button widget programmatically
            /*mTextViewQuestion.setCompoundDrawables(
                    drawableRightAnswer, // Drawable left
                    null, // Drawable top
                    null, // Drawable right
                    null // Drawable bottom
            );*/
            mTextToSpeak = "Yes";
            speakNow(mTextToSpeak);
        }else{
            // Set the left drawable image for button widget programmatically
            /*mTextViewQuestion.setCompoundDrawables(
                    drawableWrongAnswer, // Drawable left
                    null, // Drawable top
                    null, // Drawable right
                    null // Drawable bottom
            );*/
            animateRightAnswer();
            mTextToSpeak = "No";
            speakNow(mTextToSpeak);
        }
    }

    protected void performBGAnimation(View v, int bgColor){
        ColorDrawable[] color = {new ColorDrawable(Color.parseColor("#c5e8ff")), new ColorDrawable(bgColor)};
        TransitionDrawable trans = new TransitionDrawable(color);
        v.setBackground(trans);
        trans.startTransition(3000); // duration 3 seconds
    }

    protected void animateRightAnswer(){
        if(mRightAnswerPosition ==0){
            performBGAnimation(mCheckBox1, Color.GREEN);
        }else if(mRightAnswerPosition == 1){
            performBGAnimation(mCheckBox2, Color.GREEN);
        }else if (mRightAnswerPosition == 2){
            performBGAnimation(mCheckBox3, Color.GREEN);
        }else if (mRightAnswerPosition == 3){
            performBGAnimation(mCheckBox4, Color.GREEN);
        }
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
