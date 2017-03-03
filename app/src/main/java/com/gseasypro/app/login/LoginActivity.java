package com.gseasypro.app.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.presenter.LoginPresenter;
import com.gseasypro.app.IndexActivity;
import com.gseasypro.app.R;

import app.gseasypro.com.utils.PresenterActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends PresenterActivity<LoginPresenter, LoginPresenter.LoginIView>
        implements LoginPresenter.LoginIView {

    @BindView(R.id.bt_login)
    Button mBtnLogin;
    @BindView(R.id.tv_login_register)
    TextView mTvLoginRegister;
    @BindView(R.id.tv_login_forget_password)
    TextView mTvLoginForgetPassword;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.bt_login, R.id.tv_login_register, R.id.tv_login_forget_password})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                launch(IndexActivity.class, true);
                break;
            case R.id.tv_login_register:
                break;
            case R.id.tv_login_forget_password:
                launch(ForgetPasswordActivity.class, false);
                break;
        }
    }
}
