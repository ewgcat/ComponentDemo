package com.yijian.staff.mvp.coach.cunke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.staff.R;
import com.yijian.staff.widget.NavigationBar2;

@Route(path = "/test/16")
public class CunKeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cun_ke);

        NavigationBar2   navigationBar2 = (NavigationBar2) findViewById(R.id.cun_ke_navigation_bar2);
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("私教课存课");

    }
}
