package com.yijian.staff.mvp.coach.setclass.orderclass;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.setclass.ExperienceClassRecordActivity;
import com.yijian.staff.mvp.coach.setclass.OpenLessonNewActivity;
import com.yijian.staff.mvp.coach.setclass.bean.OrderClassDayBean;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.yijian.staff.mvp.coach.setclass.orderclass.OrderClassActivity.ORDER_REFRESH_REQUESTCODE;

public class OrderclassDayAdapter extends RecyclerView.Adapter<OrderclassDayAdapter.ViewHolder> {

    private List<OrderClassDayBean> orderClassDayBeanList = new ArrayList<>();
    private Fragment fragment;

    public OrderclassDayAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    public void resetDataList(List<OrderClassDayBean> orderClassDayBeans) {
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
            holder.bind(orderClassDayBean, position, orderClassDayBeanList, fragment);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return orderClassDayBeanList == null ? 0 : orderClassDayBeanList.size();
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
        ImageView iv_order_class_statu;
        ImageView iv_status_ysk; //已上课
        ImageView iv_status_sy; //爽约
        ImageView iv_status_cancel; //取消预约
        TextView tv_order_class_statu;
        RelativeLayout rel_statu;


        public ViewHolder(View view) {
            super(view);
            tv_startOrderTime = view.findViewById(R.id.tv_startOrderTime);
            tv_endOrderTime = view.findViewById(R.id.tv_endOrderTime);
            tv_className = view.findViewById(R.id.tv_className);
            tv_venue = view.findViewById(R.id.tv_venue);
            tv_stuList = view.findViewById(R.id.tv_stuList);
            tv_stu_num = view.findViewById(R.id.tv_stu_num);
            tv_intervalTime = view.findViewById(R.id.tv_intervalTime);
            view_last_line = view.findViewById(R.id.view_last_line);
            iv_order_class_statu = view.findViewById(R.id.iv_order_class_statu);
            tv_order_class_statu = view.findViewById(R.id.tv_order_class_statu);
            rel_statu = view.findViewById(R.id.rel_statu);
            iv_status_ysk = view.findViewById(R.id.iv_status_ysk);
            iv_status_sy = view.findViewById(R.id.iv_status_sy);
            iv_status_cancel = view.findViewById(R.id.iv_status_cancel);
        }

        public void bind(OrderClassDayBean orderClassDayBean, int position, List<OrderClassDayBean> orderClassDayBeanList, Fragment fragment) throws ParseException {
            tv_startOrderTime.setText(orderClassDayBean.getStartDatetime());
            tv_endOrderTime.setText(orderClassDayBean.getEndDatetime());
            tv_className.setText(orderClassDayBean.getLessonName());
            tv_venue.setText(orderClassDayBean.getLessonPlace());
            tv_stuList.setText(orderClassDayBean.getMemberName());
            tv_stu_num.setText("1人");


            iv_order_class_statu.setVisibility(View.GONE);
            tv_order_class_statu.setVisibility(View.GONE);
            iv_status_ysk.setVisibility(View.GONE);
            iv_status_sy.setVisibility(View.GONE);
            iv_status_cancel.setVisibility(View.GONE);
            //教练上课打卡状态(0:未打卡 1:正在上课 2:下课已打卡)
            int punchStatus = orderClassDayBean.getPunchStatus();
            //状态（1已约课，2取消约课，3：会员已上课，4：会员爽约）
            int status = orderClassDayBean.getStatus();

            int resStatu = 0;
            String strStatu = "";
            if(status == 2){
                iv_status_cancel.setVisibility(View.VISIBLE);
            }else if(status == 4){
                iv_status_sy.setVisibility(View.VISIBLE);
            }else{
                if (punchStatus == 0 || punchStatus == 1) {
                    iv_order_class_statu.setVisibility(View.VISIBLE);
                    tv_order_class_statu.setVisibility(View.VISIBLE);
                    resStatu = R.mipmap.lesson_class;
                    strStatu = "上课";
                }else{
                    iv_status_ysk.setVisibility(View.VISIBLE);
                }
            }

            rel_statu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (punchStatus == 0 || punchStatus == 1) {
                        Intent intent = new Intent(fragment.getActivity(), OpenLessonNewActivity.class);
                        intent.putExtra("startDate", orderClassDayBean.getStartDate());
                        intent.putExtra("startTimeActual", orderClassDayBean.getStartTimeActual());
                        intent.putExtra("endTimeActual", orderClassDayBean.getEndTimeActual());
                        intent.putExtra("punchStatus", orderClassDayBean.getPunchStatus());
                        intent.putExtra("privateApplyId", orderClassDayBean.getId());
                        fragment.startActivityForResult(intent, ORDER_REFRESH_REQUESTCODE);

                    }

                }
            });

            iv_order_class_statu.setImageResource(resStatu);
            tv_order_class_statu.setText(strStatu);

            tv_intervalTime.setText("约课时间间隔" + orderClassDayBean.getIntervalTime() + "分钟");

            if (position == orderClassDayBeanList.size() - 1) {
                view_last_line.setVisibility(View.GONE);
            }
        }

    }

}
