package com.txm.topcodes.animationpracticeapplication.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.txm.topcodes.animationpracticeapplication.util.LogUtil;
import com.txm.topcodes.animationpracticeapplication.R;
import com.txm.topcodes.animationpracticeapplication.util.ToastUtils;

/**
 * Created by tangxianming on 2018/4/13.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            // Activity was brought to front and not created,
            // Thus finishing this will get us to the last viewed activity
            finish();
            return;
        }
        Object o = initContentView(savedInstanceState);
        if (o instanceof Integer) {
            setContentView((Integer) o);
        } else if (o instanceof View) {
            setContentView((View) o);
        }
        initListener();
        initdata();
    }

    public abstract Object initContentView(@Nullable Bundle savedInstanceState);

    public abstract void initListener();

    public abstract void initdata();

    public Context getCurrentContext() {
        return this;
    }

    public String getLogTag() {
        return String.format("<%s>", this.getClass().getSimpleName());
    }

    /**
     * 打印调试级别日志
     */
    protected void logDebug(String msg) {
        logMessage(Log.DEBUG, msg);
    }

    /**
     * 打印信息级别日志
     */
    protected void logInfo(String msg) {
        logMessage(Log.INFO, msg);
    }

    /**
     * 打印警告级别日志
     */
    protected void logWarn(String msg) {
        logMessage(Log.WARN, msg);
    }

    /**
     * 打印错误级别日志
     */
    protected void logError(String msg) {
        logMessage(Log.ERROR, msg);
    }

    /**
     * 展示短时Toast
     */
    protected void showShortToast(String msg) {
        showToast(Toast.LENGTH_SHORT, msg);
    }

    /**
     * 展示短时Toast
     */
    protected void showShortToast(int resId) {
        showShortToast(getString(resId));
    }

    /**
     * 展示长时Toast
     */
    protected void showLongToast(String msg) {
        showToast(Toast.LENGTH_LONG, msg);
    }

    /**
     * 展示长时Toast
     */
    protected void showLongToast(int resId) {
        showLongToast(getString(resId));
    }

    /**
     * 打印日志
     *
     * @param level
     */
    private void logMessage(int level, String msg) {
        switch (level) {
            case Log.DEBUG:
                LogUtil.d(getLogTag(), msg);
                break;
            case Log.INFO:
                LogUtil.i(getLogTag(), msg);
                break;
            case Log.WARN:
                LogUtil.w(getLogTag(), msg);
                break;
            case Log.ERROR:
                LogUtil.e(getLogTag(), msg);
                break;
        }
    }

    /**
     * 展示Toast
     *
     * @param duration
     */
    private void showToast(int duration, String msg) {
        ToastUtils.showShortToast(this, msg);
    }
}