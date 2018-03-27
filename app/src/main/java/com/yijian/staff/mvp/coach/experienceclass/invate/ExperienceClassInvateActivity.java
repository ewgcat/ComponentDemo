package com.yijian.staff.mvp.coach.experienceclass.invate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.bumptech.glide.Glide;
import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.experienceclass.template.Template1ClassActivity;
import com.yijian.staff.mvp.coach.experienceclass.template.Template2ClassActivity;
import com.yijian.staff.widget.LastInputEditText;
import com.yijian.staff.widget.NavigationBar2;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExperienceClassInvateActivity extends AppCompatActivity {

    @BindView(R.id.et_name)
    LastInputEditText etName;
    @BindView(R.id.et_class_count)
    LastInputEditText etClassCount;
    @BindView(R.id.et_coach)
    LastInputEditText etCoach;
    @BindView(R.id.et_class_num)
    LastInputEditText etClassNum;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.iv_template1)
    ImageView ivTemplate1;
    @BindView(R.id.iv_template2)
    ImageView ivTemplate2;
    private TimePickerView pickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_class_invate);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.class_invate_navigation_bar2);

        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("体验课邀请");
        navigationBar2.setmRightTvText("完成");
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(1234);
                finish();
            }
        });

        //提交结果
        pickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View view) {
                String result = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
                tvTime.setText(result);
            }
        }).build();
    }


    @OnClick({R.id.ll_time,
            R.id.ll_template1, R.id.ll_template2,
            R.id.ll_select_template1_status, R.id.ll_select_template2_status
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_time:
                pickerView.show();
                break;
            case R.id.ll_select_template1_status:
                Glide.with(ExperienceClassInvateActivity.this).load(R.mipmap.trialclass_select_small).into(ivTemplate1);
                Glide.with(ExperienceClassInvateActivity.this).load(R.mipmap.trialclass_normal_select_small).into(ivTemplate2);
                break;
            case R.id.ll_select_template2_status:
                Glide.with(ExperienceClassInvateActivity.this).load(R.mipmap.trialclass_normal_select_small).into(ivTemplate1);
                Glide.with(ExperienceClassInvateActivity.this).load(R.mipmap.trialclass_select_small).into(ivTemplate2);
                break;
            case R.id.ll_template1:
                startActivity(new Intent(ExperienceClassInvateActivity.this, Template1ClassActivity.class));
                break;
            case R.id.ll_template2:
                startActivity(new Intent(ExperienceClassInvateActivity.this, Template2ClassActivity.class));
                break;
        }
    }
}
