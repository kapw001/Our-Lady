package com.pappayaed.recyclerviewrows;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.RecyclerViewRow;
import com.pappayaed.adapter.HomeWorkAdapter;
import com.pappayaed.common.Utils;
import com.pappayaed.data.model.AttachmentFileId;
import com.pappayaed.data.model.HomeWork;
import com.pappayaed.ui.assignmentdownload.AttachmentListActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yasar on 23/4/18.
 */

public class HomeWorkRow extends LinearLayout implements RecyclerViewRow<HomeWork> {


    Unbinder unbinder;
    @BindView(R.id.txtDate)
    TextView txtDate;
    @BindView(R.id.studentname)
    TextView studentname;
    @BindView(R.id.grade)
    TextView grade;
    @BindView(R.id.subject)
    TextView subject;
    @BindView(R.id.sublay)
    LinearLayout sublay;
    @BindView(R.id.submissiondate)
    TextView submissiondate;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.attachment)
    ImageView attachment;

    public HomeWorkRow(Context context) {
        super(context);

    }

    public HomeWorkRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();


//        inflate(getContext(), R.layout.homework_row, this);

        unbinder = ButterKnife.bind(this);

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        unbinder.unbind();
    }

    @Override
    public void showData(HomeWork object) {

        final List<AttachmentFileId> list = object.getAttachmentFileIds() != null ? object.getAttachmentFileIds() : null;

        if (list != null && list.size() > 0) {
            attachment.setVisibility(View.VISIBLE);
            attachment.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), AttachmentListActivity.class);
                    intent.putExtra("assignmentlist", (Serializable) list);
                    v.getContext().startActivity(intent);
                }
            });


        } else {
            attachment.setVisibility(View.GONE);
        }

//        ((RowViewHolder) holder).grade.setText(object.getGrade());
        description.setText((Utils.getHtmlText(object.getDescription())));
        subject.setText(object.getSubjectName());
        submissiondate.setText(object.getDate());

        txtDate.setText(Utils.getFullNameFromDate(Utils.convertStringToDate(object.getDate())));

    }

    @Override
    public void setOnItemClickListener(OnClickListener listener) {

    }

    @Override
    public void setOnItemLongClickListener(OnLongClickListener listener) {

    }


}
