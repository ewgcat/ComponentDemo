package com.yijian.staff.mvp.goodsdetail;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.utils.DensityUtils;
import com.yijian.staff.R;
import com.yijian.staff.bean.KeCheng;
import com.yijian.staff.util.system.StatusBarUtils;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;

import java.util.ArrayList;
import java.util.List;

public class GoodsRightSupportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setLightStatusBar(this, 0xffffff);

        setContentView(R.layout.activity_goods_right_support);
        initView();
    }

    private void initView() {
        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.goods_right_support_navigation_bar);
        navigationBar.setTitle("权益支持", "#000000");
        navigationBar.setNavigationBarBackgroudColor(Color.parseColor("#ffffff"));
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_BLACK));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));




    }
}
