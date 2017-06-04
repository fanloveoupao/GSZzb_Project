package com.gseasypro.app;

import android.app.Application;

import com.example.lang.StringUtil;
import com.example.repositories.ClientRepositories;
import com.example.ui.ClientSettings;
import com.gseasypro.app.login.LoginActivity;
import com.gseasypro.app.picasso.ImageLoader;

import app.gseasypro.com.utils.BaseActivity;
import app.gseasypro.com.utils.repositories.SharedPreferencesRepositoryProvider;
import app.gseasypro.com.utils.ui.ActivitiesHelper;
import app.gseasypro.com.utils.utils.AndroidUtil;
import app.gseasypro.com.utils.utils.DeviceUtil;
import app.gseasypro.com.utils.widget.ImageSelecter;

/**
 * Created by fan-gk on 2017/2/3.
 */

public class ZzbApplication extends Application {
    public static final boolean DEBUG = true;

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoader.init(this);

        ImageSelecter.init(this);
        InitRepositories();

        ClientSettings clientSettings = new ClientSettings();
        if (StringUtil.isNullOrWhiteSpace(clientSettings.getUuid())) {
            clientSettings.setUuid(AndroidUtil.getAndroidId(this));
        }

        ClientSettings.DeviceInfo deviceInfo = new ClientSettings.DeviceInfo(
                DeviceUtil.getDeviceDisplay(this),
                DeviceUtil.getOSVersion(),
                DeviceUtil.getDeviceModel(),
                AndroidUtil.getVersionName(this),
                AndroidUtil.getVersionCode(this));
        clientSettings.setDeviceInfo(deviceInfo);
    }

    protected <T> boolean existsActivity(Class<T> classOfActivity) {
        return ActivitiesHelper.existsActivity(classOfActivity);
    }

    protected <T> void finishAllActivitiesButThis(Class<T> classOfActivity) {
        ActivitiesHelper.finishAllButThis(classOfActivity);
    }

    public void launchLoginActivityForForce(BaseActivity currentActivity) {
        currentActivity.launch(LoginActivity.class, false);
        finishAllActivitiesButThis(LoginActivity.class);
    }

    public void exit() {
        ActivitiesHelper.finishAll();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    private void InitRepositories() {
        ClientRepositories
                .getInstance()
                .setSharedPreferencesRepositoryProvider(new SharedPreferencesRepositoryProvider(this));
    }

}
