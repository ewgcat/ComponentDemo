package com.yijian.staff.mvp.coach.experienceclass.template.template_system;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.experienceclass.template.template_system.bean.PowerListBean;

import java.util.List;

/**
 * Created by The_P on 2018/4/13.
 */

public class TypeItemPowerViewHolder extends  TypeViewHolder{
    private final TextView column0;
    private final TextView column1;
    private final TextView column2;
    private final TextView column3;


    public TypeItemPowerViewHolder(View itemView) {
        super(itemView);
        column0 = itemView.findViewById(R.id.tv_column0);
        column1 = itemView.findViewById(R.id.tv_column1);
        column2 = itemView.findViewById(R.id.tv_column2);
        column3 = itemView.findViewById(R.id.tv_column3);
    }

    @Override
    void bindView(int position, List<Object> objects) {
        Object o = objects.get(position);
        if (o instanceof PowerListBean) {
            if (((PowerListBean) o).isHead()) {//头部
                column0.setText("器材");
                column1.setText("重量");
                column2.setText("次数");
                column3.setText("组数");

//                RelativeLayout.LayoutParams layoutParams = (.LayoutParams) column0.getLayoutParams();
//                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) column0.getLayoutParams();
//                layoutParams.gravity=Gravity.CENTER;
//                column0.setLayoutParams(layoutParams);
                column0.setGravity(Gravity.CENTER);
                column0.setTextColor(itemView.getContext().getResources().getColor(R.color.blue));
                column1.setTextColor(itemView.getContext().getResources().getColor(R.color.blue));
                column2.setTextColor(itemView.getContext().getResources().getColor(R.color.blue));
                column3.setTextColor(itemView.getContext().getResources().getColor(R.color.blue));
                itemView.setBackgroundResource(R.drawable.shape_fillet_white_up_8);
            } else {
                if (((PowerListBean) o).isLastItem){
                    itemView.setBackgroundResource(R.drawable.shape_fillet_white_down_8);
                }else {
                    itemView.setBackgroundResource(R.color.white);
                }

                column0.setTextColor(itemView.getContext().getResources().getColor(R.color.text_black2));
                column1.setTextColor(itemView.getContext().getResources().getColor(R.color.text_black2));
                column2.setTextColor(itemView.getContext().getResources().getColor(R.color.text_black2));
                column3.setTextColor(itemView.getContext().getResources().getColor(R.color.text_black2));


                column0.setGravity(Gravity.CENTER_VERTICAL);
                column0.setText(""+((PowerListBean) o).getName());
                column1.setText(""+((PowerListBean) o).getWeight());
                column2.setText(""+((PowerListBean) o).getGroupTime());
                column2.setText(""+((PowerListBean) o).getGroupNum());
            }
        }
    }
}
