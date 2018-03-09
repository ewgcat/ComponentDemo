package com.yijian.staff.mvp.vip.potentialandintent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.yijian.staff.R;
import com.yijian.staff.mvp.vip.model.VipPeopleInfo;
import com.yijian.staff.util.Logger;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 潜在会员或意向会员 列表
 */
@Route(path = "/test/activity")
public class PotentialAndIntentViperListActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.rv_vip_intention)
    RecyclerView rv_vip_intention;
    private List<VipPeopleInfo> vipPeopleInfoList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potential_and_intent_viper_list);
        ButterKnife.bind(this);

        initView();
        initVipPeopleList();
    }

    private void initView() {

        String title = getIntent().getStringExtra("title");
        NavigationBar2 navigationBar2 = findViewById(R.id.vip_intent_navigation_bar);
        navigationBar2.hideLeftSecondIv();
        navigationBar2.getmRightTv().setOnClickListener(this);
        navigationBar2.setBackClickListener(this);
        ImageView rightIv = navigationBar2.getmRightIv();
        Glide.with(this).load(R.mipmap.wt_shuaixuan).into(rightIv);
        navigationBar2.setTitle(title);
        navigationBar2.setmRightTvText("筛选");


    }

    private void initVipPeopleList(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("headerUrl", "");
            jsonObject.put("name", "张三三");
            jsonObject.put("gender", "0");
            jsonObject.put("birth", "1990-8-9");
            jsonObject.put("birthType", "农历");
            jsonObject.put("bodyStatus", "正常");
            jsonObject.put("bodybuildingHobby", "跑步");
            jsonObject.put("interestHobby", "打橄榄球");
            jsonObject.put("useCar", "无");
            jsonObject.put("isIntentVip","0");
            for (int i = 0; i < 10; i++) {
                VipPeopleInfo vipPeopleInfo = new VipPeopleInfo(jsonObject);
                vipPeopleInfoList.add(vipPeopleInfo);
            }


            LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
            //设置RecyclerView 布局
            rv_vip_intention.setLayoutManager(layoutmanager);
            PotentialAndIntentViperListAdapter potentialAndIntentViperListAdapter = new PotentialAndIntentViperListAdapter(this, vipPeopleInfoList);
            rv_vip_intention.setAdapter(potentialAndIntentViperListAdapter);
        } catch (JSONException e) {
            Logger.i("TEST", "JSONException: " + e);

        }
    }


    @Override
    public void onClick(View v) {

    }
}
