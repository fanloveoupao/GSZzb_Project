package com.example.presenter;

import com.example.ActionRequest;
import com.example.ActionRunnable;
import com.example.BasePresenter;
import com.example.IView;
import com.example.resources.bean.ExperiencePredecessorsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan-gk on 2017/4/12.
 */

public class ExperiencePredecessorsPresenter extends BasePresenter<ExperiencePredecessorsPresenter.ExperiencePredecessorsView> {
    public ExperiencePredecessorsPresenter(ExperiencePredecessorsView viewer) {
        super(viewer);
    }

    public interface ExperiencePredecessorsView extends IView {
        void onNetWorkExceptionToast();


        void onNetWorkExceptionError();

        void onRefreshDataSuccess(List<ExperiencePredecessorsBean> certificationProjectBeanList);


        void onRefreshDataEmpty();

        void onNoMoreData();

        void onLoadMoreSuccess(List<ExperiencePredecessorsBean> projectBeanList);

        void onLoadMoreDataError();
    }

    private int start = 0;
    private int limit = 20;

    private boolean hasMoreata = true;
    private boolean loadHeader = true;

    public int getLimit() {
        return limit;
    }


    public void refreshData(final boolean runBackground) {

        newActionBuilder().setRunnable(new ActionRunnable() {
            @Override
            public void run() throws Exception {

                List<ExperiencePredecessorsBean> projectBeanList = new ArrayList<ExperiencePredecessorsBean>();
                for (int i = 0; i < 20; i++) {
                    ExperiencePredecessorsBean bean = new ExperiencePredecessorsBean();
                    bean.name = "创新强校工程";
                    bean.content = "根据《广东省教育厅关于做好“创新强校工程”科研项目管理工作的通知》的要求，现启动我校“创新强校工程”科研项目开题、中期检查及结题工作，请2014年至2016年的“创新强校工程”科研项目负责人查看附件通知，并根据自己的项目进展，开展相应工作";
                    bean.update_date = "2017-05-06T12:25";
                    bean.recommend = true;
                    List<String> keywords = new ArrayList<String>();
                    keywords.add("创新强校工程");
                    bean.keywords = keywords;
                    bean.user = "广东技术";
                    bean.project_stage = "Android开发";
                    projectBeanList.add(bean);
                }
                getView().onRefreshDataSuccess(projectBeanList);


            }
        }).setRunType(runBackground ? ActionRequest.RUN_TYPE_BACKGROUND : ActionRequest.RUN_TYPE_LOADING)
                .setAllowUserReload().run();

    }

    public void loadMoreData() {
        newActionBuilder().setRunnable(new ActionRunnable() {
            @Override
            public void run() throws Exception {
                getView().onNoMoreData();
            }
        }).setRunBackground().run();
    }
}
