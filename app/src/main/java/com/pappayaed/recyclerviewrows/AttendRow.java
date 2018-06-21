package com.pappayaed.recyclerviewrows;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.RecyclerViewRow;
import com.pappayaed.common.Utils;
import com.pappayaed.ui.studentprofile.Attend;

/**
 * Created by yasar on 23/4/18.
 */

public class AttendRow extends RelativeLayout implements RecyclerViewRow<Attend> {


    private TextView mAttendanceDate;
    private TextView mIntime;
    private TextView mOutTime;
    private TextView mWorkours;
    private TextView mStatus;

    public AttendRow(Context context) {
        super(context);

    }

    public AttendRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

//        inflate(getContext(), R.layout.attend_row, this);

        initView();


    }

    @Override
    public void showData(Attend item) {

        mAttendanceDate.setText(item.getAttendance_Date());
        mIntime.setText(item.getIn_time());

        mOutTime.setText(item.getOut_time());
        mWorkours.setText(item.getWorked_Hours());
        mStatus.setText(item.getAttendance_Status());

    }

    @Override
    public void setOnItemClickListener(OnClickListener listener) {

    }

    @Override
    public void setOnItemLongClickListener(OnLongClickListener listener) {

    }

    private void initView() {

        mAttendanceDate = (TextView) findViewById(R.id.Attendance_Date);
        mIntime = (TextView) findViewById(R.id.intime);
        mOutTime = (TextView) findViewById(R.id.out_time);
        mWorkours = (TextView) findViewById(R.id.workours);
        mStatus = (TextView) findViewById(R.id.status);
    }
}
