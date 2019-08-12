package com.paly.zv.latty.util.timer;


import java.util.TimerTask;

public class BaseTimerTask extends TimerTask {
    private ItimerListener itimerListener = null;

    public BaseTimerTask(ItimerListener itimerListener) {
        this.itimerListener = itimerListener;
    }

    @Override
    public void run() {
        if (itimerListener!=null){
            itimerListener.onTimer();
        }
    }
}
