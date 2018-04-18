package com.yijian.staff.mvp.coach.experienceclass.step1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.experienceclass.invate.ExperienceClassInvateActivity;
import com.yijian.staff.mvp.coach.experienceclass.step2.ExperienceClassProcess2Activity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.DateUtil;
import com.yijian.staff.widget.ClassTimeBar;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONObject;

import java.util.HashMap;

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
    @BindView(R.id.bt_invite)
    Button btInvite;
    private NavigationBar2 navigationBar2;
    private String memberId;
    private String processId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_class_process1);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        navigationBar2 = (NavigationBar2) findViewById(R.id.step_one_navigation_bar2);

        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("体验课流程");

        ClassTimeBar timeBar = findViewById(R.id.step_one_timebar);
        timeBar.showTimeBar(1);

        memberId = getIntent().getStringExtra("memberId");
        processId = getIntent().getStringExtra("processId");
        HashMap<String, String> map = new HashMap<>();
        map.put("processId", processId);
        HttpManager.getHasHeaderHasParam(HttpManager.GET_EXPERICECE_INVITE_HISTORY_URL, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                llEmptyView.setVisibility(View.GONE);
                llContent.setVisibility(View.VISIBLE);
                ExperienceClassProcess1Bean experienceClassProcess1Bean = new ExperienceClassProcess1Bean(result);
                tvName.setText(experienceClassProcess1Bean.getMemberName());
                tvCoach.setText(experienceClassProcess1Bean.getCoachName());
                tvClassCount.setText(experienceClassProcess1Bean.getCourseCurrent() + "");
                tvClassNum.setText(experienceClassProcess1Bean.getCourseNum() + "");
                Long startTime = experienceClassProcess1Bean.getStartTime();
                if (startTime != null) {
                    String time = DateUtil.parseLongDateToTimeString(startTime);
                    tvTime.setText(time);
                }

                etRemark.setText(experienceClassProcess1Bean.getRemark());
            }

            @Override
            public void onFail(String msg) {
                llEmptyView.setVisibility(View.VISIBLE);
                llContent.setVisibility(View.GONE);
            }
        });
    }

    @OnClick(R.id.bt_invite)
    public void onViewClicked() {
        Intent intent = new Intent(ExperienceClassProcess1Activity.this, ExperienceClassInvateActivity.class);
        intent.putExtra("memberId", memberId);
        intent.putExtra("processId",processId);
        startActivityForResult(intent, 1001);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 1234 && requestCode == 1001) {

            llEmptyView.setVisibility(View.GONE);
            btInvite.setVisibility(View.GONE);
            llContent.setVisibility(View.VISIBLE);


            navigationBar2.setmRightTvText("下一步");
            navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ExperienceClassProcess1Activity.this, ExperienceClassProcess2Activity.class);
                    intent.putExtra("processId", memberId);
                    startActivity(intent);
                }
            });


        }
    }
}

