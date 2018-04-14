package com.yijian.staff.mvp.setclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.yijian.staff.R;
import com.yijian.staff.mvp.setclass.bean.AerobicsBean;
import com.yijian.staff.mvp.setclass.bean.NoInstrumentBean;
import com.yijian.staff.mvp.setclass.bean.PowerBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExperienceClassRecordActivity extends AppCompatActivity {

    @BindView(R.id.rv_noInstrument)
    RecyclerView rv_noInstrument;
    @BindView(R.id.rv_aerobics)
    RecyclerView rv_aerobics;
    @BindView(R.id.rv_power)
    RecyclerView rv_power;

    NoInstrumentAdapter noInstrumentAdapter;
    List<NoInstrumentBean> noInstrumentBeanList = new ArrayList<>();

    AerobicsAdapter aerobicsAdapter;
    List<AerobicsBean> aerobicsBeanList = new ArrayList<>();

    PowerAdapter powerAdapter;
    List<PowerBean> powerBeanList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_class_record);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {

        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        rv_noInstrument.setLayoutManager(layoutmanager);
        noInstrumentAdapter = new NoInstrumentAdapter();
        rv_noInstrument.setAdapter(noInstrumentAdapter);

        LinearLayoutManager layoutmanager2 = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        rv_aerobics.setLayoutManager(layoutmanager2);
        aerobicsAdapter = new AerobicsAdapter();
        rv_aerobics.setAdapter(aerobicsAdapter);

        LinearLayoutManager layoutmanager3 = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        rv_power.setLayoutManager(layoutmanager3);
        powerAdapter = new PowerAdapter();
        rv_power.setAdapter(powerAdapter);

    }

    private void initData() {
        Map<String,String> map = new HashMap<>();
        map.put("privateApplyId",getIntent().getStringExtra("privateApplyId"));
        HttpManager.getHasHeaderHasParam(HttpManager.COACH_PRIVATE_COURSE_STOCK_EXPERIENCE_RECORD_URL, map,  new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                JSONArray noInstrumentJsonArray = JsonUtil.getJsonArray(result, "noInstrumentList");
                JSONArray aerobicsJsonArray = JsonUtil.getJsonArray(result, "aerobicsList");
                JSONArray powerJsonArray = JsonUtil.getJsonArray(result, "powerList");
                noInstrumentBeanList = com.alibaba.fastjson.JSONObject.parseArray(noInstrumentJsonArray.toString(), NoInstrumentBean.class);
                aerobicsBeanList = com.alibaba.fastjson.JSONObject.parseArray(aerobicsJsonArray.toString(), AerobicsBean.class);
                powerBeanList = com.alibaba.fastjson.JSONObject.parseArray(powerJsonArray.toString(), PowerBean.class);
                noInstrumentAdapter.resetActionList(noInstrumentBeanList);
                aerobicsAdapter.resetActionList(aerobicsBeanList);
                powerAdapter.resetActionList(powerBeanList);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(ExperienceClassRecordActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        });

    }
}
