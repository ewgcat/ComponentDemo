package com.yijian.staff.mvp.course.experienceclass.step1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bigkoo.pickerview.view.TimePickerView;
import com.yijian.staff.R;
import com.yijian.staff.bean.InviterBean;
import com.yijian.staff.mvp.course.experienceclass.step2.ExperienceClassProcess2Activity;
import com.yijian.staff.mvp.course.experienceclass.template.Template2ClassActivity;
import com.yijian.staff.mvp.course.experienceclass.template.template_system.bean.TemplateListBean;
import com.yijian.staff.widget.ClassTimeBar;
import com.yijian.staff.widget.LastInputEditText;
import com.yijian.staff.widget.NavigationBar2;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExperienceClassStep1Activity_ycm extends AppCompatActivity implements ExperienceClassStep1Contract.View {

    @BindView(R.id.et_name)
    LastInputEditText etName;
    @BindView(R.id.et_class_count)
    LastInputEditText etClassCount;
    @BindView(R.id.et_coach)
    LastInputEditText etCoach;
    @BindView(R.id.et_class_num)
    LastInputEditText etClassNum;
    @BindView(R.id.tv_time_start)
    TextView tvTimeStart;
    @BindView(R.id.tv_time_class)
    TextView tvTimeClass;

    @BindView(R.id.et_remark)
    TextView etRemark;
    @BindView(R.id.ll_template1_root)
    LinearLayout llTemplate1Root;
    @BindView(R.id.ll_template2_root)
    LinearLayout llTemplate2Root;

    @BindView(R.id.iv_template1)
    ImageView ivTemplate1;
    @BindView(R.id.iv_template2)
    ImageView ivTemplate2;
    @BindView(R.id.tv_template1_name)
    TextView tvTemplate1;
    @BindView(R.id.tv_template2_name)
    TextView tvTemplate2;

    private TimePickerView pickerView;
    private String memberId;
    private ExperienceClassStep1Presenter presenter;
    private List<TemplateListBean> templateList;
    private List<Object> customerTemplateList;
    private String memberName;
    private String processId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_class_step1_ycm);

        ButterKnife.bind(this);

        presenter = new ExperienceClassStep1Presenter(getLifecycle(),this);
        presenter.setView(this);
        initView();

        initData();
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        memberId = bundle.getString("memberId");
        memberName = bundle.getString("memberName");
        processId = bundle.getString("processId");

        if (!TextUtils.isEmpty(this.processId)) {
            presenter.getInviterInfo(this.processId);
        }


    }

    private void initView() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.navigation_bar2);

        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("体验课流程");
        navigationBar2.setmRightTvText("下一步");
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                //TODO 提交保存请求后执行下面操作
//                setResult(1234);
//                finish();

                if (TextUtils.isEmpty(memberId)) {
                    Toast.makeText(ExperienceClassStep1Activity_ycm.this, "客户id不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(memberName)) {
                    Toast.makeText(ExperienceClassStep1Activity_ycm.this, "客户姓名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(processId)) {
                    Toast.makeText(ExperienceClassStep1Activity_ycm.this, "流程ID 不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }


                Intent intent = new Intent(ExperienceClassStep1Activity_ycm.this, ExperienceClassProcess2Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("memberId", memberId);
                bundle.putString("memberName", memberName);
                bundle.putString("processId", processId);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        ClassTimeBar timeBar = findViewById(R.id.step_one_timebar);
        timeBar.showTimeBar(1);

    }

    @OnClick({R.id.ll_template1_root,
            R.id.ll_template2_root})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.ll_template1_root://系统模板
//                if (templateList == null) {
//                    Toast.makeText(this, "模板数据异常", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                Intent intent = new Intent(this, Template1ClassActivity_ycm.class);
//                intent.putParcelableArrayListExtra("templateList", (ArrayList<? extends Parcelable>) templateList);
//                startActivity(intent);
                break;
            case R.id.ll_template2_root://自定义模板
                startActivity(new Intent(ExperienceClassStep1Activity_ycm.this, Template2ClassActivity.class));
                break;
        }
    }

    @Override
    public void showInviterInfo(InviterBean inviterBean) {
        etName.setText(inviterBean.getMemberName());
        etCoach.setText(inviterBean.getCoachName());
        etClassCount.setText("" + inviterBean.getCourseCurrent());
        etClassNum.setText("" + inviterBean.getCourseNum());
//                Long startTime = inviterBean.getStartTime();
//                if (startTime!=null){
//                    String time = DateUtil.parseLongDateToTimeString(startTime);
//                    tvTime.setText(time);
//                }
        etRemark.setText("备注信息：" + inviterBean.getRemark());


//        templateList = inviterBean.getTemplateList();
//        if (templateList != null && templateList.size() != 0) {
//            //显示系统提供的模板
//            Glide.with(ExperienceClassStep1Activity_ycm.this).load(R.mipmap.trialclass_select_small).into(ivTemplate1);
//            tvTemplate1.setTextColor(getResources().getColor(R.color.blue));
//        }
//
//        customerTemplateList = inviterBean.getCustomerTemplateList();
//        if (customerTemplateList != null && customerTemplateList.size() != 0) {
//            //显示教练自定义模板
//
//
//        }
    }
}
