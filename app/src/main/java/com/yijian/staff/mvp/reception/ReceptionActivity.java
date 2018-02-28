package com.yijian.staff.mvp.reception;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yijian.staff.R;
import com.yijian.staff.util.Logger;
import com.yijian.staff.util.system.StatusBarUtil;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReceptionActivity extends AppCompatActivity implements View.OnClickListener {

    private List<ReceptionInfo> mReceptionInfoList=new ArrayList<>();

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception);
        StatusBarUtil.setLightStatusBar(this, 0xffffff);

        initView();


    }

    private void initView() {
        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.reception_activity_navigation_bar);
        navigationBar.setTitle("接待", "#000000");
        navigationBar.setNavigationBarBackgroudColor(Color.parseColor("#ffffff"));
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_BLACK));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));

        recyclerView = findViewById(R.id.recyclerview_jiedai_history);



        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("name","朱沙");
            jsonObject.put("phone","12345678900");
            jsonObject.put("sex","男");
            jsonObject.put("status","完成");
            jsonObject.put("product","十周年纪念卡");
            for (int i = 0; i < 10; i++) {
                ReceptionInfo receptionInfo = new ReceptionInfo(jsonObject);
                mReceptionInfoList.add(receptionInfo);
            }
            Logger.i("TEST","mReceptionInfoList: "+mReceptionInfoList.size());


            LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
            //设置RecyclerView 布局
            recyclerView.setLayoutManager(layoutmanager);
            ReceptionHistoryAdapter receptionHistoryAdapter = new ReceptionHistoryAdapter(this, mReceptionInfoList);
            recyclerView.setAdapter(receptionHistoryAdapter);
        } catch (JSONException e) {
            Logger.i("TEST","JSONException: "+e);

        }


    }


    @Override
    public void onClick(View v) {

    }

}