package com.txm.topcodes.animationpracticeapplication.activity;

import androidx.annotation.Nullable;

import android.os.Bundle;
import android.view.View;
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
        findViewById(R.id.btnViewPropertyAnimation).setOnClickListener(this);
        findViewById(R.id.animatedVectorDrawable).setOnClickListener(this);
        findViewById(R.id.btnRevealHide).setOnClickListener(this);
        findViewById(R.id.btnMove).setOnClickListener(this);
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
            case R.id.btnViewPropertyAnimation:
                ViewPropertyAnimationActivity.start(this, ((Button) findViewById(R.id.btnViewPropertyAnimation)).getText().toString());
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                break;
            case R.id.animatedVectorDrawable:
                AnimatedVectorDrawableActivity.start(this, ((Button) findViewById(R.id.animatedVectorDrawable)).getText().toString());
                break;
            case R.id.btnRevealHide:
                RevealOrHideActivity.start(this, ((Button) findViewById(R.id.btnRevealHide)).getText().toString());
                break;
            case R.id.btnMove:
                MoveActivity.start(this, ((Button) findViewById(R.id.btnMove)).getText().toString());
                break;
        }
    }
}
