package com.yijian.staff.mvp.course.experienceclass.step3;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.mvp.course.experienceclass.index.ExperienceClassBean;
import com.yijian.staff.mvp.course.experienceclass.step3.bean.ConsultationProgrammeBean;
import com.yijian.staff.mvp.reception.physical.PhysicalReportActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by The_P on 2018/4/25.
 */

public class ExperienceClassStep3Fragment extends Fragment implements ExperienceClassProcess3Contract.View {
    private ExperienceClassBean bean;
    Unbinder unbinder;

    @BindView(R.id.et_huishang_fangan_result)
    EditText etHuishangFanganResult;
    @BindView(R.id.tv_coach_huifang_record)
    TextView tvCoachHuifangRecord;

    @BindView(R.id.ll_huifang_jilu_content)
    LinearLayout llHuifangJiluContent;

    @BindView(R.id.tv_huifang_jilu_states)
    TextView tvHuifangJiluStates;
    private ExperienceClassProcess3Presenter presenter;
    private String memberId;
    private String memberName;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        bean = arguments.getParcelable("bean");
        memberId = bean.getMemberId();
        memberName = bean.getMemberName();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_experience_step3, container, false);
        presenter = new ExperienceClassProcess3Presenter(getContext());
        presenter.setView(this);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showConsultationProgramme(ConsultationProgrammeBean consultationProgrammeBean) {
        etHuishangFanganResult.setText("" + consultationProgrammeBean.getProgrammeContext());
        tvCoachHuifangRecord.setText("" + consultationProgrammeBean.getVisitRecord());
    }

    @Override
    public void showSaveSecceed() {

    }


    @OnClick({R.id.ll_ticeshuju, R.id.ll_huifang_jilu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_ticeshuju:
                if (TextUtils.isEmpty(memberId) || TextUtils.isEmpty(memberName)) {
                    Toast.makeText(getContext(), "错误：用户id或用户名称为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getActivity(), PhysicalReportActivity.class);
                intent.putExtra("memberId", memberId);
                intent.putExtra("memberName", memberName);
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
