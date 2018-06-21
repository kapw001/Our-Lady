package com.pappayaed.ui.showfeedetails.paymenthistory;

import com.pappayaed.data.model.FeesPaymentHistoryList;

import java.util.List;

/**
 * Created by yasar on 27/3/18.
 */

public interface IPaymentHistoryView {


    void setData(List<FeesPaymentHistoryList> list);

    void setEmptyData();

}
