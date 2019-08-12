package com.txm.topcodes.animationpracticeapplication.util;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;


import com.txm.topcodes.animationpracticeapplication.SysApplication;

import java.util.ArrayList;

import androidx.annotation.StringRes;

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
        Toast toast = Toast.makeText(GlobalUtil.INSTANCE.getContext(), null, Toast.LENGTH_SHORT);
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
        Toast toast = Toast.makeText(GlobalUtil.INSTANCE.getContext(), null, Toast.LENGTH_LONG);
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
            showShortToast(GlobalUtil.INSTANCE.getContext(), (String) text);
        } else {
            showLongToast(GlobalUtil.INSTANCE.getContext(), (String) text);
        }
    }


    public static void show(@StringRes int resId) {
        show(GlobalUtil.INSTANCE.getContext().getString(resId));
    }
}
