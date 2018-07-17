package com.yijian.staff.mvp.workspace.sport;

import android.os.Bundle;
import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.workspace.utils.ActivityUtils;
import com.yijian.staff.widget.NavigationBar2;

public class SportTestActivity1  extends MvcBaseActivity {

    private final String tag1 = "step1";
    private final String tag2 = "step2";
    private final String tag3 = "step3";

    @Override
    protected int getLayoutID() {
        return R.layout.activity_sport_test1;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initTitle();
        initData();
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = findViewById(R.id.navigation_bar);
        navigationBar2.setTitle(ActivityUtils.moduleType+"测试");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
    }

    private void initData() {
        ActivityUtils.addFragment(getSupportFragmentManager(),R.id.fl_sport,new SportFragment1(),tag1);
    }

}
