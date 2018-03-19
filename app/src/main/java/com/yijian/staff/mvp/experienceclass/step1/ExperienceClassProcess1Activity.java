package com.yijian.staff.mvp.experienceclass.step1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.experienceclass.index.ExperienceClassBean;
import com.yijian.staff.mvp.experienceclass.invate.ExperienceClassInvateActivity;
import com.yijian.staff.widget.ClassTimeBar;
import com.yijian.staff.widget.NavigationBar2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExperienceClassProcess1Activity extends AppCompatActivity {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_coach)
    TextView tvCoach;
    @BindView(R.id.tv_class_num)
    TextView tvClassNum;
    @BindView(R.id.tv_class_count)
    TextView tvClassCount;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.ll_empty_view)
    LinearLayout llEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_class_process1);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.step_one_navigation_bar2);

        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("体验课流程");

        ClassTimeBar timeBar = findViewById(R.id.step_one_timebar);
        timeBar.showTimeBar(1);
    }

    @OnClick(R.id.bt_invite)
    public void onViewClicked() {
        startActivityForResult(new Intent(ExperienceClassProcess1Activity.this,ExperienceClassInvateActivity.class),1000 );
    }
}
