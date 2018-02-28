package com.yijian.staff.mvp.reception;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.util.system.StatusBarUtil;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class ReceptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception);
        StatusBarUtil.setLightStatusBar(this, 0xffffff);

        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.reception_activity_navigation_bar);
        navigationBar.setTitle("接待", "#000000");
        navigationBar.setNavigationBarBackgroudColor(Color.parseColor("#ffffff"));
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_BLACK));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));

        showStatu(4);
    }

    /**
     * @return 返回1-5中的一个数值,对应选择几个
     */
    //todo 通过接口数据获取位置，这里是假数据
    public int getPosition() {
        return 1;
    }

    public void showStatu(int position) {
        ViewGroup timebarGroup = findViewById(R.id.timebarGroup);
        for (int i = 0; i < 5; i++) {
            LayoutInflater.from(this).inflate(R.layout.view_time, timebarGroup, true);
        }

        List<String> descs = new ArrayList<>();
        descs.add("查看问卷");
        descs.add("体侧录入");
        descs.add("产品报价");
        descs.add("订单详情");
        descs.add("成交");

        for (int i = 0; i < timebarGroup.getChildCount(); i++) {
            View view = timebarGroup.getChildAt(i);
            View lineLeft = view.findViewById(R.id.lineLeft);
            View lineRight = view.findViewById(R.id.lineRight);
            ImageView img = view.findViewById(R.id.img);
            TextView desc = view.findViewById(R.id.desc);

            desc.setText(descs.get(i));

            if (i == 0) {
                lineLeft.setVisibility(View.INVISIBLE);
            } else if (i == 4) {
                lineRight.setVisibility(View.INVISIBLE);
            }

            if (position - i == 1) {
                lineLeft.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                lineRight.setBackgroundColor(getResources().getColor(R.color.gray));
                desc.setTextColor(getResources().getColor(R.color.colorPrimary));
                img.setImageResource(R.drawable.time_bar_dot_blue);
            } else if (position - i > 1) {
                lineLeft.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                lineRight.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                desc.setTextColor(getResources().getColor(R.color.colorPrimary));
                img.setImageResource(R.drawable.time_bar_dot_blue);
            } else {
                lineLeft.setBackgroundColor(getResources().getColor(R.color.gray));
                lineRight.setBackgroundColor(getResources().getColor(R.color.gray));
                desc.setTextColor(getResources().getColor(R.color.black_bg));
                img.setImageResource(R.drawable.time_bar_dot_gray);
            }

        }
    }

//    public void showStatu(int position) {
//        List<View> timebars = new ArrayList<>();
//        timebars.add(findViewById(R.id.timebar1));
//        timebars.add(findViewById(R.id.timebar2));
//        timebars.add(findViewById(R.id.timebar3));
//        timebars.add(findViewById(R.id.timebar4));
//        timebars.add(findViewById(R.id.timebar5));
//
//        List<String> descs = new ArrayList<>();
//        descs.add("查看问卷");
//        descs.add("体侧录入");
//        descs.add("产品报价");
//        descs.add("订单详情");
//        descs.add("成交");
//
//        for (int i = 0; i < timebars.size(); i++) {
//            View view = timebars.get(i);
//            View lineLeft = view.findViewById(R.id.lineLeft);
//            View lineRight = view.findViewById(R.id.lineRight);
//            ImageView img = view.findViewById(R.id.img);
//            TextView desc = view.findViewById(R.id.desc);
//
//            desc.setText(descs.get(i));
//
//            if (i == 0) {
//                lineLeft.setVisibility(View.INVISIBLE);
//            } else if (i == 4) {
//                lineRight.setVisibility(View.INVISIBLE);
//            }
//
//            if (position - i == 1) {
//                lineLeft.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                lineRight.setBackgroundColor(getResources().getColor(R.color.gray));
//                desc.setTextColor(getResources().getColor(R.color.colorPrimary));
//                img.setImageResource(R.drawable.time_bar_dot_blue);
//            } else if (position - i > 1) {
//                lineLeft.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                lineRight.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                desc.setTextColor(getResources().getColor(R.color.colorPrimary));
//                img.setImageResource(R.drawable.time_bar_dot_blue);
//            } else {
//                lineLeft.setBackgroundColor(getResources().getColor(R.color.gray));
//                lineRight.setBackgroundColor(getResources().getColor(R.color.gray));
//                desc.setTextColor(getResources().getColor(R.color.black_bg));
//                img.setImageResource(R.drawable.time_bar_dot_gray);
//            }
//
//        }
//    }

    public static void actionStart(Activity activity) {
        activity.startActivity(new Intent(activity, ReceptionActivity.class));
    }

}