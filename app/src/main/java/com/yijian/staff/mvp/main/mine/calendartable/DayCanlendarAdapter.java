package com.yijian.staff.mvp.main.mine.calendartable;

import android.content.Context;
import android.content.Intent;
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangk on 2018/3/15.
 * 日视图Adapter适配器  item_day_canlendar
 */

public class DayCanlendarAdapter extends RecyclerView.Adapter<DayCanlendarAdapter.ViewHolder> {

    private List<DayCanlendarInfo> dayCanlendarInfoList = new ArrayList<>();
    private Context context;

    public DayCanlendarAdapter(Context context){
        this.context = context;
    }

    public void resetDataList(List<DayCanlendarInfo> dayCanlendarInfos){
        this.dayCanlendarInfoList = dayCanlendarInfos;
        notifyDataSetChanged();
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
        try {
            holder.bind(dayCanlendarInfo,position,dayCanlendarInfoList,context);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return dayCanlendarInfoList == null ? 0 : dayCanlendarInfoList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        List<DayCanlendarInfo> dayCanlendarInfoList;

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

        public void bind(DayCanlendarInfo dayCanlendarInfo, int position, List<DayCanlendarInfo> dayCanlendarInfoList, Context context) throws ParseException {

//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            tv_startOrderTime.setText(dayCanlendarInfo.getStartDatetime());
            tv_endOrderTime.setText(dayCanlendarInfo.getEndDatetime());
            tv_className.setText(dayCanlendarInfo.getLessonName());
            tv_venue.setText(dayCanlendarInfo.getLessonPlace());
            tv_stuList.setText(dayCanlendarInfo.getMemberName());
            tv_stu_num.setText("1人");


            //教练备课状态(0:未备课 1:已备课)
            int isPrepare = dayCanlendarInfo.getIsPrepare();
            //教练上课打卡状态(0:未打卡 1:正在上课 2:下课已打卡)
            int punchStatus = dayCanlendarInfo.getPunchStatus();

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
                    if("0".equals(dayCanlendarInfo.getIsExperience())){ // 0：私教课，
                        if(dayCanlendarInfo.getIsPrepare() == 0){ // 备课

                        }else if(dayCanlendarInfo.getIsPrepare() == 1){ // 上课
                            Intent intent = new Intent(context, OpenLessonNewActivity.class);
                            intent.putExtra("privateApplyId",dayCanlendarInfo.getId());
                            intent.putExtra("startDateTime",dayCanlendarInfo.getStartDatetime());
                            intent.putExtra("endDateTime",dayCanlendarInfo.getEndDatetime());
                            intent.putExtra("startDate",dayCanlendarInfo.getStartDate());
                            intent.putExtra("punchStatus",punchStatus);
                            context.startActivity(intent);
                        }
                    }else if("1".equals(dayCanlendarInfo.getIsExperience())){//1：体验课
                        if("0".equals(dayCanlendarInfo.getIsUseTemplate())){ //体验课：0：用体侧模板，1：私教课模板 ,
                            Intent intent = new Intent(context, ExperienceClassRecordActivity.class);
                            intent.putExtra("privateApplyId",dayCanlendarInfo.getId());
                            context.startActivity(intent);
                        }else if("1".equals(dayCanlendarInfo.getIsUseTemplate())){
                            Intent intent = new Intent(context, OpenLessonNewActivity.class);
                            intent.putExtra("privateApplyId",dayCanlendarInfo.getId());
                            intent.putExtra("startDateTime",dayCanlendarInfo.getStartDatetime());
                            intent.putExtra("endDateTime",dayCanlendarInfo.getEndDatetime());
                            intent.putExtra("startDate",dayCanlendarInfo.getStartDate());
                            intent.putExtra("punchStatus",punchStatus);
                            context.startActivity(intent);
                        }

                    }
                }
            });

//        holder.iv_status.setVisibility("0".equals(dayCanlendarInfo.getStatus())?View.GONE:View.VISIBLE);
            iv_order_class_statu.setImageResource(resStatu);
            tv_order_class_statu.setText(strStatu);
            tv_intervalTime.setText("约课时间间隔30分钟");

            if(position == dayCanlendarInfoList.size()-1){
                view_last_line.setVisibility(View.GONE);
            }
        }

    }
    
}
