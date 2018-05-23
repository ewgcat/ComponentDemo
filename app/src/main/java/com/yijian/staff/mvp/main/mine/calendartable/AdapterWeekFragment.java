package com.yijian.staff.mvp.main.mine.calendartable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.main.mine.calendartable.bean.DayTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by The_P on 2018/3/22.
 */

public class AdapterWeekFragment extends RecyclerView.Adapter<AdapterWeekFragment.WeekViewHolder> {
    private static final String TAG = "AdapterWeekFragment";
    private LayoutInflater inflater;
    private Context context;
    private List<DayTask> list = new ArrayList<>();

    public AdapterWeekFragment(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void resetData(List<DayTask> list1) {
        list.clear();
        list.addAll(list1);
        notifyDataSetChanged();
    }

    @Override
    public WeekViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_weekfragment_task, parent, false);

        return new WeekViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeekViewHolder holder, int position) {
//        Log.e(TAG, "onBindViewHolder: position="+position );
        if (list.size() > position) {
            DayTask dayTask = list.get(position);
            Log.e(TAG, "onBindViewHolder: dayTask=" + dayTask.toString());
            holder.bind(dayTask);
        }
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class WeekViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rlContainer;
        RelativeLayout view_1;
        RelativeLayout view_2;
        RelativeLayout view_3;
        RelativeLayout view_4;
        RelativeLayout view_5;
        RelativeLayout view_6;
        RelativeLayout view_7;
        RelativeLayout view_8;
        RelativeLayout view_9;
        RelativeLayout view_10;
        RelativeLayout view_11;
        RelativeLayout view_12;
        RelativeLayout view_13;
        RelativeLayout view_14;
        RelativeLayout view_15;
        RelativeLayout view_16;
        RelativeLayout view_17;
        RelativeLayout view_18;
        RelativeLayout view_19;
        RelativeLayout view_20;
        RelativeLayout view_21;
        RelativeLayout view_22;
        RelativeLayout view_23;
        RelativeLayout view_24;

        public WeekViewHolder(View itemView) {
            super(itemView);
            rlContainer = itemView.findViewById(R.id.rl_container);
            view_1 = itemView.findViewById(R.id.view_1);
            view_2 = itemView.findViewById(R.id.view_2);
            view_3 = itemView.findViewById(R.id.view_3);
            view_4 = itemView.findViewById(R.id.view_4);
            view_5 = itemView.findViewById(R.id.view_5);
            view_6 = itemView.findViewById(R.id.view_6);
            view_7 = itemView.findViewById(R.id.view_7);
            view_8 = itemView.findViewById(R.id.view_8);
            view_9 = itemView.findViewById(R.id.view_9);
            view_10 = itemView.findViewById(R.id.view_10);
            view_11 = itemView.findViewById(R.id.view_11);
            view_12 = itemView.findViewById(R.id.view_12);
            view_13 = itemView.findViewById(R.id.view_13);
            view_14 = itemView.findViewById(R.id.view_14);
            view_15 = itemView.findViewById(R.id.view_15);
            view_16 = itemView.findViewById(R.id.view_16);
            view_17 = itemView.findViewById(R.id.view_17);
            view_18 = itemView.findViewById(R.id.view_18);
            view_19 = itemView.findViewById(R.id.view_19);
            view_20 = itemView.findViewById(R.id.view_20);
            view_21 = itemView.findViewById(R.id.view_21);
            view_22 = itemView.findViewById(R.id.view_22);
            view_23 = itemView.findViewById(R.id.view_23);
            view_24 = itemView.findViewById(R.id.view_24);
        }

        public void bind(DayTask dayTask) {
            rlContainer.removeAllViews();
            if (dayTask == null){
                view_1.setBackgroundColor(Color.parseColor("#f1f2f6"));
                view_2.setBackgroundColor(Color.parseColor("#f1f2f6"));
                view_3.setBackgroundColor(Color.parseColor("#f1f2f6"));
                view_4.setBackgroundColor(Color.parseColor("#f1f2f6"));
                view_5.setBackgroundColor(Color.parseColor("#f1f2f6"));
                view_6.setBackgroundColor(Color.parseColor("#f1f2f6"));
                view_7.setBackgroundColor(Color.parseColor("#f1f2f6"));
                view_8.setBackgroundColor(Color.parseColor("#f1f2f6"));
                view_9.setBackgroundColor(Color.parseColor("#f1f2f6"));
                view_10.setBackgroundColor(Color.parseColor("#f1f2f6"));
                view_11.setBackgroundColor(Color.parseColor("#f1f2f6"));
                view_12.setBackgroundColor(Color.parseColor("#f1f2f6"));
                view_13.setBackgroundColor(Color.parseColor("#f1f2f6"));
                view_14.setBackgroundColor(Color.parseColor("#f1f2f6"));
                view_15.setBackgroundColor(Color.parseColor("#f1f2f6"));
                view_16.setBackgroundColor(Color.parseColor("#f1f2f6"));
                view_17.setBackgroundColor(Color.parseColor("#f1f2f6"));
                view_18.setBackgroundColor(Color.parseColor("#f1f2f6"));
                view_19.setBackgroundColor(Color.parseColor("#f1f2f6"));
                view_20.setBackgroundColor(Color.parseColor("#f1f2f6"));
                view_21.setBackgroundColor(Color.parseColor("#f1f2f6"));
                view_22.setBackgroundColor(Color.parseColor("#f1f2f6"));
                view_23.setBackgroundColor(Color.parseColor("#f1f2f6"));
                view_24.setBackgroundColor(Color.parseColor("#f1f2f6"));
                return;
            }

            Calendar calendar = Calendar.getInstance();
            String curDate = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH)+1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);


            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = simpleDateFormat.parse(dayTask.getStartDate());
                Date date2 = simpleDateFormat.parse(curDate);
                if (date1.getTime() == date2.getTime()) {

                    view_1.setBackgroundColor(Color.parseColor("#d0ebff"));
                    view_2.setBackgroundColor(Color.parseColor("#d0ebff"));
                    view_3.setBackgroundColor(Color.parseColor("#d0ebff"));
                    view_4.setBackgroundColor(Color.parseColor("#d0ebff"));
                    view_5.setBackgroundColor(Color.parseColor("#d0ebff"));
                    view_6.setBackgroundColor(Color.parseColor("#d0ebff"));
                    view_7.setBackgroundColor(Color.parseColor("#d0ebff"));
                    view_8.setBackgroundColor(Color.parseColor("#d0ebff"));
                    view_9.setBackgroundColor(Color.parseColor("#d0ebff"));
                    view_10.setBackgroundColor(Color.parseColor("#d0ebff"));
                    view_11.setBackgroundColor(Color.parseColor("#d0ebff"));
                    view_12.setBackgroundColor(Color.parseColor("#d0ebff"));
                    view_13.setBackgroundColor(Color.parseColor("#d0ebff"));
                    view_14.setBackgroundColor(Color.parseColor("#d0ebff"));
                    view_15.setBackgroundColor(Color.parseColor("#d0ebff"));
                    view_16.setBackgroundColor(Color.parseColor("#d0ebff"));
                    view_17.setBackgroundColor(Color.parseColor("#d0ebff"));
                    view_18.setBackgroundColor(Color.parseColor("#d0ebff"));
                    view_19.setBackgroundColor(Color.parseColor("#d0ebff"));
                    view_20.setBackgroundColor(Color.parseColor("#d0ebff"));
                    view_21.setBackgroundColor(Color.parseColor("#d0ebff"));
                    view_22.setBackgroundColor(Color.parseColor("#d0ebff"));
                    view_23.setBackgroundColor(Color.parseColor("#d0ebff"));
                    view_24.setBackgroundColor(Color.parseColor("#d0ebff"));

                }else{
                    view_1.setBackgroundColor(Color.parseColor("#f1f2f6"));
                    view_2.setBackgroundColor(Color.parseColor("#f1f2f6"));
                    view_3.setBackgroundColor(Color.parseColor("#f1f2f6"));
                    view_4.setBackgroundColor(Color.parseColor("#f1f2f6"));
                    view_5.setBackgroundColor(Color.parseColor("#f1f2f6"));
                    view_6.setBackgroundColor(Color.parseColor("#f1f2f6"));
                    view_7.setBackgroundColor(Color.parseColor("#f1f2f6"));
                    view_8.setBackgroundColor(Color.parseColor("#f1f2f6"));
                    view_9.setBackgroundColor(Color.parseColor("#f1f2f6"));
                    view_10.setBackgroundColor(Color.parseColor("#f1f2f6"));
                    view_11.setBackgroundColor(Color.parseColor("#f1f2f6"));
                    view_12.setBackgroundColor(Color.parseColor("#f1f2f6"));
                    view_13.setBackgroundColor(Color.parseColor("#f1f2f6"));
                    view_14.setBackgroundColor(Color.parseColor("#f1f2f6"));
                    view_15.setBackgroundColor(Color.parseColor("#f1f2f6"));
                    view_16.setBackgroundColor(Color.parseColor("#f1f2f6"));
                    view_17.setBackgroundColor(Color.parseColor("#f1f2f6"));
                    view_18.setBackgroundColor(Color.parseColor("#f1f2f6"));
                    view_19.setBackgroundColor(Color.parseColor("#f1f2f6"));
                    view_20.setBackgroundColor(Color.parseColor("#f1f2f6"));
                    view_21.setBackgroundColor(Color.parseColor("#f1f2f6"));
                    view_22.setBackgroundColor(Color.parseColor("#f1f2f6"));
                    view_23.setBackgroundColor(Color.parseColor("#f1f2f6"));
                    view_24.setBackgroundColor(Color.parseColor("#f1f2f6"));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }



            List<DayTask.CoursesBean> courseInfoList = dayTask.getCourses();
            if (courseInfoList == null || courseInfoList.size() <= 0) return;
            for (DayTask.CoursesBean courseBean : courseInfoList) {
                creatCourseView(courseBean, rlContainer);
            }

//            creatCourseView(new CourseInfo(),rlContainer);
        }


        //手动创建CourseView
        @SuppressLint("ResourceType")
        private void creatCourseView(DayTask.CoursesBean courseInfo, RelativeLayout rlContainer) {
//                SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");//如2016-08-10 20:40
//            计算时间差(分钟)课程时间
            int diff_course = computerDiffminutes_start_end(courseInfo);

//            计算时间差(分钟)课程时间起始时间至00：00
            int diff_start = computerDiffminutes_start_0(courseInfo);

            //CourseView距离Relativielayout的top的距离(一小时为60dp的高度)
            float marginTop = ((diff_start * 1.0f / 60) * 60);

            //CourseView的高度(一小时为60dp的高度)
            float Height = ((diff_course * 1.0f / 60) * 60);


            TextView textView = new TextView(context);
            RelativeLayout.LayoutParams layoutParams =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, dip2px(Height));
            layoutParams.setMargins(1, dip2px(marginTop), 0, 0);
            /*if (courseInfo.getIsExperience().equals("1")) { //0：私教课，1：体验课
                textView.setBackgroundResource(R.color.textColorde947d);
            } else {
                textView.setBackgroundResource(R.color.textColor9acac4);
            }*/


            textView.setBackgroundResource(R.color.textColorde947d);

            textView.setTextColor(context.getResources().getColor(R.color.white));
            textView.setText("" + courseInfo.getLessonName());
            rlContainer.addView(textView, layoutParams);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(courseInfo);


                    }
                }
            });
        }

    }


    public interface ICourseListener {
        void onClick(DayTask.CoursesBean courseInfo);
    }

    private ICourseListener listener;

    public void setICourseListener(ICourseListener iCourseInterface) {
        listener = iCourseInterface;
    }

    public int dip2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private int computerDiffminutes_start_0(DayTask.CoursesBean courseInfo) {
//        String t1 = "2008-03-10 00:00:00";
//        String t2 = "2008-03-10 16:25:02";
        String t1 = "00:00";
        String t2 = courseInfo.getStartDatetime();
        Date d1 = null;
        Date d2 = null;

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        try {
            d1 = sdf.parse(t1);
            d2 = sdf.parse(t2);
            long dd1 = d1.getTime();
            long dd2 = d2.getTime();
            int minutes = (int) ((dd2 - dd1) / 1000 / 60);
            return minutes;
        } catch (Exception pe) {
            pe.printStackTrace();
        }


        return 0;

    }


    private int computerDiffminutes_start_end(DayTask.CoursesBean courseInfo) {


//        String t1 = "2008-03-10 16:25:02";
//        String t2 = "2008-03-10 18:45:02";
        String t1 = courseInfo.getStartDatetime();
        String t2 = courseInfo.getEndDatetime();

        Date d1 = null;
        Date d2 = null;

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        try {
            d1 = sdf.parse(t1);
            d2 = sdf.parse(t2);
            long dd1 = d1.getTime();
            long dd2 = d2.getTime();
            int minutes = (int) ((dd2 - dd1) / 1000 / 60);
            return minutes;
        } catch (Exception pe) {
            pe.printStackTrace();
        }


        return 0;
    }
}
