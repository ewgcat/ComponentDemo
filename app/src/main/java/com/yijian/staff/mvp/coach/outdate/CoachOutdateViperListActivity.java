package com.yijian.staff.mvp.coach.outdate;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
import com.yijian.staff.mvp.coach.bean.CoachViperBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yijian.staff.tab.tools.ContextUtil.getContext;

/**
 * 过期会员列表
 */
@Route(path = "/test/4.1")
public class CoachOutdateViperListActivity extends AppCompatActivity {

    @BindView(R.id.rv_outdate)
    RecyclerView rv_outdate;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int pageNum = 1;//页码
    private int pageSize = 1;//每页数量
    private int pages;


    private List<CoachViperBean> coachViperBeanList = new ArrayList<CoachViperBean>();
    private CoachOutdateViperListAdapter coachOutdateViperListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outdate_viper_list);
        ButterKnife.bind(this);

        initView();
    }



    private void initView() {

        NavigationBar2 navigationBar2 = findViewById(R.id.vip_over_navigation_bar2);
        navigationBar2.setTitle("过期会员");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);

        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        rv_outdate.setLayoutManager(layoutmanager);
        coachOutdateViperListAdapter = new CoachOutdateViperListAdapter(this, coachViperBeanList);
        rv_outdate.setAdapter(coachOutdateViperListAdapter);
        initComponent();
        refresh();


    }

    private void refresh() {
        coachViperBeanList.clear();


        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum", 1+"");
        map.put("pageSize", 1+"");


        HttpManager.getHasHeaderHasParam(HttpManager.GET_COACH_OUTDATE_VIPER_LIST_URL,map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                refreshLayout.finishRefresh(2000, true);


                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                for (int i = 0; i < records.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        CoachViperBean coachViperBean = new CoachViperBean(jsonObject);
                        coachViperBeanList.add(coachViperBean);
                    } catch (JSONException e) {


                    }
                }
                coachOutdateViperListAdapter.update(coachViperBeanList);
            }

            @Override
            public void onFail(String msg) {
                refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void loadMore() {


        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum", pageNum + "");
        map.put("pageSize", pageSize + "");


        HttpManager.getHasHeaderHasParam(HttpManager.GET_COACH_OUTDATE_VIPER_LIST_URL, map, new ResultJSONObjectObserver() {
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
                        CoachViperBean coachViperBean = new CoachViperBean(jsonObject);
                        coachViperBeanList.add(coachViperBean);
                    } catch (JSONException e) {
                    }
                }
                coachOutdateViperListAdapter.update(coachViperBeanList);
            }

            @Override
            public void onFail(String msg) {
                boolean hasMore = pages > pageNum ? true : false;
                refreshLayout.finishLoadMore(2000, false, hasMore);//传入false表示刷新失败
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initComponent() {
        //设置 Header 为 BezierRadar 样式
        BezierRadarHeader header = new BezierRadarHeader(CoachOutdateViperListActivity.this).setEnableHorizontalDrag(true);
        header.setPrimaryColor(Color.parseColor("#1997f8"));
        refreshLayout.setRefreshHeader(header);
        //设置 Footer 为 球脉冲
        BallPulseFooter footer = new BallPulseFooter(CoachOutdateViperListActivity.this).setSpinnerStyle(SpinnerStyle.Scale);
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

}
