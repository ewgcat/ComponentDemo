package com.yijian.staff.mvp.coach.outdate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.staff.R;
import com.yijian.staff.mvp.vip.bean.VipOutdateInfo;
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
 * 过期会员列表
 */
@Route(path = "/test/4.1")
public class CoachOutdateViperListActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.rv_outdate)
    RecyclerView rv_outdate;
    private List<VipOutdateInfo> vipOutdateInfoList = new ArrayList<VipOutdateInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outdate_viper_list);
        ButterKnife.bind(this);

        initView();
        initList();
    }

    private void initView() {
        NavigationBar navigationBar = findViewById(R.id.vip_over_navigation_bar);
        navigationBar.setTitle("过期会员","#ffffff");
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_WHITE));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));
        /*View view = getLayoutInflater().inflate(R.layout.view_header_filter,null);
        navigationBar.setRightButtonView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.i("筛选");
            }
        });*/
    }

    @Override
    public void onClick(View v) {

    }

    private void initList() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("headerUrl", "");
            jsonObject.put("name", "张三三");
            jsonObject.put("gender", "0");
            jsonObject.put("cardName", "原生俱乐部30年年卡");
            jsonObject.put("cardType", "时间卡");
            jsonObject.put("historyLesson", "没想到");
            jsonObject.put("outDate", "1990-10-12");
            jsonObject.put("outDateReason", "工作太忙没时间");
            for (int i = 0; i < 10; i++) {
                VipOutdateInfo vipPeopleInfo = new VipOutdateInfo(jsonObject);
                vipOutdateInfoList.add(vipPeopleInfo);
            }


            LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
            //设置RecyclerView 布局
            rv_outdate.setLayoutManager(layoutmanager);
            CoachOutdateAdapter coachOutdateAdapter = new CoachOutdateAdapter(this, vipOutdateInfoList);
            rv_outdate.setAdapter(coachOutdateAdapter);
        } catch (JSONException e) {
            Logger.i("TEST", "JSONException: " + e);

        }

    }

}
