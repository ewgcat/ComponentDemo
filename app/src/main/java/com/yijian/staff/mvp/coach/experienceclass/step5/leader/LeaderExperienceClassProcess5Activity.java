package com.yijian.staff.mvp.coach.experienceclass.step5.leader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.staff.R;
import com.yijian.staff.widget.ClassTimeBar;
import com.yijian.staff.widget.NavigationBar2;

@Route(path = "/test/leader_experience_class")
public class LeaderExperienceClassProcess5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_experience_class_process5);
        initView();
    }

    private void initView() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.step_five_navigation_bar2);

        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);

        navigationBar2.setTitle("体验课流程");

        navigationBar2.setmRightTvText("确定");

        ClassTimeBar timeBar = findViewById(R.id.step_five_timebar);
        timeBar.showTimeBar(5);
    }
}
