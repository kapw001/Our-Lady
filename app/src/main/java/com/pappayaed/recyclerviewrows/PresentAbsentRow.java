package com.pappayaed.recyclerviewrows;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.RecyclerViewRow;
import com.pappayaed.data.model.AttendanceLine;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yasar on 23/4/18.
 */

public class PresentAbsentRow extends RelativeLayout implements RecyclerViewRow<AttendanceLine> {


    private CircleImageView mCircleImageView;
    private TextView mStudentName;
    private LinearLayout mPresentabsent;
    private View mPresent;
    private View mAbsent;

    public PresentAbsentRow(Context context) {
        super(context);

    }

    public PresentAbsentRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

//        inflate(getContext(), R.layout.presentabsent_row, this);

        initView();


    }

    @Override
    public void showData(AttendanceLine item) {

        mStudentName.setText(item.getStudentFullName());


//        GradientDrawable bgShape = (GradientDrawable) mPresent.getBackground();
//        bgShape.setColor(Color.WHITE);
//        bgShape.setStroke(2, Color.BLACK);

//        GradientDrawable bgShape1 = (GradientDrawable) mAbsent.getBackground();
//        bgShape1.setColor(Color.WHITE);
//        bgShape1.setStroke(2, Color.BLACK);

        if (item.getStatus().toLowerCase().equalsIgnoreCase("present".toLowerCase())) {

//            bgShape.setColor(Color.GREEN);
//            bgShape.setStroke(0, Color.TRANSPARENT);

//            bgShape1.setColor(Color.WHITE);
//            bgShape1.setStroke(2, Color.BLACK);


            customView(mAbsent, Color.TRANSPARENT, ContextCompat.getColor(getContext(), R.color.line6), 2);
            customView(mPresent, ContextCompat.getColor(getContext(), R.color.endcolor), Color.BLACK, 0);

        } else {

//            bgShape1.setColor(Color.RED);
//            bgShape1.setStroke(0, Color.WHITE);

//            bgShape.setColor(Color.TRANSPARENT);
//            bgShape.setStroke(2, Color.BLACK);
            customView(mAbsent, ContextCompat.getColor(getContext(), R.color.pink), Color.BLACK, 0);
            customView(mPresent, Color.TRANSPARENT, ContextCompat.getColor(getContext(), R.color.line6), 2);
        }


    }

    public static void customView(View v, int backgroundColor, int borderColor, int stroke) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.OVAL);
        shape.setCornerRadii(new float[]{8, 8, 8, 8, 0, 0, 0, 0});
        shape.setColor(backgroundColor);
        shape.setStroke(stroke, borderColor);
        v.setBackground(shape);
    }

    @Override
    public void setOnItemClickListener(OnClickListener listener) {

        mPresent.setOnClickListener(listener);
        mAbsent.setOnClickListener(listener);

    }

    @Override
    public void setOnItemLongClickListener(OnLongClickListener listener) {

    }

    private void initView() {


        mCircleImageView = (CircleImageView) findViewById(R.id.circleImageView);
        mStudentName = (TextView) findViewById(R.id.studentName);

        mPresentabsent = (LinearLayout) findViewById(R.id.presentabsent);
        mPresent = (View) findViewById(R.id.present);
        mAbsent = (View) findViewById(R.id.absent);
    }
}
