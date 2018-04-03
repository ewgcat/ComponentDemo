package com.yijian.staff.mvp.empty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.staff.R;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.NavigationBarItemFactory;

@Route(path = "/test/empty")
public class EmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        NavigationBar2 navigationBar2=  findViewById(R.id.empty_navigation_bar2);
        navigationBar2.setBackClickListener(this);
        navigationBar2.hideLeftSecondIv();
    }
}
