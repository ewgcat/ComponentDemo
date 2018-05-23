package com.yijian.staff.mvp.huiji.viperlist.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.huiji.bean.TodayHuiJiViperBean;
import com.yijian.staff.mvp.huiji.detail.HuiJiViperDetailActivity_ycm;
import com.yijian.staff.util.DateUtil;
import com.yijian.staff.util.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HuiJiTodayVisitAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<TodayHuiJiViperBean> viperBeanList=new ArrayList<>();
    private Context context;


    public HuiJiTodayVisitAdapter(Context context,List<TodayHuiJiViperBean> viperBeanList) {
        this.context = context;
        this.viperBeanList = viperBeanList;

    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_huiji_vip_today_visit,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        this.context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TodayHuiJiViperBean huiJiViperBean = viperBeanList.get(position);
        ((ViewHolder)holder).bind(context,huiJiViperBean);
    }

    @Override
    public int getItemCount() {
        return viperBeanList != null ? viperBeanList.size() : 0;
    }

    private class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv_header;
        private TextView tv_name;
        private ImageView iv_gender;
        private TextView tv_today_consumption; //今日消费
        private TextView tv_arrival_time; //到场时间
        private TextView tv_leaving_time; //离场时间
        private LinearLayout lin_container;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_header = itemView.findViewById(R.id.iv_header);
            tv_name = itemView.findViewById(R.id.tv_name);
            iv_gender = itemView.findViewById(R.id.iv_gender);
            tv_today_consumption = itemView.findViewById(R.id.tv_today_consumption);
            tv_arrival_time = itemView.findViewById(R.id.tv_arrival_time);
            tv_leaving_time = itemView.findViewById(R.id.tv_leaving_time);
            lin_container = itemView.findViewById(R.id.lin_container);
        }

        public void bind(Context context, TodayHuiJiViperBean huiJiViperBean){
            ImageLoader.setHeadImageResource(huiJiViperBean.getHeadImg(), context, iv_header);
            iv_gender.setImageResource(huiJiViperBean.getGenderImg());
            tv_name.setText(huiJiViperBean.getName());

            int clockedCount = huiJiViperBean.getClockedCount();
            if (clockedCount!=-1){

                tv_today_consumption.setText(clockedCount+"次");
            }
            Long bePresentTime = huiJiViperBean.getVisitTime();
            if (bePresentTime!=null&&bePresentTime!=-1){
                String s = DateUtil.parseLongDateToTimeString(bePresentTime);
                tv_arrival_time.setText(s);
            }

            Long departureTime = huiJiViperBean.getLeaveTime();
            if (departureTime!=null&&departureTime!=-1){
                String s1 = DateUtil.parseLongDateToTimeString(departureTime);
                tv_leaving_time.setText(s1);
            }


            lin_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //viperDetailBean
//                    Intent intent = new Intent(context, HuiJiViperDetailActivity.class);
                    Intent intent = new Intent(context, HuiJiViperDetailActivity_ycm.class);
                    intent.putExtra("memberId",huiJiViperBean.getMemberId());
//                    intent.putExtra("memberName",huiJiViperBean.getName());
                    context.startActivity(intent);
                }
            });
        }

    }

}
