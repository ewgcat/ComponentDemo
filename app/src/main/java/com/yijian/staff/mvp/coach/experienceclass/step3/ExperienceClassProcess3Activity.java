package com.yijian.staff.mvp.coach.experienceclass.step3;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.experienceclass.step3.bean.ConsultationProgrammeBean;
import com.yijian.staff.mvp.coach.experienceclass.step3.bean.ExperienceClassProcess3Bean;
import com.yijian.staff.mvp.coach.experienceclass.step4.ExperienceClassProcess4Activity;
import com.yijian.staff.mvp.physical.PhysicalReportActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.rx.RxBus;
import com.yijian.staff.widget.ClassTimeBar;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExperienceClassProcess3Activity extends AppCompatActivity implements ExperienceClassProcess3Contract.View {

    @BindView(R.id.et_huishang_fangan_result)
    EditText etHuishangFanganResult;
    @BindView(R.id.tv_coach_huifang_record)
    TextView tvCoachHuifangRecord;

    @BindView(R.id.ll_huifang_jilu_content)
    LinearLayout llHuifangJiluContent;

    @BindView(R.id.tv_huifang_jilu_states)
    TextView tvHuifangJiluStates;
    private String memberId;
    private ExperienceClassProcess3Bean.BodyCheckBean bodyCheck;
    private Intent intent;
    private ExperienceClassProcess3Presenter presenter;
    private String memberName;
    private String processId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_class_process3);
        ButterKnife.bind(this);
        presenter = new ExperienceClassProcess3Presenter(this);
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
            presenter.getConsultationProgramme(processId);
    }

    private void initView() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.step_three_navigation_bar2);

        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);

        navigationBar2.setTitle("体验课流程");
        navigationBar2.setmRightTvText("下一步");

        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = etHuishangFanganResult.getText().toString();
                if (TextUtils.isEmpty(s)) {
                    Toast.makeText(ExperienceClassProcess3Activity.this, "请先填写会商方案，才可以进行下一步", Toast.LENGTH_SHORT).show();
                } else {
                    presenter.postConsultationProgramme(processId,s.trim());

                }
            }
        });
        ClassTimeBar timeBar = findViewById(R.id.step_three_timebar);
        timeBar.showTimeBar(3);


    }

    @OnClick({R.id.ll_ticeshuju, R.id.ll_huifang_jilu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_ticeshuju:
                if (TextUtils.isEmpty(memberId)||TextUtils.isEmpty(memberName)) {
                    Toast.makeText(this,"错误：用户id或用户名称为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                intent = new Intent(ExperienceClassProcess3Activity.this, PhysicalReportActivity.class);
                intent.putExtra("memberId", memberId);
                intent.putExtra("memberName",memberName);
                startActivity(intent);
                break;
            case R.id.ll_huifang_jilu:
                toggle(llHuifangJiluContent, tvHuifangJiluStates);
                break;

        }
    }

    private void toggle(LinearLayout linearLayout, TextView textView) {
        if (linearLayout.getVisibility() == View.GONE) {

            linearLayout.setVisibility(View.VISIBLE);
            textView.setText("隐藏");
            Drawable drawable = getResources().getDrawable(R.mipmap.trialclass_hide);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawables(null, null, drawable, null);

        } else {
            linearLayout.setVisibility(View.GONE);
            textView.setText("展开");
            Drawable drawable = getResources().getDrawable(R.mipmap.trialclass_unfold);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawables(null, null, drawable, null);
        }
    }

    @Override
    public void showConsultationProgramme(ConsultationProgrammeBean consultationProgrammeBean) {
        etHuishangFanganResult.setText(""+consultationProgrammeBean.getProgrammeContext());
        tvCoachHuifangRecord.setText(""+consultationProgrammeBean.getVisitRecord());
        }

    @Override
    public void showSaveSecceed() {

        Intent intent = new Intent(ExperienceClassProcess3Activity.this, ExperienceClassProcess4Activity.class);
        Bundle bundle = new Bundle();
        bundle.putString("memberId", memberId);
        bundle.putString("memberName", memberName);
        bundle.putString("processId", processId);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
