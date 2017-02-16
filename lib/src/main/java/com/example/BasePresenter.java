package com.example;

/**
 * Created by fan-gk on 2017/2/3.
 */

public abstract class BasePresenter<V extends IView> {
    public V mView;

    public void attach(V mView) {
        this.mView = mView;
    }

    public void dettach() {
        mView = null;
    }
}
