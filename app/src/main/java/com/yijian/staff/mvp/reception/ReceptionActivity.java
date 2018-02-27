package com.yijian.staff.mvp.reception;

import android.graphics.Color;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.commonlibrary.BaseActivity;
import com.example.commonlibrary.utils.system.StatusBarUtil;
import com.example.commonlibrary.widget.NavigationBar;
import com.example.commonlibrary.widget.NavigationBarItemFactory;
import com.yijian.staff.R;
@Route(path = "/staff/reception")
public class ReceptionActivity extends BaseActivity<Object,ReceptionPresenter> implements View.OnClickListener{



    @Override
    protected int getContentLayout() {
        return R.layout.activity_reception;
    }

    @Override
    protected  void initView() {
        StatusBarUtil.setLightStatusBar(this, 0xffffff);

        NavigationBar navigationBar= (NavigationBar) findViewById(R.id.reception_activity_navigation_bar);
        navigationBar.setTitle("接待","#000000");
        navigationBar.setNavigationBarBackgroudColor(Color.parseColor("#ffffff"));
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_BLACK));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));
    }




    @Override
    protected boolean isNeedEmptyLayout() {
        return false;
    }

    @Override
    protected void initData() {


    }





    @Override
    public void onClick(View v) {

    }

    @Override
    public void updateData(Object o) {

    }
}
