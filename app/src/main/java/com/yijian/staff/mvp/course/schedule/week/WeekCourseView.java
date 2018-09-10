package com.yijian.staff.mvp.course.schedule.week;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.bean.CourseStudentBean;
import com.yijian.staff.mvp.course.schedule.day.FlagPopuwindow;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.DateUtil;
import com.yijian.staff.util.Logger;

import java.util.ArrayList;

import static android.support.v4.widget.ViewDragHelper.STATE_DRAGGING;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/21 15:15:00
 */
public class WeekCourseView extends FrameLayout {
    private static String TAG = WeekCourseView.class.getSimpleName();

    private int itemHeight = 200;
    private int itemWidth = 100;
    private int itemSize = 48;
    private float topLimitAbsY;
    private float bottomLimitAbsY;
    private Context mContext;
    private Paint mPaint; //分割线高度
    private TextPaint mTextPaint;

    private Paint mRedPaint; //分割线高度
    private TextPaint mRedTextPaint;
    private FlagPopuwindow popuwindow;

    private float mLastMotionX, mLastMotionY, scollY;
    //是否移动了
    private View dragView;


    private ViewDragHelper viewDragHelper;
    private ArrayList<View> views = new ArrayList<>();
    private boolean mIsUnableToDrag;
    private boolean isMove;
    private Rect dragViewOriginRect;


    public WeekCourseView(@NonNull Context context) {
        this(context, null);
    }

    public WeekCourseView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public WeekCourseView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        prepare();
        init();


    }
    @TargetApi(Build.VERSION_CODES.ECLAIR_MR1)
    private void prepare(){
        setChildrenDrawingOrderEnabled(true);
    }
    private Activity activity;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }


    public void onScollYPosition(int y) {
        this.scollY = y;
    }


    public void setItemParams(int width, int height, int size) {
        this.itemWidth = width;
        this.itemHeight = height;
        this.itemSize = size;
        requestLayout();
    }

    public void setLimit(float topLimitAbsY, float bottomLimitAbsY) {

        this.topLimitAbsY = topLimitAbsY;
        this.bottomLimitAbsY = bottomLimitAbsY;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(resolveSizeAndState(getSuggestedMinimumWidth(), widthMeasureSpec, 0), itemHeight * itemSize + getPaddingTop() + getPaddingBottom());
    }

    private void init() {
        setWillNotDraw(false);
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
        viewDragHelper = ViewDragHelper.create(this, 1.0f, new DragHelperCall());
    }

    private class DragHelperCall extends ViewDragHelper.Callback {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            boolean contains = views.contains(child);
            if (contains) {
                CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean courseBean = (CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean) child.getTag();

                if (courseBean!=null){
                    dragView = child;
                }else {
                    contains=false;
                }

            }
            return contains;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);

        }

        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
            if (state == STATE_DRAGGING) {
                if (dragView != null) {
                    int top = dragView.getTop();
                    int left = dragView.getLeft();
                    int i = (top - getPaddingTop()) / itemHeight;
                    int finalTop = itemHeight * i + getPaddingTop();
                    int m = (left - getPaddingLeft()) / itemWidth;
                    int finalLeft = itemWidth * m + getPaddingLeft();
                    int finalRigth = itemWidth + finalLeft;
                    int finalBottom = finalTop + dragView.getHeight();
                    dragViewOriginRect = new Rect(finalLeft, finalTop, finalRigth, finalBottom);

                }
            }
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {

            if (left > getWidth() - child.getMeasuredWidth()) {
                left = getWidth() - child.getMeasuredWidth();
            } else if (left < getPaddingLeft()) {
                left = getPaddingLeft();
            }
            return left;
        }

        //计算child垂直方向的位置，top表示y轴坐标（相对于ViewGroup），默认返回0（如果不复写该方法）。这里，你可以控制垂直方向可移动的范围。
        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {

            if (child.getTop() >= getPaddingTop() && child.getBottom() <= getBottom() - getPaddingBottom()) {
                top = top;
            } else if (child.getBottom() > getBottom() - getPaddingBottom()) {
                top = getBottom() - getPaddingBottom() - child.getMeasuredHeight();
            } else if (top < getPaddingTop()) {
                top = getPaddingTop();
            }
            return top;
        }


        @Override
        public void onViewReleased(View child, float xvel, float yvel) {
            int top = child.getTop();
            int left = child.getLeft();
            int i = (top - getPaddingTop()) / itemHeight;
            int m = (left - getPaddingLeft()) / itemWidth;


            child.setVisibility(INVISIBLE);
            CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean courseBean = (CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean) child.getTag();
            String startTime = "";
            String endTime = "";

            int duration = courseBean.getDuration();
            if (i == 0) {
                startTime += "00:00";
                if (duration / 60 < 10) {
                    endTime += "0" + duration / 60;
                } else {
                    endTime += duration / 60;
                }

                if (duration % 60 < 10) {
                    endTime += ":0" + (duration % 60);
                } else {
                    endTime += ":" + (duration % 60);
                }
            } else {
                int hour = i / 2;
                int minut = 0;
                if (i / 2 < 10) {
                    startTime += "0" + i / 2;
                } else {
                    startTime += i / 2;
                }
                if (i % 2 == 0) {
                    startTime += ":00";
                    minut = 0;
                } else {
                    startTime += ":30";
                    minut = 30;
                }
                int i1 = minut + duration;
                hour+=i1/60;
                if (hour < 10) {
                    endTime += "0" + hour;
                } else {
                    endTime +=hour;
                }
                if (i1%60 < 10) {
                    endTime += ":0" + (i1%60);
                } else {
                    endTime +=":"+ (i1%60);
                }
            }
            courseBean.setWeek(m);
            courseBean.setSTime(startTime);
            courseBean.setETime(endTime);
            if (onDragEndListener != null) {
                onDragEndListener.onDragEnd(courseBean);
            }
            dragView=null;
        }
    }

    @Override
    public void computeScroll() {
        if (viewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            viewDragHelper.cancel();
            return false;
        }
        if (!isEnabled() || (mIsUnableToDrag && action != MotionEvent.ACTION_DOWN)) {
            viewDragHelper.cancel();
            return super.onInterceptTouchEvent(ev);
        }

        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }


    boolean isUp;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = MotionEventCompat.getActionMasked(event);

        if (!isEnabled() || (mIsUnableToDrag && action != MotionEvent.ACTION_DOWN)) {
            viewDragHelper.cancel();
            return super.onTouchEvent(event);
        }
        int index = MotionEventCompat.getActionIndex(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                isUp = false;
                final float x = event.getX();
                final float y = event.getY();
                mLastMotionX = x;
                mLastMotionY = y;
                mIsUnableToDrag = false;

                break;
            }
            case MotionEvent.ACTION_MOVE: {
                final float x = event.getX();
                final float currentY = event.getY();
                final float ady = Math.abs(currentY - mLastMotionY);
                int slop = viewDragHelper.getTouchSlop();

                float offsetY = mLastMotionY - currentY;
                mLastMotionY = currentY;


                if (Math.abs(ady - slop) > 0) {
                    //TODO 1.在边界内 上下拖拽请求消费该事件，防止被ScrollView嵌套的手势冲突
                    //TODO 2.在边界上下滑动交给ScrollView


                    if (dragView != null) {
                        RectF rectF = CommonUtil.calcViewScreenLocation(dragView);
                        Logger.i(TAG, "rectF.top: " + rectF.top);
                        Logger.i(TAG, "topLimitAbsY: " + topLimitAbsY);
                        Logger.i(TAG, "bottomLimitAbsY: " + bottomLimitAbsY);
                        Logger.i(TAG, "rectF.bottom: " + rectF.bottom);
                        if (rectF.top > topLimitAbsY && rectF.bottom < bottomLimitAbsY) {
                            requestDisallowInterceptTouchEvent(true);
                        }

                    }
                }

                break;
            }
            case MotionEvent.ACTION_UP: {
                isUp = true;

                break;
            }
        }
        viewDragHelper.processTouchEvent(event);
        return true;
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
            canvas.drawLine(getPaddingLeft() + itemWidth * i, getPaddingTop(), getPaddingLeft() + itemWidth * i, itemHeight * itemSize + getPaddingTop(), mPaint);
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


    public void addItem(CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean courseBean, int weekCode) {
        String startTime = courseBean.getSTime();
        String endTime = courseBean.getETime();
        View view = LayoutInflater.from(mContext).inflate(R.layout.week_course_view, null, false);
        View ll_week_course = view.findViewById(R.id.ll_week_course);
        View iv_lock = view.findViewById(R.id.iv_lock);
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
        layoutParams.leftMargin = itemWidth * weekCode;
        view.setLayoutParams(layoutParams);
        if (courseBean.getDataType() == 1) {
            iv_lock.setVisibility(GONE);
            tv_member_name.setVisibility(VISIBLE);
            view.setTag(courseBean);

            views.add(view);
            CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCourseMemberVOBean privateCourseMemberVO = courseBean.getPrivateCourseMemberVO();
            String colour = courseBean.getColour();
            if (TextUtils.isEmpty(colour)) {
                colour = "#f3f3f3";
                tv_member_name.setTextColor(Color.parseColor("#333333"));
            } else if (colour.equals("#f3f3f3")) {
                tv_member_name.setTextColor(Color.parseColor("#333333"));
            } else {
                tv_member_name.setTextColor(Color.parseColor("#ffffff"));
            }
            if (privateCourseMemberVO!=null){
                tv_member_name.setText(privateCourseMemberVO.getMemberName());
            }
            ll_week_course.setBackgroundColor(Color.parseColor(colour));

        } else {
            tv_member_name.setVisibility(GONE);
            iv_lock.setVisibility(VISIBLE);
            views.add(view);
            ll_week_course.setBackgroundColor(Color.parseColor("#efefef"));
        }

    }

    public void clearView() {
        removeAllViewsInLayout();
        views.clear();
    }

    //让可以拖动的View显示在最上面
    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        if (dragView != null) {
            int mPosition = indexOfChild(dragView);
            if (i == childCount - 1) {
                return mPosition;
            }
            if (i == mPosition) {
                return childCount - 1;
            }
        }

        return super.getChildDrawingOrder(childCount, i);
    }

    private OnDragEndListener onDragEndListener;

    public void setOnDragEndListener(OnDragEndListener onDragEndListener) {
        this.onDragEndListener = onDragEndListener;
    }

    public interface OnDragEndListener {
        void onDragEnd(CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean courseBean);
    }


}
