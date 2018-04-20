package com.yijian.staff.mvp.coach.huifang.tianxieresult;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.yijian.staff.R;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoachTianXieHuiFangResultActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.tv_next_hui_fang_time)
    TextView tvNextHuiFangTime;
    private String interviewRecordId;
    private String memberId;
    private String dictItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tian_xie_hui_fang_result);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        NavigationBar2 navigationBar2 = findViewById(R.id.tian_xie_hui_fang_result_navigation_bar);
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("填写回访结果");
        navigationBar2.setmRightTvText("发送");
        navigationBar2.findViewById(R.id.right_tv).setOnClickListener(this);


        interviewRecordId = getIntent().getStringExtra("interviewRecordId");
        memberId = getIntent().getStringExtra("memberId");
        dictItemId = getIntent().getStringExtra("dictItemId");
        findViewById(R.id.ll_next_hui_fang).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.iv_first_left:
                finish();
                break;
            case R.id.ll_next_hui_fang:
                //提交结果
                TimePickerView pickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View view) {
                        String result = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
                        tvNextHuiFangTime.setText(result);
                    }
                }).build();
                pickerView.show();
                break;
            case R.id.right_tv:
                //提交结果

                HashMap<String, String> param = new HashMap<>();
                param.put("interviewRecordId", interviewRecordId);
                param.put("memberId",memberId);
                HttpManager.getHasHeaderHasParam(HttpManager.GET_COACH_HUI_FANG_CALL_PHONE_URL, param, new ResultJSONObjectObserver() {
                    @Override
                    public void onSuccess(JSONObject result) {

                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });
                break;
        }
    }
}
