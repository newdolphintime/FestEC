package com.paly.zv.latty.net.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitCallbacks implements Callback<String> {
    private final IRequest IREQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    public RetrofitCallbacks(IRequest irequest, ISuccess success, IFailure failure, IError error) {
        IREQUEST = irequest;
        SUCCESS = success;
        FAILURE = failure;
        ERROR = error;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if(response.isSuccessful()){
            if(call.isExecuted()){
                if (SUCCESS != null){
                    SUCCESS.onSuccess(response.body());
                }
            }
        }
        else{
           if (ERROR != null){
               ERROR.onError(response.code(),response.message());
           }
        }

    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if(FAILURE!=null){
            FAILURE.onFailure();
        }
        if(IREQUEST != null){
            IREQUEST.onRequestEnd();
        }
    }
}
