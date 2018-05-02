package com.yijian.staff.mvp.huiji.viperlist.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.bean.HuiJiViperBean;
import com.yijian.staff.mvp.huiji.detail.HuiJiViperDetailActivity;
import com.yijian.staff.util.ImageLoader;

import java.util.List;

public class HuiJiTodayVisitAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HuiJiViperBean> viperBeanList;
    private Context context;

    public HuiJiTodayVisitAdapter(Context context, List<HuiJiViperBean> viperBeanList) {
        this.viperBeanList = viperBeanList;
        this.context = context;
    }

    public void update(List<HuiJiViperBean> viperBeanList) {
        this.viperBeanList = viperBeanList;
        notifyDataSetChanged();
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
        HuiJiViperBean huiJiViperBean = viperBeanList.get(position);
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

        public void bind(Context context, HuiJiViperBean huiJiViperBean){
            ImageLoader.setImageResource(huiJiViperBean.getHeadImg(), context, iv_header);
            iv_gender.setImageResource("1".equals(huiJiViperBean.getSex()) ? R.mipmap.lg_man : R.mipmap.lg_women);
            tv_name.setText(huiJiViperBean.getName());
            tv_arrival_time.setText(huiJiViperBean.getVisitTime());
            tv_leaving_time.setText(huiJiViperBean.getLeaveTime());
//            tv_today_consumption.setText(huiJiViperBean.getLeaveTime());
            lin_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //viperDetailBean
                    Intent intent = new Intent(context, HuiJiViperDetailActivity.class);
                    intent.putExtra("viperDetailBean",huiJiViperBean);
                    context.startActivity(intent);
                }
            });
        }

    }

}
