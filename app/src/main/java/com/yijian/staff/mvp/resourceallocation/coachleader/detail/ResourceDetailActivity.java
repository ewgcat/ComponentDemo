package com.yijian.staff.mvp.resourceallocation.coachleader.detail;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.resourceallocation.coachleader.selectcoach.CoachInfo;
import com.yijian.staff.mvp.resourceallocation.coachleader.selectcoach.SelectCoachPopupWindow;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.Logger;
import com.yijian.staff.widget.NavigationBar;

import org.json.JSONObject;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResourceDetailActivity extends MvcBaseActivity {


    private LinearLayout root_view;
    private LinearLayout ll_zhengsi_vip;
    private LinearLayout ll_intent_vip;
    private LinearLayout ll_potential_vip;
    private SelectCoachPopupWindow selectCoachPopupWindow;
    private String memberId;
    private String subclassName;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_resource_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        NavigationBar NavigationBar = findView(R.id.resource_detail_navigation_bar2);
        NavigationBar.hideLeftSecondIv();
        NavigationBar.setBackClickListener(this);

        root_view = findView(R.id.root_view);
        ll_zhengsi_vip = findView(R.id.ll_zhengsi_vip);
        ll_intent_vip = findView(R.id.ll_intent_vip);
        ll_potential_vip = findView(R.id.ll_potential_vip);
        int type = getIntent().getIntExtra("type", 0);
        memberId = getIntent().getStringExtra("memberId");
        subclassName = getIntent().getStringExtra("subclassName");

        Logger.i("ResourceDetailActivity", "memberId=" + memberId);
        switch (type) {
            case 1:
                ll_zhengsi_vip.setVisibility(View.VISIBLE);
                ll_intent_vip.setVisibility(View.GONE);
                ll_potential_vip.setVisibility(View.GONE);
                initZhengshi();
                break;
            case 2:
                ll_intent_vip.setVisibility(View.VISIBLE);
                ll_zhengsi_vip.setVisibility(View.GONE);
                ll_potential_vip.setVisibility(View.GONE);
                initIntent();
                break;
            case 3:
                ll_potential_vip.setVisibility(View.VISIBLE);
                ll_zhengsi_vip.setVisibility(View.GONE);
                ll_intent_vip.setVisibility(View.GONE);
                initPotential();
                break;

        }

        selectCoachPopupWindow = new SelectCoachPopupWindow(getLifecycle(),this);
        selectCoachPopupWindow.setSelectCoachListener(new SelectCoachPopupWindow.SelectCoachListener() {
            @Override
            public void onSelect(CoachInfo coachInfo) {

                post(coachInfo);
            }
        });

    }

    private void initPotential() {
        ImageView iv_header = ll_potential_vip.findViewById(R.id.iv_header);
        TextView tv_name = ll_potential_vip.findViewById(R.id.tv_name);
        ImageView iv_gender = ll_potential_vip.findViewById(R.id.iv_gender);
        TextView tv_birthday = ll_potential_vip.findViewById(R.id.tv_birthday);
        TextView tv_birthday_type = ll_potential_vip.findViewById(R.id.tv_birthday_type);
        TextView tv_body_status = ll_potential_vip.findViewById(R.id.tv_body_status);
        TextView tv_fitness_hobby = ll_potential_vip.findViewById(R.id.tv_fitness_hobby);
        TextView tv_hobby = ll_potential_vip.findViewById(R.id.tv_hobby);
        TextView tv_car_name = ll_potential_vip.findViewById(R.id.tv_car_name);

    }

    private void initIntent() {
        ImageView iv_header = ll_intent_vip.findViewById(R.id.iv_header);
        TextView tv_name = ll_intent_vip.findViewById(R.id.tv_name);
        ImageView iv_gender = ll_intent_vip.findViewById(R.id.iv_gender);
        RecyclerView rv_card = ll_intent_vip.findViewById(R.id.rv_card);
        TextView tv_huiji = ll_intent_vip.findViewById(R.id.tv_huiji);
        TextView tv_tiyanke_count = ll_intent_vip.findViewById(R.id.tv_tiyanke_count);
        TextView tv_first_class_record = ll_intent_vip.findViewById(R.id.tv_first_class_record);
        TextView tv_second_class_record = ll_intent_vip.findViewById(R.id.tv_second_class_record);
        TextView tv_like_lesson = ll_intent_vip.findViewById(R.id.tv_like_lesson);
        TextView tv_like_teacher = ll_intent_vip.findViewById(R.id.tv_like_teacher);
        TextView tv_regist_time = ll_intent_vip.findViewById(R.id.tv_regist_time);
        TextView tv_contract_deadline = ll_intent_vip.findViewById(R.id.tv_contract_deadline);
        TextView tv_contract_balance = ll_intent_vip.findViewById(R.id.tv_contract_balance);
        TextView tv_buy_count = ll_intent_vip.findViewById(R.id.tv_buy_count);

    }

    private void initZhengshi() {
        ImageView iv_header = ll_zhengsi_vip.findViewById(R.id.iv_header);
        TextView tv_name = ll_zhengsi_vip.findViewById(R.id.tv_name);
        ImageView iv_gender = ll_zhengsi_vip.findViewById(R.id.iv_gender);
        RecyclerView rv_card = ll_zhengsi_vip.findViewById(R.id.rv_card);
        TextView tv_private_lesson = ll_zhengsi_vip.findViewById(R.id.tv_private_lesson);
        TextView tv_like_lesson = ll_zhengsi_vip.findViewById(R.id.tv_like_lesson);
        TextView tv_like_teacher = ll_zhengsi_vip.findViewById(R.id.tv_like_teacher);
        TextView tv_regist_time = ll_zhengsi_vip.findViewById(R.id.tv_regist_time);
        TextView tv_contract_deadline = ll_zhengsi_vip.findViewById(R.id.tv_contract_deadline);
        TextView tv_contract_balance = ll_zhengsi_vip.findViewById(R.id.tv_contract_balance);
        TextView tv_buy_count = ll_zhengsi_vip.findViewById(R.id.tv_buy_count);
    }

    private void post(CoachInfo coachInfo) {
        HashMap<String, String> param = new HashMap<>();
        param.put("memberId", memberId);
        param.put("distributedUserId", coachInfo.getUserId());
        param.put("subclassName", subclassName);
        HttpManager.getHasHeaderHasParam(HttpManager.COACHZONGJIAN_DISTRIBUTE_RESOURCE_URL, param, new ResultJSONObjectObserver(getLifecycle()) {
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
