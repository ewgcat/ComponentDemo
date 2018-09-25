package com.yijian.clubmodule.ui.course.schedule.week;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import androidx.core.view.ViewCompat;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yijian.clubmodule.R;
import com.yijian.clubmodule.bean.CourseStudentBean;
import com.yijian.commonlib.util.CommonUtil;
import com.yijian.commonlib.util.DateUtil;
import com.yijian.commonlib.util.Logger;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/9/10 13:58:06
 */
public class WeekCourseView extends FrameLayout {


    private View mDragView;  //被拖动的子元素

    private static final long DRAGDURATION = 300;   //长按的时间
    private static String TAG = WeekCourseView.class.getSimpleName();

    private int itemHeight = 200;
    private int itemWidth = 100;
    private int itemSize = 48;
    private Context mContext;
    private Paint mPaint; //分割线高度
    private TextPaint mTextPaint;
    private float topLimitAbsY;
    private float bottomLimitAbsY;
    private Paint mRedPaint; //分割线高度
    private TextPaint mRedTextPaint;
    private float scollY, mLastMotionY;
    private boolean isMove;
    private int maxHeight;


    public WeekCourseView(Context context) {
        this(context, null);
    }

    public WeekCourseView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeekCourseView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        prepare();
        init();
        prepare();
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR_MR1)
    private void prepare() {
        setChildrenDrawingOrderEnabled(true);
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
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
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
            if (privateCourseMemberVO != null) {
                tv_member_name.setText(privateCourseMemberVO.getMemberName());
            }
            if (CommonUtil.isColor(colour)) {
                int color = Color.parseColor(colour);
                ll_week_course.setBackgroundColor(color);
            } else {
                tv_member_name.setTextColor(Color.parseColor("#333333"));
                ll_week_course.setBackgroundColor(Color.parseColor("#f3f3f3"));
            }

        } else {
            tv_member_name.setVisibility(GONE);
            iv_lock.setVisibility(VISIBLE);
            ll_week_course.setBackgroundColor(Color.parseColor("#efefef"));
        }
        requestLayout();

    }

    public void clearView() {
        removeAllViews();

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(resolveSizeAndState(getSuggestedMinimumWidth(), widthMeasureSpec, 0), itemHeight * itemSize + getPaddingTop() + getPaddingBottom());
        maxHeight = itemHeight * itemSize + getPaddingTop() + getPaddingBottom();
    }

    public void setLimit(float topLimitAbsY, float bottomLimitAbsY) {

        this.topLimitAbsY = topLimitAbsY;
        this.bottomLimitAbsY = bottomLimitAbsY;
    }


    //让可以拖动的View显示在最上面
    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        if (mDragView != null) {
            int mPosition = indexOfChild(mDragView);
            if (i == childCount - 1) {
                return mPosition;
            }
            if (i == mPosition) {
                return childCount - 1;
            }
        }

        return super.getChildDrawingOrder(childCount, i);
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }


    private float downX;
    private float downY;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mDragView == null) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    final float x = event.getX();
                    final float y = event.getY();
                    downX = event.getX();
                    downY = event.getY();
                    isMove = false;
                    postDelayed(mDragRun, DRAGDURATION);
                    break;
                case MotionEvent.ACTION_MOVE:
                    isMove = true;
                    removeCallbacks(mDragRun);
                    float currentY = event.getY();
                    float offsetY = downY - currentY;
                    downY = currentY;
                    if (getScrollY() + offsetY < 0) {    //控制上边界
                        offsetY = 0 - getScrollY();
                    } else if (getScrollY() + offsetY > maxHeight) {    //控制下边界
                        offsetY = maxHeight - getScrollY();
                    }

                    scrollBy(0, (int) offsetY);
                    break;
                case MotionEvent.ACTION_UP:
                    removeCallbacks(mDragRun);
                    break;
            }

            return super.onTouchEvent(event);
        } else {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    final float x = event.getX();
                    final float y = event.getY();
                    mLastMotionY = y;
                    downX = event.getX();
                    downY = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float currentX = event.getX();
                    float currentY = event.getY();
                    float offsetX = currentX - downX;
                    float offsetY = currentY - downY;
                    downX = currentX;
                    downY = currentY;


                    final float ady = Math.abs(currentY - mLastMotionY);

                    mLastMotionY = currentY;
                    int TouchSlop = ViewConfiguration.get(mContext).getScaledTouchSlop();
                    if (Math.abs(ady - TouchSlop) > 0) {
                        //TODO 1.在边界内 上下拖拽请求消费该事件，防止被ScrollView嵌套的手势冲突
                        //TODO 2.在边界上下滑动交给ScrollView

                        if (mDragView != null) {
                            RectF rectF = CommonUtil.calcViewScreenLocation(mDragView);
                            Logger.i(TAG, "rectF.top: " + rectF.top);
                            Logger.i(TAG, "topLimitAbsY: " + topLimitAbsY);
                            Logger.i(TAG, "bottomLimitAbsY: " + bottomLimitAbsY);
                            Logger.i(TAG, "rectF.bottom: " + rectF.bottom);
                            if (rectF.top > topLimitAbsY && rectF.bottom < bottomLimitAbsY) {
                                requestDisallowInterceptTouchEvent(true);
                            } else {
                                requestDisallowInterceptTouchEvent(false);
                            }
                        }
                    }
                    ViewCompat.offsetLeftAndRight(mDragView, (int) offsetX);
                    ViewCompat.offsetTopAndBottom(mDragView, (int) offsetY);
                    removeCallbacks(mDragScrollRun);
                    post(mDragScrollRun);
                    break;
                case MotionEvent.ACTION_UP:
                    removeCallbacks(mDragScrollRun);
                    View changeView = findPositionView((mDragView.getLeft() + mDragView.getRight()) / 2, (mDragView.getTop() + mDragView.getBottom()) / 2);
                    if (changeView != null) {
                        requestLayout();
                    } else {
                        int top = mDragView.getTop();
                        int left = mDragView.getLeft();
                        int i = (top - getPaddingTop()) / itemHeight;
                        int m = (left - getPaddingLeft()) / itemWidth;


                        mDragView.setVisibility(INVISIBLE);
                        CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean courseBean = (CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean) mDragView.getTag();
                        if (courseBean != null) {


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
                                hour += i1 / 60;
                                if (hour < 10) {
                                    endTime += "0" + hour;
                                } else {
                                    endTime += hour;
                                }
                                if (i1 % 60 < 10) {
                                    endTime += ":0" + (i1 % 60);
                                } else {
                                    endTime += ":" + (i1 % 60);
                                }
                            }
                            courseBean.setWeek(m);
                            courseBean.setSTime(startTime);
                            courseBean.setETime(endTime);
                            if (onDragEndListener != null) {
                                onDragEndListener.onDragEnd(courseBean);
                            }
                        }
                    }

                    playCancelAnimation(mDragView);

                    mDragView = null;
                    break;
            }
            return true;
        }
    }


    private View findPositionView(int x, int y) {
        int childCount = getChildCount();
        for (int i = childCount - 1; i >= 0; --i) {
            View child = getChildAt(i);
            if (x >= child.getLeft() && x < child.getRight() && y >= child.getTop() && y < child.getBottom()) {
                if (child != mDragView) {
                    return child;
                }
            }
        }
        return null;
    }

    private Runnable mDragRun = new Runnable() {
        @Override
        public void run() {
            mDragView = findPositionView((int) downX + getScrollX(), (int) downY + getScrollY());

            if (mDragView != null) {
                CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean courseBean = (CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean) mDragView.getTag();

                if (courseBean != null) {
                    playStartAnimation(mDragView);
                    invalidate();
                } else {
                    mDragView = null;
                }

            }
        }
    };

    private int vDragScroll = 5;
    private Runnable mDragScrollRun = new Runnable() {
        @Override
        public void run() {

            if (mDragView != null) {
                CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean courseBean = (CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean) mDragView.getTag();
                if (courseBean != null) {
                    if (getHeight() + getScrollY() < mDragView.getBottom() && getScrollY() < maxHeight) {
                        scrollBy(0, vDragScroll);
                        ViewCompat.offsetTopAndBottom(mDragView, vDragScroll);
                        post(mDragScrollRun);
                    } else if (mDragView.getTop() < getScrollY() && getScrollY() > 0) {
                        scrollBy(0, -vDragScroll);
                        ViewCompat.offsetTopAndBottom(mDragView, -vDragScroll);
                        post(mDragScrollRun);
                    }
                } else {
                    mDragView = null;
                }


            }
        }
    };

    public interface AnimationCallBack {
        long getDuration();

        Animator getStartAnimator(View view);

        Animator getCancelAnimator(View view);
    }

    //定义自己想要的动画效果
    public void setAnimationCallBack(AnimationCallBack callBack) {
        this.mAnimationCallBack = callBack;
    }

    private AnimationCallBack mAnimationCallBack = new AnimationCallBack() {
        @Override
        public long getDuration() {
            return 200;
        }

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        public Animator getStartAnimator(View view) {
            AnimatorSet set = new AnimatorSet();
            set.playTogether(ObjectAnimator.ofFloat(view, "rotationX", 0, 0),
                    ObjectAnimator.ofFloat(view, "rotationY", 0, 0),
                    ObjectAnimator.ofFloat(view, "translationX", 0, 0),
                    ObjectAnimator.ofFloat(view, "translationY", 0, 0),
                    ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 1.2f),
                    ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 1.2f),
                    ObjectAnimator.ofFloat(view, "alpha", 1, 0.7f));
            return set;
        }

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        public Animator getCancelAnimator(View view) {
            AnimatorSet set = new AnimatorSet();
            set.playTogether(ObjectAnimator.ofFloat(view, "rotationX", 0, 0),
                    ObjectAnimator.ofFloat(view, "rotationY", 0, 0),
                    ObjectAnimator.ofFloat(view, "translationX", 0, 0),
                    ObjectAnimator.ofFloat(view, "translationY", 0, 0),
                    ObjectAnimator.ofFloat(view, "scaleX", 1.2f, 1.0f),
                    ObjectAnimator.ofFloat(view, "scaleY", 1.2f, 1.0f),
                    ObjectAnimator.ofFloat(view, "alpha", 0.7f, 1));
            return set;
        }
    };

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void playStartAnimation(View view) {
        if (mAnimationCallBack != null) {
            Animator animator = mAnimationCallBack.getStartAnimator(view);
            if (animator != null) {
                animator.setDuration(mAnimationCallBack.getDuration()).start();
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void playCancelAnimation(View view) {
        if (mAnimationCallBack != null) {
            Animator animator = mAnimationCallBack.getCancelAnimator(view);
            if (animator != null) {
                animator.setDuration(mAnimationCallBack.getDuration()).start();
            }
        }
    }

    public interface ChildPositionChangeListener {
        //第pos1位置的view1和第pos2位置的view2互换了位置
        void onChildPositionChange(int pos1, View view1, int pos2, View view2);
    }

    public interface ItemClickListener {
        void onItemClickListener(int pos, View view);
    }

    private ChildPositionChangeListener mChildPositionChangeListener;
    private ItemClickListener mItemClickListener;

    public void setChildPositionChangeListener(ChildPositionChangeListener listener) {
        this.mChildPositionChangeListener = listener;
    }

    public void setItemClickListener(ItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    private OnDragEndListener onDragEndListener;

    public void setOnDragEndListener(OnDragEndListener onDragEndListener) {
        this.onDragEndListener = onDragEndListener;
    }

    public interface OnDragEndListener {
        void onDragEnd(CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean courseBean);
    }


}

