package com.yijian.staff.mvp.huiji.detail;

import android.view.View;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.huiji.bean.VipDetailBean;
import com.yijian.staff.util.DateUtil;

/**
 * Created by The_P on 2018/5/15.
 */

public class ViewHolderHuijiVipType_1 extends  ViewHolderHuijiVipper{

//    @BindView(R.id.tv_sex)
    TextView tvSex;
//    @BindView(R.id.tv_phone)
    TextView tvPhone;
//    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
//    @BindView(R.id.tv_birthday_type)
    TextView tvBirthdayType;
//    @BindView(R.id.tv_viper_type)
    TextView tvViperType;
    /*@BindView(R.id.tv_vip_card_num)
    TextView tvVipCardNum;*/
//    @BindView(R.id.tv_head_info)
    TextView tvHeadInfo;
//    @BindView(R.id.tv_zhi_wen_info)
    TextView tvZhiWenInfo;
//    @BindView(R.id.tv_jin_mai_info)
    TextView tvJinMaiInfo;
//    @BindView(R.id.tv_certificateType)
    TextView tv_certificateType;
//    @BindView(R.id.tv_shenfencard_num)
    TextView tvShenfencardNum;

    public ViewHolderHuijiVipType_1(View itemView) {
        super(itemView);
        tvSex= itemView.findViewById(R.id.tv_sex);
        tvPhone= itemView.findViewById(R.id.tv_phone);
        tvBirthday= itemView.findViewById(R.id.tv_birthday);
        tvBirthdayType= itemView.findViewById(R.id.tv_birthday_type);
        tvViperType= itemView.findViewById(R.id.tv_viper_type);
        tvHeadInfo= itemView.findViewById(R.id.tv_head_info);
        tvZhiWenInfo= itemView.findViewById(R.id.tv_zhi_wen_info);
        tvJinMaiInfo= itemView.findViewById(R.id.tv_jin_mai_info);
        tv_certificateType= itemView.findViewById(R.id.tv_certificateType);
        tvShenfencardNum= itemView.findViewById(R.id.tv_shenfencard_num);
    }

    @Override
   public void bindView(VipDetailBean vipDetailBean) {
        tvSex.setText(judgeNull(vipDetailBean.getSex()));
        tvPhone.setText(judgeNull(vipDetailBean.getMobile()));
        tvBirthday.setText(DateUtil.parseLongDateToDateString(vipDetailBean.getBirthday()));
        tvBirthdayType.setText(judgeNull(vipDetailBean.getBirthdayType()));
        tvViperType.setText(judgeNull(vipDetailBean.getMemberType()));
//        tvVipCardNum.setText(vipDetailBean.getMemberCardNo());
        tvHeadInfo.setText(judgeNull(vipDetailBean.getFaceInfo()));
        tvZhiWenInfo.setText(judgeNull(vipDetailBean.getFingerprint()));
        tvJinMaiInfo.setText(judgeNull(vipDetailBean.getVein()));
        tv_certificateType.setText(judgeNull(vipDetailBean.getCertificateType()));
        tvShenfencardNum.setText(judgeNull(vipDetailBean.getCertificateNo()));
    }
}
