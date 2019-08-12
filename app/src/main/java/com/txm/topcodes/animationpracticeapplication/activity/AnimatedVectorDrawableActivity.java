package com.txm.topcodes.animationpracticeapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.txm.topcodes.animationpracticeapplication.R;
import com.txm.topcodes.animationpracticeapplication.base.BaseActivity;

/**
 * Created by Tangxianming on 2SingleFile9/2/14.
 * 矢量图动画
 */
public class AnimatedVectorDrawableActivity extends BaseActivity {
    ImageView ivAnimationSingleFile;
    ImageView ivAnimationThreeFile;
    ImageView ivAnimationTrimClip;
    ConstraintLayout cslAnimationSingleFile;
    ConstraintLayout cslAnimationThreeFile;
    ConstraintLayout cslAnimationTrimClip;
    ConstraintLayout cslAnimatorSelector;

    public static void start(Context context) {
        Intent starter = new Intent(context, AnimatedVectorDrawableActivity.class);
        context.startActivity(starter);
    }


    @Override
    public Object initContentView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_vector_drawable_animate;
    }

    @Override
    public void initView() {
        cslAnimationSingleFile = findViewById(R.id.cslAnimationSingleFile);
        ivAnimationSingleFile = findViewById(R.id.ivAnimationSingleFile);
        cslAnimationThreeFile = findViewById(R.id.cslAnimationThreeFile);
        cslAnimationTrimClip = findViewById(R.id.cslAnimationTrimClip);
        cslAnimatorSelector = findViewById(R.id.cslAnimatorSelector);
        findViewById(R.id.btnStartSingleFile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable drawable = ivAnimationSingleFile.getDrawable();//单个文件定义的矢量图动画
                if (drawable instanceof Animatable) {
                    ((Animatable) drawable).start();
                }
            }
        });
        ivAnimationThreeFile = findViewById(R.id.ivAnimationThreeFile);
        findViewById(R.id.btnStartThreeFile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable drawable = ivAnimationThreeFile.getDrawable();//三个文件定义的矢量图动画
                if (drawable instanceof Animatable) {
                    ((Animatable) drawable).start();
                }
            }
        });
        ivAnimationTrimClip = findViewById(R.id.ivAnimationTrimClip);
        ivAnimationTrimClip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable drawable = ivAnimationTrimClip.getDrawable();//修建裁剪
                if (drawable instanceof Animatable) {
                    ((Animatable) drawable).start();
                }
            }
        });
    }

    @Override
    public void initdata() {

    }

    @Override
    public void action() {

    }

    @Override
    public boolean hasToolbar() {
        return true;
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
            case R.id.action_way_single_file:
                cslAnimationSingleFile.setVisibility(View.VISIBLE);
                cslAnimationThreeFile.setVisibility(View.GONE);
                cslAnimationTrimClip.setVisibility(View.GONE);
                cslAnimatorSelector.setVisibility(View.GONE);
                toolbar.setTitle("矢量图动画(单个文件方式)");
                break;
            case R.id.action_way_three_file:
                cslAnimationSingleFile.setVisibility(View.GONE);
                cslAnimationThreeFile.setVisibility(View.VISIBLE);
                cslAnimationTrimClip.setVisibility(View.GONE);
                cslAnimatorSelector.setVisibility(View.GONE);
                toolbar.setTitle("矢量图动画(三个文件方式)");
                break;
            case R.id.action_way_trim_clip:
                cslAnimationSingleFile.setVisibility(View.GONE);
                cslAnimationThreeFile.setVisibility(View.GONE);
                cslAnimationTrimClip.setVisibility(View.VISIBLE);
                cslAnimatorSelector.setVisibility(View.GONE);
                toolbar.setTitle("矢量图动画(修剪和裁剪)");
                break;
            case R.id.action_way_animated_selector:
                cslAnimationSingleFile.setVisibility(View.GONE);
                cslAnimationThreeFile.setVisibility(View.GONE);
                cslAnimationTrimClip.setVisibility(View.GONE);
                cslAnimatorSelector.setVisibility(View.VISIBLE);
                toolbar.setTitle("矢量图动画(动画切换)");
                break;
        }
        return false;
    }
}
