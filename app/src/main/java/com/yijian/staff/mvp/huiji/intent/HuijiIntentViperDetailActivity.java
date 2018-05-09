package com.yijian.staff.mvp.huiji.intent;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.huiji.bean.VipDetailBean;
import com.yijian.staff.mvp.huiji.edit.HuiJiVipInfoEditActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.DateUtil;
import com.yijian.staff.util.ImageLoader;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 潜在会员或意向会员 基本信息
 */
public class HuijiIntentViperDetailActivity extends MvcBaseActivity {


    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_birthday_type)
    TextView tvBirthdayType;
    @BindView(R.id.tv_certificateType)
    TextView tv_certificateType;
    @BindView(R.id.tv_shenfencard_num)
    TextView tvShenfencardNum;

    //详细信息
    @BindView(R.id.tv_wechat_num)
    TextView tvWechatNum;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_shengao)
    TextView tvShengao;
    @BindView(R.id.tv_weight)
    TextView tvWeight;
    @BindView(R.id.tv_guaoji)
    TextView tvGuaoJi;
    @BindView(R.id.tv_jiguan)
    TextView tvJiguan;
    @BindView(R.id.tv_minzu)
    TextView tvMinzu;
    @BindView(R.id.tv_shenti_status)
    TextView tvShentiStatus;
    @BindView(R.id.tv_jianshen_aihao)
    TextView tvJianshenAihao;
    @BindView(R.id.tv_jianshen_mudi)
    TextView tvJianshenMudi;
    @BindView(R.id.tv_xingqu_aihao)
    TextView tvXingquAihao;
    @BindView(R.id.tv_shifou_canjia_club)
    TextView tvShifouCanjiaClub;
    @BindView(R.id.tv_canjia_clubName)
    TextView tvCanjiaClubName;
    @BindView(R.id.tv_year_income)
    TextView tvYearIncome;
    @BindView(R.id.tv_car_value)
    TextView tvCarValue;
    @BindView(R.id.tv_marry_status)
    TextView tvMarryStatus;
    @BindView(R.id.tv_children_status)
    TextView tvChildrenStatus;
    @BindView(R.id.tv_hangye)
    TextView tvHangye;
    @BindView(R.id.tv_zhiwu)
    TextView tvZhiwu;
    @BindView(R.id.tv_company_name)
    TextView tvCompanyName;
    @BindView(R.id.tv_company_phone)
    TextView tvCompanyPhone;
    @BindView(R.id.tv_company_location)
    TextView tvCompanyLocation;
    @BindView(R.id.tv_home_location)
    TextView tvHomeLocation;
    @BindView(R.id.tv_jin_ji_lianxiren)
    TextView tvJinJiLianxiren;


    VipDetailBean vipDetailBean;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_potential_and_intent_viper_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        NavigationBar2 navigationBar2 = findViewById(R.id.navigation_bar);
        navigationBar2.hideBottomLine();
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("会员详情");

        loadData();
    }


    private void loadData() {
        String id = getIntent().getStringExtra("id");

        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);

        HttpManager.getHasHeaderHasParam(HttpManager.GET_VIPER_DETAIL_URL, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                vipDetailBean = com.alibaba.fastjson.JSONObject.parseObject(result.toString(), VipDetailBean.class);
                updateUi(vipDetailBean);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(HuijiIntentViperDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUi(VipDetailBean vipDetailBean) {
        ImageLoader.setImageResource((vipDetailBean.getHeadImg() == null) ? "" : vipDetailBean.getHeadImg(), this, ivHead);
        tvName.setText(judgeNull(vipDetailBean.getName()));
        tvSex.setText(judgeNull(vipDetailBean.getSex()));
        tvPhone.setText(judgeNull(vipDetailBean.getMobile()));
        tvBirthday.setText(DateUtil.parseLongDateToDateString(vipDetailBean.getBirthday()));
        tvBirthdayType.setText(judgeNull(vipDetailBean.getBirthdayType()));
        tv_certificateType.setText(judgeNull(vipDetailBean.getCertificateType()));
        tvShenfencardNum.setText(judgeNull(vipDetailBean.getCertificateNo()));

        //详情信息
        VipDetailBean.DetailBean detailBean = vipDetailBean.getDetail();
        tvWechatNum.setText(judgeNull(detailBean.getWechatNo()));
        tvEmail.setText(judgeNull(detailBean.getEmail()));
        tvShengao.setText(judgeNull(detailBean.getHeight()));
        tvWeight.setText(judgeNull(detailBean.getWeight()));
        tvGuaoJi.setText(judgeNull(detailBean.getNationality()));
        tvJiguan.setText(judgeNull(detailBean.getNativePlace()));
        tvMinzu.setText(judgeNull(detailBean.getNation()));
        tvShentiStatus.setText(judgeNull(detailBean.getHealthStatus()));
        tvJianshenAihao.setText(judgeNull(detailBean.getFitnessHobby()));
        tvJianshenMudi.setText(judgeNull(detailBean.getFitnessGoal()));
        tvXingquAihao.setText(judgeNull(detailBean.getHobby()));
        tvShifouCanjiaClub.setText((detailBean.isOnceJoinedClub()) ? "是" : "否");
        tvCanjiaClubName.setText(judgeNull(detailBean.getClubBrand()));
        tvYearIncome.setText(judgeNull(detailBean.getYearIncome()));
        tvCarValue.setText(judgeNull(detailBean.getCarPrice()));
        tvMarryStatus.setText(judgeNull(detailBean.getMarriageStatus()));
        tvChildrenStatus.setText(judgeNull(detailBean.getChildrenStatus()));
        tvHangye.setText(judgeNull(detailBean.getOccupation()));
        tvZhiwu.setText(judgeNull(detailBean.getPosition()));
        tvCompanyName.setText(judgeNull(detailBean.getCompany()));
        tvCompanyPhone.setText(judgeNull(detailBean.getCompanyPhone()));
        tvCompanyLocation.setText(judgeNull(detailBean.getCompanyAddress()));
        tvJinJiLianxiren.setText(judgeNull(detailBean.getUrgentContact()));
        tvHomeLocation.setText(judgeNull(detailBean.getAddress()));
    }

    private String judgeNull(String str) {
        return TextUtils.isEmpty(str) ? "未录入" : str;
    }

    @OnClick({R.id.ll_edit, R.id.ll_chakan_wenjuan, R.id.iv_visit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_edit:
                Intent intent = new Intent(HuijiIntentViperDetailActivity.this, HuiJiVipInfoEditActivity.class);
                intent.putExtra("detail", vipDetailBean.getDetail());
                intent.putExtra("memberId", vipDetailBean.getMemberId());
                intent.putExtra("source", vipDetailBean.getCustomerServiceInfo().getUserChannel());
                intent.putExtra("name", vipDetailBean.getName());
                startActivityForResult(intent, 0);

                break;
            case R.id.ll_chakan_wenjuan: //邀约

                break;
            case R.id.iv_visit: //回访
                String mobile = vipDetailBean.getMobile();
                if (!TextUtils.isEmpty(mobile)){
                    callVisit(mobile);
                } else {
                    Toast.makeText(this,"未录入手机号,无法进行电话回访",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void callVisit(String mobile){
        Map<String,String> map = new HashMap<>();
        map.put("memberId",vipDetailBean.getMemberId());
        map.put("dictItemKey",getIntent().getIntExtra("dictItemKey",0)+"");
        HttpManager.getHasHeaderHasParam(HttpManager.HUIJI_HUIFANG_CALL_RECORD, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                CommonUtil.callPhone(HuijiIntentViperDetailActivity.this,mobile);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(HuijiIntentViperDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 1) {
            loadData();
        }
    }


}
