package com.cfsuman.me.kidoni;

import android.content.Context;
import android.content.Intent;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Point;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.PagerAdapter;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;


// Base class providing the adapter to populate pages inside of a ViewPager.
public class AlphabetPagerAdapter extends PagerAdapter {
    private Context mContext;
    private String mStringToSplit = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";;
    private String[] mDataArray = new String[]{};

    private GradientManager mGradientManager;
    private ScreenManager mScreenManager;
    private Point mScreenSize;

    private Shader mShader;

    AlphabetPagerAdapter(Context context){
        this.mContext = context;
        mScreenManager = new ScreenManager(mContext);
        mScreenSize = mScreenManager.getScreenSizeInPixels();
        mGradientManager = new GradientManager(mContext,mScreenSize);
    }

    // Get the data array
    public String[] getDataArray(){
        if(mDataArray.length<1){
            mDataArray = mStringToSplit.split("(?!^)");
        }
        return mDataArray;
    }

    @Override
    public int getCount(){
        return getDataArray().length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object){
        return view == (RelativeLayout)object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position){
        // Create a new LayoutParams object
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        // Initialize a new RelativeLayout instance
        RelativeLayout rl = new RelativeLayout(mContext);
        rl.setLayoutParams(params);
        //rl.setBackground(StaticDrawable.SweepDrawable());
        rl.setBackground(StaticDrawable.getLayoutDrawable());
        //rl.setPadding(15, 15, 15, 15);
        rl.setPadding(15, 15, 15, 15);

        TextView tv = new TextView(mContext);
        tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        // Place the TextView at the center of layout
        ((LayoutParams)tv.getLayoutParams()).addRule(RelativeLayout.CENTER_IN_PARENT);

        // Set an positive id for TextView
        tv.setId(position+1);
        // Set the TextView layout parameters
        tv.setLayoutParams(params);

        // Assign some properties to TextView widget
        tv.setText(getDataArray()[position] +" " + getDataArray()[position].toLowerCase());
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 150);
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.BLACK);

        // Initialize a new Typeface instance
        //Typeface font = Typeface.createFromAsset(mContext.getAssets(), "fonts/SEASRN__.ttf");
        Typeface font = Typeface.createFromAsset(mContext.getAssets(), "fonts/FFF_Tusj.ttf");
        tv.setTypeface(font);

        // Set the TextView layer type
        tv.setLayerType(View.LAYER_TYPE_SOFTWARE,null);

        // Define the blur effect radius
        float radius = tv.getTextSize()/10;


        EmbossMaskFilter embossFilter = new EmbossMaskFilter(
                new float[]{1f, 5f, 1f}, // direction of the light source
                0.8f, // ambient light between 0 to 1
                8, // specular highlights
                7f // blur before applying lighting
        );

        EmbossMaskFilter debossFilter = new EmbossMaskFilter(
                new float[]{0f, -1f, 0.5f}, // direction of the light source
                0.8f, // ambient light between 0 to 1
                13, // specular highlights
                7.0f // blur before applying lighting
        );

        mShader = mGradientManager.getRandomLinearGradient();
        tv.getPaint().setMaskFilter(embossFilter);
        tv.getPaint().setShader(mShader);

        // Initialize a new BlurMaskFilter instance
        BlurMaskFilter blurFilter = new BlurMaskFilter(radius, BlurMaskFilter.Blur.OUTER);

        // Finally, apply the blur effect on TextView text
        //tv.getPaint().setMaskFilter(blurFilter);

        // Set the TextView layer type
        tv.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        // Set the TextView margins
        MarginLayoutParams mlp = (MarginLayoutParams)tv.getLayoutParams();
        mlp.setMargins(10, 10,10,0);
        rl.addView(tv,0);
        container.addView(rl);

        // Set a click listener for TextView
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("SEND_CLICK_POSITION");
                // Include the click position
                intent.putExtra("position",position);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
            }
        });

        return rl;
    }

    public void destroyItem(ViewGroup container, int position, Object object){
        // Remove the page from ViewPager object
        container.removeView((RelativeLayout) object);
    }
}
