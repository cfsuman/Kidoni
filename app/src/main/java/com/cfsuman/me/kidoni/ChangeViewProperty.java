package com.cfsuman.me.kidoni;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jones on 2/23/2017.
 */

public class ChangeViewProperty {

    public static void setPositiveTextColor(TextView...views){
        for(TextView v : views){
            v.setTextColor(Color.GREEN);
        }
    }
    public static void setNegativeTextColor(TextView...views){
        for(TextView v : views){
            v.setTextColor(Color.GREEN);
        }
    }
    public static void setPositiveBackgroundColor(View...views){
        for(View v : views){
            v.setBackgroundColor(Color.GREEN);
        }
    }
    public static void setNegativeBackgroundColor(View...views){
        for(View v : views){
            v.setBackgroundColor(Color.RED);
        }
    }
    public static void setInitialBackgroundColor(View...views){
        for(View v : views){
            v.setBackgroundColor(Color.BLUE);
        }
    }

    public static void disabledViews(View...views){
        for(View v : views){
            v.setEnabled(false);
        }
    }
    public static void enabledViews(View...views){
        for(View v : views){
            v.setEnabled(true);
        }
    }
}
