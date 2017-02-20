package com.example.resources.bean;

/**
 * Created by fan-gk on 2017/2/20.
 */

public class SubsBeanWithMainLocal<T extends LinearBean> extends SubsBeanWithMain<T> {
    public boolean isLocal;

    public SubsBeanWithMainLocal(T linearBean) {
        super(linearBean);
    }
}
