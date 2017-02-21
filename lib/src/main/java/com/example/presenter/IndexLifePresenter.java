package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;
import com.example.resources.bean.ItemLifeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan-gk on 2017/2/9.
 */

public class IndexLifePresenter extends BasePresenter<IndexLifePresenter.IndexLifeIView> {
    public IndexLifePresenter(IndexLifeIView mView) {
        super(mView);
    }

    public interface IndexLifeIView extends IView {
        void onDataSuccess(List<ItemLifeBean> lifeBeanList);
    }

    public void initLifeItem() {
        List<ItemLifeBean> lifeBeanList = new ArrayList<>();
        getView().onDataSuccess(lifeBeanList);
    }
}
