package com.yijian.staff.mvp.coach.experienceclass.invate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.experienceclass.invate.bean.InvateBean;
import com.yijian.staff.mvp.coach.experienceclass.invate_ycm.ExperienceClass_ycm;
import com.yijian.staff.mvp.coach.experienceclass.step1.bean.InviterBean;
import com.yijian.staff.mvp.coach.experienceclass.template.template_system.Template1ClassActivity_ycm;
import com.yijian.staff.mvp.coach.experienceclass.template.template_system.bean.TemplateListBean;
import com.yijian.staff.widget.LastInputEditText;
import com.yijian.staff.widget.NavigationBar2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExperienceClassInvateAgainActivity_ycm extends AppCompatActivity implements ExperienceClassInvateAgainContract_ycm.View {

    @BindView(R.id.et_name)
    LastInputEditText etName;
    @BindView(R.id.et_class_count)
    LastInputEditText etClassCount;
    @BindView(R.id.et_coach)
    LastInputEditText etCoach;
    @BindView(R.id.et_class_num)
    LastInputEditText etClassNum;
    @BindView(R.id.tv_time_class)
    TextView tvTimeClass;
    @BindView(R.id.tv_time_length)
    TextView tvTimeLength;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.iv_template1)
    ImageView ivTemplate1;
    @BindView(R.id.iv_template2)
    ImageView ivTemplate2;

    @BindView(R.id.tv_template1)
    TextView tvTemplate1;

    @BindView(R.id.tv_template2)
    TextView tvTemplate2;

    @BindView(R.id.ll_template1_root)
    LinearLayout llTemplate1Root;

    @BindView(R.id.ll_template2_root)
    LinearLayout llTemplate2Root;

    private TimePickerView pickerView;
    private ExperienceClassInvateAgainPresenter_ycm presenter;
    private List<TemplateListBean> templateList;
    private OptionsPickerView pvNoLinkOptions;
    private InviterBean inviteVO;

    private boolean template1Select;
    private boolean template2Select;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_class_invate_again_ycm);
        ButterKnife.bind(this);
        presenter = new ExperienceClassInvateAgainPresenter_ycm(this);
        presenter.setView(this);
        initData();
        initView();
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent.hasExtra("inviteVO")) {
            inviteVO = intent.getParcelableExtra("inviteVO");
        }

    }

    private void initView() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.class_invate_navigation_bar2);

        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("体验课邀请");
        navigationBar2.setmRightTvText("完成");
        navigationBar2.setmRightTvColor(R.color.blue);
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 提交保存请求后执行下面操作
//                setResult(1234);
//                finish();
                if (template1Select) {
                    sendInvate();

                } else if (template2Select) {

                } else {
                    Toast.makeText(ExperienceClassInvateAgainActivity_ycm.this, "请选择一个课程模板", Toast.LENGTH_SHORT).show();

                }

            }
        });

        //上课时间选择
        pickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View view) {
                String result = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
                tvTimeClass.setText(result);
            }
        }).build();


        //课程时间选择
        List<String> times = Arrays.asList("60分钟", "120分钟", "180分钟");
        pvNoLinkOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvTimeLength.setText(times.get(options1));

            }
        }).build();

        pvNoLinkOptions.setNPicker(times, null, null);
        pvNoLinkOptions.setSelectOptions(0);

        if (inviteVO == null) return;
        String memberName = inviteVO.getMemberName();
        if (!TextUtils.isEmpty(memberName)) etName.setText(memberName);

        String coachName = inviteVO.getCoachName();
        if (!TextUtils.isEmpty(coachName)) etCoach.setText(coachName);

        Integer courseNum = inviteVO.getCourseNum();
        if (courseNum != null) etClassNum.setText(String.valueOf(courseNum));

        Integer courseCurrent = inviteVO.getCourseCurrent();
        if (courseCurrent != null) etClassCount.setText(String.valueOf(courseCurrent));

        String startTime = inviteVO.getStartTime();
        if (!TextUtils.isEmpty(startTime)) tvTimeClass.setText(startTime);

        Integer courseTime = inviteVO.getCourseTime();
        if (courseTime != null) tvTimeLength.setText(String.valueOf(courseTime));

        String remark = inviteVO.getRemark();
        if (!TextUtils.isEmpty(remark)) etRemark.setText(remark);

        templateList = inviteVO.getExperienceTemplateList();
        if (templateList != null && !templateList.isEmpty()) llTemplate1Root.setVisibility(View.VISIBLE);
    }

    private void sendInvate() {
        //                    {
//                        "courseTime": 0,
//                            "memberId": "string",
//                            "processId": "string",
//                            "remark": "string",
//                            "startTime": "2018-05-04 02:26",
//                            "templateId": "string"
//                    }

        if (inviteVO == null) return;

        InvateBean invateBean = new InvateBean();
//        "60分钟", "120分钟", "180分钟"
        String text = tvTimeLength.getText().toString();
        if ("60分钟".equals(text)) {
            invateBean.setCourseTime(60);
        } else if ("120分钟".equals(text)) {
            invateBean.setCourseTime(120);
        } else if ("180分钟".equals(text)) {
            invateBean.setCourseTime(180);
        } else {
            Toast.makeText(ExperienceClassInvateAgainActivity_ycm.this, "请选择课程时长", Toast.LENGTH_SHORT).show();
            return;
        }

        String memberId = inviteVO.getMemberId();
        if (!TextUtils.isEmpty(memberId)) {
            invateBean.setMemberId(memberId);
        } else {
            Toast.makeText(ExperienceClassInvateAgainActivity_ycm.this, "memberId不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String processId = inviteVO.getProcessId();
        if (!TextUtils.isEmpty(processId)) {
            invateBean.setProcessId(processId);
        } else {
            Toast.makeText(ExperienceClassInvateAgainActivity_ycm.this, "processId不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String remark = etRemark.getText().toString();
        if (!TextUtils.isEmpty(remark)) {
            invateBean.setRemark(remark);
        } else {
            Toast.makeText(ExperienceClassInvateAgainActivity_ycm.this, "请填写备注信息", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(tvTimeClass.getText()) || "请选择".equals(tvTimeClass.getText())) {
            Toast.makeText(ExperienceClassInvateAgainActivity_ycm.this, "请选择上课时间", Toast.LENGTH_SHORT).show();
            return;
        } else {
            invateBean.setStartTime(tvTimeClass.getText().toString());
        }

        List<TemplateListBean> experienceTemplateList = inviteVO.getExperienceTemplateList();
        if (experienceTemplateList == null || experienceTemplateList.isEmpty()) {
            Toast.makeText(ExperienceClassInvateAgainActivity_ycm.this, "课程模板数据异常", Toast.LENGTH_SHORT).show();
            return;
        } else {
            String templateId = experienceTemplateList.get(0).getTemplateId();
            if (!TextUtils.isEmpty(templateId)) {
                invateBean.setTemplateId(templateId);
            } else {
                Toast.makeText(ExperienceClassInvateAgainActivity_ycm.this, "课程模板数据异常", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        presenter.saveAndSendInvite(invateBean);
    }


    @OnClick({R.id.ll_time_class,
            R.id.ll_time_length, R.id.ll_select_template1_status, R.id.ll_select_template2_status,
            R.id.ll_template1, R.id.ll_template2
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_time_class:
                pickerView.show();
                break;

            case R.id.ll_time_length:
                pvNoLinkOptions.show();
                break;

            case R.id.ll_select_template1_status:
                Glide.with(ExperienceClassInvateAgainActivity_ycm.this).load(R.mipmap.trialclass_select_small).into(ivTemplate1);
                tvTemplate1.setTextColor(getResources().getColor(R.color.blue));

                Glide.with(ExperienceClassInvateAgainActivity_ycm.this).load(R.mipmap.trialclass_normal_select_small).into(ivTemplate2);
                tvTemplate2.setTextColor(getResources().getColor(R.color.text_black2));

                template1Select = true;
                template2Select = false;
                break;
            case R.id.ll_select_template2_status:
                Glide.with(ExperienceClassInvateAgainActivity_ycm.this).load(R.mipmap.trialclass_normal_select_small).into(ivTemplate1);
                tvTemplate1.setTextColor(getResources().getColor(R.color.text_black2));
                Glide.with(ExperienceClassInvateAgainActivity_ycm.this).load(R.mipmap.trialclass_select_small).into(ivTemplate2);
                tvTemplate2.setTextColor(getResources().getColor(R.color.blue));
                template1Select = false;
                template2Select = true;

                break;

            case R.id.ll_template1:
                if (templateList != null && !templateList.isEmpty()) {
                    Intent intent = new Intent(this, Template1ClassActivity_ycm.class);
//                    intent.putParcelableArrayListExtra("templateList", (ArrayList<? extends Parcelable>) templateList);
                    TemplateListBean value = templateList.get(0);
                    if (value == null) return;
                    intent.putExtra("templateListBean", value);
                    startActivity(intent);
                }

                break;
            case R.id.ll_template2:
//                startActivity(new Intent(ExperienceClassInvateActivity_ycm.this, Template2ClassActivity.class));
                break;
        }
    }

    @Override
    public void showSendSucceed() {
        this.setResult(ExperienceClass_ycm.INVATEAGAIN_RESULT);
        finish();
    }
}
