package com.pappayaed.ui.feedetails;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.RecyclerViewAdapterMultiView;
import com.pappayaed.base.BaseFragment;
import com.pappayaed.data.model.TermFeesCollectionList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import info.hoang8f.android.segmented.SegmentedGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class TermFeesFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {

    private static final String TAG = "TermFeesFragment";

    @BindView(R.id.student_name)
    TextView studentName;
    @BindView(R.id.student_grade)
    TextView studentGrade;
    @BindView(R.id.student_id)
    TextView studentId;
    @BindView(R.id.due_date)
    TextView dueDate;
    @BindView(R.id.tutitionamount)
    TextView tutitionamount;
    @BindView(R.id.latepayment)
    TextView latepayment;
    @BindView(R.id.totalamount)
    TextView totalamount;
    @BindView(R.id.status_name)
    TextView statusName;
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.termfee)
    RadioButton termfee;
    @BindView(R.id.history)
    RadioButton history;
    @BindView(R.id.segmented2)
    SegmentedGroup segmented2;
    @BindView(R.id.recyclerviewtermsandhistory)
    RecyclerView recyclerviewtermsandhistory;
    Unbinder unbinder;
    @BindView(R.id.content)
    LinearLayout content;

    private TermFeesCollectionList termFeesCollectionList;
    private RecyclerViewAdapterMultiView adapterMultiView;

    public TermFeesFragment() {
        // Required empty public constructor
    }


    public static TermFeesFragment getTermFeesFragment(TermFeesCollectionList termFeesCollectionList) {

        TermFeesFragment termFeesFragment = new TermFeesFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("termfeesdetails", termFeesCollectionList);

        termFeesFragment.setArguments(bundle);

        return termFeesFragment;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            this.termFeesCollectionList = (TermFeesCollectionList) getArguments().getSerializable("termfeesdetails");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_term_fees, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerviewtermsandhistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerviewtermsandhistory.setHasFixedSize(true);
        recyclerviewtermsandhistory.setNestedScrollingEnabled(false);
        segmented2.setOnCheckedChangeListener(this);

        segmented2.setTintColor(ContextCompat.getColor(getContext(), R.color.red));

//        recyclerviewtermsandhistory.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

//        adapterMultiView = new RecyclerViewAdapterMultiView(Collections.EMPTY_LIST);
//
//        recyclerviewtermsandhistory.setAdapter(adapterMultiView);

//        setFeeDetails(termFeesCollectionList);

    }

    public void setFeeDetails(TermFeesCollectionList feeDetails) {
        this.termFeesCollectionList = feeDetails;

        if (feeDetails != null) {

            studentName.setText(feeDetails.getStudentName());
            studentGrade.setText(feeDetails.getGradeName());

            String id = "Student ID -" + String.valueOf(feeDetails.getStudentId());


            studentId.setText(id);
            dueDate.setText(feeDetails.getDueDate());
            tutitionamount.setText(String.valueOf(feeDetails.getAmount()));
            totalamount.setText(String.valueOf(feeDetails.getAmount()));

            status.setText(feeDetails.getStatus());

            if (feeDetails.getStatus().equalsIgnoreCase("paid")) {
                status.setTextColor(getColor(R.color.green));
                statusName.setTextColor(getColor(R.color.green));
            } else {
                status.setTextColor(getColor(R.color.red));
                statusName.setTextColor(getColor(R.color.red));
            }

            loadData(true);


        } else {

            content.setVisibility(View.GONE);

            Toast.makeText(getContext(), "There is no data", Toast.LENGTH_SHORT).show();

            Log.e(TAG, "setFeeDetails: There is no TermFeesCollectionList ,Please check the details");

        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {

            case R.id.termfee:

                loadData(true);


                break;

            case R.id.history:

                loadData(false);

                break;

        }
    }


    private void loadData(boolean isTerm) {

        List list = new ArrayList();
        if (isTerm) {

            list = this.termFeesCollectionList.getFeesStructureIdO2m();
//            list.addAll(this.termFeesCollectionList.getFeesStructureIdO2m());
//            list.addAll(this.termFeesCollectionList.getFeesStructureIdO2m());
//            list.addAll(this.termFeesCollectionList.getFeesStructureIdO2m());
//            list.addAll(this.termFeesCollectionList.getFeesStructureIdO2m());
//            list.addAll(this.termFeesCollectionList.getFeesStructureIdO2m());
        } else {

            list = this.termFeesCollectionList.getHeadwisePaymentHistoryO2m();
//            list.addAll(this.termFeesCollectionList.getHeadwisePaymentHistoryO2m());
//            list.addAll(this.termFeesCollectionList.getHeadwisePaymentHistoryO2m());
//            list.addAll(this.termFeesCollectionList.getHeadwisePaymentHistoryO2m());
//            list.addAll(this.termFeesCollectionList.getHeadwisePaymentHistoryO2m());
//            list.addAll(this.termFeesCollectionList.getHeadwisePaymentHistoryO2m());
        }

        adapterMultiView = new RecyclerViewAdapterMultiView(list);

        recyclerviewtermsandhistory.setAdapter(adapterMultiView);

        if (!(list.size() > 0))
            Toast.makeText(getContext(), "There is no data", Toast.LENGTH_SHORT).show();


    }
}
