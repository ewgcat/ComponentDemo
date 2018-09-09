package com.yijian.staff.mvp.base.empty;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.widget.NavigationBar;

@Route(path = "/test/empty")
public class EmptyActivity extends MvcBaseActivity {

    @Override
    protected int getLayoutID() {
        return R.layout.activity_empty;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        NavigationBar NavigationBar = findViewById(R.id.empty_navigation_bar2);
        NavigationBar.setBackClickListener(this);
        NavigationBar.hideLeftSecondIv();
    }
}
