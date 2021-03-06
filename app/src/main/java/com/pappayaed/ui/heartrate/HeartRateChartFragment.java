package com.pappayaed.ui.heartrate;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.pappayaed.R;
import com.pappayaed.base.BaseFragment;
import com.pappayaed.common.NullCheckUtils;
import com.pappayaed.common.Utils;
import com.pappayaed.customview.LineChartAndroid;
import com.pappayaed.data.listener.DataListener;
import com.pappayaed.data.model.HeartrateList;
import com.pappayaed.data.model.ResultResponse;
import com.pappayaed.ui.showprofile.StudentList;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HeartRateChartFragment extends BaseFragment {


    private static final String TAG = "HeartRateChartFragment";

    @BindView(R.id.error)
    TextView error;
    Unbinder unbinder;
    @BindView(R.id.lineChart)
    LineChartAndroid lineChart;
    @BindView(R.id.heartratecount)
    TextView heartratecount;
    @BindView(R.id.heartratecomments)
    TextView heartratecomments;
    @BindView(R.id.content)
    ScrollView content;
    @BindView(R.id.comments)
    TextView comments;

    public HeartRateChartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_heart_rate_chart, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }


    public void loadStudentData(final StudentList studentList) {

//        error.setText("Student ID is :  " + studentList.getId() + "  " + studentList.getName());


        Map<Object, Object> json = new HashMap<>();
        Map<Object, Object> params = new HashMap<>();
        params.put("login", dataSource.getEmailOrUsername());
        params.put("password", dataSource.getPassword());
        params.put("user_type", dataSource.getUserType());
        params.put("student_id", studentList.getId());


        json.put("params", params);

        final String js = new JSONObject(json).toString();

        Utils.showProgress(getActivity(), "Loading");

        dataSource.get_Standard_HeartRate_list(js, new DataListener() {
            @Override
            public void onSuccess(Object object) {

                Utils.hideProgress();

                ResultResponse response = (ResultResponse) object;

                setLineChart(response, studentList.getColor());

            }

            @Override
            public void onFail(Throwable throwable) {
                Utils.hideProgress();
                setLineChart(null, Color.BLUE);
            }

            @Override
            public void onNetworkFailure() {
                Utils.hideProgress();
                setLineChart(null, Color.GREEN);
            }
        });


    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setLineChart(ResultResponse response, int color) {

//        Log.e(TAG, "setLineChart: " + response.toString());

//        String json = Utils.loadJSONFromAsset(getContext(), "heartrate.json");
//        Gson gson = new Gson();

//        ResultResponse response = gson.fromJson(json, ResultResponse.class);

        if (!NullCheckUtils.isEmpty(response) && !NullCheckUtils.isEmpty(response.getResult())) {

            List<HeartrateList> list = !NullCheckUtils.isEmpty(response.getResult().getHeartrateList()) ? response.getResult().getHeartrateList() : new ArrayList<HeartrateList>();

            if (list != null && list.size() > 0) {


                ArrayList<String> labels = new ArrayList<>();
                ArrayList<Float> values = new ArrayList<>();

                long heartcount = 0;

                for (int i = 0; i < list.size(); i++) {

                    HeartrateList heartrateList = list.get(i);

                    labels.add(heartrateList.getConvertedTime());
                    values.add(Float.valueOf(heartrateList.getHeartrate()));

                    heartcount = heartcount + heartrateList.getHeartrate();

                }

                heartcount = heartcount / list.size();

//
//                heartcount = 50;

                heartratecount.setText(heartcount + "");

                if ((heartcount >= 60) && (heartcount <= 100)) {

                    heartratecomments.setText("Good");

                    comments.setText("Good Heart Rate");
                    heartratecount.setTextColor(ContextCompat.getColor(getContext(), R.color.green));
                    heartratecomments.setTextColor(ContextCompat.getColor(getContext(), R.color.green));

                    color = ContextCompat.getColor(getContext(), R.color.green);

                } else if (heartcount > 100) {

                    heartratecomments.setText("High");
                    comments.setText("Borderline High Heart Rate");
                    heartratecomments.setTextSize(8f);
                    heartratecount.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
                    heartratecomments.setTextColor(ContextCompat.getColor(getContext(), R.color.red));

                    color = ContextCompat.getColor(getContext(), R.color.red);

                } else if (heartcount < 60) {

                    heartratecomments.setText("Low");
                    comments.setText("Borderline Low Heart Rate");
                    heartratecomments.setTextSize(8f);
                    heartratecount.setTextColor(ContextCompat.getColor(getContext(), R.color.blue));
                    heartratecomments.setTextColor(ContextCompat.getColor(getContext(), R.color.blue));

                    color = ContextCompat.getColor(getContext(), R.color.blue);

                }

//                lineChart.setEntry(values, "Heart Rate", ContextCompat.getColor(getContext(), R.color.colorPrimary));
                lineChart.setEntry(values, "Heart Rate", color);

                lineChart.showLineChart(labels, true);

                error.setVisibility(View.GONE);
                content.setVisibility(View.VISIBLE);

            } else {

                error.setVisibility(View.VISIBLE);
                content.setVisibility(View.GONE);

            }
        } else {

            error.setVisibility(View.VISIBLE);
            content.setVisibility(View.GONE);

        }

    }
}
