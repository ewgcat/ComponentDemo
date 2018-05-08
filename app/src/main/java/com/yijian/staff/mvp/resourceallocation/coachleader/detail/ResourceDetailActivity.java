package com.yijian.staff.mvp.resourceallocation.coachleader.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.resourceallocation.coachleader.selectcoach.CoachInfo;
import com.yijian.staff.mvp.resourceallocation.coachleader.selectcoach.SelectCoachPopupWindow;
import com.yijian.staff.util.Logger;
import com.yijian.staff.widget.NavigationBar2;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResourceDetailActivity extends MvcBaseActivity {


    private LinearLayout root_view;
    private LinearLayout ll_zhengsi_vip;
    private SelectCoachPopupWindow selectCoachPopupWindow;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_resource_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        NavigationBar2 navigationBar2 = findView(R.id.resource_detail_navigation_bar2);
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);

        root_view = findView(R.id.root_view);
        ll_zhengsi_vip = findView(R.id.ll_zhengsi_vip);
        int type = getIntent().getIntExtra("type", 0);
        String memberId = getIntent().getStringExtra("memberId");

        switch (type) {
            case 1:
                ll_zhengsi_vip.setVisibility(View.VISIBLE);
                break;
            case 2:
                ll_zhengsi_vip.setVisibility(View.GONE);
                break;
            case 3:
                ll_zhengsi_vip.setVisibility(View.GONE);
                break;

        }

        selectCoachPopupWindow = new SelectCoachPopupWindow(this);
        selectCoachPopupWindow.setSelectCoachListener(new SelectCoachPopupWindow.SelectCoachListener() {
            @Override
            public void onSelect(CoachInfo coachInfo) {
                //TODO 分配教练请求
                Logger.i("ResourceDetailActivity", "coachInfo" + coachInfo.toString());

            }
        });

    }


    @OnClick(R.id.ll_send_coach)
    public void onViewClicked() {

        selectCoachPopupWindow.showAtLocation(root_view);
    }
}
