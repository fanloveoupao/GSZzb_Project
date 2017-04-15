package com.gseasypro.app.ioc.modules;

import com.example.schoolapi.ISchoolApi;
import com.example.schoolapi.ISchoolFactory;
import com.example.schoolapi.ISchoolService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fan-gk on 2017/4/14.
 */

@Module(includes = ApiModule.class)
public class ServiceModule {
    @Provides
    public ISchoolService provideISchoolService(ISchoolApi schoolApi) {
        return new ISchoolFactory(schoolApi).createSchoolService();
    }
}
