package com.yijian.staff.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/1 10:57:48
 */
public class ClassTimeBar extends LinearLayout {

    String[] titles = new String[]{"邀约", "回访", "会商方案", "二次邀约", "会商结论"};

    private Context context;
    private ViewGroup timebarGroup;

    public ClassTimeBar(Context context) {
        this(context,null);
    }

    public ClassTimeBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ClassTimeBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        timebarGroup = findViewById(R.id.timebar);
        for (int i = 0; i < 5; i++) {
            LayoutInflater.from(context).inflate(R.layout.view_time, timebarGroup, true);
        }
    }

    public void showTimeBar(int position) {


        for (int i = 0; i < timebarGroup.getChildCount(); i++) {
            View view = timebarGroup.getChildAt(i);
            View lineLeft = view.findViewById(R.id.lineLeft);
            View lineRight = view.findViewById(R.id.lineRight);
            ImageView img = view.findViewById(R.id.img);
            TextView desc = view.findViewById(R.id.desc);

            desc.setText(titles[i]);

            if (i == 0) {
                lineLeft.setVisibility(View.INVISIBLE);
            } else if (i == 4) {
                lineRight.setVisibility(View.INVISIBLE);
            }

            if (position - i == 1) {
                lineLeft.setBackgroundColor(Color.parseColor("#41aecf"));
                lineRight.setBackgroundColor(Color.parseColor("#cccccc"));
                desc.setTextColor(Color.parseColor("#41aecf"));
                img.setImageResource(R.drawable.time_bar_dot_blue);
            } else if (position - i > 1) {
                lineLeft.setBackgroundColor(Color.parseColor("#41aecf"));
                lineRight.setBackgroundColor(Color.parseColor("#41aecf"));
                desc.setTextColor(Color.parseColor("#41aecf"));
                img.setImageResource(R.drawable.time_bar_dot_blue);
            } else {
                lineLeft.setBackgroundColor(Color.parseColor("#cccccc"));
                lineRight.setBackgroundColor(Color.parseColor("#cccccc"));
                desc.setTextColor(Color.parseColor("#d0000000"));
                img.setImageResource(R.drawable.time_bar_dot_gray);
            }
        }
    }
}
