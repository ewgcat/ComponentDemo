package com.yijian.staff.mvp.coach.cunke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.widget.NavigationBar2;

public class CunkeNewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cunke_new);
        initTitle();
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = findViewById(R.id.navigation_bar);
        navigationBar2.setTitle( "存课信息");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
    }

}
