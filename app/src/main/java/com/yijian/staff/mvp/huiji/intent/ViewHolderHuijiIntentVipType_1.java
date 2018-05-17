package com.yijian.staff.mvp.huiji.intent;

import android.view.View;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.huiji.bean.VipDetailBean;
import com.yijian.staff.mvp.huiji.detail.ViewHolderHuijiVipper;
import com.yijian.staff.util.DateUtil;

import butterknife.BindView;

/**
 * Created by The_P on 2018/5/16.
 */

class ViewHolderHuijiIntentVipType_1 extends ViewHolderHuijiVipper {
//    @BindView(R.id.tv_sex)
    TextView tvSex;
//    @BindView(R.id.tv_phone)
    TextView tvPhone;
//    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
//    @BindView(R.id.tv_birthday_type)
    TextView tvBirthdayType;
//    @BindView(R.id.tv_certificateType)
    TextView tv_certificateType;
//    @BindView(R.id.tv_shenfencard_num)
    TextView tvShenfencardNum;

    public ViewHolderHuijiIntentVipType_1(View itemView) {
        super(itemView);
        tvSex = itemView.findViewById(R.id.tv_sex);
        tvPhone = itemView.findViewById(R.id.tv_phone);
        tvBirthday = itemView.findViewById(R.id.tv_birthday);
        tvBirthdayType = itemView.findViewById(R.id.tv_birthday_type);
        tv_certificateType = itemView.findViewById(R.id.tv_certificateType);
        tvShenfencardNum = itemView.findViewById(R.id.tv_shenfencard_num);
    }

    @Override
    public void bindView(VipDetailBean vipDetailBean) {
        tvSex.setText(judgeNull(vipDetailBean.getSex()));
        tvPhone.setText(judgeNull(vipDetailBean.getMobile()));
        tvBirthday.setText(DateUtil.parseLongDateToDateString(vipDetailBean.getBirthday()));
        tvBirthdayType.setText(judgeNull(vipDetailBean.getBirthdayType()));
        tv_certificateType.setText(judgeNull(vipDetailBean.getCertificateType()));
        tvShenfencardNum.setText(judgeNull(vipDetailBean.getCertificateNo()));
    }
}
