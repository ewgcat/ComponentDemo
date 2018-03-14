package com.yijian.staff.mvp.reception.step3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.ReceptionActivity;
import com.yijian.staff.mvp.reception.step3.coach.CoachProductFragment;
import com.yijian.staff.mvp.reception.step3.kefu.HuiJiProductQuotationFragment;
import com.yijian.staff.mvp.reception.step3.leader.LeaderProductFragment;
import com.yijian.staff.mvp.reception.step4.ReceptionStepFourActivity;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.TimeBar;

import butterknife.ButterKnife;

public class ReceptionStepThreeActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception_step_three);
        ButterKnife.bind(this);
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

//        HuiJiProductQuotationFragment huiJiProductQuotationFragment = new HuiJiProductQuotationFragment();
//        CoachProductFragment huiJiProductQuotationFragment = new CoachProductFragment();
        LeaderProductFragment huiJiProductQuotationFragment = new LeaderProductFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fl_content,huiJiProductQuotationFragment).commit();
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
                Intent intent = new Intent(ReceptionStepThreeActivity.this, ReceptionStepFourActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }



}
