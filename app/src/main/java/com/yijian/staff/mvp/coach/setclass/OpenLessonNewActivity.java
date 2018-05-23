package com.yijian.staff.mvp.coach.setclass;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
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
import com.yijian.staff.mvp.coach.preparelessons.createlession.EditActionObservable;
import com.yijian.staff.mvp.coach.setclass.bean.PrivateLessonRecordBean;
import com.yijian.staff.mvp.coach.setclass.bean.PrivateShangKeBean;
import com.yijian.staff.mvp.coach.setclass.orderclass.SaveDataDialog;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.yijian.staff.mvp.coach.setclass.orderclass.OrderClassActivity.ORDER_REFRESH_REQUESTCODE;

public class OpenLessonNewActivity extends MvcBaseActivity {

    @BindView(R.id.rv_open_lesson)
    RecyclerView rv_open_lesson;
    @BindView(R.id.chronometer)
    Chronometer chronometer;
    @BindView(R.id.tv_today)
    TextView tv_today;
    @BindView(R.id.tv_shangke)
    TextView tv_shangke;
    @BindView(R.id.tv_xiake)
    TextView tv_xiake;
    @BindView(R.id.tv_shangke_statu)
    TextView tv_shangke_statu;
    @BindView(R.id.rel_punch_card)
    RelativeLayout rel_punch_card;

    private int punchStatus = -1;
    private String recordId; //记录表ID
    private String state; // 是否需要下课打卡（0:是,1:否）

    private String startDate;

    OpenLessonNewAdapter openLessonNewAdapter;
    List<PrivateLessonRecordBean> privateLessonRecordBeans = new ArrayList<PrivateLessonRecordBean>();
    EditActionObservable editActionObservable = new EditActionObservable();

    public int getPunchStatus() {
        return punchStatus;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.activity_open_lesson_new;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initView();
//        loadData();
    }

    /**
     * 加载上课记录详情数据
     */
    public void loadData() {
        String privateApplyId = getIntent().getStringExtra("privateApplyId");
        Map<String, String> map = new HashMap<String, String>();
        map.put("privateApplyId", privateApplyId);
        HttpManager.getHasHeaderHasParam(HttpManager.COACH_PRIVATE_COURSE_STOCK_RECORD_URL, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    recordId = result.getString("recordId");
                    JSONArray records = JsonUtil.getJsonArray(result, "recordContextList");
                    privateLessonRecordBeans = com.alibaba.fastjson.JSONObject.parseArray(records.toString(), PrivateLessonRecordBean.class);
                    openLessonNewAdapter.resetData(privateLessonRecordBeans);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(OpenLessonNewActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        });
    }

    /**
     * 提交上课打卡数据
     */
    private void submitShangke() {

        String privateApplyId = getIntent().getStringExtra("privateApplyId");
        Map<String, String> map = new HashMap<String, String>();
        map.put("appointId", privateApplyId);
        HttpManager.postHasHeaderHasParam(HttpManager.COACH_PRIVATE_COURSE_STOCK_RECORD_SHANGKE_URL, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                Toast.makeText(OpenLessonNewActivity.this, "上课打卡成功", Toast.LENGTH_SHORT).show();
                punchStatus = 1;
                tv_shangke_statu.setText("下课打卡");
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(OpenLessonNewActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        });

    }

    /**
     * 提交下课卡的数据
     */
    private void submitXiake() {
        String privateApplyId = getIntent().getStringExtra("privateApplyId");
        Map<String, String> map = new HashMap<String, String>();
        PrivateShangKeBean privateShangKeBean = new PrivateShangKeBean();
        privateShangKeBean.setPrivateApplyId(privateApplyId);
        privateShangKeBean.setRecordId(recordId);
        List<PrivateShangKeBean.RecordContextListBean> recordContextList = new ArrayList<>();

        for (int i = 0; i < privateLessonRecordBeans.size(); i++) {
            PrivateShangKeBean.RecordContextListBean recordContextListBean = new PrivateShangKeBean.RecordContextListBean();
            recordContextListBean.setActionForm(privateLessonRecordBeans.get(i).getActionForm());
            recordContextListBean.setActionStrength(privateLessonRecordBeans.get(i).getActionStrength());
            recordContextListBean.setId(privateLessonRecordBeans.get(i).getId());
            recordContextListBean.setInterval(privateLessonRecordBeans.get(i).getInterval());
            recordContextListBean.setNum(privateLessonRecordBeans.get(i).getSort());
            recordContextListBean.setPrepareId(privateLessonRecordBeans.get(i).getPrepareId());
            recordContextListBean.setTime(privateLessonRecordBeans.get(i).getTime());
            recordContextList.add(recordContextListBean);
        }
        privateShangKeBean.setRecordContextList(recordContextList);

        HttpManager.postXiaKeRecord(HttpManager.COACH_PRIVATE_COURSE_STOCK_RECORD_XIAKE_URL, privateShangKeBean, state, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                Toast.makeText(OpenLessonNewActivity.this, "下课打卡成功", Toast.LENGTH_SHORT).show();
                punchStatus = 2;
                tv_shangke_statu.setText("已完成");
                rel_punch_card.setEnabled(false);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(OpenLessonNewActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initView() {
//        chronometer.setFormat("H:MM:SS");
        Calendar mCalendar = Calendar.getInstance();
        int hour = mCalendar.get(Calendar.HOUR);
        int minute = mCalendar.get(Calendar.MINUTE);
        int second = mCalendar.get(Calendar.SECOND);

        int time = hour * 60 * 60 + minute * 60 + second;
        chronometer.setBase(SystemClock.elapsedRealtime() - time * 1000);//计时器清零
        chronometer.start();
        tv_today.setText(mCalendar.get(Calendar.YEAR) + "-" + (mCalendar.get(Calendar.MONTH) + 1) + "-" + mCalendar.get(Calendar.DATE));

        String startTime = getIntent().getStringExtra("startTimeActual");
        String endTime = getIntent().getStringExtra("endTimeActual");
        startDate = getIntent().getStringExtra("startDate");
        if (TextUtils.isEmpty(startTime)) {
            tv_shangke.setText(CommonUtil.emptyIfNull(startDate) + " " + CommonUtil.emptyIfNull(startTime));
        }

        if (TextUtils.isEmpty(startTime)) {
            tv_xiake.setText(CommonUtil.emptyIfNull(startDate) + " " + CommonUtil.emptyIfNull(endTime));
        }


        punchStatus = getIntent().getIntExtra("punchStatus", -1);
        if (punchStatus == 0) {
            tv_shangke_statu.setText("上课打卡");
        } else if (punchStatus == 1) {
            tv_shangke_statu.setText("下课打卡");
        }

        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        rv_open_lesson.setLayoutManager(layoutmanager);
        openLessonNewAdapter = new OpenLessonNewAdapter(privateLessonRecordBeans, this, editActionObservable);
        rv_open_lesson.setAdapter(openLessonNewAdapter);
    }

    public void notifyAllLesson(int itemPosition) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("type", "8");
        map.put("itemPosition", itemPosition + "");
        editActionObservable.notifyObservers(map);
    }

    public void setOpenLessonList(int itemPosition, PrivateLessonRecordBean openLessonNewBean) {
        privateLessonRecordBeans.set(itemPosition, openLessonNewBean);
    }


    /**
     * 点击头部 监听各分组的隐藏和显示
     *
     * @param itemPosition
     */
    public void notifyClickHeader(int itemPosition) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("type", "7");
        map.put("itemPosition", itemPosition + "");
        editActionObservable.notifyObservers(map);
    }

    @OnClick({R.id.iv_finish, R.id.rel_punch_card})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.iv_finish: //保存数据
//                SaveDataDialog saveDataDialog = new SaveDataDialog(this);
//                saveDataDialog.showSaveDialog();
                Intent intent = getIntent();
                intent.putExtra("date", startDate);
                setResult(ORDER_REFRESH_REQUESTCODE, intent);
                finish();
                break;
            case R.id.rel_punch_card: //打卡
                if (punchStatus == 0) { //打上课卡
                    submitShangke();
                } else if (punchStatus == 1) { // 打下课卡
                    state = "0";
                    submitXiake();
                }
                break;

        }
    }

    public void finishSaveData() {
        state = "1";
        submitXiake();
    }

}

