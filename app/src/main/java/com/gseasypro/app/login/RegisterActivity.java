package com.gseasypro.app.login;

import android.app.ActivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.presenter.RegisterPresenter;
import com.example.resources.bean.BaseUserBean;
import com.gseasypro.app.IndexActivity;
import com.gseasypro.app.R;
import com.gseasypro.app.activity.BasePresenterActivity;
import com.gseasypro.app.ioc.compoents.PresenterComponent;

import app.gseasypro.com.utils.DialogUtil;
import app.gseasypro.com.utils.ui.widget.EnlargeCrossEditText;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BasePresenterActivity<RegisterPresenter, RegisterPresenter.RegisterView>
        implements RegisterPresenter.RegisterView {

    @BindView(R.id.titleBar)
    TitleBar mTitleBar;
    @BindView(R.id.et_phone)
    EnlargeCrossEditText mEtPhone;
    @BindView(R.id.et_verification)
    EnlargeCrossEditText mEtVerification;
    @BindView(R.id.btn_submit_verify_code)
    Button btnSubmitVerifyCode;
    @BindView(R.id.et_name)
    EnlargeCrossEditText mEtName;
    @BindView(R.id.et_student_number)
    EnlargeCrossEditText mEtStudentNumber;
    @BindView(R.id.et_major)
    EnlargeCrossEditText mEtMajor;
    @BindView(R.id.et_password)
    EnlargeCrossEditText mEtPassword;
    @BindView(R.id.bt_ensure)
    Button mBtEnsure;
    @BindView(R.id.ll_add_info)
    LinearLayout mLlAddInfo;
    @BindView(R.id.ll_add_phone)
    LinearLayout mLlAddPhone;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mTitleBar.setLeftText("添加手机");
        mTitleBar.setBackClick(this);
    }

    @Override
    protected void injectPresenter(PresenterComponent component, RegisterPresenter presenter) {
        component.inject(presenter);
    }

    @Override
    public void onEmptyPhone() {
        DialogUtil.softOneBtnDialog(this, "请输入您的手机号码");
    }

    @Override
    public void onEmptyVerifyCode() {
        DialogUtil.softOneBtnDialog(this, "请输入验证码");
    }

    @Override
    public void onEmptyAccount() {
        DialogUtil.softOneBtnDialog(this, "请输入您的学号");
    }

    @Override
    public void onEmptyUserName() {
        DialogUtil.softOneBtnDialog(this, "请输入您的真实名字");
    }

    @Override
    public void onEmptyPassword() {
        DialogUtil.softOneBtnDialog(this, "请输入您的密码");
    }

    @Override
    public void onEmptyMajor() {
        DialogUtil.softOneBtnDialog(this, "请输入您的专业");
    }

    @Override
    public void onVerifyPhoneSuccess() {
        mLlAddPhone.setVisibility(View.GONE);
        mLlAddInfo.setVisibility(View.VISIBLE);
        mTitleBar.setLeftText("个人资料");
    }

    @Override
    public void onRegisterSuccess(BaseUserBean bean) {
        getActivitiesManger().finishOtherActivities();
        launch(LoginActivity.class, true);
    }

    @Override
    public void onRegisterFailed() {
        DialogUtil.softOneBtnDialog(this, "注册失败");
    }

    @OnClick({R.id.bt_get_verification, R.id.btn_submit_verify_code, R.id.bt_ensure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_get_verification:
                getPresenter().verifyPhone(mEtPhone.getText().toString());
                break;
            case R.id.btn_submit_verify_code:
                getPresenter().verfyCode(mEtVerification.getText().toString());
                break;
            case R.id.bt_ensure:
                getPresenter().registerGSZZB(mEtPhone.getText().toString()
                        , mEtStudentNumber.getText().toString(), mEtName.getText().toString()
                        , mEtPassword.getText().toString(), mEtMajor.getText().toString());
                break;
        }
    }
}
