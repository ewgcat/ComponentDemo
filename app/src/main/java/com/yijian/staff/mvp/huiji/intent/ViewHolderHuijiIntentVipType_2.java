package com.yijian.staff.mvp.huiji.intent;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.application.CustomApplication;
import com.yijian.staff.mvp.huiji.bean.VipDetailBean;
import com.yijian.staff.mvp.huiji.detail.ViewHolderHuijiVipper;
import com.yijian.staff.mvp.user.permission.PermissionUtils;

import butterknife.BindView;

/**
 * Created by The_P on 2018/5/16.
 */

class ViewHolderHuijiIntentVipType_2 extends ViewHolderHuijiVipper {
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

    TextView tvLianXiPhone;
    TextView tvHuoquQudao;
    LinearLayout llEdit;

    public ViewHolderHuijiIntentVipType_2(View itemView) {
        super(itemView);
        tvWechatNum = itemView.findViewById(R.id.tv_wechat_num);
        tvEmail = itemView.findViewById(R.id.tv_email);
        tvShengao = itemView.findViewById(R.id.tv_shengao);
        tvWeight = itemView.findViewById(R.id.tv_weight);
        tvGuaoJi = itemView.findViewById(R.id.tv_guaoji);

        tvJiguan = itemView.findViewById(R.id.tv_jiguan);
        tvMinzu = itemView.findViewById(R.id.tv_minzu);
        tvShentiStatus = itemView.findViewById(R.id.tv_shenti_status);
        tvJianshenAihao = itemView.findViewById(R.id.tv_jianshen_aihao);
        tvJianshenMudi = itemView.findViewById(R.id.tv_jianshen_mudi);

        tvXingquAihao = itemView.findViewById(R.id.tv_xingqu_aihao);
        tvShifouCanjiaClub = itemView.findViewById(R.id.tv_shifou_canjia_club);
        tvCanjiaClubName = itemView.findViewById(R.id.tv_canjia_clubName);
        tvYearIncome = itemView.findViewById(R.id.tv_year_income);
        tvCarValue = itemView.findViewById(R.id.tv_car_value);

        tvMarryStatus = itemView.findViewById(R.id.tv_marry_status);
        tvChildrenStatus = itemView.findViewById(R.id.tv_children_status);
        tvHangye = itemView.findViewById(R.id.tv_hangye);
        tvZhiwu = itemView.findViewById(R.id.tv_zhiwu);
        tvCompanyName = itemView.findViewById(R.id.tv_company_name);

        tvCompanyPhone = itemView.findViewById(R.id.tv_company_phone);
        tvCompanyLocation = itemView.findViewById(R.id.tv_company_location);
        tvHomeLocation = itemView.findViewById(R.id.tv_home_location);
        tvJinJiLianxiren = itemView.findViewById(R.id.tv_jin_ji_lianxiren);
        tvLianXiPhone = itemView.findViewById(R.id.tv_lianxiren_phone);
        tvHuoquQudao = itemView.findViewById(R.id.tv_huoqu_qudao);
        llEdit = itemView.findViewById(R.id.ll_edit);
    }

    @Override
    public void bindView(VipDetailBean vipDetailBean) {
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
        tvLianXiPhone.setText(judgeNull(detailBean.getContactPhone()));
        VipDetailBean.CustomerServiceInfoBean customerServiceInfoBean = vipDetailBean.getCustomerServiceInfo();
        tvHuoquQudao.setText(judgeNull(customerServiceInfoBean.getUserChannel()));
        boolean allEditable = PermissionUtils.getInstance().isEdit(CustomApplication.getInstance(), "app_workbench", "app_formal_member");
        if (allEditable) {
            llEdit.setVisibility(vipDetailBean.isEditEnable() ? View.VISIBLE : View.GONE);
        } else {
            llEdit.setVisibility(View.GONE);
        }
        llEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (huijiVipInterface != null) huijiVipInterface.edit();
            }
        });
    }
}
