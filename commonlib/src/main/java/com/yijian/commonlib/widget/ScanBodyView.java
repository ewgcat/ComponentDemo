package com.yijian.commonlib.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.yijian.commonlib.R;

/**
 * Created by The_P on 2018/3/2.
 */

public class ScanBodyView extends View {
    private static final String TAG = "ScanBodyView";
    private Bitmap bitmap_upper;
    private Bitmap bitmap_bottom;
    private Paint paint;
    private Bitmap bitmap_line;
    private ObjectAnimator animator;
    private int position_y;

    public ScanBodyView(Context context) {
        this(context, null);
    }

    public ScanBodyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScanBodyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        bitmap_upper = BitmapFactory.decodeResource(getResources(), R.mipmap.lg_blueren);
        bitmap_bottom = BitmapFactory.decodeResource(getResources(), R.mipmap.lg_blackman);
        bitmap_line = BitmapFactory.decodeResource(getResources(), R.mipmap.lg_blueline);
        animator = ObjectAnimator.ofInt(this, "PositionY", 0, 100);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
    }

    public int getPositionY() {
        return position_y;
    }

    public void setPositionY(int y) {
        position_y = y;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int measuredHeight = getMeasuredHeight();//控件的高度
        int measuredWidth = getMeasuredWidth();//控件的宽度
        double ratio_bottom = (bitmap_bottom.getHeight() * 1.0) / measuredHeight;//bitmap_line与控件的比例——高
        double ratio_upper = (bitmap_upper.getHeight() * 1.0) / measuredHeight;//bitmap_line与控件的比例——高
        double ratio_line = (bitmap_line.getWidth() * 1.0) / measuredWidth;//bitmap_line与控件的比例——宽

        double bitmap_line_height = bitmap_line.getHeight() / ratio_line;//bitmap_line控件在view上的高度

        int left = (int) ((measuredWidth - bitmap_bottom.getWidth() / ratio_bottom) / 2);//bitmap_bottom的画图的左坐标_x
        int top = (int) (bitmap_line_height);//bitmap_top的画图的左坐标_y
        int scanning_height = (int) (measuredHeight - 2 * top);//扫描的区间高度
        Rect bitmapRect1 = new Rect(0, 0, bitmap_bottom.getWidth(), bitmap_bottom.getHeight());//bitmap_bottom原始区域(需要绘制的bitmap的区域)
        Rect bitmapRect2 = new Rect(0, 0, bitmap_upper.getWidth(), (int) (bitmap_upper.getHeight() * (position_y / 100.0)));//bitmap_upper原始区域(需要绘制的bitmap的区域——只绘制bitmap的一部分)
        Rect bitmapRect3 = new Rect(0, 0, bitmap_line.getWidth(), bitmap_line.getHeight());//bitmap_line原始区域(需要绘制的bitmap的区域)

        Rect destRect1 = new Rect(left, top, (int) (bitmap_bottom.getWidth() / ratio_bottom) + left, top + scanning_height);  //bitmap放置的目标区域
        Rect destRect2 = new Rect(left, top, (int) (bitmap_upper.getWidth() / ratio_upper) + left, (int) (top + scanning_height * (position_y / 100.0)));//bitmap放置的目标区域
        Rect destRect3 = new Rect(0, (int) (0 + scanning_height * (position_y / 100.0)), measuredWidth, (int) (bitmap_line.getHeight() / ratio_line) + (int) (scanning_height * (position_y / 100.0)));//bitmap放置的目标区域

        canvas.drawBitmap(bitmap_bottom, bitmapRect1, destRect1, paint);
        canvas.drawBitmap(bitmap_upper, bitmapRect2, destRect2, paint);
        canvas.drawBitmap(bitmap_line, bitmapRect3, destRect3, paint);
    }

    public void startScan() {
        animator.start();
    }

    public void stopScan() {
        animator.end();
        this.setPositionY(0);
    }

    public boolean isAnimationRun() {
        return animator.isRunning();
    }
}

