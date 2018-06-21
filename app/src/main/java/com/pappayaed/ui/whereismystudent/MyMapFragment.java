package com.pappayaed.ui.whereismystudent;


import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pappayaed.R;
import com.pappayaed.base.BaseFragment;
import com.pappayaed.common.Utils;
import com.pappayaed.data.listener.DataListener;
import com.pappayaed.ui.showprofile.StudentList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyMapFragment extends BaseFragment implements OnMapReadyCallback {

    static LatLng KIEL = new LatLng(0.0, 0.0);

    private static final String TAG = "MyMapFragment";
    @BindView(R.id.address)
    TextView address;
    Unbinder unbinder;
    private GoogleMap googleMap;

    public MyMapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_map, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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

        dataSource.whereisstudent(js, new DataListener() {
            @Override
            public void onSuccess(Object object) {

                Log.e(TAG, "onSuccess: location " + object.toString());

                Utils.hideProgress();

                try {
                    JSONObject jsonObject = new JSONObject(object.toString());
                    JSONObject result = jsonObject.getJSONObject("result");

                    JSONArray student_location_ids = result.getJSONArray("student_location_ids");

                    JSONObject jsonObject1 = student_location_ids.getJSONObject(0);

                    Double lat = jsonObject1.getDouble("latitude");
                    Double lng = jsonObject1.getDouble("longtitude");

                    Log.e(TAG, "onSuccess: lat and lng " + lat + "         " + lng);

                    if (googleMap != null) {

                        LatLng KIEL1 = new LatLng(lat, lng);

                        loadAddress(lat, lng, "");

                        MarkerOptions marker = new MarkerOptions().position(KIEL1).title("Here your child");

// adding marker
                        googleMap.addMarker(marker);

                        CameraPosition cameraPosition = new CameraPosition.Builder().target(KIEL1).zoom(12).build();

                        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                    }


                } catch (JSONException e) {
                    e.printStackTrace();

                    Toast.makeText(getActivity(), "There is no location for this child", Toast.LENGTH_SHORT).show();

                    address.setText("There is no address");

                }


            }

            @Override
            public void onFail(Throwable throwable) {
                Utils.hideProgress();
            }

            @Override
            public void onNetworkFailure() {
                Utils.hideProgress();
            }
        });


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        // create marker
        MarkerOptions marker = new MarkerOptions().position(KIEL).title("Here your child");

// adding marker
        googleMap.addMarker(marker);

        CameraPosition cameraPosition = new CameraPosition.Builder().target(KIEL).zoom(12).build();

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void loadAddress(double LATITUDE, double LONGITUDE, String student_Name) {


        String address1 = getCompleteAddressString(LATITUDE, LONGITUDE);


        address.setText(address1);


    }

    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "No Geodecode";
        if (Geocoder.isPresent()) {


            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
            try {
                List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
                if (addresses != null) {
                    Address returnedAddress = addresses.get(0);
                    StringBuilder strReturnedAddress = new StringBuilder("");
                    strReturnedAddress.append("Address is : ");

                    for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                        strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                    }
                    strAdd = strReturnedAddress.toString();
                    Log.w("My Current loction address", strReturnedAddress.toString());
                } else {
                    Log.w("My Current loction address", "No Address returned!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.w("My Current loction address", "Canont get Address!");
            }
        }

        return strAdd;
    }
}
