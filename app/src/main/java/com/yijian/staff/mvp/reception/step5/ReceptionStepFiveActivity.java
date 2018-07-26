package com.yijian.staff.mvp.reception.step5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.ReceptionActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultNullObserver;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.TimeBar;

import java.util.HashMap;
import java.util.Map;

public class ReceptionStepFiveActivity extends AppCompatActivity implements View.OnClickListener {

    private String memberId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception_step_five);
        initView();

        Intent intent = getIntent();
        if (intent.hasExtra("memberId")) {
            memberId = intent.getStringExtra("memberId");
        }

    }

    private void initView() {


        NavigationBar2 navigationBar2 = findViewById(R.id.step_five_navigation_bar2);
        navigationBar2.getFirstLeftIv().setOnClickListener(this);
        navigationBar2.getSecondLeftIv().setOnClickListener(this);
        navigationBar2.getmRightTv().setOnClickListener(this);

        navigationBar2.setTitle("合同签订(5/5)");
        navigationBar2.setmRightTvText("完成");
        TimeBar timeBar = findViewById(R.id.step_five_timebar);
        timeBar.showTimeBar(5);


    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.iv_first_left:
                finish();
                break;
            case R.id.iv_second_left:


                Intent i = new Intent(this, ReceptionActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;
            case R.id.right_tv:
                if (!TextUtils.isEmpty(memberId)) endProcess();

                break;
        }
    }

    private void endProcess() {
        Map<String, String> params = new HashMap<>();
        params.put("memberId", memberId);
        HttpManager.postHasHeaderHasParam(HttpManager.RECEPTION_STEP5_END, params, new ResultNullObserver(getLifecycle()) {
            @Override
            public void onSuccess(Object result) {

            }

            @Override
            public void onFail(String msg) {

            }
        });

    }
}
