package com.yijian.staff.mvp.coach.experienceclass.step4;

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
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.experienceclass.index.ExperienceClassBean;
import com.yijian.staff.mvp.coach.experienceclass.step1.bean.ClassRecordTable;
import com.yijian.staff.mvp.coach.experienceclass.step1.bean.InviterBean;
import com.yijian.staff.mvp.coach.experienceclass.step4.bean.ExperienceClassRecordTable;
import com.yijian.staff.mvp.coach.experienceclass.step4.bean.InvitationAgainBean;
import com.yijian.staff.mvp.coach.experienceclass.template.template_system.Template1ClassActivity_ycm;
import com.yijian.staff.mvp.coach.experienceclass.template.template_system.bean.TemplateListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by The_P on 2018/4/25.
 */

public class ExperienceClassStep4Fragment extends Fragment implements ExperienceClassProcess4Contract.View {
    private ExperienceClassBean bean;
    Unbinder unbinder;

    @BindView(R.id.tv_invitation_again)
    TextView tvInvitationAgain;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private ExperienceClassProcess4Presenter presenter;
    private InviterBean inviteVO;
    private ExperienceStep4Adapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        bean = arguments.getParcelable("bean");
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_experience_step4,container,false);
        presenter = new ExperienceClassProcess4Presenter(getContext());
        presenter.setView(this);
        initView();

        unbinder= ButterKnife.bind(this,view);
        return view;
    }

    private void initView() {
//        recyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ExperienceStep4Adapter(getContext());
        recyclerView.setAdapter(adapter);
        adapter.setItemClickLisenter(new ExperienceStep4Adapter.ItemClickLisenter() {
            @Override
            public void onClick(ExperienceClassRecordTable bean) {
                TemplateListBean prepareRecord = bean.getPrepareRecord();
                ClassRecordTable definedRecord = bean.getDefinedRecord();
                if (prepareRecord!=null){//体验课上课记录表(使用自定义模板)
                    Intent intent = new Intent(getContext(), Template1ClassActivity_ycm.class);
                    intent.putExtra("templateListBean",prepareRecord);
                    startActivity(intent);
                }else if (definedRecord!=null){//体验课上课记录表(采用备课模板)

                }



            }
        });

    }

    @OnClick({R.id.tv_invitation_again})
    public void onViewClicked(View view) {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void showClassRecordList(InvitationAgainBean bean) {
        List<ExperienceClassRecordTable> recordList = bean.getRecordList();
        inviteVO = bean.getInviteVO();
        if (recordList!=null&&!recordList.isEmpty()){
            adapter.resetData(recordList);
        }
    }
}
