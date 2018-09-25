package com.yijian.clubmodule.ui.course.appointcourse;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/21 17:57:10
 */
public class AppointCourseItemView extends LinearLayout{
    public AppointCourseItemView(Context context) {
        this(context,null);
    }

    public AppointCourseItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AppointCourseItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
