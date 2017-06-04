package com.example;

import com.example.exceptions.ErrorCode;
import com.example.exceptions.GsnetException;
import com.example.exceptions.NetworkException;
import com.example.exceptions.TgnetException;
import com.example.exceptions.UnloginException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by fan-gk on 2017/2/3.
 */

public class CallAdapter<T extends EmptyBean> {
    private NetworkException ne;
    private UnloginException ue;
    private TgnetException te;

    protected T body = null;

    private Call<T> call;

    public CallAdapter(Call<T> call) {
        this.call = call;
    }

    public void ok() throws NetworkException, TgnetException, UnloginException {
        Response<T> response = null;
        if(!call.isExecuted()) {
            try {
                response = call.execute();
            } catch (Exception ex) {
                ne = new NetworkException("网络有点卡，请稍后再试", ex);
            }
            if(response == null) {
                if(ne == null)
                    ne = new NetworkException("服务器错误，请稍后再试");
            } else {
                if(!response.isSuccessful())
                    te = new TgnetException(ErrorCode.SERVER, "服务不可用，请稍后再试.错误码：" + response.code());
                else {
                    T body = response.body();
                    if (body == null)
                        te = new TgnetException(ErrorCode.SERVER, "服务不可用，请稍后再试");
                    else if (!body.ok()) {
                        ErrorCode errorCode = ErrorCode.parse(body.state_code);
                        if(errorCode == ErrorCode.UNLOGIN)
                            ue = new UnloginException(UnloginException.TYPE_OTHER_DEVICE);
                        else
                            te = new TgnetException(errorCode, body.message);
                    }
                    else
                        this.body = body;
                }
            }
        }
        if(ne != null)
            throw ne;
        if(te != null)
            throw te;
        if(ue != null)
            throw ue;
    }
}
