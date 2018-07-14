package com.yijian.staff.mvp.vipermanage.student.detail;

import android.support.v7.widget.RecyclerView;

import com.yijian.staff.mvp.vipermanage.viper.bean.VipDetailBean;
import com.yijian.staff.mvp.vipermanage.viper.detail.HuijiVipInterface;

/**
 * Created by The_P on 2018/5/17.
 */

public abstract class AdapterAbsCoachViper extends RecyclerView.Adapter<ViewHolderCoachVipper> implements HuijiVipInterface {
    public AdapterInterface adapterInterface;
    public VipDetailBean mVipDetailBean;

    public void setAdapterInterface(AdapterInterface adapterInterface) {
        this.adapterInterface = adapterInterface;
    }

    public interface AdapterInterface {
        void clickVisit();

        void clickEdit();
    }

    public void setData(VipDetailBean vipDetailBean) {
        mVipDetailBean = vipDetailBean;
        notifyDataSetChanged();
    }
}
