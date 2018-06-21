package com.pappayaed.ui.more;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pappayaed.R;
import com.pappayaed.base.BaseFragment;
import com.pappayaed.common.ActivityUtils;
import com.pappayaed.common.Utils;
import com.pappayaed.ui.login.LoginActivity;
import com.pappayaed.ui.parentprofile.ParentActivity;
import com.pappayaed.ui.showprofile.ProfileActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yasar on 2/5/17.
 */

public class MoreFragment extends BaseFragment implements IMoreView {


    private CircleImageView profileimage;
    private TextView profilename, usertype;
    private RelativeLayout logout;
    private RelativeLayout profileGo;

    private static final String TAG = "MoreFragment";

    private IMorePresenter iMorePresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.more_fragment, container, false);

//        profileimage = view.findViewById(R.id.profile_image);
//        profilename = view.findViewById(R.id.profilename);
//        usertype = view.findViewById(R.id.usertype);
        logout = view.findViewById(R.id.logout);
        profileGo = view.findViewById(R.id.goprofile);

        profileGo.setOnClickListener(onClickListener);
        logout.setOnClickListener(onClickListener);

        iMorePresenter = new MorePresenter(this, dataSource);

        iMorePresenter.displayProfile();

        return view;
    }


    @Override
    public void displayProfile(String profileName, String userType, String profileImage) {


//        profilename.setText(profileName);
//        usertype.setText(userType);
//
//        Bitmap imagebmp = Utils.decodeBitmap(getActivity(), profileImage);
//        profileimage.setImageBitmap(imagebmp);

    }

    @Override
    public void gotoProfileActivity() {


        ActivityUtils.startActivity(getActivity(), ParentActivity.class, new Bundle());

//        Intent in = new Intent(getActivity(), ParentActivity.class);
////        View pimage = profileimage;
////        View pname = profilename;
////        Pair<View, String> pair1 = Pair.create(pimage, "profile_image");
////        Pair<View, String> pair2 = Pair.create(pname, "profilename");
////        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), pair1, pair2);
////        startActivity(in, optionsCompat.toBundle());
//        startActivity(in);

    }

    @Override
    public void logout() {

        startActivity(new Intent(getActivity(), LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.goprofile:

                    iMorePresenter.gotoProfileActivity();

                    break;

                case R.id.logout:

                    iMorePresenter.logout();

                    break;

            }

        }
    };


}
