package com.pappayaed.ui.attendance;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.RecyclerViewAdapterMultiView;
import com.pappayaed.common.Utils;
import com.pappayaed.data.model.AttendanceSheetList;
import com.pappayaed.data.model.ResultResponse;
import com.pappayaed.ui.attendance.model.ClassName;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassListFragment extends Fragment implements GridViewRecyclerAdapter.OnItemClickListener{


    @BindView(R.id.classlistrecyclerview)
    RecyclerView classlistrecyclerview;
    Unbinder unbinder;

    private List list = new ArrayList();
    private GridViewRecyclerAdapter mAdapter;

    public ClassListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_class_list, container, false);
        unbinder = ButterKnife.bind(this, view);


//        classlistrecyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//        classlistrecyclerview.setHasFixedSize(true);
//
//        classlistrecyclerview.addItemDecoration(new GridDividerDecoration(getContext()));


        classlistrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)); // set LayoutManager to RecyclerView
//        gridRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), 0));
        classlistrecyclerview.addItemDecoration(new EqualSpaceItemDecoration(1));
        classlistrecyclerview.setHasFixedSize(true);

        mAdapter = new GridViewRecyclerAdapter(this, getContext(), new ArrayList<>());


        classlistrecyclerview.setAdapter(mAdapter);


        loadData();

        return view;
    }

    @Override
    public void position(AttendanceSheetList itemName, int position) {

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


    private void loadData() {


        String json= Utils.loadJSONFromAsset(getContext(),"classlist.json");

        ResultResponse response=new Gson().fromJson(json,ResultResponse.class);

        List<AttendanceSheetList> list=response.getResult().getAttendanceSheetList();


        mAdapter.updateData(list);


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
