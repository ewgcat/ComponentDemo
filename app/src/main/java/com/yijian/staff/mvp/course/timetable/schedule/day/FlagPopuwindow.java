package com.yijian.staff.mvp.course.timetable.schedule.day;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.yijian.staff.R;


/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/24 19:11:04
 */
public class FlagPopuwindow extends PopupWindow {
    private Context mContext;

    public FlagPopuwindow(Context context) {
        this(context, null);
    }

    public FlagPopuwindow(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlagPopuwindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.flag_view, null, false);
        setContentView(view);
        ImageView ivGreen = view.findViewById(R.id.iv_green);
        ImageView ivRed = view.findViewById(R.id.iv_red);
        ImageView ivBlue = view.findViewById(R.id.iv_blue);
        ImageView ivWhite = view.findViewById(R.id.iv_white);

    }

    public void show(View view) {
        this.showAsDropDown(view);
    }
}
