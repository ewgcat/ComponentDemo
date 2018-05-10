package com.yijian.staff.mvp.main.mine.calendartable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.yijian.staff.R;
import com.yijian.staff.widget.NavigationBar2;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.yijian.staff.mvp.main.mine.calendartable.CalendarSettingActivity.RESULT_CODE_SETTING_PREVIEW;


public class PreviewTimeActivity extends AppCompatActivity {

    @BindView(R.id.tv_startTime)
    TextView tv_startTime;
    @BindView(R.id.tv_endTime)
    TextView tv_endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_internal);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.reception_activity_navigation_bar);
        navigationBar2.setTitle("选择可约时间段");
        navigationBar2.setmRightTvText("确定");
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("startTime",tv_startTime.getText());
                intent.putExtra("endTime",tv_endTime.getText());
                setResult(RESULT_CODE_SETTING_PREVIEW,intent);
                finish();
            }
        });
        navigationBar2.setBackClickListener(this);
        navigationBar2.hideLeftSecondIv();

    }

    private void showTimeDialog(int flag){ // flag : 0 开始，1  结束
        TimePickerView timePickerView = new TimePickerView.Builder(PreviewTimeActivity.this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {

               /* if(flag == 0){
                    tv_startTime.setText();
                }else if(flag == 1){

                }*/
            }
        }).build();
        timePickerView.show();
    }

    @OnClick({R.id.rel_start,R.id.rel_end})
    public void click(View v) {

        switch (v.getId()){
            case R.id.rel_start: //选择开始时间
                showTimeDialog(0);
                break;
            case R.id.rel_end: //选择结束时间
                showTimeDialog(1);
                break;
        }

    }


}
