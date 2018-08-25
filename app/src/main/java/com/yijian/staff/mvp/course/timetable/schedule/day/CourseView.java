package com.yijian.staff.mvp.course.timetable.schedule.day;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yijian.staff.BuildConfig;
import com.yijian.staff.R;
import com.yijian.staff.bean.CourseStudentBean;
import com.yijian.staff.mvp.course.appointcourse.AppointCourseBean;
import com.yijian.staff.mvp.course.punch.CoursePunchActivity;
import com.yijian.staff.util.DateUtil;
import com.yijian.staff.util.ImageLoader;
import com.yijian.staff.util.Logger;

import java.util.Date;

import static com.yijian.staff.mvp.course.timetable.schedule.day.FlagPopuwindow.BLUE_FLAG;
import static com.yijian.staff.mvp.course.timetable.schedule.day.FlagPopuwindow.GREEN_FLAG;
import static com.yijian.staff.mvp.course.timetable.schedule.day.FlagPopuwindow.RED_FLAG;
import static com.yijian.staff.mvp.course.timetable.schedule.day.FlagPopuwindow.WHITE_FLAG;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/21 15:15:00
 */
public class CourseView extends FrameLayout implements View.OnLongClickListener {
    private static String TAG = CourseView.class.getSimpleName();

    private int itemHeight = 200;
    private int itemSize = 24;
    private Context mContext;
    private Paint mPaint; //分割线高度
    private TextPaint mTextPaint;

    private Paint mRedPaint; //分割线高度
    private TextPaint mRedTextPaint;
    private FlagPopuwindow popuwindow;

    private int mLastMotionX, mLastMotionY;
    //是否移动了
    private boolean isMoved;
    //是否释放了
    private boolean isReleased;
    //长按的runnable
    private Runnable mLongPressRunnable;
    //移动的阈值
    private static final int TOUCH_SLOP = 20;
    private int maxHeight;
    private LockTimePopuwindow lockTimePopuwindow;


    public boolean dispatchTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastMotionX = x;
                mLastMotionY = y;

                isReleased = false;
                isMoved = false;
                postDelayed(mLongPressRunnable, ViewConfiguration.getLongPressTimeout());
                break;
            case MotionEvent.ACTION_MOVE:
                if (isMoved) break;
                if (Math.abs(mLastMotionX - x) > TOUCH_SLOP
                        || Math.abs(mLastMotionY - y) > TOUCH_SLOP) {
                    //移动超过阈值，则表示移动了
                    isMoved = true;
                    removeCallbacks(mLongPressRunnable);
                }
                break;
            case MotionEvent.ACTION_UP:
                //释放了
                isReleased = true;
                removeCallbacks(mLongPressRunnable);
                break;
        }
        return true;
    }


    public CourseView(@NonNull Context context) {
        this(context, null);
    }

    public CourseView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CourseView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mLongPressRunnable = new Runnable() {

            @Override
            public void run() {
                if (isReleased || isMoved) return;
                performLongClick();
            }
        };
        init(context);


    }

    private Activity activity;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean onLongClick(View v) {
        maxHeight = itemHeight * itemSize;
        int i = 1440 * mLastMotionY / maxHeight;
        int hour = i / 60;
        int minute = i % 60;
        lockTimePopuwindow = new LockTimePopuwindow(mContext);
        lockTimePopuwindow.setStartTime(hour, minute);
        lockTimePopuwindow.setBackgroundAlpha(activity,0.3f);
        lockTimePopuwindow.setAnimationStyle(R.style.locktime_popwin_anim_style);
        lockTimePopuwindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        lockTimePopuwindow.setOutsideTouchable(true);
        lockTimePopuwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                lockTimePopuwindow.setBackgroundAlpha(activity,1f);
            }
        });
        lockTimePopuwindow.showAtLocation(this, Gravity.CENTER, 0, 0);
        return false;
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
        setOnLongClickListener(this);
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


    public void addItem(CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean courseBean) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.course_view, null, false);
        addView(view);

        CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCoachCourseVOBean privateCoachCourseVO = courseBean.getPrivateCoachCourseVO();
        String memberCourseName = privateCoachCourseVO.getMemberCourseName();
        CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCourseMemberVOBean privateCourseMemberVO = courseBean.getPrivateCourseMemberVO();
        ImageView iv_header = view.findViewById(R.id.iv_header);
        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_course_name = view.findViewById(R.id.tv_course_name);
        ImageView iv_flag = view.findViewById(R.id.iv_flag);
        String colour = courseBean.getColour();
        if (TextUtils.isEmpty(colour)) {
            colour = "#ffffff";
        }
        switch (colour) {
            case "#5cd6b5":
                ImageLoader.setImageResource(R.mipmap.sign_green, view.getContext(), iv_flag);
                popuwindow.setFlagColor(GREEN_FLAG);
                break;
            case "#FF4081":
                ImageLoader.setImageResource(R.mipmap.sign_red, view.getContext(), iv_flag);
                popuwindow.setFlagColor(RED_FLAG);
                break;
            case "#1997f8":
                ImageLoader.setImageResource(R.mipmap.sign_blue, view.getContext(), iv_flag);
                popuwindow.setFlagColor(BLUE_FLAG);
                break;
            case "#ffffff":
                ImageLoader.setImageResource(R.mipmap.sign_white, view.getContext(), iv_flag);
                popuwindow.setFlagColor(WHITE_FLAG);
                break;
        }
        ImageLoader.setHeadImageResource(BuildConfig.FILE_HOST + privateCourseMemberVO.getHeadPath(), view.getContext(), iv_header);
        tv_name.setText(privateCourseMemberVO.getMemberName());
        tv_course_name.setText(memberCourseName);
        view.setBackgroundColor(Color.parseColor("#f5f5f5"));
        popuwindow.setOnSelectFlagListener(new FlagPopuwindow.OnSelectFlagListener() {
            @Override
            public void OnSelectFlag(int position) {
                String color = "";
                switch (position) {
                    case GREEN_FLAG:
                        ImageLoader.setImageResource(R.mipmap.sign_green, view.getContext(), iv_flag);
                        color = "#5cd6b5";
                        break;
                    case RED_FLAG:
                        ImageLoader.setImageResource(R.mipmap.sign_red, view.getContext(), iv_flag);
                        color = "#FF4081";
                        break;
                    case BLUE_FLAG:
                        ImageLoader.setImageResource(R.mipmap.sign_blue, view.getContext(), iv_flag);
                        color = "#1997f8";
                        break;
                    case WHITE_FLAG:
                        ImageLoader.setImageResource(R.mipmap.sign_white, view.getContext(), iv_flag);
                        color = "#ffffff";
                        break;
                }
                courseBean.setColour(color);
                if (onSelectFlagListener != null) {
                    onSelectFlagListener.OnSelectFlag(courseBean, color);
                }
            }
        });
        iv_flag.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean showing = popuwindow.isShowing();
                if (showing) {
                    popuwindow.dismiss();
                } else {
                    String colour = courseBean.getColour();
                    if (TextUtils.isEmpty(colour)) {
                        colour = "#ffffff";
                    }
                    switch (colour) {
                        case "#5cd6b5":
                            popuwindow.setFlagColor(GREEN_FLAG);
                            break;
                        case "#FF4081":
                            popuwindow.setFlagColor(RED_FLAG);
                            break;
                        case "#1997f8":
                            popuwindow.setFlagColor(BLUE_FLAG);
                            break;
                        case "#ffffff":
                            popuwindow.setFlagColor(WHITE_FLAG);
                            break;
                    }
                    popuwindow.showAsDropDown(v);
                }
            }
        });

        String startTime = courseBean.getSTime();
        String endTime = courseBean.getETime();
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

    public void clearView() {
        removeAllViewsInLayout();
    }

    public void dismiss() {
        boolean showing = popuwindow.isShowing();
        if (showing) {
            popuwindow.dismiss();
        }
    }

    private OnSelectFlagListener onSelectFlagListener;


    public interface OnSelectFlagListener {
        void OnSelectFlag(CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean courseBean, String color);
    }

    public void setOnSelectFlagListener(OnSelectFlagListener onSelectFlagListener) {
        this.onSelectFlagListener = onSelectFlagListener;
    }
}
