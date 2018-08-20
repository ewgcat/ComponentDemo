package com.yijian.staff.mvp.course.timetable.schedule.week.edit.list.addstudent.step1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.yijian.staff.BuildConfig;
import com.yijian.staff.R;
import com.yijian.staff.bean.GroupedStudentBean;
import com.yijian.staff.mvp.course.timetable.schedule.week.edit.list.addstudent.step1.StudentCourseViewHolder;
import com.yijian.staff.util.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import drawthink.expandablerecyclerview.adapter.BaseRecyclerViewAdapter;
import drawthink.expandablerecyclerview.bean.RecyclerViewData;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/7/31 11:37:17
 */
public class StudentCourseListAdapter extends BaseRecyclerViewAdapter<GroupedStudentBean, GroupedStudentBean.PrivateCoachCourseVOSBean, StudentCourseViewHolder> {

    private Context context;
    private List<RecyclerViewData> datas = new ArrayList<>();
    private LayoutInflater mInflater;


    public StudentCourseListAdapter(Context context, List<RecyclerViewData> datas) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public View getGroupView(ViewGroup parent) {
        return mInflater.inflate(R.layout.item_parent, parent, false);
    }

    @Override
    public View getChildView(ViewGroup parent) {
        return mInflater.inflate(R.layout.item_child, parent, false);
    }

    @Override
    public StudentCourseViewHolder createRealViewHolder(Context ctx, View view, int viewType) {
        return new StudentCourseViewHolder(ctx, view, viewType);

    }

    @Override
    public void onBindGroupHolder(StudentCourseViewHolder holder, int groupPos, int position, GroupedStudentBean groupData) {
        holder.tvName.setText(groupData.getMemberName());
        boolean expand = datas.get(groupPos).getGroupItem().isExpand();
        int resId = expand ? R.mipmap.select : R.mipmap.circle_normal;
        ImageLoader.setImageResource(resId, context, holder.ivselect);
        int resIdsex = groupData.getMemberSex() == 0 ? R.mipmap.lg_man : R.mipmap.lg_women;
        ImageLoader.setImageResource(resIdsex,context,holder.ivSex);
        ImageLoader.setImageResource(BuildConfig.FILE_HOST+groupData.getHeadPath(),context,holder.ivHeader);
    }

    @Override
    public void onBindChildpHolder(StudentCourseViewHolder holder, int groupPos, int childPos, int position, GroupedStudentBean.PrivateCoachCourseVOSBean childData) {
        holder.tvCourseName.setText(childData.getMemberCourseName());
        holder.tvCourseTime.setText(childData.getConsumingMinute());
        if (childPos == selectChildPosition) {
            holder.rlCourse.setBackground(context.getDrawable(R.drawable.blue_stroke_bg));
        } else {
            holder.rlCourse.setBackground(context.getDrawable(R.drawable.gray_stroke_bg));
        }

    }

    /**
     * true 全部可展开
     * fasle  同一时间只能展开一个
     */
    @Override
    public boolean canExpandAll() {
        return false;
    }


    private int selectGroupPosition=-1;
    private int selectChildPosition = 0;

    public void selectChild(int selectGroupPosition, int selectChildPosition) {
        this.selectGroupPosition = selectGroupPosition;
        this.selectChildPosition = selectChildPosition;
        notifyRecyclerViewData();
    }


    public void selectParent(int selectGroupPosition) {
        this.selectGroupPosition = selectGroupPosition;
        this.selectChildPosition = 0;

        notifyRecyclerViewData();
    }

    public int getSelectedChildIndex(){
        return selectChildPosition;
    }


}
