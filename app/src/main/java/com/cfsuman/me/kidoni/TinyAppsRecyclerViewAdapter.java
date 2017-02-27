package com.cfsuman.me.kidoni;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.common.util.UriUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;


public class TinyAppsRecyclerViewAdapter extends RecyclerView.Adapter<TinyAppsRecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private Activity mActivity;
    private List<TinyApp> mDataSet;
    private int mCounter = 1;

    public TinyAppsRecyclerViewAdapter(Context context, Activity activity, List<TinyApp> list){
        mContext = context;
        mActivity = activity;
        mDataSet = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CardView mCardView;
        public TextView mTextViewTitle;
        public SimpleDraweeView mSimpleDraweeView;

        public ViewHolder(View v){
            super(v);
            // Get the widget reference from the custom layout
            mCardView = (CardView) v.findViewById(R.id.card_view);
            mTextViewTitle = (TextView) v.findViewById(R.id.tv_label);
            mSimpleDraweeView = (SimpleDraweeView) v.findViewById(R.id.iv);
        }
    }

    @Override
    public TinyAppsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        final View v = LayoutInflater.from(mContext).inflate(R.layout.tinyapps_custom_view,parent,false);
        final CardView card = (CardView) v.findViewById(R.id.card_view);

        // Get the TextView reference from RecyclerView current item
        final TextView tv_title = (TextView) v.findViewById(R.id.tv_label);

        // Initialize two pairs instance
        final Pair<View,String> pair1 = Pair.create((View)tv_title,"horseImageTransition");
        //final Pair<View,String> pair2 = Pair.create((View)v,"imageTitleTransition");

        // Set a click listener for the current item of RecyclerView
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the RecyclerView current item serial and text
                final String title = tv_title.getText().toString();

                // Display the RecyclerView clicked item serial and label
                //Toast.makeText(mContext,"Clicked : " + title,Toast.LENGTH_SHORT).show();
                String className = tv_title.getTag().toString();

                try {
                    Intent intent = new Intent(mContext, Class.forName(mContext.getPackageName() +"."+ tv_title.getTag()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                                mActivity, // This activity
                                tv_title, // Shared element
                                className
                        );
                        // Start the second activity
                        //mContext.startActivity(intent, options.toBundle());
                    //}else {
                        mContext.startActivity(intent);
                    //}

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        ViewHolder vh = new ViewHolder(v);

        // Return the ViewHolder
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        // Get the current tiny app
        TinyApp tinyApp = mDataSet.get(position);

        // Get the current tiny app data
        String title = tinyApp.getTitle();
        String className = tinyApp.getClassname();
        String imageName = tinyApp.getImage();
        String imageFolder = "tinyapps_images";
        String imagePath = imageFolder +"/"+imageName;

        Uri imageUri = new Uri.Builder()
                .scheme(UriUtil.LOCAL_ASSET_SCHEME) // "res"
                .path(imagePath)
                .build();

        // Set the TextView widgets text
        holder.mTextViewTitle.setText(title);
        holder.mTextViewTitle.setTag(className);
        holder.mSimpleDraweeView.setImageURI(imageUri);
        holder.mTextViewTitle.setTransitionName(className);

        // Increase the counter
        mCounter +=1;
    }

    @Override
    public int getItemCount(){
        // Count the items
        return mDataSet.size();
    }
}