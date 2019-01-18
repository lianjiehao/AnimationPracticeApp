package com.txm.topcodes.animationpracticeapplication.util;

import android.content.Context;

/**
 * Created by Tangxianming on 2019/1/18.
 */
public class SystemUtil {

    /**
     * dp转px
     *
     * @param context
     * @param dp
     * @return
     */
    public static int dp2px(Context context, float dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5F);
    }

    /**
     * sp转px
     *
     * @param spValue
     * @return
     */
    public static int sp2Px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

}
