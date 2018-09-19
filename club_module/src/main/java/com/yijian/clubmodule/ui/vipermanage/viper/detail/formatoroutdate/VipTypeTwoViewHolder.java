package com.yijian.clubmodule.ui.vipermanage.viper.detail.formatoroutdate;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.clubmodule.R;
import com.yijian.clubmodule.bean.ViperDetailBean;
import com.yijian.clubmodule.ui.vipermanage.viper.detail.BaseVipperViewHolder;
import com.yijian.commonlib.util.DateUtil;

import java.util.List;



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


        String birthday = viperDetailBean.getBirthday();
        if (!TextUtils.isEmpty(birthday)) {
            tvBirthday.setText(birthday);
        } else {
            tvBirthday.setText("暂未录入");
        }


        tvBirthdayType.setText(judgeNull(viperDetailBean.getBirthdayType()));
        tvAge.setText(judgeNull(viperDetailBean.getAge() + ""));

        String deadline = viperDetailBean.getDeadline();
        if (!TextUtils.isEmpty(deadline)) {
            String s = DateUtil.parseLongDateToDateString(Long.parseLong(deadline));
            deadLine.setText(s);
        } else {
            deadLine.setText("暂未录入");
        }

        String recentlyFitTime = viperDetailBean.getRecentlyFitTime();
        if (!TextUtils.isEmpty(recentlyFitTime)) {
            String s = DateUtil.parseLongDateToDateString(Long.parseLong(recentlyFitTime));
            tvRecentFitNessTime.setText(s);
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
