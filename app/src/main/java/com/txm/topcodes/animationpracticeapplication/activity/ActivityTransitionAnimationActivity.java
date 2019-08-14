package com.txm.topcodes.animationpracticeapplication.activity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Slide;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.txm.topcodes.animationpracticeapplication.R;
import com.txm.topcodes.animationpracticeapplication.base.BaseActivity;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

/**
 * @author :created by tangxianming
 * @date: 2019/7/23
 * @desc: 过渡动画之界面过渡：
 * 1、不带共享元素(Content Transition)
 * 2、带共享元素(ShareElement Transition)
 */
public class ActivityTransitionAnimationActivity extends BaseActivity implements View.OnClickListener {
    Button btnContentTransition;
    Button btnShareElementTransition;

    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityTransitionAnimationActivity.class);
        context.startActivity(starter);
    }

    @Override
    public Object initContentView(@Nullable Bundle savedInstanceState) {
        // inside your activity (if you did not enable transitions in your theme)
        //getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        return R.layout.activity_activity_transition_animation;
    }

    @Override
    public void initView() {
        btnContentTransition = findViewById(R.id.btnContentTransition);
        btnContentTransition.setOnClickListener(this);
        btnShareElementTransition = findViewById(R.id.btnShareElementTransition);
        btnShareElementTransition.setOnClickListener(this);
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

    //RequiresApi：告诉程序员这块代码只有在指定版本及以上才能使用，而并不是也不能用来解决兼容问题，
    // 指定代码块在低于指定版本上是不能运行通过的！
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupTransition() {
        Slide slide = new Slide(Gravity.LEFT);
        slide.setDuration(1000);
        slide.excludeTarget(android.R.id.statusBarBackground, true);
        slide.excludeTarget(android.R.id.navigationBarBackground, true);
        slide.excludeTarget(R.id.appbar, true);
        slide.setInterpolator(new FastOutSlowInInterpolator());
        getWindow().setExitTransition(slide);
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnContentTransition: {
                Intent intent = new Intent(ActivityTransitionAnimationActivity.this, ActivityContentTransitionAnimationActivity.class);
                startActivity(intent,
                        ActivityOptionsCompat.makeSceneTransitionAnimation(ActivityTransitionAnimationActivity.this).toBundle());
            }
            break;
            case R.id.btnShareElementTransition: {
                Intent intent = new Intent(ActivityTransitionAnimationActivity.this, ActivityShareElementTransitionAnimationActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(ActivityTransitionAnimationActivity.this, btnShareElementTransition, getString(R.string.planet_transition_item));
                startActivity(intent, options.toBundle());
                break;
            }
        }
    }
}
