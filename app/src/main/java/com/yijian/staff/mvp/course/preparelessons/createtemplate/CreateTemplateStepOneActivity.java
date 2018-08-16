package com.yijian.staff.mvp.course.preparelessons.createtemplate;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.widget.NavigationBar2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateTemplateStepOneActivity extends MvcBaseActivity {


    @BindView(R.id.ll_container1)
    LinearLayout llContainer1;
    @BindView(R.id.ll_container2)
    LinearLayout llContainer2;
    @BindView(R.id.ll_container3)
    LinearLayout llContainer3;

    @Override
    protected void initView(Bundle savedInstanceState) {
        NavigationBar2 navigationBar2 = findViewById(R.id.create_template_one_navigation_bar2);
        navigationBar2.setBackClickListener(this);
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setmRightTvText("下一步");
        navigationBar2.setmRightTvColor(getResources().getColor(R.color.blue));
        navigationBar2.setTitle("创建私教课备课");


    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_create_template_step_one;
    }


    @OnClick({R.id.ll_controller1, R.id.ll_controller2, R.id.ll_controller3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_controller1:
                int visibility1 = llContainer1.getVisibility();
                if (visibility1==View.VISIBLE){
                    llContainer1.setVisibility(View.GONE);
                }else {
                    llContainer1.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.ll_controller2:
                int visibility2 = llContainer2.getVisibility();
                if (visibility2==View.VISIBLE){
                    llContainer2.setVisibility(View.GONE);
                }else {
                    llContainer2.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.ll_controller3:
                int visibility3 = llContainer3.getVisibility();
                if (visibility3==View.VISIBLE){
                    llContainer3.setVisibility(View.GONE);
                }else {
                    llContainer3.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

}
