package com.yijian.staff.mvp.resourceallocation.huijileader.detail;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.resourceallocation.huijileader.selecthuiji.HuiJiInfo;
import com.yijian.staff.mvp.resourceallocation.huijileader.selecthuiji.SelectHuiJiPopupWindow;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.Logger;
import com.yijian.staff.widget.NavigationBar;

import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

public class ResourceDetailActivity extends MvcBaseActivity {


    private LinearLayout root_view;
    private LinearLayout ll_zhengsi_vip;
    private LinearLayout ll_intent_vip;
    private SelectHuiJiPopupWindow selectCoachPopupWindow;
    private String memberId;
    private String subclassName;

    /************ 正式会员 *************/

    @BindView(R.id.rv_card)
    RecyclerView rv_card; //卡列表
    @BindView(R.id.tv_private_coach)
    TextView tv_private_coach; //私教教练
    @BindView(R.id.tv_like_lesson)
    TextView tv_like_lesson; //喜欢课程
    @BindView(R.id.tv_like_teacher)
    TextView tv_like_teacher; //喜欢老师
    @BindView(R.id.tv_regist_time)
    TextView tv_regist_time; //注册时间
    @BindView(R.id.tv_contract_deadline)
    TextView tv_contract_deadline; //tv_contract_deadline
    @BindView(R.id.tv_contract_balance)
    TextView tv_contract_balance; //合同余额
    @BindView(R.id.tv_buy_count)
    TextView tv_buy_count; //购买次数


    /************* 意向/潜在会员 **************/
    @BindView(R.id.iv_header)
    ImageView iv_header;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.iv_gender)
    ImageView iv_gender;
    @BindView(R.id.tv_birthDay)
    TextView tv_birthDay; // 生日
    @BindView(R.id.tv_birthday_type)
    TextView tv_birthday_type; // 生日类型
    @BindView(R.id.tv_body_status)
    TextView tv_body_status; // 身体状态
    @BindView(R.id.tv_building_hobby)
    TextView tv_building_hobby; //健身爱好
    @BindView(R.id.tv_intersting)
    TextView tv_intersting; //兴趣爱好
    @BindView(R.id.tv_usecar)
    TextView tv_usecar; // 使用车辆


    @Override
    protected int getLayoutID() {
        return R.layout.activity_huiji_resource_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        NavigationBar NavigationBar = findView(R.id.resource_detail_navigation_bar2);
        NavigationBar.hideLeftSecondIv();
        NavigationBar.setBackClickListener(this);

        root_view = findView(R.id.root_view);
        ll_zhengsi_vip = findView(R.id.ll_zhengsi_vip);
        ll_intent_vip = findView(R.id.ll_intent_vip);
        int type = getIntent().getIntExtra("type", 0);
        memberId = getIntent().getStringExtra("memberId");
        subclassName = getIntent().getStringExtra("subclassName");

        Logger.i("ResourceDetailActivity", "memberId=" + memberId);
        switch (type) {
            case 1: // 正式会员
                ll_zhengsi_vip.setVisibility(View.VISIBLE);
                ll_intent_vip.setVisibility(View.GONE);
                updateZhengshiUi();
                break;
            case 2: //意向会员
            case 3: //潜在会员
                ll_zhengsi_vip.setVisibility(View.GONE);
                ll_intent_vip.setVisibility(View.VISIBLE);
                updateIntentUi();
                break;
        }

        selectCoachPopupWindow = new SelectHuiJiPopupWindow(getLifecycle(),this);
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
    private void updateZhengshiUi() {


    }

    /**
     * 更新意向会员信息
     */
    private void updateIntentUi() {

    }

    private void post(HuiJiInfo coachInfo) {
        HashMap<String, String> param = new HashMap<>();
        param.put("memberId", memberId);
        param.put("distributedUserId", coachInfo.getUserId());
        param.put("subclassName", subclassName);
        HttpManager.getHasHeaderHasParam(HttpManager.HUIJIZONGJIAN_DISTRIBUTE_RESOURCE_URL, param, new ResultJSONObjectObserver(getLifecycle()) {
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
