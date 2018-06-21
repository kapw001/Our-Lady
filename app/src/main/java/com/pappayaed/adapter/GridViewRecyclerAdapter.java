package com.pappayaed.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pappayaed.R;

import com.pappayaed.common.Utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yasar on 2/8/17.
 */

public class GridViewRecyclerAdapter extends RecyclerView.Adapter<GridViewRecyclerAdapter.MyViewHolder> {

    public int selectedPosition = 0;
    private OnItemClickListener onItemClickListener;

    private Fragment studentTableFragment;

    public GridViewRecyclerAdapter(Fragment studentTableFragment, Context context, List<String> list) {
        this.context = context;
        this.list = list;
        this.onItemClickListener = (OnItemClickListener) studentTableFragment;
    }

    private Context context;
    private List<String> list;

    public void update(List<String> list) {
//        this.list = new ArrayList<>();
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final String name = list.get(position);

        try {
            holder.month.setText(Utils.getMonthByName(name));
            holder.daysname.setText(Utils.getDaysByName(name));
            holder.daysnumber.setText(Utils.getDaysByNumber(name));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (selectedPosition == position) {
            holder.itemView.setBackgroundColor(Color.parseColor("#FF5722"));
            holder.month.setTextColor(Color.parseColor("#ffffff"));
            holder.daysnumber.setTextColor(Color.parseColor("#ffffff"));
            holder.daysname.setTextColor(Color.parseColor("#ffffff"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.month.setTextColor(Color.parseColor("#000000"));
            holder.daysnumber.setTextColor(Color.parseColor("#000000"));
            holder.daysname.setTextColor(Color.parseColor("#000000"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onItemClickListener.position(name);
                selectedPosition = position;
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView month, daysnumber, daysname;// init the item view's

        public MyViewHolder(View itemView) {
            super(itemView);
// get the reference of item view's
            month = (TextView) itemView.findViewById(R.id.month);
            daysname = (TextView) itemView.findViewById(R.id.daysname);
            daysnumber = (TextView) itemView.findViewById(R.id.daysnumber);
        }
    }

    public interface OnItemClickListener {
        void position(String itemName);
    }
}