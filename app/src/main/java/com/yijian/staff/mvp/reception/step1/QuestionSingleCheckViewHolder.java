package com.yijian.staff.mvp.reception.step1;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckedTextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step1.bean.QuestOptBean;
import com.yijian.staff.mvp.reception.step1.bean.QuestionOption;
import com.yijian.staff.mvp.reception.step1.recyclerView.ChildViewHolderGroup;

/**
 * Created by The_P on 2018/3/12.
 */

public class QuestionSingleCheckViewHolder extends ChildViewHolderGroup {
    private CheckedTextView ctvSingleCheck;

    public QuestionSingleCheckViewHolder(@NonNull View itemView) {
        super(itemView);
        ctvSingleCheck = (CheckedTextView) itemView.findViewById(R.id.ctv_single_check);
    }

    public void bind(QuestOptBean child, int parentPosition, int childPosition) {
        ctvSingleCheck.setChecked(child.isSelected());
        ctvSingleCheck.setBackgroundResource(child.isSelected()?R.drawable.shape_fillet_blue:R.drawable.shape_fillet_gray_5);

        ctvSingleCheck.setText(child.getName());

        ctvSingleCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onSingleClick(child,parentPosition,childPosition);
                }
            }
        });
    }

    public  interface SingleCheckListener{
        void onSingleClick(QuestOptBean child, int parentPosition,int childPosition);
    }

    private SingleCheckListener listener;
    public void setSingleCheckListener(SingleCheckListener singleCheckListener){
        listener=singleCheckListener;
    }


}
