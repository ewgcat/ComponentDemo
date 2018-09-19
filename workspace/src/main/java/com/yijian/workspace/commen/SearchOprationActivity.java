package com.yijian.workspace.commen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.yijian.commonlib.mvp.base.mvc.MvcBaseActivity;
import com.yijian.commonlib.util.ImageLoader;
import com.yijian.staff.mvp.workspace.bean.WorkSpaceVipBean;
import com.yijian.staff.mvp.workspace.perfect.PerfectActivity;
import com.yijian.staff.mvp.workspace.sport.SportTestActivity;
import com.yijian.staff.mvp.workspace.static_assessment.StaticAssessmentActivity;
import com.yijian.staff.mvp.workspace.utils.ActivityUtils;
import com.yijian.workspace.R;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchOprationActivity extends MvcBaseActivity {

    @BindView(R.id.iv_header)
    ImageView iv_header;
    @BindView(R.id.iv_gender)
    ImageView iv_gender;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_age)
    TextView tv_age;
    @BindView(R.id.tv_role)
    TextView tv_role;

    WorkSpaceVipBean workSpaceVipBean;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_search_opration;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initTitle();
        initData();
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = findViewById(R.id.navigation_bar);
        navigationBar2.setTitle("学员测评");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
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

    @OnClick({R.id.btn_start, R.id.btn_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start: //测试
                if(ActivityUtils.isShareJump){
                    ActivityUtils.moduleType = ActivityUtils.tempModule;
                    ActivityUtils.isShareJump = false;
                }
                if (ActivityUtils.moduleType.equals(ActivityUtils.MODULE_SPORT)) {
                    mContext.startActivity(new Intent(mContext, SportTestActivity.class));
                }else if(ActivityUtils.moduleType.equals(ActivityUtils.MODULE_PERFECT)){
                    mContext.startActivity(new Intent(mContext, PerfectActivity.class));
                }else if(ActivityUtils.moduleType.equals(ActivityUtils.MODULE_STATIC_EVALUATE)){
                    mContext.startActivity(new Intent(mContext, StaticAssessmentActivity.class));
                }else if(ActivityUtils.moduleType.equals(ActivityUtils.MODULE_DYNAMIC_EVALUATE)){
                    mContext.startActivity(new Intent(mContext, DynamicAssessmentActivity.class));
                }
                break;
            case R.id.btn_record: //记录
                if(ActivityUtils.isShareJump){
                    ActivityUtils.moduleType = ActivityUtils.tempModule;
                    ActivityUtils.isShareJump = false;
                }
                mContext.startActivity(new Intent(mContext, WorkSpaceRecordActivity.class));
                break;
            default:
        }
    }
}
