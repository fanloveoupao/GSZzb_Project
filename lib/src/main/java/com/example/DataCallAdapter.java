package com.example;

import com.example.exceptions.GsnetException;
import com.example.exceptions.NetworkException;
import com.example.exceptions.TgnetException;
import com.example.exceptions.UnloginException;

import retrofit2.Call;

/**
 * Created by fan-gk on 2017/2/3.
 */

public class DataCallAdapter<D> extends CallAdapter<BaseBean<D>> {
    public DataCallAdapter(Call<BaseBean<D>> call) {
        super(call);
    }

    public D getData() throws GsnetException, NetworkException, UnloginException, TgnetException {
        ok();
        return body.data;
    }
}