package com.yijian.staff.mvp.course.timetable.schedule.week.edit.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.staff.BuildConfig;
import com.yijian.staff.R;
import com.yijian.staff.bean.CourseStudentBean;
import com.yijian.staff.mvp.course.timetable.schedule.week.edit.list.edit.EditCourseTimeActivity;
import com.yijian.staff.util.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class CourseListAdapter extends RecyclerView.Adapter {

    private List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> dataList = new ArrayList<>();
    private Context mContext;

    public CourseListAdapter(Context context, List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> dataList) {
        this.mContext = context;
        this.dataList = dataList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bindView(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTime; //课程名称
        private ImageView ivHead; // 头像
        private ImageView ivSex; // 头像
        private ImageView ivEdit; // 头像
        private TextView tvName; //姓名
        private TextView tvCourseName; //课程名称
        private TextView tvCourseTime; //课程名称


        public ViewHolder(View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tv_time);
            ivHead = itemView.findViewById(R.id.iv_head);
            ivSex = itemView.findViewById(R.id.iv_sex);
            ivEdit = itemView.findViewById(R.id.iv_edit);
            tvName = itemView.findViewById(R.id.tv_name);
            tvCourseName = itemView.findViewById(R.id.tv_course_name);
            tvCourseTime = itemView.findViewById(R.id.tv_course_time);

        }

        public void bindView(CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean bean) {
            CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCourseMemberVOBean privateCourseMemberVO = bean.getPrivateCourseMemberVO();
            CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCoachCourseVOBean privateCoachCourseVO = bean.getPrivateCoachCourseVO();

            tvName.setText(privateCourseMemberVO.getMemberName());
            int resId = privateCourseMemberVO.getMemberSex() == 0 ? R.mipmap.lg_man : R.mipmap.lg_women;
            ImageLoader.setImageResource(resId, mContext, ivSex);
            ImageLoader.setImageResource(BuildConfig.FILE_HOST + privateCourseMemberVO.getHeadPath(), mContext, ivHead);
            tvTime.setText(bean.getSTime());

            tvCourseName.setText(privateCoachCourseVO.getMemberCourseName());
            tvCourseTime.setText(" (" + privateCoachCourseVO.getConsumingMinute() + "分钟）");
            ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, EditCourseTimeActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("PrivateCoachCurriculumArrangementPlanVOSBean", bean);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            });
        }

    }

}
