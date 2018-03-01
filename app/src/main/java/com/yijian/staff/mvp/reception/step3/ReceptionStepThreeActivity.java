package com.yijian.staff.mvp.reception.step3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step4.ReceptionStepFourActivity;
import com.yijian.staff.util.system.StatusBarUtils;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.TimeBar;

public class ReceptionStepThreeActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setLightStatusBar(this, 0xffffff);

        setContentView(R.layout.activity_reception_step_three);
        initView();
    }

    private void initView() {

        NavigationBar2 navigationBar2 = findViewById(R.id.step_three_navigation_bar2);
        navigationBar2.findViewById(R.id.iv_first_left).setOnClickListener(this);
        navigationBar2.findViewById(R.id.iv_second_left).setOnClickListener(this);
        navigationBar2.findViewById(R.id.right_tv).setOnClickListener(this);
        navigationBar2.setTitle("产品报价(3/5)");
        navigationBar2.setmRightTvText("下一步");
        TimeBar timeBar = findViewById(R.id.step_three_timebar);
        timeBar.showTimeBar(3);


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
                Intent intent = new Intent(ReceptionStepThreeActivity.this, ReceptionStepFourActivity.class);
                startActivity(intent);

                break;
        }
    }
}
