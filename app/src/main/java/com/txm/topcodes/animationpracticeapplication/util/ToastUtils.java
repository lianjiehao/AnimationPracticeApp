package com.txm.topcodes.animationpracticeapplication.util;

import android.content.Context;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import com.yeting.yetinglive.SysApplication;

import java.util.ArrayList;

/**
 * Created by Tangxianming on 2018/12/19.
 */
public class ToastUtils {
    private static ArrayList<Toast> toastList = new ArrayList<Toast>();

    /**
     * 解决Toast重复弹出 长时间不消失的问题
     *
     * @param context
     * @param content
     */
    public static void showShortToast(Context context, String content) {
        cancelAll();
        Toast toast = Toast.makeText(SysApplication.getInstance(), null, Toast.LENGTH_SHORT);
        toast.setText(content);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toastList.add(toast);
        toast.show();
    }

    /**
     * 解决Toast重复弹出 长时间不消失的问题
     *
     * @param context
     * @param content
     */
    public static void showLongToast(Context context, String content) {
        cancelAll();
        Toast toast = Toast.makeText(SysApplication.getInstance(), null, Toast.LENGTH_LONG);
        toast.setText(content);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toastList.add(toast);
        toast.show();
    }

    public static void cancelAll() {
        if (!toastList.isEmpty()) {
            for (Toast t : toastList) {
                t.cancel();
            }
            toastList.clear();
        }
    }

    public static void show(CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        if (text.length() < 10) {
            showShortToast(SysApplication.getInstance(), (String) text);
        } else {
            showLongToast(SysApplication.getInstance(), (String) text);
        }
    }


    public static void show(@StringRes int resId) {
        show(SysApplication.getInstance().getString(resId));
    }
}
