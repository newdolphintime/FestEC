package com.paly.zv.latty.ec.sign;

import android.app.Activity;
import android.media.MediaCodec;
import android.os.Bundle;
import android.print.PrinterId;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.material.textfield.TextInputEditText;
import com.paly.zv.latty.delegate.LatteDelegate;
import com.paly.zv.latty.ec.R;
import com.paly.zv.latty.ec.R2;
import com.paly.zv.latty.net.RestClient;
import com.paly.zv.latty.net.callback.ISuccess;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

public class SignUpDelegate extends LatteDelegate {
    private ISignListener iSignListener = null;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ISignListener){
            iSignListener = (ISignListener)activity;
        }
    }

    @BindView(R2.id.edit_name)
    TextInputEditText mName = null;

    @BindView(R2.id.edit_email)
    TextInputEditText mEmail = null;

    @BindView(R2.id.edit_phone)
    TextInputEditText mPhone = null;

    @BindView(R2.id.edit_password)
    TextInputEditText mPassword = null;

    @BindView(R2.id.edit_password_re)
    TextInputEditText mPassword_re = null;

    @BindView(R2.id.btn_sign_up)
    AppCompatButton mSignUp = null;

    @BindView(R2.id.tv_link_sign_in)
    AppCompatTextView mLink_sign_in = null;

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp() {
        if (checkForm()) {
            //https://www.bilibili.com/bangumi/play/ep56485?from=search&seid=13882735553418783435
            //http://mock.fulingjie.com/mock-android/data/user_profile.json
            RestClient.builder()
                    .url("http://mock.fulingjie.com/mock-android/data/user_profile.json")
                    .params("name", mName.getText().toString())
                    .params("email",mEmail.getText().toString())
                    .params("phone",mPhone.getText().toString())
                    .params("password",mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            SignHandler.onSignUp(response,iSignListener);
                            Log.d("存储成功","存储成功");
                        }
                    })
                    .build()
                    .post();

        }

    }

    @OnClick(R2.id.tv_link_sign_in)
    void onClickSignIn(){
        start(new SignInDelegate());
    }


    private boolean checkForm() {
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String password_re = mPassword_re.getText().toString();


        boolean isPass = true;


        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }
        if (phone.isEmpty() || !Patterns.PHONE.matcher(phone).matches()) {
            mPhone.setError("电话号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }
        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("密码错误");
            isPass = false;
        } else {
            mPassword.setError(null);
        }
        if (password_re.isEmpty() || password_re.equals(password)) {
            mPassword_re.setError("密码错误");
            isPass = false;
        } else {
            mPassword_re.setError(null);
        }
        return isPass;

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
