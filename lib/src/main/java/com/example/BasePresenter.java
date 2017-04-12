package com.example;

/**
 * Created by fan-gk on 2017/2/3.
 */

public abstract class BasePresenter<E extends IView> implements IPresenter<E> {
    private E viewer;

    public BasePresenter(E viewer) {
        this.viewer = viewer;
    }

    @Override
    public E getView() {
        return viewer;
    }

    /**
     * run in mian thread
     */
    public void onViewCreate() {
    }

    /**
     * run in mian thread
     */
    public void onViewDestroy() {
    }

    /**
     * run in mian thread
     */
    public void onViewResume() {
    }

    /**
     * run in mian thread
     */
    public void onViewPause() {
    }

    /**
     * run in mian thread
     */
    public void onViewStart() {
    }

    /**
     * run in mian thread
     */
    public void onViewStop() {
    }

    protected ActionRequest.Builder newActionBuilder(){
        return new ActionRequest.Builder().setView(getView());
    }
}
