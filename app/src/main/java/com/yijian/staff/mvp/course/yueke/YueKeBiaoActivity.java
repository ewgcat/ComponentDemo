package com.yijian.staff.mvp.course.yueke;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.widget.NavigationBar2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YueKeBiaoActivity extends MvcBaseActivity {


    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.ll2)
    LinearLayout ll2;
    @BindView(R.id.ll3)
    LinearLayout ll3;
    @BindView(R.id.ll4)
    LinearLayout ll4;
    @BindView(R.id.ll5)
    LinearLayout ll5;
    @BindView(R.id.ll6)
    LinearLayout ll6;
    @BindView(R.id.ll7)
    LinearLayout ll7;
    private YueKeBiaoFragment yueKeBiaoFragment1;
    private YueKeBiaoFragment yueKeBiaoFragment2;
    private YueKeBiaoFragment yueKeBiaoFragment3;
    private YueKeBiaoFragment yueKeBiaoFragment4;
    private YueKeBiaoFragment yueKeBiaoFragment5;
    private YueKeBiaoFragment yueKeBiaoFragment6;
    private YueKeBiaoFragment yueKeBiaoFragment7;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_yue_ke_biao;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        NavigationBar2 navigationBar2 = findViewById(R.id.navigation_bar);
        navigationBar2.getSecondLeftIv().setVisibility(View.GONE);
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("约课表");
        changeFragment(0);

    }

    public void selectDay(int i) {
        ll1.setBackgroundColor(Color.parseColor("#f1f2f6"));
        ll2.setBackgroundColor(Color.parseColor("#f1f2f6"));
        ll3.setBackgroundColor(Color.parseColor("#f1f2f6"));
        ll4.setBackgroundColor(Color.parseColor("#f1f2f6"));
        ll5.setBackgroundColor(Color.parseColor("#f1f2f6"));
        ll6.setBackgroundColor(Color.parseColor("#f1f2f6"));
        ll7.setBackgroundColor(Color.parseColor("#f1f2f6"));
        switch (i) {
            case 0:
                ll1.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
            case 1:
                ll2.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
            case 2:
                ll3.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
            case 3:
                ll4.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
            case 4:
                ll5.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
            case 5:
                ll6.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
            case 6:
                ll7.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
        }
    }

    @OnClick({R.id.ll1, R.id.ll2, R.id.ll3, R.id.ll4, R.id.ll5, R.id.ll6, R.id.ll7})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll1:
                changeFragment(0);
                selectDay(0);
                break;
            case R.id.ll2:
                changeFragment(1);
                selectDay(1);

                break;
            case R.id.ll3:
                changeFragment(2);
                selectDay(2);

                break;
            case R.id.ll4:
                changeFragment(3);
                selectDay(3);

                break;
            case R.id.ll5:
                changeFragment(4);
                selectDay(4);

                break;
            case R.id.ll6:
                changeFragment(5);
                selectDay(5);

                break;
            case R.id.ll7:
                changeFragment(6);
                selectDay(6);

                break;
        }
    }

    private void changeFragment(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideAllIndex(fragmentTransaction);
        switch (index) {
            case 0:
                if (yueKeBiaoFragment1 == null) {
                    yueKeBiaoFragment1 = new YueKeBiaoFragment();
                    fragmentTransaction.add(R.id.fl_content, yueKeBiaoFragment1);
                } else {
                    fragmentTransaction.show(yueKeBiaoFragment1);
                }
                break;
            case 1:
                if (yueKeBiaoFragment2 == null) {
                    yueKeBiaoFragment2 = new YueKeBiaoFragment();
                    fragmentTransaction.add(R.id.fl_content, yueKeBiaoFragment2);
                } else {
                    fragmentTransaction.show(yueKeBiaoFragment2);
                }
                break;
            case 2:
                if (yueKeBiaoFragment3 == null) {
                    yueKeBiaoFragment3 = new YueKeBiaoFragment();
                    fragmentTransaction.add(R.id.fl_content, yueKeBiaoFragment3);
                } else {
                    fragmentTransaction.show(yueKeBiaoFragment3);
                }
                break;
            case 3:
                if (yueKeBiaoFragment4 == null) {
                    yueKeBiaoFragment4 = new YueKeBiaoFragment();
                    fragmentTransaction.add(R.id.fl_content, yueKeBiaoFragment4);
                } else {
                    fragmentTransaction.show(yueKeBiaoFragment4);
                }
                break;
            case 4:
                if (yueKeBiaoFragment5 == null) {
                    yueKeBiaoFragment5 = new YueKeBiaoFragment();
                    fragmentTransaction.add(R.id.fl_content, yueKeBiaoFragment5);
                } else {
                    fragmentTransaction.show(yueKeBiaoFragment5);
                }
                break;
            case 5:
                if (yueKeBiaoFragment6 == null) {
                    yueKeBiaoFragment6 = new YueKeBiaoFragment();
                    fragmentTransaction.add(R.id.fl_content, yueKeBiaoFragment6);
                } else {
                    fragmentTransaction.show(yueKeBiaoFragment6);
                }
                break;
            case 6:
                if (yueKeBiaoFragment7 == null) {
                    yueKeBiaoFragment7 = new YueKeBiaoFragment();
                    fragmentTransaction.add(R.id.fl_content, yueKeBiaoFragment7);
                } else {
                    fragmentTransaction.show(yueKeBiaoFragment7);
                }
                break;
        }
        fragmentTransaction.commit();

    }


    //隐藏所有的Fragment
    public void hideAllIndex(FragmentTransaction fragmentTransaction) {

        if (yueKeBiaoFragment1 != null && yueKeBiaoFragment1.isAdded()) {
            fragmentTransaction.hide(yueKeBiaoFragment1);
        }
        if (yueKeBiaoFragment2 != null && yueKeBiaoFragment2.isAdded()) {
            fragmentTransaction.hide(yueKeBiaoFragment2);
        }
        if (yueKeBiaoFragment3 != null && yueKeBiaoFragment3.isAdded()) {
            fragmentTransaction.hide(yueKeBiaoFragment3);
        }
        if (yueKeBiaoFragment4 != null && yueKeBiaoFragment4.isAdded()) {
            fragmentTransaction.hide(yueKeBiaoFragment4);
        }
        if (yueKeBiaoFragment5 != null && yueKeBiaoFragment5.isAdded()) {
            fragmentTransaction.hide(yueKeBiaoFragment5);
        }
        if (yueKeBiaoFragment6 != null && yueKeBiaoFragment6.isAdded()) {
            fragmentTransaction.hide(yueKeBiaoFragment6);
        }
        if (yueKeBiaoFragment7 != null && yueKeBiaoFragment7.isAdded()) {
            fragmentTransaction.hide(yueKeBiaoFragment7);
        }
    }


}
