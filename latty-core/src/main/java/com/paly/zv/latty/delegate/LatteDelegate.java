package com.paly.zv.latty.delegate;

public abstract class LatteDelegate extends PermissionCheckerDelegate {

    public <T extends LatteDelegate> T getParentDelegate(){
        return (T)getParentFragment();
    }
}
