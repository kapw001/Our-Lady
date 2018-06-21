package com.pappayaed.recyclerviewrows;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.TableLayout;
import android.widget.TextView;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.RecyclerViewRow;
import com.pappayaed.common.Utils;
import com.pappayaed.data.model.MiscellaneousFeesCollectionList;

/**
 * Created by yasar on 23/4/18.
 */

public class Standard_Fees_Row extends TableLayout implements RecyclerViewRow<MiscellaneousFeesCollectionList> {


    private TextView mTermName;
    private TextView mAmount;
    private TextView mActualAmount;
    private TextView mDiscountAmount;
    private TextView mStatus;
    private TextView mStatus_title;

    public Standard_Fees_Row(Context context) {
        super(context);


    }

    public Standard_Fees_Row(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();

    }

    @Override
    public void showData(MiscellaneousFeesCollectionList item) {

        mTermName.setText(item.getFeesName());
        mAmount.setText(Utils.getDecimal(item.getAmount()));
        mActualAmount.setText(Utils.getDecimal(item.getActualAmount()));
        mDiscountAmount.setText(Utils.getDecimal(item.getDiscountAmount()));
        mStatus.setText(item.getStatus());

        if (item.getStatus().toUpperCase().equalsIgnoreCase("paid") || item.getStatus().toLowerCase().equalsIgnoreCase("partially_paid")) {
            mStatus_title.setTextColor(ContextCompat.getColor(getContext(), R.color.green));
            mStatus.setTextColor(ContextCompat.getColor(getContext(), R.color.green));
        } else {
            mStatus_title.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
            mStatus.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
        }

    }

    @Override
    public void setOnItemClickListener(OnClickListener listener) {

        setOnClickListener(listener);

    }

    @Override
    public void setOnItemLongClickListener(OnLongClickListener listener) {

    }


    private void initView() {
        mTermName = (TextView) findViewById(R.id.term_name);
        mAmount = (TextView) findViewById(R.id.amount);
        mActualAmount = (TextView) findViewById(R.id.actual_amount);
        mDiscountAmount = (TextView) findViewById(R.id.discount_amount);
        mStatus = (TextView) findViewById(R.id.status);
        mStatus_title = (TextView) findViewById(R.id.status_title);
    }
}
