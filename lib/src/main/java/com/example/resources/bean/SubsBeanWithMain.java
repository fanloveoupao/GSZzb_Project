package com.example.resources.bean;

/**
 * Created by fan-gk on 2017/2/20.
 */


public class SubsBeanWithMain<T extends LinearBean> extends SubsBean<T> {
    public boolean main;

    public SubsBeanWithMain(T linearBean) {
        super(linearBean);
    }
}