package com.yijian.staff.mvp.complaint.handling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.widget.NavigationBar2;

/**
 *员工的处理投诉 界面
 */
public class ComplaintHandlingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_handling);
        initView();

    }

    private void initView() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.complaint_handling_navigation_bar);

        navigationBar2.setTitle("处理投诉");
        navigationBar2.setBackClickListener(this);
        navigationBar2.hideLeftSecondIv();
        TextView rightTv = navigationBar2.getmRightTv();
        rightTv.setText("提交");
        rightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
