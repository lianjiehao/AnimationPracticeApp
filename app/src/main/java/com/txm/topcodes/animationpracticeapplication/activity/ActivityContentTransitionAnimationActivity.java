package com.txm.topcodes.animationpracticeapplication.activity;

import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.txm.topcodes.animationpracticeapplication.R;
import com.txm.topcodes.animationpracticeapplication.base.BaseActivity;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

/**
 * @author :created by tangxianming
 * @date: 2019/7/23
 * @desc: Android过渡动画之界面过渡：
 * 不带共享元素的Content Transition
 */
public class ActivityContentTransitionAnimationActivity extends BaseActivity {
    @Override
    public Object initContentView(@Nullable Bundle savedInstanceState) {
        // inside your activity (if you did not enable transitions in your theme)
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        return R.layout.activity_activity_content_transition_animation;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initdata() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setupTransition();
        }
    }

    @Override
    public void action() {

    }

    @Override
    public boolean hasToolbar() {
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupTransition() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.entertransition);
        transition.setDuration(1000);
        transition.excludeTarget(android.R.id.statusBarBackground, true);
        transition.excludeTarget(android.R.id.navigationBarBackground, true);
        transition.excludeTarget(R.id.appbar, true);
        getWindow().setEnterTransition(transition);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

}
