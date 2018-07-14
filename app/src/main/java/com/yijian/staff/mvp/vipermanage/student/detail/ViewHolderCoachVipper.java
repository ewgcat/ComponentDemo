package com.yijian.staff.mvp.vipermanage.student.detail;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.yijian.staff.mvp.vipermanage.viper.bean.VipDetailBean;
import com.yijian.staff.mvp.vipermanage.viper.detail.HuijiVipInterface;

/**
 * Created by The_P on 2018/5/15.
 */

public abstract class ViewHolderCoachVipper extends RecyclerView.ViewHolder {
    public ViewHolderCoachVipper(View itemView) {
        super(itemView);
    }

    public abstract void bindView(VipDetailBean bean);

    public String judgeNull(String str) {
        return TextUtils.isEmpty(str) ? "暂未录入" : str;
    }

    public HuijiVipInterface huijiVipInterface;

    public void setHuijiVipInterface(HuijiVipInterface huijiVipInterface) {
        this.huijiVipInterface = huijiVipInterface;
    }
}
