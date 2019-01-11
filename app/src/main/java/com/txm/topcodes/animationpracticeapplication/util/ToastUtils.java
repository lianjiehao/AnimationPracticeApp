package com.txm.topcodes.animationpracticeapplication.util;

import android.content.Context;
import android.widget.Toast;

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
        Toast toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
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
    public static Toast toast;
    public static void show(Context context, String text) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }
}
