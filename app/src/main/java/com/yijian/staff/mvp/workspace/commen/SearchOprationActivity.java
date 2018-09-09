package com.yijian.staff.mvp.workspace.commen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.workspace.bean.WorkSpaceVipBean;
import com.yijian.staff.mvp.workspace.perfect.PerfectActivity;
import com.yijian.staff.mvp.workspace.sport.SportTestActivity;
import com.yijian.staff.mvp.workspace.static_assessment.StaticAssessmentActivity;
import com.yijian.staff.mvp.workspace.utils.ActivityUtils;
import com.yijian.staff.util.ImageLoader;
import com.yijian.staff.widget.NavigationBar;

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
        NavigationBar NavigationBar = findViewById(R.id.navigation_bar);
        NavigationBar.setTitle("学员测评");
        NavigationBar.hideLeftSecondIv();
        NavigationBar.setBackClickListener(this);
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        workSpaceVipBean = (WorkSpaceVipBean) bundle.getSerializable("workSpaceVipBean");
        ImageLoader.setHeadImageResource(workSpaceVipBean.getHeadPath(), mContext, iv_header);
        iv_gender.setImageResource("1".equals(workSpaceVipBean.getGender()) ? R.mipmap.lg_man : R.mipmap.lg_women);
        tv_name.setText(workSpaceVipBean.getName());
        tv_age.setText(String.valueOf(workSpaceVipBean.getAge()));
        tv_role.setText(workSpaceVipBean.getTypeName());
       /* String subclassName = workSpaceVipBean.getSubclassName();
        switch (subclassName) {
            case "CustomerInfoVO":
                tv_role.setText("正式会员");
                break;
            case "PotentialVO":
                tv_role.setText("潜在会员");
                break;
            case "CustomerIntentionVO":
                tv_role.setText("意向会员");
                break;
            case "CustomerExpireVO":
                tv_role.setText("过期会员");
                break;
            case "CustomerTodayVisitVO":
                tv_role.setText("今日来访会员");
                break;
            case "CoachInfoVO":
                tv_role.setText("正式学员");
                break;
            case "CoachIntentionVO":
                tv_role.setText("意向学员");
                break;
            case "CoachExpireVO":
                tv_role.setText("过期学员");
                break;
            case "CoachPotentialStudentVO":
                tv_role.setText("潜在学员");
                break;
            case "CoachTodayVisitVO":
                tv_role.setText("今日来访学员");
                break;
            default:
        }*/
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
