package com.yijian.staff.mvp.course.schedule.week;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yijian.staff.BuildConfig;
import com.yijian.staff.R;
import com.yijian.staff.bean.CourseStudentBean;
import com.yijian.staff.mvp.course.schedule.day.FlagPopuwindow;
import com.yijian.staff.mvp.course.schedule.day.LockTimePopuwindow;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.DateUtil;
import com.yijian.staff.util.ImageLoader;
import com.yijian.staff.util.Logger;

import java.util.ArrayList;

import static com.yijian.staff.application.CustomApplication.SCREEN_WIDTH;
import static com.yijian.staff.mvp.course.schedule.day.FlagPopuwindow.BLUE_FLAG;
import static com.yijian.staff.mvp.course.schedule.day.FlagPopuwindow.GREEN_FLAG;
import static com.yijian.staff.mvp.course.schedule.day.FlagPopuwindow.RED_FLAG;
import static com.yijian.staff.mvp.course.schedule.day.FlagPopuwindow.WHITE_FLAG;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/21 15:15:00
 */
public class WeekCourseView extends FrameLayout  {
    private static String TAG = WeekCourseView.class.getSimpleName();

    private int itemHeight = 200;
    private int itemWidth = 100;
    private int itemSize = 48;
    private Context mContext;
    private Paint mPaint; //分割线高度
    private TextPaint mTextPaint;

    private Paint mRedPaint; //分割线高度
    private TextPaint mRedTextPaint;
    private FlagPopuwindow popuwindow;

    private int mLastMotionX, mLastMotionY,scollY;
    //是否移动了
    private boolean isMoved;
    //是否释放了
    private boolean isReleased;
    //长按的runnable
    private Runnable mLongPressRunnable;
    //移动的阈值
    private static final int TOUCH_SLOP = 20;
    private int maxHeight;

    private ArrayList<View> views = new ArrayList<>();



    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {


        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Logger.i(TAG, "ACTION_DOWN");

              break;
            case MotionEvent.ACTION_MOVE:

                Logger.i(TAG, "ACTION_MOVE");

                break;
            case MotionEvent.ACTION_UP:
                //释放了
                isReleased = true;

                Logger.i(TAG, "ACTION_UP");
                break;
        }



        return true;
    }



    public WeekCourseView(@NonNull Context context) {
        this(context, null);
    }

    public WeekCourseView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public WeekCourseView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

        init();


    }

    private Activity activity;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }




    public void onScollYPosition(int y) {
        this.scollY = y;
    }




    public void setItemParams(int width,int height, int size) {
        this.itemWidth = width;
        this.itemHeight = height;
        this.itemSize = size;
        requestLayout();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(resolveSizeAndState(getSuggestedMinimumWidth(), widthMeasureSpec, 0), itemHeight * itemSize + getPaddingTop() + getPaddingBottom());
    }

    private void init() {
        setWillNotDraw(false);
        popuwindow = new FlagPopuwindow(mContext);
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
        drawHorizontalLineAndTime(canvas);
        drawVerticalLine(canvas);
    }

    private void drawHorizontalLineAndTime(Canvas canvas) {


        canvas.drawLine(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getPaddingTop(), mPaint);
        canvas.drawText("00:00", (getPaddingLeft() - mTextPaint.measureText("00:00")) / 2, getPaddingTop() + mTextPaint.getTextSize() / 2, mTextPaint);
        for (int i = 1; i <= itemSize; i++) {
            if (i % 2 == 0) {
                canvas.drawLine(getPaddingLeft(), i * itemHeight + getPaddingTop(), getWidth() - getPaddingRight(), i * itemHeight + getPaddingTop(), mPaint);
                canvas.drawText(i / 2 + ":00", (getPaddingLeft() - mTextPaint.measureText(i / 2 + ":00")) / 2, i * itemHeight + getPaddingTop() + mTextPaint.getTextSize() / 2, mTextPaint);
            }
        }
        drawCurrentTime(canvas);
    }

    private void drawVerticalLine(Canvas canvas) {

        for (int i = 0; i < 7; i++) {
                canvas.drawLine(getPaddingLeft()+itemWidth*i,  getPaddingTop(), getPaddingLeft()+itemWidth*i, itemHeight*itemSize + getPaddingTop(), mPaint);
        }
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


    public void addItem(CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean courseBean,int weekCode) {
        String startTime = courseBean.getSTime();
        String endTime = courseBean.getETime();
        View view = LayoutInflater.from(mContext).inflate(R.layout.week_course_view, null, false);
        View ll_week_course = view.findViewById(R.id.ll_week_course);
        TextView tv_member_name = view.findViewById(R.id.tv_member_name);
        addView(view);
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
        layoutParams.width = itemWidth;
        layoutParams.height = (int) (bottom - top);
        layoutParams.topMargin = (int) top;
        layoutParams.leftMargin = itemWidth*weekCode;
        view.setLayoutParams(layoutParams);
        if (courseBean.getDataType() == 1) {
            view.setTag("课程");
            views.add(view);
            CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCourseMemberVOBean privateCourseMemberVO = courseBean.getPrivateCourseMemberVO();
            String colour = courseBean.getColour();
            if (TextUtils.isEmpty(colour)) {
                colour = "#f3f3f3";
            }
            tv_member_name.setText(privateCourseMemberVO.getMemberName());
            ll_week_course.setBackgroundColor(Color.parseColor(colour));

        } else {
            ll_week_course.setBackgroundColor(Color.parseColor("#808080"));
        }

    }

    public void clearView() {
        removeAllViewsInLayout();
        views.clear();
    }

    public void removeLockView(View view) {
        removeView(view);
        views.remove(view);
    }





}
