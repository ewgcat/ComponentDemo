package com.yijian.staff.mvp.reception.step2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.ReceptionActivity;
import com.yijian.staff.mvp.reception.step3.ReceptionStepThreeActivity;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.util.Logger;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.ScanBodyView;
import com.yijian.staff.widget.TimeBar;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class KeFuReceptionStepTwoActivity extends AppCompatActivity implements View.OnClickListener,KeFuReceptionStepTwoContract.View {

    private ScanBodyView scanBodyView;
    private View ll_to_coach;
    private KeFuReceptionStepTwoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_kefu_reception_step_two);
        ButterKnife.bind(this);
        initView();
        presenter = new KeFuReceptionStepTwoPresenter(this);
        presenter.setView(this);
    }

    private void initView() {
        NavigationBar2 navigationBar2 = findViewById(R.id.step_two_navigation_bar2);

        navigationBar2.setBackClickListener(this);
        navigationBar2.getSecondLeftIv().setOnClickListener(this);
        navigationBar2.setTitle("体测录入(2/5)");


        TimeBar timeBar = findViewById(R.id.step_two_timebar);
        timeBar.showTimeBar(2);

        scanBodyView = findViewById(R.id.scan_view);

         findViewById(R.id.tv_next_step).setOnClickListener(this);
        ll_to_coach = findViewById(R.id.ll_to_coach);
//       if( SharePreferenceUtil.getHasToScan()){
//           ll_to_coach.setVisibility(View.INVISIBLE);
//       }else {
//           ll_to_coach.setVisibility(View.VISIBLE);
//           ll_to_coach.setOnClickListener(this);
//       }

        ll_to_coach.setOnClickListener(this);
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
                scanBodyView.stopScan();
                Intent i = new Intent(this, ReceptionActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;

            case R.id.tv_next_step:
                scanBodyView.stopScan();

                presenter.jumpBodyCheck();
                break;
            case R.id.ll_to_coach:
//                SharePreferenceUtil.setHasToScan(true);
//                ll_to_coach.setVisibility(View.INVISIBLE);
                scanBodyView.startScan();
                presenter.coachBodyCheck();
                break;

        }
    }

    @Override
    protected void onPause() {
        scanBodyView.stopScan();
        super.onPause();
    }

    @Override
    protected void onResume() {
//        if( SharePreferenceUtil.getHasToScan()){
//            ll_to_coach.setVisibility(View.INVISIBLE);
//            scanBodyView.startScan();
//        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        scanBodyView.stopScan();
        super.onDestroy();
    }

    @Override
    public void showJumpBodyCheck() {
        //TODO 教练没录完，不能跳转,教练没开始录，可跳转
//        Intent intent = new Intent(KeFuReceptionStepTwoActivity.this, ReceptionStepThreeActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
    }

    @Override
    public void showCoachBodyCheck() {

    }
}
