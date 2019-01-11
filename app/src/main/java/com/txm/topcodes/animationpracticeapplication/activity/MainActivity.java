package com.txm.topcodes.animationpracticeapplication.activity;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.txm.topcodes.animationpracticeapplication.R;
import com.txm.topcodes.animationpracticeapplication.base.BaseActivity;
import com.txm.topcodes.animationpracticeapplication.util.StatusBarUtil;

/**
 * Created by Tangxianming on 2019/1/2.
 * 选择页
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    public Object initContentView(@Nullable Bundle savedInstanceState) {
        StatusBarUtil.fullScreen(this);
        return R.layout.activity_main;
    }

    @Override
    public void initListener() {
        findViewById(R.id.btnCoordinate).setOnClickListener(this);
    }

    @Override
    public void initdata() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCoordinate:
                CoordinateActivity.start(this, ((Button) findViewById(R.id.btnCoordinate)).getText().toString());
                break;
        }
    }
}
