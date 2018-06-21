package com.pappayaed.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.pappayaed.R;
import com.pappayaed.common.AnimUtils;
import com.pappayaed.common.Utils;
import com.pappayaed.data.model.AttachmentFileId;
import com.pappayaed.data.model.Circular;
import com.pappayaed.ui.assignmentdownload.AttachmentListActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.holder.AnimateViewHolder;


public class CircularAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "FeeAdapter";
    private List<Circular> list;
    private static RecyclerAdapterPositionClicked recyclerAdapterPositionClicked;
    private static Context context;
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    private static final int UNSELECTED = -1;
    private int lastPosition = -1;

    public CircularAdapter() {
        // uncomment the line below if you want to open only one row at a time
        // viewBinderHelper.setOpenOnlyOne(true);
    }

    public CircularAdapter(Fragment context, List<Circular> list) {
        this.list = list;
        this.context = context.getContext();
        if (context instanceof RecyclerAdapterPositionClicked)
            this.recyclerAdapterPositionClicked = (RecyclerAdapterPositionClicked) context;
        else Log.e(TAG, "CircularAdapter: class cast exception");
        viewBinderHelper.setOpenOnlyOne(true);

    }

    public CircularAdapter(Context context, List<Circular> list) {
        this.list = list;
        this.context = context;
        this.recyclerAdapterPositionClicked = (RecyclerAdapterPositionClicked) context;
        viewBinderHelper.setOpenOnlyOne(true);
    }

    public void updateList(List<Circular> list) {

        Log.e(TAG, "updateList: test " + list.size());
        this.list = new ArrayList<>();
        this.list.addAll(list);
        notifyDataSetChanged();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.circular_row, parent, false);
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
        final Circular object = list.get(position);

//        String s = "<b>Description: </b> " + object.getDescription();
        String s = object.getDescription();

        ((RowViewHolder) holder).description.setText(Utils.getHtmlText(s));
        ((RowViewHolder) holder).txtDate.setText(Utils.getFullNameFromDate(Utils.convertStringToDate(object.getDate())));

        String d = object.getDate();

        ((RowViewHolder) holder).fullDate.setText(Utils.getHtmlText(d));


        final List<AttachmentFileId> list = object.getAttachmentFileIds() != null ? object.getAttachmentFileIds() : null;

        if (list != null && list.size() > 0) {
            ((RowViewHolder) holder).attachment.setVisibility(View.VISIBLE);

//            ((RowViewHolder) holder).attachment.setAlpha(.5f);

            ((RowViewHolder) holder).attachment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), AttachmentListActivity.class);
                    intent.putExtra("assignmentlist", (Serializable) list);
                    v.getContext().startActivity(intent);
                }
            });


        } else {
            ((RowViewHolder) holder).attachment.setVisibility(View.GONE);
//            ((RowViewHolder) holder).attachment.setAlpha(.2f);
        }


        if (position > lastPosition) {
            AnimUtils.setAnimTranslationY(context, holder.itemView);
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

        private TextView description;
        private TextView fullDate;
        private TextView txtDate;
        private ImageView attachment;

        public RowViewHolder(View itemView) {
            super(itemView);

            description = (TextView) itemView.findViewById(R.id.description);
            fullDate = (TextView) itemView.findViewById(R.id.fullDate);
            txtDate = (TextView) itemView.findViewById(R.id.txtDate);
            attachment = (ImageView) itemView.findViewById(R.id.attachment);


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
