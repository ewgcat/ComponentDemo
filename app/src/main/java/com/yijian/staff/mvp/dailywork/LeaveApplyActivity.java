package com.yijian.staff.mvp.dailywork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yijian.staff.R;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.NavigationBarItemFactory;

/**
 * 请假申请
 */
public class LeaveApplyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_apply);
        initTitle();
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = findViewById(R.id.vip_over_navigation_bar2);
        navigationBar2.setTitle("请假申请");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
    }
}