package com.pappayaed.ui.homework;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.RecyclerViewAdapterMultiView;
import com.pappayaed.base.BaseFragment;
import com.pappayaed.common.Utils;
import com.pappayaed.data.listener.DataListener;
import com.pappayaed.data.model.HomeworkId;
import com.pappayaed.data.model.HomeworkList;
import com.pappayaed.data.model.Result;
import com.pappayaed.data.model.ResultResponse;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by yasar on 2/5/17.
 */

public class HomeFragment extends BaseFragment implements AdapterView.OnItemSelectedListener, RecyclerViewAdapterMultiView.OnRecyclerViewItemClickListener {

    private static final String TAG = "HomeFragment";

    public static final int REQUEST_HOMEWORK = 1234;
    @BindView(R.id.spinner)
    CCSpinner spinner;
    Unbinder unbinder;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.content)
    LinearLayout content;
    @BindView(R.id.nodata)
    TextView nodata;

    private List<HomeworkList> homeworkLists = new ArrayList<>();

    private List<String> classNames = new ArrayList<>();

    private Map<Object, HomeworkList> objectMap = new LinkedHashMap<>();

    private RecyclerViewAdapterMultiView mAdapter;

    private MyCustomSpinnerAdapter myCustomSpinnerAdapter;

    private int updatePosition = 0;

    private List homeworkData = new ArrayList();


    public static HomeFragment getInstance() {
        HomeFragment submissionFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        submissionFragment.setArguments(bundle);
        return submissionFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.new_fragment1, container, false);


        unbinder = ButterKnife.bind(this, view);

        myCustomSpinnerAdapter = new MyCustomSpinnerAdapter(getContext(), R.layout.spinnerlayout, new ArrayList<>(), spinner);

        mAdapter = new RecyclerViewAdapterMultiView(new ArrayList());

        mAdapter.setOnItemClickListener(this);

        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
        recyclerview.setAdapter(mAdapter);


        spinner.setAdapter(myCustomSpinnerAdapter);

        spinner.setOnItemSelectedListener(this);

        callApi();

        return view;
    }


    private void callApi() {

//        loadData(new ResultResponse());


        Utils.showProgress(getContext(), "Loading");
        Map<Object, Object> json = new HashMap<>();
        Map<Object, Object> params = new HashMap<>();
        params.put("login", dataSource.getEmailOrUsername());
        params.put("password", dataSource.getPassword());
        params.put("user_type", dataSource.getUserType());

        json.put("params", params);

        final String js = new JSONObject(json).toString();

        dataSource.getDailyHomeWorkList(js, new DataListener() {
            @Override
            public void onSuccess(Object object) {

                Utils.hideProgress();

                ResultResponse response = (ResultResponse) object;

                Result result = response.getResult();

                if (result != null) {

                    if (result.getHomeworkList() != null) {

                        loadData(response);

                    } else {
                        noData();
                    }

                } else {
                    noData();
                }

            }

            @Override
            public void onFail(Throwable throwable) {
                Utils.hideProgress();
                noData();
            }

            @Override
            public void onNetworkFailure() {
                Utils.hideProgress();
                noData();
                nodata.setText("There is no internet");
                Toast.makeText(getContext(), "There is no internet", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (REQUEST_HOMEWORK == resultCode) {
            Log.e(TAG, "onActivityResult: " + requestCode + "   " + resultCode);

            if (homeworkData.size() > 0) {
                String des = data.getStringExtra("des");

                HomeworkId homeworkId = (HomeworkId) homeworkData.get(updatePosition);

                homeworkId.setDescription(des);

                homeworkData.set(updatePosition, homeworkId);

                mAdapter.updateData(homeworkData);
            } else {

                Log.e(TAG, "onActivityResult: no data ");
            }

//            Intent intent = getActivity().getIntent();
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
//            getActivity().overridePendingTransition(0, 0);
//            getActivity().finish();
//
//            getActivity().overridePendingTransition(0, 0);
//            startActivity(intent);


//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    callApi();
//                }
//            }, 1000);


        }
    }

    private void noData() {

        content.setVisibility(View.GONE);
        nodata.setVisibility(View.VISIBLE);

        Toast.makeText(getContext(), "There is no data", Toast.LENGTH_SHORT).show();

    }

    private void loadData(ResultResponse response) {

//        String json = Utils.loadJSONFromAsset(getContext(), "homework.json");
//
//        ResultResponse response = new Gson().fromJson(json, ResultResponse.class);

        homeworkLists.clear();
        homeworkLists = response.getResult().getHomeworkList();
        classNames = new ArrayList<>();
        classNames.clear();
        objectMap = new LinkedHashMap<>();
        objectMap.clear();
        int size = homeworkLists.size();

        Log.e(TAG, "loadData: " + size);

        if (size > 0) {

            for (int i = 0; i < size; i++) {

                HomeworkList homeworkList = homeworkLists.get(i);

                String section_name = homeworkList.getSectionName();

                classNames.add(section_name);
                objectMap.put(section_name, homeworkList);
            }


            myCustomSpinnerAdapter.updateList(classNames);

            content.setVisibility(View.VISIBLE);
            nodata.setVisibility(View.GONE);

        } else {

            noData();
        }


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        String name = classNames.get(position);

        HomeworkList homeworkList = objectMap.get(name);

        HeaderRowData headerRowData = new HeaderRowData(R.drawable.ic_account_circle_black_24dp, "Primary Teacher", homeworkList.getPrimaryTutorName());
        HeaderRowData headerRowData1 = new HeaderRowData(R.drawable.ic_account_circle_black_24dp, "Secondary Teacher", homeworkList.getSecondaryTutorName().toString());
        HeaderRowData headerRowData2 = new HeaderRowData(R.drawable.smallcalendar, "Date", homeworkList.getDate());


        homeworkData = new ArrayList();

        homeworkData.add(headerRowData);
        homeworkData.add(headerRowData1);
        homeworkData.add(headerRowData2);

        HomeworkId homeworkId = new HomeworkId();
        homeworkId.setTitle(true);
        homeworkId.setSubjectName("Subjects");
        homeworkId.setSubPrimaryTutorName("Primary Teacher");

        homeworkData.add(homeworkId);

        homeworkData.addAll(homeworkList.getHomeworkIds());


        mAdapter.updateData(homeworkData);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(View view, Object o, int position) {

        if (view.getId() == R.id.lay_root) {

            updatePosition = position;

            String cName = classNames.get(spinner.getSelectedItemPosition());
            if (cName != null) {

                HomeworkId homeworkId = (HomeworkId) o;

                Intent intent = new Intent(getContext(), UpdateHomeWorkActivity.class);
                intent.putExtra("classname", cName);
                intent.putExtra("homeworkId", homeworkId);

                startActivityForResult(intent, REQUEST_HOMEWORK);


            }


        }

    }
}
