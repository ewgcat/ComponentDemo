package com.yijian.staff.mvp.coach.experienceclass.step4;

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
import com.yijian.staff.mvp.coach.experienceclass.step1.ExperienceClassProcess1Bean;
import com.yijian.staff.mvp.coach.experienceclass.step3.ExperienceClassProcess3Bean;
import com.yijian.staff.mvp.coach.experienceclass.step5.coach.ExperienceClassProcess5Activity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONArrayObserver;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.DateUtil;
import com.yijian.staff.widget.ClassTimeBar;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExperienceClassProcess4Activity extends AppCompatActivity {

    @BindView(R.id.bt_invite)
    Button btInvite;

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

    private NavigationBar2 navigationBar2;
    private String memberId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_class_process4);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        navigationBar2 = (NavigationBar2) findViewById(R.id.step_four_navigation_bar2);

        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);

        navigationBar2.setTitle("体验课流程");
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExperienceClassProcess4Activity.this, ExperienceClassProcess5Activity.class);
                intent.putExtra("memberId", memberId);
                startActivity(intent);

            }
        });

        ClassTimeBar timeBar = findViewById(R.id.step_four_timebar);
        timeBar.showTimeBar(4);

        llEmptyView.setVisibility(View.VISIBLE);
        llContent.setVisibility(View.GONE);

        memberId = getIntent().getStringExtra("memberId");
        initData();
    }

    private void initData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("memberId", memberId);
        HttpManager.getHasHeaderHasParam(HttpManager.GET_EXPERICECE_INVITE_AGAIN_URL, map, new ResultJSONArrayObserver() {
            @Override
            public void onSuccess(JSONArray result) {
                List<ExperienceClassProcess4Bean> process4BeanList = com.alibaba.fastjson.JSONObject.parseArray(result.toString(), ExperienceClassProcess4Bean.class);
                llEmptyView.setVisibility(View.GONE);
                llContent.setVisibility(View.VISIBLE);
                ExperienceClassProcess1Bean experienceClassProcess1Bean = new ExperienceClassProcess1Bean(new JSONObject());
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

    @OnClick({R.id.ll_first_class, R.id.bt_invite})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_first_class:
                break;
            case R.id.bt_invite:
                Intent intent = new Intent(ExperienceClassProcess4Activity.this, ExperienceClassInvateActivity.class);
                intent.putExtra("memberId", memberId);
                startActivityForResult(intent, 1001);

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 1234 && requestCode == 1001) {
            llEmptyView.setVisibility(View.GONE);
            btInvite.setVisibility(View.GONE);
            llContent.setVisibility(View.VISIBLE);
            initData();

            navigationBar2.setmRightTvText("下一步");
            navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(ExperienceClassProcess4Activity.this, ExperienceClassProcess5Activity.class));
                }
            });


        }
    }
}
