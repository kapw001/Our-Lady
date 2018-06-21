package com.pappayaed.recyclerviewrows;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.RecyclerViewRow;
import com.pappayaed.ui.showprofile.UserDetails;
import com.pappayaed.ui.studentprofile.StudentDetails;

/**
 * Created by yasar on 10/4/18.
 */

public class CustomStudentProfileRow extends RelativeLayout implements RecyclerViewRow<StudentDetails> {


    private ImageView mImg;
    private TextView mTitle;
    private TextView mTitleValue;

    public CustomStudentProfileRow(Context context) {
        super(context);
        init(context, null);
    }

    public CustomStudentProfileRow(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomStudentProfileRow(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomStudentProfileRow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTitle = (TextView) findViewById(R.id.title);
        mTitleValue = (TextView) findViewById(R.id.title_value);
        mImg = (ImageView) findViewById(R.id.img);

    }

    private void init(Context context, AttributeSet attrs) {


//        LayoutInflater inflater = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.custom_profile_row, this, true);
////        View view = inflate(context, R.layout.custom_profile_row, this);
//
//        ButterKnife.bind(this);
//
//        TypedArray a = context.obtainStyledAttributes(attrs,
//                R.styleable.CustomProfileRow, 0, 0);
//        String titleText = a.getString(R.styleable.CustomProfileRow_titleText);
//        String title = a.getString(R.styleable.CustomProfileRow_title);
//        int img = a.getResourceId(R.styleable.CustomProfileRow_img, R.drawable.ic_person_outline_black_24dp);
//        boolean isBold = a.getBoolean(R.styleable.CustomProfileRow_isbold, false);
//        a.recycle();
//
//        setTextValues(titleText);
//        setTitle(title);
//        setImg(img);
//        setBold(isBold);

    }

    private void setBold(boolean isBold) {
        if (isBold) {
//            mTitleValue.setTypeface(Typeface.DEFAULT_BOLD);
            mTitleValue.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        }
    }

    public void setTitle(String values) {
        mTitle.setText(values);
    }

    public void setTextValuesStyles() {
        mTitleValue.setTypeface(Typeface.DEFAULT_BOLD);
    }

    public void setTextValues(String values) {
        mTitleValue.setText(values);
    }

    public void setImg(int img) {

        mImg.setImageResource(img);

    }


    @Override
    public void showData(StudentDetails item) {

//        if (item.getmTitle().toLowerCase().equalsIgnoreCase("name")) {
//            setBold(true);
//        }

        if (item.isBold()) setBold(item.isBold());

        setTitle(item.getmTitle() + "");
        setTextValues(item.getmTitleValue() + "");
        setImg(item.getImgID());


    }

    @Override
    public void setOnItemClickListener(OnClickListener listener) {


    }

    @Override
    public void setOnItemLongClickListener(OnLongClickListener listener) {

    }
}
