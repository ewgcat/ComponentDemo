package com.yijian.staff.mvp.course.timetable.helper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class NoScrollRecycleView extends RecyclerView {
    public NoScrollRecycleView(Context context) {
        super(context);
    }

    public NoScrollRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setNestedScrollingEnabled(false);
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}