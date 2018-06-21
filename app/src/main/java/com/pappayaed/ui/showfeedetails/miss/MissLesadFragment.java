package com.pappayaed.ui.showfeedetails.miss;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pappayaed.R;
import com.pappayaed.adapter.FeeAdapter;
import com.pappayaed.data.model.TermFeesCollectionList;

import java.util.ArrayList;

/**
 * Created by yasar on 26/12/17.
 */

public class MissLesadFragment extends Fragment implements FeeAdapter.RecyclerAdapterPositionClicked {
    private static final String TAG = "TermFeeFragment";
    private ArrayList<TermFeesCollectionList> list;
    private RecyclerView recyclerView;
    private TextView error;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private FeeAdapter feeAdapter;

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
        return view;
    }

    @Override
    public void position(int pos, View view) {

    }

    @Override
    public void onRefresh() {

    }
}
