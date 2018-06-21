package com.pappayaed.ui.parentprofile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.RecyclerViewAdapterMultiView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileViewFragment extends Fragment {


    private static final String TAG = "ProfileViewFragment";

    @BindView(R.id.recyclerviewprofileview)
    RecyclerView recyclerviewprofileview;
    Unbinder unbinder;
    @BindView(R.id.nodata)
    TextView nodata;
    private RecyclerViewAdapterMultiView adapter;

    public ProfileViewFragment() {
        // Required empty public constructor
    }


    public static ProfileViewFragment getProfileViewFragment(List list) {

        ProfileViewFragment profileViewFragment = new ProfileViewFragment();

        Bundle bundle = new Bundle();

        bundle.putSerializable("list", (Serializable) list);

        profileViewFragment.setArguments(bundle);

        return profileViewFragment;


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_view, container, false);
        unbinder = ButterKnife.bind(this, view);

//        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerview.setHasFixedSize(true);


        recyclerviewprofileview.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerviewprofileview.setLayoutManager(linearLayoutManager);

//        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        layoutManager.setScrollEnabled(false);
//        recyclerviewprofileview.setLayoutManager(layoutManager);


        List list = (List) getArguments().getSerializable("list");

        Log.e(TAG, "onViewCreated: " + list.size());


        updateFragment(list);

        return view;
    }


    public void updateFragment(List list) {

        if (list.size() > 0) {

            adapter = new RecyclerViewAdapterMultiView(new ArrayList(list));
            recyclerviewprofileview.setAdapter(adapter);
            recyclerviewprofileview.setVisibility(View.VISIBLE);
            nodata.setVisibility(View.GONE);

        } else {

            recyclerviewprofileview.setVisibility(View.GONE);
            nodata.setVisibility(View.VISIBLE);

        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
