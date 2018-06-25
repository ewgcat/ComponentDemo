package com.yijian.staff.mvp.coach.experienceclass.invate_ycm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.experienceclass.index.ExperienceClassBean;
import com.yijian.staff.mvp.coach.experienceclass.invate.ExperienceClassInvateAgainActivity_ycm;
import com.yijian.staff.mvp.coach.experienceclass.step1.ExperienceClassStep1Fragment;
import com.yijian.staff.mvp.coach.experienceclass.step1.bean.InviterBean;
import com.yijian.staff.mvp.coach.experienceclass.step2.ExperienceClassStep2Fragment;
import com.yijian.staff.mvp.coach.experienceclass.step3.ExperienceClassStep3Fragment;
import com.yijian.staff.mvp.coach.experienceclass.step4.ExperienceClassStep4Fragment;
import com.yijian.staff.mvp.coach.experienceclass.template.template_system.Template1ClassActivity_ycm;
import com.yijian.staff.widget.ClassTimeBar;
import com.yijian.staff.widget.NavigationBar2;

/**
 * Created by The_P on 2018/4/24.
 */

public class ExperienceClass_ycm extends AppCompatActivity implements ExperienceStatusLisenter {

    private NavigationBar2 navigationBar2;
    private ClassTimeBar timeBar;
    private ExperienceClassBean bean;
    private ExperienceClassStep1Fragment experienceClassStep1Fragment;
    private ExperienceClassStep2Fragment experienceClassStep2Fragment;
    private ExperienceClassStep3Fragment experienceClassStep3Fragment;

    public static final int INVATEAGAIN_REQUEST = 100;
    public static final int INVATEAGAIN_RESULT = 101;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_ycm);

        Intent intent = getIntent();
        if (intent.hasExtra("experienceClassBean")) {
            bean = (ExperienceClassBean) intent.getParcelableExtra("experienceClassBean");
        } else {
            return;
        }


        initView();
        initFragment();
        showView();
    }

    private void showView() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("bean", bean);
        showFragmentStep1(bundle);
    }

    private void initFragment() {
        experienceClassStep1Fragment = new ExperienceClassStep1Fragment();

        experienceClassStep2Fragment = new ExperienceClassStep2Fragment();

        experienceClassStep3Fragment = new ExperienceClassStep3Fragment();


    }


    private void initView() {
        navigationBar2 = findViewById(R.id.navigation_bar2);
        navigationBar2.setTitle("体验课流程");
        timeBar = findViewById(R.id.timebar);
    }

    public void showFragmentStep1(Bundle bundle) {

        timeBar.showTimeBar(1);

        experienceClassStep1Fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.content, experienceClassStep1Fragment).commitAllowingStateLoss();
    }

    public void showFragmentStep2(Bundle bundle) {
        timeBar.showTimeBar(2);

        experienceClassStep2Fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.content, experienceClassStep2Fragment).commitAllowingStateLoss();
    }

    public void showFragmentStep3(Bundle bundle) {
        timeBar.showTimeBar(3);

        experienceClassStep3Fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.content, experienceClassStep3Fragment).commitAllowingStateLoss();
    }


    public void showFragmentStep4(Bundle bundle) {
        timeBar.showTimeBar(4);
        ExperienceClassStep4Fragment experienceClassStep4Fragment = new ExperienceClassStep4Fragment();
        experienceClassStep4Fragment.setStatusLisenter(this);
        experienceClassStep4Fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.content, experienceClassStep4Fragment).commitAllowingStateLoss();
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


    @Override
    public void toInvateAgainActivity(InviterBean bean) {
        Intent intent = new Intent(this, ExperienceClassInvateAgainActivity_ycm.class);
        intent.putExtra("inviteVO", bean);
        startActivityForResult(intent, INVATEAGAIN_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INVATEAGAIN_REQUEST) {
            if (resultCode == INVATEAGAIN_RESULT) {
                //发送二次邀约成功

            }
        }

    }
}
