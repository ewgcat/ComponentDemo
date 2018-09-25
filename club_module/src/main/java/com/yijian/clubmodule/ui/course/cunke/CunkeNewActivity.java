package com.yijian.clubmodule.ui.course.cunke;

import android.graphics.Color;
import androidx.annotation.NonNull;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.commonlib.util.CommonUtil;
import com.yijian.commonlib.widget.EmptyView;
import com.yijian.commonlib.widget.NavigationBar;
import com.yijian.clubmodule.R;
import com.yijian.clubmodule.net.requestbody.AccessStatisticsRequestBody;
import com.yijian.clubmodule.bean.TypeOfCunKeBody;
import com.yijian.commonlib.base.mvc.MvcBaseActivity;
import com.yijian.clubmodule.net.httpmanager.HttpManager;
import com.yijian.commonlib.net.response.ResultJSONObjectObserver;
import com.yijian.commonlib.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



@Route(path = "/test/16")
public class CunkeNewActivity extends MvcBaseActivity {

    RecyclerView rv;
    SmartRefreshLayout refreshLayout;
    EmptyView empty_view;

    private int pageNum = 1;//页码
    private int pageSize = 10;//每页数量
    private int pagesTotal; //总共多少页
    private List<TypeOfCunKeBody> typeOfCunKeBodyList = new ArrayList<>();
    private CunKeNewAdapter cunKeNewAdapter;



    @Override
    protected int getLayoutID() {
        return R.layout.activity_cunke;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        rv= findViewById(R.id.rv);
        refreshLayout= findViewById(R.id.refreshLayout);
        empty_view= findViewById(R.id.empty_view);
        initTitle();
        initView();
        refresh();
    }

    private void initTitle() {
        NavigationBar navigationBar = findViewById(R.id.navigation_bar);
        navigationBar.setTitle( "存课信息");
        navigationBar .hideLeftSecondIv();
        navigationBar .setBackClickListener(this);
    }

    private void initView(){


        String version = CommonUtil.getAccessStatisticsVersionName(this) + " " + CommonUtil.getVersionCode(this);
        AccessStatisticsRequestBody body=new AccessStatisticsRequestBody("app_storage_course_num",version);
        HttpManager.postAccessStatistics(body, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
        cunKeNewAdapter = new CunKeNewAdapter();
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(cunKeNewAdapter);
        initComponent();
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
                refresh();
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
            }
        });
    }

    private void refresh() {
        typeOfCunKeBodyList.clear();
        pageNum = 1;//页码
        pageSize = 10;
        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum", pageNum + "");
        map.put("pageSize", pageSize + "");
        empty_view.setVisibility(View.GONE);

        HttpManager.postHasHeaderHasParam(HttpManager.COACH_PRIVATE_COURSE_STOCK_PRIVATE_LIST_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                refreshLayout.finishRefresh(2000, true);
                pagesTotal = JsonUtil.getInt(result, "pages");
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                for (int i = 0; i < records.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        TypeOfCunKeBody typeOfCunKeBody = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.toString(), TypeOfCunKeBody.class);
                        typeOfCunKeBodyList.add(typeOfCunKeBody);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                empty_view.setVisibility(typeOfCunKeBodyList.size()>0?View.GONE:View.VISIBLE);
                cunKeNewAdapter.resetDataList(typeOfCunKeBodyList);
            }

            @Override
            public void onFail(String msg) {
                refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                Toast.makeText(CunkeNewActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void loadMore() {

        if(pageNum < pagesTotal){
            pageNum++;
            HashMap<String, String> map = new HashMap<>();
            map.put("pageNum", pageNum + "");
            map.put("pageSize", pageSize + "");

            HttpManager.postHasHeaderHasParam(HttpManager.COACH_PRIVATE_COURSE_STOCK_PRIVATE_LIST_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
                @Override
                public void onSuccess(JSONObject result) {
                    pagesTotal = JsonUtil.getInt(result, "pages");
                    refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败

                    JSONArray records = JsonUtil.getJsonArray(result, "records");
                    for (int i = 0; i < records.length(); i++) {
                        try {
                            JSONObject jsonObject = (JSONObject) records.get(i);
                            TypeOfCunKeBody typeOfCunKeBody = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.toString(), TypeOfCunKeBody.class);
                            typeOfCunKeBodyList.add(typeOfCunKeBody);
                        } catch (JSONException e) {
                        }
                    }
                    cunKeNewAdapter.resetDataList(typeOfCunKeBodyList);
                }

                @Override
                public void onFail(String msg) {

                    refreshLayout.finishLoadMore(2000, false, false);//传入false表示刷新失败
                    Toast.makeText(CunkeNewActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败
        }

    }

}
