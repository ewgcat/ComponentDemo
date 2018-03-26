package com.yijian.staff.mvp.preparelessons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.staff.R;
import com.yijian.staff.mvp.mine.calendartable.CalendarSettingActivity;
import com.yijian.staff.mvp.mine.calendartable.CalendarTableActivity;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrepareLessonsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_lessons);
        ButterKnife.bind(this);
        initTitle();
    }


    private void initTitle() {
        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.navigation_bar);
        navigationBar.setTitle("备课", "#ffffff");
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_WHITE));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));
    }

    @OnClick({R.id.rel_create})
    public void click(View v){
        switch(v.getId()){
            case R.id.rel_create: //创建私教备课
                startActivity(new Intent(PrepareLessonsActivity.this,CreatePrivateLessionActivity.class));
                break;

        }
    }

}
