package com.yijian.staff.mvp.coach.recordchart.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.mvp.setclass.OpenLessonNewActivity;
import com.yijian.staff.mvp.setclass.bean.PrivateLessonRecordBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassRecordDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_record_detail);
    }

    public void loadData() {
        String privateApplyId = getIntent().getStringExtra("id");
        Map<String, String> map = new HashMap<String, String>();
        map.put("recordId", privateApplyId);
        HttpManager.getHasHeaderHasParam(HttpManager.COACH_PRIVATE_COURSE_STOCK_RECORD_URL, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {

                JSONArray records = JsonUtil.getJsonArray(result, "recordContextList");
                List<PrivateLessonRecordBean> privateLessonRecordBeans = com.alibaba.fastjson.JSONObject.parseArray(records.toString(), PrivateLessonRecordBean.class);

            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(ClassRecordDetailActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
