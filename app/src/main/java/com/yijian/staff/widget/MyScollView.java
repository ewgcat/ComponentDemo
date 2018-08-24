package com.yijian.staff.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.yijian.staff.widget.ScrollViewListener;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/9 18:00:39
 */
public class MyScollView extends ScrollView{
    private ScrollViewListener scrollViewListener = null;

    public MyScollView(Context context) {
        super(context);
    }

    public MyScollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyScollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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
