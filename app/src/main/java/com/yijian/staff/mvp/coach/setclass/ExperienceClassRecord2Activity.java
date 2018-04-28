package com.yijian.staff.mvp.setclass;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.yijian.staff.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExperienceClassRecord2Activity extends OpenLessonNewActivity {

    @BindView(R.id.rv_open_lesson)
    RecyclerView rv_open_lesson;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_lesson_new2);
        ButterKnife.bind(this);
        initView();
        loadData();
    }




    private void initView() {

    }


}

