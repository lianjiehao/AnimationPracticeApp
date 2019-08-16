package com.txm.topcodes.animationpracticeapplication.activity;

import android.os.Build;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
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
 * TODO 在某些机型上会出现状态栏、导航栏、标题栏闪烁的现象。
 */
public class ActivityContentTransitionAnimationActivity extends BaseActivity {
    boolean transitionEnd = false;

    @Override
    public Object initContentView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_activity_content_transition_animation;
    }

    @Override
    public void initView() {
        // 防止状态栏、导航栏、Toolbar闪烁
        getWindow().setSharedElementsUseOverlay(false);
        postponeEnterTransition();
        final View decor = getWindow().getDecorView();
        decor.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                decor.getViewTreeObserver().removeOnPreDrawListener(this);
                startPostponedEnterTransition();
                return true;
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
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
        getWindow().setEnterTransition(transition);
        getWindow().setSharedElementEnterTransition(null);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transitionEnd = true;
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }


    @Override
    public void onBackPressed() {
        if (transitionEnd) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

}
