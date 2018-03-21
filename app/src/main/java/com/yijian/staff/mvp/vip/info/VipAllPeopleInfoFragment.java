package com.yijian.staff.mvp.vip.info;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.vip.model.VipPeopleInfo;
import com.yijian.staff.util.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangk on 2018/3/6.
 * 全部会员信息
 */

public class VipAllPeopleInfoFragment extends Fragment {

    SmartRefreshLayout refreshLayout;
    private RecyclerView rv_vip_all;
    private List<VipPeopleInfo> vipPeopleInfoList=new ArrayList<>();

    private static VipAllPeopleInfoFragment vipAllPeopleInfoFragment;
    public static VipAllPeopleInfoFragment getInstance(){
        if(vipAllPeopleInfoFragment == null){
            vipAllPeopleInfoFragment = new VipAllPeopleInfoFragment();
        }
        return vipAllPeopleInfoFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vip_all_people_info,container,false);
        initView(view);
        initVipPeopleList();
        return view;
    }

    private void initView(View view){
        rv_vip_all = view.findViewById(R.id.rv_vip_all);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        initComponent();
    }

    private void initVipPeopleList(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("headerUrl", "");
            jsonObject.put("name", "张三三");
            jsonObject.put("gender", "0");
            jsonObject.put("cardName", "原力俱乐部30年年卡");
            jsonObject.put("cardType", "时间卡");
            jsonObject.put("privateCoach", "邹市明");
            jsonObject.put("likeLesson", "健身课");
            jsonObject.put("likeTeacher", "陈周奇老师1");
            jsonObject.put("registTime", "2017-12-25");
            jsonObject.put("contractOverTime","2018-12-05");
            jsonObject.put("contractBalance","20天");
            jsonObject.put("buyCount","2次");
            for (int i = 0; i < 10; i++) {
                VipPeopleInfo vipPeopleInfo = new VipPeopleInfo(jsonObject);
                vipPeopleInfoList.add(vipPeopleInfo);
            }


            LinearLayoutManager layoutmanager = new LinearLayoutManager(getActivity());
            //设置RecyclerView 布局
            rv_vip_all.setLayoutManager(layoutmanager);
            VipPeopleInfoAdapter vipPeopleInfoAdapter = new VipPeopleInfoAdapter(getActivity(), vipPeopleInfoList,true);
            rv_vip_all.setAdapter(vipPeopleInfoAdapter);
        } catch (JSONException e) {
            Logger.i("TEST", "JSONException: " + e);

        }
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
                refreshLayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000/*,false*/);//传入false表示刷新失败
            }
        });
    }

}
