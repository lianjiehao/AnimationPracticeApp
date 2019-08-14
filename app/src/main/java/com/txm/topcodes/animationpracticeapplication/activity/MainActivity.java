package com.txm.topcodes.animationpracticeapplication.activity;

import androidx.annotation.Nullable;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

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
            case R.id.btnCoordinate://坐标系、尺寸、视图层级
                CoordinateActivity.start(this);
                break;
            case R.id.btnViewPropertyAnimation://视图、属性动画
                ViewPropertyAnimationActivity.start(this);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                break;
            case R.id.animatedVectorDrawable://矢量图动画
                AnimatedVectorDrawableActivity.start(this);
                break;
            case R.id.btnRevealHide://淡入淡出、翻牌、揭露动画
                RevealOrHideActivity.start(this);
                break;
            case R.id.btnMove://插值器和估值器补充、Fling动画
                MoveActivity.start(this);
                break;
            case R.id.btnSpring://物理动画
                SpringActivity.start(this);
                break;
            case R.id.btnZoom://放大缩小动画
                ZoomActivity.start(this);
                break;
            case R.id.btnLayoutTransition://布局动画之LayoutTransition动画
                LayoutTransitionActivity.start(this);
                break;
            case R.id.btnLayoutAnimation://布局动画之LayoutAnimation动画
                LayoutAnimationActivity.start(this);
                break;
            case R.id.btnSceneTransition://过渡动画之场景切换
                SceneTransitionActivity.start(this);
                break;
            case R.id.btnActivityTransition://过渡动画之界面过渡
                ActivityTransitionAnimationActivity.start(this);
                break;
            case R.id.btnDemo://Overlay使用
                OverlayActivity.start(this);
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
