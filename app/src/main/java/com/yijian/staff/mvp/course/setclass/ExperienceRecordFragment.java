package com.yijian.staff.mvp.course.setclass;

import android.annotation.SuppressLint;
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
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressLint("ValidFragment")
public class ExperienceRecordFragment extends MvcBaseFragment {

    private int type;
    private RecyclerView rv_experience_record;
    private View rootView;
    private List<Map<String, String>> recordList = new ArrayList<>();

    public ExperienceRecordFragment(int type) {
        this.type = type;
        loadData(type);
    }

    private void loadData(int type) {
        recordList.clear();
        if (type == 1) { //无器械
            Map<String, String> map1 = new HashMap<>();
            map1.put("projectlabel", "心肺功能测试");
            map1.put("project", "台阶测试");
            map1.put("grouplabel", "组数");
            map1.put("group", "2组");
            map1.put("timelabel", "次数");
            map1.put("time", "20次");

            Map<String, String> map2 = new HashMap<>();
            map2.put("projectlabel", "肌肉力量测试");
            map2.put("project", "俯卧撑测试");
            map2.put("grouplabel", "组数");
            map2.put("group", "2组");
            map2.put("timelabel", "次数");
            map2.put("time", "20次");
            recordList.add(map1);
            recordList.add(map2);
        } else if (type == 2) { //有氧器械
            Map<String, String> map1 = new HashMap<>();
            map1.put("projectlabel", "跑步机");
            map1.put("grouplabel", "程式/级数");
            map1.put("group", "低");
            map1.put("timelabel", "时间(s)");
            map1.put("time", "20");

            Map<String, String> map2 = new HashMap<>();
            map2.put("projectlabel", "健身单车");
            map2.put("grouplabel", "程式/级数");
            map2.put("group", "低");
            map2.put("timelabel", "时间(s)");
            map2.put("time", "20");
            recordList.add(map1);
            recordList.add(map2);
        } else if (type == 3) { // 力量器械
            Map<String, String> map1 = new HashMap<>();
            map1.put("projectlabel", "器材");
            map1.put("project", "推胸训练机");
            map1.put("weightlabel", "重量(kg)");
            map1.put("weight", "50");
            map1.put("grouplabel", "组数");
            map1.put("group", "10");
            map1.put("timelabel", "次数");
            map1.put("time", "20");

            Map<String, String> map2 = new HashMap<>();
            map2.put("projectlabel", "器材");
            map2.put("project", "推肩训练机");
            map2.put("weightlabel", "重量(kg)");
            map2.put("weight", "50");
            map2.put("grouplabel", "组数");
            map2.put("group", "10");
            map2.put("timelabel", "次数");
            map2.put("time", "20");
            recordList.add(map1);
            recordList.add(map2);
        }

    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_experience_record;
    }

    @Override
    public void initView() {
        rv_experience_record = rootView.findViewById(R.id.rv_experience_record);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rv_experience_record.setLayoutManager(linearLayoutManager);
        rv_experience_record.setAdapter(new ExperienceRecorderAdapter(recordList, type));
    }


}
