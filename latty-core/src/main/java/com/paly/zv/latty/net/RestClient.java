package com.paly.zv.latty.net;

import android.content.Context;

import com.paly.zv.latty.net.callback.IError;
import com.paly.zv.latty.net.callback.IFailure;
import com.paly.zv.latty.net.callback.IRequest;
import com.paly.zv.latty.net.callback.ISuccess;
import com.paly.zv.latty.net.callback.RetrofitCallbacks;
import com.paly.zv.latty.net.download.DownloadHandler;
import com.paly.zv.latty.ui.LatteLoader;
import com.paly.zv.latty.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;


import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class RestClient {
    private final String URL;
    private final WeakHashMap<String, Object> PARAMS ;
    private final IRequest IREQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody REQUESTBODY;
    private final LoaderStyle LOADER_STYLE;
    private final File FILE;
    private final Context CONTEXT;

    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;


    public RestClient(String url,
                      WeakHashMap<String, Object> params,
                      String downloadDir,
                      String extension,
                      String name,
                      IRequest request, ISuccess success,
                      IFailure failure, IError error,
                      RequestBody requestBody,
                      LoaderStyle loaderStyle,
                      File file,
                      Context context

    ) {
        this.URL = url;
        this.PARAMS=params;
        this.IREQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.REQUESTBODY = requestBody;
        this.LOADER_STYLE = loaderStyle;
        this.CONTEXT = context;
        this.FILE = file;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
    }

    public static RestClientBulider builder() {
        return new RestClientBulider();
    }

    private Callback<String> getRetrofitCallback() {
        return new RetrofitCallbacks(IREQUEST, SUCCESS, FAILURE, ERROR, LOADER_STYLE);
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (IREQUEST != null) {
            IREQUEST.onRequestStart();
        }
        if (LOADER_STYLE != null) {
            LatteLoader.showloading(CONTEXT, LOADER_STYLE);
        }
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.post_raw(URL, REQUESTBODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.post_raw(URL, REQUESTBODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body =MultipartBody.Part.createFormData("file",FILE.getName(),requestBody);
                call = service.upload(URL,body);
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRetrofitCallback());
        }
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        if (REQUESTBODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("参数必须为空");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void put() {
        if (REQUESTBODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("参数必须为空");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }
    public final void upload() {
        request(HttpMethod.UPLOAD);
    }
    public final void download() {
        new DownloadHandler(this.URL,
                this.PARAMS,this.IREQUEST,
                this.SUCCESS,this.FAILURE,
                this.ERROR,this.DOWNLOAD_DIR,
                this.EXTENSION,this.NAME).handleDownload();
    }
}
