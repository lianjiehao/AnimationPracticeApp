package com.txm.topcodes.animationpracticeapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.txm.topcodes.animationpracticeapplication.R;
import com.txm.topcodes.animationpracticeapplication.base.BaseActivity;

/**
 * Created by Tangxianming on 2019/1/17.
 * 视图、属性动画
 */
public class ViewPropertyAnimationActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {


    String title;
    private static final String TITLE_EXTRA = "titleExtra";
    ImageView ivTween;


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
        initToolbar();
    }

    @Override
    public void initdata() {
        tweenAnimation();
    }

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
                tweenAnimation();
                break;
            case R.id.action_frame://逐帧动画
                frameAnimation();
                break;
            case R.id.action_property://属性动画
                propertyAnimation();
                break;
        }
        return false;
    }

    /**
     * 补间动画
     */
    void tweenAnimation() {
        Animation translateAnimation = AnimationUtils.loadAnimation(this, R.anim.view_animation);
        ivTween.startAnimation(translateAnimation);
    }

    /**
     * 逐帧动画
     */
    void frameAnimation() {

    }

    /**
     * 属性动画
     */
    void propertyAnimation() {

    }


}
