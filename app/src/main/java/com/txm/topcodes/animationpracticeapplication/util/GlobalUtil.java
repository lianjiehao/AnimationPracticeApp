package com.txm.topcodes.animationpracticeapplication.util;

import android.content.Context;

/**
 * Created by tangxianming on 2018/6/9.
 * 全局的变量
 **/
public enum GlobalUtil {
    INSTANCE;
    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
