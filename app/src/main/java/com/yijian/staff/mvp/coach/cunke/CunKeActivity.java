package com.yijian.staff.mvp.coach.cunke;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.coach.cunke.bean.TypeOfCunKeBody;
import com.yijian.staff.mvp.huiji.bean.HuiJiViperBean;
import com.yijian.staff.mvp.huiji.intent.HuijiIntentViperListActivity;
import com.yijian.staff.mvp.setclass.AdapterLesson;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.MDividerItemDecoration;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = "/test/16")
public class CunKeActivity extends AppCompatActivity {

    @BindView(R.id.rc_ck)
    RecyclerView rc_ck;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    CunKeAdapter cunKeAdapter;
    List<Object> bodyList = new ArrayList<Object>();
    private int pageNum = 1;//页码
    private int pageSize = 1;//每页数量
    private int pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cun_ke);
        ButterKnife.bind(this);

        initTitle();
        initView();
        initData();
    }

    private void initTitle(){
        NavigationBar2   navigationBar2 = (NavigationBar2) findViewById(R.id.cun_ke_navigation_bar2);
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("私教课");
    }

    private void initView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rc_ck.setLayoutManager(linearLayoutManager);
        //添加Android自带的分割线
        MDividerItemDecoration decor = new MDividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        decor.setDrawable(getDrawable(R.drawable.divider_recyclerview));
        rc_ck.addItemDecoration(decor);
        rc_ck.setNestedScrollingEnabled(false);
        cunKeAdapter = new CunKeAdapter();
        rc_ck.setAdapter(cunKeAdapter);
        initComponent();
    }

    private void initData() {
       /* bodyList.add(new TypeOfCunKeBody("小二","减肥课","10节","1节"));
        bodyList.add(new TypeOfCunKeBody("小三","减肥课2","9节","2节"));
        bodyList.add(new TypeOfCunKeBody("小四","减肥课3","8节","3节"));

        cunKeAdapter.resetDataList(bodyList);*/
       refresh();
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
        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum", 1 + "");
        map.put("pageSize", 1 + "");

        HttpManager.postHasHeaderHasParam(HttpManager.COACH_PRIVATE_COURSE_STOCK_PRIVATE_LIST_URL, map, new ResultObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                refreshLayout.finishRefresh(2000, true);

                bodyList.clear();
                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                for (int i = 0; i < records.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        TypeOfCunKeBody typeOfCunKeBody = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.toString(),TypeOfCunKeBody.class);
                        bodyList.add(typeOfCunKeBody);
                        cunKeAdapter.resetDataList(bodyList);
                    } catch (JSONException e) {


                    }
                }
                cunKeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
                refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                Toast.makeText(CunKeActivity.this,msg,Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void loadMore() {

        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum", pageNum + "");
        map.put("pageSize", pageSize + "");

        HttpManager.getHasHeaderHasParam(HttpManager.GET_HUIJI_INTENT_VIPER_LIST_URL, map, new ResultObserver() {
            @Override
            public void onSuccess(JSONObject result) {

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");

                boolean hasMore = pages > pageNum ? true : false;
                refreshLayout.finishLoadMore(2000, true, hasMore);//传入false表示刷新失败

                JSONArray records = JsonUtil.getJsonArray(result, "records");
                for (int i = 0; i < records.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        TypeOfCunKeBody typeOfCunKeBody = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.toString(),TypeOfCunKeBody.class);
                        bodyList.add(typeOfCunKeBody);
                    } catch (JSONException e) {
                    }
                }
                cunKeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
                boolean hasMore = pages > pageNum ? true : false;
                refreshLayout.finishLoadMore(2000, false, hasMore);//传入false表示刷新失败
                Toast.makeText(CunKeActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

}
