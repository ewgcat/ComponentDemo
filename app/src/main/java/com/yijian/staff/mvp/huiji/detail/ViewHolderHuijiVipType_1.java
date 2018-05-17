package com.yijian.staff.mvp.huiji.detail;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.huiji.bean.HuiJiVipeCardAdapter;
import com.yijian.staff.mvp.huiji.bean.VipDetailBean;
import com.yijian.staff.util.DateUtil;
import com.yijian.staff.util.ImageLoader;

import java.util.List;

import butterknife.BindView;

/**
 * Created by The_P on 2018/5/15.
 */

public class ViewHolderHuijiVipType_1 extends  ViewHolderHuijiVipper{

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

    public ViewHolderHuijiVipType_1(View itemView) {
        super(itemView);
        tvSex= itemView.findViewById(R.id.tv_sex);
        tvPhone= itemView.findViewById(R.id.tv_phone);
        tvBirthday= itemView.findViewById(R.id.tv_birthday);
        tvBirthdayType= itemView.findViewById(R.id.tv_birthday_type);
        tvAge= itemView.findViewById(R.id.tv_age);

        tvXiaoFeiTotal= itemView.findViewById(R.id.tv_xiao_fei_total);
        tvFuwuHuiji= itemView.findViewById(R.id.tv_fuwu_huiji);
        tvFuwuJiaolian= itemView.findViewById(R.id.tv_fuwu_jiaolian);
        tvSijiaoClass= itemView.findViewById(R.id.tv_sijiao_class);
        rlSijiaoClass= itemView.findViewById(R.id.rl_sijiao_class);
    }

    @Override
   public void bindView(VipDetailBean vipDetailBean) {
        tvSex.setText(judgeNull(vipDetailBean.getSex()));
        tvPhone.setText(judgeNull(vipDetailBean.getMobile()));
        tvBirthday.setText(DateUtil.parseLongDateToDateString(vipDetailBean.getBirthday()));
        tvBirthdayType.setText(judgeNull(vipDetailBean.getBirthdayType()));

        tvAge.setText(judgeNull(vipDetailBean.getAge()+""));
        tvXiaoFeiTotal.setText(judgeNull(vipDetailBean.getTotalConsumption())+" 元");
        VipDetailBean.CustomerServiceInfoBean customerServiceInfoBean = vipDetailBean.getCustomerServiceInfo();
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
