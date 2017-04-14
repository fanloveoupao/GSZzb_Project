package com.example.schoolapi;

import com.example.DataCallAdapter;
import com.example.exceptions.GsnetException;
import com.example.exceptions.NetworkException;
import com.example.exceptions.TgnetException;
import com.example.exceptions.UnloginException;
import com.example.resources.bean.IndexBean;

/**
 * Created by fan-gk on 2017/4/14.
 */

public class ISchoolService {
    private ISchoolApi schoolApi;

    public ISchoolService(ISchoolApi schoolApi) {
        this.schoolApi = schoolApi;
    }

    public IndexBean getComplainDetail() throws UnloginException, NetworkException, TgnetException, GsnetException {
        String content = "测试";
        DataCallAdapter<IndexBean> callAdapter = new DataCallAdapter<>(schoolApi.PublishCertificationProject(content));
        return callAdapter.getData();
    }
}
