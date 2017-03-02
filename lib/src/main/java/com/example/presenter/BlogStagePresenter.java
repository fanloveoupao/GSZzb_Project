package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;
import com.example.resources.bean.BaseClassBean;
import com.example.resources.bean.SubsBean;
import com.example.utils.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan-gk on 2017/3/1.
 */

public class BlogStagePresenter extends BasePresenter<BlogStagePresenter.BlogStageIView> {
    public BlogStagePresenter(BlogStageIView viewer) {
        super(viewer);
    }

    public interface BlogStageIView extends IView {
        void initBlogStageSuccess(List<SubsBean<BaseClassBean>> mStageBeanList);
    }

    public void initBlogStage() {
        List<BaseClassBean> areas = new ArrayList<>();
        BaseClassBean bean = new BaseClassBean();
        bean.name = "新生";
        BaseClassBean bean1 = new BaseClassBean();
        bean1.name = "大一";
        BaseClassBean bean2 = new BaseClassBean();
        bean2.name = "大二";
        BaseClassBean bean3 = new BaseClassBean();
        bean3.name = "大三";
        BaseClassBean bean4 = new BaseClassBean();
        bean4.name = "大四";
        BaseClassBean bean5 = new BaseClassBean();
        bean5.name = "毕业季";
        areas.add(bean);
        areas.add(bean1);
        areas.add(bean2);
        areas.add(bean3);
        areas.add(bean4);
        areas.add(bean5);
        List<SubsBean<BaseClassBean>> mStageBeanList = BeanUtils.toSubsList(areas, null);
        getView().initBlogStageSuccess(mStageBeanList);
    }
}
