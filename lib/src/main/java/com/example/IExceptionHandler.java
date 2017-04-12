package com.example;

import com.example.exceptions.NetworkException;

/**
 * Created by fan-gk on 2017/2/18.
 */

public interface IExceptionHandler {
    void onNeedLogin(boolean otherDevice);
    void onException(Exception e);
    void onException(Exception e, boolean finish);
    void onException(ActionRequest request, NetworkException e);
    void onWarn(String message);
}

