package com.yijian.staff.mvp.course.schedule.week.edit.list.addstudent.step1;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yijian.staff.R;

import drawthink.expandablerecyclerview.holder.BaseViewHolder;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/7/31 15:30:53
 */
public class StudentCourseViewHolder extends BaseViewHolder {

    public final ImageView ivHeader;
    public final ImageView ivselect;
    public final TextView tvName;
    public final RelativeLayout rlCourse;
    public final TextView tvCourseName;
    public final TextView tvCourseTime;

    public StudentCourseViewHolder(Context ctx, View itemView, int viewType) {
        super(ctx, itemView, viewType);
        ivHeader = itemView.findViewById(R.id.iv_header);
        ivselect = itemView.findViewById(R.id.iv_select);
        tvName = itemView.findViewById(R.id.tv_name);
        rlCourse = itemView.findViewById(R.id.rl_course);
        tvCourseName = itemView.findViewById(R.id.tv_course_name);
        tvCourseTime = itemView.findViewById(R.id.tv_course_time);


    }

    @Override
    public int getChildViewResId() {
        return R.id.rl_child;

    }

    @Override
    public int getGroupViewResId() {
        return R.id.ll_parent;

    }
}
