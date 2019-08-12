package com.txm.topcodes.animationpracticeapplication.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.txm.topcodes.animationpracticeapplication.R;
import com.txm.topcodes.animationpracticeapplication.base.BaseActivity;
import com.txm.topcodes.animationpracticeapplication.util.LogUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

/**
 * Created by Tangxianming on 2019/1/2.
 * 显示、隐藏动画
 */
public class RevealOrHideActivity extends BaseActivity {
    private View mContentView;
    private View mLoadingView;
    RelativeLayout rlCrossfade;
    FrameLayout flContainer;
    ImageView ivReveal;
    RelativeLayout rlReveal;
    Button btnStartReveal;

    private int mShortAnimationDuration;
    boolean showingBack = false;

    public static void start(Context context) {
        Intent starter = new Intent(context, RevealOrHideActivity.class);
        context.startActivity(starter);
    }

    @Override
    public Object initContentView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_reveal_hide;
    }

    @Override
    public void initView() {
        mContentView = findViewById(R.id.content);
        mLoadingView = findViewById(R.id.loading_spinner);
        rlCrossfade = findViewById(R.id.rlCrossfade);
        flContainer = findViewById(R.id.flContainer);
        ivReveal = findViewById(R.id.ivReveal);
        rlReveal = findViewById(R.id.rlReveal);
        btnStartReveal = findViewById(R.id.btnStartReveal);
        findViewById(R.id.btnShow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crossfade();
            }
        });
        findViewById(R.id.flContainer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard();
            }
        });
        btnStartReveal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.valueOf(String.valueOf(btnStartReveal.getTag())) == 1) {
                    revealInVisible();
                } else {
                    revealVisible();
                }
            }
        });
    }

    @Override
    public void initdata() {
        // 初始化crossfade相关
        mContentView.setVisibility(View.GONE);
        mShortAnimationDuration = getResources().getInteger(
                android.R.integer.config_shortAnimTime);
        //初始化card flip相关
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.flContainer, new CardFrontFragment())
                .commit();
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
        getMenuInflater().inflate(R.menu.menu_reveal_hide, menu);
        return true;
    }


    private void crossfade() {
        mContentView.setAlpha(0f);
        mContentView.setVisibility(View.VISIBLE);
        mContentView.animate()
                .alpha(1f)
                .setDuration(mShortAnimationDuration)
                .setListener(null);
        mLoadingView.animate()
                .alpha(0f)
                .setDuration(mShortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mLoadingView.setVisibility(View.GONE);
                    }
                });
    }

    private void flipCard() {
        if (showingBack) {
            //Flip to the font.
            showingBack = false;
            getSupportFragmentManager()
                    .beginTransaction()
                    //注意setCustomAnimations()方法必须在add、remove、replace调用之前被设置，否则不起作用。
                    .setCustomAnimations(
                            R.animator.card_flip_left_in,
                            R.animator.card_flip_left_out,
                            0,
                            0)
                    .replace(R.id.flContainer, new CardFrontFragment())
                    .commit();
            return;
        }
        // Flip to the back.
        showingBack = true;
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        0,
                        0)
                .replace(R.id.flContainer, new CardBackFragment())
                .commit();
    }


    private void revealVisible() {
        // Check if the runtime version is at least Lollipop
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // get the center for the clipping circle
            int cx = ivReveal.getWidth() / 2;
            int cy = ivReveal.getHeight() / 2;

            // get the final radius for the clipping circle
            float finalRadius = (float) Math.hypot(cx, cy);

            // create the animator for this view (the start radius is zero)
            Animator anim = ViewAnimationUtils.createCircularReveal(ivReveal, cx, cy, 0f, finalRadius);

            // make the view visible and start the animation
            ivReveal.setVisibility(View.VISIBLE);
            anim.start();
        } else {
            // set the view to visible without a circular reveal animation below Lollipop
            ivReveal.setVisibility(View.VISIBLE);
        }
        btnStartReveal.setText("reveal to InVisible");
        btnStartReveal.setTag("1");
    }

    private void revealInVisible() {
        // Check if the runtime version is at least Lollipop
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // get the center for the clipping circle
            int cx = ivReveal.getWidth() / 2;
            int cy = ivReveal.getHeight() / 2;
            // get the initial radius for the clipping circle
            float initialRadius = (float) Math.hypot(cx, cy);

            // create the animation (the final radius is zero)
            Animator anim = ViewAnimationUtils.createCircularReveal(ivReveal, cx, cy, initialRadius, 0f);

            // make the view invisible when the animation is done
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    ivReveal.setVisibility(View.INVISIBLE);
                }
            });
            // start the animation
            anim.start();
        } else {
            // set the view to visible without a circular reveal animation below Lollipop
            ivReveal.setVisibility(View.INVISIBLE);
        }
        btnStartReveal.setText("reveal to Visible");
        btnStartReveal.setTag("2");
    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_crossfade:
                rlCrossfade.setVisibility(View.VISIBLE);
                flContainer.setVisibility(View.GONE);
                rlReveal.setVisibility(View.GONE);
                toolbar.setTitle("淡入淡出动画");
                break;
            case R.id.action_card_flip:
                rlCrossfade.setVisibility(View.GONE);
                flContainer.setVisibility(View.VISIBLE);
                rlReveal.setVisibility(View.GONE);
                toolbar.setTitle("卡片翻转动画");
                break;
            case R.id.action_circular_reveal:
                rlCrossfade.setVisibility(View.GONE);
                flContainer.setVisibility(View.GONE);
                rlReveal.setVisibility(View.VISIBLE);
                toolbar.setTitle("揭露动画");
                break;
        }
        return false;
    }


    /**
     * A fragment representing the front of the card.
     */
    public static class CardFrontFragment extends Fragment {

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            LogUtil.d("fragmentCycle", "CardFront-onAttach");
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            LogUtil.d("fragmentCycle", "CardFront-onCreate");
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            LogUtil.d("fragmentCycle", "CardFront-onCreateView");
            return inflater.inflate(R.layout.fragment_card_front, container, false);
        }


        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            LogUtil.d("fragmentCycle", "CardFront-onActivityCreated");
        }

        @Override
        public void onStart() {
            super.onStart();
            LogUtil.d("fragmentCycle", "CardFront-onStart");
        }

        @Override
        public void onResume() {
            super.onResume();
            LogUtil.d("fragmentCycle", "CardFront-onResume");
        }

        @Override
        public void onPause() {
            super.onPause();
            LogUtil.d("fragmentCycle", "CardFront-onPause");
        }

        @Override
        public void onStop() {
            super.onStop();
            LogUtil.d("fragmentCycle", "CardFront-onStop");
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            LogUtil.d("fragmentCycle", "CardFront-onDestroyView");
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            LogUtil.d("fragmentCycle", "CardFront-onDestroy");
        }

        @Override
        public void onDetach() {
            super.onDetach();
            LogUtil.d("fragmentCycle", "CardFront-onDetach");
        }
    }

    /**
     * A fragment representing the back of the card.
     */
    public static class CardBackFragment extends Fragment {


        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            LogUtil.d("fragmentCycle", "CardBack-onAttach");
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            LogUtil.d("fragmentCycle", "CardBack-onCreate");
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            LogUtil.d("fragmentCycle", "CardBack-onCreateView");
            return inflater.inflate(R.layout.fragment_card_back, container, false);
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            LogUtil.d("fragmentCycle", "CardBack-onActivityCreated");
        }

        @Override
        public void onStart() {
            super.onStart();
            LogUtil.d("fragmentCycle", "CardBack-onStart");
        }

        @Override
        public void onResume() {
            super.onResume();
            LogUtil.d("fragmentCycle", "CardBack-onResume");
        }

        @Override
        public void onPause() {
            super.onPause();
            LogUtil.d("fragmentCycle", "CardBack-onPause");
        }

        @Override
        public void onStop() {
            super.onStop();
            LogUtil.d("fragmentCycle", "CardBack-onStop");
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            LogUtil.d("fragmentCycle", "CardBack-onDestroyView");
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            LogUtil.d("fragmentCycle", "CardBack-onDestroy");
        }

        @Override
        public void onDetach() {
            super.onDetach();
            LogUtil.d("fragmentCycle", "CardBack-onDetach");
        }


    }


}
