package com.yijian.staff.mvp.coach.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.bean.CoachVipDetailBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.coach.card.CoachVipCardListAdapter;
import com.yijian.staff.mvp.coach.detail.edit.CoachVipInfoEditActivity;
import com.yijian.staff.mvp.reception.physical.PhysicalReportActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.DateUtil;
import com.yijian.staff.util.ImageLoader;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 会员个人详情页
 */
public class CoachViperDetailActivity extends MvcBaseActivity {

    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_card_no)
    TextView tv_card_no;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_birthday_type)
    TextView tvBirthdayType;
    @BindView(R.id.tv_viper_type)
    TextView tvViperType;
    @BindView(R.id.tv_head_info)
    TextView tvHeadInfo;
    @BindView(R.id.tv_zhi_wen_info)
    TextView tvZhiWenInfo;
    @BindView(R.id.tv_jin_mai_info)
    TextView tvJinMaiInfo;
    @BindView(R.id.tv_certificateType)
    TextView tv_certificateType;
    @BindView(R.id.tv_shenfencard_num)
    TextView tvShenfencardNum;
    //会籍信息
    @BindView(R.id.rv_card)
    RecyclerView rv_card;
    @BindView(R.id.tv_tuijian_ren)
    TextView tvTuijianRen;
    @BindView(R.id.tv_tuijian_ren_phone)
    TextView tvTuijianRenPhone;
    @BindView(R.id.tv_huoqu_qudao)
    TextView tvHuoquQudao;
    @BindView(R.id.tv_tianjia_ren_name)
    TextView tvTianjiaRenName;
    @BindView(R.id.tv_fuwu_huiji)
    TextView tvFuwuHuiji;
    @BindView(R.id.tv_fuwu_jiaolian)
    TextView tvFuwuJiaolian;
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

    CoachVipDetailBean coachVipDetailBean;
    //会籍信息
    @BindView(R.id.ll_vip_content)
    LinearLayout llVipContent;
    @BindView(R.id.ll_1)
    LinearLayout ll_1;
    @BindView(R.id.lin_vip_people_bottom)
    LinearLayout lin_vip_people_bottom;

    @BindView(R.id.ll_3)
    LinearLayout ll_3;
    @BindView(R.id.tv_sijiao_class)
    TextView tvSijiaoClass;
    @BindView(R.id.rl_sijiao_class)
    RelativeLayout rlSijiaoClass;

    private int vipType = 0;//0 正式会员 、1、意向会员（有会籍信息）  2、 潜在会员3、 过期会员（无会籍信息）;
    private List<CoachVipDetailBean.CardprodsBean> cardprodsBeans;
    private String memberName;
    private String memberId;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_coach_viper_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initTitle();
        initData();
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = findViewById(R.id.vip_detail_navigation_bar2);
        navigationBar2.hideBottomLine();
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("会员详情");
        memberId = getIntent().getStringExtra("memberId");
        memberName = getIntent().getStringExtra("memberName");
    }


    @OnClick({R.id.lin_ti_ce_shu_ju, R.id.iv_call, R.id.lin_baojia, R.id.ll_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.lin_ti_ce_shu_ju:
                Intent intent1 = new Intent(CoachViperDetailActivity.this, PhysicalReportActivity.class);
                intent1.putExtra("memberId", memberId);
                intent1.putExtra("memberName", memberName);
                startActivity(intent1);
                break;
            case R.id.lin_baojia:
                Intent intent2 = new Intent(CoachViperDetailActivity.this, NoSearchBarCoachClassBaojiaActivity.class);
                intent2.putExtra("memberId", memberId);
                intent2.putExtra("memberName", memberName);
                startActivity(intent2);
                break;

            case R.id.iv_call:
                String mobile = coachVipDetailBean.getMobile();
                if (!TextUtils.isEmpty(mobile)) {
                    if (CommonUtil.isPhoneFormat(mobile)) {
                        CommonUtil.callPhone(CoachViperDetailActivity.this, mobile);
                    } else {
                        showToast("返回的手机号不正确！");
                    }
                } else {
                    showToast("未录入手机号！");
                }
                break;
            case R.id.ll_edit:
                Intent intent = new Intent(CoachViperDetailActivity.this, CoachVipInfoEditActivity.class);
                if(coachVipDetailBean!=null){
                    intent.putExtra("detail", coachVipDetailBean.getDetail());
                    intent.putExtra("memberId", coachVipDetailBean.getMemberId());
                    intent.putExtra("name", coachVipDetailBean.getName());
                    CoachVipDetailBean.CustomerServiceInfoBean customerServiceInfo = coachVipDetailBean.getCustomerServiceInfo();
                    if (customerServiceInfo!=null){
                        intent.putExtra("source", customerServiceInfo.getUserChannel());
                    }
                    startActivityForResult(intent,1111);
                }

                break;

        }
    }

    private void initData() {


        vipType = getIntent().getIntExtra("vipType", 0);
        if (vipType == 0 || vipType == 2 || vipType == 3) {
            llVipContent.setVisibility(View.VISIBLE);
            if (vipType == 0) {
                lin_vip_people_bottom.setVisibility(View.VISIBLE);
                ll_1.setVisibility(View.VISIBLE);
                ll_3.setVisibility(View.GONE);
            } else {
                lin_vip_people_bottom.setVisibility(View.GONE);
            }
        } else if (vipType == 1) {//意向会员
            lin_vip_people_bottom.setVisibility(View.VISIBLE);
            llVipContent.setVisibility(View.GONE);
            ll_1.setVisibility(View.GONE);
            ll_3.setVisibility(View.VISIBLE);
        }
        lin_vip_people_bottom.setVisibility(View.GONE);

        loadData(memberId);

    }

    private void loadData(String id) {

        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);
        showBlueProgress();
        HttpManager.getHasHeaderHasParam(HttpManager.GET_VIPER_DETAIL_URL, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                coachVipDetailBean = new CoachVipDetailBean(result);
                updateUi(coachVipDetailBean);
                hideBlueProgress();
            }

            @Override
            public void onFail(String msg) {
                showToast(msg);
                hideBlueProgress();
            }
        });
    }

    private void updateUi(CoachVipDetailBean coachVipDetailBean) {
        ImageLoader.setImageResource(coachVipDetailBean.getHeadImg(), this, ivHead);


        tvName.setText(coachVipDetailBean.getName());
        tv_card_no.setText(coachVipDetailBean.getMemberCardNo());
        tvSex.setText(coachVipDetailBean.getSex());
        tvPhone.setText(coachVipDetailBean.getMobile());
        Long birthday = coachVipDetailBean.getBirthday();
        if (birthday != null && birthday != -1) {
            String s = DateUtil.parseLongDateToDateString(birthday);
            tvBirthday.setText(s);
        }
        tvBirthdayType.setText(coachVipDetailBean.getBirthdayType());
        tvViperType.setText(coachVipDetailBean.getMemberType());
        tvHeadInfo.setText((TextUtils.isEmpty(coachVipDetailBean.getHeadImg())) ? "未录入" : "未录入");
        tvZhiWenInfo.setText((TextUtils.isEmpty(coachVipDetailBean.getFingerprint())) ? "未录入" : "未录入");
        tvJinMaiInfo.setText((TextUtils.isEmpty(coachVipDetailBean.getVein())) ? "未录入" : "未录入");
        tv_certificateType.setText(coachVipDetailBean.getCertificateType());
        tvShenfencardNum.setText(coachVipDetailBean.getCertificateNo());

        //会籍信息
        rv_card.setLayoutManager(new LinearLayoutManager(this));
        rv_card.setNestedScrollingEnabled(false);
        cardprodsBeans = coachVipDetailBean.getCardprods();
        rv_card.setAdapter(new CoachVipCardListAdapter(cardprodsBeans));
        CoachVipDetailBean.CustomerServiceInfoBean customerServiceInfoBean = coachVipDetailBean.getCustomerServiceInfo();
        tvTuijianRen.setText(customerServiceInfoBean.getReferee());
        tvTuijianRenPhone.setText(customerServiceInfoBean.getRefereeMobile());
        tvHuoquQudao.setText(customerServiceInfoBean.getUserChannel());
        tvTianjiaRenName.setText(customerServiceInfoBean.getReceptionSale());
        tvFuwuHuiji.setText(customerServiceInfoBean.getServiceSale());
        tvFuwuJiaolian.setText(customerServiceInfoBean.getServiceCoach());
        List<String> privateCourses = customerServiceInfoBean.getPrivateCourses();
        if (privateCourses != null && privateCourses.size() > 0) {
            rlSijiaoClass.setVisibility(View.VISIBLE);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < privateCourses.size(); i++) {
                String s = privateCourses.get(i) + " ";
                sb.append(s);
            }
            tvSijiaoClass.setText(sb.toString());
        } else {
            rlSijiaoClass.setVisibility(View.GONE);
        }

        //详情信息
        CoachVipDetailBean.DetailBean detailBean = coachVipDetailBean.getDetail();
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
        tvShifouCanjiaClub.setText((detailBean.isOnceJoinedClub()) ? "是" : "否");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1234) {
            loadData(memberId);
        }
    }
}
