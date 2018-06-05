package com.yijian.staff.mvp.reception.step1;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step1.bean.DataListBean;
import com.yijian.staff.mvp.reception.step1.bean.QuestOptBean;
import com.yijian.staff.mvp.reception.step1.bean.QuestionEntry;
import com.yijian.staff.mvp.reception.step1.bean.QuestionOption;
import com.yijian.staff.mvp.reception.step1.bean.Step1Bean;
import com.yijian.staff.mvp.reception.step1.recyclerView.ParentViewHolderGroup;

/**
 * Created by The_P on 2018/3/12.
 */

public class QuestionViewHolder extends AbsParentViewHolder {

    private final TextView tvTitle;

    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public QuestionViewHolder(@NonNull View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tv_question_title);
    }


    public void bind(DataListBean parent, int parentPosition) {
        tvTitle.setText("" + parent.getOrder() + "." + parent.getDesc());
    }
}
