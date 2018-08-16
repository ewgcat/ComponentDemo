package com.yijian.staff.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;


import com.yijian.staff.R;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/4 11:44:06  com.yijian.staff.mvp.course.timetable.schedule.week
 */
public class TimeLayout  extends ScrollView {
    private ScrollViewListener scrollViewListener = null;
    private Context context;
    private LinearLayout llContent;

    public TimeLayout(Context context) {
        this(context,null);
    }

    public TimeLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TimeLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;

    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        llContent = this.findViewById(R.id.ll_content);
    }



    public void setTimeItemWidthAndHeight( int width,int height){

        int childCount = llContent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = llContent.getChildAt(i);
            childAt.setLayoutParams(new LinearLayout.LayoutParams(width,height));
        }
        requestLayout();
    }

    public void setOnScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }


}
