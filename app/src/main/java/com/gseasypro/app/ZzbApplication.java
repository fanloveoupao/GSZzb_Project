package com.gseasypro.app;

import android.app.Application;

import com.gseasypro.app.picasso.ImageLoader;

import app.gseasypro.com.utils.widget.ImageSelecter;

/**
 * Created by fan-gk on 2017/2/3.
 */

public class ZzbApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoader.init(this);

        ImageSelecter.init(this);
    }
}
