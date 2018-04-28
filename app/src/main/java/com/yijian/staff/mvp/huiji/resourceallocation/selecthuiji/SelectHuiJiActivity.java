package com.yijian.staff.mvp.huiji.resourceallocation.selecthuiji;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.huiji.resourceallocation.bean.HuijiInfo;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.util.Logger;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.NavigationBarItemFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 选择会籍
 */
public class SelectHuiJiActivity extends AppCompatActivity {

    private static String titleCenter;
    public static void startToActivity(String title, Context context){
        titleCenter = title;
        Intent intent = new Intent(context,SelectHuiJiActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rev_select_huiji)
    RecyclerView rev_select_huiji;
    private List<HuijiInfo> huijiInfoList=new ArrayList<>();

    @BindView(R.id.tv_comment_order)
    TextView tv_comment_order;  //评分排序
    @BindView(R.id.tv_business_order)
    TextView tv_business_order;   //业绩排序
    @BindView(R.id.tv_letter_order)
    TextView tv_letter_order; //字母排序

    @BindView(R.id.iv_comment_order_up)
    ImageView iv_comment_order_up;
    @BindView(R.id.iv_comment_order_down)
    ImageView iv_comment_order_down;

    @BindView(R.id.iv_business_order_up)
    ImageView iv_business_order_up;
    @BindView(R.id.iv_business_order_down)
    ImageView iv_business_order_down;

    @BindView(R.id.iv_letter_order_up)
    ImageView iv_letter_order_up;
    @BindView(R.id.iv_letter_order_down)
    ImageView iv_letter_order_down;


    private int orderResId; //排序ID
    private boolean isDown = true; //箭头是否向下的标识位

    private int pageNum = 1;
    private int pageSize = 1;
    private int pages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_hui_ji);
        ButterKnife.bind(this);
        initTitle();
        initView();
//        initHuijiInfoList();
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = findViewById(R.id.vip_over_navigation_bar2);
        navigationBar2.setTitle(titleCenter);
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setmRightTvText("确定");
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //提交选择的会籍

                /*Map<String,String> map = new HashMap<>();
                map.put("memberId",);
                map.put("distributedUserId",);
                map.put("subclassName",);
                HttpManager.getHasHeaderHasParam(HttpManager.ALLOCATION_HUIJI_RESOURCE_ALLOCATION_URL, map, new ResultJSONObjectObserver() {
                    @Override
                    public void onSuccess(JSONObject result) {

                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });*/

            }
        });
    }

    private void initView() {
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        rev_select_huiji.setLayoutManager(layoutmanager);
        SelectHuiJiAdapter historyResourceAllocationAdatper = new SelectHuiJiAdapter(this, huijiInfoList);
        rev_select_huiji.setAdapter(historyResourceAllocationAdatper);
        initComponent();
    }

    /*private void initHuijiInfoList() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("headerUrl", "");
            jsonObject.put("name", "张三三");
            jsonObject.put("name", "1");
            jsonObject.put("commentGrade", "3.5");
            jsonObject.put("businessProgress", "0.8");
            jsonObject.put("actionTargetProgress", "0.5");
            jsonObject.put("vipServiceNum", "150");
            jsonObject.put("potentialServiceNum", "260");
            for (int i = 0; i < 20; i++) {
                HuijiInfo huijiInfo = new HuijiInfo(jsonObject);
                huijiInfoList.add(huijiInfo);
            }


            LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
            //设置RecyclerView 布局
            rev_select_huiji.setLayoutManager(layoutmanager);
            com.yijian.staff.mvp.resourceallocation.selecthuiji.SelectHuiJiAdapter historyResourceAllocationAdatper = new com.yijian.staff.mvp.resourceallocation.selecthuiji.SelectHuiJiAdapter(this, huijiInfoList);
            rev_select_huiji.setAdapter(historyResourceAllocationAdatper);
        } catch (JSONException e) {
            Logger.i("TEST", "JSONException: " + e);

        }
    }*/

    @OnClick({R.id.lin_comment_order,R.id.lin_businees_order,R.id.lin_letter_order})
    public void click(View v){
        switch(v.getId()){
            case R.id.lin_comment_order: //评分排序
                setTopBarStyle(R.id.lin_comment_order);
                break;
            case R.id.lin_businees_order: //业绩排序
                setTopBarStyle(R.id.lin_businees_order);
                break;
            case R.id.lin_letter_order: //字母排序
                setTopBarStyle(R.id.lin_letter_order);
                break;
        }
    }

    private void setTopBarStyle(int selectId) {
        tv_comment_order.setTextColor(Color.parseColor("#666666"));
        tv_business_order.setTextColor(Color.parseColor("#666666"));
        tv_letter_order.setTextColor(Color.parseColor("#666666"));

        iv_comment_order_up.setImageResource(R.mipmap.fp_shang);
        iv_comment_order_down.setImageResource(R.mipmap.fp_xia);
        iv_business_order_up.setImageResource(R.mipmap.fp_shang);
        iv_business_order_down.setImageResource(R.mipmap.fp_xia);
        iv_letter_order_up.setImageResource(R.mipmap.fp_shang);
        iv_letter_order_down.setImageResource(R.mipmap.fp_xia);

        switch(selectId){
            case R.id.lin_comment_order: //评分排序
                tv_comment_order.setTextColor(Color.parseColor("#1997f8"));
                if(selectId == orderResId){
                    iv_comment_order_up.setImageResource(isDown?R.mipmap.fp_shang:R.mipmap.fp_shanglan);
                    iv_comment_order_down.setImageResource(isDown?R.mipmap.fp_xialan:R.mipmap.fp_xia);
                }else{
                    iv_comment_order_up.setImageResource(R.mipmap.fp_shang);
                    iv_comment_order_down.setImageResource(R.mipmap.fp_xialan);
                }
                isDown = !isDown;
                break;
            case R.id.lin_businees_order: //业绩排序
                tv_business_order.setTextColor(Color.parseColor("#1997f8"));
                if(selectId == orderResId){
                    iv_business_order_up.setImageResource(isDown?R.mipmap.fp_shang:R.mipmap.fp_shanglan);
                    iv_business_order_down.setImageResource(isDown?R.mipmap.fp_xialan:R.mipmap.fp_xia);
                }else{
                    iv_business_order_up.setImageResource(R.mipmap.fp_shang);
                    iv_business_order_down.setImageResource(R.mipmap.fp_xialan);
                }
                isDown = !isDown;
                break;
            case R.id.lin_letter_order: //字母排序
                tv_letter_order.setTextColor(Color.parseColor("#1997f8"));
                if(selectId == orderResId){
                    iv_letter_order_up.setImageResource(isDown?R.mipmap.fp_shang:R.mipmap.fp_shanglan);
                    iv_letter_order_down.setImageResource(isDown?R.mipmap.fp_xialan:R.mipmap.fp_xia);
                }else{
                    iv_letter_order_up.setImageResource(R.mipmap.fp_shang);
                    iv_letter_order_down.setImageResource(R.mipmap.fp_xialan);
                }
                isDown = !isDown;
                break;
        }
        orderResId = selectId;
    }

    public void initComponent() {
        //设置 Header 为 BezierRadar 样式
        BezierRadarHeader header = new BezierRadarHeader(this).setEnableHorizontalDrag(true);
        header.setPrimaryColor(Color.parseColor("#1997f8"));
        refreshLayout.setRefreshHeader(header);
        //设置 Footer 为 球脉冲
        BallPulseFooter footer = new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale);
        footer.setAnimatingColor(Color.parseColor("#1997f8"));
        refreshLayout.setRefreshFooter(footer);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 1;
                pageSize = 1;
                refresh();
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
            }
        });
    }


    private void refresh() {
        Map<String, String> params = new HashMap<>();

        params.put("pageNum", pageNum + "");
        params.put("pageSize", pageSize + "");

        HttpManager.getHasHeaderHasParam(HttpManager.GET_HUIJI_ENABLE_RECEIVE_RESOURCE_ALLOCATION__PHONE_URL,params, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                refreshLayout.finishRefresh(2000, true);
                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");
            }

            @Override
            public void onFail(String msg) {
                refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                Toast.makeText(SelectHuiJiActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void loadMore() {

        Map<String, String> params = new HashMap<>();

        params.put("pageNum", pageNum + "");
        params.put("pageSize", pageSize + "");

        HttpManager.getHasHeaderHasParam(HttpManager.GET_HUIJI_ENABLE_RECEIVE_RESOURCE_ALLOCATION__PHONE_URL,params, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");
                boolean hasMore = pages > pageNum ? true : false;
                refreshLayout.finishLoadMore(2000, true, hasMore);//传入false表示刷新失败
            }

            @Override
            public void onFail(String msg) {
                boolean hasMore = pages > pageNum ? true : false;
                refreshLayout.finishLoadMore(2000, false, hasMore);//传入false表示刷新失败
                Toast.makeText(SelectHuiJiActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
