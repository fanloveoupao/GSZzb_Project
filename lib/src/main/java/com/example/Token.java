package com.example;

import com.example.lang.StringUtil;
import com.example.utils.DateUtil;
import com.google.gson.annotations.Expose;

import java.util.Date;

/**
 * Created by fan-gk on 2017/4/14.
 */
public class Token {
    @Expose
    private String accessToken;
    @Expose
    private String refreshToken;
    @Expose
    private Date expired;

    @Expose
    private long uid;

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Date getExpired() {
        return expired;
    }

    public long getUid() {
        return uid;
    }

    public boolean isExpired(){
        return expired == null || DateUtil.now().after(expired);
    }

    public boolean isRefreshExpired(){
        return expired == null || StringUtil.isNullOrWhiteSpace(refreshToken) || DateUtil.now().after(DateUtil.addDays(expired, 28));
    }

    public Token(String accessToken, String refreshToken, Date expired, long uid) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expired = expired;
        this.uid = uid;
    }
}
