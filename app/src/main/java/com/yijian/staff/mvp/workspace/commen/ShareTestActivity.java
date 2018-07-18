package com.yijian.staff.mvp.workspace.commen;

import android.os.Bundle;
import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.workspace.utils.ActivityUtils;
import com.yijian.staff.widget.NavigationBar2;

public class ShareTestActivity extends MvcBaseActivity {

    @Override
    protected int getLayoutID() {
        return R.layout.activity_share_test;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initTitle();
        initData();
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = findViewById(R.id.navigation_bar);
        navigationBar2.setTitle(ActivityUtils.name+"的测试记录");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
    }

    private void initData(){

    }
}
