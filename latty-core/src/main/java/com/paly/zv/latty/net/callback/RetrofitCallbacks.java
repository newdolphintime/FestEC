package com.paly.zv.latty.net.callback;

import android.os.Handler;

import com.paly.zv.latty.ui.LatteLoader;
import com.paly.zv.latty.ui.LoaderStyle;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitCallbacks implements Callback<String> {
    private final IRequest IREQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOAD_STYLE;
    private static final Handler handler = new Handler();


    public RetrofitCallbacks(IRequest irequest, ISuccess success, IFailure failure, IError error,LoaderStyle loaderStyle) {
        IREQUEST = irequest;
        SUCCESS = success;
        FAILURE = failure;
        ERROR = error;
        LOAD_STYLE = loaderStyle;
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

        stopLoading();

    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if(FAILURE!=null){
            FAILURE.onFailure();
        }
        if(IREQUEST != null){
            IREQUEST.onRequestEnd();
        }
        stopLoading();
    }
    private void stopLoading(){
        if(LOAD_STYLE!=null){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            },1000);
        }
    }

}
