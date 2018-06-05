package com.yijian.staff.mvp.main.mine.calendartable;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;


import com.jeek.calendar.widget.calendar.CalendarUtils;
import com.jeek.calendar.widget.calendar.month.MonthCalendarView;
import com.yijian.staff.R;

import java.lang.ref.WeakReference;

/**
 * Created by cyandev on 2016/12/14.
 */
public class HeaderWeekBehavior extends CoordinatorLayout.Behavior<View> {
    private static final String TAG = "HeaderWeekBehavior";
    private WeakReference<View> dependentView;
    private int rowSize;//日历行高
    private int row;    //位于日历第几行 (0:为第一行)

    public HeaderWeekBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {


        if (dependency != null && dependency.getId() == R.id.ll_calendar) {
            dependentView = new WeakReference<>(dependency);

            return true;
        }
        return false;
    }


    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }


    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {


        float translationY = dependency.getTranslationY();//获取dependency的偏移量
//        Log.e(TAG, "onDependentViewChanged: ="+translationY );


        if (dependency instanceof LinearLayout) {
//            Log.e(TAG, "onDependentViewChanged: LinearLayout" );
            View view = ((LinearLayout) dependency).getChildAt(1);
            if (view instanceof MonthCalendarView) {
                int selectYear = ((MonthCalendarView) view).getCurrentMonthView().getSelectYear();
                int selectMonth = ((MonthCalendarView) view).getCurrentMonthView().getSelectMonth();
                int selectDay = ((MonthCalendarView) view).getCurrentMonthView().getSelectDay();
                //返回当前月份1号位于周几
                int weekNumber = CalendarUtils.getFirstDayWeek(selectYear, selectMonth);
//            Log.e(TAG, "onDependentViewChanged: weekNumber="+weekNumber+"   ----selectDay="+selectDay );
                row = (selectDay + weekNumber - 2) / 7;
//            Log.e(TAG, "onDependentViewChanged: row=="+row );
                rowSize = ((MonthCalendarView) view).getCurrentMonthView().getRowSize();
                if (-translationY > row * rowSize) {
                    child.setVisibility(View.VISIBLE);
                } else {
                    child.setVisibility(View.GONE);
                }
            }

        }


        return true;
    }


    private View getDependentView() {
        return dependentView.get();
    }

}
