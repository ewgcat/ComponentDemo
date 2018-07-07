package com.yijian.staff.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;
import com.yijian.staff.R;

public class CircleProgressBar extends View {

    private Paint mPaint;
    private int radius;
    private int circleStrokeWidth;
    private int textStrokeWidth;
    private int cx,cy;
    private Context mContext;
    private float progress = 0;
    private int textSize;
    private int mSpeed = 30;


    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        this.mContext = context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        TypedArray tArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar);
        textSize = tArray.getDimensionPixelSize(R.styleable.CircleProgressBar_textSize, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
        circleStrokeWidth = (int) tArray.getDimension(R.styleable.CircleProgressBar_circleStrokeWidth, 10);
        textStrokeWidth = (int) tArray.getDimension(R.styleable.CircleProgressBar_textStrokeWidth, 5);
        tArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        Toast.makeText(mContext, "onDraw.....", 0).show();
        mPaint.setStrokeWidth(circleStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#fcdfdf"));
        canvas.drawCircle(cx, cy, radius, mPaint);
        mPaint.setColor(Color.parseColor("#f27a7a"));
        canvas.drawArc(new RectF(circleStrokeWidth/2, circleStrokeWidth/2,
                        getMeasuredWidth()-circleStrokeWidth/2, getMeasuredWidth()-circleStrokeWidth/2)
                , 270, (360*progress/100), false, mPaint);

        String txtProgress = progress+"%";
        mPaint.setTextSize(textSize);
        mPaint.setStrokeWidth(textStrokeWidth);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        Paint.FontMetricsInt fm = mPaint.getFontMetricsInt();
        float diff = Math.abs(fm.top) - (fm.bottom-fm.top)/2;
        float baseLine = (getMeasuredHeight())/2 + diff;
        canvas.drawText(txtProgress, (getMeasuredWidth()-mPaint.measureText(txtProgress))/2, baseLine, mPaint);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // TODO Auto-generated method stub
        super.onSizeChanged(w, h, oldw, oldh);
        radius = w/2-circleStrokeWidth/2;
        cx = w/2;
        cy = w/2;
    }

    public void setProgress(int totalProgress){
        new Thread()
        {
            @Override
            public void run()
            {
                while (true)
                {
                    progress++;
                    if (progress <= totalProgress)
                    {
                        postInvalidate();
                    }
                    try
                    {
                        Thread.sleep(mSpeed);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            };
        }.start();
    }

}
