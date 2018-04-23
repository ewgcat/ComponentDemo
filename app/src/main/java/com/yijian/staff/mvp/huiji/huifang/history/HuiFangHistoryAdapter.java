package com.yijian.staff.mvp.huiji.huifang.history;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yijian.staff.R;
import com.yijian.staff.mvp.huiji.huifang.bean.HuiFangInfo;
import com.yijian.staff.util.DateUtil;
import com.yijian.staff.util.Logger;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/2/28 17:08:17
 */
public class HuiFangHistoryAdapter extends RecyclerView.Adapter<HuiFangHistoryAdapter.ViewHolder> {
    private List<HuiFangInfo> mHuiFangInfoList;
    private Context context;

    public HuiFangHistoryAdapter(Context context, List<HuiFangInfo> mHuiFangInfoList) {
        this.mHuiFangInfoList = mHuiFangInfoList;
        this.context = context;
    }

    @Override
    public HuiFangHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hui_fang_history, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public void update(List<HuiFangInfo> mHuiFangInfoList){
        this.mHuiFangInfoList = mHuiFangInfoList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(HuiFangHistoryAdapter.ViewHolder holder, int position) {
        Logger.i("CoachHuiFangTaskAdapter", "position: " + position);
        HuiFangInfo huiFangInfo = mHuiFangInfoList.get(position);
        Glide.with(context).load(R.mipmap.wt_boysmall).into(holder.ivHead);
        holder.viperName.setText(huiFangInfo.getName());
        Glide.with(context).load(R.mipmap.lg_man).into(holder.viperSex);
//        holder.tvQuanyi.setText(huiFangInfo.getQuanyi());
        Long deadline = huiFangInfo.getDeadline();
        if (deadline!=null&&deadline!=-1){
            String s = DateUtil.parseLongDateToDateString(deadline);
            holder.tvOutdateTime.setText(s);
        }
        holder.tvHuifangType.setText(huiFangInfo.getInterviewType());
        holder.tvHuifangResult.setText(huiFangInfo.getInterviewResult());
    }

    @Override
    public int getItemCount() {
        return mHuiFangInfoList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivHead;
        TextView viperName;
        ImageView viperSex;
        TextView tvQuanyi;
        TextView tvOutdateTime;
        TextView tvOutdateReason;
        TextView tvHuifangType;
        TextView tvHuifangResult;

        public ViewHolder(View view) {
            super(view);
            ivHead = view.findViewById(R.id.iv_head);
            viperName = view.findViewById(R.id.tv_viper_name);
            viperSex = view.findViewById(R.id.iv_sex);
            tvQuanyi = view.findViewById(R.id.tv_quanyi);
            tvOutdateTime = view.findViewById(R.id.tv_outdate_time);
            tvOutdateReason = view.findViewById(R.id.tv_outdate_reason);
            tvHuifangType = view.findViewById(R.id.tv_huifang_type);
            tvHuifangResult = view.findViewById(R.id.tv_huifang_result);
        }
    }
}
