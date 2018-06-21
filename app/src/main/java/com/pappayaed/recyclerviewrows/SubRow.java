package com.pappayaed.recyclerviewrows;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.RecyclerViewRow;
import com.pappayaed.ui.studentprofile.Sub;

/**
 * Created by yasar on 23/4/18.
 */

public class SubRow extends RelativeLayout implements RecyclerViewRow<Sub> {


    private TextView mAcademicYear;
    private TextView mCategory;
    private TextView mGrade;
    private TextView mSection;
    private TextView mSubject;

    public SubRow(Context context) {
        super(context);

    }

    public SubRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

//        inflate(getContext(), R.layout.sub_row, this);

        initView();


    }

    @Override
    public void showData(Sub item) {


        mAcademicYear.setText(item.getAcademic_Year());
        mCategory.setText(item.getCategory());
        mGrade.setText(item.getGrade());
        mSection.setText(item.getSection());
        mSubject.setText(item.getSubject());

    }

    @Override
    public void setOnItemClickListener(OnClickListener listener) {

    }

    @Override
    public void setOnItemLongClickListener(OnLongClickListener listener) {

    }

    private void initView() {


        mAcademicYear = (TextView) findViewById(R.id.Academic_Year);
        mCategory = (TextView) findViewById(R.id.Category);
        mGrade = (TextView) findViewById(R.id.grade);
        mSection = (TextView) findViewById(R.id.Section);
        mSubject = (TextView) findViewById(R.id.Subject);
    }
}
