package com.yijian.staff.mvp.coach.experienceclass.step2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.experienceclass.step2.bean.AccessRecordBean;
import com.yijian.staff.mvp.coach.experienceclass.step3.ExperienceClassProcess3Activity;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.widget.ClassTimeBar;
import com.yijian.staff.widget.NavigationBar2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExperienceClassProcess2Activity extends AppCompatActivity implements ExperienceClassProcess2Contract.View {

    @BindView(R.id.et_coach_huifang_result)
    EditText etCoachHuifangResult;
    @BindView(R.id.tv_huiji_huifang_result)
    TextView tvHuijiHuifangResult;
    private String memberId;
    private ExperienceClassProcess2Presenter presenter;
    private String mobileNum;
    private String memberName;
    private String processId;
    private String processId_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_class_process2);
        ButterKnife.bind(this);
        presenter = new ExperienceClassProcess2Presenter(this);
        presenter.setView(this);
        initView();

        initData();
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        memberId = bundle.getString("memberId");
        memberName = bundle.getString("memberName");
        processId = bundle.getString("processId");
        if (!TextUtils.isEmpty(processId))
            presenter.getAccessRecord(processId);
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
//                showSavaSucceed();

                String coachVisitRecord = etCoachHuifangResult.getText().toString();
                if (TextUtils.isEmpty(coachVisitRecord)) {
                    Toast.makeText(ExperienceClassProcess2Activity.this, "请先对客户进行电话回访，填写回访记录，才可以进行下一步", Toast.LENGTH_SHORT).show();
                } else {

                    if (mobileNum != null && processId_result != null && coachVisitRecord.trim() != null) {
                        presenter.postCoachAccessRecord(new AccessRecordBean(processId_result, mobileNum, coachVisitRecord, null));
                    } else {
                        Toast.makeText(ExperienceClassProcess2Activity.this, "会员的信息缺失", Toast.LENGTH_SHORT).show();
                    }


//                    //TODO 发送请求
//                    Intent intent = new Intent(ExperienceClassProcess2Activity.this, ExperienceClassProcess3Activity.class);
//                    intent.putExtra("memberId", memberId);
//                    startActivity(intent);
                }
            }
        });

        ClassTimeBar timeBar = findViewById(R.id.step_two_timebar);
        timeBar.showTimeBar(2);

    }

    @OnClick(R.id.tv_call_phone)
    public void onViewClicked() {

        mobileNum="13222114412";
        if (TextUtils.isEmpty(mobileNum)) {
            Toast.makeText(this, "获取用户手机号码异常", Toast.LENGTH_SHORT).show();
            return;
        }
        CommonUtil.callPhone(this, mobileNum);
    }

    @Override
    public void showAccessRecord(AccessRecordBean accessRecordBean) {
        tvHuijiHuifangResult.setText("" + accessRecordBean.getSellerVisitRecord());
        etCoachHuifangResult.setText("" + accessRecordBean.getCoachVisitRecord());
        mobileNum = accessRecordBean.getMobile();
        processId_result = accessRecordBean.getProcessId();

    }

    @Override
    public void showSavaSucceed() {
        Intent intent = new Intent(ExperienceClassProcess2Activity.this, ExperienceClassProcess3Activity.class);
        Bundle bundle = new Bundle();
        bundle.putString("memberId", memberId);
        bundle.putString("memberName", memberName);
        bundle.putString("processId", processId);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
