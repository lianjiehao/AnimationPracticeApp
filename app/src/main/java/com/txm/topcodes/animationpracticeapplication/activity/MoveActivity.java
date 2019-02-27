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
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.txm.topcodes.animationpracticeapplication.R;
import com.txm.topcodes.animationpracticeapplication.base.BaseActivity;
import com.txm.topcodes.animationpracticeapplication.util.LogUtil;
import com.txm.topcodes.animationpracticeapplication.util.SystemUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FlingAnimation;

/**
 * Created by Tangxianming on 2019/1/2.
 * 移动View
 */
public class MoveActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {
    ImageView ivBalPathInterpolator;
    ImageView ivBalPathAnimator;
    ImageView ivFling;
    Toolbar toolbar;
    String title;
    private static final String TITLE_EXTRA = "titleExtra";
    RelativeLayout rlFlingAnimator;
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
        ivFling = findViewById(R.id.ivFling);
        rlPathAnimator = findViewById(R.id.rlPathAnimator);
        rlPathInterpolator = findViewById(R.id.rlPathInterpolator);
        rlFlingAnimator = findViewById(R.id.rlFlingAnimator);
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
        findViewById(R.id.btnResetFling).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetFling();
            }
        });
    }

    @Override
    public void initdata() {
        // 1.创建一个监听回调
        GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                logDebug(String.format("velocityX=%f;velocityY=%f", velocityX, velocityY));
                //定义x方向的fling
                FlingAnimation flingAnimationX = new FlingAnimation(ivFling, DynamicAnimation.X);
                flingAnimationX.setStartVelocity(velocityX);
                flingAnimationX.setFriction(1f);
                flingAnimationX.start();
                //定义y方向的fling
                FlingAnimation flingAnimationY = new FlingAnimation(ivFling, DynamicAnimation.Y);
                flingAnimationY.setStartVelocity(velocityY);
                flingAnimationY.setFriction(1f);
                flingAnimationY.start();
                return super.onFling(e1, e2, velocityX, velocityY);
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                logDebug(String.format("distanceX=%f,distanceY=%f", distanceX, distanceY));
                return super.onScroll(e1, e2, distanceX, distanceY);
            }
        };
        // 2.创建一个检测器
        final GestureDetector detector = new GestureDetector(this, listener);
        rlFlingAnimator.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return detector.onTouchEvent(motionEvent);
            }
        });
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
                rlFlingAnimator.setVisibility(View.GONE);
                break;
            case R.id.action_path_animator://路径动画
                rlPathAnimator.setVisibility(View.VISIBLE);
                rlPathInterpolator.setVisibility(View.GONE);
                rlFlingAnimator.setVisibility(View.GONE);
                break;
            case R.id.action_fling:
                rlPathAnimator.setVisibility(View.GONE);
                rlPathInterpolator.setVisibility(View.GONE);
                rlFlingAnimator.setVisibility(View.VISIBLE);
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

    /**
     * 重置fling对象的位置
     */
    public void resetFling() {
        ivFling.setX(SystemUtil.getScreenWidth() / 2f - ivFling.getMeasuredWidth() / 2f);
        ivFling.setY(0);
    }

}
