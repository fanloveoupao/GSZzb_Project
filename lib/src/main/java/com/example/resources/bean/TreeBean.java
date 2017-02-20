package com.example.resources.bean;

import java.util.List;

/**
 * Created by fan-gk on 2017/2/20.
 */


public interface TreeBean<T extends TreeBean> extends LinearBean, TwoLevelTreeBean<T> {
    List<T> getSubTrees();
}
