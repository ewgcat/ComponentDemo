package com.yijian.staff.mvp.vip.coach.outdate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.staff.R;
import com.yijian.staff.util.Logger;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;

import butterknife.ButterKnife;

/**
 * 过期会员列表
 */
@Route(path = "/test/4.1")
public class CoachOutdateViperListActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outdate_viper_list);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        NavigationBar navigationBar = findViewById(R.id.vip_over_navigation_bar);
        navigationBar.setTitle("过期会员","#ffffff");
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_WHITE));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));
        View view = getLayoutInflater().inflate(R.layout.view_header_filter,null);
        navigationBar.setRightButtonView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.i("筛选");
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
