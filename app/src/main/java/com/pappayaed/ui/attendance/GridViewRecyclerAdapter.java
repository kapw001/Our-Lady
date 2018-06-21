package com.pappayaed.ui.attendance;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pappayaed.R;
import com.pappayaed.data.model.AttendanceSheetList;
import com.pappayaed.ui.attendance.model.ClassName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yasar on 18/6/18.
 */

public class GridViewRecyclerAdapter extends RecyclerView.Adapter<GridViewRecyclerAdapter.MyViewHolder> {

    public int selectedPosition = 0;
    private OnItemClickListener onItemClickListener;

    private Fragment fragment;

    public GridViewRecyclerAdapter(Fragment fragment, Context context, List<AttendanceSheetList> list) {
        this.context = context;
        this.list = list;
        this.onItemClickListener = (OnItemClickListener) fragment;
    }

    private Context context;
    private List<AttendanceSheetList> list;

    public void update(List<AttendanceSheetList> list) {
//        this.list = new ArrayList<>();
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_classname, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final AttendanceSheetList className = list.get(position);


        holder.name.setText(className.getSectionName());

        if (selectedPosition == position) {
            holder.name.setTextColor(ContextCompat.getColor(context, R.color.pink));

        } else {
            holder.name.setTextColor(Color.parseColor("#000000"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onItemClickListener.position(className, position);
                selectedPosition = position;
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateData(List<AttendanceSheetList> list) {

        this.list = new ArrayList<>();
        this.list.addAll(list);
        notifyDataSetChanged();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;// init the item view's

        public MyViewHolder(View itemView) {
            super(itemView);
// get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.name);

        }
    }

    public interface OnItemClickListener {
        void position(AttendanceSheetList itemName, int position);
    }
}