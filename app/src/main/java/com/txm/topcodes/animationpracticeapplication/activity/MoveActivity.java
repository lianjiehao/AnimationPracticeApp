package com.txm.topcodes.animationpracticeapplication.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.txm.topcodes.animationpracticeapplication.R;
import com.txm.topcodes.animationpracticeapplication.base.BaseActivity;
import com.txm.topcodes.animationpracticeapplication.util.LogUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

/**
 * Created by Tangxianming on 2019/1/2.
 * 移动View
 */
public class MoveActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {
    ImageView ivBalPathInterpolator;
    ImageView ivBalPathAnimator;
    Toolbar toolbar;
    String title;
    private static final String TITLE_EXTRA = "titleExtra";
    RelativeLayout rlPathInterpolator;
    RelativeLayout rlPathAnimator;

    public static void start(Context context, String title) {
        Intent starter = new Intent(context, MoveActivity.class);
        starter.putExtra(TITLE_EXTRA, title);
        context.startActivity(starter);
    }

    @Override
    public Object initContentView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_move;
    }

    @Override
    public void initListener() {
        logDebug("onCreate");
        title = getIntent().getStringExtra(TITLE_EXTRA);
        ivBalPathInterpolator = findViewById(R.id.ivBalPathInterpolator);
        ivBalPathAnimator = findViewById(R.id.ivBalPathAnimator);
        rlPathAnimator = findViewById(R.id.rlPathAnimator);
        rlPathInterpolator = findViewById(R.id.rlPathInterpolator);
        initToolbar();
        findViewById(R.id.btnStartPathInterpolator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPathInterpolatorAnimator();
            }
        });
        findViewById(R.id.btnStartPathAnimator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPathAnimator();
            }
        });
    }

    @Override
    public void initdata() {

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
        getMenuInflater().inflate(R.menu.menu_move, menu);
        return true;
    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_path_interpolator://path类型的插值器动画
                rlPathAnimator.setVisibility(View.GONE);
                rlPathInterpolator.setVisibility(View.VISIBLE);
                break;
            case R.id.action_path_animator://路径动画
                rlPathAnimator.setVisibility(View.VISIBLE);
                rlPathInterpolator.setVisibility(View.GONE);
                break;
            case R.id.action_fling:
                rlPathAnimator.setVisibility(View.GONE);
                rlPathInterpolator.setVisibility(View.GONE);
                break;
        }
        return false;
    }

    /**
     * path类型的插值器动画(自定义插值器的简便方法)
     */
    void startPathInterpolatorAnimator() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Path path = new Path();
            path.lineTo(0.25f, 0.25f);
            path.moveTo(0.25f, 0.5f);
            path.lineTo(1f, 1f);
            PathInterpolator pathInterpolator = new PathInterpolator(path);
            ObjectAnimator animator = ObjectAnimator.ofFloat(ivBalPathInterpolator, "translationY", 800f);
            animator.setDuration(2000);
            animator.setInterpolator(pathInterpolator);
            animator.start();
        }
    }

    /**
     * 路径动画（自定义估值器的简便方法）
     */
    public void startPathAnimator() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Path path = new Path();
            path.moveTo(ivBalPathAnimator.getX(), ivBalPathAnimator.getY());
            path.cubicTo(ivBalPathAnimator.getX(), ivBalPathAnimator.getY(),
                    ivBalPathAnimator.getX() + 300, ivBalPathAnimator.getY() + 200,
                    ivBalPathAnimator.getX() - 400, ivBalPathAnimator.getY() + 500);
            //下面两种方法不允许
//            path.addCircle(ivBall.getX(), ivBall.getY(),100,Path.Direction.CCW);
//            path.arcTo(ivBall.getX()+100, ivBall.getY()+100, ivBall.getX() + 500, ivBall.getY() + 500, 270f, -180f, true);
            ObjectAnimator objectAnimator =
                    ObjectAnimator.ofFloat(ivBalPathAnimator, View.X, View.Y, path);
            objectAnimator.setDuration(1200);
            objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
            objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
            objectAnimator.setInterpolator(new LinearInterpolator());
            objectAnimator.start();

        }
    }


}
