package com.yijian.staff.mvp.vip;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VipInfoActivity extends AppCompatActivity {

    @BindView(R.id.lin_all_vip)
    LinearLayout lin_all_vip;
    @BindView(R.id.lin_today_visit)
    LinearLayout lin_today_visit;
    @BindView(R.id.tv_label_all)
    TextView tv_label_all;
    @BindView(R.id.tv_label_visit)
    TextView tv_label_visit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_info);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        NavigationBar navigationBar = findViewById(R.id.vip_over_navigation_bar);
        navigationBar.setTitle("会员信息","#ffffff");
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_WHITE));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));
        changeFragment(0);
    }

    @OnClick({R.id.lin_all_vip, R.id.lin_today_visit})
    public void click(View view) {

        switch (view.getId()){
            case R.id.lin_all_vip:
                changeFragment(0);
                break;

            case R.id.lin_today_visit:
                changeFragment(1);
                break;
        }

    }

    private void changeFragment(int status){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(status == 0){
            tv_label_visit.setTextColor(Color.parseColor("#31a4fc"));
            tv_label_all.setTextColor(Color.parseColor("#666666"));
            fragmentTransaction.replace(R.id.fl_content,VipAllPeopleInfoFragment.getInstance());
        }else{
            tv_label_visit.setTextColor(Color.parseColor("#666666"));
            tv_label_visit.setTextColor(Color.parseColor("#31a4fc"));
            fragmentTransaction.replace(R.id.fl_content,VipTodayVisitInfoFragment.getInstance());
        }
        fragmentTransaction.commit();
    }

}
