package com.yijian.staff.mvp.course.experienceclass.step2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.bean.ExperienceClassBean;
import com.yijian.staff.mvp.course.experienceclass.step2.bean.AccessRecordBean;
import com.yijian.staff.util.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by The_P on 2018/4/25.
 */

public class ExperienceClassStep2Fragment extends Fragment implements ExperienceClassProcess2Contract.View {

    @BindView(R.id.et_coach_huifang_result)
    EditText etCoachHuifangResult;
    @BindView(R.id.tv_huiji_huifang_result)
    TextView tvHuijiHuifangResult;

    private ExperienceClassBean bean;
    Unbinder unbinder;
    private ExperienceClassProcess2Presenter presenter;
    private String mobileNum;
    private String processId_result;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        bean = arguments.getParcelable("bean");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_experience_step2, container, false);
        presenter = new ExperienceClassProcess2Presenter(getLifecycle(),getContext());
        presenter.setView(this);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @OnClick(R.id.tv_call_phone)
    public void onViewClicked() {

//        mobileNum = "13222114412";
        if (TextUtils.isEmpty(mobileNum)) {
            Toast.makeText(getContext(), "获取用户手机号码异常", Toast.LENGTH_SHORT).show();
            return;
        }
        CommonUtil.callPhone(getContext(), mobileNum);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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

    }
}
