package com.yijian.staff.mvp.course.punch;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.warkiz.widget.IndicatorSeekBar;
import com.yijian.staff.R;
import com.yijian.staff.bean.CourseInfoBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.httpmanager.PrivateCoursePingJiaRequestBody;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class PunchCourseActivity extends MvcBaseActivity {


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
    @BindView(R.id.seek_bar1)
    IndicatorSeekBar seekBar1;
    @BindView(R.id.seek_bar2)
    IndicatorSeekBar seekBar2;
    @BindView(R.id.seek_bar3)
    IndicatorSeekBar seekBar3;
    @BindView(R.id.ll_pingjia)
    LinearLayout llPingjia;
    @BindView(R.id.tv_xiake_label)
    TextView tvXiakeLabel;
    @BindView(R.id.tv_pingjia)
    TextView tvPingjia;

    private int punchStatus = 0;
    private String startDate;
    private String startDatetime;
    private String endDatetime;
    private String appointId;
    private MyCountDownTimer timer;
    private PunchCourseQRDialog punchCourseQrDialog;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_da_ka;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        punchCourseQrDialog = new PunchCourseQRDialog(PunchCourseActivity.this);
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
        initCourseInfo();


    }

    private void initCourseInfo() {
        showLoading();
        Map<String, String> map = new HashMap<String, String>();
        map.put("appointId", appointId);
        HttpManager.postHasHeaderHasParam(HttpManager.PRIVATE_COURSE_INFO_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {

                hideLoading();
                /**
                 * endDatetime (string, optional): 教练下课打卡时间_分秒 ,
                 punchStatus (integer, optional): 教练打卡状态(0:未打卡 1:已打上课卡 2:已打下课卡) ,
                 startDate (string, optional): 上课日期 ,
                 startDatetime (string, optional): 教练上课打卡时间_分秒
                 */
                llPingjia.setVisibility(View.GONE);
                CourseInfoBean courseInfoBean = com.alibaba.fastjson.JSONObject.parseObject(result.toString(), CourseInfoBean.class);

                punchStatus = courseInfoBean.getPunchStatus();
                startDate = courseInfoBean.getStartDate();
                startDatetime = courseInfoBean.getStartDatetime();
                endDatetime = courseInfoBean.getEndDatetime();
                CourseInfoBean.PrivateCourseCoachSummaryDTOBean privateCourseCoachSummaryDTO = courseInfoBean.getPrivateCourseCoachSummaryDTO();
                if (punchStatus == 0) {
                    tv_shangke_statu.setText("上课打卡");
                } else if (punchStatus == 1) {
                    tv_shangke_statu.setText("下课打卡");
                    tv_shangke_time.setText(startDatetime);
                } else if (punchStatus == 2) {
                    tv_shangke_statu.setText("已完成");
                    punchCourseQrDialog.dismiss();
                    tv_shangke_time.setText(startDatetime);
                    tv_xiake_time.setText(endDatetime);
                    llPingjia.setVisibility(View.VISIBLE);
                    seekBar1.setIndicatorTextFormat("${PROGRESS}%");
                    if (privateCourseCoachSummaryDTO != null) {
                        tvPingjia.setVisibility(View.GONE);
                    } else {
                        tvPingjia.setVisibility(View.VISIBLE);
                    }
                }

            }


            @Override
            public void onFail(String msg) {
                llPingjia.setVisibility(View.GONE);
                hideLoading();

                Toast.makeText(PunchCourseActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }


    //评价
    private void postPingJia() {
        showLoading();

        PrivateCoursePingJiaRequestBody body = new PrivateCoursePingJiaRequestBody();
        body.setPrivateApplyId(appointId);
        float progress1 = (float) seekBar1.getProgress();
        float progress2 = (float) seekBar2.getProgress();
        float progress3 = (float) seekBar3.getProgress();
        body.setActionComplete(progress1 / 100);
        body.setActionEvaluate(progress2 / 100);
        body.setAdaptStrength(progress3 / 100);

        HttpManager.postPrivateCoursePingJia(body, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();

            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(PunchCourseActivity.this, msg, Toast.LENGTH_SHORT).show();
                hideLoading();

            }
        });
    }

    /**
     * 提交上课打卡数据
     */
    private void submitShangke() {
        showLoading();
        Map<String, String> map = new HashMap<String, String>();
        map.put("appointId", appointId);
        HttpManager.postHasHeaderHasParam(HttpManager.COACH_PRIVATE_COURSE_STOCK_RECORD_SHANGKE_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();
                initCourseInfo();
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(PunchCourseActivity.this, msg, Toast.LENGTH_SHORT).show();
                hideLoading();
            }
        });

    }

    /**
     * 提交下课卡的数据
     */
    private void submitXiake() {
        showLoading();
        HashMap<String, String> map = new HashMap<>();
        map.put("appointId", appointId);

        HttpManager.postHasHeaderHasParam(HttpManager.COACH_PRIVATE_COURSE_STOCK_RECORD_XIAKE_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();
                String classOverQRCode = JsonUtil.getString(result, "classOverQRCode");
                showQRDialog(classOverQRCode);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(PunchCourseActivity.this, msg, Toast.LENGTH_SHORT).show();
                hideLoading();
            }
        });
    }

    private void showQRDialog(String classOverQRCode) {

        punchCourseQrDialog.setQR(classOverQRCode);
        punchCourseQrDialog.show();
        if (timer != null) {
            timer.cancel();
        }
        timer = new MyCountDownTimer(1000 * 2, 1000);
        timer.setActivity(PunchCourseActivity.this);
        timer.start();
    }


    @OnClick({R.id.rel_punch_card, R.id.iv_finish, R.id.tv_pingjia})
    public void click(View v) {
        switch (v.getId()) {

            case R.id.rel_punch_card: //打卡
                if (punchStatus == 0) { //打上课卡
                    submitShangke();
                } else if (punchStatus == 1) { // 打下课卡
                    submitXiake();
                }
                break;
            case R.id.iv_finish:
                finish();
                break;
            case R.id.tv_pingjia:
                postPingJia();
                break;

        }
    }

    @Override
    protected void onDestroy() {
        if (timer != null) {
            timer.cancel();
        }
        super.onDestroy();
    }


    public static class MyCountDownTimer extends CountDownTimer {
        private WeakReference<PunchCourseActivity> weakReference;

        public void setActivity(PunchCourseActivity activity) {
            weakReference = new WeakReference<PunchCourseActivity>(activity);
        }

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            if (weakReference.get() != null) {
                weakReference.get().initCourseInfo();
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, android.R.anim.fade_out);
    }


}

