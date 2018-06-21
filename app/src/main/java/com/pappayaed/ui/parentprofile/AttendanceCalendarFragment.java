package com.pappayaed.ui.parentprofile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.pappayaed.R;
import com.pappayaed.ui.studentprofile.Attend;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import calendar.android.com.customcalendar.CalendarCustomViewRecycler;
import calendar.android.com.customcalendar.CalendarUtils;
import calendar.android.com.customcalendar.onItemClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttendanceCalendarFragment extends Fragment implements onItemClick {

    private static final String TAG = "AttendanceCalendarFragm";


    Unbinder unbinder;
    @BindView(R.id.customCalendar)
    CalendarCustomViewRecycler customCalendar;
    @BindView(R.id.intime)
    TextView intime;
    @BindView(R.id.out_time)
    TextView outTime;
    @BindView(R.id.workours)
    TextView workours;
    @BindView(R.id.attstatus)
    TextView attstatus;
    @BindView(R.id.content)
    RelativeLayout content;
    @BindView(R.id.nodata)
    RelativeLayout nodata;

    public AttendanceCalendarFragment() {
        // Required empty public constructor
    }


    public static AttendanceCalendarFragment getProfileViewFragment(List list) {

        AttendanceCalendarFragment profileViewFragment = new AttendanceCalendarFragment();

        Bundle bundle = new Bundle();

        bundle.putSerializable("list", (Serializable) list);

        profileViewFragment.setArguments(bundle);

        return profileViewFragment;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attendance_calendar, container, false);
        unbinder = ButterKnife.bind(this, view);

        customCalendar.setOnItemClick(this);


        updateUI(new Date());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemClick(View view, int position, Object o) {


        Date mDate = (Date) o;


        updateUI(mDate);


        Log.e(TAG, "onItemClick: " + new Gson().toJson(o));

    }


    private void updateUI(Date mDate) {

        String d = CalendarUtils.getDate(mDate);

        List list = (List) getArguments().getSerializable("list");
        Log.e(TAG, "onViewCreated: " + list.size());

        Observable.just(list).map(new Function<List, Attend>() {
            @Override
            public Attend apply(List list) throws Exception {


                for (int i = 0; i < list.size(); i++) {

                    Attend attend = (Attend) list.get(i);

                    if (attend.getAttendance_Date().equalsIgnoreCase(d)) {

                        return attend;
                    }

                }


                Attend attend = new Attend();
                attend.setNodata(true);

                return attend;
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Attend>() {
            @Override
            public void accept(Attend attend) throws Exception {

                if (attend != null) {


                    if (attend.isNodata()) {

                        content.setVisibility(View.GONE);
                        nodata.setVisibility(View.VISIBLE);

                    } else {

                        content.setVisibility(View.VISIBLE);
                        nodata.setVisibility(View.GONE);
                        intime.setText(attend.getIn_time());
                        outTime.setText(attend.getOut_time());
                        workours.setText(attend.getWorked_Hours());
                        attstatus.setText(attend.getAttendance_Status());
                    }

                } else {

                    Log.e(TAG, "accept: There is no data");

                }

            }
        });

    }
}
