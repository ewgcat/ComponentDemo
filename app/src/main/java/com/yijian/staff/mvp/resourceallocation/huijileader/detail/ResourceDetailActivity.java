package com.yijian.staff.mvp.resourceallocation.huijileader.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.resourceallocation.huijileader.selecthuiji.HuiJiInfo;
import com.yijian.staff.mvp.resourceallocation.huijileader.selecthuiji.SelectHuiJiPopupWindow;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.Logger;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONObject;

import java.util.HashMap;

import butterknife.OnClick;

public class ResourceDetailActivity extends MvcBaseActivity {


    private LinearLayout root_view;
    private LinearLayout ll_zhengsi_vip;
    private SelectHuiJiPopupWindow selectCoachPopupWindow;
    private String memberId;
    private String subclassName;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_huiji_resource_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        NavigationBar2 navigationBar2 = findView(R.id.resource_detail_navigation_bar2);
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);

        root_view = findView(R.id.root_view);
        ll_zhengsi_vip = findView(R.id.ll_zhengsi_vip);
        int type = getIntent().getIntExtra("type", 0);
        memberId = getIntent().getStringExtra("memberId");
        subclassName = getIntent().getStringExtra("subclassName");

        Logger.i("ResourceDetailActivity", "memberId=" + memberId);
        switch (type) {
            case 1:
                ll_zhengsi_vip.setVisibility(View.VISIBLE);
                break;
            case 2:
            case 3:
                ll_zhengsi_vip.setVisibility(View.GONE);

                break;

        }

        selectCoachPopupWindow = new SelectHuiJiPopupWindow(this);
        selectCoachPopupWindow.setSelectCoachListener(new SelectHuiJiPopupWindow.SelectCoachListener() {
            @Override
            public void onSelect(HuiJiInfo coachInfo) {

                post(coachInfo);
            }
        });

    }

    /**
     * 更新正式会员信息
     */
    private void updateZhengshiUi(){

    }

    /**
     * 更新意向会员信息
     */
    private void updateIntentUi(){

    }

    private void post(HuiJiInfo coachInfo) {
        HashMap<String, String> param = new HashMap<>();
        param.put("memberId", memberId);
        param.put("distributedUserId", coachInfo.getUserId());
        param.put("subclassName", subclassName);
        HttpManager.getHasHeaderHasParam(HttpManager.HUIJIZONGJIAN_DISTRIBUTE_RESOURCE_URL, param, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                finish();
            }

            @Override
            public void onFail(String msg) {
                showToast(msg);
            }
        });
    }


    @OnClick(R.id.ll_send_coach)
    public void onViewClicked() {

        selectCoachPopupWindow.showAtLocation(root_view);
    }
}
