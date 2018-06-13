package com.yijian.staff.mvp.coach.detail;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.yijian.staff.bean.CoachVipDetailBean;
import com.yijian.staff.mvp.huiji.bean.VipDetailBean;
import com.yijian.staff.mvp.huiji.detail.HuijiVipInterface;

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
