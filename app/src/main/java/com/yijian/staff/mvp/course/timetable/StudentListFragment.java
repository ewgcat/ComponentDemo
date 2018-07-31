package com.yijian.staff.mvp.course.timetable;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.bean.AccessStatisticsRequestBody;
import com.yijian.staff.bean.StudentBean;
import com.yijian.staff.bean.TypeOfCunKeBody;
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;
import com.yijian.staff.mvp.course.cunke.CunKeNewAdapter;
import com.yijian.staff.mvp.course.cunke.CunkeNewActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.EmptyView;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class StudentListFragment extends MvcBaseFragment {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.empty_view)
    EmptyView emptyView;
    private int pageNum = 1;//页码
    private int pageSize = 10;//每页数量
    private int pagesTotal; //总共多少页
    private List<StudentBean> dataList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_student_list;
    }

    @Override
    public void initView() {
        emptyView.setVisibility(View.GONE);

        String version = CommonUtil.getAccessStatisticsVersionName(getContext()) + " " + CommonUtil.getVersionCode(getContext());
        AccessStatisticsRequestBody body=new AccessStatisticsRequestBody("app_storage_course_num",version);
        HttpManager.postAccessStatistics(body, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {

            }

            @Override
            public void onFail(String msg) {

            }
        });

        //测试数据
        for (int i = 0; i < 10; i++) {
            StudentBean studentBean = new StudentBean();
            studentBean.setCourse("增肌课");
            studentBean.setName("学员"+i);
            studentBean.setTime(Long.valueOf((11+i)));
            dataList.add(studentBean);
        }


        StudentListAdapter  studentListAdapter = new StudentListAdapter(getContext(),dataList);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(studentListAdapter);
        initComponent();
    }





    public void initComponent() {
        //设置 Header 为 BezierRadar 样式
        BezierRadarHeader header = new BezierRadarHeader(getContext()).setEnableHorizontalDrag(true);
        header.setPrimaryColor(Color.parseColor("#1997f8"));
        refreshLayout.setRefreshHeader(header);
        //设置 Footer 为 球脉冲
        BallPulseFooter footer = new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale);
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
        pageNum = 1;//页码
        pageSize = 10;
        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum", pageNum + "");
        map.put("pageSize", pageSize + "");

        HttpManager.postHasHeaderHasParam(HttpManager.COACH_PRIVATE_COURSE_STOCK_PRIVATE_LIST_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                refreshLayout.finishRefresh(2000, true);
                pagesTotal = JsonUtil.getInt(result, "pages");
                JSONArray records = JsonUtil.getJsonArray(result, "records");

            }

            @Override
            public void onFail(String msg) {
                refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                showToast(msg);

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
                    pagesTotal = JsonUtil.getInt(result, "pageSize");
                    refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败

                    JSONArray records = JsonUtil.getJsonArray(result, "records");
                }

                @Override
                public void onFail(String msg) {

                    refreshLayout.finishLoadMore(2000, false, false);//传入false表示刷新失败
                    showToast(msg);
                }
            });
        }else{
            refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败
        }

    }



    @OnClick(R.id.ll_add_student)
    public void onViewClicked() {
    }
}
