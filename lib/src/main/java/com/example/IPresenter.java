package com.example;

/**
 * Created by fan-gk on 2017/2/18.
 */

public interface IPresenter<T extends IView> {
    T getView();

    void onViewCreate();

    void onViewDestroy();
}
