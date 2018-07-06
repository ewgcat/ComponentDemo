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
        NavigationBar2 navigationBar2 = findViewById(R.id.vip_edit_navigation_bar);
        navigationBar2.setTitle( "详细信息");
        navigationBar2.hideLeftSecondIv();
        TextView rightTv = navigationBar2.getmRightTv();
        rightTv.setText("保存");
        rightTv.setTextColor(getResources().getColor(R.color.blue));
        navigationBar2.setBackClickListener(this);
    }

}
