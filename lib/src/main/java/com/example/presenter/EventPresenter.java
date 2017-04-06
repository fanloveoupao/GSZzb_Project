package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;
import com.example.event.ForceLogoutEvent;
import com.example.utils.EventBusUtil;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by fan-gk on 2017/4/6.
 */


public abstract class EventPresenter<E extends IView> extends BasePresenter<E> {


    public EventPresenter(E viewer) {
        super(viewer);
    }

    @Override
    public void onViewCreate() {
        EventBusUtil.getInstance().register(this);
        super.onViewCreate();
    }

    @Override
    public void onViewDestroy() {
        super.onViewDestroy();
        EventBusUtil.getInstance().unregister(this);
    }

    @Subscribe
    public void doNothing(ForceLogoutEvent event){}
}

