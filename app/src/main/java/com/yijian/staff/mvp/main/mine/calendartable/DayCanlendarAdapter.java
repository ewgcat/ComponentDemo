package com.yijian.staff.mvp.main.mine.calendartable;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.bean.DayCanlendarInfo;
import com.yijian.staff.mvp.course.punch.PunchCourseActivity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.yijian.staff.mvp.course.setclass.orderclass.OrderClassActivity.ORDER_REFRESH_REQUESTCODE;

/**
 * Created by yangk on 2018/3/15.
 * 日视图Adapter适配器  item_day_canlendar
 */

public class DayCanlendarAdapter extends RecyclerView.Adapter<DayCanlendarAdapter.ViewHolder> {

    private List<DayCanlendarInfo> dayCanlendarInfoList = new ArrayList<>();
    private Fragment fragment;
    private int itemHeight;
    private int ITEM_BODY = 0;
    private int ITEM_FOOTER = 0;


    public DayCanlendarAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    public void resetDataList(List<DayCanlendarInfo> dayCanlendarInfos) {
        this.dayCanlendarInfoList = dayCanlendarInfos;
        notifyDataSetChanged();
    }

    @Override
    public DayCanlendarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day_canlendar, parent, false);
        DayCanlendarAdapter.ViewHolder holder = new DayCanlendarAdapter.ViewHolder(view);
        LinearLayout lin_container = view.findViewById(R.id.lin_container);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) lin_container.getLayoutParams();
        itemHeight = lp.height;
        return holder;
    }

    @Override
    public void onBindViewHolder(DayCanlendarAdapter.ViewHolder holder, int position) {
        if (holder.getItemViewType() == ITEM_BODY) {
            DayCanlendarInfo dayCanlendarInfo = (DayCanlendarInfo) dayCanlendarInfoList.get(position);
            try {
                holder.bind(dayCanlendarInfo, position, fragment);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{

        }
    }

    @Override
    public int getItemViewType(int position) {
        Object obj = dayCanlendarInfoList.get(position);
        if (obj instanceof DayCanlendarInfo) {
            return ITEM_BODY;
        } else {
            return ITEM_FOOTER;
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
        ImageView iv_order_class_statu;
        ImageView iv_status_ysk; //已上课
        ImageView iv_status_sy; //爽约
        ImageView iv_status_cancel; //取消预约
        TextView tv_order_class_statu;
        RelativeLayout rel_statu;
        RelativeLayout rel_course;

        public ViewHolder(View view) {
            super(view);
            tv_startOrderTime = view.findViewById(R.id.tv_startOrderTime);
            tv_endOrderTime = view.findViewById(R.id.tv_endOrderTime);
            tv_className = view.findViewById(R.id.tv_className);
            tv_venue = view.findViewById(R.id.tv_venue);
            tv_stuList = view.findViewById(R.id.tv_stuList);
            tv_stu_num = view.findViewById(R.id.tv_stu_num);
            tv_intervalTime = view.findViewById(R.id.tv_intervalTime);
//            iv_status = view.findViewById(R.id.iv_status);
            view_last_line = view.findViewById(R.id.view_last_line);
            iv_order_class_statu = view.findViewById(R.id.iv_order_class_statu);
            tv_order_class_statu = view.findViewById(R.id.tv_order_class_statu);
            rel_statu = view.findViewById(R.id.rel_statu);
            rel_course = view.findViewById(R.id.rel_course);
            iv_status_ysk = view.findViewById(R.id.iv_status_ysk);
            iv_status_sy = view.findViewById(R.id.iv_status_sy);
            iv_status_cancel = view.findViewById(R.id.iv_status_cancel);
        }

        public void bind(DayCanlendarInfo dayCanlendarInfo, int position, Fragment fragment) throws ParseException {

            tv_startOrderTime.setText(dayCanlendarInfo.getStartDatetime());
            tv_endOrderTime.setText(dayCanlendarInfo.getEndDatetime());
            tv_className.setText(dayCanlendarInfo.getLessonName());
            tv_venue.setText(dayCanlendarInfo.getLessonPlace());
            tv_stuList.setText(dayCanlendarInfo.getMemberName());
            tv_stu_num.setText("1人");

            iv_order_class_statu.setVisibility(View.GONE);
            tv_order_class_statu.setVisibility(View.GONE);
            iv_status_ysk.setVisibility(View.GONE);
            iv_status_sy.setVisibility(View.GONE);
            iv_status_cancel.setVisibility(View.GONE);
            //教练上课打卡状态(0:未打卡 1:正在上课 2:下课已打卡)
            int punchStatus = dayCanlendarInfo.getPunchStatus();
            //状态（1已约课，2取消约课，3：会员已上课，4：会员爽约）
            int status = dayCanlendarInfo.getStatus();
            int resStatu = 0;
            String strStatu = "";
            if (status == 2) {
                iv_status_cancel.setVisibility(View.VISIBLE);
            } else if (status == 4) {
                iv_status_sy.setVisibility(View.VISIBLE);
            } else {
                if (punchStatus == 0 || punchStatus == 1) {
                    iv_order_class_statu.setVisibility(View.VISIBLE);
                    tv_order_class_statu.setVisibility(View.VISIBLE);
                    resStatu = R.mipmap.lesson_class;
                    strStatu = "上课";
                } else {
                    iv_status_ysk.setVisibility(View.VISIBLE);
                }
            }
            rel_course.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (status == 2) {
                        Toast.makeText(fragment.getActivity(), "该课已取消预约", Toast.LENGTH_SHORT).show();
                    } else if (status == 4) {
                        Toast.makeText(fragment.getActivity(), "该课已爽约", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(fragment.getActivity(), PunchCourseActivity.class);
                        intent.putExtra("startDate", dayCanlendarInfo.getStartDate());
                        intent.putExtra("startTimeActual", dayCanlendarInfo.getStartTimeActual());
                        intent.putExtra("endTimeActual", dayCanlendarInfo.getEndTimeActual());
                        intent.putExtra("punchStatus", dayCanlendarInfo.getPunchStatus());
                        intent.putExtra("privateApplyId", dayCanlendarInfo.getId());
                        fragment.startActivityForResult(intent, ORDER_REFRESH_REQUESTCODE);
                    }

                }
            });
            iv_order_class_statu.setImageResource(resStatu);
            tv_order_class_statu.setText(strStatu);
            tv_intervalTime.setText("约课时间间隔" + dayCanlendarInfo.getIntervalTime() + "分钟");

        }

    }


}
