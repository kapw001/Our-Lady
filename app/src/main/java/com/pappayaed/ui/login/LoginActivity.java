package com.pappayaed.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;

import com.pappayaed.data.helper.L;
import com.pappayaed.ui.main.MainActivity;
import com.pappayaed.R;
import com.pappayaed.base.BaseActivity;
import com.pappayaed.common.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginView {

    private static final String TAG = "LoginActivity";

    @BindView(R.id.username)
    AppCompatEditText username;
    @BindView(R.id.password)
    AppCompatEditText password;
    @BindView(R.id.login)
    Button login;

    private ILoginPresenter<ILoginView> loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        loginPresenter = new LoginPresenterImpl(dataSource);

        loginPresenter.onAttach(this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        loginPresenter.onDetach();

    }

    @OnClick(R.id.login)
    public void loginClick(View view) {

        String userName = username.getText().toString();
        String passWord = password.getText().toString();

        loginPresenter.validateCredentials(userName, passWord);

    }

    @Override
    public void onFail(Throwable throwable) {

        L.loge(throwable.getMessage());

        showSnackBar(findViewById(R.id.loginroot), throwable.getMessage());
    }


    @Override
    public void showEmailError() {

        L.loge("Invalid Email ID");

        showSnackBar(findViewById(R.id.loginroot), "Invalid Email ID");

    }

    @Override
    public void showPasswordError() {
        L.loge("Invalid Password");

        showSnackBar(findViewById(R.id.loginroot), "Invalid Password");
    }

    @Override
    public void showEmailAndPasswordError() {

        L.loge("Invalid Email ID and Password");
        showSnackBar(findViewById(R.id.loginroot), "Invalid Email ID or Password");
    }

    @Override
    public void onSuccssLogin(String s) {
        L.loge(s);
    }

    @Override
    public void gotoMainActivity() {

        startActivity(new Intent(this, MainActivity.class));
        finish();

    }


    @Override
    public void showLoading() {

        Utils.showProgress(this, "Loading...");

        L.loge("Loading...");
    }

    @Override
    public void hideLoading() {

        Utils.hideProgress();

        L.loge("Called");
    }


}
