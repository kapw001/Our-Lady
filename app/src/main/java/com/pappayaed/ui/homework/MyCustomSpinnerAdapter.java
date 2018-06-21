package com.pappayaed.ui.homework;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.pappayaed.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yasar on 19/6/18.
 */

public class MyCustomSpinnerAdapter extends ArrayAdapter<String> {

    private List<String> list;
    private Context context;
    private CCSpinner spinner;

    public MyCustomSpinnerAdapter(Context context, int textViewResourceId,
                                  ArrayList<String> objects, CCSpinner spinner) {
        super(context, textViewResourceId, objects);

        this.context = context;
        this.list = objects;
        this.spinner = spinner;

        // TODO Auto-generated constructor stub
    }

    public void updateList(List<String> list) {

        this.list = new ArrayList<>();
        this.list.addAll(list);
        notifyDataSetChanged();

    }


    @Override
    public View getDropDownView(final int position, View convertView,
                                ViewGroup parent) {
        // TODO Auto-generated method stub

        View v = getCustomView(position, convertView, parent);

        v.setBackgroundColor(Color.WHITE);

//            LayoutInflater inflater = getLayoutInflater();
//            View v = inflater.inflate(R.layout.simple_spinner_dropdown_item, parent, false);
        TextView label = (TextView) v.findViewById(R.id.text1);
        RelativeLayout.LayoutParams llp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        llp.setMargins(0, 8, 10, 8);
        label.setLayoutParams(llp);
        label.setTextColor(Color.BLACK);
        label.setBackground(null);
//        label.setGravity(Gravity.CENTER);
        label.setBackgroundDrawable(null);
        label.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        label.setSingleLine(false);
        v.setOnClickListener(v1 -> {

            spinner.setSelection(position);
            spinner.onDetachedFromWindow();


        });


        return v;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        return getCustomView(position, convertView, parent);
    }


    public View getCustomView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        //return super.getView(position, convertView, parent);

        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.spinnerlayout, parent, false);
        TextView label = (TextView) row.findViewById(R.id.text1);
//        label.setTextColor(Color.WHITE);
//            RelativeLayout.LayoutParams llp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//            llp.setMargins(50, 0, 0, 0);
//            label.setLayoutParams(llp);
        label.setText(list.get(position));
        label.setSingleLine(true);

//            Toast.makeText(MainActivity.this, "First Time Called", Toast.LENGTH_SHORT).show();
        return row;
    }


    @Override
    public int getCount() {
        return list.size();
    }
}