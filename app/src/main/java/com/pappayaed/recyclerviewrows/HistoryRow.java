package com.pappayaed.recyclerviewrows;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.TableLayout;
import android.widget.TextView;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.RecyclerViewRow;
import com.pappayaed.common.Utils;
import com.pappayaed.data.model.FeesStructureIdO2m;
import com.pappayaed.data.model.HeadwisePaymentHistoryO2m;

/**
 * Created by yasar on 23/4/18.
 */

public class HistoryRow extends TableLayout implements RecyclerViewRow<HeadwisePaymentHistoryO2m> {


    private TextView mTermName;
    private TextView mPayAmount;
    private TextView mPaidAmount;
    private TextView mBalanceAmount;
    private TextView mPaidDate;
    private TextView mPaymentMode;
    private TextView mDescription;
    private TextView mDescription_title;
    private TextView mStatus;
    private TextView mStatus_title;

    public HistoryRow(Context context) {
        super(context);


    }

    public HistoryRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();

    }

    @Override
    public void showData(HeadwisePaymentHistoryO2m item) {


        String des = item.getDescription() != null && item.getDescription().toString().equalsIgnoreCase("false") ? "" : item.getDescription().toString();

        mTermName.setText(item.getFeesId());
        mPaidAmount.setText(Utils.getDecimal(item.getPaidAmount()));
        mPayAmount.setText(Utils.getDecimal(item.getPayAmount()));
        mBalanceAmount.setText(Utils.getDecimal(item.getBalanceAmount()));
        mPaidDate.setText(item.getPaidDate());

        if (item.getPaymentMode().toLowerCase().equalsIgnoreCase("bank")) {

            mPaymentMode.setText("Cheque/DD");

        } else if (item.getPaymentMode().toLowerCase().equalsIgnoreCase("neft")) {

            mPaymentMode.setText("NEFT");

        } else {

            mPaymentMode.setText(item.getPaymentMode());

        }


        mDescription.setText(des);

        if (des.length() > 0) {
            mDescription_title.setVisibility(VISIBLE);
            mDescription.setVisibility(VISIBLE);
        } else {
            mDescription_title.setVisibility(GONE);
            mDescription.setVisibility(GONE);
        }

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

    }

    @Override
    public void setOnItemLongClickListener(OnLongClickListener listener) {

    }


    private void initView() {
        mTermName = (TextView) findViewById(R.id.term_name);
        mPayAmount = (TextView) findViewById(R.id.pay_amount);
        mPaidAmount = (TextView) findViewById(R.id.paid_amount);
        mBalanceAmount = (TextView) findViewById(R.id.balance_amount);
        mPaidDate = (TextView) findViewById(R.id.paid_date);
        mPaymentMode = (TextView) findViewById(R.id.payment_mode);
        mDescription = (TextView) findViewById(R.id.description);
        mDescription_title = (TextView) findViewById(R.id.description_title);
        mStatus = (TextView) findViewById(R.id.status);
        mStatus_title = (TextView) findViewById(R.id.status_title);
    }
}
