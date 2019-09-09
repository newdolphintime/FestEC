package com.paly.zv.latty.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.material.textfield.TextInputEditText;
import com.paly.zv.latty.delegate.LatteDelegate;
import com.paly.zv.latty.ec.R;
import com.paly.zv.latty.ec.R2;
import com.paly.zv.latty.net.RestClient;
import com.paly.zv.latty.net.callback.IError;
import com.paly.zv.latty.net.callback.IFailure;
import com.paly.zv.latty.net.callback.ISuccess;

import butterknife.BindView;
import butterknife.OnClick;

public class SignInDelegate extends LatteDelegate {

    private ISignListener iSignListener = null;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ISignListener){
            iSignListener = (ISignListener)activity;
        }
    }


    @BindView(R2.id.edit_email)
    TextInputEditText mEmail = null;

    @BindView(R2.id.edit_password)
    TextInputEditText mPassword = null;



    @OnClick(R2.id.btn_sign_in)
    void onClickSignUp() {
        Toast.makeText(getContext(),"CLICK",Toast.LENGTH_SHORT).show();
        if (checkForm()) {
            RestClient.builder()
                    .url("http://mock.fulingjie.com/mock-android/data/user_profile.json")
                    //.params("", "")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Toast.makeText(getContext(),response,Toast.LENGTH_SHORT).show();
                            SignHandler.onSignIn(response,iSignListener);

                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String reason) {
                            Toast.makeText(getContext(),reason,Toast.LENGTH_SHORT).show();
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            Toast.makeText(getContext(),"失败",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .build()
                    .get();

        }

    }
    @OnClick(R2.id.tv_link_sign_up)
    void onClickSignIn(){
        start(new SignUpDelegate());
    }


    private boolean checkForm() {

        final String email = mEmail.getText().toString();

        final String password = mPassword.getText().toString();



        boolean isPass = true;



        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("密码错误");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
