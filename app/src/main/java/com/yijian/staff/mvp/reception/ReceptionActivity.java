package com.yijian.staff.mvp.reception;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.util.Logger;
import com.yijian.staff.util.system.StatusBarUtil;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;

public class ReceptionActivity extends AppCompatActivity {

    NavigationBar navigationBar;
    String[] titles = new String[]{"查看问卷", "体侧录入", "产品报价", "订单详情", "成交"};

    //private List<ReceptionInfo> mReceptionInfoList=new ArrayList<>();

    //private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception);
        StatusBarUtil.setLightStatusBar(this, 0xffffff);

        navigationBar = (NavigationBar) findViewById(R.id.reception_activity_navigation_bar);
        navigationBar.setTitle("接待", "#000000");
        navigationBar.setNavigationBarBackgroudColor(Color.parseColor("#ffffff"));
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_BLACK));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));

        show(getPosition());
    }

    /**
     * @return 返回1-5中的一个数值,对应选择几个
     */
    //todo 通过接口数据获取位置，这里是假数据
    public int getPosition() {
        return 3;
    }

    public void show(int position) {
        if (position < 1 || position > 5) {
            Logger.e(ReceptionActivity.class.getName() + "---show(position)");
            return;
        }
        showTitleBar(position);
        showTimeBar(position);
        showContent(position);
    }

    private void showTitleBar(int position) {
        navigationBar.setTitle(titles[getPosition() - 1], "#000000");
    }

    private void showTimeBar(int position) {
        ViewGroup timebarGroup = findViewById(R.id.timebarGroup);
        for (int i = 0; i < 5; i++) {
            LayoutInflater.from(this).inflate(R.layout.view_time, timebarGroup, true);
        }

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

    //todo 根据position 加载不同的布局
    private void showContent(int position) {

    }

    public static void actionStart(Activity activity) {
        activity.startActivity(new Intent(activity, ReceptionActivity.class));
    }


//    private void initView() {
//        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.reception_activity_navigation_bar);
//        navigationBar.setTitle("接待", "#000000");
//        navigationBar.setNavigationBarBackgroudColor(Color.parseColor("#ffffff"));
//        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_BLACK));
//        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));
//
//        recyclerView = findViewById(R.id.recyclerview_jiedai_history);
//
//
//
//        JSONObject jsonObject=new JSONObject();
//        try {
//            jsonObject.put("name","朱沙");
//            jsonObject.put("phone","12345678900");
//            jsonObject.put("sex","男");
//            jsonObject.put("status","完成");
//            jsonObject.put("product","十周年纪念卡");
//            for (int i = 0; i < 10; i++) {
//                ReceptionInfo receptionInfo = new ReceptionInfo(jsonObject);
//                mReceptionInfoList.add(receptionInfo);
//            }
//            Logger.i("TEST","mReceptionInfoList: "+mReceptionInfoList.size());
//
//
//            LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
//            //设置RecyclerView 布局
//            recyclerView.setLayoutManager(layoutmanager);
//            ReceptionHistoryAdapter receptionHistoryAdapter = new ReceptionHistoryAdapter(this, mReceptionInfoList);
//            recyclerView.setAdapter(receptionHistoryAdapter);
//        } catch (JSONException e) {
//            Logger.i("TEST","JSONException: "+e);
//
//        }
//
//
//    }
//
//
//    @Override
//    public void onClick(View v) {
//
//    }

}