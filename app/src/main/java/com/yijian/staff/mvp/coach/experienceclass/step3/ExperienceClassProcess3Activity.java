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

public class ExperienceClassProcess3Activity extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_class_process3);
        ButterKnife.bind(this);
        initView();
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
                    //TODO 发送请求
                    Intent intent = new Intent(ExperienceClassProcess3Activity.this, ExperienceClassProcess4Activity.class);
                    intent.putExtra("memberId", memberId);
                    startActivity(intent);
                }
            }
        });
        ClassTimeBar timeBar = findViewById(R.id.step_three_timebar);
        timeBar.showTimeBar(3);

        memberId = getIntent().getStringExtra("memberId");
        HashMap<String, String> map = new HashMap<>();
        map.put("memberId", memberId);
        HttpManager.getHasHeaderHasParam(HttpManager.GET_EXPERICECE_HUI_SHANG_FANG_AN_URL, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                ExperienceClassProcess3Bean experienceClassProcess3Bean = new ExperienceClassProcess3Bean(result);
                etHuishangFanganResult.setText(experienceClassProcess3Bean.getProgrammeContext());
                ExperienceClassProcess3Bean.VisitRecordBean visitRecord = experienceClassProcess3Bean.getVisitRecord();
                if (visitRecord!=null){
                    String coachVisitRecord = visitRecord.getCoachVisitRecord();
                    if (TextUtils.isEmpty(coachVisitRecord)) {
                        tvCoachHuifangRecord.setText(coachVisitRecord);
                    }

                }
                bodyCheck = experienceClassProcess3Bean.getBodyCheck();
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @OnClick({R.id.ll_ticeshuju, R.id.ll_huifang_jilu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_ticeshuju:
                intent = new Intent(ExperienceClassProcess3Activity.this, PhysicalReportActivity.class);
                RxBus.getDefault().post(bodyCheck);
                intent.putExtra("memberId", memberId);
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
}
