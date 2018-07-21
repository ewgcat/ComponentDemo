package com.yijian.staff.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.util.CommonUtil;

public class TaskCircleProgressBar extends View {

    private Paint mPaint,txtBasePaint1, txtBasePaint;
    private int radius;
    private int circleStrokeWidth;
    private int textStrokeWidth;
    private int textBaseStrokeWidth;
    private int cx, cy;
    private int progress = 0;
    private int totalProgress = 0;
    private int textSize;
    private int textBaseSize;
    private Typeface typeFace_diy;

    public TaskCircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        txtBasePaint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        txtBasePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        TypedArray tArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar);
        textSize = tArray.getDimensionPixelSize(R.styleable.CircleProgressBar_textSize, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX, CommonUtil.dp2px(context, 20), getResources().getDisplayMetrics()));
        textBaseSize = tArray.getDimensionPixelSize(R.styleable.CircleProgressBar_textBaseSize, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX, CommonUtil.dp2px(context, 20), getResources().getDisplayMetrics()));
        circleStrokeWidth = (int) tArray.getDimension(R.styleable.CircleProgressBar_circleStrokeWidth, CommonUtil.dp2px(context, 20));
        textStrokeWidth = (int) tArray.getDimension(R.styleable.CircleProgressBar_textStrokeWidth, CommonUtil.dp2px(context, 10));
        textBaseStrokeWidth = (int) tArray.getDimension(R.styleable.CircleProgressBar_textBaseStrokeWidth, CommonUtil.dp2px(context, 10));
        tArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        if (typeFace_diy != null) {
            mPaint.setTypeface(typeFace_diy);
            txtBasePaint1.setTypeface(typeFace_diy);
            txtBasePaint.setTypeface(typeFace_diy);

        }
        mPaint.setStrokeWidth(circleStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);


        mPaint.setColor(Color.parseColor("#efefef"));
        canvas.drawCircle(cx, cy, radius, mPaint);

        mPaint.setColor(Color.parseColor("#1997f8"));
        txtBasePaint1.setColor(Color.parseColor("#333333"));
        txtBasePaint.setColor(Color.parseColor("#333333"));

        canvas.drawArc(new RectF(circleStrokeWidth / 2, circleStrokeWidth / 2,
                        getMeasuredWidth() - circleStrokeWidth / 2, getMeasuredWidth() - circleStrokeWidth / 2)
                , 270, (360 * progress / 100), false, mPaint);

        String txtProgress = progress + "";
        txtBasePaint1.setTextSize(textSize);
        txtBasePaint1.setStrokeWidth(textStrokeWidth);
        txtBasePaint1.setStyle(Paint.Style.FILL_AND_STROKE);
        Paint.FontMetricsInt fm = txtBasePaint1.getFontMetricsInt();
        float diff = Math.abs(fm.top) - (fm.bottom - fm.top) / 2;
        float baseLine = (getMeasuredHeight()) / 2 ;

        String txtBase = " %";
        txtBasePaint.setTextSize(textBaseSize);
        txtBasePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        txtBasePaint.setStrokeWidth(textBaseStrokeWidth);
        Paint.FontMetricsInt fm2 = mPaint.getFontMetricsInt();

        float txtProgressWidth = txtBasePaint1.measureText(txtProgress);
        float txtBaseWidth = txtBasePaint.measureText(txtBase);

        canvas.drawText(txtProgress, (getMeasuredWidth() - (txtProgressWidth + txtBaseWidth)) / 2, baseLine, txtBasePaint1);
        canvas.drawText(txtBase, (getMeasuredWidth() - (txtProgressWidth + txtBaseWidth)) / 2 + txtProgressWidth, baseLine, txtBasePaint);

        if (progress < totalProgress) {
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



    public void setTypeFace_DIY(Typeface typeFace_diy) {
        this.typeFace_diy = typeFace_diy;
    }

}
