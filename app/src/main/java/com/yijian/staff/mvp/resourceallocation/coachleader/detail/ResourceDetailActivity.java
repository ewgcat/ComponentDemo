package com.yijian.staff.mvp.resourceallocation.coachleader.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.widget.NavigationBar2;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResourceDetailActivity extends MvcBaseActivity {


    private LinearLayout ll_zhengsi_vip;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_resource_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        NavigationBar2 navigationBar2 = findView(R.id.resource_detail_navigation_bar2);
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);

        ll_zhengsi_vip = findView(R.id.ll_zhengsi_vip);
        int type = getIntent().getIntExtra("type", 0);
        String memberId = getIntent().getStringExtra("memberId");

        switch (type){
            case 1:
                ll_zhengsi_vip.setVisibility(View.VISIBLE);
                break;
        }
    }



    @OnClick(R.id.ll_send_coach)
    public void onViewClicked() {
        //TODO 选择教练 分配


    }
}
