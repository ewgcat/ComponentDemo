package com.yijian.staff.mvp.reception.step1;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckedTextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step1.bean.QuestOptBean;
import com.yijian.staff.mvp.reception.step1.recyclerView.ChildViewHolderGroup;

/**
 * Created by The_P on 2018/3/17.
 */

public class QuestionOptMixViewHolder extends ChildViewHolderGroup {
    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */

    private CheckedTextView ctvSingleCheck;

    public QuestionOptMixViewHolder(@NonNull View itemView) {
        super(itemView);
        ctvSingleCheck = (CheckedTextView) itemView.findViewById(R.id.ctv_multi);
    }

    public void bind(QuestOptBean child, int parentPosition, int childPosition) {
        ctvSingleCheck.setChecked(child.isSelected());

        ctvSingleCheck.setText(child.getName());

        ctvSingleCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (listener!=null){
//                    listener.onSingleClick(child,parentPosition,childPosition);
//                }
            }
        });
    }
}
