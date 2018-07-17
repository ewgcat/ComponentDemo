package com.yijian.staff.mvp.workspace.commen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.workspace.utils.ActivityUtils;
import com.yijian.staff.widget.NavigationBar2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkSpaceRecordActivity extends MvcBaseActivity {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_work_space_record;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initTitle();
        initData();
    }

    private void initTitle() {
        String moduleType = getIntent().getStringExtra("moduleType");
        String name = getIntent().getStringExtra("name");
        NavigationBar2 navigationBar2 = findViewById(R.id.navigation_bar);
        navigationBar2.setTitle(ActivityUtils.name + ActivityUtils.moduleType+"测评记录");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
    }

    private void initData() {
        WorkSpaceRecordAdapter workSpaceRecordAdapter = new WorkSpaceRecordAdapter();
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(workSpaceRecordAdapter);
    }

}
