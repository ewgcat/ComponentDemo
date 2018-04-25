package com.yijian.staff.mvp.coach.experienceclass.index;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.bean.CoachViperBean;
import com.yijian.staff.mvp.coach.card.CoachVipCardListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/13 12:17:55
 */
public class ExperienceClassVipCardListAdapter extends RecyclerView.Adapter {
    List<CardprodVOsBean> cardprodsBeans = new ArrayList<>();

    public ExperienceClassVipCardListAdapter(List<CardprodVOsBean> cardprodsBeans) {
        this.cardprodsBeans = cardprodsBeans;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_huiji_vip_card,null);
        ExperienceClassVipCardListAdapter.ViewHolder viewHolder = new ExperienceClassVipCardListAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CardprodVOsBean cardprodsBean = cardprodsBeans.get(position);
        ((ExperienceClassVipCardListAdapter.ViewHolder)holder).bind(cardprodsBean);
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

        public void bind(CardprodVOsBean cardprodsBean){
            tv_cardName.setText(cardprodsBean.getCardName());
            tv_cardType.setText(cardprodsBean.getCardType());

        }

    }
}
