package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;
import com.example.resources.bean.MyElderSisterBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan-gk on 2017/2/24.
 */

public class MyElderSisterPresenter extends BasePresenter<MyElderSisterPresenter.MyElderSisterIView> {
    public MyElderSisterPresenter(MyElderSisterIView viewer) {
        super(viewer);
    }

    public interface MyElderSisterIView extends IView {

        void onNetWorkError();

        void onNonePublishProject();

        void onProjectEmpty();

        void onInitData(List<MyElderSisterBean> data);

        void onCanRefresh(boolean canRefresh);
    }

    public void initMyElderSisterData() {
        List<MyElderSisterBean> data = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            MyElderSisterBean bean = new MyElderSisterBean();
            bean.college = "传播学院";
            bean.gaduateWork = "放飞希望梦想广师!";
            bean.content = "替代的组织领导作用，下设组织部宣教部、调研部、大学生自愿者协会";
            bean.name = "黄家常";
            bean.signature = "个性签名展现自我";
            bean.image = "";
            List<String> keyList = new ArrayList<>();
            keyList.add("宣教部");
            keyList.add("调研部");
            bean.keywords = keyList;
            data.add(bean);
        }
        getView().onInitData(data);
    }

    public void getSearchCacheTime() {
        try {

            getView().onCanRefresh(true);
        } catch (Exception e) {
            getView().onException(e);
        }

    }
}
