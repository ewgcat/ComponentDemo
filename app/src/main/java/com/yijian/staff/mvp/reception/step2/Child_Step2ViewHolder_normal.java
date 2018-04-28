package com.yijian.staff.mvp.reception.step2;

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

public class Child_Step2ViewHolder_normal extends ChildViewHolder {

    private final TextView tvKey;
    private final TextView tvValue;
    private  final View itemView;
    private final View spaceview;

    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public Child_Step2ViewHolder_normal(@NonNull View itemView) {
        super(itemView);
        tvKey = (TextView) itemView.findViewById(R.id.tv_key);
        tvValue = (TextView) itemView.findViewById(R.id.tv_value);
        spaceview = itemView.findViewById(R.id.view_space);
        this.itemView= itemView;
    }

    public void bind(ChildOptBean child, final int childPosition, final int parentPosition, List<ParentQuestionBean> parentList) {

        tvKey.setText(child.getQustion());
        tvValue.setText(child.getUserValue());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onChildNormalClick(childPosition,parentPosition);
                }
            }
        });

        int size = parentList.get(parentPosition).getChildList().size();

        if (childPosition==size-1){
            spaceview.setVisibility(View.VISIBLE);
            itemView.setBackgroundResource(R.drawable.shape_fillet_white_down_8);
        }else {
            spaceview.setVisibility(View.GONE);
            itemView.setBackgroundResource(R.color.white);
        }

//        if ("年龄(岁)".equals(child.getQustion())||"脂肪控制(kg)".equals(child.getQustion())
//                ||"静态心跳率(次/分钟)".equals(child.getQustion())||"腓骨外髁".equals(child.getQustion())
//                ||"足部".equals(child.getQustion())){
//            spaceview.setVisibility(View.VISIBLE);
//            itemView.setBackgroundResource(R.drawable.shape_fillet_white_down_8);
//        }else {
//            spaceview.setVisibility(View.GONE);
//            itemView.setBackgroundResource(R.color.white);
//        }
    }

//    public void bind(Child_Demo child, final int childPosition, final int parentPosition) {
//        tvKey.setText(child.getKey());
//        tvKey.setText(child.getValue());
//        tvValue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener!=null){
//                    listener.onChildNormalClick(childPosition,parentPosition);
//                }
//            }
//        });
//
//    }



    public interface ChildNormalListener{
        void onChildNormalClick(int childPosition, int parentPosition);
    }

    private ChildNormalListener listener;

    public void setChildNormalListener(ChildNormalListener childNormalListener){
        listener=childNormalListener;
    }
}
