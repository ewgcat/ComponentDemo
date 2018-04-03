package com.yijian.staff.mvp.coach.viperlist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.bean.ViperBean;
import com.yijian.staff.mvp.huiji.bean.HuiJiVipeCardAdapter;
import com.yijian.staff.mvp.physical.PhysicalReportActivity;
import com.yijian.staff.mvp.coach.recordchart.RecordChartActivity;
import com.yijian.staff.mvp.vip.detail.ViperDetailActivity;

import java.util.List;

/**
 * Created by yangk on 2018/3/6.
 */

public class CoachViperListAdapter extends RecyclerView.Adapter<CoachViperListAdapter.ViewHolder> {

    private List<ViperBean> viperBeanList;
    private Context context;
    private Boolean isAllVipInfo; // true 全部会员，false  今日来访

    public CoachViperListAdapter(Context context, List<ViperBean> viperBeanList, boolean isAllVipInfo) {
        this.context = context;
        this.viperBeanList = viperBeanList;
        this.isAllVipInfo = isAllVipInfo;
    }

    @Override
    public CoachViperListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coach_vip_info, parent, false);
        CoachViperListAdapter.ViewHolder holder = new CoachViperListAdapter.ViewHolder(view);
        return holder;
    }

    public void update( List<ViperBean> viperBeanList){
        this.viperBeanList=viperBeanList;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(CoachViperListAdapter.ViewHolder holder, int position) {
        ViperBean viperBean = viperBeanList.get(position);
        holder.rel_be_present_time.setVisibility(isAllVipInfo ? View.GONE : View.VISIBLE);
        holder.rel_be_departure_time.setVisibility(isAllVipInfo ? View.GONE : View.VISIBLE);

        holder.tv_name.setText(viperBean.getName());
        holder.iv_gender.setImageResource("0".equals(viperBean.getSex()) ? R.mipmap.lg_man : R.mipmap.lg_women);


        holder.rv_card.setLayoutManager(new LinearLayoutManager(context));
        holder.rv_card.setAdapter(new HuiJiVipeCardAdapter(viperBean.getCardprodsBeans()));

        holder.rel_expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.rv_card.setVisibility((holder.rv_card.getVisibility()==View.GONE)?View.VISIBLE:View.GONE);
            }
        });

        holder.tv_private_class.setText(viperBean.getPrivateClass());
        holder.tv_like_lesson.setText(viperBean.getFavorCourse());
        holder.tv_like_teacher.setText(viperBean.getFavorTeacher());
        holder.tv_regist_time.setText(viperBean.getRegisterTime());
        holder.tv_contract_overTime.setText(viperBean.getContractDeadline());
        holder.tv_contract_balance.setText(viperBean.getContractBalance());
        holder.tv_buy_count.setText(viperBean.getPurchaseCount()+"");
        holder.tv_be_present_time.setText(viperBean.getBePresentTime());
        holder.tv_be_departure_time.setText(viperBean.getDepartureTime());

        //上课记录表
        holder.lin_shangke_recordchart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, RecordChartActivity.class));
            }
        });
        //体测数据
        holder.lin_ti_ce_shu_ju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, PhysicalReportActivity.class));
            }
        });
        //详情
        holder.lin_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ViperDetailActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return viperBeanList == null ? 0 : viperBeanList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_header;
        ImageView iv_gender;
        TextView tv_name;
        RelativeLayout rel_expand;
        RecyclerView rv_card;

        TextView tv_private_class;
        TextView tv_like_lesson;
        TextView tv_like_teacher;
        TextView tv_regist_time;
        TextView tv_contract_overTime;
        TextView tv_contract_balance;
        TextView tv_buy_count;
        TextView tv_be_present_time;
        TextView tv_be_departure_time;
        RelativeLayout rel_be_present_time;
        RelativeLayout rel_be_departure_time;
        LinearLayout lin_shangke_recordchart;
        LinearLayout lin_ti_ce_shu_ju;
        LinearLayout lin_content;

        public ViewHolder(View view) {
            super(view);
            lin_content = view.findViewById(R.id.lin_content);
            iv_header = view.findViewById(R.id.iv_header);
            iv_gender = view.findViewById(R.id.iv_gender);
            tv_name = view.findViewById(R.id.tv_name);

            rel_expand = view.findViewById(R.id.rel_expand);
            rv_card = view.findViewById(R.id.rv_card);
            tv_private_class = view.findViewById(R.id.tv_private_class);
            tv_like_lesson = view.findViewById(R.id.tv_like_lesson);
            tv_like_teacher = view.findViewById(R.id.tv_like_teacher);
            tv_regist_time = view.findViewById(R.id.tv_regist_time);
            tv_contract_overTime = view.findViewById(R.id.tv_contract_overTime);
            tv_contract_balance = view.findViewById(R.id.tv_contract_balance);
            tv_buy_count = view.findViewById(R.id.tv_buy_count);
            tv_be_present_time = view.findViewById(R.id.tv_be_present_time);
            tv_be_departure_time = view.findViewById(R.id.tv_be_departure_time);
            rel_be_present_time = view.findViewById(R.id.rel_be_present_time);
            rel_be_departure_time = view.findViewById(R.id.rel_be_departure_time);
            lin_shangke_recordchart = view.findViewById(R.id.lin_shangke_recordchart);
            lin_ti_ce_shu_ju = view.findViewById(R.id.lin_ti_ce_shu_ju);
        }
    }

}

