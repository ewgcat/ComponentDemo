package com.yijian.clubmodule.ui.vipermanage.viper.detail.protentialorintent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yijian.clubmodule.R;
import com.yijian.clubmodule.bean.ViperDetailBean;
import com.yijian.clubmodule.ui.vipermanage.viper.detail.HuijiVipInterface;
import com.yijian.clubmodule.ui.vipermanage.viper.detail.BaseVipperViewHolder;

/**
 * Created by The_P on 2018/5/15.
 */

public class ProtentialOrIntentViperDetailAdapter extends RecyclerView.Adapter<BaseVipperViewHolder> implements HuijiVipInterface {

    public static final int TYPE0 = 0;
    public static final int TYPE1 = 1;
    public static final int TYPE2 = 2;

    private Context context;
    private ViperDetailBean mViperDetailBean;

    public ProtentialOrIntentViperDetailAdapter(Context context) {
        this.context = context;

    }

    public void setData(ViperDetailBean viperDetailBean) {
        mViperDetailBean = viperDetailBean;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseVipperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        BaseVipperViewHolder holder;
        switch (viewType) {
            default:
            case TYPE0:
                view = LayoutInflater.from(context).inflate(R.layout.item_huijivip_intent_type0, parent, false);
                holder = new ProtentialOrIntentViperTypeOneViewHolder(view);
                holder.setHuijiVipInterface(this);
                break;
            case TYPE1:
                view = LayoutInflater.from(context).inflate(R.layout.item_huijivip_intent_type1, parent, false);
                holder = new ProtentialOrIntentViperTypeTwoViewHolder(view);
                break;
            case TYPE2:
                view = LayoutInflater.from(context).inflate(R.layout.item_huijivip_intent_type2, parent, false);
                holder = new ProtentialOrIntentViperTypeThreeViewHolder(view);
                holder.setHuijiVipInterface(this);
                break;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseVipperViewHolder holder, int position) {
        if (mViperDetailBean != null) holder.bindView(mViperDetailBean);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            default:
            case 0:
                return TYPE0;
            case 1:
                return TYPE1;
            case 2:
                return TYPE2;

        }

    }

    @Override
    public void visit() {
        if (adapterInterface != null) adapterInterface.clickVisit();
    }

    @Override
    public void edit() {
        if (adapterInterface != null) adapterInterface.clickEdit();
    }

    public interface AdapterInterface {
        void clickVisit();

        void clickEdit();
    }

    public AdapterInterface adapterInterface;

    public void setAdapterInterface(AdapterInterface adapterInterface) {
        this.adapterInterface = adapterInterface;
    }
}
