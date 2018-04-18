package com.yijian.staff.mvp.questionnaire;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.widget.NavigationBar2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionListActivity extends MvcBaseActivity {


    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.question_navigation_bar2)
    NavigationBar2 navigationBar2;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_question_list;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        navigationBar2.setTitle("问卷查询");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        rlv.setLayoutManager(layoutmanager);

//        rlv.setAdapter();
    }


}
