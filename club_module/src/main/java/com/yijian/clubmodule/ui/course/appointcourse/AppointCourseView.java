package com.yijian.clubmodule.ui.course.appointcourse;

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

import com.yijian.clubmodule.R;
import com.yijian.clubmodule.bean.P2mToBCappVOSBean;
import com.yijian.clubmodule.ui.course.punch.CoursePunchActivity;
import com.yijian.commonlib.prefs.SharePreferenceUtil;
import com.yijian.commonlib.util.DateUtil;
import com.yijian.commonlib.util.ImageLoader;

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

    public AppointCourseView(@NonNull Context context) {
        this(context, null);
    }

    public AppointCourseView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AppointCourseView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    public void setHeightAndSize(int height, int size) {
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
                canvas.drawText(i / 2 + ":00", (getPaddingLeft() - mTextPaint.measureText(i / 2 + ":00")) / 2, i * itemHeight + getPaddingTop() + mTextPaint.getTextSize() / 2, mTextPaint);
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


    public void addItem(P2mToBCappVOSBean appointCourseBean) {
        int status = appointCourseBean.getStatus();
        if (status == 1 || status == 3 || status == 4 || status == 5) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.appoint_course_item_view, null, false);
            addView(view);

            ImageView iv_header = view.findViewById(R.id.iv_header);
            TextView tv_name = view.findViewById(R.id.tv_name);
            TextView tv_course_name = view.findViewById(R.id.tv_course_name);
            TextView tv_course_status = view.findViewById(R.id.tv_course_status);
            LinearLayout ll_content = view.findViewById(R.id.ll_content);
            ImageLoader.setHeadImageResource(SharePreferenceUtil.getHostUrl() + appointCourseBean.getHeadPath(), view.getContext(), iv_header);
            tv_name.setText(appointCourseBean.getMemberName());
            String memberCourseName = appointCourseBean.getMemberCourseName();
            tv_course_name.setText("私教课: "+memberCourseName);
            ll_content.setBackgroundColor(Color.parseColor("#f5f5f5"));
            tv_course_status.setVisibility(View.VISIBLE);
            switch (status) {//约课状态（1已约课，2取消约课，3：会员已上课，4：会员爽约（此状态为系统定时服务更新用），5：已评价，6：不可约）
                case 1:
                    tv_course_status.setText("上课");
                    tv_course_status.setBackgroundColor(Color.parseColor("#fca354"));
                    tv_course_status.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, CoursePunchActivity.class);
                            intent.putExtra("appointId", appointCourseBean.getPrivateapply2memberId());
                            mContext.startActivity(intent);
                        }
                    });
                    break;
                case 3:
                    tv_course_status.setText("评价");
                    tv_course_status.setBackgroundColor(Color.parseColor("#fca354"));
                    tv_course_status.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, CoursePunchActivity.class);
                            intent.putExtra("appointId", appointCourseBean.getPrivateapply2memberId());
                            mContext.startActivity(intent);
                        }
                    });
                    break;
                case 5:
                    tv_course_status.setText("查看");
                    tv_course_status.setBackgroundColor(Color.parseColor("#5cd6b5"));
                    tv_course_status.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, CoursePunchActivity.class);
                            intent.putExtra("appointId", appointCourseBean.getPrivateapply2memberId());
                            mContext.startActivity(intent);
                        }
                    });
                    break;
                case 4:
                    tv_course_status.setText("会员爽约");
                    tv_course_status.setBackgroundColor(Color.parseColor("#cdcdcd"));
                    break;
            }

            String startTime = appointCourseBean.getStartTime();
            String endTime = appointCourseBean.getEndTime();
            long startTimestringToDate = DateUtil.getStringToDate(startTime, "HH:mm");
            long endTimestringToDate = DateUtil.getStringToDate(endTime, "HH:mm");
            long currentDate = DateUtil.getStringToDate("00:00", "HH:mm");

            int height = itemHeight * itemSize;
            long l1 = 86400000;
            long l2 = startTimestringToDate - currentDate;
            long l3 = endTimestringToDate - currentDate;
            long top = height * l2 / l1;
            long bottom = height * l3 / l1;

            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.width = LayoutParams.MATCH_PARENT;
            layoutParams.height = (int) (bottom - top);
            layoutParams.topMargin = (int) top;
            view.setLayoutParams(layoutParams);
        }else if (status==6){
            View view = LayoutInflater.from(mContext).inflate(R.layout.lock_view, null, false);
            addView(view);

            String startTime = appointCourseBean.getStartTime();
            String endTime = appointCourseBean.getEndTime();
            long startTimestringToDate = DateUtil.getStringToDate(startTime, "HH:mm");
            long endTimestringToDate = DateUtil.getStringToDate(endTime, "HH:mm");
            long currentDate = DateUtil.getStringToDate("00:00", "HH:mm");

            int height = itemHeight * itemSize;
            long l1 = 86400000;
            long l2 = startTimestringToDate - currentDate;
            long l3 = endTimestringToDate - currentDate;
            long top = height * l2 / l1;
            long bottom = height * l3 / l1;

            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.width = LayoutParams.MATCH_PARENT;
            layoutParams.height = (int) (bottom - top);
            layoutParams.topMargin = (int) top;
            view.setLayoutParams(layoutParams);
        }


    }

    public void clearView() {
        removeAllViews();
    }

}
