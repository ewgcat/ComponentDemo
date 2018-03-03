package com.yijian.staff.mvp.reception.step2;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step3.ReceptionStepThreeActivity;
import com.yijian.staff.util.system.StatusBarUtils;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.ScanBodyView;
import com.yijian.staff.widget.TimeBar;

public class ReceptionStepTwoActivity extends AppCompatActivity implements View.OnClickListener {

    private ScanBodyView scanBodyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setLightStatusBar(this, 0xffffff);
        setContentView(R.layout.activity_reception_step_two);
        initView();
    }

    private void initView() {
        NavigationBar2 navigationBar2 = findViewById(R.id.step_two_navigation_bar2);
        navigationBar2.findViewById(R.id.iv_first_left).setOnClickListener(this);
        navigationBar2.findViewById(R.id.iv_second_left).setOnClickListener(this);
        navigationBar2.findViewById(R.id.right_tv).setOnClickListener(this);
        navigationBar2.setTitle("体测录入(2/5)");
        navigationBar2.setmRightTvText("下一步");
        TimeBar timeBar = findViewById(R.id.step_two_timebar);
        timeBar.showTimeBar(2);

        scanBodyView = findViewById(R.id.scan_view);




    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.iv_first_left:
                scanBodyView.stopScan();
                finish();
                break;
            case R.id.iv_second_left:
                scanBodyView. startScan();

                break;
            case R.id.right_tv:
                Intent intent = new Intent(ReceptionStepTwoActivity.this, ReceptionStepThreeActivity.class);
                startActivity(intent);
                scanBodyView.stopScan();
                break;
        }
    }
}
