package com.yijian.staff.mvp.huiji.goodsbaojia.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.huiji.goodsbaojia.bean.GoodsInfo;
import com.yijian.staff.util.Logger;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/3 17:08:17
 */
public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.ViewHolder> {
    private List<GoodsInfo> mGoodsInfoList;
    private Context context;
    private int clickIndex = -1;

    public GoodsListAdapter(Context context, List<GoodsInfo> mGoodsInfoList) {
        this.mGoodsInfoList = mGoodsInfoList;
        this.context = context;
    }

    @Override
    public GoodsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(GoodsListAdapter.ViewHolder holder, int position) {
        Logger.i("ClassListAdapter", "position: " + position);
        if (clickIndex==position){
            holder.itemView.setBackgroundResource(R.drawable.goods_blue_stroke_bg);

        }else {
            holder.itemView.setBackgroundResource(R.drawable.white_bg);
        }

        GoodsInfo goodsInfo = mGoodsInfoList.get(position);
        holder.tvGoodsName.setText(goodsInfo.getCardName());
        holder.tvJianshenplace.setText(goodsInfo.getVenusNames());
        if (goodsInfo.getCardType() == 1) {
            holder.tvYuEr.setText(goodsInfo.getValidDay());
            holder.tv_danwei.setText("天");
        } else if (goodsInfo.getCardType() == 2) {
            holder.tvYuEr.setText(goodsInfo.getValidTime());
            holder.tv_danwei.setText("次");
        } else if (goodsInfo.getCardType() == 3) {
            holder.tvYuEr.setText(goodsInfo.getAmount());
            holder.tv_danwei.setText("元");
        } else if (goodsInfo.getCardType() == 4) {
            holder.tvYuEr.setText(goodsInfo.getAmount());
            holder.tv_danwei.setText("元");
        }

        holder.tvChuzhiyouhui.setText(goodsInfo.getRechargeGivePercent());
        holder.tvPrice.setText(goodsInfo.getSalePrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, goodsInfo);
                }
                clickIndex = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGoodsInfoList.size();
    }

    public void update(List<GoodsInfo> mGoodsInfoList) {
        this.mGoodsInfoList = mGoodsInfoList;
        notifyDataSetChanged();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout itemView;
        TextView tvGoodsName;
        TextView tvJianshenplace;
        TextView tvYuEr;
        TextView tvChuzhiyouhui;
        TextView tvPrice;
        TextView tv_danwei;


        public ViewHolder(View view) {
            super(view);
            itemView = view.findViewById(R.id.item_view);
            tvGoodsName = view.findViewById(R.id.tv_goods_name);
            tvJianshenplace = view.findViewById(R.id.tv_jianshenplace);
            tvYuEr = view.findViewById(R.id.tv_yu_er);
            tvChuzhiyouhui = view.findViewById(R.id.tv_chuzhiyouhui);
            tvPrice = view.findViewById(R.id.tv_price);
            tv_danwei = view.findViewById(R.id.tv_danwei);

        }
    }

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {

        void onItemClick(View v, GoodsInfo goodsInfo);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
