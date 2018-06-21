package com.pappayaed.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;

import com.pappayaed.R;
import com.vlonjatg.progressactivity.ProgressLayout;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by yasar on 2/5/18.
 */

public class ActivityUtils {

    private static final String TAG = "ActivityUtils";

    public static void startActivity(Activity packageContext, Class<?> cls, Bundle bundle) {

        Intent intent = getIntent(packageContext, cls, bundle);
        packageContext.startActivity(intent);

        packageContext.overridePendingTransition(R.anim.slide_in_left, R.anim.fadeout);

    }

    public static void startActivity(Context packageContext, Class<?> cls, Bundle bundle) {

        Intent intent = getIntent(packageContext, cls, bundle);
        packageContext.startActivity(intent);

        if (packageContext instanceof Activity)
            ((Activity) packageContext).overridePendingTransition(R.anim.slide_in_left, R.anim.fadeout);

    }

    public static void exitActivityAnimation(Activity activity) {
        activity.overridePendingTransition(R.anim.fadein, R.anim.slide_out_right);
    }

    public static Intent getIntent(Context packageContext, Class<?> cls, Bundle bundle) {

        Intent intent = new Intent(packageContext, cls);
        intent.putExtras(bundle);

        return intent;
    }

    public static Intent getIntentWithAction(Context packageContext, Class<?> cls, Bundle bundle, String action) {

        Intent intent = new Intent(packageContext, cls);
        intent.setAction(action);
        intent.putExtras(bundle);

        return intent;
    }


    public static void setupExitWindowAnimations(Activity context) {

        Slide slide = (Slide) TransitionInflater.from(context).inflateTransition(R.transition.activity_slide);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.getWindow().setExitTransition(slide);
        }
    }

    public static void setupEnterWindowAnimations(Activity context) {

        Fade fade = (Fade) TransitionInflater.from(context).inflateTransition(R.transition.activity_fade);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.getWindow().setExitTransition(fade);
        }
    }


    public static SpannableString getNoInternetMessage(final Context mContext) {
        SpannableString spannableString = new SpannableString("No Internet connection. Enable Wi-Fi or Cellular Mobile data, then try again.");
        ClickableSpan spanWiFi = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                //to enable wifi
                try {
                    mContext.getApplicationContext().startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS).setFlags(FLAG_ACTIVITY_NEW_TASK));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        ClickableSpan spanMobile = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                //to enable mobile data
                try {
                    mContext.getApplicationContext().startActivity(new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS).setFlags(FLAG_ACTIVITY_NEW_TASK));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        spannableString.setSpan(spanWiFi, 31, 36, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), 31, 36, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(spanMobile, 40, 60, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), 40, 60, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }


    public static void progressBarStateCall(ProgressLayout progressLayout, int drawable, String state, View.OnClickListener errorListener) {

        if (progressLayout != null) {
            switch (state.toUpperCase()) {
                case "LOADING":
                    progressLayout.showLoading();
                    break;
                case "EMPTY":
                    progressLayout.showEmpty(R.drawable.nodata,
                            "There is no data",
                            "");
                    break;
                case "ERROR":
                    progressLayout.showError(drawable,
                            "Something went wrong",
                            "",
                            "Try Again", errorListener);

                    break;

                case "NOINTERNET":
                    progressLayout.showError(drawable,
                            "No Connection",
                            "We could not establish a connection with our servers. Please try again when you are connected to the internet.",
                            "Try Again", errorListener);

                    break;

                case "CONTENT":
                    progressLayout.showContent();
                    break;
            }
        } else {

            Log.e(TAG, "progressStateCall: progress layout not used please check  ");
        }

    }
}
