package com.example.exceptions;

import com.example.lang.StringUtil;

/**
 * Created by fan-gk on 2017/2/3.
 */

public enum ErrorCode {

    NONE(0),
    UNLOGIN(1),
    ARGUMENT(3),
    IN_BLACK_LIST(4),
    SERVER(5),
    ILLEGAL_OPERATION(6),
    NO_PERMISSION(8),
    NOT_EXISTS(9),
    EXISTS(10),
    NOT_ENOUGH(11),
    NETWORK(50),

    USER_NOTEXISTS(10001),
    USER_ACCOUNTORPWDERROR(10002),
    USER_UNVERIFY(10003),
    USER_MOBILE_EXISTS(10004),
    USER_MOBILE_NOT_EXISTS(10005),
    USER_VERIFY_CODE_EXPIRED(10006),
    USER_VERIFY_CODE_ERROR(10007),
    USER_INFOUNVERIFY(10008),
    USER_NONE_PROJECT(10009),
    INTERNAL(10010),
    USER_FACE_NOT_DETECTED(10011),

    FRIEND_APPLY_NOT_EXISTENT(20001),
    FRIEND_NOT_EXISTENT(20002),
    FRIEND_NOT_FRIEND(20003),
    FRIEND_BEYOND_FRIEND_LIMIT(20004),
    FRIEND_BEYOND_OTHER_FRIEND_LIMIT(20005);


    private int code;
    public int getCode() {
        return code;
    }

    ErrorCode(int code){
        this.code = code;
    }

    @Override
    public String toString(){
        return "0x" + StringUtil.padLeft(String.valueOf(code), 8, '0');
    }

    public static ErrorCode parse(String serverStateCode){
        switch (serverStateCode){
            case "0x00000000":
                return NONE;
            case "0x00000001":
                return UNLOGIN;
            case "0x00000003":
                return ARGUMENT;
            case "0x00000004":
                return IN_BLACK_LIST;
            case "0x00000007":
            case "0x00010001":
                return USER_NOTEXISTS;
            case "0x00000006":
                return ILLEGAL_OPERATION;
            case "0x00000008":
                return NO_PERMISSION;
            case "0x00000009":
                return NOT_EXISTS;
            case "0x00000010":
                return EXISTS;
            case "0x00000011":
                return NOT_ENOUGH;
            case "0x00010002":
                return USER_ACCOUNTORPWDERROR;
            case "0x00010003":
                return USER_UNVERIFY;
            case "0x00010004":
                return USER_MOBILE_EXISTS;
            case "0x00010005":
                return USER_MOBILE_NOT_EXISTS;
            case "0x00010006":
                return USER_VERIFY_CODE_EXPIRED;
            case "0x00010007":
                return USER_VERIFY_CODE_ERROR;
            case "0x00010008":
                return USER_INFOUNVERIFY;
            case "0x00010009":
                return USER_NONE_PROJECT;
            case "0x00010010":
                return INTERNAL;
            case "0x00010011":
                return USER_FACE_NOT_DETECTED;
            case "0x00020001":
                return FRIEND_APPLY_NOT_EXISTENT;
            case "0x00020002":
                return FRIEND_NOT_EXISTENT;
            case "0x00020003":
                return FRIEND_NOT_FRIEND;
            case "0x00020004":
                return FRIEND_BEYOND_FRIEND_LIMIT;
            case "0x00020005":
                return FRIEND_BEYOND_OTHER_FRIEND_LIMIT;
            default:
                return SERVER;
        }
    }
}
