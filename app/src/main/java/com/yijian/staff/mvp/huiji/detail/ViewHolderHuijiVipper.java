package com.yijian.staff.mvp.huiji.detail;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.yijian.staff.mvp.huiji.bean.VipDetailBean;

/**
 * Created by The_P on 2018/5/15.
 */

public abstract class ViewHolderHuijiVipper extends RecyclerView.ViewHolder {
    public ViewHolderHuijiVipper(View itemView) {
        super(itemView);
    }

    public  abstract void bindView(VipDetailBean bean);
    public String judgeNull(String str) {
        return TextUtils.isEmpty(str) ? "未录入" : str;
    }

    public HuijiVipInterface huijiVipInterface;

    public void setHuijiVipInterface(HuijiVipInterface huijiVipInterface) {
        this.huijiVipInterface = huijiVipInterface;
    }
}
