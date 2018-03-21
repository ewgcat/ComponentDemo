package com.yijian.staff.mvp.physical;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step2.expandablerecyclerview.ChildViewHolder;
import com.yijian.staff.mvp.reception.step2.step2Bean.ChildOptBean;
import com.yijian.staff.mvp.reception.step2.step2Bean.ParentQuestionBean;

import java.util.List;


/**
 * Created by The_P on 2018/3/13.
 */

public class Child_physical_normal extends ChildViewHolder {

    private final TextView tvKey;
    private final TextView tvValue;
    private  final View itemView;
    private final View spaceview;

    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public Child_physical_normal(@NonNull View itemView) {
        super(itemView);
        tvKey = (TextView) itemView.findViewById(R.id.tv_key);
        tvValue = (TextView) itemView.findViewById(R.id.tv_value);
        spaceview = itemView.findViewById(R.id.view_space);
        this.itemView= itemView;
    }

    public void bind(ChildOptBean child, final int childPosition, final int parentPosition, List<ParentQuestionBean> parentList) {

        tvKey.setText(child.getQustion());
        tvValue.setText(child.getUserValue());

        int size = parentList.get(parentPosition).getChildList().size();

        if (childPosition==size-1){
            spaceview.setVisibility(View.VISIBLE);
            itemView.setBackgroundResource(R.drawable.shape_fillet_white_down_8);
        }else {
            spaceview.setVisibility(View.GONE);
            itemView.setBackgroundResource(R.color.white);
        }

    }



}
