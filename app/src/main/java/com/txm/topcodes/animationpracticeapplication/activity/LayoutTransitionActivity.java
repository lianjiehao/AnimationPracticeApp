package com.txm.topcodes.animationpracticeapplication.activity;

import android.animation.AnimatorInflater;
import android.animation.LayoutTransition;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.txm.topcodes.animationpracticeapplication.R;
import com.txm.topcodes.animationpracticeapplication.base.BaseActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * Created by Tangxianming on 2019/1/2.
 * transition动画
 */
public class LayoutTransitionActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {
    Toolbar toolbar;
    String title;
    private static final String TITLE_EXTRA = "titleExtra";
    ConstraintLayout cslAutoLayoutTransition;
    LinearLayout llAutoLayoutTransition;
    Button btnAutoLayoutTransitionAdd;
    ConstraintLayout cslLayoutTransition;
    LinearLayout llLayoutTransition;
    Button btnLayoutTransitionAdd;
    Button btnLayoutTransitionReduce;

    public static void start(Context context, String title) {
        Intent starter = new Intent(context, LayoutTransitionActivity.class);
        starter.putExtra(TITLE_EXTRA, title);
        context.startActivity(starter);
    }

    @Override
    public Object initContentView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_layout_transition;
    }

    @Override
    public void initListener() {
        title = getIntent().getStringExtra(TITLE_EXTRA);
        cslAutoLayoutTransition = findViewById(R.id.cslAutoLayoutTransition);
        llAutoLayoutTransition = findViewById(R.id.llAutoLayoutTransition);
        btnAutoLayoutTransitionAdd = findViewById(R.id.btnAutoLayoutTransitionAdd);
        cslLayoutTransition = findViewById(R.id.cslLayoutTransition);
        llLayoutTransition = findViewById(R.id.llLayoutTransition);
        btnLayoutTransitionAdd = findViewById(R.id.btnLayoutTransitionAdd);
        btnLayoutTransitionReduce = findViewById(R.id.btnLayoutTransitionReduce);
        btnAutoLayoutTransitionAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = new TextView(LayoutTransitionActivity.this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(params);
                textView.setText("ITEM");
                textView.setTextColor(getResources().getColor(R.color.colorAccent));
                textView.setGravity(Gravity.CENTER);
                textView.setPadding(0, 20, 0, 20);
                llAutoLayoutTransition.addView(textView);
            }
        });
        btnLayoutTransitionAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = new TextView(LayoutTransitionActivity.this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(params);
                textView.setText("ITEM");
                textView.setTextColor(getResources().getColor(R.color.colorAccent));
                textView.setGravity(Gravity.CENTER);
                textView.setPadding(0, 20, 0, 20);
                llLayoutTransition.addView(textView);
            }
        });
        btnLayoutTransitionReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (llLayoutTransition.getChildCount() > 0) {
                    llLayoutTransition.removeView(llLayoutTransition.getChildAt(0));
                }
            }
        });
        initToolbar();
    }

    @Override
    public void initdata() {
        initTransition();
    }


    /**
     * 创建菜单
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_transition, menu);
        return true;
    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        cslAutoLayoutTransition.setVisibility(View.GONE);
        cslLayoutTransition.setVisibility(View.GONE);
        switch (menuItem.getItemId()) {
            case R.id.action_auto_layouttransition://自动layoutTransition动画
                cslAutoLayoutTransition.setVisibility(View.VISIBLE);
                break;
            case R.id.action_custom_layouttransition://自定义layoutTransition动画
                cslLayoutTransition.setVisibility(View.VISIBLE);
                break;
        }
        return false;
    }


    public void initTransition() {
        LayoutTransition layoutTransition = new LayoutTransition();
        llLayoutTransition.setLayoutTransition(layoutTransition);
        //View出現的動畫
        layoutTransition.setAnimator(LayoutTransition.APPEARING, AnimatorInflater.loadAnimator(this, R.animator.anim_scale_x));
        //元素在容器中消失時需要動畫顯示
        layoutTransition.setAnimator(LayoutTransition.DISAPPEARING, AnimatorInflater.loadAnimator(this, R.animator.anim_color));
        layoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, AnimatorInflater.loadAnimator(this, R.animator.layout_change_appearing));
        layoutTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, AnimatorInflater.loadAnimator(this, R.animator.layout_change_disappearing));
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

}
