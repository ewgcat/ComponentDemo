package com.yijian.staff.mvp.course.experienceclass.step1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yijian.staff.R;
import com.yijian.staff.bean.ExperienceClassBean;
import com.yijian.staff.bean.InviterBean;
import com.yijian.staff.mvp.course.experienceclass.template.Template2ClassActivity;
import com.yijian.staff.mvp.course.experienceclass.template.template_system.Template1ClassActivity_ycm;
import com.yijian.staff.mvp.course.experienceclass.template.template_system.bean.TemplateListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by The_P on 2018/4/24.
 */

public class ExperienceClassStep1Fragment extends Fragment implements ExperienceClassStep1Contract.View {

    @BindView(R.id.tv_name)
    TextView etName;
    @BindView(R.id.tv_class_count)
    TextView etClassCount;
    @BindView(R.id.tv_coach)
    TextView etCoach;
    @BindView(R.id.tv_class_num)
    TextView etClassNum;
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

    private ExperienceClassStep1Presenter presenter;
    Unbinder unbinder;
    private ExperienceClassBean bean;
    private TemplateListBean templateListBean;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        bean = arguments.getParcelable("bean");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_experience_step1, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new ExperienceClassStep1Presenter(getLifecycle(),getContext());
        presenter.setView(this);

        if (!TextUtils.isEmpty(bean.getProcessId())) {
            presenter.getInviterInfo(bean.getProcessId());
        }
        return view;
    }

    @OnClick({R.id.ll_template1_root,
            R.id.ll_template2_root})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.ll_template1_root://系统模板
                if (templateListBean == null) {
                    Toast.makeText(getContext(), "模板数据异常", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getContext(), Template1ClassActivity_ycm.class);
                intent.putExtra("templateListBean", templateListBean);
                startActivity(intent);
                break;
            case R.id.ll_template2_root://自定义模板
                startActivity(new Intent(getContext(), Template2ClassActivity.class));
                break;
        }

    }


    @Override
    public void showInviterInfo(InviterBean inviterBean) {
        etName.setText(inviterBean.getMemberName());
        etCoach.setText(inviterBean.getCoachName());
        etClassCount.setText(inviterBean.getCourseCurrent() == null ? "" : "" + inviterBean.getCourseCurrent());
        etClassNum.setText(inviterBean.getCourseNum() == null ? "" : "" + inviterBean.getCourseNum());

        tvTimeStart.setText("" + inviterBean.getStartTime());

        etRemark.setText("备注信息：" + inviterBean.getRemark());

        Integer courseTime = inviterBean.getCourseTime();
        tvTimeClass.setText(courseTime == null ? "" : "" + courseTime);

        List<TemplateListBean> experienceTemplateList = inviterBean.getExperienceTemplateList();

        if (experienceTemplateList != null && !experienceTemplateList.isEmpty()) {
            templateListBean = experienceTemplateList.get(0);

            //显示系统提供的模板
            Glide.with(getContext()).load(R.mipmap.trialclass_select_small).into(ivTemplate1);
            tvTemplate1.setTextColor(getResources().getColor(R.color.blue));
        } else if (inviterBean.getPrepareVO() != null) {
            templateListBean = inviterBean.getPrepareVO().getTemplateVO();

            //显示系统提供的模板
            Glide.with(getContext()).load(R.mipmap.trialclass_select_small).into(ivTemplate1);
            tvTemplate1.setTextColor(getResources().getColor(R.color.blue));
        }

//
//
//        templateList = inviterBean.getTemplateList();
//        if (templateList != null && templateList.size() != 0) {
//            //显示系统提供的模板
//            Glide.with(getContext()).load(R.mipmap.trialclass_select_small).into(ivTemplate1);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
