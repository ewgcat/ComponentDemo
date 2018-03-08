package com.yijian.staff.mvp.dailywork;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yijian.staff.R;
import com.yijian.staff.mvp.vip.info.VipAllPeopleInfoFragment;
import com.yijian.staff.mvp.vip.info.VipPeopleInfoAdapter;
import com.yijian.staff.mvp.vip.model.VipPeopleInfo;
import com.yijian.staff.util.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 待审批
 */
public class WattingApprovalFragment extends Fragment {

    private RecyclerView rv_vip_watting_approval;
    private List<ApprovalInfo> approvalInfoList = new ArrayList<>();

    private static WattingApprovalFragment wattingApprovalFragment;
    public static WattingApprovalFragment getInstance(){
        if(wattingApprovalFragment == null){
            wattingApprovalFragment = new WattingApprovalFragment();
        }
        return wattingApprovalFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_watting_approval, container, false);
        rv_vip_watting_approval = view.findViewById(R.id.rv_vip_all);
        initApprovalInfoList();
        return view;
    }

    private void initApprovalInfoList() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("headerUrl", "");
            jsonObject.put("name", "张三三");
            jsonObject.put("applyType", "病假");
            jsonObject.put("applayTotalTime", "10小时");
            jsonObject.put("applayStartTime", "1990-11-12");
            jsonObject.put("applayEndTime", "1990-11-13");
            for (int i = 0; i < 10; i++) {
                ApprovalInfo vipPeopleInfo = new ApprovalInfo(jsonObject);
                approvalInfoList.add(vipPeopleInfo);
            }


            LinearLayoutManager layoutmanager = new LinearLayoutManager(getActivity());
            //设置RecyclerView 布局
            rv_vip_watting_approval.setLayoutManager(layoutmanager);
            ApprovalAdapter vipPeopleInfoAdapter = new ApprovalAdapter(getActivity(), approvalInfoList,true);
            rv_vip_watting_approval.setAdapter(vipPeopleInfoAdapter);
        } catch (JSONException e) {
            Logger.i("TEST", "JSONException: " + e);

        }
    }

}
