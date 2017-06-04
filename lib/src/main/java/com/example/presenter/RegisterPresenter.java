package com.example.presenter;

import com.example.ActionRunnable;
import com.example.BasePresenter;
import com.example.IView;
import com.example.api.ILoginService;
import com.example.lang.StringUtil;
import com.example.resources.bean.BaseUserBean;
import com.example.ui.ClientSettings;
import com.example.ui.UserSettings;

import javax.inject.Inject;

/**
 * Created by fan-gk
 * on 2017/5/23.
 */

public class RegisterPresenter extends BasePresenter<RegisterPresenter.RegisterView> {
    public RegisterPresenter(RegisterView viewer) {
        super(viewer);
    }

    public interface RegisterView extends IView {

        void onEmptyPhone();

        void onEmptyVerifyCode();

        void onEmptyAccount();

        void onEmptyUserName();

        void onEmptyPassword();

        void onEmptyMajor();

        void onVerifyPhoneSuccess();

        void onRegisterSuccess(BaseUserBean bean);

        void onRegisterFailed();
    }

    @Inject
    ILoginService service;

    public void verifyPhone(String phone) {
        if (StringUtil.isNullOrEmpty(phone)) {
            getView().onEmptyPhone();
            return;
        }
    }

    public void verfyCode(String code) {
        if (StringUtil.isNullOrEmpty(code)) {
            getView().onEmptyVerifyCode();
            return;
        }
        getView().onVerifyPhoneSuccess();
    }

    public void registerGSZZB(final String phone, final String account, final String username, final String password, final String major) {
        if (StringUtil.isNullOrEmpty(account)) {
            getView().onEmptyAccount();
            return;
        }
        if (StringUtil.isNullOrEmpty(username)) {
            getView().onEmptyUserName();
            return;
        }
        if (StringUtil.isNullOrEmpty(password)) {
            getView().onEmptyPassword();
            return;
        }
        if (StringUtil.isNullOrEmpty(major)) {
            getView().onEmptyMajor();
            return;
        }
        newActionBuilder().setRunnable(new ActionRunnable() {
            @Override
            public void run() throws Exception {
                BaseUserBean userBean = service.registerGSZZB(phone, account, username, password, major);
                ClientSettings clientSettings = new ClientSettings();
                clientSettings.getLoginSettings().addUsername(account);
                getView().onRegisterSuccess(userBean);

            }
        }).setRunLoading().run();

    }
}
