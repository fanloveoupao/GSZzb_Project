package com.example.resources;

import com.example.exceptions.NetworkException;
import com.example.resources.bean.BaseClassBean;

import java.util.List;

/**
 * Created by fan-gk on 2017/2/20.
 */


public interface CommonDataInterface<T extends BaseClassBean> {
    List<T> getAreaData() throws NetworkException;

    List<T> getProjectType() throws NetworkException;

    List<T> getProjectStage() throws NetworkException;

}
