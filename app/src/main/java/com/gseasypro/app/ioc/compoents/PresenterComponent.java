package com.gseasypro.app.ioc.compoents;

import com.example.presenter.BeautifulGsPresenter;
import com.example.presenter.GsBestPresenter;
import com.example.presenter.LoginPresenter;
import com.example.presenter.PhpDataTestPresenter;
import com.example.presenter.RegisterPresenter;
import com.gseasypro.app.ioc.modules.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by fan-gk on 2017/4/14
 */
@Singleton
@Component(modules = ServiceModule.class)
public interface PresenterComponent {
    void inject(BeautifulGsPresenter presenter);

    void inject(GsBestPresenter presenter);

    void inject(PhpDataTestPresenter presenter);

    void inject(RegisterPresenter presenter);

    void inject(LoginPresenter presenter);
}
