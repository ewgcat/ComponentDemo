package com.yijian.staff.mvp.coach.huifang.tianxieresult;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.requestbody.huifang.AddHuiFangResultBody;
import com.yijian.staff.net.response.ResultJSONArrayObserver;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoachTianXieHuiFangResultActivity extends MvcBaseActivity implements View.OnClickListener {


    @BindView(R.id.tv_next_hui_fang_time)
    TextView tvNextHuiFangTime;
    @BindView(R.id.et_hui_fang_result)
    EditText etHuiFangResult;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.ll_next_hui_fang_time)
    LinearLayout llNextHuiFangTime;
    @BindView(R.id.tv_next_hui_fang_reason)
    TextView tvNextHuiFangReason;
    @BindView(R.id.ll_next_hui_fang_reason)
    LinearLayout llNextHuiFangReason;
    @BindView(R.id.ll_fufang)
    LinearLayout llFufang;
    @BindView(R.id.ll_huifang_result)
    LinearLayout llHuifangResult;
    @BindView(R.id.rg)
    RadioGroup rg;
    private String interviewRecordId;
    private String memberId;
    private String dictItemId;
    private boolean needReview;
    private List<CoachHuiFangReasonBean> coachHuiFangReasonBeanList = new ArrayList<>();

    @Override

    protected int getLayoutID() {
        return R.layout.activity_tian_xie_hui_fang_result;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initView();

    }

    private void initView() {
        NavigationBar2 navigationBar2 = findViewById(R.id.tian_xie_hui_fang_result_navigation_bar);
        navigationBar2.hideLeftSecondIv();
        navigationBar2.getFirstLeftIv().setVisibility(View.GONE);
        navigationBar2.setTitle("填写回访结果");
        navigationBar2.setmRightTvText("发送");
        navigationBar2.findViewById(R.id.right_tv).setOnClickListener(this);
        rb1.setChecked(true);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb1) {
                    llHuifangResult.setVisibility(View.VISIBLE);
                    llFufang.setVisibility(View.GONE);
                    needReview = false;
                } else {
                    llHuifangResult.setVisibility(View.GONE);
                    llFufang.setVisibility(View.VISIBLE);
                    needReview = true;
                }
            }
        });
        interviewRecordId = getIntent().getStringExtra("interviewRecordId");
        memberId = getIntent().getStringExtra("memberId");
        llNextHuiFangTime.setOnClickListener(this);
        llNextHuiFangReason.setOnClickListener(this);
        HttpManager.getHasHeaderNoParam(HttpManager.GET_COACH_HUI_FANG_REASON_LIST_URL, new ResultJSONArrayObserver() {
            @Override
            public void onSuccess(JSONArray result) {
                for (int i = 0; i < result.length(); i++) {
                    JSONObject jsonObject = JsonUtil.getJsonObject(result, i);
                    CoachHuiFangReasonBean coachHuiFangReasonBean = new CoachHuiFangReasonBean(jsonObject);
                    coachHuiFangReasonBeanList.add(coachHuiFangReasonBean);
                }
            }

            @Override
            public void onFail(String msg) {
                showToast(msg);
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {

            case R.id.ll_next_hui_fang_time:
                //提交结果
                TimePickerView pickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View view) {
                        String result = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
                        tvNextHuiFangTime.setText(result);
                    }
                }).build();
                pickerView.show();
                break;
            case R.id.ll_next_hui_fang_reason:
                showPickerReasonView();
                break;
            case R.id.right_tv:
                //提交结果

                sendResult();

                break;
        }
    }

    private void showPickerReasonView() {// 弹出选择器

        if (coachHuiFangReasonBeanList.size() > 0 ) {
            OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    String reason = coachHuiFangReasonBeanList.get(options1).getDictItemName();
                    tvNextHuiFangReason.setText(reason);
                    dictItemId=coachHuiFangReasonBeanList.get(options1).getDictItemId();
                }
            })

                    .setTitleText("城市选择")
                    .setDividerColor(Color.BLACK)
                    .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                    .setContentTextSize(20)
                    .build();

            pvOptions.setPicker(coachHuiFangReasonBeanList);//二级选择器
            pvOptions.show();
        }

    }

    private void sendResult() {
        AddHuiFangResultBody body = new AddHuiFangResultBody();
        body.setInterviewRecordId(interviewRecordId);
        body.setMemberId(memberId);



        body.setNeedReview(needReview);

        String time = tvNextHuiFangTime.getText().toString();
        String reason = tvNextHuiFangReason.getText().toString();
        if (needReview) {
            if (TextUtils.isEmpty(time)) {
                showToast("请选择复访时间");
                return;
            }

            if (TextUtils.isEmpty(reason)) {
                showToast("请选择复访原因");
                return;
            }
            body.setReviewTime(time);
            body.setDictItemId(dictItemId);

        }else {

            String result = etHuiFangResult.getText().toString();
            if (TextUtils.isEmpty(result)) {
                showToast("请填写回访结果");
                return;
            }
            body.setInterviewResult(result);

        }


        HttpManager.postAddHuiFangResult(body, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {

                finish();
            }

            @Override
            public void onFail(String msg) {
                showToast(msg);
            }
        });
    }

    /**
     * 监听按键的点击
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showToast("请填写回访结果，并发送");
        }
        return true;
    }

}
