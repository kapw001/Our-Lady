package com.pappayaed.ui.feedetails;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pappayaed.R;
import com.pappayaed.base.BaseFragment;
import com.pappayaed.common.NullCheckUtils;
import com.pappayaed.common.Utils;
import com.pappayaed.data.listener.DataListener;
import com.pappayaed.data.model.MiscellaneousFeesCollectionList;
import com.pappayaed.data.model.ResultResponse;
import com.pappayaed.ui.reuserecyclerfragment.RecyclerFragment;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class StandardFeeFragment extends BaseFragment implements RecyclerFragment.OnItemCallback {


    Unbinder unbinder;

    private RecyclerFragment recyclerFragment;


    public StandardFeeFragment() {
        // Required empty public constructor
    }

    public static StandardFeeFragment getStandardFeeFragment(String student_id) {

        StandardFeeFragment fragment = new StandardFeeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("student_id", student_id);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_standard_fee, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerFragment = (RecyclerFragment) getChildFragmentManager().findFragmentById(R.id.recyclerFragment);

//        recyclerFragment.setLayoutManager(new LinearLayoutManager(getContext()));

//        recyclerviewstandard.addItemDecoration(new DividerItemDecoration(getContext(), R.drawable.divider));

        if (getArguments() != null) {

            loadData(getArguments().getString("student_id"));
        }

    }


    public void loadData(String student_id) {

        Utils.showProgress(getContext(), "Loading");

        Map<Object, Object> json = new HashMap<>();
        Map<Object, Object> params = new HashMap<>();
        params.put("login", dataSource.getEmailOrUsername());
        params.put("password", dataSource.getPassword());
        params.put("user_type", dataSource.getUserType());
        params.put("student_id", student_id);

        json.put("params", params);

        final String js = new JSONObject(json).toString();


        dataSource.getStandard_fee_collection(js, new DataListener() {
            @Override
            public void onSuccess(Object object) {

                Utils.hideProgress();

                ResultResponse resultResponse = (ResultResponse) object;

                Log.e(" Standard Fees  ", "onSuccess: " + new Gson().toJson(resultResponse));

                if (!NullCheckUtils.isEmpty(resultResponse) && !NullCheckUtils.isEmpty(resultResponse.getResult())) {

                    List<MiscellaneousFeesCollectionList> list = resultResponse.getResult().getMiscellaneousFeesCollectionList();
                    if (list.size() > 0)
                        recyclerFragment.updateData(list);
                    else
                        Toast.makeText(getContext(), "There is no data", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(getContext(), "There is no data", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFail(Throwable throwable) {
                Utils.hideProgress();
                Toast.makeText(getContext(), "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNetworkFailure() {

                Utils.hideProgress();

                Toast.makeText(getContext(), "Network error", Toast.LENGTH_SHORT).show();

            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItem(View view, Object o, int position) {


        MiscellaneousFeesCollectionList cha = (MiscellaneousFeesCollectionList) o;

        Toast.makeText(getContext(), "" + cha.getFeesName(), Toast.LENGTH_SHORT).show();

        cha.setName("sdfcsdfsdfsd");

        recyclerFragment.notifyDataChanged();

    }
}
