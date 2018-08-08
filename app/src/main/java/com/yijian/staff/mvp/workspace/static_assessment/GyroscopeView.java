package com.yijian.staff.mvp.workspace.static_assessment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.yijian.staff.R;

public class GyroscopeView extends View {

    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private Bitmap rotationArrow_top;
    private int rotation;
    private PorterDuffXfermode xfermode;
    private int outStrokeWidth = 5;
    private int lineStrokeWidth = 5;

    public GyroscopeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rotationArrow_top = BitmapFactory.decodeResource(getResources(), R.mipmap.arrow_above);
        xfermode=new PorterDuffXfermode(PorterDuff.Mode.DST_OVER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF ovalRectF = new RectF(outStrokeWidth/2, outStrokeWidth/2, mWidth - outStrokeWidth/2, mHeight - outStrokeWidth/2);
        Path path = new Path();
        path.addOval(ovalRectF, Path.Direction.CW);
        canvas.clipPath(path);
        int sc =canvas.saveLayer(0,0,mWidth,mHeight,mPaint,Canvas.ALL_SAVE_FLAG);
        mPaint.setStrokeWidth(outStrokeWidth);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawOval(ovalRectF, mPaint);

        mPaint.setXfermode(xfermode);
        Matrix matrix = new Matrix();
        matrix.postRotate(rotation, mWidth/2, mHeight/2);
        canvas.setMatrix(matrix);

        if(rotation > 356 || rotation < 5){
            mPaint.setColor(Color.BLUE);
        }else{
            mPaint.setColor(Color.RED);
        }
        mPaint.setStrokeWidth(lineStrokeWidth);
        Path pathLine = new Path();
        pathLine.moveTo(0,mHeight/2);
        pathLine.lineTo(mWidth, mHeight/2);
        canvas.drawPath(pathLine, mPaint);
        canvas.drawBitmap(rotationArrow_top, mWidth/2 - rotationArrow_top.getWidth()/2, outStrokeWidth, null);

        mPaint.setXfermode(null);
        canvas.restoreToCount(sc);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    public void setOritationRotation(int rotation){
        this.rotation = rotation;
        invalidate();
    }


}
