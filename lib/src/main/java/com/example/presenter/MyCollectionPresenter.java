package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;
import com.example.resources.bean.CollectionProjectBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan-gk on 2017/3/2.
 */

public class MyCollectionPresenter extends BasePresenter<MyCollectionPresenter.MyCollectionIView> {
    private final int limit = 20;
    private int start = 0;

    public interface MyCollectionIView extends IView {
        void onNoMoreData();

        void onNoCollection();

        void onLoadMoreDataError();

        void onLoadDataSuccess(List<CollectionProjectBean> projectBeanList);

        void onDeleteSuccess(int position);
    }

    public MyCollectionPresenter(MyCollectionIView viewer) {
        super(viewer);
    }

    public void getCollectionData() {
        List<CollectionProjectBean> projectBeanList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            CollectionProjectBean bean = new CollectionProjectBean();
            bean.name = "为什么要理财";
            bean.update_date = "2017-01-09T14:11:55.33";
            bean.type = i % 4 + 1;
            if (i % 2 == 0)
                bean.name = "学者视野——周末科技文化艺术系列讲座";
            projectBeanList.add(bean);
        }
        int size = projectBeanList.size();
        start += size;
        if (size == 0 && start == 0) {
            getView().onNoCollection();
            return;
        }
        if (size < limit)
            getView().onNoMoreData();
        getView().onLoadDataSuccess(projectBeanList);
    }

    public void deleteCollection(final long cid, final int position) {

    }


    public int getLimit() {
        return limit;
    }
}
