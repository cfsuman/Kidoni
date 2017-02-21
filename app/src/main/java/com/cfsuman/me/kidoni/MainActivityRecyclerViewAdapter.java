package com.cfsuman.me.kidoni;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MainActivityRecyclerViewAdapter extends RecyclerView.Adapter<MainActivityRecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private List<TinyApp> mDataSet;
    private int mCounter = 1;

    public MainActivityRecyclerViewAdapter(Context context, List<TinyApp> list){
        mContext = context;
        mDataSet = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CardView mCardView;
        public TextView mTextViewSerial;
        public TextView mTextViewLabel;

        public ViewHolder(View v){
            super(v);
            // Get the widget reference from the custom layout
            mCardView = (CardView) v.findViewById(R.id.card_view);
            mTextViewSerial = (TextView) v.findViewById(R.id.tv_serial);
            mTextViewLabel = (TextView) v.findViewById(R.id.tv_label);
        }
    }

    @Override
    public MainActivityRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        final View v = LayoutInflater.from(mContext).inflate(R.layout.main_activity_recycler_view_custom_view,parent,false);

        // Get the TextView reference from RecyclerView current item
        final TextView tv_serial = (TextView) v.findViewById(R.id.tv_serial);
        final TextView tv_label = (TextView) v.findViewById(R.id.tv_label);

        // Set a click listener for the current item of RecyclerView
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the RecyclerView current item serial and text
                final String serial = tv_serial.getText().toString();
                final String label = tv_label.getText().toString();

                    // Display the RecyclerView clicked item serial and label
                    Toast.makeText(
                            mContext,
                            "Clicked : " + serial + ". " + label,
                            Toast.LENGTH_SHORT
                    ).show();

                    //Class<?> act = Class.forName(label);

                try {
                    Intent intent = new Intent(mContext, Class.forName(mContext.getPackageName() +"."+ label.toString()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
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
        // Get the current animal from the data set
        String animal = mDataSet.get(position).getName();
        String activity = mDataSet.get(position).getActivity();

        // Set the TextView widgets text
        holder.mTextViewSerial.setText(mCounter + "");
        holder.mTextViewLabel.setText(activity);

        // Increase the counter
        mCounter +=1;
    }

    @Override
    public int getItemCount(){
        // Count the items
        return mDataSet.size();
    }
}