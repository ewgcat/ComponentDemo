package com.yijian.staff.mvp.coach.experienceclass.invate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.experienceclass.step1.bean.InviterBean;
import com.yijian.staff.mvp.coach.experienceclass.template.template_system.Template1ClassActivity_ycm;
import com.yijian.staff.mvp.coach.experienceclass.template.template_system.bean.TemplateListBean;
import com.yijian.staff.widget.NavigationBar2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExperienceClassInvateActivity_ycm extends AppCompatActivity implements ExperienceClassInvateContract_ycm.View {

    @BindView(R.id.tv_name_invitee)
    TextView tvNameInvitee;
    @BindView(R.id.tv_name_coach)
    TextView tvNameCoach;

    @BindView(R.id.tv_class_num)
    TextView tvClassNum;
    @BindView(R.id.tv_class_count)
    TextView tvClassCount;


    @BindView(R.id.tv_time_start)
    TextView tvTimeStart;
    @BindView(R.id.tv_time_class)
    TextView tvTimeClass;

    @BindView(R.id.ll_time_start)
    LinearLayout llTimeStart;
    @BindView(R.id.ll_time_class)
    LinearLayout llTimeClass;

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

    private TimePickerView pickerView;
    private String memberId;
    private ExperienceClassInvatePresenter_ycm presenter;
    private List<TemplateListBean> templateList;
    private OptionsPickerView pvNoLinkOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_class_invate_ycm);
        ButterKnife.bind(this);
        presenter = new ExperienceClassInvatePresenter_ycm(this);
        presenter.setView(this);

        initView();
//        memberId="666";
        presenter.getInviterInfo(memberId);
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
            }
        });

        //上课时间选择
        pickerView = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View view) {
                String result = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
                tvTimeStart.setText(result);
            }
        }).build();


        //课程时间选择
        List<String> times = Arrays.asList("1小时", "2小时", "3小时", "其他时间");
        pvNoLinkOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvTimeClass.setText(times.get(options1));

            }
        }).build();

        pvNoLinkOptions.setNPicker(times, null, null);
        pvNoLinkOptions.setSelectOptions(0);


    }


    @OnClick({R.id.ll_time_start,
            R.id.ll_time_class, R.id.ll_select_template1_status, R.id.ll_select_template2_status,
            R.id.ll_template1, R.id.ll_template2
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_time_start:
                pickerView.show();
                break;

            case R.id.ll_time_class:
                pvNoLinkOptions.show();
                break;

            case R.id.ll_select_template1_status:
                Glide.with(ExperienceClassInvateActivity_ycm.this).load(R.mipmap.trialclass_select_small).into(ivTemplate1);
                tvTemplate1.setTextColor(getResources().getColor(R.color.blue));

                Glide.with(ExperienceClassInvateActivity_ycm.this).load(R.mipmap.trialclass_normal_select_small).into(ivTemplate2);
                tvTemplate2.setTextColor(getResources().getColor(R.color.text_black2));
                break;
            case R.id.ll_select_template2_status:
                Glide.with(ExperienceClassInvateActivity_ycm.this).load(R.mipmap.trialclass_normal_select_small).into(ivTemplate1);
                tvTemplate1.setTextColor(getResources().getColor(R.color.text_black2));
                Glide.with(ExperienceClassInvateActivity_ycm.this).load(R.mipmap.trialclass_select_small).into(ivTemplate2);
                tvTemplate2.setTextColor(getResources().getColor(R.color.blue));
                break;

            case R.id.ll_template1:
                if (templateList != null) {
                    if (templateList == null) {
                        Toast.makeText(this, "模板数据异常", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent(this, Template1ClassActivity_ycm.class);
                    intent.putParcelableArrayListExtra("templateList", (ArrayList<? extends Parcelable>) templateList);
                    startActivity(intent);
                }

                break;
            case R.id.ll_template2:
//                startActivity(new Intent(ExperienceClassInvateActivity_ycm.this, Template2ClassActivity.class));
                break;
        }
    }

    @Override
    public void showInviterInfo(InviterBean inviterBean) {
        tvNameInvitee.setText(inviterBean.getMemberName());
        tvNameCoach.setText(inviterBean.getCoachName());
        tvClassCount.setText("" + inviterBean.getCourseCurrent());
        tvClassNum.setText("" + inviterBean.getCourseNum());
//                Long startTime = inviterBean.getStartTime();
//                if (startTime!=null){
//                    String time = DateUtil.parseLongDateToTimeString(startTime);
//                    tvTime.setText(time);
//                }
//        etRemark.setText("备注信息：" + inviterBean.getRemark());


//        templateList = inviterBean.getTemplateList();

//        if (templateList != null && templateList.size() != 0) {
        //显示系统提供的模板
//            Glide.with(ExperienceClassInvateActivity_ycm.this).load(R.mipmap.trialclass_select_small).into(ivTemplate1);
//            tvTemplate1.setTextColor(getResources().getColor(R.color.blue));
//        }

//        customerTemplateList = inviterBean.getCustomerTemplateList();
//        if (customerTemplateList != null && customerTemplateList.size() != 0) {
//            //显示教练自定义模板
//
//
//        }
    }
}
