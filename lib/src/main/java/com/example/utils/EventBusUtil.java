package com.example.utils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by fan-gk on 2017/3/2.
 */
public class EventBusUtil {
    public static EventBus getInstance() {
        return EventBus.getDefault();
    }
}
