package com.txm.topcodes.animationpracticeapplication.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.txm.topcodes.animationpracticeapplication.R;
import com.txm.topcodes.animationpracticeapplication.base.BaseActivity;
import com.txm.topcodes.animationpracticeapplication.view.MProgressView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Tangxianming on 2019/1/17.
 * 补间动画、逐帧动画、属性动画
 */
public class ViewPropertyAnimationActivity extends BaseActivity {
    @BindView(R.id.ivTween)
    ImageView ivTween;
    @BindView(R.id.ivFrame)
    ImageView ivFrame;
    @BindView(R.id.cslFrame)
    ConstraintLayout cslFrame;
    @BindView(R.id.progressView)
    MProgressView progressView;
    @BindView(R.id.cslProperty)
    ConstraintLayout cslProperty;
    @BindView(R.id.rcyContent)
    RecyclerView rcyContent;
    @BindView(R.id.cslLayoutAnimation)
    ConstraintLayout cslLayoutAnimation;
    @BindView(R.id.ivViewProperty)
    ImageView ivViewProperty;
    @BindView(R.id.cslViewProperty)
    ConstraintLayout cslViewProperty;

    AnimationDrawable animationDrawable;
    Animation translateAnimation;
    ObjectAnimator objectAnimator;
    List<String> strings = new ArrayList<>();
    StringAdapter stringAdapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, ViewPropertyAnimationActivity.class);
        context.startActivity(starter);
    }

    @Override
    public Object initContentView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_view_property_animation;
    }

    @Override
    public void initView() {
        rcyContent.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initdata() {
        //补间动画
        translateAnimation = AnimationUtils.loadAnimation(this, R.anim.view_animation);
        //逐帧动画
        animationDrawable = (AnimationDrawable) ivFrame.getDrawable();
        //属性动画
        objectAnimator = ObjectAnimator.ofFloat(progressView, "progress", 30f, 280f).setDuration(1000);
        objectAnimator.setInterpolator(new OvershootInterpolator());//设置插值器
    }

    @Override
    public void action() {
        //开始补间动画
        ivTween.startAnimation(translateAnimation);
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
        getMenuInflater().inflate(R.menu.menu_view_property, menu);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_tween://补间动画
                ivTween.setAlpha(1f);//运用了补间动画的view "setVisibility"方法无效
                cslFrame.setVisibility(View.GONE);
                cslProperty.setVisibility(View.GONE);
                cslLayoutAnimation.setVisibility(View.GONE);
                cslViewProperty.setVisibility(View.GONE);
                ivTween.startAnimation(translateAnimation);
                toolbar.setTitle("补间动画");
                break;
            case R.id.action_layout_animation:
                ivTween.setAlpha(0f);//运用了补间动画的view "setVisibility"方法无效
                cslLayoutAnimation.setVisibility(View.VISIBLE);
                cslFrame.setVisibility(View.GONE);
                cslProperty.setVisibility(View.GONE);
                cslViewProperty.setVisibility(View.GONE);
                initRecyleView();
                toolbar.setTitle("LayoutAnimation（布局动画）");
                break;
            case R.id.action_frame://逐帧动画
                ivTween.setAlpha(0f);//运用了补间动画的view "setVisibility"方法无效
                cslFrame.setVisibility(View.VISIBLE);
                cslProperty.setVisibility(View.GONE);
                cslLayoutAnimation.setVisibility(View.GONE);
                cslViewProperty.setVisibility(View.GONE);
                animationDrawable.stop();
                animationDrawable.start();
                toolbar.setTitle("逐帧动画");
                break;
            case R.id.action_property://属性动画
                ivTween.setAlpha(0f);//运用了补间动画的view "setVisibility"方法无效
                cslFrame.setVisibility(View.GONE);
                cslProperty.setVisibility(View.VISIBLE);
                cslLayoutAnimation.setVisibility(View.GONE);
                cslViewProperty.setVisibility(View.GONE);
                objectAnimator.start();
                toolbar.setTitle("属性动画");
                break;
            case R.id.action_view_property:
                ivTween.setAlpha(0f);//运用了补间动画的view "setVisibility"方法无效
                cslFrame.setVisibility(View.GONE);
                cslProperty.setVisibility(View.GONE);
                cslLayoutAnimation.setVisibility(View.GONE);
                cslViewProperty.setVisibility(View.VISIBLE);
                toolbar.setTitle("ViewPropertyAnimator");
                break;
        }
        return false;
    }


    @OnClick({R.id.btnStart, R.id.btnStop, R.id.btnRestart, R.id.btnListAdd, R.id.btnListRemove, R.id.btnContinueViewProperty})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStart:
                animationDrawable.stop();// 特别注意：当oneshot==ture时，在动画start()之前要先stop()，不然在第一次动画之后会停在最后一帧，这样动画就只会触发一次。
                animationDrawable.start();
                break;
            case R.id.btnStop:
                animationDrawable.stop();
                break;
            case R.id.btnRestart:
                objectAnimator.start();
                break;
            case R.id.btnListAdd:
                strings.add(String.format("ITEM_%d", strings.size()));
                stringAdapter.notifyItemInserted(strings.size() - 1);
                break;
            case R.id.btnListRemove:
                if (strings.size() > 0) {
                    strings.remove(strings.size() - 1);
                    stringAdapter.notifyItemRemoved(strings.size());
                }
                break;
            case R.id.btnContinueViewProperty://验证ViewPropertyAnimator动画的withStartAction
                viewPropertyAnimatior();
                break;
        }
    }


    /**
     * 初始化list列表
     */
    void initRecyleView() {
        strings.clear();
        for (int i = 0; i < 10; i++) {
            strings.add(String.format("ITEM_%d", i));
        }
        stringAdapter = new StringAdapter(strings);
        rcyContent.setAdapter(stringAdapter);
    }


    /**
     * 此方法验证如下结论：
     * withStartAction() / withEndAction() 是一次性的，在动画执行结束后就自动弃掉了，就算之后再重用  ViewPropertyAnimator 来做别的动画，用它们设置的回调也不会再被调用。
     * 而 add/setListener() 所设置的 AnimatorListener 是持续有效的，当动画重复执行时，回调总会被调用。
     */
    void viewPropertyAnimatior() {
        final ViewPropertyAnimator viewPropertyAnimator = ivViewProperty.animate();
        logDebug(viewPropertyAnimator.toString());
        viewPropertyAnimator.rotationBy(360).withStartAction(new Runnable() {
            @Override
            public void run() {
                logDebug("withStartAction");//注意它的执行次数
            }
        }).withEndAction(new Runnable() {
            @Override
            public void run() {
                logDebug("withEndAction");//注意它的执行次数
            }
        }).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                logDebug("onAnimationStart");//注意它的执行次数
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                logDebug("onAnimationEnd");//注意它的执行次数
            }
        });
        ivViewProperty.postDelayed(new Runnable() {
            @Override
            public void run() {
                viewPropertyAnimator.translationXBy(20);
            }
        }, 1000);
    }


    /**
     * String列表适配器
     */
    class StringAdapter extends RecyclerView.Adapter<StringAdapter.MyViewHolder> {
        List<String> strings;

        public StringAdapter(List<String> strings) {
            this.strings = strings;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
            MyViewHolder viewHolder = new MyViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder m, int i) {
            m.tvString.setText(strings.get(i));
        }

        @Override
        public int getItemCount() {
            return strings.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            protected TextView tvString;

            public MyViewHolder(View itemView) {
                super(itemView);
                tvString = (TextView) itemView.findViewById(R.id.tvString);
            }
        }

    }


}
