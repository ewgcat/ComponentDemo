package com.yijian.staff.mvp.huifang.task.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yijian.staff.R;
import com.yijian.staff.mvp.huifang.bean.HuiFangInfo;
import com.yijian.staff.mvp.huifang.tianxieresult.TianXieHuiFangResultActivity;
import com.yijian.staff.mvp.login.LoginActivity;
import com.yijian.staff.mvp.main.MainActivity;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.Logger;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/2/28 17:08:17
 */
public class HuiFangTaskAdapter extends RecyclerView.Adapter<HuiFangTaskAdapter.ViewHolder> {
    private List<HuiFangInfo> mHuiFangInfoList;
    private Context context;

    public HuiFangTaskAdapter(Context context, List<HuiFangInfo> mHuiFangInfoList) {
        this.mHuiFangInfoList = mHuiFangInfoList;
        this.context = context;
    }
    public void update(List<HuiFangInfo> mHuiFangInfoList){
        this.mHuiFangInfoList = mHuiFangInfoList;
        notifyDataSetChanged();
    }

    @Override
    public HuiFangTaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hui_fang_task, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HuiFangTaskAdapter.ViewHolder holder, int position) {
        Logger.i("HuiFangTaskAdapter", "position: " + position);
        HuiFangInfo huiFangInfo = mHuiFangInfoList.get(position);
        Glide.with(context).load(R.mipmap.wt_boysmall).into(holder.ivHead);
        holder.viperName.setText(huiFangInfo.getName());
        Glide.with(context).load(R.mipmap.lg_man).into(holder.viperSex);
        holder.tvQuanyi.setText(huiFangInfo.getQuanyi());
        holder.tvOutdateTime.setText(huiFangInfo.getOutdateTime());
        holder.tvHuifangType.setText(huiFangInfo.getHuifangType());

        if (position==0){
            holder.tv.setText("填写回访结果");
            Glide.with(context).load(R.mipmap.wt_huifangjieguo).into(holder.iv);

        }
        holder.llBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = holder.tv.getText().toString();
                if (text.equals("回访")){
                    CommonUtil.callPhone(context,"13386170640");
                }else {
                    Intent i = new Intent(context,TianXieHuiFangResultActivity.class);
                    context.startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mHuiFangInfoList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivHead;
        TextView viperName;
        ImageView viperSex;
        TextView tvShentiZhuangtai;
        TextView tvXingquAihao;
        TextView tvJianshenAihao;
        TextView tvCarName;
        TextView tvQuanyi;
        TextView tvOutdateTime;
        TextView tvOutdateReason;
        TextView tvHuifangType;

        ImageView iv;
        TextView tv;
        LinearLayout llBt;


        public ViewHolder(View view) {
            super(view);
            ivHead = view.findViewById(R.id.iv_head);
            viperName = view.findViewById(R.id.tv_viper_name);
            viperSex = view.findViewById(R.id.iv_sex);
            tvShentiZhuangtai = view.findViewById(R.id.tv_shenti_zhuangtai);
            tvJianshenAihao = view.findViewById(R.id.tv_jianshen_aihao);
            tvXingquAihao = view.findViewById(R.id.tv_xingqu_aihao);
            tvCarName = view.findViewById(R.id.tv_car_name);

            tvQuanyi = view.findViewById(R.id.tv_quanyi);
            tvOutdateTime = view.findViewById(R.id.tv_outdate_time);
            tvOutdateReason = view.findViewById(R.id.tv_outdate_reason);
            tvHuifangType = view.findViewById(R.id.tv_huifang_type);

            llBt = view.findViewById(R.id.ll_bt);
            iv = view.findViewById(R.id.iv);
            tv = view.findViewById(R.id.tv);
        }
    }
}
