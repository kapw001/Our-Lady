package com.pappayaed.ui.whereisstudent;

import android.content.ContentResolver;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pappayaed.R;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WhereIsStudentActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = "WhereIsStudentActivity";

    private static final int SYSTEM_ALERT_WINDOW_PERMISSION = 12;
    @BindView(R.id.stopRingtone)
    LinearLayout stopRingtone;
    private LatLng HAMBURG = new LatLng(0, 0);
//    static final LatLng KIEL = new LatLng(53.551, 9.993);

    private TextView mAddress, studentName;

    private void askPermission() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, SYSTEM_ALERT_WINDOW_PERMISSION);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_where_is_student);
        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {


            getSupportActionBar().setTitle("Your child invoked panic alert");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        }


        stopRingtone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRingtone();
            }
        });


        mAddress = (TextView) findViewById(R.id.address);
        studentName = (TextView) findViewById(R.id.studentName);

//        mAddress.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
//                    startService(new Intent(WhereIsStudentActivity.this, FloatingViewService.class));
//                    finish();
//                } else if (Settings.canDrawOverlays(getApplicationContext())) {
//                    startService(new Intent(WhereIsStudentActivity.this, FloatingViewService.class));
//                    finish();
//                } else {
//                    askPermission();
//                    Toast.makeText(WhereIsStudentActivity.this, "You need System Alert Window Permission to do this", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });

        if (getIntent().getExtras() != null) {

            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.e(TAG, "Key: " + key + " Value: " + value);
            }
        }

        if (getIntent() != null && getIntent().getExtras() != null) {

//            String alert = getIntent().getStringExtra("panicAlert");

//            if (alert != null) {
//
//                try {
//                    JSONObject json = new JSONObject(alert);

            String student_Name = getIntent().getExtras().getString("student_name");
            Double lat = Double.valueOf(getIntent().getExtras().getString("lat"));
            Double lng = Double.valueOf(getIntent().getExtras().getString("lng"));

            Log.e(TAG, "onCreate: " + student_Name + "  " + lat + "  " + lng);

            HAMBURG = new LatLng(lat, lng);

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);


            loadAddress(HAMBURG.latitude, HAMBURG.longitude, student_Name);


//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//        }

        }


    }


    private void stopRingtone() {

        try {
            Uri alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                    + "://" + getPackageName() + "/raw/panic");
            Ringtone r = RingtoneManager.getRingtone(this, alarmSound);
            if (r.isPlaying()) {

                r.stop();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        ;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        // create marker
        MarkerOptions marker = new MarkerOptions().position(HAMBURG).title("Here your child");

// adding marker
        googleMap.addMarker(marker);

        CameraPosition cameraPosition = new CameraPosition.Builder().target(HAMBURG).zoom(12).build();

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


    }


    private void loadAddress(double LATITUDE, double LONGITUDE, String student_Name) {


        String address = getCompleteAddressString(LATITUDE, LONGITUDE);

        studentName.setText(student_Name);

        mAddress.setText(address);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {

            super.onBackPressed();

            return true;

        } else
            return super.onOptionsItemSelected(item);


    }

    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "No Geodecode";
        if (Geocoder.isPresent()) {


            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
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
