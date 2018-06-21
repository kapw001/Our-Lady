package com.pappayaed.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by yasar on 8/5/17.
 */

public class CustomTextViewBold extends android.support.v7.widget.AppCompatTextView {
    public CustomTextViewBold(Context context) {
        super(context);
        init();
    }

    public CustomTextViewBold(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextViewBold(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/roboto.bold.ttf");
            setTypeface(tf);
        }
    }
}
