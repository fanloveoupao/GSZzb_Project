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
 * Created by fan-gk on 2017/3/3.
 */

public class LoginPresenter extends BasePresenter<LoginPresenter.LoginIView> {

    public interface LoginIView extends IView {

        void onEmptyAccount();

        void onEmptyPassword();

        void onLoginFailed();

        void onLoginSuccess();
    }

    public LoginPresenter(LoginIView viewer) {
        super(viewer);
    }

    @Inject
    ILoginService service;

    public void initUser(final String account, final String password) {
        if (StringUtil.isNullOrEmpty(account)) {
            getView().onEmptyAccount();
            return;
        }
        if (StringUtil.isNullOrEmpty(password)) {
            getView().onEmptyPassword();
            return;
        }
        ClientSettings clientSettings = new ClientSettings();
        clientSettings.getLoginSettings().addUsername(account);
        newActionBuilder().setRunnable(new ActionRunnable() {
            @Override
            public void run() throws Exception {
                BaseUserBean userBean = service.getUserData(account);
                if (userBean != null) {
                    UserSettings.getInstance().setBaseUserBean(userBean);
                    getView().onLoginSuccess();
                } else {
                    getView().onLoginFailed();
                }
            }
        }).setRunLoading().run();

    }
}
