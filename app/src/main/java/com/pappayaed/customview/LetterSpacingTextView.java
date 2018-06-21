package com.pappayaed.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ScaleXSpan;
import android.util.AttributeSet;
import android.widget.TextView;

import com.google.android.exoplayer2.util.Util;
import com.pappayaed.R;
import com.pappayaed.common.Utils;

/**
 * Created by yasar on 24/4/18.
 */

public class LetterSpacingTextView extends AppCompatTextView {
    private float letterSpacing = LetterSpacing.BIGGEST;
    private CharSequence originalText = "Education";


    public LetterSpacingTextView(Context context) {
        super(context);

        init(context, null);
    }

    public LetterSpacingTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LetterSpacingTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LetterSpacingTextView);
        float space = a.getFloat(R.styleable.LetterSpacingTextView_space, 10);
        String txt = a.getString(R.styleable.LetterSpacingTextView_text);
        setLetterSpacing(space);

        a.recycle();
        setLetterSpacingAndText(space, txt);
        this.invalidate();

    }


    public float getLetterSpacing() {
        return letterSpacing;
    }

    public void setLetterSpacing(float letterSpacing) {
        this.letterSpacing = letterSpacing;
        applyLetterSpacing();
    }

    public void setLetterSpacingAndText(float letterSpacing, String txt) {
        this.letterSpacing = letterSpacing;
        this.originalText = txt;
        applyLetterSpacing();
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        originalText = text;
        applyLetterSpacing();
    }

    @Override
    public CharSequence getText() {
        return originalText;
    }

    private void applyLetterSpacing() {
        if (this == null || this.originalText == null) return;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < originalText.length(); i++) {
            String c = "" + originalText.charAt(i);
            builder.append(c);
//            builder.append(c.toLowerCase());
            if (i + 1 < originalText.length()) {
                builder.append("\u00A0");
            }
        }
        SpannableString finalText = new SpannableString(builder.toString());
        if (builder.toString().length() > 1) {
            for (int i = 1; i < builder.toString().length(); i += 2) {
                finalText.setSpan(new ScaleXSpan((letterSpacing + 1) / 10), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        super.setText(finalText, BufferType.SPANNABLE);
    }

    public class LetterSpacing {
        public final static float NORMAL = 0;
        public final static float NORMALBIG = (float) 0.025;
        public final static float BIG = (float) 0.05;
        public final static float BIGGEST = (float) 0.2;
    }
}