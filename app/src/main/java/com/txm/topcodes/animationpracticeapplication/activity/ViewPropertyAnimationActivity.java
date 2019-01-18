package com.txm.topcodes.animationpracticeapplication.activity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import com.txm.topcodes.animationpracticeapplication.R;
import com.txm.topcodes.animationpracticeapplication.base.BaseActivity;
import com.txm.topcodes.animationpracticeapplication.view.MProgressView;

/**
 * Created by Tangxianming on 2019/1/17.
 * 视图、属性动画
 */
public class ViewPropertyAnimationActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener, View.OnClickListener {


    String title;
    private static final String TITLE_EXTRA = "titleExtra";
    ImageView ivTween;
    ImageView ivFrame;
    ConstraintLayout cslFrame;
    ConstraintLayout cslProperty;
    MProgressView progressView;
    AnimationDrawable animationDrawable;
    ObjectAnimator objectAnimator;

    public static void start(Context context, String title) {
        Intent starter = new Intent(context, ViewPropertyAnimationActivity.class);
        starter.putExtra(TITLE_EXTRA, title);
        context.startActivity(starter);
    }

    @Override
    public Object initContentView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_view_property_animation;
    }

    @Override
    public void initListener() {
        title = getIntent().getStringExtra(TITLE_EXTRA);
        ivTween = findViewById(R.id.ivTween);
        ivFrame = findViewById(R.id.ivFrame);
        cslFrame = findViewById(R.id.cslFrame);
        progressView = findViewById(R.id.progressView);
        cslProperty = findViewById(R.id.cslProperty);
        findViewById(R.id.btnStart).setOnClickListener(this);
        findViewById(R.id.btnStop).setOnClickListener(this);
        findViewById(R.id.btnRestart).setOnClickListener(this);
        initToolbar();
    }

    @Override
    public void initdata() {
        animationDrawable = (AnimationDrawable) ivFrame.getDrawable();//逐帧动画
        objectAnimator = ObjectAnimator.ofFloat(progressView, "progress", 280f).setDuration(1000);//属性动画
        objectAnimator.setInterpolator(new OvershootInterpolator());//设置插值器
        startTweenAnimation();
    }

    /**
     * 初始化toolbar参数
     */
    void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        toolbar.setOnMenuItemClickListener(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    /**
     * 创建菜单
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_property, menu);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_tween://补间动画
                ivTween.setAlpha(1f);//运用了补间动画的view "setVisibility"方法无效
                cslFrame.setVisibility(View.GONE);
                cslProperty.setVisibility(View.GONE);
                startTweenAnimation();
                break;
            case R.id.action_frame://逐帧动画
                ivTween.setAlpha(0f);//运用了补间动画的view "setVisibility"方法无效
                cslFrame.setVisibility(View.VISIBLE);
                cslProperty.setVisibility(View.GONE);
                animationDrawable.stop();
                animationDrawable.start();
                break;
            case R.id.action_property://属性动画
                ivTween.setAlpha(0f);//运用了补间动画的view "setVisibility"方法无效
                cslFrame.setVisibility(View.GONE);
                cslProperty.setVisibility(View.VISIBLE);
                objectAnimator.start();
                break;
        }
        return false;
    }

    /**
     * 开始补间动画
     */
    void startTweenAnimation() {
        Animation translateAnimation = AnimationUtils.loadAnimation(this, R.anim.view_animation);
        ivTween.startAnimation(translateAnimation);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStart:
                animationDrawable.stop();// 特别注意：当oneshot==ture时，在动画start()之前要先stop()，不然在第一次动画之后会停在最后一帧，这样动画就只会触发一次。
                animationDrawable.start();
                break;
            case R.id.btnStop:
                animationDrawable.stop();
                break;
            case R.id.btnRestart:
                objectAnimator.start();
                break;
        }
    }
}
