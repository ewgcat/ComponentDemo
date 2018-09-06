package com.yijian.staff.mvp.vipermanage.viper.detail.formatoroutdate;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.bean.ViperDetailBean;
import com.yijian.staff.mvp.vipermanage.viper.detail.BaseVipperViewHolder;
import com.yijian.staff.util.DateUtil;

import java.util.List;

/**
 * Created by The_P on 2018/5/15.
 */

public class VipTypeTwoViewHolder extends BaseVipperViewHolder {

    TextView tvSex;
    TextView tvPhone;
    TextView tvBirthday;
    TextView tvBirthdayType;
    TextView tvAge;
    TextView tvXiaoFeiTotal;
    TextView tvFuwuHuiji;
    TextView tvFuwuJiaolian;
    RecyclerView rv_sijiao_class;
    LinearLayout rlSijiaoClass;
    TextView tvRecentFitNessTime;
    TextView deadLine;

    public VipTypeTwoViewHolder(View itemView) {
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
        rv_sijiao_class = itemView.findViewById(R.id.rv_sijiao_class);
        rlSijiaoClass = itemView.findViewById(R.id.rl_sijiao_class);
        tvRecentFitNessTime = itemView.findViewById(R.id.tv_recent_fitness_time);

    }

    @Override
    public void bindView(ViperDetailBean viperDetailBean) {
        tvSex.setText(judgeNull(viperDetailBean.getSex()));
        tvPhone.setText(judgeNull(viperDetailBean.getMobile()));


        long birthday = viperDetailBean.getBirthday();
        if (birthday != 0) {
            tvBirthday.setText(DateUtil.parseLongDateToDateString(birthday));
        } else {
            tvBirthday.setText("暂未录入");
        }


        tvBirthdayType.setText(judgeNull(viperDetailBean.getBirthdayType()));
        tvAge.setText(judgeNull(viperDetailBean.getAge() + ""));

        long deadline = viperDetailBean.getDeadline();
        if (deadline != 0) {
            deadLine.setText(DateUtil.parseLongDateToDateString(deadline));
        } else {
            deadLine.setText("暂未录入");
        }

        long recentlyFitTime = viperDetailBean.getRecentlyFitTime();
        if (recentlyFitTime != 0) {
            tvRecentFitNessTime.setText(DateUtil.parseLongDateToDateString(recentlyFitTime));
        } else {
            tvRecentFitNessTime.setText("暂未录入");
        }


        tvXiaoFeiTotal.setText(judgeNull(viperDetailBean.getTotalConsumption()) + " 元");
        ViperDetailBean.CustomerServiceInfoBean customerServiceInfoBean = viperDetailBean.getCustomerServiceInfo();
        if (customerServiceInfoBean == null) return;
        tvFuwuHuiji.setText(judgeNull(customerServiceInfoBean.getServiceSale()));
        tvFuwuJiaolian.setText(judgeNull(customerServiceInfoBean.getServiceCoach()));
        List<String> privateCourses = customerServiceInfoBean.getPrivateCourses();

        if (privateCourses != null && privateCourses.size() > 0) {
            rlSijiaoClass.setVisibility(View.VISIBLE);
            StringListAdapter stringListAdapter = new StringListAdapter(privateCourses);

            final LinearLayoutManager layoutManager = new LinearLayoutManager(rv_sijiao_class.getContext());
            rv_sijiao_class.setLayoutManager(layoutManager);
            rv_sijiao_class.setAdapter(stringListAdapter);
//            StringBuffer sb = new StringBuffer();
//            for (int i = 0; i < privateCourses.size(); i++) {
//                if (i< privateCourses.size()-1){
//                    String s = privateCourses.get(i) + "\n";
//                    sb.append(s);
//                }else {
//                    String s = privateCourses.get(i) ;
//                    sb.append(s);
//                }
//            }
        } else {
            rlSijiaoClass.setVisibility(View.GONE);
        }


    }
}
