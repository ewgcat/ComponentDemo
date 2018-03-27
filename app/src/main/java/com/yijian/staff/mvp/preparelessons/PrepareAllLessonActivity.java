package com.yijian.staff.mvp.preparelessons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yijian.staff.R;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;

import butterknife.ButterKnife;

public class PrepareAllLessonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_all_lesson);
        ButterKnife.bind(this);
        initTitle();
    }


    private void initTitle() {
        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.navigation_bar);
        navigationBar.setTitle("所有课程安排", "#ffffff");
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_WHITE));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));
    }

}
