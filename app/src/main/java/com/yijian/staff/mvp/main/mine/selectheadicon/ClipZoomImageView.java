package com.yijian.staff.mvp.main.mine.selectheadicon;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver;

import com.yijian.staff.util.Logger;


@SuppressWarnings("deprecation")
public class ClipZoomImageView extends AppCompatImageView implements OnScaleGestureListener, OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener {

    public static float SCALE_MAX = 8.0f;
    private static float SCALE_MID = 0.01f;

    /**
     * 水平方向与View的边距
     */
    private int mHorizontalPadding;//——屏幕边缘离截图区的宽度

    /**
     * 垂直方向与View的边距
     */
    private int mVerticalPadding;//——屏幕顶部离截图区的高度

    /**
     * 初始化时的缩放比例，如果图片宽或高大于屏幕，此值将小于0
     */
    private float initScale = 0.25f;
    private boolean once = true;

    /**
     * 用于存放矩阵的9个值
     */
    private final float[] matrixValues = new float[9];

    /**
     * 缩放的手势检测
     */
    private ScaleGestureDetector mScaleGestureDetector = null;
    private final Matrix mScaleMatrix = new Matrix();
    private int mTouchSlop = 0;
    private float mLastX;
    private float mLastY;
    private boolean isCanDrag;
    private int lastPointerCount;


    public ClipZoomImageView(Context context) {
        this(context, null);
    }

    public ClipZoomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setScaleType(ScaleType.MATRIX);
        mScaleGestureDetector = new ScaleGestureDetector(context, this);
        this.setOnTouchListener(this);
    }

    public void setHorizontalPadding(int mHorizontalPadding) {
        this.mHorizontalPadding = mHorizontalPadding;
    }


    public void setVerticalPadding(int mVerticalPadding) {
        this.mVerticalPadding = mVerticalPadding;
    }

    /**
     * 是否是拖动行为
     *
     * @param dx
     * @param dy
     * @return
     */
    private boolean isCanDrag(float dx, float dy) {
        return Math.sqrt((dx * dx) + (dy * dy)) >= mTouchSlop;
    }


    /**
     * 获得当前的缩放比例
     *
     * @return
     */
    public final float getScale() {
        mScaleMatrix.getValues(matrixValues);
        return matrixValues[Matrix.MSCALE_X];
    }


    @Override
    public void onGlobalLayout() {
        if (once) {
            Drawable d = getDrawable();
            if (d == null) {
                return;
            }
            // 垂直方向的边距
            mVerticalPadding = (getHeight() - (getWidth() - 2 * mHorizontalPadding)) / 2;

            int width = getWidth();
            int height = getHeight();
            // 拿到图片的宽和高
            int dw = d.getIntrinsicWidth();
            int dh = d.getIntrinsicHeight();
            float scale = 1.0f;
            if (dw <= getWidth() - mHorizontalPadding * 2 && dh >= getHeight() - mVerticalPadding * 2) {
                //如果图片的宽度<截图区宽度，并且图片高度>截图区高度时
                scale = (getWidth() * 1.0f - mHorizontalPadding * 2) / dw;
                Logger.i("ImageTools", "图片的宽度<截图区宽度，并且图片高度>截图区高度时 scale=" + scale);
            }


            if (dh <= getHeight() - mVerticalPadding * 2 && dw >= getWidth() - mHorizontalPadding * 2) {
                //如果图片的高度<截图区高度，并且图片宽度>截图区宽度时
                scale = (getHeight() * 1.0f - mVerticalPadding * 2) / dh;
                Logger.i("ImageTools", "图片的高度<截图区高度，并且图片宽度>截图区宽度时 scale=" + scale);
            }

            if (dw <= getWidth() - mHorizontalPadding * 2 && dh <= getHeight() - mVerticalPadding * 2) {
                //如果图片的高度<截图区高度，并且图片宽度<截图区宽度时
                float scaleW = (getWidth() * 1.0f - mHorizontalPadding * 2) / dw;
                float scaleH = (getHeight() * 1.0f - mVerticalPadding * 2) / dh;
                scale = Math.max(scaleW, scaleH);
                Logger.i("ImageTools", "图片的高度<截图区高度，并且图片宽度<截图区宽度时 scale=" + scale);
            }


            Logger.i("ImageTools", " scale=" + scale);
            if (scale < 0.25f) {
                initScale = scale;
            }

            mScaleMatrix.postTranslate((width - dw) / 2, (height - dh) / 2);//平移至屏幕中心
            mScaleMatrix.postScale(scale, scale, getWidth() / 2, getHeight() / 2);//设置缩放比例
            // 图片移动至屏幕中心
            setImageMatrix(mScaleMatrix);
            once = false;
        }
    }


    /**
     * 根据当前图片的Matrix获得图片的范围
     *
     * @return
     */
    private RectF getMatrixRectF() {
        Matrix matrix = mScaleMatrix;
        RectF rect = new RectF();
        Drawable d = getDrawable();
        if (null != d) {
            rect.set(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            matrix.mapRect(rect);
        }
        return rect;
    }


    /**
     * 边界检测
     */
    private void checkBorder() {
        RectF rect = getMatrixRectF();
        float deltaX = 0;
        float deltaY = 0;

        int width = getWidth();
        int height = getHeight();
        // 如果宽或高大于屏幕，则控制范围 ; 这里的0.001是因为精度丢失会产生问题，但是误差一般很小，所以我们直接加了一个0.01
        if (rect.width() + 0.01 >= width - 2 * mHorizontalPadding) {
            if (rect.left > mHorizontalPadding) {
                deltaX = -rect.left + mHorizontalPadding;
            }
            if (rect.right < width - mHorizontalPadding) {
                deltaX = width - mHorizontalPadding - rect.right;
            }
        }
        if (rect.height() + 0.01 >= height - 2 * mVerticalPadding) {
            if (rect.top > mVerticalPadding) {
                deltaY = -rect.top + mVerticalPadding;
            }
            if (rect.bottom < height - mVerticalPadding) {
                deltaY = height - mVerticalPadding - rect.bottom;
            }
        }
        mScaleMatrix.postTranslate(deltaX, deltaY);
    }


    /**
     * 剪切图片，返回剪切后的bitmap对象
     *
     * @return
     */
    public Bitmap clip() {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        draw(canvas);

        //若为裁剪为方形区域
        return Bitmap.createBitmap(bitmap, mHorizontalPadding, mVerticalPadding, getWidth() - 2 * mHorizontalPadding, getWidth() - 2 * mHorizontalPadding);

        //若为裁剪为圆形区域，
        //return getCircleBitmap(Bitmap.createBitmap(bitmap, mHorizontalPadding,	mVerticalPadding, getWidth() - 2 * mHorizontalPadding,getWidth() - 2 * mHorizontalPadding));
    }


    /**
     * 将方形bitmap转换为圆形bitmap
     *
     * @param bitmap
     * @return
     */
    private Bitmap getCircleBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        int x = bitmap.getWidth();
        canvas.drawCircle(x / 2, x / 2, x / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }


    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        float scale = getScale();
        float scaleFactor = detector.getScaleFactor();
        if (getDrawable() == null) {
            return true;
        }
        /**
         * 缩放的范围控制
         */
        if ((scale < SCALE_MAX && scaleFactor > 1.0f) || (scale > initScale && scaleFactor < 1.0f)) {
            /**
             * 最大值最小值判断
             */
            if (scaleFactor * scale < initScale) {
                scaleFactor = initScale / scale;
            }
            if (scaleFactor * scale > SCALE_MAX) {
                scaleFactor = SCALE_MAX / scale;
            }
            /**
             * 设置缩放比例
             */
            mScaleMatrix.postScale(scaleFactor, scaleFactor, detector.getFocusX(), detector.getFocusY());
            checkBorder();
            setImageMatrix(mScaleMatrix);
        }
        return true;
    }


    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mScaleGestureDetector.onTouchEvent(event);
        float x = 0, y = 0;
        // 拿到触摸点的个数
        final int pointerCount = event.getPointerCount();
        // 得到多个触摸点的x与y均值
        for (int i = 0; i < pointerCount; i++) {
            x += event.getX(i);
            y += event.getY(i);
        }
        x = x / pointerCount;
        y = y / pointerCount;
        /**
         * 每当触摸点发生变化时，重置mLasX , mLastY
         */
        if (pointerCount != lastPointerCount) {
            isCanDrag = false;
            mLastX = x;
            mLastY = y;
        }
        lastPointerCount = pointerCount;
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float dx = x - mLastX;
                float dy = y - mLastY;

                if (!isCanDrag) {
                    isCanDrag = isCanDrag(dx, dy);
                }
                if (isCanDrag) {
                    if (getDrawable() != null) {
                        RectF rectF = getMatrixRectF();
                        // 如果宽度小于屏幕宽度，则禁止左右移动
                        if (rectF.width() <= getWidth() - mHorizontalPadding * 2) {
                            dx = 0;
                        }
                        // 如果高度小雨屏幕高度，则禁止上下移动
                        if (rectF.height() <= getHeight() - mVerticalPadding * 2) {
                            dy = 0;
                        }
                        mScaleMatrix.postTranslate(dx, dy);
                        checkBorder();
                        setImageMatrix(mScaleMatrix);
                    }
                }
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                lastPointerCount = 0;
                break;
        }
        return true;
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }


}
