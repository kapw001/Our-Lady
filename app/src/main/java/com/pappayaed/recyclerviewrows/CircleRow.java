package com.pappayaed.recyclerviewrows;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.RecyclerViewRow;
import com.pappayaed.adapter.CircularAdapter;
import com.pappayaed.common.ActivityUtils;
import com.pappayaed.common.Utils;
import com.pappayaed.data.model.AttachmentFileId;
import com.pappayaed.data.model.Circular;
import com.pappayaed.ui.assignmentdownload.AttachmentListActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yasar on 23/4/18.
 */

public class CircleRow extends LinearLayout implements RecyclerViewRow<Circular> {


    @BindView(R.id.txtDate)
    TextView txtDate;
    @BindView(R.id.studentname)
    TextView studentname;
    @BindView(R.id.grade)
    TextView grade;
    @BindView(R.id.fullDate)
    TextView fullDate;
    @BindView(R.id.sublay)
    LinearLayout sublay;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.attachment)
    ImageView attachment;

    Unbinder unbinder;

    public CircleRow(Context context) {
        super(context);

    }

    public CircleRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

//        inflate(getContext(), R.layout.circular_row, this);

        unbinder = ButterKnife.bind(this);


    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        unbinder.unbind();

    }

    @Override
    public void showData(Circular object) {


        String s = object.getDescription();

        description.setText(Utils.getHtmlText(s));
        txtDate.setText(Utils.getFullNameFromDate(Utils.convertStringToDate(object.getDate())));

        String d = object.getDate();

        fullDate.setText(Utils.getHtmlText(d));


        final List<AttachmentFileId> list = object.getAttachmentFileIds() != null ? object.getAttachmentFileIds() : null;

        if (list != null && list.size() > 0) {
            attachment.setVisibility(View.VISIBLE);

//            ((RowViewHolder) holder).attachment.setAlpha(.5f);

            attachment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent();
//                    intent.putExtra("assignmentlist", (Serializable) list);
//                    v.getContext().startActivity(intent);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("assignmentlist", (Serializable) list);

                    ActivityUtils.startActivity(getContext(), AttachmentListActivity.class, bundle);

                }
            });


        } else {
            attachment.setVisibility(View.GONE);
//            ((RowViewHolder) holder).attachment.setAlpha(.2f);
        }


    }

    @Override
    public void setOnItemClickListener(OnClickListener listener) {

    }

    @Override
    public void setOnItemLongClickListener(OnLongClickListener listener) {

    }

}
