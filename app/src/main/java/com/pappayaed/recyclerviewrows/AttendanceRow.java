package com.pappayaed.recyclerviewrows;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.RecyclerViewRow;
import com.pappayaed.adapter.AttadanceAdapter;
import com.pappayaed.common.Utils;
import com.pappayaed.data.model.AttendanceList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yasar on 23/4/18.
 */

public class AttendanceRow extends LinearLayout implements RecyclerViewRow<AttendanceList> {


    Unbinder unbinder;
    @BindView(R.id.txtDate)
    TextView txtDate;
    @BindView(R.id.layColor)
    LinearLayout layColor;
    @BindView(R.id.studentname)
    TextView studentname;
    @BindView(R.id.attendance_date)
    TextView attendanceDate;
    @BindView(R.id.remark)
    TextView remark;
    @BindView(R.id.present_morning)
    TextView presentMorning;
    @BindView(R.id.accept)
    Button accept;
    @BindView(R.id.reject)
    Button reject;
    @BindView(R.id.acceptrejectlayout)
    LinearLayout acceptrejectlayout;
    @BindView(R.id.cancel)
    Button cancel;
    @BindView(R.id.cancellayout)
    LinearLayout cancellayout;
    @BindView(R.id.requestlayout)
    RelativeLayout requestlayout;

    public AttendanceRow(Context context) {
        super(context);

    }

    public AttendanceRow(Context context, AttributeSet attrs) {
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
        unbinder.unbind();

    }

    @Override
    public void showData(AttendanceList object) {


        if (object.getRemark() == null || object.getRemark().length() <= 0 || object.getRemark().equalsIgnoreCase("false")) {
            remark.setText("There is no remark");
        } else {
            remark.setText(object.getRemark());
        }

        if (object.getPresentMorning()) {

            presentMorning.setText("Present");
        } else {
            presentMorning.setText("Absent");
        }

        String d = Utils.convertDateGivenFormat(Utils.convertStringToDate1(object.getAttendanceDate()));

        attendanceDate.setText(d);

        txtDate.setText(Utils.getFullNameFromDate(Utils.convertStringToDate1(object.getAttendanceDate())));

        layColor.setBackgroundColor(object.getColor());


    }

    @Override
    public void setOnItemClickListener(OnClickListener listener) {

    }

    @Override
    public void setOnItemLongClickListener(OnLongClickListener listener) {

    }

}
