package com.yijian.staff.mvp.huifang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.yijian.staff.R;
import com.yijian.staff.util.system.StatusBarUtils;
import com.yijian.staff.widget.NavigationBar2;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TianXieHuiFangResultActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.tv_next_hui_fang_time)
    TextView tvNextHuiFangTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setLightStatusBar(this, 0xffffff);
        setContentView(R.layout.activity_tian_xie_hui_fang_result);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        NavigationBar2 navigationBar2 = findViewById(R.id.tian_xie_hui_fang_result_navigation_bar);
        navigationBar2.findViewById(R.id.iv_first_left).setOnClickListener(this);
        navigationBar2.findViewById(R.id.iv_second_left).setVisibility(View.GONE);
        navigationBar2.findViewById(R.id.right_tv).setOnClickListener(this);
        navigationBar2.setTitle("填写回访结果");
        navigationBar2.setmRightTvText("完成");


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
                        String result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                        tvNextHuiFangTime.setText(result);

                    }
                }).build();

                pickerView.show();
                break;
            case R.id.right_tv:
                //提交结果

                break;
        }
    }
}
