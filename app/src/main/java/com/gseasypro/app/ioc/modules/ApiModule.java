package com.gseasypro.app.ioc.modules;

import com.example.schoolapi.ISchoolApi;
import com.gseasypro.app.http.HttpApiProxyCreater;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fan-gk on 2017/4/14.
 */
@Singleton
@Module
public class ApiModule {
    private final HttpApiProxyCreater creater;

    public ApiModule() {
        creater = new HttpApiProxyCreater();
    }
    @Provides
    public ISchoolApi provideCertificationApi() {
        return creater.create(ISchoolApi.class);
    }
}
