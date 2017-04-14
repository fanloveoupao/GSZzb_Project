package com.example.schoolapi;

import com.example.BaseBean;
import com.example.resources.bean.IndexBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by fan-gk on 2017/4/14.
 */

public interface ISchoolApi {
    @FormUrlEncoded
    @POST("/app/index.php")
    Call<BaseBean<IndexBean>> PublishCertificationProject(
            @Field("name") String name);

}
