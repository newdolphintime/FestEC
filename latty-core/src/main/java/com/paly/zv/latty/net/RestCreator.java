package com.paly.zv.latty.net;

import com.paly.zv.latty.app.ConfigKeys;
import com.paly.zv.latty.app.Latty;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RestCreator {

    public static final class ParamsHolder {
        public static final WeakHashMap<String,Object> PARAMS = new WeakHashMap<>();
    }

    public static  WeakHashMap<String,Object> getParams(){

        return ParamsHolder.PARAMS;
    }

    private static final class OKHttpClientHolder{
        private static final int TIME_OUT = 60;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder().connectTimeout(TIME_OUT, TimeUnit.SECONDS).build();

    }
    private static final class RetrofitHolder {
        private static final String BASE_URL =(String) Latty.getConfigurations().get(ConfigKeys.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT=new
                Retrofit.Builder().baseUrl(BASE_URL)
                .client(OKHttpClientHolder.OK_HTTP_CLIENT).
                addConverterFactory(ScalarsConverterFactory.create()).build();
    }
    private static final class RestServiceHolder{
        private static final RestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    public static RestService getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }
}
