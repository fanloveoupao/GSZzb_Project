package com.example;

import retrofit2.Call;

/**
 * Created by fan-gk on 2017/2/3.
 */


public class EmptyCallAdpter extends CallAdapter<EmptyBean> {
    public EmptyCallAdpter(Call<EmptyBean> call) {
        super(call);
    }
}
