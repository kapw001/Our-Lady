package com.pappayaed.ui.showfeedetails;


import com.pappayaed.base.BaseView;
import com.pappayaed.data.model.ResultResponse;
import com.pappayaed.data.model.TermFeesCollectionList;

import java.util.Map;

/**
 * Created by yasar on 27/3/18.
 */

public interface IFeeDetailsView extends BaseView {

    void pageUpdate(ResultResponse resultResponse);

    void loadData(Map<String, Object> response);

    void setAcademicYear(String academicYear);

    void showFeeFullDeatils(TermFeesCollectionList termFeesCollectionList);

}
