package com.yijian.staff.mvp.coach.cunke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.cunke.bean.TypeOfCunKeBody;
import com.yijian.staff.mvp.setclass.AdapterLesson;
import com.yijian.staff.widget.MDividerItemDecoration;
import com.yijian.staff.widget.NavigationBar2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = "/test/16")
public class CunKeActivity extends AppCompatActivity {

    @BindView(R.id.rc_ck)
    RecyclerView rc_ck;
    CunKeAdapter cunKeAdapter;
    List<Object> bodyList = new ArrayList<Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cun_ke);
        ButterKnife.bind(this);

        initTitle();
        initView();
        initData();
    }

    private void initTitle(){
        NavigationBar2   navigationBar2 = (NavigationBar2) findViewById(R.id.cun_ke_navigation_bar2);
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("私教课");
    }

    private void initView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rc_ck.setLayoutManager(linearLayoutManager);
        //添加Android自带的分割线
        MDividerItemDecoration decor = new MDividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        decor.setDrawable(getDrawable(R.drawable.divider_recyclerview));
        rc_ck.addItemDecoration(decor);
        rc_ck.setNestedScrollingEnabled(false);
        cunKeAdapter = new CunKeAdapter();
        rc_ck.setAdapter(cunKeAdapter);
    }

    private void initData() {
        bodyList.add(new TypeOfCunKeBody("小二","减肥课","10节","1节"));
        bodyList.add(new TypeOfCunKeBody("小三","减肥课2","9节","2节"));
        bodyList.add(new TypeOfCunKeBody("小四","减肥课3","8节","3节"));

        cunKeAdapter.resetDataList(bodyList);
    }

}
