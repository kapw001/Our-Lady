package com.pappayaed.ui.reuserecyclerfragment;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by yasar on 27/4/18.
 */

public interface IMvpView {


    void setLayoutManager(RecyclerView.LayoutManager layoutManager);

    void addItemDecoration(RecyclerView.ItemDecoration itemDecoration);

    void showData(List<?> list);

    void updateData(List<?> list);

    void notifyDataChanged();

    void notifyDataChanged(int position);

}
