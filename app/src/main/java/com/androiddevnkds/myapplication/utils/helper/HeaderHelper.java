package com.androiddevnkds.myapplication.utils.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androiddevnkds.myapplication.R;

public class HeaderHelper {

    private static View parentView;

    public static void initialize(View parentView) {
        generateView(parentView);
    }

    public static void initialize(Context context, View parentView) {
        generateView(parentView);
    }

    public static void initialize(Context context, int resId) {
        LayoutInflater inflater = (LayoutInflater) context.getApplicationContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(resId, null);
        generateView(view);
    }

    private static void generateView(View mParentView) {
        parentView = mParentView;



    }

    //Title region
    public static void setLabelVisible(boolean visible) {

    }
}
