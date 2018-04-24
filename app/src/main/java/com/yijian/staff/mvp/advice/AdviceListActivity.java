package com.yijian.staff.mvp.advice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.staff.R;
import com.yijian.staff.widget.NavigationBar2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = "/test/30")
public class AdviceListActivity extends AppCompatActivity {

    @BindView(R.id.rlv)
    RecyclerView rlv;

    private List<AdviceBean> adviceBeanList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice_list);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.advice_list_view_navigation_bar2);
        navigationBar2.setTitle("建议与反馈");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        for (int i = 0; i <3 ; i++) {
            adviceBeanList.add(new AdviceBean("百度新闻是包含海量资讯的新闻服务平台,真实反映每时每刻的新闻热点。您可以搜索新闻事件、热点话题、人物动态、产品资讯等,快速了解它们的最新进展。","" +
                    "朱沙","2018-03-17 09:54:54"));

        }

        rlv.setLayoutManager(new LinearLayoutManager(this));
        AdviceListAdapter adviceListAdapter = new AdviceListAdapter(this, adviceBeanList);
        rlv.setAdapter(adviceListAdapter);
    }
}
