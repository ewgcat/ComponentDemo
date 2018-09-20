package com.yijian.workspace.commen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.commonlib.base.mvc.MvcBaseActivity;
import com.yijian.commonlib.util.ImageLoader;
import com.yijian.commonlib.widget.NavigationBar;
import com.yijian.workspace.bean.WorkSpaceVipBean;
import com.yijian.workspace.dynamic_assessment.DynamicAssessmentActivity;
import com.yijian.workspace.perfect.PerfectActivity;
import com.yijian.workspace.sport.SportTestActivity;
import com.yijian.workspace.static_assessment.StaticAssessmentActivity;
import com.yijian.workspace.utils.ActivityUtils;
import com.yijian.workspace.R;

public class SearchOprationActivity extends MvcBaseActivity implements View.OnClickListener {

    ImageView iv_header;
    ImageView iv_gender;
    TextView tv_name;
    TextView tv_age;
    TextView tv_role;

    WorkSpaceVipBean workSpaceVipBean;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_search_opration;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        iv_header = findViewById(R.id.iv_header);
        iv_gender = findViewById(R.id.iv_gender);
        tv_name = findViewById(R.id.tv_name);
        tv_age = findViewById(R.id.tv_age);
        tv_role = findViewById(R.id.tv_role);

        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_record).setOnClickListener(this);

        initTitle();
        initData();
    }

    private void initTitle() {
        NavigationBar navigationBar = findViewById(R.id.navigation_bar);
        navigationBar.setTitle("学员测评");
        navigationBar.hideLeftSecondIv();
        navigationBar.setBackClickListener(this);
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        workSpaceVipBean = (WorkSpaceVipBean) bundle.getSerializable("workSpaceVipBean");
        ImageLoader.setHeadImageResource(workSpaceVipBean.getHeadPath(), mContext, iv_header);
        iv_gender.setImageResource("1".equals(workSpaceVipBean.getGender()) ? R.mipmap.lg_man : R.mipmap.lg_women);
        tv_name.setText(workSpaceVipBean.getName());
        tv_age.setText(String.valueOf(workSpaceVipBean.getAge()));
        tv_role.setText(workSpaceVipBean.getTypeName());
    }



    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_start) {
            if (ActivityUtils.isShareJump) {
                ActivityUtils.moduleType = ActivityUtils.tempModule;
                ActivityUtils.isShareJump = false;
            }
            if (ActivityUtils.moduleType.equals(ActivityUtils.MODULE_SPORT)) {
                mContext.startActivity(new Intent(mContext, SportTestActivity.class));
            } else if (ActivityUtils.moduleType.equals(ActivityUtils.MODULE_PERFECT)) {
                mContext.startActivity(new Intent(mContext, PerfectActivity.class));
            } else if (ActivityUtils.moduleType.equals(ActivityUtils.MODULE_STATIC_EVALUATE)) {
                mContext.startActivity(new Intent(mContext, StaticAssessmentActivity.class));
            } else if (ActivityUtils.moduleType.equals(ActivityUtils.MODULE_DYNAMIC_EVALUATE)) {
                mContext.startActivity(new Intent(mContext, DynamicAssessmentActivity.class));
            }

        } else if (i == R.id.btn_record) {
            if (ActivityUtils.isShareJump) {
                ActivityUtils.moduleType = ActivityUtils.tempModule;
                ActivityUtils.isShareJump = false;
            }
            mContext.startActivity(new Intent(mContext, WorkSpaceRecordActivity.class));

        } else {
        }
    }
}
