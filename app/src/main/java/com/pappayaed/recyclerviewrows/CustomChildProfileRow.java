package com.pappayaed.recyclerviewrows;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.graphics.Palette;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.RecyclerViewRow;
import com.pappayaed.common.Utils;
import com.pappayaed.ui.showprofile.StudentList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yasar on 10/4/18.
 */

public class CustomChildProfileRow extends LinearLayout implements RecyclerViewRow<StudentList> {


    private CircleImageView mImg;
    private TextView mTitle;

    public CustomChildProfileRow(Context context) {
        super(context);
        init(context, null);
    }

    public CustomChildProfileRow(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomChildProfileRow(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomChildProfileRow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTitle = (TextView) findViewById(R.id.title);
        mImg = (CircleImageView) findViewById(R.id.img);
    }

    private void init(Context context, AttributeSet attrs) {


//
//        LayoutInflater inflater = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.custom_child_profile_row, this, true);
////        View view = inflate(context, R.layout.custom_profile_row, this);

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


    public void setTitle(String values) {
        mTitle.setText(values);
    }


    public void setImg(int img) {

        mImg.setImageResource(img);

    }


    @Override
    public void showData(StudentList item) {

        setTitle(item.getName());
//        setTextValues(item.getTitleValue());
//        setImg(item.getImgID());

        Utils.setBorderColor(mImg);

    }

    @Override
    public void setOnItemClickListener(OnClickListener listener) {

        setOnClickListener(listener);

    }

    @Override
    public void setOnItemLongClickListener(OnLongClickListener listener) {

    }
}
