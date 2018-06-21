package com.pappayaed.ui.attendance;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.DividerItemDecoration;
import com.pappayaed.RecyclerViewCommon.RecyclerViewAdapterMultiView;
import com.pappayaed.base.BaseFragment;
import com.pappayaed.common.Utils;
import com.pappayaed.data.listener.DataListener;
import com.pappayaed.data.model.AttendanceLine;
import com.pappayaed.data.model.AttendanceSheetList;
import com.pappayaed.data.model.Result;
import com.pappayaed.data.model.ResultResponse;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import calendar.android.com.customcalendar.CalendarUtils;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class PresentAbsentFragmentBackup extends BaseFragment implements GridViewRecyclerAdapter.OnItemClickListener, RecyclerViewAdapterMultiView.OnRecyclerViewItemClickListener {

    private static final String TAG = "PresentAbsentFragment";

    @BindView(R.id.totalcount)
    TextView totalcount;
    @BindView(R.id.txtDate)
    TextView txtDate;
    @BindView(R.id.presentcount)
    TextView presentcount;
    @BindView(R.id.absentcount)
    TextView absentcount;
    @BindView(R.id.presentabsentrecyclerview)
    RecyclerView presentabsentrecyclerview;
    Unbinder unbinder;
    @BindView(R.id.classlistrecyclerview)
    RecyclerView classlistrecyclerview;
    @BindView(R.id.contentdisplay)
    RelativeLayout contentdisplay;
    @BindView(R.id.nodatapresent)
    TextView nodatapresent;
    @BindView(R.id.totalname)
    TextView totalname;
    @BindView(R.id.totalstudentlay)
    LinearLayout totalstudentlay;
    @BindView(R.id.presentname)
    TextView presentname;
    @BindView(R.id.presentlay)
    LinearLayout presentlay;
    @BindView(R.id.absentname)
    TextView absentname;
    @BindView(R.id.absentlay)
    LinearLayout absentlay;

    private GridViewRecyclerAdapter mAdapter;

    private RecyclerViewAdapterMultiView presentAbsentAdapter;

    private Map<Object, List<AttendanceLine>> listMap;
    private List<AttendanceSheetList> list;
    private List<AttendanceLine> presentAbsentList;
    private List<AttendanceLine> presentAbsentListBackup = new ArrayList<>();
    private List<AttendanceLine> serverUpdateRecord = new ArrayList<>();

    private MenuItem menu;

    private String sectionName;

    private ShowHideCallBack showHideCallBack;

    public PresentAbsentFragmentBackup() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        showHideCallBack = (ShowHideCallBack) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_present_absent, container, false);
        unbinder = ButterKnife.bind(this, view);

        init();

        callApi();

        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.save, menu);

        this.menu = menu.findItem(R.id.save);

        this.menu.setVisible(false);

        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {

            case R.id.save:


//                Log.e(TAG, "onOptionsItemSelected: " + new JSONArray(presentAbsentList).toString());


                updateDailyAttendance();

//                Log.e(TAG, "onOptionsItemSelected: " + new Gson().toJson(presentAbsentList));


                return true;


            default:

                return super.onOptionsItemSelected(item);

        }


    }


    @OnClick(R.id.totalstudentlay)
    public void onTotalStudentClick(View view) {

        updateUIForCount(1);

        if (presentAbsentList.size() > 0) {

            presentAbsentListBackup.clear();
            presentAbsentListBackup.addAll(presentAbsentList);
            presentAbsentAdapter.updateData(presentAbsentList);
        }

    }

    @OnClick(R.id.presentlay)
    public void onTotalPresentClick(View view) {
        updateUIForCount(2);

        filterList("present");
    }

    @OnClick(R.id.absentlay)
    public void onTotalAbsentClick(View view) {
        updateUIForCount(3);

        filterList("absent");
    }


    private void updateUIForCount(int position) {

        totalstudentlay.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
        presentlay.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
        absentlay.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));

        totalcount.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        presentcount.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        absentcount.setTextColor(ContextCompat.getColor(getContext(), R.color.black));

        totalname.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        presentname.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        absentname.setTextColor(ContextCompat.getColor(getContext(), R.color.black));


        switch (position) {

            case 1:


                totalstudentlay.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                presentlay.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
                absentlay.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));

                totalcount.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
                presentcount.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                absentcount.setTextColor(ContextCompat.getColor(getContext(), R.color.black));

                totalname.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
                presentname.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                absentname.setTextColor(ContextCompat.getColor(getContext(), R.color.black));

                break;

            case 2:

                totalstudentlay.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
                presentlay.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                absentlay.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));

                totalcount.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                presentcount.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
                absentcount.setTextColor(ContextCompat.getColor(getContext(), R.color.black));

                totalname.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                presentname.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
                absentname.setTextColor(ContextCompat.getColor(getContext(), R.color.black));

                break;

            case 3:

                totalstudentlay.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
                presentlay.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
                absentlay.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));

                totalcount.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                presentcount.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                absentcount.setTextColor(ContextCompat.getColor(getContext(), R.color.white));

                totalname.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                presentname.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                absentname.setTextColor(ContextCompat.getColor(getContext(), R.color.white));

                break;

            default:

                totalstudentlay.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
                presentlay.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
                absentlay.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));

                totalcount.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                presentcount.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                absentcount.setTextColor(ContextCompat.getColor(getContext(), R.color.black));

                totalname.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                presentname.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                absentname.setTextColor(ContextCompat.getColor(getContext(), R.color.black));


        }


    }


    public void updateDailyAttendance() {

        if (presentAbsentList != null && presentAbsentList.size() > 0) {
//        Log.e(TAG, "onOptionsItemSelected: " + new JSONArray(presentAbsentList).toString());
            JsonArray jsonElements = (JsonArray) new Gson().toJsonTree(presentAbsentList);

            Log.e(TAG, "updateDailyAttendance: " + jsonElements.toString());

//        Toast.makeText(getContext(), "Ok", Toast.LENGTH_SHORT).show();

//            List<AttendanceLine> upDateList = new ArrayList<>();
//            upDateList.clear();
//            upDateList.addAll(presentAbsentList);
//
//            if (presentAbsentList != null) {
//
//                AttendanceSheetList sheetList1 = null;
//
//                for (int i = 0; i < list.size(); i++) {
//
//                    AttendanceSheetList sheetList = list.get(i);
//
//                    if (this.sectionName.toLowerCase().equalsIgnoreCase(sheetList.getSectionName().toLowerCase())) {
//
//                        sheetList1 = sheetList;
//
//                    }
//
//                }
//
//
//                try {
//                    Utils.showProgress(getContext(), "Loading");
//                    JSONObject jsonObject = new JSONObject();
//
//                    JSONObject jsonObject1 = new JSONObject();
//                    jsonObject1.put("login", dataSource.getEmailOrUsername());
//
//                    jsonObject1.put("password", dataSource.getPassword());
//                    jsonObject1.put("user_type", dataSource.getUserType());
//                    jsonObject1.put("attendance_sheet_id", sheetList1.getAttendanceSheetId());
//
//                    JSONArray jsonArray = new JSONArray();
//
//                    for (int i = 0; i < upDateList.size(); i++) {
//
//                        AttendanceLine line = upDateList.get(i);
//
//                        JSONObject jsonObject2 = new JSONObject();
//                        jsonObject2.put("status", line.getStatus());
//                        jsonObject2.put("student_full_name", line.getStudentFullName());
//                        jsonObject2.put("attendance_id", line.getAttendanceId());
//                        jsonObject2.put("enrollment_num", line.getEnrollmentNum());
//                        jsonObject2.put("student_id", line.getStudentId());
//                        jsonObject2.put("attendance_date", line.getAttendanceDate());
//                        jsonObject2.put("remark", line.getRemark());
//                        jsonObject2.put("school_id", line.getSchoolId());
//
//                        jsonArray.put(jsonObject2);
//
//                    }
//
//                    jsonObject1.put("attendance_line", jsonArray);
//
//                    jsonObject.put("params", jsonObject1);
//
//                    Log.e(TAG, "updateDailyAttendance: " + jsonObject.toString());
//
//                    dataSource.updateAttendanceList(jsonObject.toString(), new DataListener() {
//                        @Override
//                        public void onSuccess(Object object) {
//
//                            Utils.hideProgress();
//
//
//                            Toast.makeText(getContext(), "Successfully attendance updated...", Toast.LENGTH_SHORT).show();
//
//                            ResultResponse response = (ResultResponse) object;
//
//
//                            for (int i = 0; i < presentAbsentList.size(); i++) {
//
//                                presentAbsentList.get(i).setIs_changed(false);
//                            }
//
//                            presentAbsentAdapter.updateData(presentAbsentList);
//                            updateUIData();
//
//
//                            Log.e(TAG, "onSuccess: " + new Gson().toJson(response));
//
//
//                        }
//
//                        @Override
//                        public void onFail(Throwable throwable) {
//                            Utils.hideProgress();
//                        }
//
//                        @Override
//                        public void onNetworkFailure() {
//                            Utils.hideProgress();
//                        }
//                    });
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }


//            Map<Object, Object> json = new HashMap<>();
//            Map<Object, Object> params = new HashMap<>();
//            params.put("login", dataSource.getEmailOrUsername());
//            params.put("password", dataSource.getPassword());
//            params.put("user_type", dataSource.getUserType());
//            params.put("attendance_sheet_id", sheetList1.getAttendanceSheetId());
//            params.put("attendance_line", upDateList);
//
//            json.put("params", params);
//
//            final String js = new JSONObject(json).toString();

//
//            dataSource.updateAttendanceList(js, new DataListener() {
//                @Override
//                public void onSuccess(Object object) {
//
//                    Utils.hideProgress();
//
//
//                    Toast.makeText(getContext(), "Successfully attendance updated...", Toast.LENGTH_SHORT).show();
//
//                    ResultResponse response = (ResultResponse) object;
//
//
//                    for (int i = 0; i < presentAbsentList.size(); i++) {
//
//                        presentAbsentList.get(i).setIs_changed(false);
//                    }
//
//                    presentAbsentAdapter.updateData(presentAbsentList);
//                    updateUIData();
//
//
//                    Log.e(TAG, "onSuccess: " + new Gson().toJson(response));
//
//
//                }
//
//                @Override
//                public void onFail(Throwable throwable) {
//                    Utils.hideProgress();
//                }
//
//                @Override
//                public void onNetworkFailure() {
//                    Utils.hideProgress();
//                }
//            });


        } else {

//            noData();
            Toast.makeText(getContext(), "There is no data", Toast.LENGTH_SHORT).show();

        }
//        }

//        else {
//
//            Toast.makeText(getContext(), "There is no data", Toast.LENGTH_SHORT).show();
//        }


    }


    private void init() {

        classlistrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)); // set LayoutManager to RecyclerView
        classlistrecyclerview.addItemDecoration(new EqualSpaceItemDecoration(1));
        classlistrecyclerview.setHasFixedSize(true);
        mAdapter = new GridViewRecyclerAdapter(this, getContext(), new ArrayList<>());
        classlistrecyclerview.setAdapter(mAdapter);


        presentAbsentAdapter = new RecyclerViewAdapterMultiView(new ArrayList());
        presentabsentrecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        presentabsentrecyclerview.setHasFixedSize(true);
        presentabsentrecyclerview.setAdapter(presentAbsentAdapter);

        presentabsentrecyclerview.addItemDecoration(new DividerItemDecoration(getContext()));


        presentAbsentAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void position(AttendanceSheetList itemName, int position) {

        getPresentAbsentBySectionName(itemName.getSectionName());

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

        dataSource.getDailyAttendanceList(js, new DataListener() {
            @Override
            public void onSuccess(Object object) {

                Utils.hideProgress();

                ResultResponse response = (ResultResponse) object;

                Result result = response.getResult();

                if (result != null) {

                    if (result.getAttendanceSheetList() != null) {

                        loadData(response);

//                        contentdisplay.setVisibility(View.VISIBLE);
//                        nodatapresent.setVisibility(View.GONE);
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
                nodatapresent.setText("There is no internet");
                Toast.makeText(getContext(), "There is no internet", Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void noData() {


        contentdisplay.setVisibility(View.GONE);
        nodatapresent.setVisibility(View.VISIBLE);

        Toast.makeText(getContext(), "There is no data", Toast.LENGTH_SHORT).show();

    }

    private void loadData(ResultResponse response) {

//        String json = Utils.loadJSONFromAsset(getContext(), "attendlist.json");
//
//        ResultResponse response = new Gson().fromJson(json, ResultResponse.class);

        list = new ArrayList<>();
        list.clear();
        list = response.getResult().getAttendanceSheetList();
        listMap = new LinkedHashMap<>();
        listMap.clear();

        if (list.size() > 0) {

            for (AttendanceSheetList sheetList : list
                    ) {

                listMap.put(sheetList.getSectionName(), sheetList.getAttendanceLine());

            }


            mAdapter.updateData(list);

            getPresentAbsentBySectionName(list.get(0).getSectionName());

            contentdisplay.setVisibility(View.VISIBLE);
            nodatapresent.setVisibility(View.GONE);

        } else {

            noData();
        }


    }


    private void getPresentAbsentBySectionName(String sectionName) {

        this.sectionName = sectionName;


        for (int i = 0; i < list.size(); i++) {

            AttendanceSheetList sheetList = list.get(i);

            if (sectionName.toLowerCase().equalsIgnoreCase(sheetList.getSectionName().toLowerCase())) {

                String d = sheetList.getAttendanceDate();

                Date date = CalendarUtils.convertStringToDate1(d);

                String dateName = CalendarUtils.getMonthbyDate(date);

                txtDate.setText(dateName + "");

            }

        }


        List<AttendanceLine> attendanceLines = listMap.get(sectionName);
        presentAbsentList = attendanceLines;
        presentAbsentListBackup = attendanceLines;

        presentAbsentAdapter.updateData(presentAbsentList);

        updateUIData();

    }


    private void updateUIData() {

        Observable.just(presentAbsentList).map(attendanceLineList -> {

            int present_count = 0;
            int total = attendanceLineList.size();

            for (int i = 0; i < total; i++) {

                AttendanceLine line = attendanceLineList.get(i);

                if (line.getStatus().toLowerCase().equalsIgnoreCase("present".toLowerCase())) {

                    present_count++;

                }

            }

            return present_count;
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(present_count -> {

            int total_student1 = presentAbsentList.size();

            int absent_count = total_student1 - present_count;

            totalcount.setText(total_student1 + "");
            presentcount.setText(present_count + "");
            absentcount.setText(absent_count + "");


        });


        Observable.just(presentAbsentList).map(attendanceLineList -> {

            int total = attendanceLineList.size();
            int isChanged = 0;
            for (int i = 0; i < total; i++) {

                AttendanceLine line = attendanceLineList.get(i);

                if (line.is_changed()) {
                    isChanged++;

                }

            }

            if (isChanged > 0) {

                return true;

            }

            return false;
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {


                if (showHideCallBack != null) {

                    showHideCallBack.show(aBoolean);
                }

                if (menu != null)
                    menu.setVisible(aBoolean);


                else Log.e(TAG, "accept: save menu not initialize ");

            }
        });


        updateUIForCount(1);

    }


    private void filterList(String s) {


        if (presentAbsentList.size() > 0) {


            Observable.just(presentAbsentList).map(new Function<List<AttendanceLine>, List<AttendanceLine>>() {
                @Override
                public List<AttendanceLine> apply(List<AttendanceLine> attendanceLineList) throws Exception {

                    List<AttendanceLine> newList = new ArrayList<AttendanceLine>();

                    for (int i = 0; i < attendanceLineList.size(); i++) {

                        AttendanceLine line = attendanceLineList.get(i);

                        if (line.getStatus().toLowerCase().equalsIgnoreCase(s.toLowerCase())) {

                            newList.add(line);
                        }

                    }


                    return newList;
                }
            }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<AttendanceLine>>() {
                @Override
                public void accept(List<AttendanceLine> attendanceLineList) throws Exception {

                    presentAbsentListBackup.clear();
                    presentAbsentListBackup.addAll(attendanceLineList);

                    presentAbsentAdapter.updateData(presentAbsentListBackup);

                }
            });


        } else {

            Toast.makeText(getContext(), "There is no data ", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public void onItemClick(View view, Object o, int position) {


        switch (view.getId()) {

            case R.id.present:


                if (presentAbsentListBackup.get(position).is_changed()) {
                    presentAbsentListBackup.get(position).setIs_changed(false);
                } else {
                    presentAbsentListBackup.get(position).setIs_changed(true);
                }

//                presentAbsentList.get(position).setIs_changed(true);


                AttendanceLine attendanceLine = presentAbsentListBackup.get(position);

                attendanceLine.setRemark("");
                attendanceLine.setStatus("present");


                for (int i = 0; i < presentAbsentList.size(); i++) {

                    AttendanceLine attendanceLine1 = presentAbsentList.get(i);

                    if (attendanceLine1.getAttendanceId() == attendanceLine.getAttendanceId()) {


                        presentAbsentList.set(i, attendanceLine);

                    }


                }

                change(presentAbsentList);


//                AttendanceLine attendanceLine = presentAbsentList.get(position);
//                presentAbsentAdapter.updateData(attendanceLine, position);
//                updateUIData();


                break;

            case R.id.absent:


                showDialog(presentAbsentList.get(position).getRemark(), new MSGCallBack() {
                    @Override
                    public void remark(String s) {

                        if (presentAbsentListBackup.get(position).is_changed()) {
                            presentAbsentListBackup.get(position).setIs_changed(false);
                        } else {
                            presentAbsentListBackup.get(position).setIs_changed(true);
                        }

//                        presentAbsentList.get(position).setStatus("absent");
//                        presentAbsentList.get(position).setRemark(s);
//                        presentAbsentList.get(position).setIs_changed(true);


                        AttendanceLine attendanceLine = presentAbsentListBackup.get(position);

                        attendanceLine.setRemark(s);
                        attendanceLine.setStatus("absent");


                        for (int i = 0; i < presentAbsentList.size(); i++) {

                            AttendanceLine attendanceLine1 = presentAbsentList.get(i);

                            if (attendanceLine1.getAttendanceId() == attendanceLine.getAttendanceId()) {


                                presentAbsentList.set(i, attendanceLine);

                            }


                        }

                        change(presentAbsentList);


//                        AttendanceLine attendanceLine = presentAbsentList.get(position);
//                        presentAbsentAdapter.updateData(attendanceLine, position);
//                        updateUIData();

                        Toast.makeText(getContext(), "Remark successfully added...", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void cancel() {

                        Toast.makeText(getContext(), "Please enter remark", Toast.LENGTH_SHORT).show();
                    }
                });


                break;


        }


//        if (o instanceof AttendanceLine) {
//
//            if (((AttendanceLine) o).getStatus().toLowerCase().equalsIgnoreCase("present".toLowerCase())) {
//
//                presentAbsentList.get(position).setStatus("absent");
//
//            } else {
//                presentAbsentList.get(position).setStatus("present");
//            }
//
//            presentAbsentAdapter.updateData(presentAbsentList);
//
//        }

    }


    private void showDialog(String s, MSGCallBack msgCallBack) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Light_Dialog);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.remark);
        edt.setTextColor(Color.BLACK);
        edt.setText(s);

        dialogBuilder.setTitle("Add Remark");
        dialogBuilder.setMessage("Enter remark below");
        dialogBuilder.setPositiveButton("Save", (dialog, whichButton) -> {
            //do something with edt.getText().toString();
            msgCallBack.remark(edt.getText().toString());
        });
//        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//                //pass
//
//                msgCallBack.cancel();
//            }
//
//        });
        AlertDialog b = dialogBuilder.create();
        b.show();

    }


    private void change(List<AttendanceLine> attendanceLineList) {

        presentAbsentAdapter.updateData(attendanceLineList);
        updateUIData();
    }


    public class EqualSpaceItemDecoration extends RecyclerView.ItemDecoration {

        private final int mSpaceHeight;

        public EqualSpaceItemDecoration(int mSpaceHeight) {
            this.mSpaceHeight = mSpaceHeight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            outRect.bottom = mSpaceHeight;
            outRect.top = mSpaceHeight;
            outRect.left = mSpaceHeight;
            outRect.right = mSpaceHeight;
        }
    }


    public interface MSGCallBack {

        void remark(String s);

        void cancel();
    }


    interface ShowHideCallBack {

        void show(boolean isEnabled);

    }
}
