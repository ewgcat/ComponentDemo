package com.yijian.staff.mvp.resourceallocation.coachleader.selectcoach;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.resourceallocation.coachleader.widget.RatingBar;
import com.yijian.staff.util.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangk on 2018/3/12.
 * 选择会籍适配器
 */

public class SelectCoachAdapter extends RecyclerView.Adapter<SelectCoachAdapter.ViewHolder> {

    private List<CoachInfo> coachInfos = new ArrayList<>();
    private Context context;
    private int selectPosition=-1;


    public SelectCoachAdapter(Context context, List<CoachInfo> coachInfos) {
        this.context = context;
        this.coachInfos = coachInfos;
    }

    @Override
    public SelectCoachAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coach, parent, false);
        SelectCoachAdapter.ViewHolder holder = new SelectCoachAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SelectCoachAdapter.ViewHolder holder, int position) {
        CoachInfo coachInfo = coachInfos.get(position);


        if (position==selectPosition){
            holder.ck_select.setChecked(true);
        }else {
            holder.ck_select.setChecked(false);
        }
        holder.ck_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.ck_select.isChecked()){
                    selectPosition=position;
                    notifyDataSetChanged();
                }
            }
        });


    }

    public CoachInfo getSelectCoachInfo(){
        if (coachInfos==null) return null;
        if (selectPosition==-1)return null;
        if (coachInfos.size()<=selectPosition)return null;
        return coachInfos.get(selectPosition);
    }
    @Override
    public int getItemCount() {
        return coachInfos == null ? 0 : coachInfos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_header;
        ImageView iv_gender;
        TextView tv_name;
        CheckBox ck_select;


        public ViewHolder(View view) {
            super(view);
            iv_header = view.findViewById(R.id.iv_header);
            iv_gender = view.findViewById(R.id.iv_gender);
            tv_name = view.findViewById(R.id.tv_name);
            ck_select = view.findViewById(R.id.ck_select);
        }
    }


}
