package com.pappayaed.ui.feedetails;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.RecyclerViewAdapter;
import com.pappayaed.adapter.TermSelectorAdapter;
import com.pappayaed.base.BaseFragment;
import com.pappayaed.common.ActivityUtils;
import com.pappayaed.common.NullCheckUtils;
import com.pappayaed.common.Utils;
import com.pappayaed.data.model.ResultResponse;
import com.pappayaed.data.model.TermFeesCollectionList;
import com.pappayaed.ui.showfeedetails.FeeIntractorImp;
import com.pappayaed.ui.showfeedetails.FeePresenter;
import com.pappayaed.ui.showfeedetails.IFeeDetailsPresenter;
import com.pappayaed.ui.showfeedetails.IFeeDetailsView;
import com.pappayaed.ui.showprofile.StudentList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class StandardAndFeeFragment extends BaseFragment implements IFeeDetailsView, TermSelectorAdapter.RecyclerAdapterPositionClicked {

    private static final String TAG = "StandardAndFeeFragment";

    @BindView(R.id.academic_year)
    TextView academicYear;
    @BindView(R.id.feecollection)
    AppCompatRadioButton feecollection;
    @BindView(R.id.standardfee)
    AppCompatRadioButton standardfee;
    @BindView(R.id.selectfeecollection)
    RadioGroup selectfeecollection;
    @BindView(R.id.recyclerviewterms)
    RecyclerView recyclerviewterms;
    @BindView(R.id.tablerow)
    TableLayout tablerow;
    @BindView(R.id.generate)
    Button generate;
    @BindView(R.id.feecollectionlayout)
    RelativeLayout feecollectionlayout;
    @BindView(R.id.history)
    Button history;
    @BindView(R.id.standardfeelayout)
    RelativeLayout standardfeelayout;
    Unbinder unbinder;

    private IFeeDetailsPresenter iFeeDetailsPresenter;
    private ResultResponse resultResponse;
    private StudentList studentList;

    private TermSelectorAdapter mAdapter;

    public StandardAndFeeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_standard_and_fee, container, false);
        unbinder = ButterKnife.bind(this, view);

        recyclerviewterms.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerviewterms.setHasFixedSize(true);
        recyclerviewterms.setNestedScrollingEnabled(false);



        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = new TermSelectorAdapter(this, Collections.EMPTY_LIST);

        recyclerviewterms.setAdapter(mAdapter);

//        if (iFeeDetailsPresenter == null) {

        iFeeDetailsPresenter = new FeePresenter(new FeeIntractorImp(dataSource), this);
//        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public void loadStudentFee(StudentList studentList) {

        this.studentList = studentList;



        if (iFeeDetailsPresenter != null) {
            mAdapter.updateList(Collections.EMPTY_LIST);
            iFeeDetailsPresenter.setTermName(null);
            iFeeDetailsPresenter.getStudentFeeList(studentList.getId());
        } else {Log.e(TAG, "presenter not initialized");
//            iFeeDetailsPresenter = new FeePresenter(new FeeIntractorImp(dataSource), this);
//            iFeeDetailsPresenter.setTermName(null);
//            iFeeDetailsPresenter.getStudentFeeList(studentList.getId());
        }


    }


    @OnCheckedChanged({R.id.standardfee, R.id.feecollection})
    void onGenderSelected(CompoundButton button, boolean checked) {
        //do your stuff.

//        boolean ischecked = button.isChecked();

        // Check which radio button was clicked
        switch (button.getId()) {
            case R.id.standardfee:
                if (checked) {

                    changeLayout(false);

                }
                break;
            case R.id.feecollection:
                if (checked) {
                    // 2 clicked
                    changeLayout(true);

                }
                break;
        }

    }

    private void changeLayout(boolean isChanged) {
        if (isChanged) {
            standardfeelayout.setVisibility(View.GONE);
            feecollectionlayout.setVisibility(View.VISIBLE);
        } else {

            standardfeelayout.setVisibility(View.VISIBLE);
            feecollectionlayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFail(Throwable throwable) {
        Utils.hideProgress();

        Log.e(TAG, "onFail: " + throwable.getMessage());
    }

    @Override
    public void onNetworkFailure() {
        Utils.hideProgress();

        Log.e(TAG, "onNetworkFailure: ");
    }

    @Override
    public void showLoading() {

        Utils.showProgress(getActivity(), "Loading");
    }

    @Override
    public void hideLoading() {

        Utils.hideProgress();
    }

    @Override
    public void showSnackBar(View view, String msg) {

    }

    @Override
    public void showToast(String msg) {

        Toast.makeText(getContext(), "" + msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void pageUpdate(ResultResponse resultResponse) {

        this.resultResponse = resultResponse;

//        iFeeDetailsPresenter.parseFeeDetails();

    }

    @Override
    public void loadData(Map<String, Object> response) {

        List<String> list = new ArrayList<>(response.keySet());

        mAdapter.updateList(list);

    }

    @Override
    public void setAcademicYear(String academic) {

        academicYear.setText(academic);

    }

    @Override
    public void showFeeFullDeatils(TermFeesCollectionList termFeesCollectionList) {

//        startActivity(new Intent(getContext(), TermFeesActivity.class).putExtra("termfeedetails", (Serializable) termFeesCollectionList));

        Bundle bundle = new Bundle();
        bundle.putSerializable("termfeedetails", termFeesCollectionList);

        ActivityUtils.startActivity(getActivity(), TermFeesActivity.class, bundle);

    }

    @Override
    public void position(int pos, View view, String s) {

        iFeeDetailsPresenter.setTermName(s);

    }

    @Override
    public void onRefresh() {

    }

    @OnClick(R.id.generate)
    public void submit(View view) {

        iFeeDetailsPresenter.getTermName();
    }

    @OnClick(R.id.history)
    public void onHistory(View view) {

        if (!NullCheckUtils.isEmpty(studentList)) {
            Bundle bundle = new Bundle();
            bundle.putString("student_id", String.valueOf(studentList.getId()));

            ActivityUtils.startActivity(getActivity(), StandardFeesActivity.class, bundle);

        } else {

            Log.e(TAG, "onHistory: studentlist is null");
        }

    }

}
