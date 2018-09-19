package com.yijian.workspace.commen;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijan.commonlib.net.response.ResultJSONObjectObserver;
import com.yijan.commonlib.util.JsonUtil;
import com.yijian.workspace.R;
import com.yijian.workspace.base.BaseSpaceFragment;
import com.yijian.workspace.bean.WorkSpaceVipBean;
import com.yijian.workspace.utils.HttpManagerWorkSpace;
import com.yijian.workspace.widget.EmptyView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;


public class SearchFragment1 extends BaseSpaceFragment {

    SmartRefreshLayout refreshLayout;
    EmptyView empty_view;
    private int pageNum = 1;//页码
    private int pageSize = 10;//每页数量
    private int pagesTotal; //总共多少页
    private List<WorkSpaceVipBean> workSpaceVipBeanList = new ArrayList<>();
    private SearchAllAdapter searchAllAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_search_fragment1;
    }

    @Override
    public void initView() {
        empty_view = findView(R.id.empty_view);
        refreshLayout = findView(R.id.refreshLayout);
        RecyclerView rv_search_all = rootView.findViewById(R.id.rv);
        rv_search_all.setLayoutManager(new LinearLayoutManager(getActivity()));
        //添加Android自带的分割线
        rv_search_all.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        searchAllAdapter = new SearchAllAdapter(getActivity());
        rv_search_all.setAdapter(searchAllAdapter);
        initComponent();
        showLoading();
        refresh();
    }

    public void initComponent() {
        //设置 Header 为 BezierRadar 样式
        BezierRadarHeader header = new BezierRadarHeader(getActivity()).setEnableHorizontalDrag(true);
        header.setPrimaryColor(Color.parseColor("#1997f8"));
        refreshLayout.setRefreshHeader(header);
        //设置 Footer 为 球脉冲
        BallPulseFooter footer = new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale);
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
        workSpaceVipBeanList.clear();
        pageNum = 1;//页码
        pageSize = 10;
        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum", pageNum + "");
        map.put("pageSize", pageSize + "");
        empty_view.setVisibility(View.GONE);

        HttpManagerWorkSpace.getHasHeaderHasParam(HttpManagerWorkSpace.WORKSPACE_QUERY_SEARCH__URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();
                refreshLayout.finishRefresh(2000, true);
                pagesTotal = JsonUtil.getInt(result, "pages");
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                for (int i = 0; i < records.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        WorkSpaceVipBean typeOfCunKeBody = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.toString(), WorkSpaceVipBean.class);
                        workSpaceVipBeanList.add(typeOfCunKeBody);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                empty_view.setVisibility(workSpaceVipBeanList.size()>0?View.GONE:View.VISIBLE);
                searchAllAdapter.resetDataList(workSpaceVipBeanList);
            }

            @Override
            public void onFail(String msg) {
                hideLoading();
                refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void loadMore() {

        if(pageNum < pagesTotal){
            pageNum++;
            HashMap<String, String> map = new HashMap<>();
            map.put("pageNum", pageNum + "");
            map.put("pageSize", pageSize + "");

            HttpManagerWorkSpace.getHasHeaderHasParam(HttpManagerWorkSpace.WORKSPACE_QUERY_SEARCH__URL, map, new ResultJSONObjectObserver(getLifecycle()) {
                @Override
                public void onSuccess(JSONObject result) {
                    pagesTotal = JsonUtil.getInt(result, "pages");
                    refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败
                    JSONArray records = JsonUtil.getJsonArray(result, "records");
                    for (int i = 0; i < records.length(); i++) {
                        try {
                            JSONObject jsonObject = (JSONObject) records.get(i);
                            WorkSpaceVipBean typeOfCunKeBody = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.toString(), WorkSpaceVipBean.class);
                            workSpaceVipBeanList.add(typeOfCunKeBody);
                        } catch (JSONException e) {
                        }
                    }
                    searchAllAdapter.resetDataList(workSpaceVipBeanList);
                }

                @Override
                public void onFail(String msg) {
                    refreshLayout.finishLoadMore(2000, false, false);//传入false表示刷新失败
                    Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败
        }

    }

   /* @Override
    public void update(Object data) {
        memberName = (String) data;
        refresh();
    }*/

}
