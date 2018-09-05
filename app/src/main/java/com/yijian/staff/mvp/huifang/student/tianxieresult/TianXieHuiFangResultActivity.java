package com.yijian.staff.mvp.huifang.student.tianxieresult;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.yijian.staff.BuildConfig;
import com.yijian.staff.R;
import com.yijian.staff.net.requestbody.AbortFuFangBody;
import com.yijian.staff.bean.HuiFangInfo;
import com.yijian.staff.bean.HuiFangReasonBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.requestbody.huifang.AddHuiFangResultBody;
import com.yijian.staff.net.response.ResultBooleanObserver;
import com.yijian.staff.net.response.ResultJSONArrayObserver;
import com.yijian.staff.util.DateUtil;
import com.yijian.staff.util.ImageLoader;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
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
    @BindView(R.id.tv_fufan_time)
    TextView tv_fufan_time;
    @BindView(R.id.tv_fufan_reason)
    TextView tv_fufan_reason;


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
    private String fufangTime, laifangTime;
    List<HuiFangReasonBean> huiFangReasonBeanList = new ArrayList<>();
    String dictItemId;
    @BindView(R.id.tv_laifan_time)
    TextView tvLaifanTime;
    @BindView(R.id.tv_vip_type)
    TextView tvVipType;
    @BindView(R.id.tv_can_input_number)
    TextView tvCanInputNumber;

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
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("填写记录");
        navigationBar2.setmRightTvText("确定");
        navigationBar2.setmRightTvColor(Color.parseColor("#1997f8"));
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
        et_huifan_record.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = s.toString();

                if (!TextUtils.isEmpty(s1)) {
                    int num = 140 - s1.length();
                    tvCanInputNumber.setText(num + "字");
                }else {
                    tvCanInputNumber.setText( "140字");
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
        ImageLoader.setHeadImageResource(BuildConfig.FILE_HOST+huiFangInfo.getHeadUrl(), this, iv_nav_header);
        ImageLoader.setHeadImageResource(BuildConfig.FILE_HOST+huiFangInfo.getHeadUrl(), this, iv_sure_header);
        tv_nav_name.setText(huiFangInfo.getName());
        tv_sure_name.setText(huiFangInfo.getName());
        int resId = huiFangInfo.getGender() == 0 ? R.mipmap.lg_man : R.mipmap.lg_women;
        iv_nav_gender.setImageResource(resId);
        iv_sure_gender.setImageResource(resId);


        tv_huifan_type.setText(huiFangInfo.getInterviewName());
        tvVipType.setText(huiFangInfo.getMemberTypeName());
    }

    @OnClick({R.id.rel_huifan_time, R.id.rel_huifan_reason, R.id.rel_laifan_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.rel_huifan_time:

                Calendar c = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker dp, int year, int month, int dayOfMonth) {

                                String time = "";
                                if (month < 9 && dayOfMonth < 10) {
                                    time += year + "-0" + (month + 1) + "-0" + dayOfMonth;
                                } else if (month > 9 && dayOfMonth > 10) {
                                    time += year + "-" + (month + 1) + "-" + dayOfMonth;
                                } else if (month < 9 && dayOfMonth > 10) {
                                    time += year + "-0" + (month + 1) + "-" + dayOfMonth;
                                } else if (month > 9 && dayOfMonth < 10) {
                                    time += year + "-" + (month + 1) + "-0" + dayOfMonth;
                                }


                                String s = "" + DateUtil.getCurrentYear() + DateUtil.getCurrentMonth() + DateUtil.getCurrentDay();
                                String s1 = "" + year + month + dayOfMonth;
                                if (Integer.parseInt(s1) >= Integer.parseInt(s)) {
                                    fufangTime = time;
                                    tv_fufan_time.setText(time);
                                } else {
                                    fufangTime = "";
                                    tv_fufan_time.setText("");
                                    showToast("复访时间不得小于当前时间");
                                }
                            }
                        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH));
                dialog.show();
                break;
            case R.id.rel_laifan_time:


                Calendar c1 = Calendar.getInstance();
                new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker dp, int year, int month, int dayOfMonth) {

                                String time = "";
                                if (month < 9 && dayOfMonth < 10) {
                                    time += year + "-0" + (month + 1) + "-0" + dayOfMonth;
                                } else if (month > 9 && dayOfMonth > 10) {
                                    time += year + "-" + (month + 1) + "-" + dayOfMonth;
                                } else if (month < 9 && dayOfMonth > 10) {
                                    time += year + "-0" + (month + 1) + "-" + dayOfMonth;
                                } else if (month > 9 && dayOfMonth < 10) {
                                    time += year + "-" + (month + 1) + "-0" + dayOfMonth;
                                }


                                String s = "" + DateUtil.getCurrentYear() + DateUtil.getCurrentMonth() + DateUtil.getCurrentDay();
                                String s1 = "" + year + month + dayOfMonth;
                                if (Integer.parseInt(s1) >= Integer.parseInt(s)) {
                                    laifangTime = time;
                                    tvLaifanTime.setText(time);
                                } else {
                                    laifangTime = "";
                                    showToast("来访时间不得小于当前时间");
                                }

                            }
                        }, c1.get(Calendar.YEAR), c1.get(Calendar.MONTH),
                        c1.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.rel_huifan_reason:
                showPickerReasonView();
                break;

        }
    }

    private void sendResult() {

        if (needReview) {
            String reason = tv_fufan_reason.getText().toString().trim();
            if (TextUtils.isEmpty(fufangTime)) {
                showToast("请选择复访时间");
                return;
            }
            if (TextUtils.isEmpty(reason)) {
                showToast("请选择复访原因");
                return;
            }
            AbortFuFangBody body = new AbortFuFangBody();
            body.setChief(true);
            body.setId(huiFangInfo.getId());
            body.setReviewReason(reason);
            body.setReviewTime(fufangTime);
            HttpManager.postAbortFuFang(body, new ResultBooleanObserver(getLifecycle()) {
                @Override
                public void onSuccess(Boolean result) {
                    if (result){
                        hideKeyBoard(et_huifan_record);
                        finish();
                    }else {
                        showToast("保存失败");
                    }

                }

                @Override
                public void onFail(String msg) {
                    showToast(msg);
                }
            });

        } else {

            String result = et_huifan_record.getText().toString().trim();
            if (TextUtils.isEmpty(result)) {
                showToast("请填写回访结果");
                return;
            }
            AddHuiFangResultBody body = new AddHuiFangResultBody();
            body.setId(huiFangInfo.getId());
            body.setChief(true);
            body.setDesc(result);
            body.setVisitTime(laifangTime);

            HttpManager.postHuiFangResult(body, new ResultBooleanObserver(getLifecycle()) {
                @Override
                public void onSuccess(Boolean result) {
                    if (result){
                        hideKeyBoard(et_huifan_record);
                        finish();
                    }else {
                        showToast("保存失败");
                    }
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
                    tv_fufan_reason.setText(reason);
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
