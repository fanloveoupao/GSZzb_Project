package com.gseasypro.app.login;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.presenter.ForgetPasswordPresenter;
import com.gseasypro.app.R;

import app.gseasypro.com.utils.PresenterActivity;

public class ForgetPasswordActivity extends PresenterActivity<ForgetPasswordPresenter,ForgetPasswordPresenter.ForgetPasswordView>
implements ForgetPasswordPresenter.ForgetPasswordView {


    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_forget_password);
    }
}
