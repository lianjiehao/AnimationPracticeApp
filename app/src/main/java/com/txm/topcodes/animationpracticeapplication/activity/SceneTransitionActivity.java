package com.txm.topcodes.animationpracticeapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.txm.topcodes.animationpracticeapplication.R;
import com.txm.topcodes.animationpracticeapplication.base.BaseActivity;
import androidx.annotation.Nullable;

/**
 * @author :created by tangxianming
 * @date: 2019/7/22
 * @desc: Android过渡动画之场景切换
 */
public class SceneTransitionActivity extends BaseActivity {
    ViewGroup sceneRootView;
    ViewGroup sceneDelayedView;
    private ImageView mImageOne;
    private ImageView mImageTwo;
    private ImageView mImageThree;
    private ImageView mImageFour;
    private Scene mSceneStart;
    private Scene mSceneEnd;
    private boolean mStartSceneState;

    public static void start(Context context) {
        Intent starter = new Intent(context, SceneTransitionActivity.class);
        context.startActivity(starter);
    }

    @Override
    public Object initContentView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_scene_transition;
    }

    @Override
    public void initListener() {
        sceneRootView = findViewById(R.id.layout_scene_root);
        sceneDelayedView = findViewById(R.id.layout_scene_delayed_root);
        findViewById(R.id.button_scene_transition_toggle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleScene01();
            }
        });
    }

    @Override
    public void initdata() {
        initData01();
    }

    ///////////////////////////////////方法一：
    void initData01() {
        sceneRootView.setVisibility(View.VISIBLE);
        sceneDelayedView.setVisibility(View.INVISIBLE);
        mSceneStart = Scene.getSceneForLayout(sceneRootView, R.layout.scene_combination_start, this);
        mSceneEnd = Scene.getSceneForLayout(sceneRootView, R.layout.scene_combination_end, this);
        /**
         * 切换到开始场景状态
         */
        TransitionManager.go(mSceneStart);
        mStartSceneState = true;
    }


    /**
     * 两个场景之间相互切换
     */
    private void toggleScene01() {
        TransitionManager.go(mStartSceneState ? mSceneEnd : mSceneStart,
                TransitionInflater.from(this).inflateTransition(R.transition.change_bounds_and_change_transform));
        mStartSceneState = !mStartSceneState;
    }

    ///////////////////////////////////方法二：
    void initData02() {
        sceneRootView.setVisibility(View.INVISIBLE);
        sceneDelayedView.setVisibility(View.VISIBLE);
        mImageOne = findViewById(R.id.image_scene_delayed_one);
        mImageTwo = findViewById(R.id.image_scene_delayed_two);
        mImageThree = findViewById(R.id.image_scene_delayed_three);
        mImageFour = findViewById(R.id.image_scene_delayed_four);
        mStartSceneState = true;
    }


    /**
     * 两个场景之间相互切换
     */
    private void toggleScene02() {
        Transition transition=TransitionInflater.from(this)
                .inflateTransition(
                        R.transition.change_bounds_and_change_transform);
        TransitionManager.beginDelayedTransition(sceneDelayedView, transition);
        /**
         * 四个角的View相互交换位置
         */
        if (mStartSceneState) {
            RelativeLayout.LayoutParams oneParams = (RelativeLayout.LayoutParams) mImageOne.getLayoutParams();
            oneParams.removeRule(RelativeLayout.ALIGN_PARENT_START);
            oneParams.removeRule(RelativeLayout.ALIGN_PARENT_TOP);
            oneParams.removeRule(RelativeLayout.ALIGN_PARENT_END);
            oneParams.removeRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            oneParams.addRule(RelativeLayout.ALIGN_PARENT_END);
            oneParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            mImageOne.setLayoutParams(oneParams);

            RelativeLayout.LayoutParams twoParams = (RelativeLayout.LayoutParams) mImageTwo.getLayoutParams();
            twoParams.removeRule(RelativeLayout.ALIGN_PARENT_START);
            twoParams.removeRule(RelativeLayout.ALIGN_PARENT_TOP);
            twoParams.removeRule(RelativeLayout.ALIGN_PARENT_END);
            twoParams.removeRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            twoParams.addRule(RelativeLayout.ALIGN_PARENT_START);
            twoParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            mImageTwo.setLayoutParams(twoParams);

            RelativeLayout.LayoutParams threeParams = (RelativeLayout.LayoutParams) mImageThree.getLayoutParams();
            threeParams.removeRule(RelativeLayout.ALIGN_PARENT_START);
            threeParams.removeRule(RelativeLayout.ALIGN_PARENT_TOP);
            threeParams.removeRule(RelativeLayout.ALIGN_PARENT_END);
            threeParams.removeRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            threeParams.addRule(RelativeLayout.ALIGN_PARENT_START);
            threeParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            mImageThree.setLayoutParams(threeParams);

            RelativeLayout.LayoutParams fourParams = (RelativeLayout.LayoutParams) mImageFour.getLayoutParams();
            fourParams.removeRule(RelativeLayout.ALIGN_PARENT_START);
            fourParams.removeRule(RelativeLayout.ALIGN_PARENT_TOP);
            fourParams.removeRule(RelativeLayout.ALIGN_PARENT_END);
            fourParams.removeRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            fourParams.addRule(RelativeLayout.ALIGN_PARENT_END);
            fourParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            mImageFour.setLayoutParams(fourParams);

            mImageTwo.setScaleX(0.5f);
            mImageTwo.setScaleY(0.5f);
            mImageFour.setRotation(90);
        } else {
            RelativeLayout.LayoutParams oneParams = (RelativeLayout.LayoutParams) mImageOne.getLayoutParams();
            oneParams.removeRule(RelativeLayout.ALIGN_PARENT_START);
            oneParams.removeRule(RelativeLayout.ALIGN_PARENT_TOP);
            oneParams.removeRule(RelativeLayout.ALIGN_PARENT_END);
            oneParams.removeRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            oneParams.addRule(RelativeLayout.ALIGN_PARENT_START);
            oneParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            mImageOne.setLayoutParams(oneParams);

            RelativeLayout.LayoutParams twoParams = (RelativeLayout.LayoutParams) mImageTwo.getLayoutParams();
            twoParams.removeRule(RelativeLayout.ALIGN_PARENT_START);
            twoParams.removeRule(RelativeLayout.ALIGN_PARENT_TOP);
            twoParams.removeRule(RelativeLayout.ALIGN_PARENT_END);
            twoParams.removeRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            twoParams.addRule(RelativeLayout.ALIGN_PARENT_END);
            twoParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            mImageTwo.setLayoutParams(twoParams);

            RelativeLayout.LayoutParams threeParams = (RelativeLayout.LayoutParams) mImageThree.getLayoutParams();
            threeParams.removeRule(RelativeLayout.ALIGN_PARENT_START);
            threeParams.removeRule(RelativeLayout.ALIGN_PARENT_TOP);
            threeParams.removeRule(RelativeLayout.ALIGN_PARENT_END);
            threeParams.removeRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            threeParams.addRule(RelativeLayout.ALIGN_PARENT_END);
            threeParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            mImageThree.setLayoutParams(threeParams);

            RelativeLayout.LayoutParams fourParams = (RelativeLayout.LayoutParams) mImageFour.getLayoutParams();
            fourParams.removeRule(RelativeLayout.ALIGN_PARENT_START);
            fourParams.removeRule(RelativeLayout.ALIGN_PARENT_TOP);
            fourParams.removeRule(RelativeLayout.ALIGN_PARENT_END);
            fourParams.removeRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            fourParams.addRule(RelativeLayout.ALIGN_PARENT_START);
            fourParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            mImageFour.setLayoutParams(fourParams);

            mImageTwo.setScaleX(1f);
            mImageTwo.setScaleY(1f);
            mImageFour.setRotation(0);
        }
        mStartSceneState = !mStartSceneState;
    }

}
