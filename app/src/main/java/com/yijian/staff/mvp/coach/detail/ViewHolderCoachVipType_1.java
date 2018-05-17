package com.yijian.staff.mvp.coach.detail;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.bean.CoachVipDetailBean;
import com.yijian.staff.util.DateUtil;

import butterknife.BindView;

/**
 * Created by The_P on 2018/5/17.
 */

public class ViewHolderCoachVipType_1 extends ViewHolderCoachVipper {
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
    public ViewHolderCoachVipType_1(View itemView) {
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
    public void bindView(CoachVipDetailBean coachVipDetailBean) {
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
    }

}
