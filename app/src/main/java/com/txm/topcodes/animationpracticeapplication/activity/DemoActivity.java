package com.txm.topcodes.animationpracticeapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.txm.topcodes.animationpracticeapplication.R;
import com.txm.topcodes.animationpracticeapplication.base.BaseActivity;

import androidx.annotation.Nullable;

public class DemoActivity extends BaseActivity {
    ImageView ivDemo;

    public static void start(Context context) {
        Intent starter = new Intent(context, DemoActivity.class);
        context.startActivity(starter);
    }

    @Override
    public Object initContentView(@Nullable Bundle savedInstanceState) {
        return R.layout.demo_layout;
    }

    @Override
    public void initListener() {
        ivDemo = findViewById(R.id.ivDemo);

    }

    @Override
    public void initdata() {

    }

    public void buttonClick(View view) {
        ivDemo.setTranslationX(20f);
        ivDemo.setTranslationY(20f);
        ivDemo.setTranslationY(10f);
    }

}
