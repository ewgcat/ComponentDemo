package com.yijian.staff.mvp.reception;

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
import com.yijian.staff.util.Logger;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/2/28 17:08:17
 */
public class ReceptionHistoryAdapter extends RecyclerView.Adapter<ReceptionHistoryAdapter.ViewHolder> {
    private List<ReceptionInfo> mReceptionInfoList;
    private Context context;

    public ReceptionHistoryAdapter(Context context, List<ReceptionInfo> mReceptionInfoList) {
        this.mReceptionInfoList = mReceptionInfoList;
        this.context = context;
    }

    @Override
    public ReceptionHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reception_history, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ReceptionHistoryAdapter.ViewHolder holder, int position) {
        Logger.i("CoachHuiFangTaskAdapter","position: "+position);
        ReceptionInfo receptionInfo = mReceptionInfoList.get(position);
        holder.viperName.setText(receptionInfo.getName());
        holder.receptionStatus.setText(receptionInfo.getStatus());
        holder.viperPhone.setText(receptionInfo.getPhone());
        holder.product.setText(receptionInfo.getProduct());
        Glide.with(context).load(R.mipmap.lg_man).into(holder.viperSex);
        holder.wenJuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,QuestionnaireResultActivity.class);
                context.startActivity(i);
            }
        });
        holder.tiCeBaoGao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,PhysicalReportActivity.class);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mReceptionInfoList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView viperName;
        ImageView viperSex;
        TextView receptionStatus;
        TextView viperPhone;
        TextView wenJuan;
        TextView tiCeBaoGao;
        TextView product;
        TextView jiedaiName;
        TextView jiedaiCoachName;

        public ViewHolder(View view) {
            super(view);
            viperName=     view.findViewById(R.id.tv_viper_name);
            viperSex=     view.findViewById(R.id.iv_sex);
            receptionStatus=     view.findViewById(R.id.tv_status);
            viperPhone=     view.findViewById(R.id.tv_viper_phone);
            product=     view.findViewById(R.id.tv_product);
            wenJuan=     view.findViewById(R.id.tv_wenjuan);
            tiCeBaoGao=     view.findViewById(R.id.tv_ticebaogao);
            jiedaiName=     view.findViewById(R.id.tv_jiedai_name);
            jiedaiCoachName=     view.findViewById(R.id.tv_coach_name);

        }
    }
}
