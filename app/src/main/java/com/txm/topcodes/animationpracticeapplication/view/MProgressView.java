package com.txm.topcodes.animationpracticeapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.txm.topcodes.animationpracticeapplication.R;
import com.txm.topcodes.animationpracticeapplication.util.SystemUtil;

/**
 * Created by Tangxianming on 2019/1/17.
 * 进度条动效
 */
public class MProgressView extends View {
    float progress = 0;//进度
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    float lineWidth = 0;//线条宽度

    public float getProgress() {
        return progress;
    }


    public MProgressView(Context context) {
        this(context, null);
    }

    public MProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        lineWidth = SystemUtil.dp2px(context, 10);
    }


    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF f = new RectF(lineWidth, lineWidth, getMeasuredWidth() - lineWidth, getMeasuredHeight() - lineWidth);
        paint.setColor(getResources().getColor(R.color.colorAccent));
        paint.setStrokeWidth(lineWidth);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(f, 0, progress, false, paint);
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#ffffff"));
        paint.setTextSize(SystemUtil.sp2Px(getContext(), 30));
        String text = String.format("%d", (int) (progress / 360 * 100)) + "%";
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        canvas.drawText(text, getMeasuredWidth() / 2 - bounds.width() / 2, getMeasuredHeight() / 2 + bounds.height() / 2, paint);
    }
}
