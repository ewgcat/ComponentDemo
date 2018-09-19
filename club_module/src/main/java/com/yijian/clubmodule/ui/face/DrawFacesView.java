package com.yijian.clubmodule.ui.face;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Xfermode;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 绘制脸部方框的view，实测发现返回的脸部数据中没有具体的眼睛，嘴巴等数据
 */
public class DrawFacesView extends View {

    private Matrix matrix;
    private Paint paint;
    private Paint strokeLinePaint;
    private Camera.Face[] faces;
    private boolean isClear;
    private int strokeLinePaintWidth = 1;
    private int strokePaintWidth = 1;
    private boolean isRemove = false;

    public DrawFacesView(Context context) {
        this(context, null);
    }

    public DrawFacesView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawFacesView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(strokePaintWidth);
        paint.setStyle(Paint.Style.STROKE);
        faces = new Camera.Face[]{};

        strokeLinePaint = new Paint();
        strokeLinePaint.setStyle(Paint.Style.FILL);
        strokeLinePaint.setColor(Color.WHITE);
        strokeLinePaint.setAntiAlias(true);
        strokeLinePaintWidth = strokePaintWidth + 5;
        strokeLinePaint.setStrokeWidth(strokeLinePaintWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isClear) {
            Log.e("Test", "清楚人脸框......");
            canvas.drawColor(Color.WHITE, PorterDuff.Mode.CLEAR);
            isClear = false;
        } else {

            int sc=canvas.saveLayer(0,0,getMeasuredWidth(),getMeasuredHeight(),paint,Canvas.ALL_SAVE_FLAG);

            paint.setColor(Color.parseColor("#50000000"));
            canvas.drawRect(new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight()), paint);

            canvas.setMatrix(matrix);
            for (Camera.Face face : faces) {
                Log.e("Test", "获取到人脸数据。。。。。");
                if (face == null) break;

                paint.setStyle(Paint.Style.FILL);
                paint.setAlpha(254);
                Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
                paint.setXfermode(xfermode);
                canvas.drawRect(face.rect, paint);
                paint.setXfermode(null);

                Rect rectangle = face.rect;
                canvas.drawLine(rectangle.left - strokePaintWidth - 2, rectangle.top - strokePaintWidth, rectangle.left + (rectangle.right - rectangle.left) / 4, rectangle.top - strokePaintWidth, strokeLinePaint); //左上(横)
                canvas.drawLine(rectangle.left - strokePaintWidth, rectangle.top - strokePaintWidth - 2, rectangle.left - strokePaintWidth, rectangle.top + (rectangle.bottom - rectangle.top) / 4, strokeLinePaint);//左上（竖）

                canvas.drawLine(rectangle.right + strokePaintWidth + 2, rectangle.top - strokePaintWidth, rectangle.right - (rectangle.right - rectangle.left) / 4, rectangle.top - strokePaintWidth, strokeLinePaint); //右上（横）
                canvas.drawLine(rectangle.right + strokePaintWidth, rectangle.top - strokePaintWidth - 2, rectangle.right + strokePaintWidth, rectangle.top + (rectangle.bottom - rectangle.top) / 4, strokeLinePaint); //右上 （竖）

                canvas.drawLine(rectangle.left - strokePaintWidth - 2, rectangle.bottom + strokePaintWidth, rectangle.left + (rectangle.right - rectangle.left) / 4, rectangle.bottom + strokePaintWidth, strokeLinePaint); //左下（横）
                canvas.drawLine(rectangle.left - strokePaintWidth, rectangle.bottom + strokePaintWidth + 2, rectangle.left - strokePaintWidth, rectangle.bottom - (rectangle.bottom - rectangle.top) / 4, strokeLinePaint); //左下（竖）

                canvas.drawLine(rectangle.right + strokePaintWidth + 2, rectangle.bottom + strokePaintWidth, rectangle.right - (rectangle.right - rectangle.left) / 4, rectangle.bottom + strokePaintWidth, strokeLinePaint); //右下（横）
                canvas.drawLine(rectangle.right + strokePaintWidth, rectangle.bottom + strokePaintWidth + 2, rectangle.right + strokePaintWidth, rectangle.bottom - (rectangle.bottom - rectangle.top) / 4, strokeLinePaint); //右下（竖）


                if (face.leftEye != null)
                    canvas.drawPoint(face.leftEye.x, face.leftEye.y, paint);
                if (face.rightEye != null)
                    canvas.drawPoint(face.rightEye.x, face.rightEye.y, paint);
                if (face.mouth != null)
                    canvas.drawPoint(face.mouth.x, face.mouth.y, paint);
                // 因为旋转了画布矩阵，所以字体也跟着旋转
//            canvas.drawText(String.valueOf("id:" + face.id + "\n置信度:" + face.score), face.rect.left, face.rect.bottom + 10, paint);


            }

            canvas.restoreToCount(sc);

            if (isRemove) {
                Log.e("Test", "isRemove....." + isRemove);
                canvas.drawColor(Color.WHITE, PorterDuff.Mode.CLEAR);
                isRemove = false;
            }

        }
    }

    /**
     * 绘制脸部方框
     *
     * @param matrix 旋转画布的矩阵
     * @param faces  脸部信息数组
     */
    public void updateFaces(Matrix matrix, Camera.Face[] faces) {
        this.matrix = matrix;
        this.faces = faces;
        invalidate();
    }

    /**
     * 清除已经画上去的框
     */
    public void removeRect() {
        isClear = true;
        invalidate();
    }

    public void isRemove(boolean isRemove) {
        this.isRemove = isRemove;
    }

}
