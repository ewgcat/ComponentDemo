package com.yijian.staff.mvp.contract;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.NavigationBarItemFactory;

public class ContractActivity extends MvcBaseActivity {



    @Override
    protected int getLayoutID() {
        return R.layout.activity_contract;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.contract_navigation_bar2);
        navigationBar2.setTitle("合同");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
    }
}
