package com.yijian.staff.mvp.reception.step2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step2.expandablerecyclerview.ParentViewHolder;
import com.yijian.staff.mvp.reception.step2.step2Bean.ParentQuestionBean;


/**
 * Created by The_P on 2018/3/13.
 */

public class Parent_Step2ViewHolder extends ParentViewHolder {

    private final TextView tvQuestion;
    private final View itemView;
    private final TextView tvToggle;
    private final RelativeLayout rlToggle;
    private Context context;
    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     * @param mContext
     */
    public Parent_Step2ViewHolder(@NonNull View itemView, Activity mContext) {
        super(itemView);
        context=mContext;
        this.itemView=itemView;
        tvQuestion = (TextView) itemView.findViewById(R.id.tv_question);
        rlToggle = itemView.findViewById(R.id.rl_toggle);
        tvToggle =  itemView.findViewById(R.id.tv_toggle);

    }

    public void bind(ParentQuestionBean parent) {
        tvQuestion.setText(parent.getTitle());
    }

    @SuppressLint("NewApi")
    @Override
    public void setExpanded(boolean expanded) {
        super.setExpanded(expanded);
        if (expanded) {
            itemView.setBackgroundResource(R.drawable.shape_fillet_white_up_8);
            tvToggle.setText("收起");


            Drawable drawable = context.getDrawable(R.mipmap.lg_shouqi);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvToggle.setCompoundDrawables(null,null, drawable,null);
        } else {
            itemView.setBackgroundResource(R.drawable.shape_fillet_white_8);
            tvToggle.setText("展开");
            Drawable drawable = context.getDrawable(R.mipmap.lg_zhankai);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvToggle.setCompoundDrawables(null,null, drawable,null);
        }
    }

}
