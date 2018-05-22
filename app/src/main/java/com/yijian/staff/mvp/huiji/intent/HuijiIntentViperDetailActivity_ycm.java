package com.yijian.staff.mvp.huiji.intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.huiji.bean.VipDetailBean;
import com.yijian.staff.mvp.huiji.detail.AdapterHuijiViper;
import com.yijian.staff.mvp.huiji.edit.HuiJiVipInfoEditActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.DensityUtil;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by The_P on 2018/5/16.
 */

public class HuijiIntentViperDetailActivity_ycm extends MvcBaseActivity implements View.OnClickListener, AdapterHuijiIntentViper.AdapterInterface {
    private static final String TAG = "HuijiIntentViperDetailA";
    private LinearLayout llHead;
    private RelativeLayout rlItem0;
    private RelativeLayout rlItem1;
    private ImageView ivItem0;
    private ImageView ivItem1;
    private TextView tvItem0;
    private TextView tvItem1;
    private AdapterHuijiIntentViper adapter;
    private VipDetailBean vipDetailBean;
    private RecyclerView recyclerView;
    private String id;
//    private String memberName;
    private NavigationBar2 navigation2;

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_huiji_intent_viper_ycm);
//        id = getIntent().getStringExtra("id");
//        initView();
//        initData();
//
//    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        id = getIntent().getStringExtra("id");
        initview();
        initData();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_huiji_intent_viper_ycm;
    }

    private void initData() {
        showBlueProgress();
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);

        HttpManager.getHasHeaderHasParam(HttpManager.GET_VIPER_DETAIL_URL, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                hideBlueProgress();
                  vipDetailBean = com.alibaba.fastjson.JSONObject.parseObject(result.toString(), VipDetailBean.class);
//                updateUi(vipDetailBean);
                if (!TextUtils.isEmpty(vipDetailBean.getName()))navigation2.setTitle(vipDetailBean.getName());
                adapter.setData(vipDetailBean);
            }

            @Override
            public void onFail(String msg) {
                hideBlueProgress();
                Toast.makeText(HuijiIntentViperDetailActivity_ycm.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initview() {
        navigation2 = findViewById(R.id.navigation_bar2);
        navigation2.setTitle("会员详情");
        navigation2.getmTitleView().setAlpha(0.0f);
        navigation2.getmTitleView().setVisibility( View.GONE);
        navigation2.setSecondLeftIvVisiable(View.GONE);
        navigation2.setBackClickListener(this);


        llHead = findViewById(R.id.ll_head);
        rlItem0 = findViewById(R.id.rl_item0);
        rlItem1 = findViewById(R.id.rl_item1);


        ivItem0 = findViewById(R.id.iv_item0);
        ivItem1 = findViewById(R.id.iv_item1);


        tvItem0 = findViewById(R.id.tv_item0);
        tvItem1 = findViewById(R.id.tv_item1);
        llHead.setVisibility(View.GONE);

        rlItem0.setOnClickListener(this);
        rlItem1.setOnClickListener(this);


        recyclerView = findViewById(R.id.recycler_view);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new AdapterHuijiIntentViper(this);
        recyclerView.setAdapter(adapter);


        adapter.setAdapterInterface(this);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState==RecyclerView.SCROLL_STATE_IDLE){
                    manualMove=false;
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
        switch (v.getId()){

            case R.id.rl_item0:
                moveToPosition(1);
                itemSelecte(0);
                break;
            case R.id.rl_item1:
                moveToPosition(2);
                itemSelecte(1);
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

        if (n<firstItem){
            layoutManager.scrollToPositionWithOffset(n, headHeight );

        }else if (n==firstItem||n<=lastItem){
            View viewByPosition = layoutManager.findViewByPosition(n);
            recyclerView.scrollBy(0,viewByPosition.getTop()-(headHeight+ DensityUtil.dip2px(this,16)));
        }else {
            layoutManager.scrollToPositionWithOffset(n, headHeight);
        }
    }

    @Override
    public void clickVisit() {
        String mobile = vipDetailBean.getMobile();
        if (!TextUtils.isEmpty(mobile)){
//            callVisit(mobile);
            CommonUtil.callPhone(HuijiIntentViperDetailActivity_ycm.this,mobile);
        } else {
            Toast.makeText(this,"未录入手机号,无法进行电话回访",Toast.LENGTH_SHORT).show();
        }
    }

//    private void callVisit(String mobile){
//        Map<String,String> map = new HashMap<>();
//        map.put("memberId",vipDetailBean.getMemberId());
//        map.put("dictItemKey",getIntent().getIntExtra("dictItemKey",0)+"");
//        HttpManager.getHasHeaderHasParam(HttpManager.HUIJI_HUIFANG_CALL_RECORD, map, new ResultJSONObjectObserver() {
//            @Override
//            public void onSuccess(JSONObject result) {
//                CommonUtil.callPhone(HuijiIntentViperDetailActivity_ycm.this,mobile);
//            }
//
//            @Override
//            public void onFail(String msg) {
//                Toast.makeText(HuijiIntentViperDetailActivity_ycm.this, msg, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    @Override
    public void clickEdit() {
        Intent intent = new Intent(HuijiIntentViperDetailActivity_ycm.this, HuiJiVipInfoEditActivity.class);
        intent.putExtra("detail", vipDetailBean.getDetail());
        intent.putExtra("memberId", vipDetailBean.getMemberId());
        intent.putExtra("source", vipDetailBean.getCustomerServiceInfo().getUserChannel());
        intent.putExtra("name", vipDetailBean.getName());
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
