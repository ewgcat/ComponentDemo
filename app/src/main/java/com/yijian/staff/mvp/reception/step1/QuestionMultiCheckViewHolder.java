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

public class QuestionMultiCheckViewHolder extends ChildViewHolderGroup {
  private CheckedTextView ctvMultiCheck;
    public QuestionMultiCheckViewHolder(@NonNull View itemView) {
        super(itemView);
        ctvMultiCheck = (CheckedTextView) itemView.findViewById(R.id.ctv_multi);
    }

    public void bind(QuestOptBean child, int parentPosition, int childPosition) {
        ctvMultiCheck.setChecked(child.isSelected());
        ctvMultiCheck.setText(child.getName());
        ctvMultiCheck.setBackgroundResource(child.isSelected()?R.drawable.shape_fillet_blue:R.drawable.shape_fillet_gray_5);
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
        void onMultiClick(QuestOptBean child, int parentPosition,int childPosition );
    }

    private MultiCheckListener listener;
    public void setSingleCheckListener(MultiCheckListener singleCheckListener){
        listener=singleCheckListener;
    }
}
