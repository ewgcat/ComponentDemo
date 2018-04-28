package com.yijian.staff.mvp.huiji.intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.huiji.bean.VipDetailBean;
import com.yijian.staff.mvp.huiji.edit.VipInfoEditActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.ImageLoader;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONObject;

import java.util.HashMap;

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
    @BindView(R.id.tv_lianxiren_phone)
    TextView tvLianxirenPhone;

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
        navigationBar2.setTitle("意向会员");
        String id = getIntent().getStringExtra("id");
        loadData(id);
    }


    private void loadData(String id){


        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);

        HttpManager.getHasHeaderHasParam(HttpManager.GET_HUIJI_VIPER_DETAIL_URL, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                vipDetailBean = com.alibaba.fastjson.JSONObject.parseObject(result.toString(),VipDetailBean.class);
                updateUi(vipDetailBean);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(HuijiIntentViperDetailActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUi(VipDetailBean vipDetailBean){
        ImageLoader.load(this,(vipDetailBean.getHeadImg()==null)?"":vipDetailBean.getHeadImg(),ivHead);
        tvName.setText(vipDetailBean.getName());
        tvSex.setText(vipDetailBean.getSex());
        tvPhone.setText(vipDetailBean.getMobile());
        tvBirthday.setText(vipDetailBean.getBirthday());
        tvBirthdayType.setText(vipDetailBean.getBirthdayType());
        tv_certificateType.setText(vipDetailBean.getCertificateType());
        tvShenfencardNum.setText(vipDetailBean.getCertificateNo());

        //详情信息
        VipDetailBean.DetailBean detailBean = vipDetailBean.getDetail();
        tvWechatNum.setText(detailBean.getWechatNo());
        tvEmail.setText(detailBean.getEmail());
        tvShengao.setText(detailBean.getHeight());
        tvWeight.setText(detailBean.getWeight());
        tvGuaoJi.setText(detailBean.getNationality());
        tvJiguan.setText(detailBean.getNativePlace());
        tvMinzu.setText(detailBean.getNation());
        tvShentiStatus.setText(detailBean.getHealthStatus());
        tvJianshenAihao.setText(detailBean.getFitnessHobby());
        tvJianshenMudi.setText(detailBean.getFitnessGoal());
        tvXingquAihao.setText(detailBean.getHobby());
        tvShifouCanjiaClub.setText((detailBean.isOnceJoinedClub())?"是":"否");
        tvCanjiaClubName.setText(detailBean.getClubBrand());
        tvYearIncome.setText(detailBean.getYearIncome());
        tvCarValue.setText(detailBean.getCarPrice());
        tvMarryStatus.setText(detailBean.getMarriageStatus());
        tvChildrenStatus.setText(detailBean.getChildrenStatus());
        tvHangye.setText(detailBean.getOccupation());
        tvZhiwu.setText(detailBean.getPosition());
        tvCompanyName.setText(detailBean.getCompany());
        tvCompanyPhone.setText(detailBean.getCompanyPhone());
        tvCompanyLocation.setText(detailBean.getCompanyAddress());
        tvJinJiLianxiren.setText(detailBean.getUrgentContact());
        tvLianxirenPhone.setText(detailBean.getCompanyPhone());

    }

    @OnClick({R.id.ll_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_edit:
                Intent intent = new Intent(HuijiIntentViperDetailActivity.this, VipInfoEditActivity.class);
                intent.putExtra("detail",vipDetailBean.getDetail());
                intent.putExtra("memberId",vipDetailBean.getMemberId());
                intent.putExtra("source",vipDetailBean.getCustomerServiceInfo().getUserChannel());

                startActivity(intent);

                break;
        }
    }


}
