package com.paly.zv.latty.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.paly.zv.latty.delegate.LatteDelegate;

public abstract class BottomItemDelegate extends LatteDelegate implements View.OnKeyListener {
    private long mExitTime = 0;
    private static final int EXIT_TIME = 2000;

    @Override
    public void onResume() {
        super.onResume();
        final View rootview = getView();
        if(rootview != null){
            rootview.setFocusableInTouchMode(true);
            rootview.requestFocus();
            rootview.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis()-mExitTime)>EXIT_TIME) {
                Toast.makeText(getContext(),"双击退出",Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            }
            else {
                _mActivity.finish();
                if(mExitTime!=0){mExitTime = 0;}
            }
            return true;
        }
        return false;
    }
}
