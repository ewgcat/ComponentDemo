package com.yijian.staff.mvp.coach.detail;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.bean.CoachVipDetailBean;

import butterknife.BindView;

/**
 * Created by The_P on 2018/5/17.
 */

public class ViewHolderCoachVipType_3 extends ViewHolderCoachVipper {
    //详细信息
//    @BindView(R.id.tv_wechat_num)
    TextView tvWechatNum;
//    @BindView(R.id.tv_email)
    TextView tvEmail;
//    @BindView(R.id.tv_shengao)
    TextView tvShengao;
//    @BindView(R.id.tv_weight)
    TextView tvWeight;
//    @BindView(R.id.tv_guaoji)
    TextView tvGuaoJi;
//    @BindView(R.id.tv_jiguan)
    TextView tvJiguan;
//    @BindView(R.id.tv_minzu)
    TextView tvMinzu;
//    @BindView(R.id.tv_shenti_status)
    TextView tvShentiStatus;
//    @BindView(R.id.tv_jianshen_aihao)
    TextView tvJianshenAihao;
//    @BindView(R.id.tv_jianshen_mudi)
    TextView tvJianshenMudi;
//    @BindView(R.id.tv_xingqu_aihao)
    TextView tvXingquAihao;
//    @BindView(R.id.tv_shifou_canjia_club)
    TextView tvShifouCanjiaClub;
//    @BindView(R.id.tv_canjia_clubName)
    TextView tvCanjiaClubName;
//    @BindView(R.id.tv_year_income)
    TextView tvYearIncome;
//    @BindView(R.id.tv_car_value)
    TextView tvCarValue;
//    @BindView(R.id.tv_marry_status)
    TextView tvMarryStatus;
//    @BindView(R.id.tv_children_status)
    TextView tvChildrenStatus;
//    @BindView(R.id.tv_hangye)
    TextView tvHangye;
//    @BindView(R.id.tv_zhiwu)
    TextView tvZhiwu;
//    @BindView(R.id.tv_company_name)
    TextView tvCompanyName;
//    @BindView(R.id.tv_company_phone)
    TextView tvCompanyPhone;
//    @BindView(R.id.tv_company_location)
    TextView tvCompanyLocation;
//    @BindView(R.id.tv_home_location)
    TextView tvHomeLocation;
//    @BindView(R.id.tv_jin_ji_lianxiren)
    TextView tvJinJiLianxiren;
//    @BindView(R.id.tv_lianxiren_phone)
    TextView tvLianxirenPhone;

    LinearLayout llEdit;
    public ViewHolderCoachVipType_3(View itemView) {
        super(itemView);
        tvWechatNum=  itemView.findViewById(R.id.tv_wechat_num);
        tvEmail=  itemView.findViewById(R.id.tv_email);
        tvShengao=  itemView.findViewById(R.id.tv_shengao);
        tvWeight=  itemView.findViewById(R.id.tv_weight);
        tvGuaoJi=  itemView.findViewById(R.id.tv_guaoji);
        tvJiguan=  itemView.findViewById(R.id.tv_jiguan);
        tvMinzu=  itemView.findViewById(R.id.tv_minzu);
        tvShentiStatus=  itemView.findViewById(R.id.tv_shenti_status);
        tvJianshenAihao=  itemView.findViewById(R.id.tv_jianshen_aihao);
        tvJianshenMudi=  itemView.findViewById(R.id.tv_jianshen_mudi);
        tvXingquAihao=  itemView.findViewById(R.id.tv_xingqu_aihao);
        tvShifouCanjiaClub=  itemView.findViewById(R.id.tv_shifou_canjia_club);
        tvCanjiaClubName=  itemView.findViewById(R.id.tv_canjia_clubName);
        tvYearIncome=  itemView.findViewById(R.id.tv_year_income);
        tvCarValue=  itemView.findViewById(R.id.tv_car_value);
        tvMarryStatus=  itemView.findViewById(R.id.tv_marry_status);
        tvChildrenStatus=  itemView.findViewById(R.id.tv_children_status);
        tvHangye=  itemView.findViewById(R.id.tv_hangye);
        tvZhiwu=  itemView.findViewById(R.id.tv_zhiwu);
        tvCompanyName=  itemView.findViewById(R.id.tv_company_name);
        tvCompanyPhone=  itemView.findViewById(R.id.tv_company_phone);
        tvCompanyLocation=  itemView.findViewById(R.id.tv_company_location);
        tvHomeLocation=  itemView.findViewById(R.id.tv_home_location);
        tvJinJiLianxiren=  itemView.findViewById(R.id.tv_jin_ji_lianxiren);
        tvLianxirenPhone=  itemView.findViewById(R.id.tv_lianxiren_phone);

         llEdit = itemView.findViewById(R.id.ll_edit);
    }

    @Override
    public void bindView(CoachVipDetailBean coachVipDetailBean) {

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

        llEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (huijiVipInterface!=null)huijiVipInterface.edit();
            }
        });
    }

}
