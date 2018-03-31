package com.yijian.staff.mvp.huiji.bean;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yijian.staff.R;

/**
 * Created by yangk on 2018/3/31.
 */

public class HuiJiVipeCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView cardName;
        private TextView cardType;

        public ViewHolder(View itemView) {
            super(itemView);
            /*cardName  = itemView.findViewById(R.id.cardName);
            cardType  = itemView.findViewById(R.id.cardType);*/
        }
    }

}
