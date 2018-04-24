package com.yijian.staff.mvp.goodsdetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.widget.NavigationBar2;

public class GoodsRightSupportActivity extends MvcBaseActivity {



    @Override
    protected int getLayoutID() {
        return R.layout.activity_goods_right_support;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.goods_right_support_navigation_bar2);
        navigationBar2.setTitle("权益支持");
        navigationBar2.setBackClickListener(this);
        navigationBar2.hideLeftSecondIv();
    }


}
