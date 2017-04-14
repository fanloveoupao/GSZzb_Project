package com.example.schoolapi;

/**
 * Created by fan-gk on 2017/4/14.
 */

public class ISchoolFactory {
    private ISchoolApi schoolApi;

    public ISchoolFactory(ISchoolApi schoolApi) {
        this.schoolApi = schoolApi;
    }

    public ISchoolService createSchoolService() {
        return new ISchoolService(schoolApi);
    }
}
