package com.pappayaed.RecyclerViewCommon;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pappayaed.common.AnimUtils;
import com.pappayaed.data.model.AttendanceLine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yasar on 28/3/18.
 */

public class RecyclerViewAdapterMultiView<T> extends RecyclerView.Adapter<RecyclerViewAdapterMultiView.ViewHolder> {

    private List<T> mDataset;
    private OnRecyclerViewItemClickListener<T> onRecyclerViewItemClickListener;
    private OnRecyclerViewLongItemClickListener<T> onItemLongClickListener;
    private int lastPosition = -1;

    public RecyclerViewAdapterMultiView(List<T> measurements) {

        this.mDataset = measurements;

    }

    public void updateData(List<T> mDataset) {

        this.mDataset = new ArrayList<>();
        this.mDataset.addAll(mDataset);
        notifyDataSetChanged();

    }

    public void updateData(T attendanceLine, int lastPosition) {

        this.mDataset.set(lastPosition, attendanceLine);
        notifyItemChanged(lastPosition);

    }


    public void setOnItemClickListener(OnRecyclerViewItemClickListener<T> onItemClickListener) {

        this.onRecyclerViewItemClickListener = onItemClickListener;

    }

    public void setOnItemLongClickListener(OnRecyclerViewLongItemClickListener<T> onItemLongClickListener) {

        this.onItemLongClickListener = onItemLongClickListener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int position = viewType;

        ViewLayout viewLayout = (ViewLayout) mDataset.get(position);


        RecyclerViewRow<T> row = (RecyclerViewRow<T>) LayoutInflater.from(parent.getContext()).inflate(viewLayout.getLayoutRes(), parent, false);
        ViewHolder vh = new ViewHolder(row);
        return vh;

    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mRow.showData(mDataset.get(position));


        if (onRecyclerViewItemClickListener != null) {
            holder.mRow.setOnItemClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecyclerViewItemClickListener.onItemClick(v, mDataset.get(position), position);

                }
            });
        }

        if (onItemLongClickListener != null) {
            holder.mRow.setOnItemLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    onItemLongClickListener.onItemLongClick(v, mDataset.get(position), position);

                    return false;
                }
            });
        }
        if (position > lastPosition) {
            AnimUtils.setAnimSlideLeft(holder.itemView.getContext(), holder.itemView);
            lastPosition = position;
        }


    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public RecyclerViewRow mRow;

        public ViewHolder(RecyclerViewRow itemView) {
            super((View) itemView);
            mRow = itemView;
        }
    }

    public interface OnRecyclerViewItemClickListener<T> {

        void onItemClick(View view, T t, int position);
    }


    public interface OnRecyclerViewLongItemClickListener<T> {

        void onItemLongClick(View view, T t, int position);

    }

}