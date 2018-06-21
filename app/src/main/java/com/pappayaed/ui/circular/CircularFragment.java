package com.pappayaed.ui.circular;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.pappayaed.R;
import com.pappayaed.base.BaseFragment;
import com.pappayaed.data.model.Circular;
import com.pappayaed.data.model.HomeWork;
import com.pappayaed.ui.calendarandlistview.CalendarAndListFragment;
import com.vlonjatg.progressactivity.ProgressFrameLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import calendar.android.com.customcalendar.EventHighlight;
import calendar.android.com.customcalendar.EventObjects;
import info.hoang8f.android.segmented.SegmentedGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class CircularFragment extends BaseFragment implements ICircularView, RadioGroup.OnCheckedChangeListener, CalendarAndListFragment.OnItemClickCallback {


    Unbinder unbinder;

    @BindView(R.id.circular)
    RadioButton circular;
    @BindView(R.id.homework)
    RadioButton homework;
    @BindView(R.id.segmented2)
    SegmentedGroup segmented2;
    @BindView(R.id.progressLayout)
    ProgressFrameLayout progressLayout;

    private CalendarAndListFragment calendarAndListFragment;

    private ICircularPresenter iCircularPresenter;


    public CircularFragment() {
        // Required empty public constructor
    }

    private static final String TAG = "CircularFragment";

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_circular, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        calendarAndListFragment = (CalendarAndListFragment) getChildFragmentManager().findFragmentById(R.id.calendarAndListFragment);


        segmented2.setOnCheckedChangeListener(this);

        segmented2.setTintColor(ContextCompat.getColor(getContext(), R.color.red));


        calendarAndListFragment.setEventHighlight(EventHighlight.CIRCLE);

        if (iCircularPresenter == null) {

            iCircularPresenter = new CircularPresenterImpl(new CircularIntractorImpl(getContext(), dataSource));

            iCircularPresenter.onAttach(this);


        }

        callNetwork();

    }

    @Override
    public void callNetwork() {

        if (iCircularPresenter != null) {
            iCircularPresenter.getCircularAndHomeWork();
        } else {
            Log.e(TAG, "callNetwork: Presenter not initialize ");
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onFail(Throwable throwable) {

//        super.onFail(throwable);


        progressBarStateCall(progressLayout, R.drawable.somethingwentwrong, "error");

    }

    @Override
    public void onNetworkFailure() {
//        super.onNetworkFailure();

        progressBarStateCall(progressLayout, R.drawable.nointernet, "nointernet");


    }

    @Override
    public void showLoading() {
//        super.showLoading();

        progressBarStateCall(progressLayout, R.drawable.nointernet, "loading");

    }

    @Override
    public void hideLoading() {
//        super.hideLoading();

        progressBarStateCall(progressLayout, R.drawable.nointernet, "content");

    }

    @Override
    public void showSnackBar(View view, String msg) {

    }

    @Override
    public void showToast(String msg) {

    }


    @Override
    public void setDataCircular(List<Circular> circularList) {

        calendarAndListFragment.updateDataCircular(circularList);
    }

    @Override
    public void setDataHome(List<HomeWork> homeList) {

        calendarAndListFragment.updateDataHome(homeList);
    }

    @Override
    public void setEventList(List<EventObjects> eventList) {

        calendarAndListFragment.updateEventList(eventList);

    }

    @Override
    public void setEmptyData() {

    }

    @Override
    public void emptyData() {

        calendarAndListFragment.showErrorOrEmptyView(null, false);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

        switch (checkedId) {

            case R.id.circular:

                iCircularPresenter.setIsCircular(true);
                iCircularPresenter.loadCircularData();

                break;

            case R.id.homework:

                iCircularPresenter.setIsCircular(false);
                iCircularPresenter.loadHomeWOrkData();

                break;

        }

    }


    @Override
    public void onItemClick(View view, int position, Object o) {


        iCircularPresenter.onItemClick(view, position, o);
    }
}
