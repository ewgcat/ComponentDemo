package com.yijian.staff.mvp.reception.step2;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step2.expandablerecyclerview.ChildViewHolder;
import com.yijian.staff.mvp.reception.step2.step2Bean.ChildObjBean;


/**
 * Created by The_P on 2018/3/15.
 */

class Child_DemoViewHolder_dispalyAndEdit extends ChildViewHolder {

    private final TextView tvKey;

    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public Child_DemoViewHolder_dispalyAndEdit(@NonNull View itemView) {
        super(itemView);
        tvKey = (TextView) itemView.findViewById(R.id.tv_key);
    }


    public void bind(ChildObjBean child, int childPosition, int parentPosition) {
        tvKey.setText(child.getQustion());
    }
}
