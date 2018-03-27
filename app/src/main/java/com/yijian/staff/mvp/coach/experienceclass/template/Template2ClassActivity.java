package com.yijian.staff.mvp.coach.experienceclass.template;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yijian.staff.R;
import com.yijian.staff.widget.NavigationBar2;

public class Template2ClassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template2_class);

        initView();

    }

    private void initView() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.class_template2_navigation_bar2);
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("模板二");
    }
}
