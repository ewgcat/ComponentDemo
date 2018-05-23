package com.yijian.staff.mvp.coach.viperlist.adpater;

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
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yijian.staff.R;
import com.yijian.staff.bean.TodayCoachViperBean;
import com.yijian.staff.mvp.coach.detail.CoachViperDetailActivity;
import com.yijian.staff.mvp.coach.detail.CoachViperDetailActivity_ycm;
import com.yijian.staff.util.DateUtil;
import com.yijian.staff.util.GlideCircleTransform;
import com.yijian.staff.util.ImageLoader;

import java.util.List;

/**
 * Created by yangk on 2018/3/6.
 */

public class CoachTodayViperListAdapter extends RecyclerView.Adapter<CoachTodayViperListAdapter.ViewHolder> {

    private List<TodayCoachViperBean> coachViperBeanList;
    private Context context;
    private Boolean isAllVipInfo; // true 全部会员，false  今日来访

    public CoachTodayViperListAdapter(Context context, List<TodayCoachViperBean> coachViperBeanList, boolean isAllVipInfo) {
        this.context = context;
        this.coachViperBeanList = coachViperBeanList;
        this.isAllVipInfo = isAllVipInfo;
    }

    @Override
    public CoachTodayViperListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coach_today_vip_info, parent, false);
        CoachTodayViperListAdapter.ViewHolder holder = new CoachTodayViperListAdapter.ViewHolder(view);
        return holder;
    }

    public void update(List<TodayCoachViperBean> coachViperBeanList) {
        this.coachViperBeanList = coachViperBeanList;
        notifyDataSetChanged();
    }

    private void setImageResource(String path,ImageView imageView) {

    }
    @Override
    public void onBindViewHolder(CoachTodayViperListAdapter.ViewHolder holder, int position) {
        TodayCoachViperBean coachViperBean = coachViperBeanList.get(position);


        holder.tv_name.setText(coachViperBean.getName());
        int resId;
        if (coachViperBean.getSex()==1) {
            resId = R.mipmap.lg_man;
        } else if (coachViperBean.getSex()==2) {
            resId = R.mipmap.lg_women;
        } else {
            resId = R.mipmap.lg_man;
        }
        holder.iv_gender.setImageResource(resId);

        String headImg = coachViperBean.getHeadImg();


        ImageLoader.setHeadImageResource(headImg,context,holder.iv_header);
        //详情
        holder.ll_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, CoachViperDetailActivity.class);
                Intent intent = new Intent(context, CoachViperDetailActivity_ycm.class);
                intent.putExtra("vipType", 0);
                intent.putExtra("memberId", coachViperBean.getMemberId());
                context.startActivity(intent);
            }
        });

        int clockedCount = coachViperBean.getClockedCount();
        if (clockedCount!=-1){

            holder.tv_daka_total_count.setText(clockedCount+"次");
        }
        Long bePresentTime = coachViperBean.getVisitTime();
        if (bePresentTime!=null&&bePresentTime!=-1){
            String s = DateUtil.parseLongDateToTimeString(bePresentTime);
            holder.tv_be_present_time.setText(s);
        }

        Long departureTime = coachViperBean.getLeaveTime();
        if (departureTime!=null&&departureTime!=-1){
            String s1 = DateUtil.parseLongDateToTimeString(departureTime);
            holder.tv_be_departure_time.setText(s1);
        }


    }

    @Override
    public int getItemCount() {
        return coachViperBeanList == null ? 0 : coachViperBeanList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_header;
        ImageView iv_gender;
        TextView tv_name;
        TextView tv_daka_total_count;
        TextView tv_be_present_time;
        TextView tv_be_departure_time;

        LinearLayout ll_content;

        public ViewHolder(View view) {
            super(view);
            ll_content = view.findViewById(R.id.ll_content);
            iv_header = view.findViewById(R.id.iv_header);
            iv_gender = view.findViewById(R.id.iv_gender);
            tv_name = view.findViewById(R.id.tv_name);
            tv_daka_total_count = view.findViewById(R.id.tv_daka_total_count);
            tv_be_present_time = view.findViewById(R.id.tv_be_present_time);
            tv_be_departure_time = view.findViewById(R.id.tv_be_departure_time);
        }
    }

}

