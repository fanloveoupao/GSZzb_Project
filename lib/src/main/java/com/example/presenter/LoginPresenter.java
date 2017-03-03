package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;

/**
 * Created by fan-gk on 2017/3/3.
 */

public class LoginPresenter extends BasePresenter<LoginPresenter.LoginIView> {

    public interface LoginIView extends IView {

    }

    public LoginPresenter(LoginIView viewer) {
        super(viewer);
    }

}
