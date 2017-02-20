package com.example.resources.bean;

import java.io.Serializable;

/**
 * Created by fan-gk on 2017/2/20.
 */

public class SubsBean<T extends LinearBean> implements Serializable {
    protected T node;
    public boolean isCheck;

    public SubsBean(T linearBean) {
        node = linearBean;
    }

    public String getName() {
        return node.getName();
    }

    public boolean equals(T linearBean) {
        return node.equals(linearBean);
    }
    public T getNode(){return node;}
}
