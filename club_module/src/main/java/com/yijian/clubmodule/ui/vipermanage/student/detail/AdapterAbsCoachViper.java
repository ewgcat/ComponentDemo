package com.yijian.clubmodule.ui.vipermanage.student.detail;

import androidx.recyclerview.widget.RecyclerView;

import com.yijian.clubmodule.bean.ViperDetailBean;
import com.yijian.clubmodule.ui.vipermanage.viper.detail.HuijiVipInterface;

/**
 * Created by The_P on 2018/5/17.
 */

public abstract class AdapterAbsCoachViper extends RecyclerView.Adapter<ViewHolderCoachVipper> implements HuijiVipInterface {
    public AdapterInterface adapterInterface;
    public ViperDetailBean mViperDetailBean;

    public void setAdapterInterface(AdapterInterface adapterInterface) {
        this.adapterInterface = adapterInterface;
    }

    public interface AdapterInterface {
        void clickVisit();

        void clickEdit();
    }

    public void setData(ViperDetailBean viperDetailBean) {
        mViperDetailBean = viperDetailBean;
        notifyDataSetChanged();
    }
}
