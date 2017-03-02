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

public class BlogTypePresenter extends BasePresenter<BlogTypePresenter.BlogTypeIView> {
    public BlogTypePresenter(BlogTypeIView viewer) {
        super(viewer);
    }

    public interface BlogTypeIView extends IView {
        void initBlogTypeSuccess(List<SubsBean<BaseClassBean>> mTypeBeanList);
    }

    public void initBlogType() {
        List<BaseClassBean> areas = new ArrayList<>();
        BaseClassBean bean = new BaseClassBean();
        bean.name = "学校要闻";
        BaseClassBean bean1 = new BaseClassBean();
        bean1.name = "综合新闻";
        BaseClassBean bean2 = new BaseClassBean();
        bean2.name = "通知公告";
        BaseClassBean bean3 = new BaseClassBean();
        bean3.name = "科研信息";
        BaseClassBean bean4 = new BaseClassBean();
        bean4.name = "教学信息";
        BaseClassBean bean5 = new BaseClassBean();
        bean5.name = "招生就业";
        BaseClassBean bean6 = new BaseClassBean();
        bean6.name = "数字校园";
        BaseClassBean bean7 = new BaseClassBean();
        bean7.name = "图书信息";
        areas.add(bean);
        areas.add(bean1);
        areas.add(bean2);
        areas.add(bean3);
        areas.add(bean4);
        areas.add(bean5);
        areas.add(bean6);
        areas.add(bean7);
        List<SubsBean<BaseClassBean>> mTypeBeanList = BeanUtils.toSubsList(areas, null);
        getView().initBlogTypeSuccess(mTypeBeanList);
    }
}
