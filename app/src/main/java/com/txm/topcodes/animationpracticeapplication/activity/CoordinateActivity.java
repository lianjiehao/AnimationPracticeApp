package com.txm.topcodes.animationpracticeapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.txm.topcodes.animationpracticeapplication.R;
import com.txm.topcodes.animationpracticeapplication.base.BaseActivity;

/**
 * Created by Tangxianming on 2019/1/2.
 * 坐标系、尺寸、视图层级
 */
public class CoordinateActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {
    RelativeLayout rlParent;
    TextView tvChild;
    TextView tvContent01;
    TextView tvContent02;
    Toolbar toolbar;
    String title;
    private static final String TITLE_EXTRA = "titleExtra";

    public static void start(Context context, String title) {
        Intent starter = new Intent(context, CoordinateActivity.class);
        starter.putExtra(TITLE_EXTRA, title);
        context.startActivity(starter);
    }

    @Override
    public Object initContentView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_coordinate;
    }

    @Override
    public void initListener() {
        title = getIntent().getStringExtra(TITLE_EXTRA);
        rlParent = findViewById(R.id.rlParent);
        tvChild = findViewById(R.id.tvChild);
        tvContent01 = findViewById(R.id.tvContent01);
        tvContent02 = findViewById(R.id.tvContent02);
        tvChild.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                String content = String.format("Touch child:\nMotionEvent.getRawX=%.0f；\nMotionEvent.getRawY=%.0f；\nMotionEvent.getX=%.0f；\nMotionEvent.getY=%.0f；", motionEvent.getRawX(), motionEvent.getRawY(), motionEvent.getX(), motionEvent.getY());
                tvContent02.setText(content);
                logDebug(motionEvent.toString());
                return false;
            }
        });
        initToolbar();
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


    /** 创建菜单 */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        String content = String.format("child.getWidth()=%d；child.getHeight()=%d；\nchild.getX=%.0f；child.getY=%.0f；child.getTranslationX=%.0f；child.getTranslationY=%.0f；child.getLeft()=%d；child.getRight()=%d；child.getTop()=%d；child.getBottom()=%d\n\nparent.getWidth()=%d；parent.getHeight()=%d；\nparent.getX=%.0f；parent.getY=%.0f；parent.getTranslationX=%.0f；parent.getTranslationY=%.0f；parent.getLeft()=%d；parent.getRight()=%d；parent.getTop()=%d；parent.getBottom()=%d", tvChild.getWidth(), tvChild.getHeight(), tvChild.getX(), tvChild.getY(), tvChild.getTranslationX(), tvChild.getTranslationY(), tvChild.getLeft(), tvChild.getRight(), tvChild.getTop(), tvChild.getBottom(), rlParent.getWidth(), rlParent.getHeight(), rlParent.getX(), rlParent.getY(), rlParent.getTranslationX(), rlParent.getTranslationY(), rlParent.getLeft(), rlParent.getRight(), rlParent.getTop(), rlParent.getBottom());
        tvContent01.setText(content);
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_coordinate:

                break;
            case R.id.action_size:

                break;
            case R.id.action_layer:

                break;
        }
        return false;
    }

//    void getMetrics() {
//        Context context = getApplicationContext();
//        DisplayMetrics localDisplayMetrics = context.getResources().getDisplayMetrics();
//        // 获取高度
//        int height = localDisplayMetrics.heightPixels;
//        // 获取宽度
//        int width = localDisplayMetrics.widthPixels;
//        Log.d("<MainActivity>", String.format("getMetrics--width:%d;height:%d", width, height));
//    }
//
//    void getRealMetrics() {
//        Context context = getApplicationContext();
//        DisplayMetrics dm = new DisplayMetrics();
//        WindowManager windowMgr = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        windowMgr.getDefaultDisplay().getRealMetrics(dm);
//        // 获取高度
//        int height = dm.heightPixels;
//        // 获取宽度
//        int width = dm.widthPixels;
//        Log.d("<MainActivity>", String.format("getRealMetrics--width:%d;height:%d", width, height));
//    }
//
//    void getHeight() {
//        View root = findViewById(android.R.id.content);
//        Log.d("<MainActivity>", "root.getHeight(): " + root.getHeight());//当前所引用视图的高度
//        Log.d("<MainActivity>", "root.getRootView().getHeight(): " + root.getRootView().getHeight());//屏幕高度
//        Log.d("<MainActivity>", "getWindow().getDecorView().getHeight(): " + getWindow().getDecorView().getHeight());//屏幕高度
//
//        Log.d("<MainActivity>", " root: " + root);
//        Log.d("<MainActivity>", " root.getRootView(): " + root.getRootView());
//        Log.d("<MainActivity>", "getWindow().getDecorView(): " + getWindow().getDecorView());
//        Log.d("<MainActivity>", "ivLogo.getRootView(): " + ivLogo.getRootView());
//        Rect frame = new Rect();
//        ivLogo.getWindowVisibleDisplayFrame(frame);
//        Log.d("<MainActivity>", "statusBarHeight: " + frame.top);
//        Log.d("<MainActivity>", "visible.bottom: " + frame.bottom);
//        Log.d("<MainActivity>", "root.top: " + root.getTop());
//    }

}
