package com.yijian.staff.mvp.course.experienceclass.step5.coach;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.bean.ExperienceClassBean;
import com.yijian.staff.bean.ClassRecordTable;
import com.yijian.staff.mvp.course.experienceclass.step4.bean.ExperienceClassRecordTable;
import com.yijian.staff.mvp.course.experienceclass.step5.bean.ConsultationConclusionBean;
import com.yijian.staff.mvp.course.experienceclass.template.template_system.Template1ClassActivity_ycm;
import com.yijian.staff.mvp.course.experienceclass.template.template_system.bean.TemplateListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by The_P on 2018/5/4.
 */

public class ExperienceClassStep5CoachFragment extends Fragment implements ExperienceClassStep5CoachContract.View {
    private ExperienceClassBean bean;
    Unbinder unbinder;

    @BindView(R.id.tv_post_price)
    TextView tvPostPrice;
    @BindView(R.id.tv_post_leader)
    TextView tvPostLeader;
    @BindView(R.id.tv_end)
    TextView tv_end;
    @BindView(R.id.et_huishang_fangan_result)
    EditText etHuishangFanganResult;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private ExperienceStep5CoachAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        bean = arguments.getParcelable("bean");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_experience_step5_coach, container, false);
        unbinder = ButterKnife.bind(this, view);
        ExperienceClassStep5CoachPresenter presenter = new ExperienceClassStep5CoachPresenter(getContext());
        presenter.setView(this);
        initRecyclerView();
        presenter.getConsultationConclusion(bean.getProcessId());
        return view;
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        adapter = new ExperienceStep5CoachAdapter(getContext());
        recyclerView.setAdapter(adapter);
        adapter.setItemClickLisenter(new ExperienceStep5CoachAdapter.ItemClickLisenter() {
            @Override
            public void onClick(ExperienceClassRecordTable bean) {
                TemplateListBean prepareRecord = bean.getPrepareRecord();
                ClassRecordTable definedRecord = bean.getDefinedRecord();
                if (prepareRecord != null) {//体验课上课记录表(采用备课模板)
                    Intent intent = new Intent(getContext(), Template1ClassActivity_ycm.class);
                    intent.putExtra("templateListBean", prepareRecord);
                    startActivity(intent);

                } else if (definedRecord != null) {//体验课上课记录表(使用自定义模板)

                }
            }
        });

    }

    @Override
    public void showConclusion(ConsultationConclusionBean bean) {
        List<ExperienceClassRecordTable> recordList = bean.getRecordList();
        if (recordList != null && !recordList.isEmpty()) {
            adapter.resetData(recordList);
        }

    }
}
