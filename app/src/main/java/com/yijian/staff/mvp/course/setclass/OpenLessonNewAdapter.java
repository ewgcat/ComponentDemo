package com.yijian.staff.mvp.course.setclass;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yijian.staff.R;
import com.yijian.staff.mvp.course.preparelessons.createlession.EditActionObservable;
import com.yijian.staff.bean.PrivateLessonRecordBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangk on 2018/3/29.
 */

public class OpenLessonNewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<PrivateLessonRecordBean> privateLessonRecordBeans = new ArrayList<>();
    OpenLessonNewActivity openLessonNewActivity;
    EditActionObservable editActionObservable;

    public void resetData(List<PrivateLessonRecordBean> privateLessonRecordBeans) {
        this.privateLessonRecordBeans = privateLessonRecordBeans;
        notifyDataSetChanged();
    }

    public OpenLessonNewAdapter(List<PrivateLessonRecordBean> privateLessonRecordBeans, OpenLessonNewActivity openLessonNewActivity, EditActionObservable editActionObservable) {
        this.privateLessonRecordBeans = privateLessonRecordBeans;
        this.openLessonNewActivity = openLessonNewActivity;
        this.editActionObservable = editActionObservable;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_open_lesson_new, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PrivateLessonRecordBean openLessonNewBean = privateLessonRecordBeans.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.view_openlesson.initActionRecord(openLessonNewBean, position, openLessonNewActivity);
        editActionObservable.addObserver(viewHolder.view_openlesson);
    }

    @Override
    public int getItemCount() {
        return privateLessonRecordBeans == null ? 0 : privateLessonRecordBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private OpenLessonNewView view_openlesson;

        public ViewHolder(View itemView) {
            super(itemView);
            view_openlesson = itemView.findViewById(R.id.view_openlesson);
        }
    }

}
