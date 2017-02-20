package com.example.resources.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan-gk on 2017/2/20.
 */


public class ParentBean<T extends TwoLevelTreeBean, S extends SubsBean> implements Serializable {
    protected T parent;
    protected List<S> subsBeans;
    protected int checkCount = 0;
    public boolean isCheck = false;

    public ParentBean(T twoLevelTreeBean) {
        parent = twoLevelTreeBean;
        subsBeans = new ArrayList<>();
    }

    public void init() {
        int i = 0;
        for (S sub : subsBeans) {
            if (sub.isCheck) i++;
        }
        checkCount = i;
    }

    public void toggleCheckSubNode(int index) {
        subsBeans.get(index).isCheck = !subsBeans.get(index).isCheck;
        if (subsBeans.get(index).isCheck)
            checkCount++;
        else
            checkCount--;
    }

    public int getCheckCount() {
        return checkCount;
    }

    public String getName() {
        return parent.getName();
    }

    public List<S> getSubs() {
        return subsBeans;
    }

    public T getNode() {
        return parent;
    }
}

