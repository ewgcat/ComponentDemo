package com.yijian.staff.mvp.vip.potential;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bigkoo.pickerview.TimePickerView;
import com.yijian.staff.R;
import com.yijian.staff.widget.NavigationBar2;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


@Route(path = "/test/7")
public class AddPotentialActivity extends AppCompatActivity {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.et_phone)
    EditText etPhone;
//    @BindView(R.id.tv_time)
//    TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_potential);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.add_potential_activity_navigation_bar);
        navigationBar2.setTitle("添加潜在");
        navigationBar2.setmRightTvText("完成");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(101);
                finish();
            }
        });
    }

    @OnClick({R.id.tv_sex})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sex:

                break;
//            case R.id.tv_time:
//                //提交结果
//                TimePickerView pickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
//                    @Override
//                    public void onTimeSelect(Date date, View view) {
//                        String result = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
//                        tvTime.setText(result);
//                    }
//                }).build();
//                pickerView.show();
//                break;
        }
    }
}
