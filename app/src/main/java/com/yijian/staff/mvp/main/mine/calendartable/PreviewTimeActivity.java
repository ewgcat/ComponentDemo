package com.yijian.staff.mvp.main.mine.calendartable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.bigkoo.pickerview.TimePickerView;
import com.yijian.staff.R;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.widget.NavigationBar2;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.yijian.staff.mvp.main.mine.calendartable.CalendarSettingActivity.REQUEST_CODE_SETTING_PREVIEW;


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
        initView();
    }

    private void initView() {
        tv_startTime.setText(getIntent().getStringExtra("startTime"));
        tv_endTime.setText(getIntent().getStringExtra("endTime"));
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.navigation_bar);
        navigationBar2.setTitle("选择可约时间段");
        navigationBar2.setmRightTvText("确定");
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData();
            }
        });
        navigationBar2.setBackClickListener(this);
        navigationBar2.hideLeftSecondIv();

    }

    private void postData() {
        Map<String, String> map = new HashMap<>();
        /*map.put("startTime ", tv_startTime.getText().toString());
        map.put("endTime ", tv_endTime.getText().toString());*/
        String path = HttpManager.COACH_PRIVATE_COURSE_SET_WORK_TIME_URL + "?startTime=" + tv_startTime.getText().toString() + "&endTime=" +tv_endTime.getText().toString();
        HttpManager.postHasHeaderHasParam(path, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                Intent intent = getIntent();
                intent.putExtra("startTime", tv_startTime.getText());
                intent.putExtra("endTime", tv_endTime.getText());
                setResult(REQUEST_CODE_SETTING_PREVIEW, intent);
                finish();
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(PreviewTimeActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showTimeDialog(TextView tv_time) {
        TimePickerView timePickerView = new TimePickerView.Builder(PreviewTimeActivity.this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                tv_time.setText(simpleDateFormat.format(date));
            }
        }).setType(new boolean[]{false, false, false, true, true, false}).build();
        timePickerView.show();
    }

    @OnClick({R.id.rel_start, R.id.rel_end})
    public void click(View v) {

        switch (v.getId()) {
            case R.id.rel_start: //选择开始时间
                showTimeDialog(tv_startTime);
                break;
            case R.id.rel_end: //选择结束时间
                showTimeDialog(tv_endTime);
                break;
        }

    }


}
