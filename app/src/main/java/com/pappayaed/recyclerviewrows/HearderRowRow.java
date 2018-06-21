package com.pappayaed.recyclerviewrows;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.RecyclerViewRow;
import com.pappayaed.ui.homework.HeaderRowData;
import com.pappayaed.ui.studentprofile.Sub;

/**
 * Created by yasar on 23/4/18.
 */

public class HearderRowRow extends RelativeLayout implements RecyclerViewRow<HeaderRowData> {


    private ImageView mImageIcon;
    private TextView mTitle;
    private TextView mTitleName;

    public HearderRowRow(Context context) {
        super(context);

    }

    public HearderRowRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

//        inflate(getContext(), R.layout.headerrow_row, this);

        initView();


    }

    @Override
    public void showData(HeaderRowData item) {


        mImageIcon.setImageResource(item.getImage());
        mTitle.setText(item.getTitle());
        mTitleName.setText(item.getTitle_name());


    }

    @Override
    public void setOnItemClickListener(OnClickListener listener) {

    }

    @Override
    public void setOnItemLongClickListener(OnLongClickListener listener) {

    }

    private void initView() {

        mImageIcon = (ImageView) findViewById(R.id.image_icon);
        mTitle = (TextView) findViewById(R.id.title);
        mTitleName = (TextView) findViewById(R.id.title_name);
    }
}
