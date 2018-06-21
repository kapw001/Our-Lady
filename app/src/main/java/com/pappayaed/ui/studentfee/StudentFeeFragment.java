package com.pappayaed.ui.studentfee;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pappayaed.R;
import com.pappayaed.adapter.StudentFeeAdapter;
import com.pappayaed.base.BaseFragment;
import com.pappayaed.interfaces.CallBackStudentID;
import com.pappayaed.data.helper.L;
import com.pappayaed.ui.showprofile.StudentList;

import java.util.Collections;
import java.util.List;

/**
 * Created by yasar on 2/5/17.
 */

public class StudentFeeFragment extends BaseFragment implements StudentFeeAdapter.RecyclerAdapterPositionClicked, IStudentFeeView {

    private RecyclerView recyclerView;
    private StudentFeeAdapter leaveListAdapter;
    private FloatingActionButton floatingActionButton;

    private static final String TAG = "LeaveFragment";
    private TextView error;

    private IStudentFeePresenter iStudentFeePresenter;


    private CallBackStudentID callBackStudentID;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        callBackStudentID = (CallBackStudentID) context;

    }

    public static StudentFeeFragment getInstance() {
        StudentFeeFragment studentFeeFragment = new StudentFeeFragment();
        Bundle bundle = new Bundle();
        studentFeeFragment.setArguments(bundle);
        return studentFeeFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.leave_fragment, container, false);


        error = (TextView) view.findViewById(R.id.error);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        leaveListAdapter = new StudentFeeAdapter(this, Collections.EMPTY_LIST, recyclerView);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), OrientationHelper.HORIZONTAL, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(linearLayoutManager);

//        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), R.drawable.divider));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(leaveListAdapter);
        error.setVisibility(View.GONE);

        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);


        IStudentIntractor iStudentIntractor = new StudentIntractorImpl(dataSource);

        if (iStudentFeePresenter == null) {
            iStudentFeePresenter = new StudentFeePresenterImp(iStudentIntractor);
            iStudentFeePresenter.onAttach(this);
        }
//        call();

        return view;
    }

    public void call() {

        if (iStudentFeePresenter != null)
            iStudentFeePresenter.getStudentProfile();
        else
            L.loge("Presenter not initialized .............................................");

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (leaveListAdapter != null) {
            leaveListAdapter.saveStates(outState);
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (leaveListAdapter != null) {
            leaveListAdapter.restoreStates(savedInstanceState);
        }

    }


    @Override
    public void position(int pos, View view, StudentList studentList) {

        callBackStudentID.loadStudentID(studentList);

    }

    @Override
    public void onRefresh() {

        call();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setData(List<StudentList> studentLists) {


        leaveListAdapter.updateList(studentLists);
        recyclerView.setVisibility(View.VISIBLE);
        error.setVisibility(View.GONE);

        if (studentLists.size() > 0) {
            callBackStudentID.loadStudentID(studentLists.get(0));
        }

    }

    @Override
    public void emptyData() {

        callBackStudentID.onFail(new Exception("Empty"));

        error.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        error.setText("There is no data...");
//
//
//        Log.e(TAG, "emptyData: ");
    }

    @Override
    public void setError(String msg) {

        callBackStudentID.onFail(new Exception(msg));


        error.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        error.setText(msg);

        Log.e(TAG, "setError: " + msg);
    }


    @Override
    public void showProgress() {
        callBackStudentID.showLoading();
    }

    @Override
    public void hideProgress() {
//        Utils.hideProgress();
        callBackStudentID.hideLoading();
    }

    @Override
    public void onNetworkFailure() {
        callBackStudentID.onNetworkFailure();
    }

    @Override
    public void onFail(Throwable throwable) {
//        super.onFail(throwable);
        callBackStudentID.onFail(throwable);
    }
}
