package com.yijian.staff.mvp.reception.step4;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step3.ReceptionStepThreeActivity;
import com.yijian.staff.mvp.reception.step5.ReceptionStepFiveActivity;
import com.yijian.staff.util.system.StatusBarUtil;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.NavigationBarItemFactory;
import com.yijian.staff.widget.TimeBar;

public class ReceptionStepFourActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setLightStatusBar(this, 0xffffff);
        setContentView(R.layout.activity_reception_step_four);
        initView();
    }
    private void initView() {

        NavigationBar2 navigationBar2 = findViewById(R.id.step_four_navigation_bar2);
        navigationBar2.findViewById(R.id.iv_first_left).setOnClickListener(this);
        navigationBar2.findViewById(R.id.iv_second_left).setOnClickListener(this);
        navigationBar2.findViewById(R.id.right_tv).setOnClickListener(this);
        navigationBar2.setTitle("订单详情(4/5)");
        navigationBar2.setmRightTvText("下一步");
        TimeBar timeBar = findViewById(R.id.step_four_timebar);
        timeBar.showTimeBar(4);

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.iv_first_left:
                finish();
                break;
            case R.id.iv_second_left:


                break;
            case R.id.right_tv:
                Intent intent = new Intent(ReceptionStepFourActivity.this, ReceptionStepFiveActivity.class);
                startActivity(intent);

                break;
        }
    }
}
