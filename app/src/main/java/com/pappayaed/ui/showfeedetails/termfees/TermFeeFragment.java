package com.pappayaed.ui.showfeedetails.termfees;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pappayaed.R;
import com.pappayaed.adapter.FeeAdapter;
import com.pappayaed.base.BaseFragment;
import com.pappayaed.common.Utils;
import com.pappayaed.errormsg.Error;
import com.pappayaed.data.model.ResultResponse;
import com.pappayaed.data.model.TermFeesCollectionList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Response;

/**
 * Created by yasar on 26/12/17.
 */

public class TermFeeFragment extends BaseFragment implements FeeAdapter.RecyclerAdapterPositionClicked, ITermView {

    private static final String TAG = "TermFeeFragment";
    private ArrayList<TermFeesCollectionList> list;
    private RecyclerView recyclerView;
    private TextView error;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private FeeAdapter feeAdapter;

    private ResultResponse response;

    private ITermPresenter iTermPresenter;

    public TermFeeFragment getInstance(Response<ResultResponse> response, boolean isError) {

        TermFeeFragment termFeeFragment = new TermFeeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("list", response.toString());
        bundle.putBoolean("isError", isError);
        termFeeFragment.setArguments(bundle);
        return termFeeFragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.termfee, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        error = (TextView) view.findViewById(R.id.error);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setEnabled(false);
        list = new ArrayList<>();


        feeAdapter = new FeeAdapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(feeAdapter);

        iTermPresenter = new TermPresenter(this);

        setList(response);


        return view;
    }

    public void setList(ResultResponse response) {


        this.response = response;

        iTermPresenter.showTermFees(response);

    }

    public void setError() {
        error.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        Utils.hideProgress();
        error.setText(Error.servererror);
    }

    @Override
    public void position(int pos, View view) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void setData(List<TermFeesCollectionList> list) {
        feeAdapter.updateList(list);
        error.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setEmptyData() {

        list.addAll(Collections.EMPTY_LIST);
        error.setVisibility(View.VISIBLE);
        error.setText(Error.nodata);
        recyclerView.setVisibility(View.GONE);
    }
}
