package com.yijian.staff.mvp.reception.step2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.ReceptionActivity;
import com.yijian.staff.mvp.reception.step3.ReceptionStepThreeActivity;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.ScanBodyView;
import com.yijian.staff.widget.TimeBar;

public class KeFuReceptionStepTwoActivity extends AppCompatActivity implements View.OnClickListener {

    private ScanBodyView scanBodyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reception_step_two);
        initView();
    }

    private void initView() {
        NavigationBar2 navigationBar2 = findViewById(R.id.step_two_navigation_bar2);

        navigationBar2.getFirstLeftIv().setOnClickListener(this);
        navigationBar2.getSecondLeftIv().setOnClickListener(this);
        navigationBar2.getmRightTv().setOnClickListener(this);
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
                finish();
                break;
            case R.id.iv_second_left:


                Intent i = new Intent(this,ReceptionActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;
            case R.id.right_tv:
                Intent intent = new Intent(KeFuReceptionStepTwoActivity.this, ReceptionStepThreeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }
}
