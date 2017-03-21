package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;
import com.example.resources.bean.RechargeBean;
import com.example.resources.bean.UserInfoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan-gk on 2017/3/16.
 */

public class RechargePresenter extends BasePresenter<RechargePresenter.RechargeView> {
    public RechargePresenter(RechargeView viewer) {
        super(viewer);
    }


    public interface RechargeView extends IView {
        void onInitData(List<RechargeBean> data);

        void onInitUserDataSuccess(UserInfoBean userInfo);
    }

    public void getUserData() {
        UserInfoBean info = new UserInfoBean();
        info.image = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3344085265,3893608825&fm=23&gp=0.jpg";
        info.name = "青花瓷";
        info.company = "2013 084 544 047";
        getView().onInitUserDataSuccess(info);
    }

    public void initData() {
        List<RechargeBean> data = new ArrayList<>();
        RechargeBean bean = new RechargeBean("30元", "售价29.85元");
        RechargeBean bean1 = new RechargeBean("50元", "售价49.75元");
        RechargeBean bean2 = new RechargeBean("100元", "售价99.05元");
        RechargeBean bean3 = new RechargeBean("200元", "售价199.00元");
        RechargeBean bean4 = new RechargeBean("300元", "售价298.50元");
        RechargeBean bean5 = new RechargeBean("500元", "售价497.50元");
        data.add(bean);
        data.add(bean1);
        data.add(bean2);
        data.add(bean3);
        data.add(bean4);
        data.add(bean5);
        getView().onInitData(data);

    }

}
