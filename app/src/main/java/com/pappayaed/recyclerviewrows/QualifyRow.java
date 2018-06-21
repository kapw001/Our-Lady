package com.pappayaed.recyclerviewrows;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.RecyclerViewRow;
import com.pappayaed.ui.studentprofile.Qualify;

/**
 * Created by yasar on 23/4/18.
 */

public class QualifyRow extends RelativeLayout implements RecyclerViewRow<Qualify> {


    private TextView mDegree;
    private TextView mYearOfCompletion;
    private TextView mInstituteOfStudy;
    private TextView mUniversity;

    public QualifyRow(Context context) {
        super(context);

    }

    public QualifyRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

//        inflate(getContext(), R.layout.qualify_row, this);

        initView();


    }

    @Override
    public void showData(Qualify item) {


        mDegree.setText(item.getDegree());

        mYearOfCompletion.setText(item.getYear_of_Completion());

        mInstituteOfStudy.setText(item.getInstitute_of_Study());

        mUniversity.setText(item.getUniversity());


    }

    @Override
    public void setOnItemClickListener(OnClickListener listener) {

    }

    @Override
    public void setOnItemLongClickListener(OnLongClickListener listener) {

    }

    private void initView() {


        mDegree = (TextView) findViewById(R.id.Degree);
        mYearOfCompletion = (TextView) findViewById(R.id.Year_of_Completion);
        mInstituteOfStudy = (TextView) findViewById(R.id.Institute_of_Study);
        mUniversity = (TextView) findViewById(R.id.University);
    }
}
