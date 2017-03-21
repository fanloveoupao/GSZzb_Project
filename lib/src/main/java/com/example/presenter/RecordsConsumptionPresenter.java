package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;
import com.example.resources.bean.RecordsConsumptionBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan-gk on 2017/3/15.
 */

public class RecordsConsumptionPresenter extends BasePresenter<RecordsConsumptionPresenter.HtmlAnalyzerView> {
    public RecordsConsumptionPresenter(HtmlAnalyzerView viewer) {
        super(viewer);
    }

    public interface HtmlAnalyzerView extends IView {

        void onLoadMoreDataSuccess(List<RecordsConsumptionBean> data);

        void onLoadError();

        void onNoMoreData();

    }

    private int start = 0;

    private int limit = 20;

    public void loadMoreData() {
        List<RecordsConsumptionBean> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            RecordsConsumptionBean bean = new RecordsConsumptionBean();
            if (i % 2 == 0) {
                bean.image = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=4176430521,1443699849&fm=11&gp=0.jpg";
                bean.consume = "面对面收款--转给阿姨";
                bean.number = "-32.0";
                bean.date = "3月24 19:25";
            } else {
                bean.image = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3344085265,3893608825&fm=23&gp=0.jpg";
                bean.consume = "广师饭堂--消费";
                bean.number = "-12.0";
                bean.date = "1月24 09:07";
            }
            data.add(bean);
        }
        getView().onLoadMoreDataSuccess(data);
    }

    public int getLimit() {
        return limit;
    }
}
