package com.yijian.staff.mvp.main.mine.calendartable;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.yijian.staff.R;
import com.yijian.staff.mvp.complaint.handling.CommentFlowLayout;
import com.yijian.staff.mvp.complaint.handling.HandImgViewGroup;
import java.util.List;

/**
 * Created by yangk on 2018/3/15.
 * 日视图Adapter适配器
 */

public class DayCanlendarAdapter extends RecyclerView.Adapter<DayCanlendarAdapter.ViewHolder> {

    private List<DayCanlendarInfo> dayCanlendarInfoList;
    private Context context;  

    public DayCanlendarAdapter(Context context, List<DayCanlendarInfo> dayCanlendarInfoList){
        this.context = context;
        this.dayCanlendarInfoList = dayCanlendarInfoList; 
    }

    @Override
    public DayCanlendarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day_canlendar, parent, false);
        DayCanlendarAdapter.ViewHolder holder = new DayCanlendarAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DayCanlendarAdapter.ViewHolder holder, int position) {
        DayCanlendarInfo dayCanlendarInfo = dayCanlendarInfoList.get(position);
        holder.tv_startOrderTime.setText(dayCanlendarInfo.getStartOrderTime());
        holder.tv_endOrderTime.setText(dayCanlendarInfo.getEndOrderTime());
        holder.tv_className.setText(dayCanlendarInfo.getClassName());
        holder.tv_venue.setText(dayCanlendarInfo.getVenue());
        StringBuffer sb = new StringBuffer();
        for(String strName : dayCanlendarInfo.getStuList()){
            sb.append(strName+" ");
        }
        holder.tv_stuList.setText(sb==null?"":sb.toString());
        holder.tv_stu_num.setText((dayCanlendarInfo.getStuList()==null?0:dayCanlendarInfo.getStuList().size())+"人");
//        holder.iv_finish.setVisibility("0".equals(dayCanlendarInfo.getStatus())?View.GONE:View.VISIBLE);
        holder.tv_intervalTime.setText("约课时间间隔"+dayCanlendarInfo.getIntervalTime()+"分钟");
        if(position == dayCanlendarInfoList.size()-1){
            holder.view_last_line.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return dayCanlendarInfoList == null ? 0 : dayCanlendarInfoList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_startOrderTime;
        TextView tv_endOrderTime;
        TextView tv_className;
        TextView tv_venue;
        TextView tv_stuList;
        TextView tv_stu_num;
        TextView tv_intervalTime;
        View view_last_line;
//        ImageView iv_finish;


        public ViewHolder(View view) {
            super(view);
            tv_startOrderTime =  view.findViewById(R.id.tv_startOrderTime);
            tv_endOrderTime =  view.findViewById(R.id.tv_endOrderTime);
            tv_className   = view.findViewById(R.id.tv_className);
            tv_venue   = view.findViewById(R.id.tv_venue);
            tv_stuList =     view.findViewById(R.id.tv_stuList);
            tv_stu_num = view.findViewById(R.id.tv_stu_num);
            tv_intervalTime = view.findViewById(R.id.tv_intervalTime);
//            iv_finish = view.findViewById(R.id.iv_finish);
            view_last_line = view.findViewById(R.id.view_last_line);
        }
    }
    
}
