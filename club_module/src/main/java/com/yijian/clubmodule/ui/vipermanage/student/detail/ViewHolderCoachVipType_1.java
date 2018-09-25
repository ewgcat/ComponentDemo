package com.yijian.clubmodule.ui.vipermanage.student.detail;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.yijian.clubmodule.R;
import com.yijian.clubmodule.bean.ViperDetailBean;
import com.yijian.clubmodule.ui.vipermanage.viper.detail.formatoroutdate.StringListAdapter;
import com.yijian.commonlib.util.DateUtil;

import java.util.List;

/**
 * Created by The_P on 2018/5/17.
 */

public class ViewHolderCoachVipType_1 extends ViewHolderCoachVipper {

    TextView tvSex;
    TextView tvPhone;
    TextView tvBirthday;
    TextView tvBirthdayType;
    TextView tvAge;
//    TextView tvXiaoFeiTotal;
    TextView tv_sijiao_class;
    TextView tvFuwuHuiji;
    TextView tvFuwuJiaolian;
    RecyclerView rv_sijiao_class;
    TextView tvRecentFitNessTime;
    TextView deadLine;

    public ViewHolderCoachVipType_1(View itemView) {
        super(itemView);
        tvSex = itemView.findViewById(R.id.tv_sex);
        tvPhone = itemView.findViewById(R.id.tv_phone);
        tvBirthday = itemView.findViewById(R.id.tv_birthday);
        tvBirthdayType = itemView.findViewById(R.id.tv_birthday_type);
        tvAge = itemView.findViewById(R.id.tv_age);

        deadLine = itemView.findViewById(R.id.tv_deadline);
//        tvXiaoFeiTotal = itemView.findViewById(R.id.tv_xiao_fei_total);
        tv_sijiao_class = itemView.findViewById(R.id.tv_sijiao_class);
        tvFuwuHuiji = itemView.findViewById(R.id.tv_fuwu_huiji);
        tvFuwuJiaolian = itemView.findViewById(R.id.tv_fuwu_jiaolian);
        rv_sijiao_class = itemView.findViewById(R.id.rv_sijiao_class);
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


//        tvXiaoFeiTotal.setText(judgeNull(viperDetailBean.getTotalConsumption()) + " 元");
        ViperDetailBean.CustomerServiceInfoBean customerServiceInfoBean = viperDetailBean.getCustomerServiceInfo();
        if (customerServiceInfoBean == null) return;
        tvFuwuHuiji.setText(judgeNull(customerServiceInfoBean.getServiceSale()));
        tvFuwuJiaolian.setText(judgeNull(customerServiceInfoBean.getServiceCoach()));
        List<String> privateCourses = customerServiceInfoBean.getPrivateCourses();
        if (privateCourses != null && privateCourses.size() > 0) {
            rv_sijiao_class.setVisibility(View.VISIBLE);
            tv_sijiao_class.setVisibility(View.GONE);
            StringListAdapter stringListAdapter = new StringListAdapter(privateCourses);

            final LinearLayoutManager layoutManager = new LinearLayoutManager(rv_sijiao_class.getContext());
            rv_sijiao_class.setLayoutManager(layoutManager);
            rv_sijiao_class.setAdapter(stringListAdapter);
        } else {
            rv_sijiao_class.setVisibility(View.GONE);
            tv_sijiao_class.setVisibility(View.VISIBLE);
        }


    }
}
