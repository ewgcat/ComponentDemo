package com.yijian.staff.mvp.resourceallocation.huijileader.selecthuiji;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.staff.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangk on 2018/3/12.
 * 选择会籍适配器
 */

public class SelectHuiJiAdapter extends RecyclerView.Adapter<SelectHuiJiAdapter.ViewHolder> {

    private List<HuiJiInfo> coachInfos = new ArrayList<>();
    private Context context;
    private int selectPosition = -1;


    public SelectHuiJiAdapter(Context context, List<HuiJiInfo> coachInfos) {
        this.context = context;
        this.coachInfos = coachInfos;
    }

    @Override
    public SelectHuiJiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coach, parent, false);
        SelectHuiJiAdapter.ViewHolder holder = new SelectHuiJiAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SelectHuiJiAdapter.ViewHolder holder, int position) {
        HuiJiInfo coachInfo = coachInfos.get(position);
        holder.tv_name.setText(coachInfo.getUserName());

        if (position == selectPosition) {
            holder.ck_select.setChecked(true);
        } else {
            holder.ck_select.setChecked(false);
        }
        holder.ck_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.ck_select.isChecked()) {
                    selectPosition = position;
                    notifyDataSetChanged();
                }
            }
        });


    }

    public HuiJiInfo getSelectCoachInfo() {
        if (coachInfos == null) return null;
        if (selectPosition == -1) return null;
        if (coachInfos.size() <= selectPosition) return null;
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
