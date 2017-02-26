package com.cfsuman.me.kidoni;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NumbersBaseAdapter extends BaseAdapter {
    private Context mContext;
    private int mStartingNumber = 1;
    private int mEndingNumber = 50;
    private String[] mDataArray = new String[mEndingNumber];

    NumbersBaseAdapter(Context context){
        mContext = context;
    }

    @Override
    public int getCount(){
        return getDataArray().length;
    }

    @Override
    public Object getItem(int position){
        return getDataArray()[position];
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.viewholder_layout,null);
            holder = new ViewHolder();
            holder.tv = (TextView) convertView.findViewById(R.id.tv);
            holder.pos = position;

            //Typeface font = Typeface.createFromAsset(mContext.getAssets(), "fonts/SEASRN__.ttf");
            //holder.tv.setTypeface(font);

            // Set a gradient background for current element
            convertView.setBackground(StaticDrawable.GVCellDrawable());

            // Get the current item name
            String stringAtPosition = getDataArray()[position];

            if(holder.pos == position){
                holder.tv.setText(stringAtPosition);
                holder.tv.setVisibility(View.VISIBLE);
            }
            convertView.setTag(holder);
        }
        else
        {
            // Do something
            holder = (ViewHolder) convertView.getTag();
            holder.tv.setText(getDataArray()[position]);
            convertView.setTag(holder);
        }
        return convertView;
    }

    // Get the data array
    public String[] getDataArray(){
        /*if(mDataArray.length<1){
            mDataArray = mStringToSplit.split("(?!^)");
        }
        Log.d("Length", mDataArray.length+"");*/

        int indexCounter = 0;
        for (int i=mStartingNumber;i<=mEndingNumber;i++){
            mDataArray[indexCounter]=i+"";
            indexCounter++;
        }

        return mDataArray;
    }

    static class ViewHolder{
        TextView tv;
        int pos;
    }
}
