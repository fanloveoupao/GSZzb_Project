package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;
import com.example.resources.bean.BaseClassBean;
import com.example.resources.bean.ParentBean;
import com.example.resources.bean.SubsBean;
import com.example.utils.BeanUtils;
import com.example.utils.CollectionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan-gk on 2017/2/28.
 */

public class CollegeTwoLeavePresenter extends BasePresenter<CollegeTwoLeavePresenter.CollegeTwoLeaveIView> {
    public CollegeTwoLeavePresenter(CollegeTwoLeaveIView viewer) {
        super(viewer);
    }

    public interface CollegeTwoLeaveIView extends IView {
        void onInitSuccess(List<ParentBean<BaseClassBean, SubsBean<BaseClassBean>>> mAreaParents);

        void onNoData();
    }

    public void initData() {
        List<BaseClassBean> areas = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            BaseClassBean bean = new BaseClassBean();
            bean.name = "传播学院";
            if (i % 2 == 0)
                bean.name = "文学院";
            List<BaseClassBean> beanList = new ArrayList<>();
            BaseClassBean bean0 = new BaseClassBean();
            bean0.name = "数字媒体";
            BaseClassBean bean1 = new BaseClassBean();
            bean1.name = "网络媒体";
            BaseClassBean bean2 = new BaseClassBean();
            bean2.name = "编导设计";
            BaseClassBean bean3 = new BaseClassBean();
            bean3.name = "教技师";
            beanList.add(bean0);
            beanList.add(bean1);
            beanList.add(bean2);
            beanList.add(bean3);
            bean.subs = beanList;
            areas.add(bean);
        }
        initAreaData(areas);
    }

    private void initAreaData(List<BaseClassBean> proviceBeanList) {
        List<ParentBean<BaseClassBean, SubsBean<BaseClassBean>>> mAreaParents = new ArrayList<>();
        if (!CollectionUtil.isNullOrEmpty(proviceBeanList)) {

            mAreaParents = BeanUtils.toParentList(proviceBeanList, null);
            for (ParentBean<BaseClassBean, SubsBean<BaseClassBean>> p : mAreaParents) {
                if (p.getSubs().get(0).isCheck) {
                    for (SubsBean<BaseClassBean> s :
                            p.getSubs()) {
                        s.isCheck = true;
                    }
                    p.getSubs().get(0).isCheck = false;
                    p.init();
                    p.getSubs().get(0).isCheck = true;
                } else if (p.getCheckCount() == p.getSubs().size() - 1) {
                    p.getSubs().get(0).isCheck = true;
                }
            }
            getView().onInitSuccess(mAreaParents);
        } else {
            getView().onNoData();
        }
    }
}
