package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;
import com.example.resources.bean.ActiveBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan-gk on 2017/3/13.
 */

public class ActiveTimePresenter extends BasePresenter<ActiveTimePresenter.ActiveTimeView> {


    public interface ActiveTimeView extends IView {
        void requestSuccess(List<ActiveBean> datas);
    }

    public ActiveTimePresenter(ActiveTimeView viewer) {
        super(viewer);
    }

    public void requestData() {
        List<ActiveBean> itemBeanList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ActiveBean itemBean = new ActiveBean();
            itemBean.title = "Mr. Donald Trump";
            itemBean.describe = "Presidency Candidate of United State";
            itemBean.tel = "+001 2345 6789";
            itemBean.email = "donald.trump@donaldtrump.com";
            if (i % 2 == 0)
                itemBean.url = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3344085265,3893608825&fm=23&gp=0.jpg";
            else
                itemBean.url = "http://pic2.52pk.com/files/allimg/141217/1141363322-17.jpg";
            itemBeanList.add(itemBean);
        }
        getView().requestSuccess(itemBeanList);
    }
}
