package com.txm.topcodes.animationpracticeapplication.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.txm.topcodes.animationpracticeapplication.R;
import com.txm.topcodes.animationpracticeapplication.base.BaseActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

/**
 * Created by Tangxianming on 2019/1/2.
 * Zoom动画
 */
public class ZoomActivity extends BaseActivity {
    private Animator currentAnimator;
    private int shortAnimationDuration;

    public static void start(Context context) {
        Intent starter = new Intent(context, ZoomActivity.class);
        context.startActivity(starter);
    }

    @Override
    public Object initContentView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_zoom;
    }

    @Override
    public void initView() {
        logDebug("onCreate");
        final View thumb1View = findViewById(R.id.thumb_button_1);
        thumb1View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(thumb1View, R.drawable.dd);
            }
        });

    }

    @Override
    public void initdata() {
        // Retrieve and cache the system's default "short" animation time.
        shortAnimationDuration = getResources().getInteger(
                android.R.integer.config_shortAnimTime);
    }

    @Override
    public void action() {

    }

    @Override
    public boolean hasToolbar() {
        return true;
    }

    private void zoomImageFromThumb(final View thumbView, int imageResId) {
        // If there's an animation in progress, cancel it
        // immediately and proceed with this one.
        if (currentAnimator != null) {
            currentAnimator.cancel();
            logDebug("currentAnimator.cancel()1");
        }
        final ImageView expandedImageView = (ImageView) findViewById(
                R.id.expanded_image);
        expandedImageView.setImageResource(imageResId);
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();
        thumbView.getGlobalVisibleRect(startBounds);
        findViewById(R.id.container)
                .getGlobalVisibleRect(finalBounds, globalOffset);
        //用于将global坐标转换成local坐标。这里的local坐标，是相对于内容区的坐标，也就是除了状态栏和action bar和虚拟按键的区域。
        //这里将startBounds区域定义到了父View内，为 View.X，
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);
        logDebug(startBounds.toString());
        logDebug(finalBounds.toString());
        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }
        thumbView.setAlpha(0f);
        expandedImageView.setVisibility(View.VISIBLE);
        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);
        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView,
                        View.X, startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView,
                        View.Y, startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView,
                        View.SCALE_X, startScale, 1f))
                .with(ObjectAnimator.ofFloat(expandedImageView,
                        View.SCALE_Y, startScale, 1f));
        set.setDuration(shortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                currentAnimator = null;
                logDebug("onAnimationEnd1");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                currentAnimator = null;
                logDebug("onAnimationCancel1");
            }
        });
        set.start();
        currentAnimator = set;
        //缩小
        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentAnimator != null) {
                    logDebug("currentAnimator.cancel()2");
                    currentAnimator.cancel();
                }
                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator
                        .ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.Y, startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_Y, startScaleFinal));
                set.setDuration(shortAnimationDuration);
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        currentAnimator = null;
                        logDebug("onAnimationEnd2");
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        currentAnimator = null;
                        logDebug("onAnimationCancel2");
                    }
                });
                set.start();
                currentAnimator = set;
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        logDebug("onStart:");
    }

    @Override
    protected void onResume() {
        super.onResume();
        logDebug("onResume:");
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
