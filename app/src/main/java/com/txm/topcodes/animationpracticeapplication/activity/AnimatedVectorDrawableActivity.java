package com.txm.topcodes.animationpracticeapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.txm.topcodes.animationpracticeapplication.R;
import com.txm.topcodes.animationpracticeapplication.base.BaseActivity;

/**
 * Created by Tangxianming on 2019/2/14.
 * 矢量图动画
 */
public class AnimatedVectorDrawableActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {
    Toolbar toolbar;
    ImageView ivAnimation01;
    ImageView ivAnimation02;
    ConstraintLayout cslAnimation01;
    ConstraintLayout cslAnimation02;
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
        cslAnimation01 = findViewById(R.id.cslAnimation01);
        cslAnimation02 = findViewById(R.id.cslAnimation02);
        title = getIntent().getStringExtra(TITLE_EXTRA);
        ivAnimation01 = findViewById(R.id.ivAnimation01);
        findViewById(R.id.btnStart01).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable drawable = ivAnimation01.getDrawable();//三个文件定义的矢量图动画
                if (drawable instanceof Animatable) {
                    ((Animatable) drawable).start();
                }
            }
        });
        ivAnimation02 = findViewById(R.id.ivAnimation02);
        findViewById(R.id.btnStart02).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable drawable = ivAnimation02.getDrawable();//单个文件定义的矢量图动画
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
        getMenuInflater().inflate(R.menu.vector_drawable, menu);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_way_01:
                cslAnimation01.setVisibility(View.VISIBLE);
                cslAnimation02.setVisibility(View.GONE);
                break;
            case R.id.action_way_02:
                cslAnimation01.setVisibility(View.GONE);
                cslAnimation02.setVisibility(View.VISIBLE);
                break;
        }
        return false;
    }
}
