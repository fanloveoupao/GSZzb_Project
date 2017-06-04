package com.example.api.schoolapi;

import com.example.DataCallAdapter;
import com.example.exceptions.GsnetException;
import com.example.exceptions.NetworkException;
import com.example.exceptions.TgnetException;
import com.example.exceptions.UnloginException;
import com.example.resources.bean.BaseUserBean;
import com.example.resources.bean.IndexBean;
import com.example.resources.bean.PhpDataBean;

/**
 * Created by fan-gk on 2017/4/14.
 */

public class ISchoolService {
    private ISchoolApi schoolApi;

    public ISchoolService(ISchoolApi schoolApi) {
        this.schoolApi = schoolApi;
    }

    public IndexBean getComplainDetail() throws UnloginException, NetworkException, TgnetException, GsnetException {
        DataCallAdapter<IndexBean> callAdapter = new DataCallAdapter<>(schoolApi.PublishCertificationProject());
        return callAdapter.getData();
    }

    public PhpDataBean getPhpData() throws UnloginException, NetworkException, TgnetException, GsnetException {
        DataCallAdapter<PhpDataBean> callAdapter = new DataCallAdapter<>(schoolApi.getPhpData());
        return callAdapter.getData();
    }

    public PhpDataBean postPhpData(String ID) throws UnloginException, NetworkException, TgnetException, GsnetException {
        DataCallAdapter<PhpDataBean> callAdapter = new DataCallAdapter<>(schoolApi.postPhpData(ID));
        return callAdapter.getData();
    }

    public BaseUserBean getUserData() throws UnloginException, NetworkException, TgnetException, GsnetException {
        DataCallAdapter<BaseUserBean> callAdapter = new DataCallAdapter<>(schoolApi.getUsrData());
        return callAdapter.getData();
    }

}
