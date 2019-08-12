package com.txm.topcodes.animationpracticeapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.txm.topcodes.animationpracticeapplication.R;
import com.txm.topcodes.animationpracticeapplication.base.BaseActivity;

import androidx.annotation.Nullable;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;

/**
 * Created by Tangxianming on 2019/3/11.
 * Spring动画
 */
public class SpringActivity extends BaseActivity {
    View ball;
    SpringAnimation springAnimx;
    SpringAnimation springAnimy;
    float downX;
    float downY;
    float x;
    float y;

    public static void start(Context context) {
        Intent starter = new Intent(context, SpringActivity.class);
        context.startActivity(starter);
    }

    @Override
    public Object initContentView(@Nullable Bundle savedInstanceState) {
        return R.layout.acitivity_spring;
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        logDebug("onWindowFocusChanged");
        // Setting up a spring animation to animate the view’s translationY property with the final
        // spring position at 0.
        springAnimx = new SpringAnimation(ball, DynamicAnimation.X, ball.getX());
        springAnimy = new SpringAnimation(ball, DynamicAnimation.Y, ball.getY());
    }

    @Override
    protected void onResume() {
        super.onResume();
        logDebug("onResume");
    }

    @Override
    public void initView() {
        ball = findViewById(R.id.flBall);
        ball.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //记录下当前按下的X、Y坐标
                        downX = event.getRawX();
                        downY = event.getRawY();
                        x = ball.getX();
                        y = ball.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //在Move事件里面，拿到现在的Y坐标减去按下时候的坐标，就能计算出当前View应该移动的距离。
                        float moveX = event.getRawX();
                        float moveY = event.getRawY();
                        float diffX = moveX - downX;
                        float diffY = moveY - downY;
                        view.setX(x + diffX);
                        view.setY(y + diffY);
                        break;
                    case MotionEvent.ACTION_UP:
                        //开始弹簧动画
                        springAnimx.setStartVelocity(2000f);
                        springAnimx.start();
                        springAnimy.setStartVelocity(2000f);
                        springAnimy.start();
                        break;
                }
                return false;
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

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
