package com.yijian.staff.mvp.questionnaire.list;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.bean.QuestionNaireVipBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.util.Logger;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = "/test/12")
public class QuestionListActivity extends MvcBaseActivity {


    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.question_navigation_bar2)
    NavigationBar2 navigationBar2;


    private int pageNum = 1;//页码
    private int pageSize = 2;//每页数量
    private int pages;

    private List<QuestionNaireVipBean> questionNaireVipBeanList = new ArrayList<>();
    private QuestionareAdapter questionareAdapter;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_question_list;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        navigationBar2.setTitle("问卷查询");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        rlv.setLayoutManager(layoutmanager);
        questionareAdapter = new QuestionareAdapter(this, questionNaireVipBeanList);
        rlv.setAdapter(questionareAdapter);
        refresh();
        initComponent();
    }

    private void refresh() {
        pageNum = 1;
        pageSize = 2;
        questionNaireVipBeanList.clear();

        HttpManager.getQuestionnaireList(pageNum, pageSize, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                refreshLayout.finishRefresh(2000, true);

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                try {
                    for (int i = 0; i < records.length(); i++) {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        QuestionNaireVipBean questionNaireVipBean = new QuestionNaireVipBean(jsonObject);
                        questionNaireVipBeanList.add(questionNaireVipBean);
                    }
                } catch (JSONException e) {
                    Logger.i("TEST", e.getMessage());
                }
                questionareAdapter.update(questionNaireVipBeanList);
            }

            @Override
            public void onFail(String msg) {
                refreshLayout.finishRefresh(2000, false);
                showToast(msg);
            }
        });
    }


    public void loadMore() {
        HttpManager.getQuestionnaireList(pageNum, pageSize, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {

                boolean hasMore = pages > pageNum ? true : false;
                refreshLayout.finishLoadMore(2000, true, !hasMore);//传入false表示刷新失败

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                try {
                    for (int i = 0; i < records.length(); i++) {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        QuestionNaireVipBean questionNaireVipBean = new QuestionNaireVipBean(jsonObject);
                        questionNaireVipBeanList.add(questionNaireVipBean);
                    }
                } catch (JSONException e) {
                    Logger.i("TEST", e.getMessage());
                }
                questionareAdapter.update(questionNaireVipBeanList);
            }

            @Override
            public void onFail(String msg) {
                boolean hasMore = pages > pageNum ? true : false;
                refreshLayout.finishLoadMore(2000, false, !hasMore);//传入false表示刷新失败
                showToast(msg);
            }
        });

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
}
