package com.pappayaed.ui.splash;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.pappayaed.R;
import com.pappayaed.base.BaseActivity;
import com.pappayaed.ui.login.LoginActivity;
import com.pappayaed.ui.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends BaseActivity implements ISplashView {

    private int progressStatus = 0;
    private Handler handler = new Handler();

    @BindView(R.id.progress_1)
    RoundCornerProgressBar pbRedProgress;
    private SplashPresenterImpl splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        ButterKnife.bind(this);

        splashPresenter = new SplashPresenterImpl(dataSource);

        splashPresenter.onAttach(this);


        pbRedProgress.setProgressColor(Color.RED);
        pbRedProgress.setProgressBackgroundColor(Color.TRANSPARENT);
        // Start the lengthy operation in a background thread
        new Thread(() -> {
            while (progressStatus < 100) {
                // Update the progress status
                progressStatus += 1;

                // Try to sleep the thread for 20 milliseconds
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Update the progress bar
                handler.post(() -> {
                    pbRedProgress.setProgress(progressStatus);

                    if (progressStatus >= 100) {

                        splashPresenter.moveToNextActivity();
                    }

                });
            }
        }).start(); // Start the operation


    }


    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onPause() {
        super.onPause();
        splashPresenter.cancelHandler();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        splashPresenter.onDetach();

    }

    @Override
    public void gotoMainActivity() {
//        avi.smoothToHide();
        startActivity(new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

    }

    @Override
    public void gotoLoginActivity() {
//        avi.smoothToHide();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

    }
}
