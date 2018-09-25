package com.yijian.commonlib.widget;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/4 11:44:06
 */
public class WeekLayout extends LinearLayout {

    public WeekLayout(Context context) {
        this(context,null);
    }

    public WeekLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WeekLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }



    public void setTimeItemWidthAndHeight( int width,int height){

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            childAt.setLayoutParams(new LayoutParams(width,height));

        }
        requestLayout();
    }




}
