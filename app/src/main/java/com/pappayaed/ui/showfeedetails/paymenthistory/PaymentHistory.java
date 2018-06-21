package com.pappayaed.ui.showfeedetails.paymenthistory;

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
import com.pappayaed.adapter.PaymentHistoryAdapter;
import com.pappayaed.base.BaseFragment;
import com.pappayaed.common.Utils;
import com.pappayaed.errormsg.Error;
import com.pappayaed.data.model.FeesPaymentHistoryList;
import com.pappayaed.data.model.ResultResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by yasar on 26/12/17.
 */

public class PaymentHistory extends BaseFragment implements IPaymentHistoryView, PaymentHistoryAdapter.RecyclerAdapterPositionClicked {

    private static final String TAG = "TermFeeFragment";
    private ArrayList<FeesPaymentHistoryList> list;
    private RecyclerView recyclerView;
    private TextView error;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private PaymentHistoryAdapter feeAdapter;
    private ResultResponse response;

    private IPaymentHistoryPresenter iPaymentHistoryPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.termfee, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        error = (TextView) view.findViewById(R.id.error);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setEnabled(false);
        list = new ArrayList<>();
        feeAdapter = new PaymentHistoryAdapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(feeAdapter);



        iPaymentHistoryPresenter = new PaymentHistoryPresenter(this);
        setList(response);
        return view;
    }

    public void setList(ResultResponse response) {

        this.response = response;
        iPaymentHistoryPresenter.showTermFees(response);

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
    public void setData(List<FeesPaymentHistoryList> list) {
        feeAdapter.updateList(list);
        error.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setEmptyData() {

        list.addAll(Collections.EMPTY_LIST);
        error.setVisibility(View.VISIBLE);
        error.setText(Error.no_data_payment_history);
        recyclerView.setVisibility(View.GONE);
    }
}

