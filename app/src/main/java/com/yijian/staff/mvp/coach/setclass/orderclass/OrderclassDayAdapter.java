package com.yijian.staff.mvp.coach.setclass.orderclass;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.yijian.staff.R;
import com.yijian.staff.mvp.main.mine.calendartable.DayCanlendarInfo;
import com.yijian.staff.mvp.coach.setclass.ExperienceClassRecordActivity;
import com.yijian.staff.mvp.coach.setclass.OpenLessonNewActivity;
import com.yijian.staff.mvp.coach.setclass.bean.OrderClassDayBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OrderclassDayAdapter extends RecyclerView.Adapter<OrderclassDayAdapter.ViewHolder> {

    private List<OrderClassDayBean> orderClassDayBeanList;
    private Context context;

    public OrderclassDayAdapter(Context context, List<OrderClassDayBean> orderClassDayBeanList){
        this.context = context;
        this.orderClassDayBeanList = orderClassDayBeanList;
    }

    public void resetDataList(List<OrderClassDayBean> orderClassDayBeans){
        this.orderClassDayBeanList = orderClassDayBeans;
        notifyDataSetChanged();
    }

    @Override
    public OrderclassDayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day_canlendar, parent, false);
        OrderclassDayAdapter.ViewHolder holder = new OrderclassDayAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(OrderclassDayAdapter.ViewHolder holder, int position) {
        OrderClassDayBean orderClassDayBean = orderClassDayBeanList.get(position);
        try {
            holder.bind(orderClassDayBean,position,orderClassDayBeanList,context);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return orderClassDayBeanList == null ? 0 : orderClassDayBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        List<OrderClassDayBean> orderClassDayBeanList;

        TextView tv_startOrderTime;
        TextView tv_endOrderTime;
        TextView tv_className;
        TextView tv_venue;
        TextView tv_stuList;
        TextView tv_stu_num;
        TextView tv_intervalTime;
        View view_last_line;
//        ImageView iv_status;
        ImageView iv_order_class_statu;
        TextView tv_order_class_statu;
        RelativeLayout rel_statu;


        public ViewHolder(View view) {
            super(view);
            tv_startOrderTime =  view.findViewById(R.id.tv_startOrderTime);
            tv_endOrderTime =  view.findViewById(R.id.tv_endOrderTime);
            tv_className   = view.findViewById(R.id.tv_className);
            tv_venue   = view.findViewById(R.id.tv_venue);
            tv_stuList =     view.findViewById(R.id.tv_stuList);
            tv_stu_num = view.findViewById(R.id.tv_stu_num);
            tv_intervalTime = view.findViewById(R.id.tv_intervalTime);
//            iv_status = view.findViewById(R.id.iv_status);
            view_last_line = view.findViewById(R.id.view_last_line);
            iv_order_class_statu = view.findViewById(R.id.iv_order_class_statu);
            tv_order_class_statu = view.findViewById(R.id.tv_order_class_statu);
            rel_statu = view.findViewById(R.id.rel_statu);
        }

        public void bind(OrderClassDayBean orderClassDayBean, int position, List<OrderClassDayBean> orderClassDayBeanList, Context context) throws ParseException {

//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            tv_startOrderTime.setText(orderClassDayBean.getStartDatetime());
            tv_endOrderTime.setText(orderClassDayBean.getEndDatetime());
            tv_className.setText(orderClassDayBean.getLessonName());
            tv_venue.setText(orderClassDayBean.getLessonPlace());
            tv_stuList.setText(orderClassDayBean.getMemberName());
            tv_stu_num.setText("1人");


            //教练备课状态(0:未备课 1:已备课)
            int isPrepare = orderClassDayBean.getIsPrepare();
            //教练上课打卡状态(0:未打卡 1:正在上课 2:下课已打卡)
            int punchStatus = orderClassDayBean.getPunchStatus();

            int resStatu = 0;
            String strStatu = "";
            if(isPrepare == 0){
                resStatu = R.mipmap.lessons_beike;
                strStatu = "备课";
            }else if(isPrepare == 1){
                if(punchStatus == 0 || punchStatus == 1){
                    resStatu = R.mipmap.lesson_class;
                    strStatu = "上课";
                }else if(punchStatus == 2){
                    resStatu = R.mipmap.lesson_restoration;
                    strStatu = "已完成";
                }
            }
            rel_statu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if("0".equals(orderClassDayBean.getIsExperience())){ // 0：私教课，
                        if(orderClassDayBean.getIsPrepare() == 0){ // 备课

                        }else if(orderClassDayBean.getIsPrepare() == 1){ // 上课
                            Intent intent = new Intent(context, OpenLessonNewActivity.class);
                            intent.putExtra("privateApplyId",orderClassDayBean.getId());
                            intent.putExtra("startDateTime",orderClassDayBean.getStartDatetime());
                            intent.putExtra("endDateTime",orderClassDayBean.getEndDatetime());
                            intent.putExtra("startDate",orderClassDayBean.getStartDate());
                            intent.putExtra("punchStatus",punchStatus);
                            context.startActivity(intent);
                        }
                    }else if("1".equals(orderClassDayBean.getIsExperience())){//1：体验课
                        if("0".equals(orderClassDayBean.getIsUseTemplate())){ //体验课：0：用体侧模板，1：私教课模板 ,
                            Intent intent = new Intent(context, ExperienceClassRecordActivity.class);
                            intent.putExtra("privateApplyId",orderClassDayBean.getId());
                            context.startActivity(intent);
                        }else if("1".equals(orderClassDayBean.getIsUseTemplate())){
                            Intent intent = new Intent(context, OpenLessonNewActivity.class);
                            intent.putExtra("privateApplyId",orderClassDayBean.getId());
                            intent.putExtra("startDateTime",orderClassDayBean.getStartDatetime());
                            intent.putExtra("endDateTime",orderClassDayBean.getEndDatetime());
                            intent.putExtra("startDate",orderClassDayBean.getStartDate());
                            intent.putExtra("punchStatus",punchStatus);
                            context.startActivity(intent);
                        }

                    }
                }
            });

//        holder.iv_status.setVisibility("0".equals(orderClassDayBean.getStatus())?View.GONE:View.VISIBLE);
            iv_order_class_statu.setImageResource(resStatu);
            tv_order_class_statu.setText(strStatu);
            tv_intervalTime.setText("约课时间间隔30分钟");

            if(position == orderClassDayBeanList.size()-1){
                view_last_line.setVisibility(View.GONE);
            }
        }

    }

}
