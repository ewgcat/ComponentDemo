package com.yijian.staff.mvp.reception;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step1.ReceptionStepOneActivity;
import com.yijian.staff.mvp.reception.step2.CoachReceptionStepTwoActivity;
import com.yijian.staff.mvp.reception.step2.KeFuReceptionStepTwoActivity;
import com.yijian.staff.mvp.reception.step3.ReceptionStepThreeActivity;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.util.Logger;
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
        initView();
    }

    private void initView() {
        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.reception_activity_navigation_bar);
        navigationBar.setTitle("接待", "#ffffff");
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_WHITE));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));

        findViewById(R.id.tv_jiedai).setOnClickListener(this);
        findViewById(R.id.tv_stopJieDai).setOnClickListener(this);

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
        int id = v.getId();

        switch (id){
            case R.id.tv_stopJieDai:
                //TODO 结束接待



                break;
            case R.id.tv_jiedai:
                //TODO 接待流程
                int userRole = SharePreferenceUtil.getUserRole();
                if (userRole==1){
                    Intent intent = new Intent(ReceptionActivity.this, ReceptionStepOneActivity.class);
                    startActivity(intent);
                }else if (userRole==2){
                    Intent intent = new Intent(ReceptionActivity.this, CoachReceptionStepTwoActivity.class);
                    startActivity(intent);
                }else if (userRole==3|userRole==4){
                    Intent intent = new Intent(ReceptionActivity.this, ReceptionStepThreeActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

}