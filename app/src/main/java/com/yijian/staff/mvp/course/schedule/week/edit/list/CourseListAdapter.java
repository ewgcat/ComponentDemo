package com.yijian.staff.mvp.course.schedule.week.edit.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.staff.BuildConfig;
import com.yijian.staff.R;
import com.yijian.staff.bean.CourseStudentBean;
import com.yijian.staff.mvp.base.BaseRvAdapter;
import com.yijian.staff.mvp.base.BaseViewHolder;
import com.yijian.staff.mvp.course.schedule.week.edit.list.edit.EditCourseTimeActivity;
import com.yijian.staff.util.ImageLoader;

public class CourseListAdapter extends BaseRvAdapter<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> {

    public CourseListAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_course;
    }

    @Override
    public void onBindItemHolder(BaseViewHolder holder, final int position) {
        Button btnDelete = holder.getView(R.id.btnDelete);
        TextView tvTime = holder.getView(R.id.tv_time);
        ImageView ivHead = holder.getView(R.id.iv_head);
        ImageView ivSex = holder.getView(R.id.iv_sex);
        ImageView ivEdit = holder.getView(R.id.iv_edit);
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvCourseName = holder.getView(R.id.tv_course_name);
        TextView tvCourseTime = holder.getView(R.id.tv_course_time);
        //这句话关掉IOS阻塞式交互效果 并依次打开左滑右滑

        //隐藏控件

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnSwipeListener) {
                    //如果删除时，不使用mAdapter.notifyItemRemoved(pos)，则删除没有动画效果，
                    //且如果想让侧滑菜单同时关闭，需要同时调用 ((CstSwipeDelMenu) holder.itemView).quickClose();
                    //((CstSwipeDelMenu) holder.itemView).quickClose();
                    mOnSwipeListener.onDel(position);
                }
            }
        });
        CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean bean=getDataList().get(position);
        CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCourseMemberVOBean privateCourseMemberVO = bean.getPrivateCourseMemberVO();
        CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCoachCourseVOBean privateCoachCourseVO = bean.getPrivateCoachCourseVO();
        if (privateCourseMemberVO != null) {
            tvName.setText(privateCourseMemberVO.getMemberName());
            int resId = privateCourseMemberVO.getMemberSex() == 0 ? R.mipmap.lg_man : R.mipmap.lg_women;
            ImageLoader.setImageResource(resId, mContext, ivSex);
            ImageLoader.setHeadImageResource(BuildConfig.FILE_HOST + privateCourseMemberVO.getHeadPath(), mContext, ivHead);
        }
        if (!TextUtils.isEmpty(bean.getSTime())) {
            tvTime.setText(bean.getSTime());
        }
        if (privateCoachCourseVO != null) {
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

    /**
     * 和Activity通信的接口
     */
    public interface onSwipeListener {
        void onDel(int pos);

    }

    private onSwipeListener mOnSwipeListener;

    public void setOnDelListener(onSwipeListener mOnDelListener) {
        this.mOnSwipeListener = mOnDelListener;
    }

}

