package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;
import com.example.resources.bean.UserInfoBean;

/**
 * Created by fan-gk on 2017/3/2.
 */

public class QRCodePresenter extends BasePresenter<QRCodePresenter.QRCodeView> {
    public interface QRCodeView extends IView {
        void onSetUi(UserInfoBean userInfoBean);
    }

    public QRCodePresenter(QRCodeView viewer) {
        super(viewer);
    }

    public void getUserInfoBean() {
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.name = "广技师";
        userInfoBean.company = "广东技术师范学院";
        userInfoBean.colleage = "传播学院";
        userInfoBean.image="https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3344085265,3893608825&fm=23&gp=0.jpg";
        userInfoBean.url="www.gdin.edu.cn";
        getView().onSetUi(userInfoBean);
    }
}
