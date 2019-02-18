package com.txm.topcodes.animationpracticeapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.txm.topcodes.animationpracticeapplication.R;
import com.txm.topcodes.animationpracticeapplication.base.BaseActivity;

/**
 * Created by Tangxianming on 2019/2/18.
 */
public class LottieActivity extends BaseActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, LottieActivity.class);
        context.startActivity(starter);
    }

    @Override
    public Object initContentView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_lottie;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {

    }
}
