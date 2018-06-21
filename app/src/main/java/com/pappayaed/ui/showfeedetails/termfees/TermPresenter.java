package com.pappayaed.ui.showfeedetails.termfees;

import android.util.Log;

import com.pappayaed.data.model.ResultResponse;
import com.pappayaed.data.model.TermFeesCollectionList;

import java.util.List;

/**
 * Created by yasar on 27/3/18.
 */

public class TermPresenter implements ITermPresenter {

    private static final String TAG = "PaymentHistoryPresenter";

    private ITermView iTermView;

    public TermPresenter(ITermView iTermView) {
        this.iTermView = iTermView;
    }


    @Override
    public void showTermFees(ResultResponse response) {

        if (response != null) {

            try {

                if (response.getResult().getTermFeesCollectionList() != null && !response.getResult().getTermFeesCollectionList().isEmpty()) {
                    List<TermFeesCollectionList> listv = response.getResult().getTermFeesCollectionList();

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
