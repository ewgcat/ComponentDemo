package com.yijian.staff.mvp.huiji.huifang.tianxieresult;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.huiji.huifang.bean.HuiFangInfo;
import com.yijian.staff.net.httpmanager.HttpManager;
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
import butterknife.OnClick;

public class HuijiTianXieHuiFangResultActivity extends MvcBaseActivity {


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
    EditText tv_huifan_reason;


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
    String result;
    List<HuiFangReasonBean> huiFangReasonBeanList = new ArrayList<>();
    String dictItemId;


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

        HttpManager.getHasHeaderNoParam(HttpManager.GET_COACH_HUI_FANG_REASON_LIST_URL, new ResultJSONArrayObserver() {
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
        ImageLoader.setHeadImageResource(huiFangInfo.getHeadImg(), this, iv_nav_header);
        ImageLoader.setHeadImageResource(huiFangInfo.getHeadImg(), this, iv_sure_header);
        tv_nav_name.setText(huiFangInfo.getName());
        tv_sure_name.setText(huiFangInfo.getName());
        iv_nav_gender.setImageResource(huiFangInfo.getGenderImg());
        iv_sure_gender.setImageResource(huiFangInfo.getGenderImg());


        String subclassName = huiFangInfo.getSubclassName();
        String huifanType = "";
        switch (subclassName) {

            case "BirthdayVO"://生日回访
                huifanType = "生日回访";
                break;
            case "ExpireVO"://过期回访
                huifanType = "过期回访";
                break;
            case "ReVO"://复访
                huifanType = "复访";
                break;
            case "NearExpireVO"://快到期回访
                huifanType = "快到期回访";
                break;

            case "YesterdayVisitVO"://昨日到访
                huifanType = "昨日到访";
                break;
            case "ReFitVO": //恢复健身
                huifanType = "恢复健身";
                break;
            case "PotentialVO"://潜在会员
                huifanType = "潜在会员";
                break;
            case "EjoyVO"://易健平台
                huifanType = "易健平台";
                break;
            case "YesterdayOpenVO": //昨日到访
                huifanType = "昨日到访";
                break;
            case "QuietVO": //沉寂会员
                huifanType = "沉寂会员";
                break;
        }

        tv_huifan_type.setText(huifanType);

    }

    @OnClick({R.id.tv_nav_sure, R.id.tv_sure_sure, R.id.tv_sure_sure_yaoyue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_nav_sure:
                sendResult();
                break;
            case R.id.tv_sure_sure:
                sendResult();
                break;
            case R.id.tv_sure_sure_yaoyue:
                break;
            case R.id.rel_huifan_time:
                //提交结果
                TimePickerView pickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View view) {
                        result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
                        tv_huifan_time.setText(time);
                    }
                }).build();
                pickerView.show();
                break;
            case R.id.rel_huifan_reason:
                showPickerReasonView();
                break;

        }
    }

    private void sendResult() {
        AddHuiFangResultBody body = new AddHuiFangResultBody();
        body.setInterviewRecordId(huiFangInfo.getInterviewRecordId());
        body.setMemberId(huiFangInfo.getId());


        body.setNeedReview(needReview);


        String reason = et_huifan_record.getText().toString();
        if (needReview) {
            if (TextUtils.isEmpty(result)) {
                showToast("请选择复访时间");
                return;
            }

            if (TextUtils.isEmpty(reason)) {
                showToast("请选择复访原因");
                return;
            }
            body.setReviewTime(result);
            body.setDictItemId(dictItemId);

        } else {

            String result = et_huifan_record.getText().toString();
            if (TextUtils.isEmpty(result)) {
                showToast("请填写回访结果");
                return;
            }
            body.setInterviewResult(result);

        }


        HttpManager.postAddHuiJiHuiFangResult(body, new ResultJSONObjectObserver() {
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

    private void showPickerReasonView() {// 弹出选择器

        if (huiFangReasonBeanList.size() > 0) {
            OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    String reason = huiFangReasonBeanList.get(options1).getDictItemName();
                    tv_huifan_reason.setText(reason);
                    dictItemId = huiFangReasonBeanList.get(options1).getDictItemId();
                }
            })

                    .setTitleText("城市选择")
                    .setDividerColor(Color.BLACK)
                    .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                    .setContentTextSize(20)
                    .build();

            pvOptions.setPicker(huiFangReasonBeanList);//二级选择器
            pvOptions.show();
        }

    }

}
