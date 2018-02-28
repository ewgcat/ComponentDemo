package com.yijian.staff.mvp.reception.step3;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step2.ReceptionStepTwoActivity;
import com.yijian.staff.mvp.reception.step4.ReceptionStepFourActivity;
import com.yijian.staff.util.system.StatusBarUtil;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;

public class ReceptionStepThreeActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setLightStatusBar(this, 0xffffff);

        setContentView(R.layout.activity_reception_step_three);
        initView();
    }

    private void initView() {

        findViewById(R.id.iv_first_left).setOnClickListener(this);
        findViewById(R.id.iv_second_left).setOnClickListener(this);
        findViewById(R.id.right_tv).setOnClickListener(this);


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
