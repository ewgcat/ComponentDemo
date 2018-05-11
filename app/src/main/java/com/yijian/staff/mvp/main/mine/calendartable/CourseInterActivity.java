package com.yijian.staff.mvp.main.mine.calendartable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.yijian.staff.R;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.widget.NavigationBar2;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import static com.yijian.staff.mvp.main.mine.calendartable.CalendarSettingActivity.RESULT_CODE_SETTING_INTERNAL;

public class CourseInterActivity extends AppCompatActivity {

    @BindView(R.id.tv_internal)
    TextView tv_internal;
    private OptionsPickerView optionsPickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_inter);
        ButterKnife.bind(this);
        initTitle();
        initView();
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.navigation_bar);
        navigationBar2.setTitle("选择课程时间间隔");
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

    private void initView(){
        tv_internal.setText(getIntent().getStringExtra("internal"));
        ArrayList<String> timeList = new ArrayList<>();
        timeList.add("10");
        timeList.add("15");
        timeList.add("20");
        timeList.add("30");

        optionsPickerView = new OptionsPickerView.Builder(CourseInterActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tv_internal.setText(timeList.get(options1));
            }
        }).build();
        optionsPickerView.setPicker(timeList);
    }

    private void postData() {
        Map<String, Object> map = new HashMap<>();
//        map.put("intervalTime  ", Integer.valueOf(tv_internal.getText().toString()));
        HttpManager.postHasHeaderHasParamOfObject(HttpManager.COACH_PRIVATE_COURSE_SET_INTERVAL_TIME_URL+"?intervalTime="+Integer.valueOf(tv_internal.getText().toString()), map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                Intent intent = getIntent();
                intent.putExtra("internal", tv_internal.getText());
                setResult(RESULT_CODE_SETTING_INTERNAL, intent);
                finish();
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(CourseInterActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @OnClick({R.id.rel_internal})
    public void click(View v) {

        switch (v.getId()) {
            case R.id.rel_internal: //选择开始时间
                optionsPickerView.show();
                break;
        }

    }


}
