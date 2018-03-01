package com.yijian.staff.mvp.reception.step1;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.ReceptionActivity;
import com.yijian.staff.mvp.reception.step2.ReceptionStepTwoActivity;
import com.yijian.staff.util.system.StatusBarUtil;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;
import com.yijian.staff.widget.TimeBar;

public class ReceptionStepOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setLightStatusBar(this, 0xffffff);
        setContentView(R.layout.activity_reception_step_one);

        initView();

    }

    private void initView() {
        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.reception_step_one_navigation_bar);
        navigationBar.setTitle("填写问卷(1/5)", "#000000");
        navigationBar.setNavigationBarBackgroudColor(Color.parseColor("#ffffff"));
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_BLACK));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));
        TextView rightTextView = navigationBar.getmRightTextView();
        rightTextView.setText("下一步");
        rightTextView.setTextColor(Color.parseColor("#1997f8"));
        rightTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReceptionStepOneActivity.this, ReceptionStepTwoActivity.class);
                startActivity(intent);

            }
        });

        TimeBar timeBar=findViewById(R.id.step_one_timebar);
        timeBar.showTimeBar(1);
    }
}
