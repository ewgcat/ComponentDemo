package com.yijian.staff.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;

public class CircleProgressBar extends View {

    private Paint mPaint,txtBasePaint;
    private int radius;
    private int circleStrokeWidth;
    private int textStrokeWidth;
    private int textBaseStrokeWidth;
    private int cx, cy;
    private int progress = 0;
    private int totalProgress = 0;
    private int textSize;
    private int textBaseSize;
    private int mSpeed = 30;
    private TextView cunkeProgress;
    private Typeface typeFace_diy;

    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        txtBasePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        TypedArray tArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar);
        textSize = tArray.getDimensionPixelSize(R.styleable.CircleProgressBar_textSize, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
        textBaseSize = tArray.getDimensionPixelSize(R.styleable.CircleProgressBar_textBaseSize, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
        circleStrokeWidth = (int) tArray.getDimension(R.styleable.CircleProgressBar_circleStrokeWidth, 10);
        textStrokeWidth = (int) tArray.getDimension(R.styleable.CircleProgressBar_textStrokeWidth, 5);
        textBaseStrokeWidth = (int) tArray.getDimension(R.styleable.CircleProgressBar_textBaseStrokeWidth, 5);
        tArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        if(typeFace_diy != null){
            mPaint.setTypeface(typeFace_diy);
            txtBasePaint.setTypeface(typeFace_diy);

        }
        mPaint.setStrokeWidth(circleStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);

        if (progress < 60) {
            mPaint.setColor(Color.parseColor("#fcdfdf"));
        } else {
            mPaint.setColor(Color.parseColor("#d3f3ea"));
        }


        canvas.drawCircle(cx, cy, radius, mPaint);
        if (progress < 60) {
            mPaint.setColor(Color.parseColor("#f27a7a"));
            cunkeProgress.setTextColor(Color.parseColor("#f27a7a"));
            txtBasePaint.setColor(Color.parseColor("#f27a7a"));
        }else{
            mPaint.setColor(Color.parseColor("#46cba7"));
            cunkeProgress.setTextColor(Color.parseColor("#46cba7"));
            txtBasePaint.setColor(Color.parseColor("#46cba7"));
        }
        canvas.drawArc(new RectF(circleStrokeWidth / 2, circleStrokeWidth / 2,
                        getMeasuredWidth() - circleStrokeWidth / 2, getMeasuredWidth() - circleStrokeWidth / 2)
                , 270, (360 * progress / 100), false, mPaint);

        String txtProgress = progress+"";
        mPaint.setTextSize(textSize);
        mPaint.setStrokeWidth(textStrokeWidth);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        Paint.FontMetricsInt fm = mPaint.getFontMetricsInt();
        float diff = Math.abs(fm.top) - (fm.bottom - fm.top) / 2;
        float baseLine = (getMeasuredHeight()) / 2 + diff;
//        canvas.drawText(txtProgress, (getMeasuredWidth() - mPaint.measureText(txtProgress)) / 2, baseLine, mPaint);

        String txtBase = "%";
        txtBasePaint.setTextSize(textBaseSize);
        txtBasePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        txtBasePaint.setStrokeWidth(textBaseStrokeWidth);
        Paint.FontMetricsInt fm2 = mPaint.getFontMetricsInt();

        float txtProgressWidth = mPaint.measureText(txtProgress);
        float txtBaseWidth = txtBasePaint.measureText(txtBase);

        canvas.drawText(txtProgress, (getMeasuredWidth() - (txtProgressWidth+txtBaseWidth)) / 2, baseLine, mPaint);
        canvas.drawText(txtBase, (getMeasuredWidth() - (txtProgressWidth+txtBaseWidth)) / 2 + txtProgressWidth, baseLine, txtBasePaint);

        if(progress < totalProgress){
            progress++;
            postInvalidate();
        }

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // TODO Auto-generated method stub
        super.onSizeChanged(w, h, oldw, oldh);
        radius = w / 2 - circleStrokeWidth / 2;
        cx = w / 2;
        cy = w / 2;
    }

    public void setProgress(int totalProgress) {
        progress = 0;
        this.totalProgress = totalProgress;
    }

    public void setCunkeViewTextColor(TextView cunkeProgress){
        this.cunkeProgress = cunkeProgress;
    }

    public void setTypeFace_DIY(Typeface typeFace_diy){
        this.typeFace_diy = typeFace_diy;
    }

}
