package com.yijian.workspace.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.yijian.workspace.R;

public class ControlView extends View {

    private int mWidth, mHeight;
    private Bitmap borderBitmap; //外边框
    private Bitmap bottomBitmap;
    private Bitmap wheelBitmap; //滚动
    private Paint mPaint;
    private boolean isDrag = false;
    private float tempCenterX, tempCenterY, centerTopX, centerTopY, centerX, centerY, firstCenterX, firstCenterY; // 滚轮的左  上边
    private float lastDragX, lastDragY;
    private int wheelRadius = 0; //滚轮半径
    private ControlViewListener listener;

    public void setListener(ControlViewListener listener) {
        this.listener = listener;
    }

    public ControlView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.border);
        bottomBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bottom);
        wheelBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.top);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bottomBitmap, mWidth - bottomBitmap.getWidth(), mHeight - bottomBitmap.getHeight(), mPaint);
        canvas.drawBitmap(borderBitmap, mWidth - borderBitmap.getWidth() - (bottomBitmap.getWidth() - borderBitmap.getWidth()) / 2,
                mHeight - borderBitmap.getHeight() - (bottomBitmap.getWidth() - borderBitmap.getWidth()) / 2, mPaint);
        canvas.drawBitmap(wheelBitmap, tempCenterX, tempCenterY, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float moveX = event.getX();
        float moveY = event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                isDrag = isWheel(moveX, moveY);
                if(isDrag){
                    listener.checkDragPoint();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isDrag) {
                    boolean tempDrag = isOutWheel(moveX, moveY);
                    if(tempDrag){
                        tempCenterX = moveX - wheelRadius;
                        tempCenterY = moveY - wheelRadius;

                    }else{
                        PointF pointF = returnIntersect(moveX, moveY);
                        tempCenterX = pointF.x - wheelRadius;
                        tempCenterY = pointF.y - wheelRadius;
                    }
                    float disX = tempCenterX - firstCenterX;
                    float disY = tempCenterY - firstCenterY;
//                    listener.invalidateMoveXY(disX,disY);
                    invalidate();
                    firstCenterX = tempCenterX;
                    firstCenterY = tempCenterY;
                    listener.invalidateMoveXY(moveX - lastDragX,moveY - lastDragY);
                    lastDragX = moveX;
                    lastDragY = moveY;
                }
                break;
            case MotionEvent.ACTION_UP:
                tempCenterX = centerTopX;
                tempCenterY = centerTopY;
                firstCenterX = tempCenterX;
                firstCenterY = tempCenterY;
                lastDragX = centerX;
                lastDragY = centerY;
                listener.invalidateUpXY();
                invalidate();
                break;
            default:
        }
        return isDrag;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        centerX = mWidth - bottomBitmap.getWidth() / 2;
        centerY = mHeight - bottomBitmap.getWidth() / 2;
        centerTopX = centerX - wheelBitmap.getWidth() / 2;
        centerTopY = centerY - wheelBitmap.getHeight() / 2;
        tempCenterX = centerTopX;
        tempCenterY = centerTopY;
        wheelRadius = wheelBitmap.getWidth() / 2;
        firstCenterX = tempCenterX;
        firstCenterY = tempCenterY;
        lastDragX = centerX;
        lastDragY = centerY;
    }

    /**
     * 滚轮是否可以滚动
     *
     * @return
     */
    private boolean isWheel(float moveX, float moveY) {
        int wheelRadiu = borderBitmap.getWidth() / 2;
        int distance = (int) Math.sqrt(Math.pow(Math.abs(centerX - moveX), 2) + Math.pow(Math.abs(centerY - moveY), 2));
        if (distance <= wheelRadiu) {
            return true;
        }
        return false;
    }

    private boolean isOutWheel(float moveX, float moveY) {
        int wheelRadiu = borderBitmap.getWidth() / 2;
        int distance = (int) Math.sqrt(Math.pow(Math.abs(centerX - moveX), 2) + Math.pow(Math.abs(centerY - moveY), 2));
        if ((distance + wheelRadius) <= wheelRadiu) {
            return true;
        }
        return false;
    }

    private PointF returnIntersect(float moveX, float moveY){
        PointF pointF = new PointF();
        //移动点到圆心的距离
        double distance = Math.sqrt(Math.pow(Math.abs(centerX - moveX), 2) + Math.pow(Math.abs(centerY - moveY), 2));
        //滚轮与边框的相切点与圆心的距离
        double distance2 = borderBitmap.getWidth() / 2 - wheelRadius;
        double d = distance/distance2;
        pointF.x = (float) ((moveX - centerX + d * centerX)/d);
        pointF.y = (float) ((moveY - centerY + d * centerY)/d);
        return pointF;
    }

}

