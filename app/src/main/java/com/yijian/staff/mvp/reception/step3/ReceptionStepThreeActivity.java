package com.yijian.staff.mvp.reception.step3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.ReceptionActivity;
import com.yijian.staff.mvp.reception.step3.coach.CoachProductFragment;
import com.yijian.staff.mvp.reception.step3.kefu.HuiJiProductQuotationFragment;
import com.yijian.staff.mvp.reception.step3.leader.LeaderProductFragment;
import com.yijian.staff.mvp.reception.step4.ReceptionStepFourActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.util.Logger;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.TimeBar;

import butterknife.ButterKnife;

public class ReceptionStepThreeActivity extends AppCompatActivity implements View.OnClickListener, ReceptionStepThreeContract.View {

    private static final String TAG = "ReceptionStepThreeActiv";
    private Fragment fragment;
    private String memberId;
    private int userRole;
    private ReceptionStepThreePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception_step_three);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent.hasExtra("memberId")) {
            memberId = intent.getStringExtra("memberId");
        }

        presenter = new ReceptionStepThreePresenter(this);
        presenter.setView(this);

        initView();
    }

    private void initView() {
        NavigationBar2 navigationBar2 = findViewById(R.id.step_three_navigation_bar2);
        navigationBar2.getFirstLeftIv().setOnClickListener(this);
        navigationBar2.getSecondLeftIv().setOnClickListener(this);
        navigationBar2.getmRightTv().setOnClickListener(this);
        navigationBar2.setTitle("产品报价(3/5)");
        navigationBar2.setmRightTvText("下一步");
        TimeBar timeBar = findViewById(R.id.step_three_timebar);
        timeBar.showTimeBar(3);

        userRole = SharePreferenceUtil.getUserRole();
//        Log.e(TAG, "initView: userRole=" + userRole);
        userRole = 2;

        if (userRole == 1) {
            fragment = new HuiJiProductQuotationFragment();
        } else if (userRole == 2) {
            fragment = new CoachProductFragment();
        } else if (userRole == 3 | userRole == 4) {
            fragment = new LeaderProductFragment();
        }

        Bundle bundle = new Bundle();
        bundle.putString("memberId", memberId);
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fl_content, fragment).commit();
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.iv_first_left:
                finish();
                break;
            case R.id.iv_second_left:
                Intent i = new Intent(this, ReceptionActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;
            case R.id.right_tv:
                rightAction();
                break;
        }
    }

    private void rightAction() {
        if (userRole == 1) {//会籍
            Intent intent = new Intent(ReceptionStepThreeActivity.this, ReceptionStepFourActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("memberId", memberId);
            startActivity(intent);
        } else if (userRole == 2) {//教练
            if (TextUtils.isEmpty(memberId)) return;
            presenter.coachToSale(memberId);

        } else if (userRole == 3) {//总监
            if (TextUtils.isEmpty(memberId)) return;
            presenter.leaderToSale(memberId);
        }
    }


    @Override
    public void leaderToSaleSecceed() {

    }

    @Override
    public void coachToSaleSecceed() {

    }
}
