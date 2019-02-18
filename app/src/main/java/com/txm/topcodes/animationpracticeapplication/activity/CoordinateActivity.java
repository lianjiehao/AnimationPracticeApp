package com.txm.topcodes.animationpracticeapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import androidx.annotation.Nullable;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
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
    TextView tvCoordinate;
    TextView tvSizeAndLayer;
    TextView tvTouchCoodinate;
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
        logDebug("onCreate");
        title = getIntent().getStringExtra(TITLE_EXTRA);
        rlParent = findViewById(R.id.rlParent);
        tvChild = findViewById(R.id.tvChild);
        tvSizeAndLayer = findViewById(R.id.tvSizeAndLayer);
        tvCoordinate = findViewById(R.id.tvCoordinate);
        tvTouchCoodinate = findViewById(R.id.tvTouchCoodinate);
        tvChild.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                String content = String.format("Touch child:\nMotionEvent.getRawX=%.0f；\n" +
                                "MotionEvent.getRawY=%.0f；\nMotionEvent.getX=%.0f；\nMotionEvent.getY=%.0f；",
                        motionEvent.getRawX(), motionEvent.getRawY(),
                        motionEvent.getX(), motionEvent.getY());
                tvTouchCoodinate.setText(content);
                return false;
            }
        });
        initToolbar();

    }

    @Override
    public void initdata() {
        testViewTreeObserver();
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        tvCoordinate.setText(getCoordinate());
        getWindow().getDecorView().setClickable(true);
        logDebug("onWindowFocusChanged:");
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_coordinate:
                tvTouchCoodinate.setVisibility(View.VISIBLE);
                tvSizeAndLayer.setVisibility(View.GONE);
                tvCoordinate.setVisibility(View.VISIBLE);
                tvCoordinate.setText(getCoordinate());
                break;
            case R.id.action_size:
                tvTouchCoodinate.setVisibility(View.GONE);
                tvSizeAndLayer.setVisibility(View.VISIBLE);
                tvCoordinate.setVisibility(View.GONE);
                tvSizeAndLayer.setText(getMetrics() + getRealMetrics() + getHeight());
                break;
            case R.id.action_layer:
                tvTouchCoodinate.setVisibility(View.GONE);
                tvSizeAndLayer.setVisibility(View.VISIBLE);
                tvCoordinate.setVisibility(View.GONE);
                tvSizeAndLayer.setText(getview());
                break;
        }
        return false;
    }

    /**
     * 获取坐标
     *
     * @return
     */
    String getCoordinate() {
        int[] childOutLocationInWindow = new int[2];
        int[] childOutLocationOnScreen = new int[2];
        tvChild.getLocationInWindow(childOutLocationInWindow);
        tvChild.getLocationOnScreen(childOutLocationOnScreen);
        int[] parentOutLocationInWindow = new int[2];
        int[] parentOutLocationOnScreen = new int[2];
        rlParent.getLocationInWindow(parentOutLocationInWindow);
        rlParent.getLocationOnScreen(parentOutLocationOnScreen);
        String coordinateStr = String.format(
                "child.getWidth()=%d，child.getHeight()=%d；\n" +
                        "child.getLocationInWindow()=[%s]，child.getLocationOnScreen=[%s]；\n" +
                        "child.getX=%.0f，child.getY=%.0f；\nchild.getTranslationX=%.0f，child.getTranslationY=%.0f；\nchild.getLeft()=%d，child.getRight()=%d，child.getTop()=%d，child.getBottom()=%d；\n\n" +
                        "parent.getWidth()=%d，parent.getHeight()=%d；\n" +
                        "parent.getLocationInWindow()=[%s]，parent.getLocationOnScreen=[%s]；\n" +
                        "parent.getX=%.0f，parent.getY=%.0f；\nparent.getTranslationX=%.0f，parent.getTranslationY=%.0f；\nparent.getLeft()=%d，parent.getRight()=%d，parent.getTop()=%d，parent.getBottom()=%d；",
                tvChild.getWidth(), tvChild.getHeight(),
                String.format("%d,%d", childOutLocationInWindow[0], childOutLocationInWindow[1]), String.format("%d,%d", childOutLocationOnScreen[0], childOutLocationOnScreen[1]),
                tvChild.getX(), tvChild.getY(), tvChild.getTranslationX(), tvChild.getTranslationY(), tvChild.getLeft(), tvChild.getRight(), tvChild.getTop(), tvChild.getBottom(),
                rlParent.getWidth(), rlParent.getHeight(),
                String.format("%d,%d", parentOutLocationInWindow[0], parentOutLocationInWindow[1]), String.format("%d,%d", parentOutLocationOnScreen[0], parentOutLocationOnScreen[1]),
                rlParent.getX(), rlParent.getY(), rlParent.getTranslationX(), rlParent.getTranslationY(), rlParent.getLeft(), rlParent.getRight(), rlParent.getTop(), rlParent.getBottom());
        return coordinateStr;
    }

    /**
     * 测量屏幕宽高
     *
     * @return
     */
    String getMetrics() {
        Context context = getApplicationContext();
        DisplayMetrics localDisplayMetrics = context.getResources().getDisplayMetrics();
        int height = localDisplayMetrics.heightPixels;
        int width = localDisplayMetrics.widthPixels;
        return String.format("Metrics--widthPixels:%d,heightPixels:%d\n", width, height);
    }

    /**
     * 测量屏幕真实宽高
     *
     * @return
     */
    String getRealMetrics() {
        Context context = getApplicationContext();
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowMgr = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowMgr.getDefaultDisplay().getRealMetrics(dm);
        int height = dm.heightPixels;
        int width = dm.widthPixels;
        return String.format("RealMetrics--widthPixels:%d,heightPixels:%d\n", width, height);
    }

    /**
     * 获取高度
     *
     * @return
     */
    String getHeight() {
        Rect frame = new Rect();
        tvChild.getWindowVisibleDisplayFrame(frame);
        Rect localVisibleRect = new Rect();
        tvChild.getLocalVisibleRect(localVisibleRect);
        Rect globalVisibleRect = new Rect();
        tvChild.getGlobalVisibleRect(globalVisibleRect);
        return String.format("findViewById(android.R.id.content).getHeight(): %d\n" +
                        "child.getRootView().getHeight():%d\n" +
                        "getWindow().getDecorView().getHeight():%d\n" +
                        "tvChild.getWindowVisibleDisplayFrame:%s\n" +
                        "tvChild.getLocalVisibleRect:%s\n" +
                        "tvChild.getGlobalVisibleRect:%s\n" +
                        "toolbar.getHeight():%d\n" +
                        "statuebarHeight:%d\n" +
                        "navigateBarHeight:%d\n",
                findViewById(android.R.id.content).getHeight(),
                tvChild.getRootView().getHeight(),
                getWindow().getDecorView().getHeight(),
                frame.toString(),
                localVisibleRect.toString(),
                globalVisibleRect.toString(),
                toolbar.getHeight(),
                frame.top,
                getWindow().getDecorView().getHeight() - frame.bottom);
    }

    /**
     * 获取视图层级
     *
     * @return
     */
    String getview() {
        return String.format("findViewById(android.R.id.content):%s\n\n" +
                        "child.getRootView():%s\n\n" +
                        "child.getParent():%s\n\n" +
                        "getWindow().getDecorView():%s\n",
                findViewById(android.R.id.content),
                tvChild.getRootView(),
                tvChild.getParent(),
                getWindow().getDecorView());
    }


    /**
     * 测试ViewTreeObserver
     */
    void testViewTreeObserver() {
        ViewTreeObserver observer = getWindow().getDecorView().getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                logDebug("ViewTree-onGlobalLayout");
            }
        });
        observer.addOnDrawListener(new ViewTreeObserver.OnDrawListener() {
            @Override
            public void onDraw() {
                logDebug("ViewTree-onDraw");
            }
        });
        observer.addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View view, View view1) {
                logDebug("ViewTree-onGlobalFocusChanged");
            }
        });
        observer.addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                logDebug("ViewTree-onScrollChanged");
            }
        });
        observer.addOnTouchModeChangeListener(new ViewTreeObserver.OnTouchModeChangeListener() {
            @Override
            public void onTouchModeChanged(boolean b) {
                logDebug("ViewTree-onTouchModeChanged");
            }
        });
//        observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override
//            public boolean onPreDraw() {
//                logDebug("onPreDraw");
//                return true;//注意这里的返回值
//            }
//        });
        observer.addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() {
            @Override
            public void onWindowFocusChanged(boolean b) {
                logDebug("ViewTree-onWindowFocusChanged");
            }
        });
        observer.addOnWindowAttachListener(new ViewTreeObserver.OnWindowAttachListener() {
            @Override
            public void onWindowAttached() {
                logDebug("ViewTree-onWindowAttached");
            }

            @Override
            public void onWindowDetached() {
                logDebug("ViewTree-onWindowDetached");
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
}
