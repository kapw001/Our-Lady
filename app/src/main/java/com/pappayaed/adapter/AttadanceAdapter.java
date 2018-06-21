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
import android.widget.TextView;
import android.widget.Toast;

import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.pappayaed.R;
import com.pappayaed.common.AnimUtils;
import com.pappayaed.common.Utils;
import com.pappayaed.data.model.AttendanceList;

import java.util.ArrayList;
import java.util.List;


public class AttadanceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<AttendanceList> list;
    private static RecyclerAdapterPositionClicked recyclerAdapterPositionClicked;
    private static Context context;
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    private static final int UNSELECTED = -1;
    private int lastPosition = -1;

    public AttadanceAdapter() {
        // uncomment the line below if you want to open only one row at a time
        // viewBinderHelper.setOpenOnlyOne(true);
    }

    public AttadanceAdapter(Fragment context, List<AttendanceList> list) {
        this.list = list;
        this.context = context.getContext();
        this.recyclerAdapterPositionClicked = (RecyclerAdapterPositionClicked) context;
        viewBinderHelper.setOpenOnlyOne(true);
        lastPosition = -1;
    }

    public AttadanceAdapter(Context context, List<AttendanceList> list) {
        this.list = list;
        this.context = context;
        this.recyclerAdapterPositionClicked = (RecyclerAdapterPositionClicked) context;
        viewBinderHelper.setOpenOnlyOne(true);
        lastPosition = -1;
    }

    public void updateList(List<AttendanceList> list) {
        lastPosition = -1;
        this.list = new ArrayList<>();
        this.list.addAll(list);
        notifyDataSetChanged();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_row, parent, false);
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
        final AttendanceList object = list.get(position);


        if (object.getRemark() == null || object.getRemark().length() <= 0 || object.getRemark().equalsIgnoreCase("false")) {
            ((RowViewHolder) holder).remark.setText("There is no remark");
        } else {
            ((RowViewHolder) holder).remark.setText(object.getRemark());
        }

        if (object.getPresentMorning()) {

            ((RowViewHolder) holder).present_morning.setText("Present");
        } else {
            ((RowViewHolder) holder).present_morning.setText("Absent");
        }

        String d = Utils.convertDateGivenFormat(Utils.convertStringToDate1(object.getAttendanceDate()));

        ((RowViewHolder) holder).attendance_date.setText(d);

        ((RowViewHolder) holder).txtDate.setText(Utils.getFullNameFromDate(Utils.convertStringToDate1(object.getAttendanceDate())));

        ((RowViewHolder) holder).layColor.setBackgroundColor(object.getColor());

        if (position > lastPosition) {
            AnimUtils.setAnim(context, holder.itemView);
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
        private TextView attendance_date, present_morning, remark, txtDate;


        private LinearLayout layColor;
        private ImageView arrowImg;

        public RowViewHolder(View itemView) {
            super(itemView);

            remark = (TextView) itemView.findViewById(R.id.remark);
            txtDate = (TextView) itemView.findViewById(R.id.txtDate);
            attendance_date = (TextView) itemView.findViewById(R.id.attendance_date);
            present_morning = (TextView) itemView.findViewById(R.id.present_morning);

            layColor = (LinearLayout) itemView.findViewById(R.id.layColor);


        }


    }

    public interface RecyclerAdapterPositionClicked {
        void position(int pos, View view);

        void onRefresh();
    }

    private static void showToat(View view) {
        Toast.makeText(view.getContext(), "" + view.getId(), Toast.LENGTH_SHORT).show();
    }
}
