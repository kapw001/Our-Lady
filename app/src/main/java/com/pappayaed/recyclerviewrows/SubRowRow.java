package com.pappayaed.recyclerviewrows;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.RecyclerViewRow;
import com.pappayaed.data.model.HomeworkId;
import com.pappayaed.ui.studentprofile.Sub;

/**
 * Created by yasar on 23/4/18.
 */

public class SubRowRow extends RelativeLayout implements RecyclerViewRow<HomeworkId> {


    private TextView mTitle;
    private TextView mTitleName;
    private ImageView mArrow;
    private SubRowRow lay_root;

    public SubRowRow(Context context) {
        super(context);

    }

    public SubRowRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

//        inflate(getContext(), R.layout.subrow_row, this);

        initView();


    }

    @Override
    public void showData(HomeworkId item) {


        if (item.isTitle()) {

            lay_root.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));

            mArrow.setVisibility(GONE);
            mTitleName.setTextColor(Color.WHITE);
            mTitle.setTextColor(Color.WHITE);
            mTitleName.setText(item.getSubPrimaryTutorName());
            mTitle.setText(item.getSubjectName());
        } else {
            mArrow.setVisibility(VISIBLE);
            mTitleName.setText(item.getSubPrimaryTutorName());
            mTitle.setText(item.getSubjectName());
            mTitle.setTextColor(ContextCompat.getColor(getContext(), R.color.line6));
            mTitleName.setTextColor(ContextCompat.getColor(getContext(), R.color.line6));
            lay_root.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
        }


    }

    @Override
    public void setOnItemClickListener(OnClickListener listener) {

        lay_root.setOnClickListener(listener);

    }

    @Override
    public void setOnItemLongClickListener(OnLongClickListener listener) {

    }

    private void initView() {


        mTitle = (TextView) findViewById(R.id.title);
        mTitleName = (TextView) findViewById(R.id.title_name);
        mArrow = (ImageView) findViewById(R.id.arrow);
        lay_root = (SubRowRow) findViewById(R.id.lay_root);
    }
}
