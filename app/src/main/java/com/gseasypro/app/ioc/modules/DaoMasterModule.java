package com.gseasypro.app.ioc.modules;

import com.gseasypro.app.ZzbApplication;

import dagger.Module;

/**
 * Created by fan-gk on 2017/4/15.
 */

@Module
public class DaoMasterModule {

    public DaoMasterModule(ZzbApplication context) {
        this.context = context;
    }

    private ZzbApplication context;

}