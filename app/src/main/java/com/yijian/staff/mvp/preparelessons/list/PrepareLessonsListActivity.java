package com.yijian.staff.mvp.preparelessons.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.staff.R;
import com.yijian.staff.widget.NavigationBar2;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = "/test/16")
public class PrepareLessonsListActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bei_ke_list);
        ButterKnife.bind(this);


        NavigationBar2 navigationBar2 = findViewById(R.id.bei_ke_navigation_bar);
        navigationBar2.setBackClickListener(this);
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setTitle("备课");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<PrepareLessonsBean> prepareLessonsBeans=new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            PrepareLessonsBean e1 = new PrepareLessonsBean();
            e1.setBeikeStatus("1");
            PrepareLessonsBean e2 = new PrepareLessonsBean();
            e1.setBeikeStatus("2");

            prepareLessonsBeans.add(e1);
            prepareLessonsBeans.add(e2);
        }
        recyclerView.setAdapter(new PrepareLessonsListAdapter(this,prepareLessonsBeans));

    }
}
