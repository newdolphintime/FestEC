package com.paly.zv.latty.net;

import com.paly.zv.latty.net.callback.IError;
import com.paly.zv.latty.net.callback.IFailure;
import com.paly.zv.latty.net.callback.IRequest;
import com.paly.zv.latty.net.callback.ISuccess;
import com.paly.zv.latty.net.callback.RetrofitCallbacks;

import java.util.Map;
import java.util.WeakHashMap;


import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class RestClient {
    private final String URL;
    private final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    private final IRequest IREQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody REQUESTBODY;

    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest request, ISuccess success,
                      IFailure failure, IError error,
                      RequestBody requestBody) {
        this.URL = url;
        PARAMS.putAll(params);
        this.IREQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.REQUESTBODY = requestBody;
    }
    public static RestClientBulider builder(){
        return new RestClientBulider();
    }
    private Callback<String> getRetrofitCallback(){
        return new RetrofitCallbacks(IREQUEST,SUCCESS,FAILURE,ERROR);
    }
    private void request (HttpMethod method){
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if(IREQUEST != null){
            IREQUEST.onRequestStart();
        }
        switch (method){
            case GET:
                call = service.get(URL,PARAMS);
                break;
            case POST:
                call = service.post(URL,PARAMS);
                break;
            case PUT:
                call = service.put(URL,PARAMS);
                break;
            case DELETE:
                call = service.delete(URL,PARAMS);
                break;
            default:
                break;
        }
        if(call != null){
            call.enqueue(getRetrofitCallback());
        }
    }
    public final void get(){
        request(HttpMethod.GET);
    }
    public final void post(){
        request(HttpMethod.POST);
    }
    public final void put(){
        request(HttpMethod.PUT);
    }
    public final void delete(){
        request(HttpMethod.DELETE);
    }
}
