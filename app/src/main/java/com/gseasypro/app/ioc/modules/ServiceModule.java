package com.gseasypro.app.ioc.modules;

import com.example.api.ILoginApi;
import com.example.api.ILoginService;
import com.example.api.schoolapi.ISchoolApi;
import com.example.api.schoolapi.ISchoolService;
import com.gseasypro.app.http.HttpApiProxyCreater;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fan-gk on 2017/4/14.
 */

@Module
public class ServiceModule {
    private HttpApiProxyCreater creater;
    private ISchoolApi schoolApi;
    private ILoginApi iLoginApi;

    public ServiceModule() {
        this.creater = new HttpApiProxyCreater();
        schoolApi = creater.create(ISchoolApi.class);
        iLoginApi = creater.create(ILoginApi.class);
    }

    @Provides
    public ISchoolService provideISchoolService() {
        return new ISchoolService(schoolApi);
    }

    @Provides
    public ILoginService providesILoginService() {
        return new ILoginService(iLoginApi);
    }
}
