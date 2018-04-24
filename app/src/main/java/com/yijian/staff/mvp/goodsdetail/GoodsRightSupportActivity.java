package com.yijian.staff.mvp.goodsdetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yijian.staff.R;
import com.yijian.staff.widget.NavigationBar2;

public class GoodsRightSupportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_goods_right_support);
        initView();
    }

    private void initView() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.goods_right_support_navigation_bar2);
        navigationBar2.setTitle("权益支持");
        navigationBar2.setBackClickListener(this);
        navigationBar2.hideLeftSecondIv();



    }
}
