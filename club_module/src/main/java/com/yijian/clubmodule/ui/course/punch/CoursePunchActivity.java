package com.yijian.clubmodule.ui.course.punch;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;
import com.yijian.clubmodule.R;
import com.yijian.clubmodule.bean.CourseInfoBean;
import com.yijian.commonlib.base.mvc.MvcBaseActivity;
import com.yijian.clubmodule.net.httpmanager.HttpManager;
import com.yijian.clubmodule.net.requestbody.PrivateCoursePingJiaRequestBody;
import com.yijian.clubmodule.net.httpmanager.url.CourseUrls;
import com.yijian.commonlib.net.response.ResultJSONObjectObserver;
import com.yijian.commonlib.util.JsonUtil;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;






public class CoursePunchActivity extends MvcBaseActivity implements View.OnClickListener {


    Chronometer chronometer;
    TextView tv_today;
    TextView tv_shangke_time;
    TextView tv_xiake_time;
    TextView tv_shangke_statu;
    RelativeLayout rel_punch_card;
    IndicatorSeekBar seekBar1;
    IndicatorSeekBar seekBar2;
    IndicatorSeekBar seekBar3;
    LinearLayout ll_pingjia;
    TextView tv_xiake_label;
    TextView tv_pingjia;
    TextView tv_jiao_cha;
    TextView tv_he_ge;
    TextView tv_jiao_hao;
    TextView tv_hen_hao;
    TextView tv_you_xiu;
    TextView tv_di;
    TextView tv_zhong;
    TextView tv_gao;

    private int punchStatus = 0;
    private String startDatetime;
    private String endDatetime;
    private String appointId;
    private CoursePunchQRPopupWindow coursePunchQRPopupWindow;
    private Timer timer;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    initCourseInfo(1);
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }
        }
    };

    @Override
    protected int getLayoutID() {
        setImmersionBar();
        return R.layout.activity_course_punch;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        findViewById(R.id.rel_punch_card).setOnClickListener(this);
        findViewById(R.id.iv_finish).setOnClickListener(this);
        findViewById(R.id.tv_pingjia).setOnClickListener(this);
        seekBar2.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
                showSeekBar2(seekParams.progress);
            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });
        seekBar3.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
                showSeekBar3(seekParams.progress);
            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });

        coursePunchQRPopupWindow = new CoursePunchQRPopupWindow(CoursePunchActivity.this);
        coursePunchQRPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (timer!=null){
                    timer.cancel();
                }
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
        initCourseInfo(0);


    }

    private void initCourseInfo(int requestType) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("appointId", appointId);
        map.put("requestType", requestType+"");
        HttpManager.getHasHeaderHasParam(CourseUrls.PRIVATE_COURSE_INFO_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                /**
                 * endDatetime (string, optional): 教练下课打卡时间_分秒 ,
                 punchStatus (integer, optional): 教练打卡状态(0:未打卡 1:已打上课卡 2:已打下课卡) ,
                 startDate (string, optional): 上课日期 ,
                 startDatetime (string, optional): 教练上课打卡时间_分秒
                 */
                ll_pingjia.setVisibility(View.GONE);
                CourseInfoBean courseInfoBean = com.alibaba.fastjson.JSONObject.parseObject(result.toString(), CourseInfoBean.class);

                punchStatus = courseInfoBean.getPunchStatus();
                startDatetime = courseInfoBean.getStartDatetime();
                endDatetime = courseInfoBean.getEndDatetime();
                CourseInfoBean.PrivateCourseCoachSummaryDTOBean privateCourseCoachSummaryDTO = courseInfoBean.getPrivateCourseCoachSummaryDTO();
                if (punchStatus == 0) {
                    tv_shangke_statu.setText("上课打卡");
                } else if (punchStatus == 1) {
                    tv_shangke_statu.setText("下课打卡");
                    tv_shangke_time.setText( startDatetime);
                } else if (punchStatus == 2) {
                    tv_shangke_statu.setText("已完成");
                    coursePunchQRPopupWindow.dismiss();
                   if (timer!=null){
                       timer.cancel();
                   }
                    tv_shangke_time.setText( startDatetime);
                    tv_xiake_time.setText( endDatetime);
                    int status = courseInfoBean.getStatus();
                    if (status==3){
                        ll_pingjia.setVisibility(View.VISIBLE);
                        tv_pingjia.setVisibility(View.VISIBLE);
                    }else if (status==5){
                        ll_pingjia.setVisibility(View.VISIBLE);
                        tv_pingjia.setVisibility(View.GONE);
                        seekBar1.setUserSeekAble(false);
                        seekBar2.setUserSeekAble(false);
                        seekBar3.setUserSeekAble(false);

                        if (privateCourseCoachSummaryDTO != null) {
                            float progress1 = privateCourseCoachSummaryDTO.getActionComplete() * 100f;
                            float progress2 = (privateCourseCoachSummaryDTO.getActionEvaluate() * 100f);
                            float progress3 =  (privateCourseCoachSummaryDTO.getAdaptStrength() * 100f);
                            seekBar1.setProgress(progress1);
                            seekBar2.setProgress(progress2);
                            seekBar3.setProgress(progress3);
                            showSeekBar2(progress2);
                            showSeekBar3(progress3);
                        }
                    }
                }
            }


            @Override
            public void onFail(String msg) {
                ll_pingjia.setVisibility(View.GONE);
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
                tv_pingjia.setVisibility(View.GONE);
                seekBar1.setUserSeekAble(false);
                seekBar2.setUserSeekAble(false);
                seekBar3.setUserSeekAble(false);
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
                initCourseInfo(0);
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
        coursePunchQRPopupWindow.showAtLocation(tv_shangke_statu, Gravity.CENTER, 0, 0);
        timer = new Timer();

        //前一次执行程序结束后 2000ms 后开始执行下一次程序
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = Message.obtain();
                message.what=1;
                handler.sendMessage(message);
            }
        }, 0, 1000);

    }





    @Override
    protected void onDestroy() {
        if (timer != null) {
            timer.cancel();
        }
        super.onDestroy();
    }



    private void showSeekBar2(float progress) {
        if (progress < 20) {
            selectSeekBar2(0);
        } else if (progress >= 20 && progress < 40) {
            selectSeekBar2(1);
        } else if (progress >= 40 && progress < 60) {
            selectSeekBar2(2);
        } else if (progress >= 60 && progress < 80) {
            selectSeekBar2(3);
        } else if (progress >= 80 && progress <= 100) {
            selectSeekBar2(4);
        }
    }

    private void selectSeekBar2(int position) {
        unSelectStyle(tv_jiao_cha);
        unSelectStyle(tv_he_ge);
        unSelectStyle(tv_jiao_hao);
        unSelectStyle(tv_hen_hao);
        unSelectStyle(tv_you_xiu);

        switch (position) {
            case 0:
                selectStyle(tv_jiao_cha);
                break;
            case 1:
                selectStyle(tv_he_ge);
                break;
            case 2:
                selectStyle(tv_jiao_hao);
                break;
            case 3:
                selectStyle(tv_hen_hao);
                break;
            case 4:
                selectStyle(tv_you_xiu);
                break;
        }
    }


    private void showSeekBar3(float progress) {
        if (progress < 33) {
            selectSeekBar3(0);
        } else if (progress >= 33 && progress < 66) {
            selectSeekBar3(1);
        } else if (progress >= 66 && progress <= 100) {
            selectSeekBar3(2);
        }
    }

    private void selectSeekBar3(int position) {
        unSelectStyle(tv_di);
        unSelectStyle(tv_zhong);
        unSelectStyle(tv_gao);
        switch (position) {
            case 0:
                selectStyle(tv_di);
                break;
            case 1:
                selectStyle(tv_zhong);
                break;
            case 2:
                selectStyle(tv_gao);
                break;
        }
    }

    private void selectStyle(TextView textView) {
        textView.setTextColor(Color.parseColor("#ffffff"));
        textView.setBackground(getDrawable(R.drawable.blue_rect_bg));
    }

    private void unSelectStyle(TextView textView) {
        textView.setTextColor(Color.parseColor("#666666"));
        textView.setBackground(null);
    }


    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.rel_punch_card) {
            if (punchStatus == 0) { //打上课卡
                submitShangke();
            } else if (punchStatus == 1) { // 打下课卡
                submitXiake();
            }

        } else if (i == R.id.iv_finish) {
            finish();

        } else if (i == R.id.tv_pingjia) {
            postPingJia();

        }
    }
}

