package com.gseasypro.app;

import android.app.Application;

import com.gseasypro.app.ioc.modules.DaoMasterModule;
import com.gseasypro.app.picasso.ImageLoader;

import app.gseasypro.com.utils.ui.ActivitiesHelper;
import app.gseasypro.com.utils.widget.ImageSelecter;

/**
 * Created by fan-gk on 2017/2/3.
 */

public class ZzbApplication extends Application {
    public static final boolean DEBUG = true;
    public final DaoMasterModule daoMasterModule = new DaoMasterModule(this);

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoader.init(this);

        ImageSelecter.init(this);

    }

    protected <T> boolean existsActivity(Class<T> classOfActivity) {
        return ActivitiesHelper.existsActivity(classOfActivity);
    }

    protected <T> void finishAllActivitiesButThis(Class<T> classOfActivity) {
        ActivitiesHelper.finishAllButThis(classOfActivity);
    }

    public void exit() {
        ActivitiesHelper.finishAll();
        android.os.Process.killProcess(android.os.Process.myPid());
    }


}
