package com.yijian.staff.mvp.huiji.intent;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.huiji.bean.VipDetailBean;
import com.yijian.staff.mvp.huiji.detail.ViewHolderHuijiVipper;
import com.yijian.staff.util.DateUtil;

import java.util.List;

import butterknife.BindView;

/**
 * Created by The_P on 2018/5/16.
 */

class ViewHolderHuijiIntentVipType_1 extends ViewHolderHuijiVipper {
    TextView tvSex;
    TextView tvPhone;
    TextView tvBirthday;
    TextView tvBirthdayType;
    TextView tvAge;
    TextView tvXiaoFeiTotal;
    TextView tvFuwuHuiji;
    TextView tvFuwuJiaolian;
    TextView tvSijiaoClass;
    RelativeLayout rlSijiaoClass;
    TextView tvRecentFitNessTime;
    TextView deadLine;

    public ViewHolderHuijiIntentVipType_1(View itemView) {
        super(itemView);
        tvSex = itemView.findViewById(R.id.tv_sex);
        tvPhone = itemView.findViewById(R.id.tv_phone);
        tvBirthday = itemView.findViewById(R.id.tv_birthday);
        tvBirthdayType = itemView.findViewById(R.id.tv_birthday_type);
        tvAge = itemView.findViewById(R.id.tv_age);

        deadLine = itemView.findViewById(R.id.tv_deadline);
        tvXiaoFeiTotal = itemView.findViewById(R.id.tv_xiao_fei_total);
        tvFuwuHuiji = itemView.findViewById(R.id.tv_fuwu_huiji);
        tvFuwuJiaolian = itemView.findViewById(R.id.tv_fuwu_jiaolian);
        tvSijiaoClass = itemView.findViewById(R.id.tv_sijiao_class);
        rlSijiaoClass = itemView.findViewById(R.id.rl_sijiao_class);
        tvRecentFitNessTime = itemView.findViewById(R.id.tv_recent_fitness_time);

    }

    @Override
    public void bindView(VipDetailBean vipDetailBean) {
        tvSex.setText(judgeNull(vipDetailBean.getSex()));
        tvPhone.setText(judgeNull(vipDetailBean.getMobile()));
        long birthday = vipDetailBean.getBirthday();
        if (birthday != 0) {
            tvBirthday.setText(DateUtil.parseLongDateToDateString(birthday));
        } else {
            tvBirthday.setText("未录入");
        }


        tvBirthdayType.setText(judgeNull(vipDetailBean.getBirthdayType()));
        tvAge.setText(judgeNull(vipDetailBean.getAge() + ""));

        long deadline = vipDetailBean.getDeadline();
        if (deadline != 0) {
            deadLine.setText(DateUtil.parseLongDateToDateString(deadline));
        } else {
            deadLine.setText("未录入");
        }

        long recentlyFitTime = vipDetailBean.getRecentlyFitTime();
        if (recentlyFitTime != 0) {
            tvRecentFitNessTime.setText(DateUtil.parseLongDateToDateString(recentlyFitTime));
        } else {
            tvRecentFitNessTime.setText("未录入");
        }


        tvXiaoFeiTotal.setText(judgeNull(vipDetailBean.getTotalConsumption()) + " 元");
        VipDetailBean.CustomerServiceInfoBean customerServiceInfoBean = vipDetailBean.getCustomerServiceInfo();
        if (customerServiceInfoBean == null) return;
        tvFuwuHuiji.setText(judgeNull(customerServiceInfoBean.getServiceSale()));
        tvFuwuJiaolian.setText(judgeNull(customerServiceInfoBean.getServiceCoach()));
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


    }
}
