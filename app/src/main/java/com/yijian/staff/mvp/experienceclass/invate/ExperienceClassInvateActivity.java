package com.yijian.staff.mvp.experienceclass.invate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yijian.staff.R;
import com.yijian.staff.widget.NavigationBar2;

public class ExperienceClassInvateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_class_invate);

        initView();
    }

    private void initView() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.class_invate_navigation_bar2);

        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("体验课邀请");
        navigationBar2.setmRightTvText("完成");

    }
}
