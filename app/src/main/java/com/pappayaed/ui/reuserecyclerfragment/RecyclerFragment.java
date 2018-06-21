package com.pappayaed.ui.reuserecyclerfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.RecyclerViewAdapterMultiView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecyclerFragment extends Fragment implements IMvpView, RecyclerViewAdapterMultiView.OnRecyclerViewItemClickListener {

    private static final String TAG = "RecyclerFragment";
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    Unbinder unbinder;
    private OnItemCallback mListener;

    private RecyclerViewAdapterMultiView mAdapter;

    public RecyclerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


        try {

            mListener = (OnItemCallback) context;

        } catch (ClassCastException e) {

            Log.e(TAG, "onAttach: " + context.toString() + " must implement in Activity   " + e.getMessage());

            try {
                mListener = (OnItemCallback) getParentFragment();
            } catch (ClassCastException e1) {
//                throw new ClassCastException(context.toString() + " must implement in fragment");
                Log.e(TAG, "onAttach: " + context.toString() + " must implement in fragment   " + e1.getMessage());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = new RecyclerViewAdapterMultiView(Collections.EMPTY_LIST);

        //default recycler layout

        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerview.setHasFixedSize(true);

        recyclerview.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {

        recyclerview.setLayoutManager(layoutManager);

    }

    @Override
    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        recyclerview.addItemDecoration(itemDecoration);
    }

    @Override
    public void showData(List<?> list) {

        mAdapter = new RecyclerViewAdapterMultiView(list);

        recyclerview.setAdapter(mAdapter);

    }

    @Override
    public void updateData(List<?> list) {

        mAdapter.updateData(list);

    }

    @Override
    public void notifyDataChanged() {

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyDataChanged(int position) {

        mAdapter.notifyItemChanged(position);
    }

    @Override
    public void onItemClick(View view, Object o, int position) {
        mListener.onItem(view, o, position);
    }


    public interface OnItemCallback {

        void onItem(View view, Object o, int position);

    }

}
