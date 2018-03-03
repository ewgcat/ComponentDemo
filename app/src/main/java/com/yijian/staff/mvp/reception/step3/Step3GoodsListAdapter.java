package com.yijian.staff.mvp.reception.step3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yijian.staff.R;
import com.yijian.staff.mvp.physical.PhysicalReportActivity;
import com.yijian.staff.mvp.questionnaireresult.QuestionnaireResultActivity;
import com.yijian.staff.mvp.reception.ReceptionInfo;
import com.yijian.staff.util.Logger;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/3 17:08:17
 */
public class Step3GoodsListAdapter extends RecyclerView.Adapter<Step3GoodsListAdapter.ViewHolder> {
    private List<GoodsInfo> mGoodsInfoList;
    private Context context;

    public Step3GoodsListAdapter(Context context, List<GoodsInfo> mGoodsInfoList) {
        this.mGoodsInfoList = mGoodsInfoList;
        this.context = context;
    }

    @Override
    public Step3GoodsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Step3GoodsListAdapter.ViewHolder holder, int position) {
        Logger.i("Step3GoodsListAdapter","position: "+position);

        GoodsInfo goodsInfo = mGoodsInfoList.get(position);
        holder.tvGoodsName.setText(goodsInfo.getGoodsName());
        holder.tvJianshenplace.setText(goodsInfo.getJianshenplace());
        holder.tvYuEr.setText(goodsInfo.getYuer());
        holder.tvChuzhiyouhui.setText(goodsInfo.getChuzhiyouhui());
        holder.tvPrice.setText(goodsInfo.getPrice());
    }

    @Override
    public int getItemCount() {
        return mGoodsInfoList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvGoodsName;
        TextView tvJianshenplace;
        TextView tvYuEr;
        TextView tvChuzhiyouhui;
        TextView tvPrice;


        public ViewHolder(View view) {
            super(view);
            tvGoodsName=     view.findViewById(R.id.tv_goods_name);
            tvJianshenplace=     view.findViewById(R.id.tv_jianshenplace);
            tvYuEr=     view.findViewById(R.id.tv_yu_er);
            tvChuzhiyouhui=     view.findViewById(R.id.tv_chuzhiyouhui);
            tvPrice=     view.findViewById(R.id.tv_price);

        }
    }
}
