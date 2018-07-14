package com.yijian.staff.mvp.course.setclass;

import android.content.Intent;
import android.os.SystemClock;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.bean.AerobicsBean;
import com.yijian.staff.bean.NoInstrumentBean;
import com.yijian.staff.bean.PowerBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class ExperienceClassRecordActivity extends MvcBaseActivity {

    @BindView(R.id.tv_shangke)
    TextView tv_shangke;
    @BindView(R.id.tv_xiake)
    TextView tv_xiake;
    @BindView(R.id.chronometer)
    Chronometer chronometer;
    @BindView(R.id.tv_today)
    TextView tv_today;
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
    protected int getLayoutID() {
        return R.layout.activity_experience_class_record;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {


        Calendar mCalendar = Calendar.getInstance();
        int hour = mCalendar.get(Calendar.HOUR);
        int minute = mCalendar.get(Calendar.MINUTE);
        int second = mCalendar.get(Calendar.SECOND);

        int time = hour * 60 * 60 + minute * 60 + second;
        chronometer.setBase(SystemClock.elapsedRealtime() - time * 1000);//计时器清零
        chronometer.start();
        tv_today.setText(mCalendar.get(Calendar.YEAR) + "-" + (mCalendar.get(Calendar.MONTH) + 1) + "-" + mCalendar.get(Calendar.DATE));


        String startTime = getIntent().getStringExtra("startDateTime");
        String endTime = getIntent().getStringExtra("endDateTime");
        String startDate = getIntent().getStringExtra("startDate");
        tv_shangke.setText(CommonUtil.emptyIfNull(startDate) + " " + CommonUtil.emptyIfNull(startTime));
        tv_xiake.setText(CommonUtil.emptyIfNull(startDate) + " " + CommonUtil.emptyIfNull(endTime));

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
        Map<String, String> map = new HashMap<>();
        map.put("privateApplyId", getIntent().getStringExtra("privateApplyId"));
        showLoading();
        HttpManager.getHasHeaderHasParam(HttpManager.COACH_PRIVATE_COURSE_STOCK_EXPERIENCE_RECORD_URL, map, new ResultJSONObjectObserver() {
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
                hideLoading();
            }

            @Override
            public void onFail(String msg) {

                hideLoading();
            }
        });

    }

    @OnClick({R.id.rel_to_record})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.rel_to_record: // 上课打卡
                startActivity(new Intent(this, ExperienceClassRecord2Activity.class));
                break;

        }
    }

}
