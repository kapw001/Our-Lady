package com.pappayaed.ui.homework;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;

/**
 * Created by yasar on 19/6/18.
 */

public class CCSpinner extends android.support.v7.widget.AppCompatSpinner {
    public CCSpinner(Context context) {
        super(context);
    }

    public CCSpinner(Context context, int mode) {
        super(context, mode);
    }

    public CCSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CCSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CCSpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode) {
        super(context, attrs, defStyleAttr, mode);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}