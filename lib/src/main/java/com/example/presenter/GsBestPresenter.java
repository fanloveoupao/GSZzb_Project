package com.example.presenter;

import com.example.ActionRunnable;
import com.example.BasePresenter;
import com.example.IView;
import com.example.resources.bean.ItemGsBestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan-gk on 2017/2/23.
 */

public class GsBestPresenter extends BasePresenter<GsBestPresenter.GsBestIView> {
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

        newActionBuilder()
                .setRunnable(new ActionRunnable() {
                    @Override
                    public void run() throws Exception {
                        List<ItemGsBestBean> list = new ArrayList<>();
                        for (int i = 0; i < 20; i++) {
                            ItemGsBestBean itemGsBestBean = new ItemGsBestBean();
                            list.add(itemGsBestBean);
                        }
                        getView().onInitGsBestSuccess(list);
                    }
                })
                .setRunLoading().setAllowUserReload().setBackOnExceptionFinishParent().run();
    }

    public int getLimit() {
        return limit;
    }
}
