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

public class QuestionMultiCheckViewHolder extends ChildViewHolder {
  private CheckedTextView ctvMultiCheck;
    public QuestionMultiCheckViewHolder(@NonNull View itemView) {
        super(itemView);
        ctvMultiCheck = (CheckedTextView) itemView.findViewById(R.id.ctv_multi);
    }

    public void bind(QuestionOption child, int parentPosition, int childPosition) {
        ctvMultiCheck.setChecked(child.isSelected());
        ctvMultiCheck.setText(child.getmName());

        ctvMultiCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onMultiClick(child,parentPosition,childPosition);
                }
            }
        });
    }


    public  interface MultiCheckListener{
        void onMultiClick(QuestionOption child, int parentPosition,int childPosition );
    }

    private MultiCheckListener listener;
    public void setSingleCheckListener(MultiCheckListener singleCheckListener){
        listener=singleCheckListener;
    }
}
