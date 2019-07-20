package com.paly.zv.latty.net;

import com.paly.zv.latty.net.callback.IError;
import com.paly.zv.latty.net.callback.IFailure;
import com.paly.zv.latty.net.callback.IRequest;
import com.paly.zv.latty.net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

import static com.paly.zv.latty.net.HttpMethod.DELETE;
import static com.paly.zv.latty.net.HttpMethod.GET;
import static com.paly.zv.latty.net.HttpMethod.POST;
import static com.paly.zv.latty.net.HttpMethod.PUT;

public class RestClientBulider {
    private  String mURL;
    private static final   Map<String,Object> PARAMS = RestCreator.getParams();
    private  IRequest mIREQUEST;
    private  ISuccess mSUCCESS;
    private  IFailure mFAILURE;
    private  IError mERROR;
    private  RequestBody mRequestBody;
    //不允许外部NEW
    RestClientBulider(){}
    public final RestClientBulider url (String url){
        this.mURL = url;
        return this;
    }
    public final RestClientBulider params (WeakHashMap<String,Object> params){
        PARAMS.putAll(params);
        return this;
    }
    public final RestClientBulider params (String key,Object value){

        PARAMS.put(key,value);
        return this;
    }
    public final RestClientBulider raw (String raw){
        this.mRequestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }
    public final RestClientBulider success (ISuccess iSuccess){
        this.mSUCCESS = iSuccess;
        return this;
    }
    public final RestClientBulider failure (IFailure iFailure){
        this.mFAILURE = iFailure;
        return this;
    }
    public final RestClientBulider error (IError iError){
        this.mERROR = iError;
        return this;
    }
    public final RestClientBulider onRequest (IRequest iRequest){
        this.mIREQUEST = iRequest;
        return this;
    }

    public final RestClient build(){
        return new RestClient(this.mURL,this.PARAMS,this.mIREQUEST,this.mSUCCESS,this.mFAILURE,this.mERROR,this.mRequestBody);
    }

}
