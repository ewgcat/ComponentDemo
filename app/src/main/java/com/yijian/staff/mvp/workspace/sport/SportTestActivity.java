package com.yijian.staff.mvp.workspace.sport;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.workspace.utils.ActivityUtils;
import com.yijian.staff.widget.NavigationBar2;

import butterknife.BindView;
import butterknife.OnClick;

public class SportTestActivity extends MvcBaseActivity {

    private final String tag1 = "com.yijian.staff.mvp.workspace.sport.SportFragment1";
    private final String tag2 = "com.yijian.staff.mvp.workspace.sport.SportFragment2";
    private final String tag3 = "com.yijian.staff.mvp.workspace.sport.SportFragment3";
    private int currentIndex = 0;

    private TextView rightTv;
    @BindView(R.id.btn_next)
    Button btn_next;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_sport_test1;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initTitle();
        initData();
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = findViewById(R.id.navigation_bar);
        navigationBar2.setTitle(ActivityUtils.moduleType + "测试");
        navigationBar2.hideLeftSecondIv();
        rightTv = navigationBar2.getmRightTv();
        rightTv.setText("上一步");
        rightTv.setTextColor(getResources().getColor(R.color.blue));
        rightTv.setVisibility(View.GONE);
        navigationBar2.setBackClickListener(this);
    }

    private void initData() {
        ActivityUtils.showFragment(getSupportFragmentManager(), R.id.fl_sport, tag1, new String[]{tag1, tag2, tag3});
    }

    @OnClick({R.id.right_tv, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btn_next: //下一步
                if (currentIndex < 3) {
                    currentIndex++;
                }
                switch (currentIndex) {
                    case 1:
                        ActivityUtils.showFragment(getSupportFragmentManager(), R.id.fl_sport, tag2, new String[]{tag1, tag2, tag3});
                        rightTv.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        ActivityUtils.showFragment(getSupportFragmentManager(), R.id.fl_sport, tag3, new String[]{tag1, tag2, tag3});
                        btn_next.setText("完成");
                        rightTv.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        finish();
                        break;
                    default:
                }
                break;

            case R.id.right_tv: //上一步
                if (currentIndex > 0) {
                    currentIndex--;
                }
                if(currentIndex == 0){
                    rightTv.setVisibility(View.GONE);
                    btn_next.setText("下一步");
                    ActivityUtils.showFragment(getSupportFragmentManager(), R.id.fl_sport, tag1, new String[]{tag1, tag2, tag3});
                }else if(currentIndex == 1){
                    rightTv.setVisibility(View.VISIBLE);
                    btn_next.setText("下一步");
                    ActivityUtils.showFragment(getSupportFragmentManager(), R.id.fl_sport, tag2, new String[]{tag1, tag2, tag3});
                }
                break;
            default:

        }
    }


}
