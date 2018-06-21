package com.pappayaed.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.pappayaed.R;
import com.pappayaed.common.Utils;
import com.pappayaed.data.model.TermFeesCollectionList;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class FeeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "FeeAdapter";
    private List<TermFeesCollectionList> list;
    private static RecyclerAdapterPositionClicked recyclerAdapterPositionClicked;
    private static Context context;
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    private static final int UNSELECTED = -1;

    public FeeAdapter() {
        // uncomment the line below if you want to open only one row at a time
        // viewBinderHelper.setOpenOnlyOne(true);
    }

    public FeeAdapter(Fragment context, List<TermFeesCollectionList> list) {
        this.list = list;
        this.context = context.getContext();
        this.recyclerAdapterPositionClicked = (RecyclerAdapterPositionClicked) context;
        viewBinderHelper.setOpenOnlyOne(true);

    }

    public FeeAdapter(Context context, List<TermFeesCollectionList> list) {
        this.list = list;
        this.context = context;
        this.recyclerAdapterPositionClicked = (RecyclerAdapterPositionClicked) context;
        viewBinderHelper.setOpenOnlyOne(true);
    }

    public void updateList(List<TermFeesCollectionList> list) {

        Log.e(TAG, "updateList: test " + list.size());
        this.list = new ArrayList<>();
        this.list.addAll(list);
        notifyDataSetChanged();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fee_row, parent, false);
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
        final TermFeesCollectionList object = list.get(position);


//

        ((RowViewHolder) holder).student_name.setText(object.getStudentName());
        ((RowViewHolder) holder).status.setText(Utils.capitalizeFirstLetter(object.getStatus()));
        ((RowViewHolder) holder).parent_name.setText(object.getParentName());
        ((RowViewHolder) holder).amount.setText(Utils.getDecimal(Double.valueOf(object.getAmount())));
        ((RowViewHolder) holder).grade_name.setText(object.getGradeName());
        ((RowViewHolder) holder).term_name.setVisibility(View.VISIBLE);
        ((RowViewHolder) holder).term_name.setText(object.getTermName());

        String d = object.getDueDate();

        Log.e(TAG, "onBindViewHolder: " + d);

        if (d != null && !d.equalsIgnoreCase("false"))
            d = Utils.convertDateToString(object.getDueDate(), "yyyy-MM-dd", "dd/MM/yyyy");
        else
            d = "";


        ((RowViewHolder) holder).due_date.setText(d);


    }


    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        return list.size();
    }


    private static class RowViewHolder extends RecyclerView.ViewHolder {
        private TextView student_name, status, parent_name, amount, grade_name, term_name, due_date;


        private ImageView arrowImg;

        public RowViewHolder(View itemView) {
            super(itemView);

            student_name = (TextView) itemView.findViewById(R.id.studentname);
            status = (TextView) itemView.findViewById(R.id.state);
            parent_name = (TextView) itemView.findViewById(R.id.parent_name);
            amount = (TextView) itemView.findViewById(R.id.amount);
            grade_name = (TextView) itemView.findViewById(R.id.grade_name);
            term_name = (TextView) itemView.findViewById(R.id.term_name);
            due_date = (TextView) itemView.findViewById(R.id.due_date);

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
