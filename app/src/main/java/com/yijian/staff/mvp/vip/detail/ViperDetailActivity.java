package com.yijian.staff.mvp.vip.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jaeger.library.StatusBarUtil;
import com.yijian.staff.R;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.contract.ContractActivity;
import com.yijian.staff.mvp.huiji.bean.HuiJiVipeCardAdapter;
import com.yijian.staff.mvp.huiji.bean.HuiJiViperBean;
import com.yijian.staff.mvp.huiji.intent.HuijiIntentViperDetailActivity;
import com.yijian.staff.mvp.questionnaireresult.QuestionnaireResultActivity;
import com.yijian.staff.mvp.vip.bean.VipDetailBean;
import com.yijian.staff.mvp.vip.edit.VipInfoEditActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultObserver;
import com.yijian.staff.util.ImageLoader;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 会员个人详情页
 */
public class ViperDetailActivity extends AppCompatActivity {

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
    @BindView(R.id.tv_vip_card_num)
    TextView tvVipCardNum;
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

    VipDetailBean vipDetailBean;
    HuiJiViperBean huiJiViperBean;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        StatusBarUtil.setTranslucentForImageView(this, 0, null);

        setContentView(R.layout.activity_viper_detail);
        ButterKnife.bind(this);
        initTitle();
        initData();


        /*findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
*/

    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = findViewById(R.id.vip_intent_navigation_bar);
        navigationBar2.hideBottomLine();
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("会员详情");
    }


    @OnClick({R.id.ll_chakan_hetong, R.id.ll_chakan_wenjuan,R.id.ll_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_chakan_hetong:
                startActivity(new Intent(ViperDetailActivity.this, ContractActivity.class));

                break;
            case R.id.ll_chakan_wenjuan:
                startActivity(new Intent(ViperDetailActivity.this, QuestionnaireResultActivity.class));

                break;
            case R.id.ll_edit:
                Intent intent = new Intent(ViperDetailActivity.this, VipInfoEditActivity.class);
                intent.putExtra("detail",vipDetailBean.getDetail());
                intent.putExtra("memberId",vipDetailBean.getMemberId());
                intent.putExtra("source",vipDetailBean.getCustomerServiceInfo().getUserChannel());

                startActivity(intent);

                break;
        }
    }

    private void initData() {
        huiJiViperBean = (HuiJiViperBean) getIntent().getSerializableExtra("viperDetailBean");
        String id = huiJiViperBean.getMemberId();
        loadData(id);
    }

    private void loadData(String id){
        HashMap<String, String> header = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        header.put("token", user.getToken());

        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);

        HttpManager.getHasHeaderHasParam(HttpManager.GET_HUIJI_VIPER_DETAIL_URL,header, map, new ResultObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                vipDetailBean = com.alibaba.fastjson.JSONObject.parseObject(result.toString(),VipDetailBean.class);
                updateUi(vipDetailBean);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(ViperDetailActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUi(VipDetailBean vipDetailBean){
        ImageLoader.load(this,vipDetailBean.getHeadImg(),ivHead);
        tvName.setText(vipDetailBean.getName());
        tv_card_no.setText(vipDetailBean.getMemberCardNo());
        tvSex.setText(vipDetailBean.getSex());
        tvPhone.setText(vipDetailBean.getMobile());
        tvBirthday.setText(vipDetailBean.getBirthday());
        tvBirthdayType.setText(vipDetailBean.getBirthdayType());
        tvViperType.setText(vipDetailBean.getMemberType());
        tvVipCardNum.setText(vipDetailBean.getMemberCardNo());
        tvHeadInfo.setText((TextUtils.isEmpty(vipDetailBean.getHeadImg()))?"未录入":"未录入");
        tvZhiWenInfo.setText((TextUtils.isEmpty(vipDetailBean.getFingerprint()))?"未录入":"未录入");
        tvJinMaiInfo.setText((TextUtils.isEmpty(vipDetailBean.getVein()))?"未录入":"未录入");
        tv_certificateType.setText(vipDetailBean.getCertificateType());
        tvShenfencardNum.setText(vipDetailBean.getCertificateNo());

        //会籍信息
        rv_card.setLayoutManager(new LinearLayoutManager(this));
        rv_card.setNestedScrollingEnabled(false);
        rv_card.setAdapter(new HuiJiVipeCardAdapter(huiJiViperBean.getCardprodsBeans()));
        VipDetailBean.CustomerServiceInfoBean customerServiceInfoBean = vipDetailBean.getCustomerServiceInfo();
        tvTuijianRen.setText(customerServiceInfoBean.getReferee());
        tvTuijianRenPhone.setText(customerServiceInfoBean.getRefereeMobile());
        tvHuoquQudao.setText(customerServiceInfoBean.getUserChannel());
        tvTianjiaRenName.setText(customerServiceInfoBean.getReceptionSale());
        tvFuwuHuiji.setText(customerServiceInfoBean.getServiceSale());
        tvFuwuJiaolian.setText(customerServiceInfoBean.getServiceCoach());

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

}
