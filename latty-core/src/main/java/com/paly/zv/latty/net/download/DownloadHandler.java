package com.paly.zv.latty.net.download;

import android.os.AsyncTask;

import com.paly.zv.latty.net.RestCreator;
import com.paly.zv.latty.net.callback.IError;
import com.paly.zv.latty.net.callback.IFailure;
import com.paly.zv.latty.net.callback.IRequest;
import com.paly.zv.latty.net.callback.ISuccess;

import java.security.PublicKey;
import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownloadHandler {
    private final String URL;
    private final WeakHashMap<String, Object> PARAMS;
    private final IRequest IREQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    public DownloadHandler(String url,
                           WeakHashMap<String, Object> params,
                           IRequest irequest,
                           ISuccess success,
                           IFailure failure,
                           IError error,
                           String download_dir,
                           String extension,
                           String name) {
        URL = url;
        PARAMS = params;
        IREQUEST = irequest;
        SUCCESS = success;
        FAILURE = failure;
        ERROR = error;
        DOWNLOAD_DIR = download_dir;
        EXTENSION = extension;
        NAME = name;
    }

    public final void handleDownload() {
        if (IREQUEST != null) {
            IREQUEST.onRequestStart();
        }
        RestCreator.getRestService().download(URL, PARAMS).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    final SaveFileTask saveFileTask = new SaveFileTask(IREQUEST, SUCCESS);
                    final ResponseBody responseBody = response.body();
                    saveFileTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, DOWNLOAD_DIR, EXTENSION, responseBody, NAME);

                    if (saveFileTask.isCancelled()) {
                        if (IREQUEST != null) {
                            IREQUEST.onRequestEnd();
                        }
                    }
                }else {
                    if(ERROR !=null){
                        ERROR.onError(response.code(),response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if(FAILURE != null ){
                    FAILURE.onFailure();
                }

            }
        });
    }
}
