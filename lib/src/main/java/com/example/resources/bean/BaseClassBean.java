package com.example.resources.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan-gk on 2017/2/20.
 */


public class BaseClassBean implements Serializable, LinearBean, TreeBean<BaseClassBean>, TwoLevelTreeBean<BaseClassBean>, NamedBean {
    public String name;
    public String no;
    public List<BaseClassBean> subs;
    public boolean isCheck;

    public BaseClassBean() {
    }

    public BaseClassBean(BaseClassBean copy) {
        this.name = copy.name;
        this.no = copy.no;
        this.subs = copy.subs;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public List<BaseClassBean> getSubTrees() {
        return subs;
    }

    @Override
    public String getNo() {
        return no;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(LinearBean linearBean) {
        return no.equals(linearBean.getNo());
    }

    @Override
    public List<BaseClassBean> getSubNodes() {
        return subs;
    }
}
