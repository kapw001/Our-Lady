package com.pappayaed.ui.calendarandlistview;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.RecyclerViewAdapterMultiView;
import com.pappayaed.data.model.AttendanceList;
import com.pappayaed.data.model.Circular;
import com.pappayaed.data.model.HomeWork;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import calendar.android.com.customcalendar.CalendarCustomViewRecycler;
import calendar.android.com.customcalendar.EventHighlight;
import calendar.android.com.customcalendar.EventObjects;
import calendar.android.com.customcalendar.onItemClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarAndListFragment extends Fragment implements ICalendarListView, onItemClick {

    private static final String TAG = "CalendarAndListFragment";
    @BindView(R.id.customCalendar)
    CalendarCustomViewRecycler customCalendar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.error)
    TextView error;
    @BindView(R.id.content)
    RelativeLayout content;
    @BindView(R.id.error_view)
    TextView errorView;
    @BindView(R.id.errorContent)
    RelativeLayout errorContent;
    Unbinder unbinder;

    private RecyclerViewAdapterMultiView mAdapter;
    private OnItemClickCallback onItemClickCallback;

    public CalendarAndListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            onItemClickCallback = (OnItemClickCallback) context;
        } catch (ClassCastException e) {

            Log.e(TAG, "onAttach: " + context.toString() + " must implement in Activity   " + e.getMessage());

            try {
                onItemClickCallback = (OnItemClickCallback) getParentFragment();
            } catch (ClassCastException e1) {
//                throw new ClassCastException(context.toString() + " must implement in fragment");
                Log.e(TAG, "onAttach: " + context.toString() + " must implement in fragment   " + e1.getMessage());
            }
        }


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar_and_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        customCalendar.setOnItemClick(this);

        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.setHasFixedSize(true);
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setEventHighlight(EventHighlight eventHighlight) {

        customCalendar.setEventHighlight(eventHighlight);
    }

    @Override
    public void setDataCircular(List<Circular> circularList, List<EventObjects> eventList) {

        showContent(circularList, eventList);
        showCOntent();

    }

    @Override
    public void updateDataCircular(List<Circular> circularList) {

        callAdapter(circularList);
        showCOntent();
    }

    @Override
    public void setDataHome(List<HomeWork> homeList, List<EventObjects> eventList) {

        showContent(homeList, eventList);

    }

    @Override
    public void updateDataHome(List<HomeWork> homeList) {

        callAdapter(homeList);
        showCOntent();
    }

    @Override
    public void setDataAttendance(List<AttendanceList> attendanceLists, List<EventObjects> eventList) {
        showContent(attendanceLists, eventList);
    }

    @Override
    public void updateAttendance(List<AttendanceList> attendanceLists) {

        if (attendanceLists.size() > 0) {
            callAdapter(attendanceLists);
            showCOntent();
        } else {
            showError("There is no data");
        }
    }

    @Override
    public void updateEventList(List<EventObjects> eventList) {

        customCalendar.setEvents(eventList);
        showCOntent();
    }

    @Override
    public void showErrorOrEmptyView(String msg, boolean isContentOrEventNotFound) {


        if (isContentOrEventNotFound) {

            showErrorView(msg);
        } else {

            showError(msg);

        }


    }

    @Override
    public void onItemClick(View view, int position, Object o) {

        if (onItemClickCallback != null) onItemClickCallback.onItemClick(view, position, o);
    }


    private void callAdapter(List<?> list) {

        mAdapter = new RecyclerViewAdapterMultiView(list);
        recyclerview.setAdapter(mAdapter);
    }

    private void showContent(List<?> list, List<EventObjects> eventList) {

        content.setVisibility(View.VISIBLE);
        errorContent.setVisibility(View.GONE);

        customCalendar.setEvents(eventList);
        mAdapter.updateData(list);

    }

    private void showCOntent() {
        content.setVisibility(View.VISIBLE);
        errorContent.setVisibility(View.GONE);
        recyclerview.setVisibility(View.VISIBLE);
        error.setVisibility(View.GONE);
    }


    private void showError(String msg) {

        Toast.makeText(getContext(), "There is no data", Toast.LENGTH_SHORT).show();
        error.setVisibility(View.VISIBLE);
//        errorContent.setVisibility(View.VISIBLE);
        recyclerview.setVisibility(View.GONE);

        if (msg != null && msg.length() > 0) {
            errorView.setText(msg);
        }

    }

    private void showErrorView(String msg) {

        content.setVisibility(View.GONE);
        errorContent.setVisibility(View.VISIBLE);

        if (msg != null && msg.length() > 0) {
            errorView.setText(msg);
        }

    }

    public interface OnItemClickCallback {
        void onItemClick(View view, int position, Object o);
    }
}
