package com.yijian.staff.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/29 20:10:14
 */
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
