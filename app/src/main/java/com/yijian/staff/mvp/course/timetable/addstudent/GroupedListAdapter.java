package com.yijian.staff.mvp.course.timetable.addstudent;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.yijian.staff.R;
import com.yijian.staff.bean.GroupedStudentBean;
import com.yijian.staff.bean.StudentBean;
import com.yijian.staff.util.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import drawthink.expandablerecyclerview.adapter.BaseRecyclerViewAdapter;
import drawthink.expandablerecyclerview.bean.RecyclerViewData;
import drawthink.expandablerecyclerview.holder.BaseViewHolder;
import drawthink.expandablerecyclerview.listener.OnRecyclerViewListener;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/7/31 11:37:17
 */
public class GroupedListAdapter extends BaseRecyclerViewAdapter<GroupedStudentBean, GroupedStudentBean.CourseBean, GroupedViewHolder> {

    private Context context;
    private List<RecyclerViewData> datas = new ArrayList<>();
    private LayoutInflater mInflater;


    public GroupedListAdapter(Context context, List<RecyclerViewData> datas) {
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
    public GroupedViewHolder createRealViewHolder(Context ctx, View view, int viewType) {
        return new GroupedViewHolder(ctx, view, viewType);

    }

    @Override
    public void onBindGroupHolder(GroupedViewHolder holder, int groupPos, int position, GroupedStudentBean groupData) {
        holder.tvName.setText(groupData.getName());
        boolean expand = datas.get(groupPos).getGroupItem().isExpand();
        int resId = expand ? R.mipmap.select : R.mipmap.circle_normal;
        ImageLoader.setImageResource(resId, context, holder.ivselect);
    }

    @Override
    public void onBindChildpHolder(GroupedViewHolder holder, int groupPos, int childPos, int position, GroupedStudentBean.CourseBean childData) {
        holder.tvCourseName.setText(childData.getCourseName());
        holder.tvCourseTime.setText(childData.getCourseTime());
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


}
