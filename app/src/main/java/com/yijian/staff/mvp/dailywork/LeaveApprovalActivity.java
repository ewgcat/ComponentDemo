package com.yijian.staff.mvp.dailywork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.yijian.staff.R;
import com.yijian.staff.widget.NavigationBar2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 请假审批 拒绝/同意 共用页面
 */
public class LeaveApprovalActivity extends AppCompatActivity {

    @BindView(R.id.rg_approval_result)
    RadioGroup rg_approval_result;
    @BindView(R.id.rel_refuse_reason)
    RelativeLayout rel_refuse_reason;
    @BindView(R.id.et_refuse_reason)
    EditText et_refuse_reason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval);
        ButterKnife.bind(this);
        initTitle();
        initView();
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = findViewById(R.id.vip_over_navigation_bar2);
        navigationBar2.setTitle("请假审批");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
    }

    private void initView(){
        rg_approval_result.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                rel_refuse_reason.setVisibility(checkedId == R.id.rb_agree? View.GONE:View.VISIBLE);
            }
        });
    }
}
