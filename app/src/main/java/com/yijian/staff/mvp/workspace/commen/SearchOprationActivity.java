package com.yijian.staff.mvp.workspace.commen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.workspace.perfect.PerfectActivity;
import com.yijian.staff.mvp.workspace.sport.SportTestActivity;
import com.yijian.staff.mvp.workspace.utils.ActivityUtils;
import com.yijian.staff.widget.NavigationBar2;

import butterknife.OnClick;

public class SearchOprationActivity extends MvcBaseActivity {


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

    }

    @OnClick({R.id.btn_start, R.id.btn_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start: //测试
                if (ActivityUtils.moduleType.equals(ActivityUtils.MODULE_SPORT)) {
                    mContext.startActivity(new Intent(mContext, SportTestActivity.class));
                }else if(ActivityUtils.moduleType.equals(ActivityUtils.MODULE_PERFECT)){
                    mContext.startActivity(new Intent(mContext, PerfectActivity.class));
                }
                break;
            case R.id.btn_record: //记录
                mContext.startActivity(new Intent(mContext, WorkSpaceRecordActivity.class));
                break;
            default:
        }
    }
}
