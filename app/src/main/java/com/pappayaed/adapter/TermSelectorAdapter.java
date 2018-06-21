package com.pappayaed.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.pappayaed.R;
import com.pappayaed.common.AnimUtils;
import com.pappayaed.common.Utils;
import com.pappayaed.data.model.AttendanceList;

import java.util.ArrayList;
import java.util.List;


public class TermSelectorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> list;
    private static RecyclerAdapterPositionClicked recyclerAdapterPositionClicked;
    private static Context context;
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    private static int mSelectedPosition = 0;
    private int lastPosition = -1;
    public TermSelectorAdapter() {
        // uncomment the line below if you want to open only one row at a time
        // viewBinderHelper.setOpenOnlyOne(true);
    }

    public TermSelectorAdapter(Fragment context, List<String> list) {
        this.list = list;
        this.context = context.getContext();
        this.recyclerAdapterPositionClicked = (RecyclerAdapterPositionClicked) context;
        viewBinderHelper.setOpenOnlyOne(true);
        lastPosition = -1;
    }

    public TermSelectorAdapter(Context context, List<String> list) {
        this.list = list;
        this.context = context;
        this.recyclerAdapterPositionClicked = (RecyclerAdapterPositionClicked) context;
        viewBinderHelper.setOpenOnlyOne(true);
        lastPosition = -1;
    }

    public void updateList(List<String> list) {
        mSelectedPosition = 0;
        lastPosition = -1;
        this.list = new ArrayList<>();
        this.list.addAll(list);
        notifyDataSetChanged();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.term_row, parent, false);
        return new RowViewHolder(view);

    }

    public void saveStates(Bundle outState) {
        viewBinderHelper.saveStates(outState);
    }

    public void restoreStates(Bundle inState) {
        viewBinderHelper.restoreStates(inState);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        final String object = list.get(position);

        ((RowViewHolder) holder).termName.setText(object);

        if (position == mSelectedPosition) {
            ((RowViewHolder) holder).termName.setChecked(true);
        } else {
            ((RowViewHolder) holder).termName.setChecked(false);
        }

        ((RowViewHolder) holder).termName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSelectedPosition = position;
                notifyDataSetChanged();

                recyclerAdapterPositionClicked.position(position, v, object);

            }
        });

        if (position > lastPosition) {
            AnimUtils.setAnimSlideLeft(context, holder.itemView);
            lastPosition = position;
        }

    }


    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        return list.size();
    }


    private static class RowViewHolder extends RecyclerView.ViewHolder {

        private RadioButton termName;

        public RowViewHolder(View itemView) {
            super(itemView);

            termName = (RadioButton) itemView.findViewById(R.id.term_name);


        }


    }

    public interface RecyclerAdapterPositionClicked {
        void position(int pos, View view, String s);

        void onRefresh();
    }

    private static void showToat(View view) {

    }
}
