package com.yijian.staff.mvp.coach.experienceclass.template.template_system;

import android.view.View;
import android.widget.TextView;


import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.experienceclass.template.template_system.bean.NoInstrumentListBean;

import java.util.List;

/**
 * Created by The_P on 2018/4/13.
 */

public class TypeItemNoInstrumentViewHolder extends TypeViewHolder {

    private final TextView column0;
    private final TextView column1;
    private final TextView column2;

    public TypeItemNoInstrumentViewHolder(View itemView) {
        super(itemView);
        column0 = itemView.findViewById(R.id.tv_column0);
        column1 = itemView.findViewById(R.id.tv_column1);
        column2 = itemView.findViewById(R.id.tv_column2);
    }

    @Override
    void bindView(int position, List<Object> objects) {

        Object o = objects.get(position);
        if (o instanceof NoInstrumentListBean &&o!=null) {

            final NoInstrumentListBean o1 = (NoInstrumentListBean) o;


            if (o1.isHead()) {//头部
                column0.setText("测试项目");
                column1.setText("组数");
                column2.setText("次数");


                column0.setTextColor(itemView.getContext().getResources().getColor(R.color.blue));
                column1.setTextColor(itemView.getContext().getResources().getColor(R.color.blue));
                column2.setTextColor(itemView.getContext().getResources().getColor(R.color.blue));
                itemView.setBackgroundResource(R.drawable.shape_fillet_white_up_8);
            } else {

                if (o1.isLastItem){
                    itemView.setBackgroundResource(R.drawable.shape_fillet_white_down_8);
                }else {
                    itemView.setBackgroundResource(R.color.white);
                }

                column0.setTextColor(itemView.getContext().getResources().getColor(R.color.text_black2));
                column1.setTextColor(itemView.getContext().getResources().getColor(R.color.text_black2));
                column2.setTextColor(itemView.getContext().getResources().getColor(R.color.text_black2));

                column0.setText(""+((NoInstrumentListBean) o).getName());
                column1.setText(""+((NoInstrumentListBean) o).getGroupNum());
                column2.setText(""+((NoInstrumentListBean) o).getGroupTime());
            }


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    o1.setName("-----111111111111111111111111111");
                }
            });
        }



    }
}
