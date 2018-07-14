package com.yijian.staff.mvp.course.experienceclass.template.template_system;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by The_P on 2018/4/13.
 */

public abstract class TypeViewHolder extends RecyclerView.ViewHolder {
    public TypeViewHolder(View itemView) {
        super(itemView);
    }

    abstract void bindView(int position, List<Object> objects);

}
