package com.paly.zv.latty.ec.sign;


import com.alibaba.fastjson.JSONObject;
import com.paly.zv.latty.app.AccountManager;
import com.paly.zv.latty.ec.database.DabaseManager;
import com.paly.zv.latty.ec.database.UserProfile;

public class SignHandler {

    public static void onSignUp(String response,ISignListener iSignListener){
        final JSONObject profileJson = JSONObject.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        UserProfile userProfile = new UserProfile(userId,name,avatar,gender,address);
        DabaseManager.getInstance().getmDao().insertOrReplace(userProfile);
        //已经注册并登录成功
        AccountManager.setSignState(true);
        iSignListener.onSignUpsuccess();

    }
    public static void onSignIn(String response,ISignListener iSignListener){
        final JSONObject profileJson = JSONObject.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        UserProfile userProfile = new UserProfile(userId,name,avatar,gender,address);
        DabaseManager.getInstance().getmDao().insertOrReplace(userProfile);
        //已经注册并登录成功
        AccountManager.setSignState(true);
        iSignListener.onSignInsuccess();

    }
}
