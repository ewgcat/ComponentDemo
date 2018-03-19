package com.yijian.staff.mvp.experienceclass.step1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yijian.staff.R;
import com.yijian.staff.widget.ClassTimeBar;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.TimeBar;

public class ExperienceClassProcess1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_class_process1);
        initView();
    }

    private void initView() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.step_one_navigation_bar2);

        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("体验课流程");

        ClassTimeBar timeBar = findViewById(R.id.step_one_timebar);
        timeBar.showTimeBar(1);
    }
}
