package com.yijian.staff.mvp.setclass;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.preparelessons.createlession.ActionBean;
import com.yijian.staff.mvp.coach.preparelessons.createlession.ActionViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangk on 2018/3/29.
 */

public class OpenLessonNewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<OpenLessonNewBean> openLessonNewBeans;
    OpenLessonNewActivity openLessonNewActivity;

    public OpenLessonNewAdapter(List<OpenLessonNewBean> openLessonNewBeans,OpenLessonNewActivity openLessonNewActivity) {
        this.openLessonNewBeans = openLessonNewBeans;
        this.openLessonNewActivity = openLessonNewActivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_open_lesson_new, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        OpenLessonNewBean openLessonNewBean = openLessonNewBeans.get(position);
        ((ViewHolder)holder).view_openlesson.initView(openLessonNewBean,position,openLessonNewActivity);
    }

    @Override
    public int getItemCount() {
        return openLessonNewBeans == null ? 0 : openLessonNewBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private OpenLessonNewView view_openlesson;

        public ViewHolder(View itemView) {
            super(itemView);
            view_openlesson = itemView.findViewById(R.id.view_openlesson);
        }
    }

}
