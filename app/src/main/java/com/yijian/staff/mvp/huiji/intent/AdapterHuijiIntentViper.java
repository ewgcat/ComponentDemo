package com.yijian.staff.mvp.huiji.intent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yijian.staff.R;
import com.yijian.staff.mvp.huiji.bean.VipDetailBean;
import com.yijian.staff.mvp.huiji.detail.HuijiVipInterface;
import com.yijian.staff.mvp.huiji.detail.ViewHolderHuijiVipType_0;
import com.yijian.staff.mvp.huiji.detail.ViewHolderHuijiVipType_1;
import com.yijian.staff.mvp.huiji.detail.ViewHolderHuijiVipType_2;
import com.yijian.staff.mvp.huiji.detail.ViewHolderHuijiVipType_3;
import com.yijian.staff.mvp.huiji.detail.ViewHolderHuijiVipper;

/**
 * Created by The_P on 2018/5/15.
 */

public class AdapterHuijiIntentViper extends RecyclerView.Adapter<ViewHolderHuijiVipper> implements HuijiVipInterface {

    public static final int TYPE0 = 0;
    public static final int TYPE1 = 1;
    public static final int TYPE2 = 2;

    private Context context;
    private VipDetailBean mVipDetailBean;

    public AdapterHuijiIntentViper(Context context) {
        this.context = context;

    }

    public void setData(VipDetailBean vipDetailBean) {
        mVipDetailBean = vipDetailBean;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderHuijiVipper onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        ViewHolderHuijiVipper holder;
        switch (viewType) {
            default:
            case TYPE0:
                view = LayoutInflater.from(context).inflate(R.layout.item_huijivip_intent_type0, parent, false);
                holder = new ViewHolderHuijiIntentVipType_0(view);
                holder.setHuijiVipInterface(this);
                break;
            case TYPE1:
                view = LayoutInflater.from(context).inflate(R.layout.item_huijivip_intent_type1, parent, false);
                holder = new ViewHolderHuijiIntentVipType_1(view);
                break;
            case TYPE2:
                view = LayoutInflater.from(context).inflate(R.layout.item_huijivip_intent_type2, parent, false);
                holder = new ViewHolderHuijiIntentVipType_2(view);
                holder.setHuijiVipInterface(this);
                break;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderHuijiVipper holder, int position) {
        if (mVipDetailBean != null) holder.bindView(mVipDetailBean);
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
