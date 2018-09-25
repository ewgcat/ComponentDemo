package com.yijian.clubmodule.ui.advice;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.commonlib.widget.NavigationBar;
import com.yijian.clubmodule.R;
import com.yijian.clubmodule.bean.AdviceBean;
import com.yijian.commonlib.base.mvc.MvcBaseActivity;

import java.util.ArrayList;
import java.util.List;





@Route(path = "/test/30")
public class AdviceListActivity extends MvcBaseActivity {


    RecyclerView rlv;

    private List<AdviceBean> adviceBeanList = new ArrayList<>();



    @Override
    protected int getLayoutID() {
        return R.layout.activity_advice_list;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        rlv=findViewById(R.id.rlv);

        initView();

    }

    private void initView() {
        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.advice_list_view_navigation_bar);
        navigationBar.setTitle("建议与反馈");
        navigationBar .hideLeftSecondIv();
        navigationBar .setBackClickListener(this);
        for (int i = 0; i < 3; i++) {
            adviceBeanList.add(new AdviceBean("百度新闻是包含海量资讯的新闻服务平台,真实反映每时每刻的新闻热点。您可以搜索新闻事件、热点话题、人物动态、产品资讯等,快速了解它们的最新进展。", "" +
                    "朱沙", "2018-03-17 09:54:54"));

        }

        rlv.setLayoutManager(new LinearLayoutManager(this));
        AdviceListAdapter adviceListAdapter = new AdviceListAdapter(this, adviceBeanList);
        rlv.setAdapter(adviceListAdapter);
    }
}
