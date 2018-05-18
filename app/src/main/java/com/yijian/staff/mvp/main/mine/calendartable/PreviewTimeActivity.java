package com.yijian.staff.mvp.main.mine.calendartable;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.bigkoo.pickerview.TimePickerView;
import com.yijian.staff.R;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.widget.NavigationBar2;

import org.jaaksi.pickerview.picker.BasePicker;
import org.jaaksi.pickerview.picker.MixedTimePicker;
import org.jaaksi.pickerview.picker.TimePicker;
import org.jaaksi.pickerview.topbar.DefaultTopBar;
import org.jaaksi.pickerview.util.DateUtil;
import org.jaaksi.pickerview.widget.DefaultCenterDecoration;
import org.jaaksi.pickerview.widget.PickerView;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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


    public void showTimeDialog(TextView tv_time, Date startDate, Date selectDate){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date endDate = null;
        try {
            endDate = simpleDateFormat.parse("23:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        TimePicker mTimePicker = new TimePicker.Builder(this, TimePicker.TYPE_TIME, new TimePicker.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(TimePicker picker, Date date) {

                tv_time.setText(simpleDateFormat.format(date));
            }
        })
                // 设置时间区间
                .setRangDate(startDate.getTime(), endDate.getTime())
                // 设置选中时间
                //.setSelectedDate()
                // 设置pickerview样式
                .setInterceptor(new BasePicker.Interceptor() {
                    @Override public void intercept(PickerView pickerView) {
                        pickerView.setColor(Color.parseColor("#d0000000"),Color.parseColor("#dddfe0"));
                        pickerView.setTextSize(15, 20);
                        pickerView.setVisibleItemCount(5);
                         DefaultCenterDecoration decoration = new DefaultCenterDecoration(PreviewTimeActivity.this);
                        decoration.setLineColor(Color.parseColor("#cccccc"));
                        pickerView.setCenterDecoration(decoration);
                    }

                })
                // 设置 Formatter
                .setFormatter(new TimePicker.DefaultFormatter() {
                    // 自定义Formatter显示去年，今年，明年
                    @Override public CharSequence format(TimePicker picker, int type, int position, int num) {
                        DefaultTopBar defaultTopBar = (DefaultTopBar) picker.getTopBar();
                        defaultTopBar.setDividerColor(Color.parseColor("#dddfe0"));
                        defaultTopBar.getBtnCancel().setTextColor(Color.parseColor("#1997f8"));
                        defaultTopBar.getBtnConfirm().setTextColor(Color.parseColor("#1997f8"));
                        defaultTopBar.getTopBarView().setBackgroundColor(Color.parseColor("#dddfe0"));
                        return super.format(picker, type, position, num);
                    }
                }).create();

        mTimePicker.setSelectedDate(selectDate.getTime());
        mTimePicker.show();

    }

    @OnClick({R.id.rel_start, R.id.rel_end})
    public void click(View v) {

        switch (v.getId()) {
            case R.id.rel_start: //选择开始时间
//                showTimeDialog(tv_startTime,10,20);
                String startTime = tv_startTime.getText() == null ? "" : tv_startTime.getText().toString();
                String endTime2 = tv_endTime.getText() == null ? "" : tv_endTime.getText().toString();;
                if(TextUtils.isEmpty(startTime)){
                    Calendar calendar = Calendar.getInstance();
                    startTime = calendar.get(Calendar.HOUR)+":"+calendar.get(Calendar.MINUTE);
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                try {
                    showTimeDialog(tv_startTime, simpleDateFormat.parse("00:00"), simpleDateFormat.parse(TextUtils.isEmpty(endTime2)?"00:00":endTime2));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.rel_end: //选择结束时间
                String startTime2 = tv_startTime.getText() == null ? "" : tv_startTime.getText().toString();
                if(!TextUtils.isEmpty(startTime2)){
                    String endTime = tv_endTime.getText() == null ? "" : tv_endTime.getText().toString();
                    if(TextUtils.isEmpty(endTime)){
                        endTime = startTime2;
                    }
                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm");
                    try {
                        showTimeDialog(tv_endTime, simpleDateFormat2.parse(startTime2), simpleDateFormat2.parse(endTime));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(PreviewTimeActivity.this, "请选择开始时间", Toast.LENGTH_SHORT).show();
                }


                break;
        }

    }


}
