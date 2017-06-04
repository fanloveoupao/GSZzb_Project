package com.example.presenter;

import com.example.ActionRunnable;
import com.example.BasePresenter;
import com.example.IView;
import com.example.resources.bean.IndexBean;
import com.example.resources.bean.ItemGsBestBean;
import com.example.api.schoolapi.ISchoolService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by fan-gk on 2017/2/23.
 */

public class GsBestPresenter extends BasePresenter<GsBestPresenter.GsBestIView> {
    @Inject
    ISchoolService service;

    public GsBestPresenter(GsBestIView viewer) {
        super(viewer);
    }

    public interface GsBestIView extends IView {
        void onInitGsBestSuccess(List<ItemGsBestBean> data);

        void onLoadSuccess(List<ItemGsBestBean> data);

        void onNoMoreData();

        void onLoadFailed();

        void onNetError();
    }

    private int start;


    private int limit = 20;

    public void initGsBestData() {

        List<ItemGsBestBean> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ItemGsBestBean itemGsBestBean = new ItemGsBestBean();
            list.add(itemGsBestBean);
        }
        getView().onInitGsBestSuccess(list);
    }

    public int getLimit() {
        return limit;
    }
}
