package com.example.api;

import com.example.BaseBean;
import com.example.resources.bean.BaseUserBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by fan-gk
 * on 2017/5/23.
 */

public interface ILoginApi {
    /**
     * @param phone
     * @param account
     * @param username
     * @param password
     * @param major
     * @return
     */

    @GET("/gsproject/data/login/register.php")
    Call<BaseBean<BaseUserBean>> registerGSZZB(
            @Query("phone") String phone,
            @Query("account") String account,
            @Query("username") String username,
            @Query("password") String password,
            @Query("major") String major
    );

    /**
     * @param account
     * @return
     */
    @GET("/gsproject/data/login/login.php")
    Call<BaseBean<BaseUserBean>> getUsrData(@Query("account") String account);
}
