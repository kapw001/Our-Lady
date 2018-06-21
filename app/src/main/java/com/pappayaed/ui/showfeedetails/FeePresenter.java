package com.pappayaed.ui.showfeedetails;

import com.pappayaed.data.model.ResultResponse;
import com.pappayaed.data.model.TermFeesCollectionList;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yasar on 27/3/18.
 */

public class FeePresenter implements IFeeDetailsPresenter, IFeeIntractor.OnFinishedListener {

    private IFeeIntractor iFeeIntractor;
    private IFeeDetailsView iFeeDetailsView;

    private Map<String, Object> parseFeeDetails = new LinkedHashMap<>();
    private ResultResponse resultResponse;
    private String termName;

    public FeePresenter(IFeeIntractor iFeeIntractor, IFeeDetailsView iFeeDetailsView) {
        this.iFeeIntractor = iFeeIntractor;
        this.iFeeDetailsView = iFeeDetailsView;
    }

    @Override
    public void getStudentFeeList(long id) {

        iFeeDetailsView.showLoading();

        iFeeIntractor.getStudentFeeList(id, this);

    }

    @Override
    public void onSuccss(ResultResponse resultResponse) {

        this.resultResponse = resultResponse;
        iFeeDetailsView.hideLoading();

        iFeeDetailsView.pageUpdate(resultResponse);
        parseFeeDetails();
    }

    @Override
    public void onFail(Throwable throwable) {
        iFeeDetailsView.hideLoading();
        iFeeDetailsView.onFail(throwable);
    }

    @Override
    public void onNetworkFailure() {
        iFeeDetailsView.hideLoading();
        iFeeDetailsView.onNetworkFailure();
    }

    public void parseFeeDetails() {

        if (resultResponse.getResult() != null) {

            if (resultResponse.getResult().getTermFeesCollectionList() != null) {

                iFeeDetailsView.setAcademicYear("Academic Year " + resultResponse.getResult().getAcademicYear());

                List<TermFeesCollectionList> list = resultResponse.getResult().getTermFeesCollectionList();

                for (int i = 0; i < list.size(); i++) {

                    TermFeesCollectionList terms = list.get(i);

                    parseFeeDetails.put(terms.getTermName(), terms);

                }

                if (list.size() > 0) {
                    iFeeDetailsView.loadData(parseFeeDetails);

                    setTermName(list.get(0).getTermName());
                } else {

                    iFeeDetailsView.onFail(new Throwable("There is no data"));
                }


            } else {
                iFeeDetailsView.onFail(new Throwable("Something went wrong"));
            }


        } else {
            iFeeDetailsView.onFail(new Throwable("Something went wrong"));
        }


    }

    @Override
    public void getTermName() {


        if (termName != null) {

            TermFeesCollectionList termFeesCollectionList = (TermFeesCollectionList) parseFeeDetails.get(termName);

            iFeeDetailsView.showFeeFullDeatils(termFeesCollectionList);

        } else {
            iFeeDetailsView.showToast("Please select any one of terms");
        }

    }

    @Override
    public void setTermName(String termName) {
        this.termName = termName;
    }
}
