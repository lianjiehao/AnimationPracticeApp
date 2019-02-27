package com.txm.topcodes.animationpracticeapplication;

import android.app.Application;

import com.txm.topcodes.animationpracticeapplication.util.GlobalUtil;

/**
 * Created by Tangxianming on 2019/2/27.
 */
public class SysApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        GlobalUtil.INSTANCE.setContext(this);
    }
}
