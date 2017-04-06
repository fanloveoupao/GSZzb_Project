package com.example.event;

import com.example.lang.StringUtil;

/**
 * Created by fan-gk on 2017/4/6.
 */


public class ForceLogoutEvent implements IEvent {

    private String message;

    public ForceLogoutEvent(String message) {
        if(StringUtil.isNullOrWhiteSpace(message))
            this.message = "您的账号在其他设备登录此设备被迫下线，如果不是您本人操作，请尽快修改密码。";
        else
            this.message = message;
    }

    public String getMessage() {
        return message;
    }
}