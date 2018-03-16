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

class Child_DemoViewHolder_dispaly extends ChildViewHolder {

    private final TextView tvKey;
    private final TextView tvValue;

    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public Child_DemoViewHolder_dispaly(@NonNull View itemView) {
        super(itemView);
        tvKey = (TextView) itemView.findViewById(R.id.tv_key);
        tvValue = (TextView) itemView.findViewById(R.id.tv_value);
    }


    public void bind(ChildObjBean child, int childPosition, int parentPosition) {
        tvKey.setText(child.getQustion());
        tvValue.setText(child.getDefaultValue());
        tvValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (listener!=null){
//                    listener.onChildNormalClick(childPosition,parentPosition);
//                }
            }
        });
    }
}
