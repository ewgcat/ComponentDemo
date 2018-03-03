package com.yijian.staff.mvp.reception.step2;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step3.ReceptionStepThreeActivity;
import com.yijian.staff.util.system.StatusBarUtils;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.ScanBodyView;
import com.yijian.staff.widget.TimeBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReceptionStepTwoActivity extends AppCompatActivity implements View.OnClickListener {

    private static final java.lang.String TAG = ReceptionStepTwoActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setLightStatusBar(this, 0xffffff);
        setContentView(R.layout.activity_reception_step_two);
        ButterKnife.bind(this);
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

        initScanAnimation();

    }

    private void initScanAnimation() {
//        ScanBodyView scanBodyView = findViewById(R.id.scan_view);
//        ObjectAnimator animator = ObjectAnimator.ofInt(scanBodyView, "PositionY", 0, 100);
//        animator.setInterpolator(new LinearInterpolator());
//        animator.setDuration(2000);
//        animator.setRepeatCount(ValueAnimator.INFINITE);
//        animator.start();



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
                Intent intent = new Intent(ReceptionStepTwoActivity.this, ReceptionStepThreeActivity.class);
                startActivity(intent);

                break;
        }
    }
}
