package com.example.presenter;

import com.example.ActionRunnable;
import com.example.BasePresenter;
import com.example.IView;
import com.example.resources.bean.BeautifulGsItemBean;
import com.example.schoolapi.ISchoolService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by fan-gk on 2017/2/18.
 */

public class BeautifulGsPresenter extends BasePresenter<BeautifulGsPresenter.BeautifulGsView> {


    public BeautifulGsPresenter(BeautifulGsView mView) {
        super(mView);
    }

    @Inject
    ISchoolService service;

    public interface BeautifulGsView extends IView {
        void requestSuccess(List<BeautifulGsItemBean> datas);
    }

    public void requestData() {
        List<BeautifulGsItemBean> itemBeanList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            BeautifulGsItemBean itemBean = new BeautifulGsItemBean();
            itemBean.isLiked = false;
            itemBean.likesCount = 1 + i * 3;
            itemBeanList.add(itemBean);
        }
        getView().requestSuccess(itemBeanList);
        newActionBuilder()
                .setRunnable(new ActionRunnable() {
                    @Override
                    public void run() throws Exception {
                        service.getComplainDetail();
                    }
                })
                .setRunLoading()
                .setAllowUserReload()
                .run();
    }
}
