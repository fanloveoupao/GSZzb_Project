package com.gseasypro.app.ioc.compoents;

import com.example.presenter.BeautifulGsPresenter;
import com.gseasypro.app.ioc.modules.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by fan-gk on 2017/4/14.
 */
@Singleton
@Component(modules = {ServiceModule.class})
public interface PresenterComponent {
    void inject(BeautifulGsPresenter presenter);
}
