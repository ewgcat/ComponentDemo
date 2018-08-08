package com.yijian.staff.mvp.vipermanage.viper.detail.protentialorintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.bean.ViperDetailBean;
import com.yijian.staff.mvp.huifang.huiji.invitation.index.InvateIndexActivity;
import com.yijian.staff.mvp.vipermanage.viper.detail.formatoroutdate.HuiJiViperDetailActivity;
import com.yijian.staff.mvp.vipermanage.viper.edit.HuiJiVipInfoEditActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.DensityUtil;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by The_P on 2018/5/16.
 */

public class ProtentialOrIntentViperDetailActivity extends MvcBaseActivity implements View.OnClickListener, ProtentialOrIntentViperDetailAdapter.AdapterInterface {
    private static final String TAG = "ProtentialOrIntentViperDetailActivity";
    private LinearLayout llHead, ll_invite;
    private RelativeLayout rlItem0;
    private RelativeLayout rlItem1;
    private ImageView ivItem0;
    private ImageView ivItem1;
    private TextView tvItem0;
    private TextView tvItem1;
    private ProtentialOrIntentViperDetailAdapter adapter;
    private ViperDetailBean viperDetailBean;
    private RecyclerView recyclerView;
    private String memberId;
    private String subclassName;
    private String memberType;
    private NavigationBar2 navigation2;


    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        memberId = getIntent().getStringExtra("memberId");
        subclassName = getIntent().getStringExtra("subclassName");

        initview();

        if (subclassName.equals("CustomerInfoVO")) { //正式会员
            memberType = "正式会员";
            ll_invite.setVisibility(View.GONE);
        } else if (subclassName.equals("PotentialVO")) {//潜在会员
            memberType = "潜在会员";
            ll_invite.setVisibility(View.VISIBLE);
        } else if (subclassName.equals("CustomerIntentionVO")) {//意向会员
            memberType = "意向会员";
            ll_invite.setVisibility(View.VISIBLE);
        } else if (subclassName.equals("CustomerExpireVO")) {//过期会员
            memberType = "过期会员";
            ll_invite.setVisibility(View.VISIBLE);
        }
        initData();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_huiji_intent_viper_ycm;
    }

    private void initData() {
        showLoading();
        HashMap<String, String> map = new HashMap<>();
        map.put("id", memberId);

        HttpManager.getHasHeaderHasParam(HttpManager.GET_VIPER_DETAIL_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();
                viperDetailBean = com.alibaba.fastjson.JSONObject.parseObject(result.toString(), ViperDetailBean.class);
//                updateUi(viperDetailBean);
                if (!TextUtils.isEmpty(viperDetailBean.getName()))
                    navigation2.setTitle(viperDetailBean.getName());
                adapter.setData(viperDetailBean);
            }

            @Override
            public void onFail(String msg) {
                hideLoading();
                Toast.makeText(ProtentialOrIntentViperDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initview() {
        navigation2 = findViewById(R.id.navigation_bar2);
        navigation2.setTitle("会员详情");
        navigation2.getmTitleView().setAlpha(0.0f);
        navigation2.getmTitleView().setVisibility(View.GONE);
        navigation2.setSecondLeftIvVisiable(View.GONE);
        navigation2.setBackClickListener(this);


        llHead = findViewById(R.id.ll_head);
        ll_invite = findViewById(R.id.ll_invite);
        rlItem0 = findViewById(R.id.rl_item0);
        rlItem1 = findViewById(R.id.rl_item1);


        ivItem0 = findViewById(R.id.iv_item0);
        ivItem1 = findViewById(R.id.iv_item1);


        tvItem0 = findViewById(R.id.tv_item0);
        tvItem1 = findViewById(R.id.tv_item1);
        llHead.setVisibility(View.GONE);

        rlItem0.setOnClickListener(this);
        rlItem1.setOnClickListener(this);

        ll_invite.setOnClickListener(this);

        recyclerView = findViewById(R.id.recycler_view);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ProtentialOrIntentViperDetailAdapter(this);
        recyclerView.setAdapter(adapter);


        adapter.setAdapterInterface(this);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    manualMove = false;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (!manualMove) {//不是用户通过点击进行的移动
//                    Log.e(TAG, "onScrolled: manualMove==" + manualMove);
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                    View firstView = layoutManager.findViewByPosition(0);
                    int headHeight = llHead.getHeight();//头部高度
                    int i = recyclerView.computeVerticalScrollOffset();
//                    Log.e(TAG, "onScrolled: computeVerticalScrollOffset==" + i);
                    if (firstView != null) {
                        llHead.setVisibility(i > 30 ? View.VISIBLE : View.GONE);
                        navigation2.getmTitleView().setVisibility(i > 30 ? View.VISIBLE : View.GONE);
                        if (firstView.getHeight() != 0) {
                            float alpha = (i / (headHeight * 1.0f));
                            llHead.setAlpha(alpha);
                            navigation2.getmTitleView().setAlpha(alpha);
                        }
                    }

                    View viewByPosition = layoutManager.findViewByPosition(firstVisibleItemPosition);
                    if (dy > 0) {//向上滚动
                        if (firstVisibleItemPosition == 0 && headHeight != 0) {
                            if (viewByPosition.getBottom() - headHeight < 0) {
                                if (!tvItem0.isSelected()) itemSelecte(0);
                            }
                        } else if (firstVisibleItemPosition == 1 && headHeight != 0) {
                            if (viewByPosition.getBottom() - headHeight < 0) {
                                if (!tvItem1.isSelected()) itemSelecte(1);
                            }
                        }
                    } else {//向下滚动
                        if (firstVisibleItemPosition == 0) {
                            resetState();
                        } else if (firstVisibleItemPosition == 1) {
                            if (!tvItem0.isSelected()) itemSelecte(0);
                        } else if (firstVisibleItemPosition == 2) {
                            if (!tvItem1.isSelected()) itemSelecte(1);
                        }
                    }
                }


            }
        });
    }

    private void resetState() {
        tvItem0.setTextColor(getResources().getColor(R.color.text_black2));
        tvItem1.setTextColor(getResources().getColor(R.color.text_black2));

        ivItem0.setVisibility(View.INVISIBLE);
        ivItem1.setVisibility(View.INVISIBLE);

        tvItem0.setSelected(false);
        tvItem1.setSelected(false);
    }


    private void itemSelecte(int position) {
        resetState();
        if (position == 0) {//基本信息
            tvItem0.setSelected(true);
            tvItem0.setTextColor(getResources().getColor(R.color.blue));
            ivItem0.setVisibility(View.VISIBLE);
        } else if (position == 1) {//详细信息
            tvItem1.setSelected(true);
            tvItem1.setTextColor(getResources().getColor(R.color.blue));
            ivItem1.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.rl_item0:
                moveToPosition(1);
                itemSelecte(0);
                break;
            case R.id.rl_item1:
                moveToPosition(2);
                itemSelecte(1);
                break;
            case R.id.ll_invite:

                Intent intent3 = new Intent(ProtentialOrIntentViperDetailActivity.this, InvateIndexActivity.class);
                intent3.putExtra("memberId", memberId);
                intent3.putExtra("memberType", memberType);
                startActivity(intent3);
                break;


        }
    }

    private boolean manualMove;//手动移动——指通过点击的移动

    private void moveToPosition(int n) {
        llHead.setAlpha(1.0f);
        manualMove = true;
        //先从RecyclerView的LayoutManager中获取第一项和最后一项的Position
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int firstItem = layoutManager.findFirstVisibleItemPosition();
        int lastItem = layoutManager.findLastVisibleItemPosition();
        int headHeight = llHead.getHeight();//头部高度

        if (n < firstItem) {
            layoutManager.scrollToPositionWithOffset(n, headHeight);

        } else if (n == firstItem || n <= lastItem) {
            View viewByPosition = layoutManager.findViewByPosition(n);
            recyclerView.scrollBy(0, viewByPosition.getTop() - (headHeight + DensityUtil.dip2px(this, 16)));
        } else {
            layoutManager.scrollToPositionWithOffset(n, headHeight);
        }
    }

    @Override
    public void clickVisit() {
        String mobile = viperDetailBean.getMobile();
        if (!TextUtils.isEmpty(mobile)) {
//            callVisit(mobile);
            CommonUtil.callPhone(ProtentialOrIntentViperDetailActivity.this, mobile);
        } else {
            Toast.makeText(this, "未录入手机号,无法进行电话回访", Toast.LENGTH_SHORT).show();
        }
    }

//    private void callVisit(String mobile){
//        Map<String,String> map = new HashMap<>();
//        map.put("memberId",viperDetailBean.getMemberId());
//        map.put("dictItemKey",getIntent().getIntExtra("dictItemKey",0)+"");
//        HttpManager.getHasHeaderHasParam(HttpManager.HUIJI_HUIFANG_CALL_RECORD, map, new ResultJSONObjectObserver(getLifecycle()) {
//            @Override
//            public void onSuccess(JSONObject result) {
//                CommonUtil.callPhone(ProtentialOrIntentViperDetailActivity.this,mobile);
//            }
//
//            @Override
//            public void onFail(String msg) {
//                Toast.makeText(ProtentialOrIntentViperDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    @Override
    public void clickEdit() {
        Intent intent = new Intent(ProtentialOrIntentViperDetailActivity.this, HuiJiVipInfoEditActivity.class);
        intent.putExtra("detail", viperDetailBean.getDetail());
        intent.putExtra("memberId", viperDetailBean.getMemberId());
        intent.putExtra("source", viperDetailBean.getCustomerServiceInfo().getUserChannel());
        intent.putExtra("name", viperDetailBean.getName());
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 1) {
            initData();
        }
    }
}
