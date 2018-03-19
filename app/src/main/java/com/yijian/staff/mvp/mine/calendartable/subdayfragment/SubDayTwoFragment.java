package com.yijian.staff.mvp.mine.calendartable.subdayfragment;

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
import com.yijian.staff.mvp.mine.calendartable.DayCanlendarAdapter;
import com.yijian.staff.mvp.mine.calendartable.DayCanlendarInfo;
import com.yijian.staff.util.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SubDayTwoFragment extends Fragment {

    RecyclerView rv_day;
    private List<DayCanlendarInfo> dayCanlendarInfoList=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sub_day_two, container, false);
        initView(view);
        initDayCanlendarInfoList();
        return view;
    }

    private void initView(View view) {
        rv_day = view.findViewById(R.id.rv_day);
    }

    private void initDayCanlendarInfoList() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("className", "张二二");
            jsonObject.put("venue", "场馆1");
            jsonObject.put("intervalTime", "20分钟");
            jsonObject.put("startOrderTime", "09:00");
            jsonObject.put("endOrderTime", "11:00");
            jsonObject.put("status", "1");
            JSONArray stuArray = new JSONArray();
            stuArray.put("张三");
            stuArray.put("李四");
            jsonObject.put("stuList", stuArray);
            for (int i = 0; i < 10; i++) {
                DayCanlendarInfo dayCanlendarInfo = new DayCanlendarInfo(jsonObject);
                dayCanlendarInfoList.add(dayCanlendarInfo);
            }


            LinearLayoutManager layoutmanager = new LinearLayoutManager(getActivity());
            //设置RecyclerView 布局
            rv_day.setLayoutManager(layoutmanager);
            DayCanlendarAdapter dayCanlendarAdapter = new DayCanlendarAdapter(getActivity(), dayCanlendarInfoList);
            rv_day.setAdapter(dayCanlendarAdapter);
        } catch (JSONException e) {
            Logger.i("TEST", "JSONException: " + e);

        }

    }

}
