package com.yijian.staff.mvp.course.shangke;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Chronometer;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yijian.staff.mvp.course.setclass.orderclass.OrderClassActivity.ORDER_REFRESH_REQUESTCODE;

public class DaKaActivity extends MvcBaseActivity {

    @BindView(R.id.rv_open_lesson)
    RecyclerView rv_open_lesson;
    @BindView(R.id.chronometer)
    Chronometer chronometer;
    @BindView(R.id.tv_today)
    TextView tv_today;
    @BindView(R.id.tv_shangke_time)
    TextView tv_shangke_time;
    @BindView(R.id.tv_xiake_time)
    TextView tv_xiake_time;
    @BindView(R.id.tv_shangke_statu)
    TextView tv_shangke_statu;
    @BindView(R.id.rel_punch_card)
    RelativeLayout rel_punch_card;

    private int punchStatus = -1;
    private String startDate;
    private String appointId;


    public int getPunchStatus() {
        return punchStatus;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.activity_open_lesson_new;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        appointId = getIntent().getStringExtra("appointId");
        Calendar mCalendar = Calendar.getInstance();
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer cArg) {
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                chronometer.setText(sdf.format(d));
            }
        });
        chronometer.setBase(System.currentTimeMillis());
        chronometer.start();

        tv_today.setText(mCalendar.get(Calendar.YEAR) + "-" + (mCalendar.get(Calendar.MONTH) + 1) + "-" + mCalendar.get(Calendar.DATE));





    }

    private void initCourseInfo(){

        Map<String, String> map = new HashMap<String, String>();
        map.put("appointId", appointId);
        HttpManager.postHasHeaderHasParam(HttpManager.PRIVATE_COURSE_INFO_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {

                /**
                 * endDatetime (string, optional): 教练下课打卡时间_分秒 ,
                 punchStatus (integer, optional): 教练打卡状态(0:未打卡 1:已打上课卡 2:已打下课卡) ,
                 startDate (string, optional): 上课日期 ,
                 startDatetime (string, optional): 教练上课打卡时间_分秒
                 */
                punchStatus = JsonUtil.getInt(result, "punchStatus");
                String startDate = JsonUtil.getString(result, "startDate");
                String startDatetime = JsonUtil.getString(result, "startDatetime");
                String endDatetime = JsonUtil.getString(result, "endDatetime");
                if (punchStatus == 0) {
                    tv_shangke_statu.setText("上课打卡");
                } else if (punchStatus == 1) {
                    tv_shangke_statu.setText("下课打卡");
                    tv_shangke_time.setText(startDatetime);
                } else if (punchStatus == 2) {
                    tv_shangke_statu.setText("已完成");
                    tv_shangke_time.setText(startDatetime);
                    tv_xiake_time.setText(endDatetime);

                }

            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(DaKaActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        });

    }

    /**
     * 提交上课打卡数据
     */
    private void submitShangke() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("appointId", appointId);
        HttpManager.postHasHeaderHasParam(HttpManager.COACH_PRIVATE_COURSE_STOCK_RECORD_SHANGKE_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    Toast.makeText(DaKaActivity.this, "上课打卡成功", Toast.LENGTH_SHORT).show();
                    punchStatus = 1;
                    tv_shangke_statu.setText("下课打卡");
                    tv_shangke_time.setText(CommonUtil.emptyIfNull(startDate) + " " + CommonUtil.emptyIfNull(result.getString("clockTime")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(DaKaActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        });

    }

    /**
     * 提交下课卡的数据
     */
    private void submitXiake() {
        HashMap<String, String> map = new HashMap<>();
        map.put("appointId",appointId);

        HttpManager.postHasHeaderHasParam(HttpManager.COACH_PRIVATE_COURSE_STOCK_RECORD_XIAKE_URL, map,  new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {

                try {
                    Toast.makeText(DaKaActivity.this, "下课打卡成功", Toast.LENGTH_SHORT).show();
                    punchStatus = 2;
                    tv_shangke_statu.setText("已完成");
                    tv_xiake_time.setText(CommonUtil.emptyIfNull(startDate) + " " + CommonUtil.emptyIfNull(result.getString("clockTime")));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(DaKaActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        });
    }




    @OnClick({R.id.rel_punch_card})
    public void click(View v) {
        switch (v.getId()) {

            case R.id.rel_punch_card: //打卡
                if (punchStatus == 0) { //打上课卡
                    submitShangke();
                } else if (punchStatus == 1) { // 打下课卡
                    submitXiake();
                }else if (punchStatus ==2){//评价

                }
                break;

        }
    }




}

