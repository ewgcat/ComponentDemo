package com.yijian.staff.mvp.huifang.tianxieresult;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.yijian.staff.R;
import com.yijian.staff.bean.HuiFangInfo;
import com.yijian.staff.bean.HuiFangReasonBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.requestbody.AddFuFangResultBody;
import com.yijian.staff.net.requestbody.huifang.AddHuiFangResultBody;
import com.yijian.staff.net.response.ResultJSONArrayObserver;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.ImageLoader;
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
import butterknife.OnClick;

public class TianXieHuiFangResultActivity extends MvcBaseActivity {


    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.rel_nav)
    RelativeLayout rel_nav;
    @BindView(R.id.rel_sure)
    RelativeLayout rel_sure;

    @BindView(R.id.iv_sure_header)
    ImageView iv_sure_header;
    @BindView(R.id.tv_sure_name)
    TextView tv_sure_name;
    @BindView(R.id.iv_sure_gender)
    ImageView iv_sure_gender;
    @BindView(R.id.tv_huifan_time)
    TextView tv_huifan_time;
    @BindView(R.id.tv_huifan_reason)
    TextView tv_huifan_reason;


    @BindView(R.id.iv_nav_header)
    ImageView iv_nav_header;
    @BindView(R.id.tv_nav_name)
    TextView tv_nav_name;
    @BindView(R.id.iv_nav_gender)
    ImageView iv_nav_gender;
    @BindView(R.id.tv_huifan_type)
    TextView tv_huifan_type;
    @BindView(R.id.et_huifan_record)
    EditText et_huifan_record;


    HuiFangInfo huiFangInfo;
    boolean needReview = false;
    String tvHuifanTimeResult, tvLaifanTimeResult;
    List<HuiFangReasonBean> huiFangReasonBeanList = new ArrayList<>();
    String dictItemId;
    @BindView(R.id.tv_laifan_time)
    TextView tvLaifanTime;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_tian_xie_hui_ji_hui_fang_result;
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
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResult();
            }
        });
        updateUi();

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_nav) {
                    rel_nav.setVisibility(View.VISIBLE);
                    rel_sure.setVisibility(View.GONE);
                    needReview = false;
                } else {
                    rel_nav.setVisibility(View.GONE);
                    rel_sure.setVisibility(View.VISIBLE);
                    needReview = true;
                }
            }
        });

    }

    private void updateUi() {

        HttpManager.getHasHeaderNoParam(HttpManager.GET_COACH_HUI_FANG_REASON_LIST_URL, new ResultJSONArrayObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONArray result) {
                for (int i = 0; i < result.length(); i++) {
                    JSONObject jsonObject = JsonUtil.getJsonObject(result, i);
                    HuiFangReasonBean huiFangReasonBean = new HuiFangReasonBean(jsonObject);
                    huiFangReasonBeanList.add(huiFangReasonBean);
                }
            }

            @Override
            public void onFail(String msg) {
                showToast(msg);
            }
        });

        huiFangInfo = (HuiFangInfo) getIntent().getSerializableExtra("huiFangInfo");
        ImageLoader.setHeadImageResource(huiFangInfo.getHeadUrl(), this, iv_nav_header);
        ImageLoader.setHeadImageResource(huiFangInfo.getHeadUrl(), this, iv_sure_header);
        tv_nav_name.setText(huiFangInfo.getName());
        tv_sure_name.setText(huiFangInfo.getName());
        int resId = huiFangInfo.getGender() == 0 ? R.mipmap.lg_man : R.mipmap.lg_women;
        iv_nav_gender.setImageResource(resId);
        iv_sure_gender.setImageResource(resId);


        tv_huifan_type.setText(huiFangInfo.getInterviewName());

    }

    @OnClick({R.id.rel_huifan_time, R.id.rel_huifan_reason, R.id.rel_laifan_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.rel_huifan_time:
                new TimePickerBuilder(this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View view) {
                        Date date1 = new Date();
                        String now = new SimpleDateFormat("yyyyMMddHHmm").format(date1);
                        String selectTime = new SimpleDateFormat("yyyyMMddHHmm").format(date);
                        int i = Integer.parseInt(now);
                        int i1 = Integer.parseInt(selectTime);
                        if (i>i1) {
                            tvHuifanTimeResult = "";
                            showToast("复访时间不得小于当前时间");
                        } else {
                            tvHuifanTimeResult = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
                            tv_huifan_time.setText(time);
                        }
                    }
                }).setType(new boolean[]{true, true, true, true, true, false}).build().show();
                break;
            case R.id.rel_laifan_time:

                new TimePickerBuilder(this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View view) {

                        Date date1 = new Date();
                        String now = new SimpleDateFormat("yyyyMMddHHmm").format(date1);
                        String selectTime = new SimpleDateFormat("yyyyMMddHHmm").format(date);
                        int i = Integer.parseInt(now);
                        int i1 = Integer.parseInt(selectTime);
                        if (i>i1) {
                            tvLaifanTimeResult = "";
                            showToast("来访时间不得小于当前时间");
                        } else {
                            tvLaifanTimeResult = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
                            tvLaifanTime.setText(time);
                        }
                    }
                }).setType(new boolean[]{true, true, true, true, true, false}).build().show();
                break;
            case R.id.rel_huifan_reason:
                showPickerReasonView();
                break;

        }
    }

    private void sendResult() {

        if (needReview) {
            String reason = tv_huifan_reason.getText().toString();
            if (TextUtils.isEmpty(tvHuifanTimeResult)) {
                showToast("请选择复访时间");
                return;
            }
            if (TextUtils.isEmpty(reason)) {
                showToast("请选择复访原因");
                return;
            }
            AddFuFangResultBody addFuFangResultBody = new AddFuFangResultBody();
            addFuFangResultBody.setChief(true);
            addFuFangResultBody.setId(huiFangInfo.getId());
            addFuFangResultBody.setReviewReason(reason);
            addFuFangResultBody.setReviewTime(tvHuifanTimeResult);
        } else {
            String result = et_huifan_record.getText().toString();
            if (TextUtils.isEmpty(result)) {
                showToast("请填写回访结果");
                return;
            }
            AddHuiFangResultBody body = new AddHuiFangResultBody();
            body.setId(huiFangInfo.getId());
            body.setChief(true);
            body.setDesc(result);
            body.setVisitTime(tvLaifanTimeResult);

            HttpManager.postHuiFangResult(body, new ResultJSONObjectObserver(getLifecycle()) {
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


    }

    private void showPickerReasonView() {// 弹出选择器

        if (huiFangReasonBeanList.size() > 0) {
            OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    String reason = huiFangReasonBeanList.get(options1).getDictItemName();
                    tv_huifan_reason.setText(reason);
                    dictItemId = huiFangReasonBeanList.get(options1).getDictItemId();
                }
            }).setTitleText("回访原因").setDividerColor(Color.BLACK)
                    .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                    .setContentTextSize(20)
                    .build();

            pvOptions.setPicker(huiFangReasonBeanList);//二级选择器
            pvOptions.show();
        }

    }


}
