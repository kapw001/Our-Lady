package com.pappayaed.recyclerviewrows;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.widget.TableLayout;
import android.widget.TextView;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.RecyclerViewRow;
import com.pappayaed.common.Utils;
import com.pappayaed.data.model.FeesStructureIdO2m;

/**
 * Created by yasar on 23/4/18.
 */

public class FeesRow extends TableLayout implements RecyclerViewRow<FeesStructureIdO2m> {


    private TextView mTermName;
    private TextView mActualAmount;
    private TextView mConsessionAmount;
    private TextView mPayAmount;
    private TextView mDescription;
    private TextView mDescription_title;

    public FeesRow(Context context) {
        super(context);

    }

    public FeesRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    @Override
    public void showData(FeesStructureIdO2m item) {

        mTermName.setText(item.getFeesName());
        mActualAmount.setText(Utils.getDecimal(item.getActualAmount()));
        mConsessionAmount.setText(Utils.getDecimal(item.getConcessionAmount()));
        mPayAmount.setText(Utils.getDecimal(item.getAmount()));

        String des = item.getDescription() != null && item.getDescription().toString().equalsIgnoreCase("false") ? "" : item.getDescription().toString();
        mDescription.setText(des);
        if (des.length() > 0) {
            mDescription_title.setVisibility(VISIBLE);
            mDescription.setVisibility(VISIBLE);
        } else {
            mDescription_title.setVisibility(GONE);
            mDescription.setVisibility(GONE);
        }

    }

    @Override
    public void setOnItemClickListener(OnClickListener listener) {

    }

    @Override
    public void setOnItemLongClickListener(OnLongClickListener listener) {

    }

    private void initView() {
        mTermName = (TextView) findViewById(R.id.term_name);
        mActualAmount = (TextView) findViewById(R.id.actual_amount);
        mConsessionAmount = (TextView) findViewById(R.id.consession_amount);
        mPayAmount = (TextView) findViewById(R.id.pay_amount);
        mDescription = (TextView) findViewById(R.id.description);
        mDescription_title = (TextView) findViewById(R.id.description_title);
    }
}
