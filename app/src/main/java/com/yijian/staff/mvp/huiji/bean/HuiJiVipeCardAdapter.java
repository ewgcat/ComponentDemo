package com.yijian.staff.mvp.huiji.bean;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yijian.staff.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangk on 2018/3/31.
 */

public class HuiJiVipeCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<HuiJiViperBean.CardprodsBean> cardprodsBeans = new ArrayList<>();

    public HuiJiVipeCardAdapter(List<HuiJiViperBean.CardprodsBean> cardprodsBeans) {
        this.cardprodsBeans = cardprodsBeans;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_huiji_vip_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
         HuiJiViperBean.CardprodsBean cardprodsBean = cardprodsBeans.get(position);
        ((ViewHolder)holder).bind(cardprodsBean);
    }

    @Override
    public int getItemCount() {
        return cardprodsBeans==null?0:cardprodsBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_cardName;
        private TextView tv_cardType;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_cardName  = itemView.findViewById(R.id.tv_cardName);
            tv_cardType  = itemView.findViewById(R.id.tv_cardType);
        }

        public void bind(HuiJiViperBean.CardprodsBean cardprodsBean){
            tv_cardName.setText(cardprodsBean.getCardName());
            tv_cardType.setText(cardprodsBean.getCardType());

        }

    }

}
