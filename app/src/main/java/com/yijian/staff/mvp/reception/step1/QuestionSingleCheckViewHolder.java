package com.yijian.staff.mvp.reception.step1;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckedTextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step1.bean.QuestionOption;
import com.yijian.staff.mvp.reception.step1.recyclerView.ChildViewHolder;

/**
 * Created by The_P on 2018/3/12.
 */

public class QuestionSingleCheckViewHolder extends ChildViewHolder{
    private CheckedTextView ctvSingleCheck;

    public QuestionSingleCheckViewHolder(@NonNull View itemView) {
        super(itemView);
        ctvSingleCheck = (CheckedTextView) itemView.findViewById(R.id.ctv_single_check);
    }

    public void bind(QuestionOption child, int parentPosition, int childPosition) {
        ctvSingleCheck.setChecked(child.isSelected());
        ctvSingleCheck.setText(child.getmName());

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
        void onSingleClick(QuestionOption child, int parentPosition,int childPosition);
    }

    private SingleCheckListener listener;
    public void setSingleCheckListener(SingleCheckListener singleCheckListener){
        listener=singleCheckListener;
    }


}
