package com.yijian.staff.mvp.course.yueke;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.course.punch.CoursePunchActivity;
import com.yijian.staff.util.DateUtil;

import java.util.zip.Inflater;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/21 15:15:00
 */
public class AppointCourseView extends FrameLayout {
    private int itemHeight = 200;
    private int itemSize = 24;
    private Context mContext;
    private Paint mPaint; //分割线高度
    private TextPaint mTextPaint;

    private Paint mRedPaint; //分割线高度
    private TextPaint mRedTextPaint;

    public  AppointCourseView(@NonNull Context context) {
        this(context, null);
    }

    public  AppointCourseView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public  AppointCourseView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    public void setWidthAndHeight(int height, int size) {
        this.itemHeight = height;
        this.itemSize = size;
        requestLayout();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(resolveSizeAndState(getSuggestedMinimumWidth(), widthMeasureSpec, 0), itemHeight * itemSize + getPaddingTop() + getPaddingBottom());
    }

    private void init(Context context) {
        setWillNotDraw(false);
        mContext = context;
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#eaeaea"));
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(1.0f);
        mTextPaint = new TextPaint();
        mTextPaint.setColor(Color.parseColor("#999999"));
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(30);
        mRedPaint = new Paint();
        mRedPaint.setColor(Color.parseColor("#ff0000"));
        mRedPaint.setAntiAlias(true);
        mRedPaint.setStrokeWidth(1.0f);
        mRedTextPaint = new TextPaint();
        mRedTextPaint.setColor(Color.parseColor("#ff0000"));
        mRedTextPaint.setAntiAlias(true);
        mRedTextPaint.setTextSize(30);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLineAndTime(canvas);

    }

    private void drawLineAndTime(Canvas canvas) {
        canvas.drawLine(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getPaddingTop(), mPaint);
        canvas.drawText("00:00", (getPaddingLeft() - mTextPaint.measureText("00:00")) / 2, getPaddingTop() + mTextPaint.getTextSize() / 2, mTextPaint);
        for (int i = 1; i <= itemSize; i++) {
            canvas.drawLine(getPaddingLeft(), i * itemHeight + getPaddingTop(), getWidth() - getPaddingRight(), i * itemHeight + getPaddingTop(), mPaint);
            if (i % 2 == 0) {
                canvas.drawText(i/2 + ":00", (getPaddingLeft() - mTextPaint.measureText(i/2 + ":00")) / 2, i * itemHeight + getPaddingTop() + mTextPaint.getTextSize() / 2, mTextPaint);
            }
        }

        drawCurrentTime(canvas);


    }

    private void drawCurrentTime(Canvas canvas) {
        long l = System.currentTimeMillis();
        String dateToString = DateUtil.getDateToString(l, "HH:mm");

        long currentDate = DateUtil.getStringToDate(DateUtil.getCurrentDate(), "yyyy-MM-dd");

        long l1 = 86400000;
        long l2 = l - currentDate;
        long top = itemHeight * itemSize * l2 / l1 + getPaddingTop();
        canvas.drawLine(getPaddingLeft(), top, getWidth() - getPaddingRight(), top, mRedPaint);
        canvas.drawText(dateToString, (getPaddingLeft() - mTextPaint.measureText(dateToString)) / 2, top + mTextPaint.getTextSize() / 2, mRedTextPaint);
    }


    public void addItem(String s,int position) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.appoint_course_item_view, null, false);
        ImageView iv_header = view.findViewById(R.id.iv_header);
        TextView  tv_name =  view.findViewById(R.id.tv_name);
        TextView  tv_course_name =  view.findViewById(R.id.tv_course_name);
        TextView  tv_course_status =  view.findViewById(R.id.tv_course_status);
        LinearLayout    ll_content =  view.findViewById(R.id.ll_content);

        if (s.equals("8")){
            tv_course_name.setText(s);
            ll_content.setBackgroundColor(Color.parseColor("#f5f5f5"));
            tv_course_status.setVisibility(View.VISIBLE);
            tv_course_status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, CoursePunchActivity.class);
                    intent.putExtra("appointId",s);
                    mContext.startActivity(intent);
                }
            });
            addView(view);
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.width = LayoutParams.MATCH_PARENT;
            layoutParams.height =itemHeight*2 ;
            layoutParams.topMargin=itemHeight*2*position;
            view.setLayoutParams(layoutParams);
        }else {
            tv_course_name.setText("");
            ll_content.setBackgroundColor(Color.WHITE);
            tv_course_status.setVisibility(View.GONE);
        }


    }

    private void removeItem(View view) {
        removeView(view);
    }


}
