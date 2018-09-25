package com.yijian.clubmodule.ui.vipermanage.viper.detail;

import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.yijian.clubmodule.bean.ViperDetailBean;

/**
 * Created by The_P on 2018/5/15.
 */

public abstract class BaseVipperViewHolder extends RecyclerView.ViewHolder {
    public BaseVipperViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindView(ViperDetailBean bean);

    public String judgeNull(String str) {
        return TextUtils.isEmpty(str) ? "暂未录入" : str;
    }

    public HuijiVipInterface huijiVipInterface;

    public void setHuijiVipInterface(HuijiVipInterface huijiVipInterface) {
        this.huijiVipInterface = huijiVipInterface;
    }
}
