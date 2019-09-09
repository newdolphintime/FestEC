package com.paly.zv.latty.app;

import com.paly.zv.latty.util.storage.LattePreference;

import java.security.PublicKey;

public class AccountManager {
    private enum SignTag {
        SIGN_TAG
    }

    //保存用户是否登录
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    private static boolean isSignIn() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker iUserChecker) {
        if (isSignIn()) {
            iUserChecker.onSignIn();
        } else {
            iUserChecker.onNotSignIn();
        }
    }
}
