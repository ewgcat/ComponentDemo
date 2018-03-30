package com.yijian.staff.mvp.vip.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.yijian.staff.R;
import com.yijian.staff.mvp.contract.ContractActivity;
import com.yijian.staff.mvp.questionnaireresult.QuestionnaireResultActivity;
import com.yijian.staff.mvp.vip.edit.VipInfoEditActivity;

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
    @BindView(R.id.tv_card_type)
    TextView tvCardType;
    @BindView(R.id.tv_shenfencard_num)
    TextView tvShenfencardNum;
    @BindView(R.id.tv_tuijian_ren)
    TextView tvTuijianRen;
    @BindView(R.id.tv_tuijian_ren_phone)
    TextView tvTuijianRenPhone;
    @BindView(R.id.tv_huozhi_qudao)
    TextView tvHuozhiQudao;
    @BindView(R.id.tv_tianjia_ren_name)
    TextView tvTianjiaRenName;
    @BindView(R.id.tv_fuwu_huiji)
    TextView tvFuwuHuiji;
    @BindView(R.id.tv_fuwu_jiaolian)
    TextView tvFuwuJiaolian;
    @BindView(R.id.tv_wechat_num)
    TextView tvWechatNum;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_shengao)
    TextView tvShengao;
    @BindView(R.id.tv_weight)
    TextView tvWeight;
    @BindView(R.id.tv_guiji)
    TextView tvGuiji;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTranslucentForImageView(this, 0, null);

        setContentView(R.layout.activity_viper_detail);
        ButterKnife.bind(this);


        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


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
                startActivity(new Intent(ViperDetailActivity.this, VipInfoEditActivity.class));

                break;
        }
    }


}
