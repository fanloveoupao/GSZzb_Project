package com.gseasypro.app.ioc.modules;

import com.example.schoolapi.ISchoolApi;
import com.example.schoolapi.ISchoolService;
import com.gseasypro.app.http.HttpApiProxyCreater;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fan-gk on 2017/4/14.
 */

@Module
public class ServiceModule {
    private HttpApiProxyCreater creater;

    public ServiceModule() {
        this.creater = new HttpApiProxyCreater();
    }

    @Provides
    public ISchoolApi provideISchoolApi() {
        return creater.create(ISchoolApi.class);
    }

    @Provides
    public ISchoolService provideISchoolService() {
        return new ISchoolService();
    }
}
