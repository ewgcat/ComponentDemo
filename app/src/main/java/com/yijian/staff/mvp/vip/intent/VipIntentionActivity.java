package com.yijian.staff.mvp.vip.intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yijian.staff.R;
import com.yijian.staff.mvp.vip.model.VipPeopleInfo;
import com.yijian.staff.util.Logger;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 意向会员 /  潜在会员 公用一个页面
 */
public class VipIntentionActivity extends AppCompatActivity {

    @BindView(R.id.rv_vip_intention)
    RecyclerView rv_vip_intention;
    private List<VipPeopleInfo> vipPeopleInfoList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_intention);
        ButterKnife.bind(this);

        initView();
        initVipPeopleList();
    }

    private void initView() {
        NavigationBar navigationBar = findViewById(R.id.vip_over_navigation_bar);
        navigationBar.setTitle("意向会员","#ffffff");
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_WHITE));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));
        View view = getLayoutInflater().inflate(R.layout.view_header_filter,null);
        navigationBar.setRightButtonView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.i("筛选");
            }
        });
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
            VipIntentionAdapter vipIntentionAdapter = new VipIntentionAdapter(this, vipPeopleInfoList);
            rv_vip_intention.setAdapter(vipIntentionAdapter);
        } catch (JSONException e) {
            Logger.i("TEST", "JSONException: " + e);

        }
    }


}
