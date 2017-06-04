package com.example.api.schoolapi;

import com.example.BaseBean;
import com.example.resources.bean.BaseUserBean;
import com.example.resources.bean.IndexBean;
import com.example.resources.bean.PhpDataBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Created by fan-gk on 2017/4/14.
 */

public interface ISchoolApi {

    @GET("/stu/home.php")
    Call<BaseBean<IndexBean>> PublishCertificationProject();

    @GET("/studentproject/main.php")
    Call<BaseBean<PhpDataBean>> getPhpData();

    @FormUrlEncoded
    @POST("/studentproject/main.php")
    Call<BaseBean<PhpDataBean>> postPhpData(@Field("ID") String ID);

    @GET("/gsproject/data/login/login.php")
    Call<BaseBean<BaseUserBean>> getUsrData();
}
