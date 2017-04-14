package com.gseasypro.app.http;

import android.app.Application;

import com.example.Config;


/**
 * Created by fan-gk on 2017/4/14.
 */

public class DnsService {
    private static DnsService instance;


    public static void init(Application context) {
        instance = new DnsService(context);
    }

    public static DnsService getInstance() {
        return instance;
    }

    private DnsService(Application context) {

    }

    public String getBaseUrl(String host) {

        return Config.getBaseUrl(host);

    }
}

