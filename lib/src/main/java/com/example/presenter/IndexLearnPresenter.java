package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;
import com.example.resources.bean.ProjectBean;
import com.example.resources.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan-gk on 2017/2/9.
 */

public class IndexLearnPresenter extends BasePresenter<IndexLearnPresenter.IndexLearnIView> {
    public IndexLearnPresenter(IndexLearnIView mView) {
        super(mView);
    }

    private int start = 0;
    private int limit = 20;

    public interface IndexLearnIView extends IView {
        void onInitBlogListSuccess(List<ProjectBean> data);

        void onLoadMoreDataSuccess(List<ProjectBean> data);

        void onNoMoreData();

        void onLoadMoreDataFailed();
    }

    public void initBlogList() {
        List<ProjectBean> projectBeanList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ProjectBean projectBean = new ProjectBean();
            UserBean userBean = new UserBean();
            userBean.image = "http://cdn-img.easyicon.net/png/12070/1207032.gif";
            projectBean.user = userBean;
            if (i % 2 == 0) {
                userBean.name = "数字媒体技术";
                userBean.tag = "影视作品";
                projectBean.name = "一分钟带你走进新广师";
                projectBean.content = "广师是我们待4年的地方，我们拍摄这部片子旨在记录广师，也许在不久的将来，我们毕业了，那么这部作品也能为我们的大学回忆里找到曾经熟悉的空气";
                List<String> keyWords = new ArrayList<>();
                keyWords.add("广师");
                keyWords.add("毕业");
                projectBean.keywords = keyWords;
            } else {
                userBean.name = "传播学院";
                userBean.tag = "毕业设计";
                userBean.image = "http://setc.gpnu.edu.cn/img/logo.png";
                projectBean.name = "特高压FLASH动画微课开发";
                projectBean.content = "本次毕业设计作品名称为“特高压Flash动画微课开发”，主要利用Flash制作关于特高压的动画微课。特高压微课整体采用诙谐幽默、语速轻快的风格";
                List<String> keyWords = new ArrayList<>();
                keyWords.add("毕业设计");
                keyWords.add("微课开发");
                projectBean.keywords = keyWords;
            }
            projectBean.view_count = 210;
            projectBean.chat_count = 5;
            projectBeanList.add(projectBean);
        }
        getView().onInitBlogListSuccess(projectBeanList);
    }

    public void loadMoreData(int start) {
        List<ProjectBean> projectBeanList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ProjectBean projectBean = new ProjectBean();
            UserBean userBean = new UserBean();
            userBean.image = "http://cdn-img.easyicon.net/png/12070/1207032.gif";
            projectBean.user = userBean;
            if (i % 2 == 0) {
                userBean.name = "数字媒体技术";
                userBean.tag = "影视作品";
                projectBean.name = "一分钟带你走进新广师";
                projectBean.content = "广师是我们待4年的地方，我们拍摄这部片子旨在记录广师，也许在不久的将来，我们毕业了，那么这部作品也能为我们的大学回忆里找到曾经熟悉的空气";
                List<String> keyWords = new ArrayList<>();
                keyWords.add("广师");
                keyWords.add("毕业");
                projectBean.keywords = keyWords;
            } else {
                userBean.name = "传播学院";
                userBean.tag = "毕业设计";
                userBean.image = "http://setc.gpnu.edu.cn/img/logo.png";
                projectBean.name = "特高压FLASH动画微课开发";
                projectBean.content = "本次毕业设计作品名称为“特高压Flash动画微课开发”，主要利用Flash制作关于特高压的动画微课。特高压微课整体采用诙谐幽默、语速轻快的风格";
                List<String> keyWords = new ArrayList<>();
                keyWords.add("毕业设计");
                keyWords.add("微课开发");
                projectBean.keywords = keyWords;
            }
            projectBean.view_count = 210;
            projectBean.chat_count = 5;
            projectBeanList.add(projectBean);
        }
        getView().onLoadMoreDataSuccess(projectBeanList);
    }

    public int getLimit() {
        return limit;
    }
}
