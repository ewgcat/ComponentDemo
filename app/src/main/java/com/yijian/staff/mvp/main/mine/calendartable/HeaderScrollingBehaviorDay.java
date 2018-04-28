package com.yijian.staff.mvp.mine.calendartable;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;


import com.jeek.calendar.widget.calendar.CalendarUtils;
import com.jeek.calendar.widget.calendar.month.MonthCalendarView;
import com.yijian.staff.R;

import java.lang.ref.WeakReference;

/**
 * Created by cyandev on 2016/11/3.
 */
public class HeaderScrollingBehaviorDay extends CoordinatorLayout.Behavior<RecyclerView> {
    private static final String TAG = "HeaderScrollingBehavior";
    private boolean isExpanded = false;
    private boolean isScrolling = false;

    private WeakReference<View> dependentView;
    private Scroller scroller;
    private Handler handler;

    public HeaderScrollingBehaviorDay(Context context, AttributeSet attrs) {
        super(context, attrs);
//        Log.e(TAG, "HeaderScrollingBehavior: " );
        scroller = new Scroller(context);
        handler = new Handler();
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, RecyclerView child, View dependency) {
//        Log.e(TAG, "layoutDependsOn: " );
        if (dependency != null && dependency.getId() == R.id.ll_calendar) {
            dependentView = new WeakReference<>(dependency);
            return true;
        }
        return false;
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, RecyclerView child, int layoutDirection) {
//        Log.e(TAG, "onLayoutChild: " );
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        if (lp.height == CoordinatorLayout.LayoutParams.MATCH_PARENT) {
//            Log.e(TAG, "onLayoutChild: lp.height" );
            child.layout(0, 0, parent.getWidth(), (int) (parent.getHeight() - getDependentViewCollapsedHeight() - getWeekBarViewHeight()));
            return true;
        }
        return super.onLayoutChild(parent, child, layoutDirection);
    }


    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull RecyclerView child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
//        Log.e(TAG, "onNestedPreScroll: " );
        if (dy < 0) {//向下拉
            return;
        }


        View dependentView = getDependentView();

//        Log.e(TAG, "onNestedPreScroll: dependentView.getTranslationY()"+dependentView.getTranslationY());
        float newTranslateY = dependentView.getTranslationY() - dy;
//        Log.e(TAG, "onNestedPreScroll: newTranslateY=="+newTranslateY+"----dependentView.getTranslationY()=="
//        +dependentView.getTranslationY()+"----dy=="+dy);
        float minHeaderTranslate = -(dependentView.getHeight() - getDependentViewCollapsedHeight());

        if (newTranslateY > minHeaderTranslate) {
            dependentView.setTranslationY(newTranslateY);
//            Log.e(TAG, "onNestedPreScroll:dy== "+dy);
            consumed[1] = dy;

        }
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull RecyclerView child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
//        Log.e(TAG, "onStartNestedScroll: " );
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
//        return true;
    }


    @Override
    public void onNestedScrollAccepted(@NonNull CoordinatorLayout coordinatorLayout, @NonNull RecyclerView child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
//        Log.e(TAG, "onNestedScrollAccepted: " );
        scroller.abortAnimation();
        isScrolling = false;

        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, axes, type);
    }


    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull RecyclerView child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
//        Log.e(TAG, "onNestedScroll: dyUnconsumed=="+dyUnconsumed );
//        Log.e(TAG, "onNestedScroll: " );
        if (dyUnconsumed > 0) {
            return;
        }

        View dependentView = getDependentView();
        float newTranslateY = dependentView.getTranslationY() - dyUnconsumed;
        final float maxHeaderTranslate = 0;

        if (newTranslateY < maxHeaderTranslate) {
            dependentView.setTranslationY(newTranslateY);
        }
    }


    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, RecyclerView child, View target, float velocityX, float velocityY) {
//        Log.e(TAG, "onNestedPreFling: " );
        return onUserStopDragging(velocityY);
    }


    @Override
    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull RecyclerView child, @NonNull View target, int type) {
//        Log.e(TAG, "onStopNestedScroll: " );
        if (!isScrolling) {
            onUserStopDragging(800);
        }
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, RecyclerView child, View dependency) {

        child.setTranslationY(dependency.getHeight()  + dependency.getTranslationY() + getWeekBarViewHeight());
        return true;
    }

    private boolean onUserStopDragging(float velocity) {
//        Log.e(TAG, "onUserStopDragging: ");
        View dependentView = getDependentView();
        float translateY = dependentView.getTranslationY();
        float minHeaderTranslate = -(dependentView.getHeight() - getDependentViewCollapsedHeight());

        if (translateY == 0 || translateY == minHeaderTranslate) {
            return false;
        }

        boolean targetState; // Flag indicates whether to expand the content.
        if (Math.abs(velocity) <= 800) {
            if (Math.abs(translateY) < Math.abs(translateY - minHeaderTranslate)) {
                targetState = false;
            } else {
                targetState = true;
            }
            velocity = 800; // Limit velocity's minimum value.
        } else {
            if (velocity > 0) {
                targetState = true;
            } else {
                targetState = false;
            }
        }

        float targetTranslateY = targetState ? minHeaderTranslate : 0;

        scroller.startScroll(0, (int) translateY, 0, (int) (targetTranslateY - translateY), (int) (1000000 / Math.abs(velocity)));
        handler.post(flingRunnable);
        isScrolling = true;
        return true;
    }

    private float getDependentViewCollapsedHeight() {
        return getDependentView().getResources().getDimension(R.dimen.collapsed_header_height);
    }

    private float getWeekBarViewHeight() {
        return getDependentView().getResources().getDimension(R.dimen.week_bar_height);
    }

    private View getDependentView() {
        return dependentView.get();
    }

    private Runnable flingRunnable = new Runnable() {
        @Override
        public void run() {
            if (scroller.computeScrollOffset()) {
                getDependentView().setTranslationY(scroller.getCurrY());
                handler.post(this);
            } else {
                isExpanded = getDependentView().getTranslationY() != 0;
                isScrolling = false;
            }
        }
    };

}
