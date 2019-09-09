package com.paly.zv.festec;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.paly.zv.latty.delegate.LatteDelegate;
import com.paly.zv.latty.net.RestClient;
import com.paly.zv.latty.net.callback.IError;
import com.paly.zv.latty.net.callback.IFailure;
import com.paly.zv.latty.net.callback.ISuccess;

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Integer setLayout() {
        return R.layout.activity_main1;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        //testNet();
    }

    public void testNet() {
        RestClient.builder()
                .url("http://mock.fulingjie.com/mock-android/data/user_profile.json")
                .params("", "")
                .loader(getContext())
                .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
                                Log.d("HAHAHAHA",response);
                                Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();

                            }
                        })
                .error(new IError() {
                    @Override
                    public void onError(int code, String reason) {
                        Log.d("HAHAHAHA",reason);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Log.d("HAHAHAHA","失败");
                    }
                }).build().get();
    }
}
