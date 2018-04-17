package com.yijian.staff.mvp.coach.experienceclass.template.template_system;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.experienceclass.template.template_system.bean.TemplateListBean;
import com.yijian.staff.widget.NavigationBar2;

import java.util.List;

public class Template1ClassActivity_ycm extends AppCompatActivity {
    private static final String TAG = "Template1ClassActivity_";
    private List<TemplateListBean> templateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_template1);

        Intent intent = getIntent();
        if (intent.hasExtra("templateList")){
            templateList = intent.getParcelableArrayListExtra("templateList");
        }

        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.navigation_bar2);

        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("体适能模板");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        RecyAdapter recyAdapter = new RecyAdapter(this);
        recyclerView.setAdapter(recyAdapter);

        if (templateList!=null&&templateList.size()!=0){
            TemplateListBean templateListBean = (TemplateListBean) templateList.get(0);

            DataHelper dataHelper = new DataHelper();
            List<Object> list = dataHelper.mapData(templateListBean);
            recyAdapter.resetData(list);
        }
    }

}
