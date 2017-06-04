package com.gseasypro.app.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lang.StringUtil;
import com.example.presenter.LoginPresenter;
import com.example.ui.ClientSettings;
import com.example.utils.CollectionUtil;
import com.gseasypro.app.IndexActivity;
import com.gseasypro.app.R;
import com.gseasypro.app.activity.BasePresenterActivity;
import com.gseasypro.app.ioc.compoents.PresenterComponent;

import app.gseasypro.com.utils.DialogUtil;
import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.ui.widget.EnlargeCrossEditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BasePresenterActivity<LoginPresenter, LoginPresenter.LoginIView>
        implements LoginPresenter.LoginIView {

    @BindView(R.id.bt_login)
    Button mBtnLogin;
    @BindView(R.id.tv_login_register)
    TextView mTvLoginRegister;
    @BindView(R.id.tv_login_forget_password)
    TextView mTvLoginForgetPassword;
    @BindView(R.id.et_login_account)
    EnlargeCrossEditText mEtLoginAccount;
    @BindView(R.id.et_login_password)
    EnlargeCrossEditText mEtLoginPassword;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        ClientSettings clientSettings = new ClientSettings();
        String account = CollectionUtil.firstOrDefault(clientSettings.getLoginSettings().getUsernames());
        if (!StringUtil.isNullOrEmpty(account))
            mEtLoginAccount.setText(account);
    }


    @OnClick({R.id.bt_login, R.id.tv_login_register, R.id.tv_login_forget_password})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                getPresenter().initUser(mEtLoginAccount.getText().toString(), mEtLoginPassword.getText().toString());
                break;
            case R.id.tv_login_register:
                launch(RegisterActivity.class, false);
                break;
            case R.id.tv_login_forget_password:
                launch(ForgetPasswordActivity.class, false);
                break;
        }
    }

    @Override
    public void onEmptyAccount() {
        DialogUtil.softOneBtnDialog(this, "请输入您的学号");
    }

    @Override
    public void onEmptyPassword() {
        DialogUtil.softOneBtnDialog(this, "请输入您的密码");
    }

    @Override
    public void onLoginFailed() {
        DialogUtil.softOneBtnDialog(this, "登录失败请稍后重试");
    }

    @Override
    public void onLoginSuccess() {
        launch(IndexActivity.class, true);
    }


    @Override
    protected void injectPresenter(PresenterComponent component, LoginPresenter preseneter) {
        component.inject(preseneter);
    }
}
