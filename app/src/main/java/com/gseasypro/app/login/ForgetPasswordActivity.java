package com.gseasypro.app.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.example.presenter.ForgetPasswordPresenter;
import com.gseasypro.app.R;

import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.ui.widget.EnlargeCrossEditText;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgetPasswordActivity extends PresenterActivity<ForgetPasswordPresenter, ForgetPasswordPresenter.ForgetPasswordView>
        implements ForgetPasswordPresenter.ForgetPasswordView {
    @BindView(R.id.titleBar)
    TitleBar mTitleBar;
    @BindView(R.id.et_phone)
    EnlargeCrossEditText mEtPhone;
    @BindView(R.id.btn_submit_verifycode)
    Button mBtnSubmitVerifycode;
    @BindView(R.id.btn_send)
    Button mBtnSend;
    private boolean is_register;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);
        initViews();

    }

    private void initViews() {
        mTitleBar.setLeftText("找回密码");
        mTitleBar.setBackClick(this);
    }


}
