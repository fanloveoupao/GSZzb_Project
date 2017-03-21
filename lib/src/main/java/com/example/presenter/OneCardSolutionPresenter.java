package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;
import com.example.resources.bean.ItemOneCardBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan-gk on 2017/3/15.
 */

public class OneCardSolutionPresenter extends BasePresenter<OneCardSolutionPresenter.OneCardSolutionView> {


    public interface OneCardSolutionView extends IView {
        void onInitItemSuccess(List<ItemOneCardBean> data);
    }

    public OneCardSolutionPresenter(OneCardSolutionView viewer) {
        super(viewer);
    }

    public void initOneCardItem() {
        getView().onInitItemSuccess(new ArrayList<ItemOneCardBean>());
    }
}
