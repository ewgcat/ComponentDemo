package com.yijian.staff.mvp.experienceclass.step2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.mvp.experienceclass.step1.ExperienceClassProcess1Activity;
import com.yijian.staff.mvp.experienceclass.step3.ExperienceClassProcess3Activity;
import com.yijian.staff.widget.ClassTimeBar;
import com.yijian.staff.widget.NavigationBar2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExperienceClassProcess2Activity extends AppCompatActivity {

    @BindView(R.id.et_coach_huifang_result)
    EditText etCoachHuifangResult;
    @BindView(R.id.tv_huiji_huifang_result)
    TextView tvHuijiHuifangResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_class_process2);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.step_two_navigation_bar2);

        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);

        navigationBar2.setTitle("体验课流程");
        navigationBar2.setmRightTvText("下一步");
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = etCoachHuifangResult.getText().toString();
                if (TextUtils.isEmpty(s)){
                    Toast.makeText(ExperienceClassProcess2Activity.this,"请先对客户进行电话回访，填写回访记录，才可以进行下一步",Toast.LENGTH_SHORT).show();
                }else {
                    startActivity(new Intent(ExperienceClassProcess2Activity.this,ExperienceClassProcess3Activity.class));
                }
            }
        });

        ClassTimeBar timeBar = findViewById(R.id.step_two_timebar);
        timeBar.showTimeBar(2);
    }

    @OnClick(R.id.tv_call_phone)
    public void onViewClicked() {
    }
}
