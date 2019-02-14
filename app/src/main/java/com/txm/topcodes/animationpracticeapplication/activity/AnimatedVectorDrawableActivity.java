package com.txm.topcodes.animationpracticeapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.txm.topcodes.animationpracticeapplication.R;
import com.txm.topcodes.animationpracticeapplication.base.BaseActivity;

/**
 * Created by Tangxianming on 2019/2/14.
 * 矢量图动画
 */
public class AnimatedVectorDrawableActivity extends BaseActivity {
    ImageView ivAnimation;
    Toolbar toolbar;
    String title;
    private static final String TITLE_EXTRA = "titleExtra";

    public static void start(Context context, String title) {
        Intent starter = new Intent(context, AnimatedVectorDrawableActivity.class);
        starter.putExtra(TITLE_EXTRA, title);
        context.startActivity(starter);
    }


    @Override
    public Object initContentView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_vector_drawable_animate;
    }

    @Override
    public void initListener() {
        title = getIntent().getStringExtra(TITLE_EXTRA);
        ivAnimation = findViewById(R.id.ivAnimation);
        findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable drawable = ivAnimation.getDrawable();
                if (drawable instanceof Animatable) {
                    ((Animatable) drawable).start();
                }
            }
        });
    }

    @Override
    public void initdata() {
        initToolbar();
    }


    void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
