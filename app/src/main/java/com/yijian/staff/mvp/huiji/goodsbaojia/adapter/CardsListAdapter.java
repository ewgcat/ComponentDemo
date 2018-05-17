package com.yijian.staff.mvp.huiji.goodsbaojia.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.huiji.goodsbaojia.bean.CardInfo;
import com.yijian.staff.widget.AlwaysMarqueeTextView;

import java.util.ArrayList;
import java.util.List;


public class CardsListAdapter extends RecyclerView.Adapter<CardsListAdapter.ViewHolder> {
    private List<CardInfo> mGoodsInfoList = new ArrayList<>();
    private Context context;
    private int clickIndex = -1;

    public CardsListAdapter(Context context) {
        this.context = context;
    }

    public void resetData(List<CardInfo> datas) {
        mGoodsInfoList.clear();
        mGoodsInfoList.addAll(datas);
        notifyDataSetChanged();
    }

    public void addDatas(List<CardInfo> datas) {
        mGoodsInfoList.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public CardsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CardsListAdapter.ViewHolder holder, int position) {
        holder.bindView(position);

    }

    @Override
    public int getItemCount() {
        return mGoodsInfoList.size();
    }

    public void update(List<CardInfo> mGoodsInfoList) {
        this.mGoodsInfoList = mGoodsInfoList;
        notifyDataSetChanged();
    }


     class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout itemView;
         AlwaysMarqueeTextView tvGoodsName;
//        TextView tvJianshenplace;
        TextView tvYuEr;
        TextView tvChuzhiyouhui;
        TextView tvPrice;
        TextView tv_danwei;


        public ViewHolder(View view) {
            super(view);
            itemView = view.findViewById(R.id.item_view);
            tvGoodsName = view.findViewById(R.id.tv_goods_name);
//            tvJianshenplace = view.findViewById(R.id.tv_jianshenplace);
            tvYuEr = view.findViewById(R.id.tv_yu_er);
            tvChuzhiyouhui = view.findViewById(R.id.tv_chuzhiyouhui);
            tvPrice = view.findViewById(R.id.tv_price);
            tv_danwei = view.findViewById(R.id.tv_danwei);

        }

        public void bindView(int position) {
//            //        Logger.i("ClassListAdapter", "position: " + position);
//            if (clickIndex == position) {
//                itemView.setBackgroundResource(R.drawable.goods_blue_stroke_bg);
//
//            } else {
                itemView.setBackgroundResource(R.drawable.white_bg);
//            }

            CardInfo goodsInfo = mGoodsInfoList.get(position);
            tvGoodsName.setText(goodsInfo.getCardName());
//            tvJianshenplace.setText(goodsInfo.getVenusNames()!=null?""+goodsInfo.getVenusNames():"");
            if (goodsInfo.getCardType()!=null&&goodsInfo.getCardType() == 1) {
                tvYuEr.setText(goodsInfo.getValidDay()!=null?""+goodsInfo.getValidDay():"");
               tv_danwei.setText("天");
            } else if (goodsInfo.getCardType()!=null&&goodsInfo.getCardType() == 2) {
               tvYuEr.setText(goodsInfo.getValidTime()!=null?""+goodsInfo.getValidTime():"");
                tv_danwei.setText("次");
            } else if (goodsInfo.getCardType()!=null&&goodsInfo.getCardType() == 3) {
               tvYuEr.setText(goodsInfo.getAmount()!=null?""+goodsInfo.getAmount():"");
               tv_danwei.setText("元");
            } else if (goodsInfo.getCardType()!=null&&goodsInfo.getCardType() == 4) {
               tvYuEr.setText(goodsInfo.getAmount()!=null?""+goodsInfo.getAmount():"");
               tv_danwei.setText("元");
            }

           tvChuzhiyouhui.setText(goodsInfo.getRechargeGivePercent()!=null?""+goodsInfo.getRechargeGivePercent():"");
           tvPrice.setText(goodsInfo.getSalePrice()!=null?""+goodsInfo.getSalePrice():"");
           itemView.setOnClickListener(new View.OnClickListener() {
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
    }

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {

        void onItemClick(View v, CardInfo goodsInfo);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public List<CardInfo> getmGoodsInfoList() {
        return mGoodsInfoList;
    }
}
