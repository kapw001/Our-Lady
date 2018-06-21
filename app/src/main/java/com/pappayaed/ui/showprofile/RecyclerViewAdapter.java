package com.pappayaed.ui.showprofile;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pappayaed.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.google.android.exoplayer2.mediacodec.MediaCodecInfo.TAG;
import static com.pappayaed.ui.showprofile.UserDetails.HEADER;
import static com.pappayaed.ui.showprofile.UserDetails.ROW;

/**
 * Created by yasar on 5/5/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<UserDetails> list;
    private RecyclerAdapterPositionClicked recyclerAdapterPositionClicked;
    private Context context;
    private Fragment fragment;

    public RecyclerViewAdapter(Context context, List<UserDetails> list) {
        this.list = list;
        this.context = context;
        this.recyclerAdapterPositionClicked = (RecyclerAdapterPositionClicked) context;

    }

    public RecyclerViewAdapter(Fragment context, List<UserDetails> list) {
        this.list = list;
        this.fragment = context;
        this.recyclerAdapterPositionClicked = (RecyclerAdapterPositionClicked) context;

    }

    public void updateList(List<UserDetails> list) {
        this.list = new ArrayList<>();
        this.list.addAll(list);
        notifyDataSetChanged();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case HEADER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header, parent, false);
                return new HeaderViewHolder(view);
            case ROW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_row_old, parent, false);
                return new RowViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final UserDetails object = list.get(position);
        if (object != null) {
            switch (object.getmType()) {
                case HEADER:
                    ((HeaderViewHolder) holder).mTitle.setText(object.getmTitle());
                    break;
                case ROW:

                    ((RowViewHolder) holder).mName.setText(object.getmName());
                    ((RowViewHolder) holder).mTitle.setText(object.getmTitle());


                    if (object.getmTitle().toLowerCase().contains("child ") || object.getmTitle().toLowerCase().contains("Attachment".toLowerCase())) {
                        ((RowViewHolder) holder).arrow.setVisibility(View.VISIBLE);
                        ((RowViewHolder) holder).profileImage.setVisibility(View.GONE);
//                        ((RowViewHolder) holder).profileImage.setImageBitmap(Utils.decodeBitmap(context, object.getProfileImage()));
//                        ((RowViewHolder) holder).profileImage.setPadding(0, 0, 16, 0);
                    } else {
                        ((RowViewHolder) holder).arrow.setVisibility(View.GONE);
//                        ((RowViewHolder) holder).profileImage.setVisibility(View.GONE);
                    }
                    ((RowViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

//                            recyclerAdapterPositionClicked.position(position);

                            if (object.getStudentList() instanceof StudentList) {
                                recyclerAdapterPositionClicked.position(position, object.getStudentList());
                            } else {
                                recyclerAdapterPositionClicked.position(position);
                                Log.e(TAG, "onClick: Studentlist class not found");
                            }

                        }
                    });

                    try {
                        Log.e(TAG, "onBindViewHolder: " + object.getmName().equalsIgnoreCase("Download"));
                        if (object.getmName().equalsIgnoreCase("Download ")) {
                            Log.e(TAG, "onBindViewHolder: " + object.getFileName() + object.getmType());
                            ((RowViewHolder) holder).mName.setText(object.getmName() + " " + object.getFileName());
                            ((RowViewHolder) holder).mName.setTextSize(12f);
                        }
                    } catch (NullPointerException e) {

                    }

//

                    break;
            }
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (list != null) {
            UserDetails object = list.get(position);
            if (object != null) {
                return object.getmType();
            }
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        return list.size();
    }

    private static class HeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.headertxt);
        }
    }

    private static class RowViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mName;
        private ImageView arrow;
        private CircleImageView profileImage;

        public RowViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.rowtitle);
            mName = (TextView) itemView.findViewById(R.id.profilename);
            arrow = (ImageView) itemView.findViewById(R.id.arrow);
            profileImage = (CircleImageView) itemView.findViewById(R.id.profile_image);
        }
    }

    public interface RecyclerAdapterPositionClicked {
        void position(int pos);

        void position(int pos, StudentList studentList);
    }
}
