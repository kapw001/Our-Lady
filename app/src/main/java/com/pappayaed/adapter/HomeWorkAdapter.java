package com.pappayaed.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.pappayaed.R;
import com.pappayaed.common.AnimUtils;
import com.pappayaed.common.Utils;
import com.pappayaed.data.model.AssignmentSubLine;
import com.pappayaed.data.model.AttachmentFileId;
import com.pappayaed.data.model.HomeWork;
import com.pappayaed.ui.assignmentdownload.AttachmentListActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class HomeWorkAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HomeWork> list;
    private static RecyclerAdapterPositionClicked recyclerAdapterPositionClicked;
    private static Context context;
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    private static final int UNSELECTED = -1;

    private static RecyclerView recyclerView;
    private static int selectedItem = UNSELECTED;
    private int lastPosition = -1;


    public HomeWorkAdapter() {
        // uncomment the line below if you want to open only one row at a time
        // viewBinderHelper.setOpenOnlyOne(true);
    }

    public HomeWorkAdapter(Fragment context, List<HomeWork> list, RecyclerView recyclerView) {
        this.list = list;
        this.context = context.getContext();
        this.recyclerAdapterPositionClicked = (RecyclerAdapterPositionClicked) context;
        this.recyclerView = recyclerView;
        viewBinderHelper.setOpenOnlyOne(true);

    }

    public HomeWorkAdapter(Context context, List<HomeWork> list, RecyclerView recyclerView) {
        this.list = list;
        this.context = context;
        this.recyclerAdapterPositionClicked = (RecyclerAdapterPositionClicked) context;
        this.recyclerView = recyclerView;
        viewBinderHelper.setOpenOnlyOne(true);
    }

    public void updateList(List<HomeWork> list) {
        this.list = new ArrayList<>();
        this.list.addAll(list);
        notifyDataSetChanged();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homework_row, parent, false);
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
        final HomeWork object = list.get(position);

        final List<AttachmentFileId> list = object.getAttachmentFileIds() != null ? object.getAttachmentFileIds() : null;

        if (list != null && list.size() > 0) {
            ((RowViewHolder) holder).attachment.setVisibility(View.VISIBLE);
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
        }

//        ((RowViewHolder) holder).grade.setText(object.getGrade());
        ((RowViewHolder) holder).description.setText((Utils.getHtmlText(object.getDescription())));
        ((RowViewHolder) holder).subject.setText(object.getSubjectName());
        ((RowViewHolder) holder).submissiondate.setText(object.getDate());

        ((RowViewHolder) holder).txtDate.setText(Utils.getFullNameFromDate(Utils.convertStringToDate(object.getDate())));
//        ((RowViewHolder) holder).submissiondate.setText(Utils.getConvertedDate(object.getSubmissionDate().toString()));

//        ((RowViewHolder) holder).Bind(object);
//        ((RowViewHolder) holder).bind(position);


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
        private TextView grade, subject, submissiondate, description, attachmentcount, txtDate;
        private ImageView attachment;


        private Button accept, reject, cancel, change;

        public RowViewHolder(View itemView) {
            super(itemView);

            attachment = (ImageView) itemView.findViewById(R.id.attachment);

            grade = (TextView) itemView.findViewById(R.id.grade);
            description = (TextView) itemView.findViewById(R.id.description);
            submissiondate = (TextView) itemView.findViewById(R.id.submissiondate);
            subject = (TextView) itemView.findViewById(R.id.subject);
            txtDate = (TextView) itemView.findViewById(R.id.txtDate);


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
