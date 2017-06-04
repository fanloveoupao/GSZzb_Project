package com.example.api;

import com.example.DataCallAdapter;
import com.example.exceptions.GsnetException;
import com.example.exceptions.NetworkException;
import com.example.exceptions.TgnetException;
import com.example.exceptions.UnloginException;
import com.example.resources.bean.BaseUserBean;

/**
 * Created by fan-gk
 * on 2017/5/23.
 */

public class ILoginService {
    private ILoginApi iLoginApi;

    public ILoginService(ILoginApi iLoginApi) {
        this.iLoginApi = iLoginApi;
    }

    public BaseUserBean registerGSZZB(String phone,
                                      String account, String username,
                                      String password, String major) throws UnloginException, NetworkException, TgnetException, GsnetException {
        DataCallAdapter<BaseUserBean> callAdapter = new DataCallAdapter<>(
                iLoginApi.registerGSZZB(phone, account, username, password, major));
        return callAdapter.getData();

    }
    public BaseUserBean getUserData(String account) throws UnloginException, NetworkException, TgnetException, GsnetException {
        DataCallAdapter<BaseUserBean> callAdapter = new DataCallAdapter<>(iLoginApi.getUsrData(account));
        return callAdapter.getData();
    }
}
