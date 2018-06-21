package com.pappayaed.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.pappayaed.common.AnimUtils;
import com.pappayaed.R;
import com.pappayaed.ui.showprofile.StudentList;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class StudentFeeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<StudentList> list;
    private static RecyclerAdapterPositionClicked recyclerAdapterPositionClicked;
    private static Context context;
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    private static final int UNSELECTED = -1;
    private static RecyclerView recyclerView;
    private static int selectedItem = UNSELECTED;
    private int lastPosition = -1;
    public int selectedPosition = 0;

    public StudentFeeAdapter() {
        // uncomment the line below if you want to open only one row at a time
        // viewBinderHelper.setOpenOnlyOne(true);
    }

    public StudentFeeAdapter(Fragment context, List<StudentList> list, RecyclerView recyclerView) {
        lastPosition = -1;
        this.list = list;
        this.context = context.getContext();
        this.recyclerAdapterPositionClicked = (RecyclerAdapterPositionClicked) context;
        this.recyclerView = recyclerView;
        viewBinderHelper.setOpenOnlyOne(true);

    }

    public StudentFeeAdapter(Context context, List<StudentList> list, RecyclerView recyclerView) {
        lastPosition = -1;
        this.list = list;
        this.context = context;
        this.recyclerAdapterPositionClicked = (RecyclerAdapterPositionClicked) context;
        this.recyclerView = recyclerView;
        viewBinderHelper.setOpenOnlyOne(true);

    }

    public void updateList(List<StudentList> list) {
        lastPosition = -1;
        this.list = new ArrayList<>();
        this.list.addAll(list);
        notifyDataSetChanged();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_row, parent, false);
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

        final StudentList object = list.get(position);

        ((RowViewHolder) holder).name.setText(object.getName());

        selectedItem = position;

        if (selectedPosition == position) {
            list.get(position).setSelected(true);
        } else {
            list.get(position).setSelected(false);
        }

        if (object.isSelected()) {

            ((RowViewHolder) holder).profileImage.setBorderWidth(15);
            ((RowViewHolder) holder).profileImage.setBorderColor(object.getColor());

        } else {

            ((RowViewHolder) holder).profileImage.setBorderWidth(0);

        }

        ((RowViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedPosition = position;
                notifyDataSetChanged();
                recyclerAdapterPositionClicked.position(position, v, object);

            }
        });

        if (position > lastPosition) {
            AnimUtils.setAnimAlpha(context, holder.itemView);
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
        private TextView title, name;
        private CircleImageView profileImage;


        public RowViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.profilename);
            profileImage = (CircleImageView) itemView.findViewById(R.id.profile_image);
        }


    }

    public interface RecyclerAdapterPositionClicked {

        void position(int pos, View view, StudentList studentList);

        void onRefresh();

    }

    private static void showToat(View view) {
        Toast.makeText(view.getContext(), "" + view.getId(), Toast.LENGTH_SHORT).show();
    }
}
