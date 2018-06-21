package com.pappayaed.recyclerviewrows;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.RecyclerViewRow;
import com.pappayaed.common.Utils;
import com.pappayaed.data.model.AttendanceList;
import com.pappayaed.ui.attendance.model.ClassName;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yasar on 23/4/18.
 */

public class ClassNameRow extends RelativeLayout implements RecyclerViewRow<ClassName> {


    Unbinder unbinder;
    @BindView(R.id.name)
    TextView name;


    public ClassNameRow(Context context) {
        super(context);

    }

    public ClassNameRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

//        inflate(getContext(), R.layout.attendance_row, this);

        unbinder = ButterKnife.bind(this);


    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
//        unbinder.unbind();

    }

    @Override
    public void showData(ClassName object) {


        name.setText(object.getName());


    }

    @Override
    public void setOnItemClickListener(OnClickListener listener) {

    }

    @Override
    public void setOnItemLongClickListener(OnLongClickListener listener) {

    }

}
