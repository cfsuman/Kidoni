package com.cfsuman.me.kidoni;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class SimpleTextBaseAdapter extends RecyclerView.Adapter<SimpleTextBaseAdapter.ViewHolder> {
    private Context mContext;
    private List<String> mDataSet;

    public SimpleTextBaseAdapter(Context context, List<String> list){
        mContext = context;
        mDataSet = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CardView mCardView;
        public TextView mTextView;

        public ViewHolder(View v){
            super(v);
            // Get the widget reference from the custom layout
            mCardView = (CardView) v.findViewById(R.id.card_view);
            mTextView = (TextView) v.findViewById(R.id.tv);
        }
    }

    @Override
    public SimpleTextBaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(mContext).inflate(R.layout.simple_text_custom_view,parent,false);
        ViewHolder vh = new ViewHolder(v);

        // Return the ViewHolder
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        // Get the current color from the data set
        final String textValue = mDataSet.get(position);
        //final int textValue = Integer.parseInt(color);

        int serial = position+1;

        // Set the TextView widget text
        holder.mTextView.setText(""+ serial+ ". "+textValue);

        // Set the CardVIew background color
        holder.mCardView.setCardBackgroundColor(StaticDrawable.getRandomDarkerHSVColor());

        // Set the TextView text color same as CardView background color
        holder.mTextView.setTextColor(Color.WHITE);

        // Set a click listener for CardView
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initialize a new Intent
                Intent intent = new Intent("BROADCAST_TEXT_VALUE");
                intent.putExtra("TextValue",textValue);

                // Broadcast the selected color value
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
            }
        });
    }

    @Override
    public int getItemCount(){
        // Count the items
        return mDataSet.size();
    }
}