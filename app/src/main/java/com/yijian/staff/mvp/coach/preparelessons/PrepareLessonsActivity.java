package com.yijian.staff.mvp.coach.preparelessons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.preparelessons.all.PrepareAllLessonActivity;
import com.yijian.staff.mvp.coach.preparelessons.createlession.CreatePrivateLessionActivity;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.NavigationBarItemFactory;

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
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.navigation_bar2);
        navigationBar2.setTitle("备课");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);}

    @OnClick({R.id.rel_create, R.id.rel_all_lesson})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.rel_create: //创建私教备课
                startActivity(new Intent(PrepareLessonsActivity.this, CreatePrivateLessionActivity.class));
                break;

            case R.id.rel_all_lesson:
                startActivity(new Intent(PrepareLessonsActivity.this, PrepareAllLessonActivity.class));
                break;
        }
    }

}
