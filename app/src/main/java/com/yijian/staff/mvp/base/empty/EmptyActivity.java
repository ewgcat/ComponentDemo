package com.yijian.staff.mvp.base.empty;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.widget.NavigationBar2;

@Route(path = "/test/empty")
public class EmptyActivity extends MvcBaseActivity {

    @Override
    protected int getLayoutID() {
        return R.layout.activity_empty;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        NavigationBar2 navigationBar2=  findViewById(R.id.empty_navigation_bar2);
        navigationBar2.setBackClickListener(this);
        navigationBar2.hideLeftSecondIv();
    }
}
