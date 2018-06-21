package com.pappayaed.ui.showfeedetails.termfees;

import com.pappayaed.data.model.TermFeesCollectionList;

import java.util.List;

/**
 * Created by yasar on 27/3/18.
 */

public interface ITermView {


    void setData(List<TermFeesCollectionList> list);

    void setEmptyData();

}
