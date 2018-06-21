package com.pappayaed.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.pappayaed.R;
import com.pappayaed.common.Utils;
import com.pappayaed.data.model.AssignmentSubLine;
import com.pappayaed.data.model.AttachmentFileId;
import com.pappayaed.ui.assignmentdownload.AttachmentListActivity;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class SubmissionListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<AssignmentSubLine> list;
    private static RecyclerAdapterPositionClicked recyclerAdapterPositionClicked;
    private static Context context;
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    private static final int UNSELECTED = -1;

    private static RecyclerView recyclerView;
    private static int selectedItem = UNSELECTED;


    public SubmissionListAdapter() {
        // uncomment the line below if you want to open only one row at a time
        // viewBinderHelper.setOpenOnlyOne(true);
    }

    public SubmissionListAdapter(Fragment context, List<AssignmentSubLine> list, RecyclerView recyclerView) {
        this.list = list;
        this.context = context.getContext();
        this.recyclerAdapterPositionClicked = (RecyclerAdapterPositionClicked) context;
        this.recyclerView = recyclerView;
        viewBinderHelper.setOpenOnlyOne(true);

    }

    public SubmissionListAdapter(Context context, List<AssignmentSubLine> list, RecyclerView recyclerView) {
        this.list = list;
        this.context = context;
        this.recyclerAdapterPositionClicked = (RecyclerAdapterPositionClicked) context;
        this.recyclerView = recyclerView;
        viewBinderHelper.setOpenOnlyOne(true);
    }

    public void updateList(List<AssignmentSubLine> list) {
        this.list = new ArrayList<>();
        this.list.addAll(list);
        notifyDataSetChanged();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.submission_row1, parent, false);
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
        final AssignmentSubLine object = list.get(position);

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

            ((RowViewHolder) holder).attachmentcount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), AttachmentListActivity.class);
                    intent.putExtra("assignmentlist", (Serializable) list);
                    v.getContext().startActivity(intent);
                }
            });

            ((RowViewHolder) holder).attachmentcount.setText(list.size() + "");
            ((RowViewHolder) holder).attachmentcount.setVisibility(View.VISIBLE);

        } else {
            ((RowViewHolder) holder).attachment.setVisibility(View.GONE);
            ((RowViewHolder) holder).attachmentcount.setVisibility(View.GONE);
        }

//        if (list == null)
//            ((RowViewHolder) holder).attachmentcount.setText("No attachment");

//        if (list != null && list.size() > 0) {
//            ((RowViewHolder) holder).attachment.setVisibility(View.VISIBLE);
//            ((RowViewHolder) holder).attachment.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(v.getContext(), AttachmentListActivity.class);
//                    intent.putExtra("assignmentlist", (Serializable) list);
//                    v.getContext().startActivity(intent);
//                }
//            });
//
//            ((RowViewHolder) holder).attachmentcount.setText(list.size() + "");
//            ((RowViewHolder) holder).attachmentcount.setVisibility(View.VISIBLE);
//
//        } else {
//            ((RowViewHolder) holder).attachment.setVisibility(View.GONE);
//            ((RowViewHolder) holder).attachmentcount.setVisibility(View.GONE);
//        }
//
//        ((RowViewHolder) holder).requestlayout.setVisibility(View.GONE);
//
//
//        if (((RowViewHolder) holder).requestlayout.isShown()) {
//            ((RowViewHolder) holder).requestlayout.setVisibility(View.GONE);
//            ((RowViewHolder) holder).cancellayout.setVisibility(View.GONE);
//            ((RowViewHolder) holder).acceptrejectlayout.setVisibility(View.GONE);
//        }
//
//        ((RowViewHolder) holder).linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                if (position > 0) {
//                    int p = position - 1;
//                    notifyItemChanged(p);
//                }
//
//                SessionManagenent sessionManagenent = SessionManagenent.getSessionManagenent();
//
//
//                if (((RowViewHolder) holder).requestlayout.isShown()) {
//                    ((RowViewHolder) holder).requestlayout.setVisibility(View.GONE);
////                    if (sessionManagenent.getSession().get(KEY_USERTYPE).toString().equalsIgnoreCase("Faculty")) {
//                    ((RowViewHolder) holder).acceptrejectlayout.setVisibility(View.GONE);
////                    } else {
//                    ((RowViewHolder) holder).cancellayout.setVisibility(View.GONE);
////                    }
//                } else if (object.getState().toString().toLowerCase().equalsIgnoreCase("Submitted".toLowerCase()) || object.getState().toString().toLowerCase().equalsIgnoreCase("Rejected".toLowerCase()) || object.getState().toString().toLowerCase().equalsIgnoreCase("Accepted".toLowerCase()) || object.getState().toString().toLowerCase().equalsIgnoreCase("Draft".toLowerCase())
//
//                        || object.getState().toString().toLowerCase().equalsIgnoreCase("Reject".toLowerCase())
//                        || object.getState().toString().toLowerCase().equalsIgnoreCase("Accept".toLowerCase())
//                        ) {
//                    ((RowViewHolder) holder).requestlayout.setVisibility(View.GONE);
////                    if (sessionManagenent.getSession().get(KEY_USERTYPE).toString().equalsIgnoreCase("Faculty")) {
//                    ((RowViewHolder) holder).acceptrejectlayout.setVisibility(View.GONE);
////                    } else {
//                    ((RowViewHolder) holder).cancellayout.setVisibility(View.GONE);
//
//                } else {
//
//                    ((RowViewHolder) holder).requestlayout.setVisibility(View.VISIBLE);
//                    if (sessionManagenent.getSession().get(KEY_USERTYPE).toString().equalsIgnoreCase("Faculty")) {
//                        ((RowViewHolder) holder).acceptrejectlayout.setVisibility(View.VISIBLE);
//                        ((RowViewHolder) holder).cancellayout.setVisibility(View.GONE);
//                    } else {
//                        ((RowViewHolder) holder).cancellayout.setVisibility(View.GONE);
//                        ((RowViewHolder) holder).acceptrejectlayout.setVisibility(View.GONE);
//                    }
//
//                    recyclerView.smoothScrollToPosition(position);
//
//
//                }
//
//
//                ((RowViewHolder) holder).accept.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(final View v) {
//                        try {
//                            update(object.getSubmissionId(), "accept");
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                });
//
//                ((RowViewHolder) holder).reject.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(final View v) {
//
//                        try {
//                            update(object.getSubmissionId(), "reject");
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//
//                ((RowViewHolder) holder).change.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(final View v) {
//
//                        try {
//                            update(object.getSubmissionId(), "change");
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//
//                ((RowViewHolder) holder).cancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(final View v) {
//                        try {
//                            update(object.getSubmissionId(), "cancel");
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                });
//
//
////                notifyDataSetChanged();
//
//                recyclerAdapterPositionClicked.position(position, v);
//
//            }
//        });
//
        ((RowViewHolder) holder).grade.setText(object.getGrade());
        ((RowViewHolder) holder).description.setText((Utils.getHtmlText(object.getDescription())));
        ((RowViewHolder) holder).subject.setText(object.getSubject());
        ((RowViewHolder) holder).submissiondate.setText(object.getSubmissiondate());
//        ((RowViewHolder) holder).submissiondate.setText(Utils.getConvertedDate(object.getSubmissionDate().toString()));

//        ((RowViewHolder) holder).Bind(object);
//        ((RowViewHolder) holder).bind(position);

    }


    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        return list.size();
    }


    private static class RowViewHolder extends RecyclerView.ViewHolder {
        private TextView grade, subject, submissiondate, description, attachmentcount;
        private FrameLayout linearLayout;
        private ImageView attachment;

        private int position;
        private RelativeLayout requestlayout;
        private LinearLayout acceptrejectlayout;
        private LinearLayout cancellayout;


        private Button accept, reject, cancel, change;

        public RowViewHolder(View itemView) {
            super(itemView);

            attachment = (ImageView) itemView.findViewById(R.id.attachment);
            requestlayout = (RelativeLayout) itemView.findViewById(R.id.requestlayout);
            acceptrejectlayout = (LinearLayout) itemView.findViewById(R.id.acceptrejectlayout);
            cancellayout = (LinearLayout) itemView.findViewById(R.id.cancellayout);
            linearLayout = (FrameLayout) itemView.findViewById(R.id.layoutrow);
            grade = (TextView) itemView.findViewById(R.id.grade);
            description = (TextView) itemView.findViewById(R.id.description);
            submissiondate = (TextView) itemView.findViewById(R.id.submissiondate);
            subject = (TextView) itemView.findViewById(R.id.subject);
            attachmentcount = (TextView) itemView.findViewById(R.id.attachmentcount);


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
