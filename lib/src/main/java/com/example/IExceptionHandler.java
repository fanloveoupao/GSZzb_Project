package com.example;

import com.example.exceptions.NetworkException;

/**
 * Created by fan-gk on 2017/2/18.
 */

public interface IExceptionHandler {

    void onException(Exception e);

    void onException(Exception e, boolean finish);

    void onException(NetworkException e);

    void onWarn(String message);
}

