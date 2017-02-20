package com.example.resources.bean;

import java.util.List;

/**
 * Created by fan-gk on 2017/2/20.
 */

public interface TwoLevelTreeBean<T extends LinearBean> extends LinearBean {
    List<T> getSubNodes();
}