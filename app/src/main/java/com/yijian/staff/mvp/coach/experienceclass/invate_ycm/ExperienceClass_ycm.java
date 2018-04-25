package com.yijian.staff.mvp.coach.experienceclass.invate_ycm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.experienceclass.index.ExperienceClassBean;
import com.yijian.staff.mvp.coach.experienceclass.step1.ExperienceClassStep1Fragment;
import com.yijian.staff.widget.ClassTimeBar;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.TimeBar;

/**
 * Created by The_P on 2018/4/24.
 */

public class ExperienceClass_ycm extends AppCompatActivity implements ExperienceStatusLisenter{

    private NavigationBar2 navigationBar2;
    private ClassTimeBar timeBar;
    private ExperienceClassBean bean;
    private ExperienceClassStep1Fragment experienceClassStep1Fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_ycm);

        Intent intent = getIntent();
        if (intent.hasExtra("experienceClassBean")){
            bean = (ExperienceClassBean) intent.getParcelableExtra("experienceClassBean");
        }else {
            return;
        }


        initView();
        initFragment();
        showView();
    }

    private void showView() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("bean",bean);
        showFragmentStep1(bundle);
    }

    private void initFragment() {
        experienceClassStep1Fragment = new ExperienceClassStep1Fragment();


    }




    private void initView() {
        navigationBar2 = findViewById(R.id.navigation_bar2);
        timeBar = findViewById(R.id.timebar);
    }

    public void showFragmentStep1(Bundle bundle){
        navigationBar2.setTitle("体验课流程");
        timeBar.showTimeBar(1);

        experienceClassStep1Fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.content, experienceClassStep1Fragment).commitAllowingStateLoss();
    }



    @Override
    public void step1ToStep2() {

    }

    @Override
    public void step2ToStep3() {

    }

    @Override
    public void step3ToStep4() {

    }

    @Override
    public void step4ToStep5() {

    }
}
