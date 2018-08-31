package com.yijian.staff.mvp.course.punch;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.warkiz.widget.IndicatorSeekBar;
import com.xw.repo.BubbleSeekBar;
import com.yijian.staff.R;
import com.yijian.staff.bean.CourseInfoBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.httpmanager.PrivateCoursePingJiaRequestBody;
import com.yijian.staff.net.httpmanager.url.CourseUrls;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

public class CoursePunchActivity extends MvcBaseActivity {


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
    BubbleSeekBar seekBar1;
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
    private CoursePunchQRPopupWindow coursePunchQRPopupWindow;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_da_ka;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        coursePunchQRPopupWindow = new CoursePunchQRPopupWindow(CoursePunchActivity.this);
        coursePunchQRPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                coursePunchQRPopupWindow.setBackgroundAlpha(CoursePunchActivity.this, 1.0f);
            }
        });
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
        HttpManager.getHasHeaderHasParam(CourseUrls.PRIVATE_COURSE_INFO_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
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
                    tv_shangke_time.setText(startDate+" "+startDatetime);
                } else if (punchStatus == 2) {
                    tv_shangke_statu.setText("已完成");
                    coursePunchQRPopupWindow.dismiss();
                    tv_shangke_time.setText(startDate+" "+startDatetime);
                    tv_xiake_time.setText(startDate+" "+endDatetime);
                    if (privateCourseCoachSummaryDTO!=null){

                        llPingjia.setVisibility(View.VISIBLE);
                        int v = (int) (privateCourseCoachSummaryDTO.getActionComplete() * 100);
                        seekBar1.setProgress(v);
                        seekBar2.setProgress(privateCourseCoachSummaryDTO.getActionEvaluate()*100);
                        seekBar3.setProgress(privateCourseCoachSummaryDTO.getAdaptStrength()*100);
                        if (privateCourseCoachSummaryDTO != null) {
                            tvPingjia.setVisibility(View.GONE);
                        } else {
                            tvPingjia.setVisibility(View.VISIBLE);
                        }
                    }
                }

            }


            @Override
            public void onFail(String msg) {
                llPingjia.setVisibility(View.GONE);
                hideLoading();

                Toast.makeText(CoursePunchActivity.this, msg, Toast.LENGTH_SHORT).show();
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
        body.setActionComplete(progress1 / 100.0F);
        body.setActionEvaluate(progress2 / 100.0F);
        body.setAdaptStrength(progress3 / 100.0F);

        HttpManager.postPrivateCoursePingJia(body, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();
            }

            @Override
            public void onFail(String msg) {
                hideLoading();
                showToast(msg);
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
        HttpManager.postHasHeaderHasParam(CourseUrls.COACH_PRIVATE_COURSE_STOCK_RECORD_SHANGKE_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();
                initCourseInfo();
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(CoursePunchActivity.this, msg, Toast.LENGTH_SHORT).show();
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

        HttpManager.postHasHeaderHasParam(CourseUrls.COACH_PRIVATE_COURSE_STOCK_RECORD_XIAKE_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();
                String classOverQRCode = JsonUtil.getString(result, "classOverQRCode");
                showQRDialog(classOverQRCode);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(CoursePunchActivity.this, msg, Toast.LENGTH_SHORT).show();
                hideLoading();
            }
        });
    }

    private void showQRDialog(String classOverQRCode) {
        coursePunchQRPopupWindow.setQR(classOverQRCode);
        coursePunchQRPopupWindow.setBackgroundAlpha(this, 0.3f);
        coursePunchQRPopupWindow.showAtLocation(tv_shangke_statu, Gravity.CENTER,0,0);

        if (timer != null) {
            timer.cancel();
        }
        timer = new MyCountDownTimer(1000 * 60*60*24, 1000);
        timer.setActivity(CoursePunchActivity.this);
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
                postPingJia();
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
        private WeakReference<CoursePunchActivity> weakReference;

        public void setActivity(CoursePunchActivity activity) {
            weakReference = new WeakReference<CoursePunchActivity>(activity);
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

