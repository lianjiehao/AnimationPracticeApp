package com.txm.topcodes.animationpracticeapplication.activity;

import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
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
    Toolbar toolbar;
    @Override
    public Object initContentView(@Nullable Bundle savedInstanceState) {
        // inside your activity (if you did not enable transitions in your theme)
        //getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        return R.layout.activity_activity_content_transition_animation;
    }

    @Override
    public void initListener() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setupTransition();
        }
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void initdata() {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupTransition() {
        getWindow().setEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.entertransition));
    }

}
