package com.yijian.staff.mvp.coach.experienceclass.template.template_system;

import android.view.View;
import android.widget.TextView;


import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.experienceclass.template.template_system.bean.TitleTemplate;

import java.util.List;

/**
 * Created by The_P on 2018/4/13.
 */

public class TypeTitleViewHolder extends  TypeViewHolder{

    private final TextView tvTitle;

    public TypeTitleViewHolder(View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tv_tilte);
    }

    @Override
    void bindView(int position, List<Object> objects) {
        if (objects.get(position) instanceof TitleTemplate){
            tvTitle.setText(""+((TitleTemplate) objects.get(position)).getTitle());
        }
    }

}
