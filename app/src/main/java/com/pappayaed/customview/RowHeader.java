package com.pappayaed.customview;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.RecyclerViewRow;
import com.pappayaed.ui.studentprofile.Header;

/**
 * Created by yasar on 11/4/18.
 */

public class RowHeader extends RelativeLayout implements RecyclerViewRow<Header> {

    private TextView title;

    public RowHeader(Context context) {
        super(context);
    }

    public RowHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RowHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RowHeader(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        title = (TextView) findViewById(R.id.title);
    }

    @Override
    public void showData(Header item) {

        title.setText(item.getTitle() + "");

    }

    @Override
    public void setOnItemClickListener(OnClickListener listener) {

    }

    @Override
    public void setOnItemLongClickListener(OnLongClickListener listener) {

    }
}
