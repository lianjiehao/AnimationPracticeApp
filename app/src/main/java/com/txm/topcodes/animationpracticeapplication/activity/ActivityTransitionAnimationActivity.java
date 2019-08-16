package com.txm.topcodes.animationpracticeapplication.activity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
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

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

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


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupTransition() {
        Slide slide = new Slide(Gravity.LEFT);
        getWindow().setExitTransition(slide);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    private List<Pair<View, String>> getPairs() {
        View statusBar = findViewById(android.R.id.statusBarBackground);
        View navigationBar = findViewById(android.R.id.navigationBarBackground);
        View appBar = findViewById(R.id.appbar);
        List<Pair<View, String>> pairs = new ArrayList<>();
        pairs.add(Pair.create(statusBar, Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME));
        pairs.add(Pair.create(navigationBar, Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME));
        pairs.add(Pair.create(appBar, "appbar"));
        return pairs;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnContentTransition: {
                List<Pair<View, String>> pairs = getPairs();
                Intent intent = new Intent(ActivityTransitionAnimationActivity.this, ActivityContentTransitionAnimationActivity.class);
                startActivity(intent,
                        ActivityOptionsCompat.makeSceneTransitionAnimation(ActivityTransitionAnimationActivity.this, pairs.toArray(new Pair[pairs.size()])).toBundle());
                break;
            }
            case R.id.btnShareElementTransition: {
                List<Pair<View, String>> pairs = getPairs();
                Pair<View, String> pair = new Pair<View, String>(btnShareElementTransition, getString(R.string.planet_transition_item));
                pairs.add(pair);
                Intent intent = new Intent(ActivityTransitionAnimationActivity.this, ActivityShareElementTransitionAnimationActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(ActivityTransitionAnimationActivity.this, pairs.toArray(new Pair[pairs.size()]));
                startActivity(intent, options.toBundle());
                break;
            }
        }
    }
}
