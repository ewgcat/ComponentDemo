package com.yijian.staff.mvp.vip;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yijian.staff.R;
import com.yijian.staff.mvp.vip.model.VipPeopleInfo;
import com.yijian.staff.util.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangk on 2018/3/6.
 * 今日来访
 */

public class VipTodayVisitInfoFragment extends Fragment {

    private RecyclerView rv_vip_all;
    private List<VipPeopleInfo> vipPeopleInfoList=new ArrayList<>();

    private static VipTodayVisitInfoFragment vipTodayVisitInfoFragment;
    public static VipTodayVisitInfoFragment getInstance(){
        if(vipTodayVisitInfoFragment == null){
            vipTodayVisitInfoFragment = new VipTodayVisitInfoFragment();
        }
        return vipTodayVisitInfoFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vip_all_people_info,container,false);
        rv_vip_all = view.findViewById(R.id.rv_vip_all);
        initVipPeopleList();
        return view;
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
            jsonObject.put("likeTeacher", "陈周奇老师");
            jsonObject.put("registTime", "2017-12-25");
            jsonObject.put("contractOverTime","2018-12-05");
            jsonObject.put("contractBalance","20天");
            jsonObject.put("buyCount","2次");
            jsonObject.put("bePresentTime","2018-12-05");
            jsonObject.put("departureTime","2018-12-05");
            for (int i = 0; i < 10; i++) {
                VipPeopleInfo vipPeopleInfo = new VipPeopleInfo(jsonObject);
                vipPeopleInfoList.add(vipPeopleInfo);
            }


            LinearLayoutManager layoutmanager = new LinearLayoutManager(getActivity());
            //设置RecyclerView 布局
            rv_vip_all.setLayoutManager(layoutmanager);
            VipPeopleInfoAdapter vipPeopleInfoAdapter = new VipPeopleInfoAdapter(getActivity(), vipPeopleInfoList,false);
            rv_vip_all.setAdapter(vipPeopleInfoAdapter);
        } catch (JSONException e) {
            Logger.i("TEST", "JSONException: " + e);

        }
    }

}
