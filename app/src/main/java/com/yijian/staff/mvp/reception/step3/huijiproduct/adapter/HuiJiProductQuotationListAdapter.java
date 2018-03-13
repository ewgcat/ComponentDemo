package com.yijian.staff.mvp.reception.step3.huijiproduct.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step3.bean.GoodsInfo;
import com.yijian.staff.util.Logger;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/3 17:08:17
 */
public class HuiJiProductQuotationListAdapter extends RecyclerView.Adapter<HuiJiProductQuotationListAdapter.ViewHolder> {
    private List<GoodsInfo> mGoodsInfoList;
    private Context context;

    public HuiJiProductQuotationListAdapter(Context context, List<GoodsInfo> mGoodsInfoList) {
        this.mGoodsInfoList = mGoodsInfoList;
        this.context = context;
    }

    @Override
    public HuiJiProductQuotationListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HuiJiProductQuotationListAdapter.ViewHolder holder, int position) {
        Logger.i("HuiJiProductQuotationListAdapter","position: "+position);

        GoodsInfo goodsInfo = mGoodsInfoList.get(position);
        holder.tvGoodsName.setText(goodsInfo.getGoodsName());
        holder.tvJianshenplace.setText(goodsInfo.getJianshenplace());
        holder.tvYuEr.setText(goodsInfo.getYuer());
        holder.tvChuzhiyouhui.setText(goodsInfo.getChuzhiyouhui());
        holder.tvPrice.setText(goodsInfo.getPrice());
    }

    @Override
    public int getItemCount() {
        return mGoodsInfoList.size();
    }

    public void update(List<GoodsInfo> mGoodsInfoList) {
        this.mGoodsInfoList=mGoodsInfoList;
        notifyDataSetChanged();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvGoodsName;
        TextView tvJianshenplace;
        TextView tvYuEr;
        TextView tvChuzhiyouhui;
        TextView tvPrice;


        public ViewHolder(View view) {
            super(view);
            tvGoodsName=     view.findViewById(R.id.tv_goods_name);
            tvJianshenplace=     view.findViewById(R.id.tv_jianshenplace);
            tvYuEr=     view.findViewById(R.id.tv_yu_er);
            tvChuzhiyouhui=     view.findViewById(R.id.tv_chuzhiyouhui);
            tvPrice=     view.findViewById(R.id.tv_price);

        }
    }
}
