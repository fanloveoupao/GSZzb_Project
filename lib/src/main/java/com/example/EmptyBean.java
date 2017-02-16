package com.example;

import com.example.exceptions.ErrorCode;
import com.google.gson.annotations.Expose;

/**
 * Created by fan-gk on 2017/2/3.
 */

public class EmptyBean {
    @Expose
    public String state_code;

    @Expose
    public String message;

    public boolean ok() {

        if ("0x00000000".equals(state_code)) {
            return true;
        }
        return false;
    }

    public EmptyBean(){}
    public EmptyBean(ErrorCode code){
        state_code = code.toString();
        message = code.getClass().getName();
    }
}
