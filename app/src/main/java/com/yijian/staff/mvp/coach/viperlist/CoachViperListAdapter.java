package com.yijian.staff.mvp.coach.viperlist;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import com.yijian.staff.bean.CoachViperBean;
import com.yijian.staff.mvp.coach.card.CoachVipCardListAdapter;
import com.yijian.staff.mvp.coach.detail.CoachViperDetailActivity;
import com.yijian.staff.mvp.reception.physical.PhysicalReportActivity;
import com.yijian.staff.mvp.coach.recordchart.RecordChartActivity;
import com.yijian.staff.util.DateUtil;

import java.util.List;

/**
 * Created by yangk on 2018/3/6.
 */

public class CoachViperListAdapter extends RecyclerView.Adapter<CoachViperListAdapter.ViewHolder> {

    private List<CoachViperBean> coachViperBeanList;
    private Context context;
    private Boolean isAllVipInfo; // true 全部会员，false  今日来访

    public CoachViperListAdapter(Context context, List<CoachViperBean> coachViperBeanList, boolean isAllVipInfo) {
        this.context = context;
        this.coachViperBeanList = coachViperBeanList;
        this.isAllVipInfo = isAllVipInfo;
    }

    @Override
    public CoachViperListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coach_vip_info, parent, false);
        CoachViperListAdapter.ViewHolder holder = new CoachViperListAdapter.ViewHolder(view);
        return holder;
    }

    public void update( List<CoachViperBean> coachViperBeanList){
        this.coachViperBeanList = coachViperBeanList;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(CoachViperListAdapter.ViewHolder holder, int position) {
        CoachViperBean coachViperBean = coachViperBeanList.get(position);
        holder.rel_be_present_time.setVisibility(isAllVipInfo ? View.GONE : View.VISIBLE);
        holder.rel_be_departure_time.setVisibility(isAllVipInfo ? View.GONE : View.VISIBLE);

        holder.tv_name.setText(coachViperBean.getName());
        int resId;
        if (coachViperBean.getSex().equals("1")){
             resId =  R.mipmap.lg_man ;
        } else if (coachViperBean.getSex().equals("2")){
             resId =  R.mipmap.lg_women ;
        }else {
            resId =  R.mipmap.lg_man ;

        }
        holder.iv_gender.setImageResource(resId);


        holder.rv_card.setLayoutManager(new LinearLayoutManager(context));
        holder.rv_card.setAdapter(new CoachVipCardListAdapter(coachViperBean.getCardprodsBeans()));

        holder.rel_expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleCardView(holder);
            }
        });

        holder.tv_private_class.setText(coachViperBean.getPrivateClass());
        holder.tv_like_lesson.setText(coachViperBean.getFavorCourse());
        holder.tv_like_teacher.setText(coachViperBean.getFavorTeacher());
        long registerTime = coachViperBean.getRegisterTime();
        if (registerTime!=0){
            String s = DateUtil.parseLongDateToDateString(registerTime);
            holder.tv_regist_time.setText(s);
        }else {
            holder.tv_regist_time.setText("");
        }

        long contractDeadline = coachViperBean.getContractDeadline();
        if (contractDeadline!=0){
            String s = DateUtil.parseLongDateToDateString(contractDeadline);
            holder.tv_contract_overTime.setText(s);
        }else {
            holder.tv_contract_overTime.setText("");
        }

        holder.tv_contract_balance.setText(coachViperBean.getContractBalance()+"元");
        holder.tv_buy_count.setText(coachViperBean.getPurchaseCount()+"");
        long bePresentTime = coachViperBean.getBePresentTime();
        if (bePresentTime!=0){
            String s = DateUtil.parseLongDateToDateString(bePresentTime);
            holder.tv_be_present_time.setText(s);
        }else {
            holder.tv_be_present_time.setText("");
        }

        long departureTime = coachViperBean.getDepartureTime();
        if (departureTime!=0){
            String s = DateUtil.parseLongDateToDateString(contractDeadline);
            holder.tv_be_departure_time.setText(s);
        }else {
            holder.tv_be_departure_time.setText("");
        }

        //上课记录表
        holder.lin_shangke_recordchart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecordChartActivity.class);
                intent.putExtra("memberId",coachViperBean.getMemberId());
                intent.putExtra("memberName",coachViperBean.getName());
                intent.putExtra("fiirstId",coachViperBean.getFiirstId());
                intent.putExtra("secondId",coachViperBean.getSecondId());
                context.startActivity(intent);
            }
        });
        //体测数据
        holder.lin_ti_ce_shu_ju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PhysicalReportActivity.class);
                intent.putExtra("memberId",coachViperBean.getMemberId());
                intent.putExtra("memberName",coachViperBean.getName());
                context.startActivity(intent);
            }
        });
        //详情
        holder.lin_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CoachViperDetailActivity.class);
                intent.putExtra("vipType",0);
                intent.putExtra("coachViperBean",coachViperBean);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return coachViperBeanList == null ? 0 : coachViperBeanList.size();
    }

    private void toggleCardView(CoachViperListAdapter.ViewHolder holder) {
        int visibility = holder.rv_card.getVisibility();
        if (visibility == View.GONE) {
            holder.rv_card.setVisibility(View.VISIBLE);
            holder.tv_zhankai_status.setText("收起");
            Drawable drawable = context.getDrawable(R.mipmap.fp_shang);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

            holder.tv_zhankai_status.setCompoundDrawables(null,null,drawable,null);
        } else {
            holder.rv_card.setVisibility(View.GONE);
            holder.tv_zhankai_status.setText("展开");
            Drawable drawable = context.getDrawable(R.mipmap.lg_xiala);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

            holder.tv_zhankai_status.setCompoundDrawables(null,null,drawable,null);
        }

    }
    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_header;
        ImageView iv_gender;
        TextView tv_name;
        TextView tv_zhankai_status;
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
            tv_zhankai_status = view.findViewById(R.id.tv_zhankai_status);

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

