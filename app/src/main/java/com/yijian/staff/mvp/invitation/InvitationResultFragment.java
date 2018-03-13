package com.yijian.staff.mvp.invitation;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.yijian.staff.util.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 邀约结果
 */
public class InvitationResultFragment extends Fragment {

    private static InvitationResultFragment invitaionResultFragment;
    public static InvitationResultFragment getInstance(){
        if(invitaionResultFragment == null){
            invitaionResultFragment = new InvitationResultFragment();
        }
        return invitaionResultFragment;
    }

    SmartRefreshLayout refreshLayout;
    RecyclerView rv_invitation;
    private List<InvitationInfo> invitationInfoList=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invitaion_result, container, false);
        rv_invitation = view.findViewById(R.id.rv_invitation);
        initView(view);
        initIinvitationList();
        return view;
    }

    private void initView(View view){
        rv_invitation = view.findViewById(R.id.rv_invitation);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        initComponent();
    }

    private void initIinvitationList(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("headerUrl", "");
            jsonObject.put("name", "张三三");
            jsonObject.put("rightsAndInterests", "0");
            jsonObject.put("overTime", "1990-8-9");
            jsonObject.put("overReason", "农历");
            jsonObject.put("invitationTime", "正常");
            jsonObject.put("invitationContent", "跑步");
            jsonObject.put("invitationType", "打橄榄球");
            jsonObject.put("invitationResult", "无");
            for (int i = 0; i < 10; i++) {
                InvitationInfo vipPeopleInfo = new InvitationInfo(jsonObject);
                invitationInfoList.add(vipPeopleInfo);
            }


            LinearLayoutManager layoutmanager = new LinearLayoutManager(getActivity());
            //设置RecyclerView 布局
            rv_invitation.setLayoutManager(layoutmanager);
            InvitationResultAdatper invitationResultAdatper = new InvitationResultAdatper(getActivity(), invitationInfoList);
            rv_invitation.setAdapter(invitationResultAdatper);
        } catch (JSONException e) {
            Logger.i("TEST", "JSONException: " + e);

        }
    }

    public void initComponent() {
        //设置 Header 为 BezierRadar 样式
        BezierRadarHeader header = new BezierRadarHeader(getActivity()).setEnableHorizontalDrag(true);
        header.setPrimaryColor(getResources().getColor(R.color.colorPrimary));
        refreshLayout.setRefreshHeader(header);
        //设置 Footer 为 球脉冲
        BallPulseFooter footer = new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale);
        footer.setAnimatingColor(getResources().getColor(R.color.colorPrimary));
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
