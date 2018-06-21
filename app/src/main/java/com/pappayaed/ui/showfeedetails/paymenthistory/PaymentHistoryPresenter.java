package com.pappayaed.ui.showfeedetails.paymenthistory;

import android.util.Log;

import com.pappayaed.data.model.FeesPaymentHistoryList;
import com.pappayaed.data.model.ResultResponse;

import java.util.List;

/**
 * Created by yasar on 27/3/18.
 */

public class PaymentHistoryPresenter implements IPaymentHistoryPresenter {

    private static final String TAG = "PaymentHistoryPresenter";

    private IPaymentHistoryView iTermView;

    public PaymentHistoryPresenter(IPaymentHistoryView iTermView) {
        this.iTermView = iTermView;
    }


    @Override
    public void showTermFees(ResultResponse response) {

        if (response != null) {

            try {

                if (response.getResult().getFeesPaymentHistoryList() != null && !response.getResult().getFeesPaymentHistoryList().isEmpty()) {
                    List<FeesPaymentHistoryList> listv = response.getResult().getFeesPaymentHistoryList();

                    iTermView.setData(listv);

                } else {
                    Log.e(TAG, "setList:     dfdsafsdfsdfsdfsdfsdfsdfsd ");
                    iTermView.setEmptyData();
                }

            } catch (NullPointerException e) {
                iTermView.setEmptyData();
            }
        } else {

            iTermView.setEmptyData();

        }


    }
}
