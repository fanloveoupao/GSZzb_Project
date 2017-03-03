package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;

/**
 * Created by fan-gk on 2017/3/3.
 */

public class ForgetPasswordPresenter extends BasePresenter<ForgetPasswordPresenter.ForgetPasswordView>{
    public ForgetPasswordPresenter(ForgetPasswordView viewer) {
        super(viewer);
    }

    public interface ForgetPasswordView extends IView{

    }
}
