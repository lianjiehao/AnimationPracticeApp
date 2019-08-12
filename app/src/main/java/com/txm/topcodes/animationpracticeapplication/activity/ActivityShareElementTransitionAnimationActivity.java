package com.txm.topcodes.animationpracticeapplication.activity;

import android.os.Build;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.MenuItem;

import com.txm.topcodes.animationpracticeapplication.R;
import com.txm.topcodes.animationpracticeapplication.base.BaseActivity;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;

/**
 * @author :created by tangxianming
 * @date: 2019/7/23
 * @desc: Android过渡动画之界面过渡：
 * 共享元素的ShareElement Transition
 */
public class ActivityShareElementTransitionAnimationActivity extends BaseActivity {
    Toolbar toolbar;
    @Override
    public Object initContentView(@Nullable Bundle savedInstanceState) {
        // inside your activity (if you did not enable transitions in your theme)
        //getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        return R.layout.activity_activity_content_transition_animation;
    }

    @Override
    public void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setupTransition();
        }
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupTransition() {
        getWindow().setEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.entertransition));
        getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.change_image_transform));
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
