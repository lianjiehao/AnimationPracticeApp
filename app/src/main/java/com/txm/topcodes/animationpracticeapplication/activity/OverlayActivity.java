package com.txm.topcodes.animationpracticeapplication.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.txm.topcodes.animationpracticeapplication.R;
import com.txm.topcodes.animationpracticeapplication.base.BaseActivity;

import androidx.annotation.Nullable;

/**
 * Overlay使用
 */
public class OverlayActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, OverlayActivity.class);
        context.startActivity(starter);
    }

    @Override
    public Object initContentView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_overlay;
    }

    private void viewsSetup() {
        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Our button is added to the parent of its parent, the most top-level layout
                final ViewGroup container = (ViewGroup) button.getParent().getParent();
                container.getOverlay().add(button);

                ObjectAnimator anim = ObjectAnimator.ofFloat(button, "translationY", container.getHeight() / 2);
                ObjectAnimator rotate = ObjectAnimator.ofFloat(button, "rotation", 0, 360);
                rotate.setDuration(350);

                /*
                 * Button needs to be removed after animation ending
                 * When we have added the view to the ViewOverlay,
                 * it was removed from its original parent.
                 */
                anim.addListener(new Animator.AnimatorListener() {

                    @Override
                    public void onAnimationStart(Animator arg0) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator arg0) {
                    }

                    @Override
                    public void onAnimationEnd(Animator arg0) {
                        container.getOverlay().remove(button);
                    }

                    @Override
                    public void onAnimationCancel(Animator arg0) {
                        container.getOverlay().remove(button);
                    }
                });

                anim.setDuration(2000);

                AnimatorSet set = new AnimatorSet();
                set.playTogether(anim, rotate);
                set.start();
            }
        });

        final Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Normal animation, we only see it when is animating in its original layout container.
                final ViewGroup container = (ViewGroup) button2.getParent().getParent();
                ObjectAnimator anim = ObjectAnimator.ofFloat(button2, "translationY", -container.getHeight());
                anim.setDuration(2000);
                anim.start();
            }
        });

        final Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                ObjectAnimator fadeOut = ObjectAnimator.ofFloat(button3, "alpha", 1f, 0f);
                fadeOut.setDuration(500);

                /*
                 * Here we add our button to center layout's ViewGroupOverlay
                 * when first fade-out animation ends.
                 */
                final ViewGroup container = (ViewGroup) button2.getParent();
                final ObjectAnimator anim = ObjectAnimator.ofFloat(button3, "translationY", -container.getHeight() * 2);
                anim.setDuration(2000);

                anim.addListener(new Animator.AnimatorListener() {

                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        container.getOverlay().remove(button3);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        container.getOverlay().remove(button3);
                    }
                });

                fadeOut.addListener(new Animator.AnimatorListener() {

                    @Override
                    public void onAnimationStart(Animator arg0) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator arg0) {
                    }

                    @Override
                    public void onAnimationEnd(Animator arg0) {
                        container.getOverlay().add(button3);
                        button3.setAlpha(1f);
                        anim.start();
                    }

                    @Override
                    public void onAnimationCancel(Animator arg0) {
                        container.getOverlay().add(button3);
                        button3.setAlpha(1f);
                        anim.start();
                    }
                });

                fadeOut.start();
            }
        });
    }

    @Override
    public void initView() {
        viewsSetup();
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
