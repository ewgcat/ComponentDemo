package com.yijian.staff.mvp.advice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.bean.AdviceBean;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/17 15:08:17
 */
public class AdviceListAdapter extends RecyclerView.Adapter<AdviceListAdapter.ViewHolder> {
    private List<AdviceBean> adviceBeanList;
    private Context context;

    public AdviceListAdapter(Context context, List<AdviceBean> adviceBeanList) {
        this.adviceBeanList = adviceBeanList;
        this.context = context;
    }

    @Override
    public AdviceListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_advice, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AdviceListAdapter.ViewHolder holder, int position) {
        AdviceBean adviceBean = adviceBeanList.get(position);
        holder.tv_content.setText(adviceBean.getContent());
        holder.tv_name.setText(adviceBean.getName());
        holder.tv_time.setText(adviceBean.getTime());
    }

    @Override
    public int getItemCount() {
        return adviceBeanList.size();
    }

 

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_content;
        TextView tv_name;
        TextView tv_time;


        public ViewHolder(View view) {
            super(view);
            tv_content = view.findViewById(R.id.tv_content);
            tv_name = view.findViewById(R.id.tv_name);
            tv_time = view.findViewById(R.id.tv_time);

        }
    }
 
}
