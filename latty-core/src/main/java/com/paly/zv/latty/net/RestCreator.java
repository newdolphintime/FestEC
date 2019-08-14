package com.paly.zv.latty.net;

import android.util.Log;

import com.paly.zv.latty.app.ConfigKeys;
import com.paly.zv.latty.app.Latty;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RestCreator {


    private static final class OKHttpClientHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();

        private static final ArrayList<Interceptor> INTERCEPTORS =
                (ArrayList<Interceptor>) Latty.getConfigurations().get(ConfigKeys.INTERCEPTORS.name());

        private static OkHttpClient.Builder addInterceptors() {
            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
                for (Interceptor interceptor : INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor);
                }
                Log.d("记载了拦截器", "addInterceptors: ");
            }else{
//            Log.d("没有记载了拦截器", Integer.toString(INTERCEPTORS.size()));
           }
            return BUILDER;
        }

        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptors().connectTimeout(TIME_OUT, TimeUnit.SECONDS).build();

    }

    private static final class RetrofitHolder {
        private static final String BASE_URL = (String) Latty.getConfigurations().get(ConfigKeys.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT = new
                Retrofit.Builder().baseUrl(BASE_URL)
                .client(OKHttpClientHolder.OK_HTTP_CLIENT).
                        addConverterFactory(ScalarsConverterFactory.create()).build();
    }

    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }
}
