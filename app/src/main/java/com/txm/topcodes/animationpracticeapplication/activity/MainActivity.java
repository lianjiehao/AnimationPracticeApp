package com.txm.topcodes.animationpracticeapplication.activity;

import androidx.annotation.Nullable;

import android.os.Bundle;
import android.view.MenuItem;
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
    public void initView() {
        findViewById(R.id.btnCoordinate).setOnClickListener(this);
        findViewById(R.id.btnViewPropertyAnimation).setOnClickListener(this);
        findViewById(R.id.animatedVectorDrawable).setOnClickListener(this);
        findViewById(R.id.btnRevealHide).setOnClickListener(this);
        findViewById(R.id.btnMove).setOnClickListener(this);
        findViewById(R.id.btnSpring).setOnClickListener(this);
        findViewById(R.id.btnZoom).setOnClickListener(this);
        findViewById(R.id.btnLayoutTransition).setOnClickListener(this);
        findViewById(R.id.btnDemo).setOnClickListener(this);
        findViewById(R.id.btnLayoutAnimation).setOnClickListener(this);
        findViewById(R.id.btnSceneTransition).setOnClickListener(this);
        findViewById(R.id.btnActivityTransition).setOnClickListener(this);
    }

    @Override
    public void initdata() {

    }

    @Override
    public void action() {

    }

    @Override
    public boolean hasToolbar() {
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCoordinate:
                CoordinateActivity.start(this);
                break;
            case R.id.btnViewPropertyAnimation:
                ViewPropertyAnimationActivity.start(this);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                break;
            case R.id.animatedVectorDrawable:
                AnimatedVectorDrawableActivity.start(this);
                break;
            case R.id.btnRevealHide:
                RevealOrHideActivity.start(this);
                break;
            case R.id.btnMove:
                MoveActivity.start(this);
                break;
            case R.id.btnSpring:
                SpringActivity.start(this);
                break;
            case R.id.btnZoom:
                ZoomActivity.start(this);
                break;
            case R.id.btnLayoutTransition:
                LayoutTransitionActivity.start(this);
                break;
            case R.id.btnLayoutAnimation:
                LayoutAnimationActivity.start(this);
                break;
            case R.id.btnSceneTransition:
                SceneTransitionActivity.start(this);
                break;
            case R.id.btnActivityTransition:
                ActivityTransitionAnimationActivity.start(this);
                break;
            case R.id.btnDemo:
                DemoActivity.start(this);
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
