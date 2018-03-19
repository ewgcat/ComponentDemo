package com.yijian.staff.mvp.questionnaireresult;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step1.AbsParentViewHolder;
import com.yijian.staff.mvp.reception.step1.bean.Step1Bean;

/**
 * Created by The_P on 2018/3/12.
 */

public class QuestionnaireWriteViewHolder extends AbsParentViewHolder {
    private final TextView tvTitle;
    private TextView tvContent;
    private final TextView tvLimit;
    private static final String TAG = "QuestionWriteViewHolder";

    public QuestionnaireWriteViewHolder(@NonNull View itemView) {
        super(itemView);
        tvContent=itemView.findViewById(R.id.tv_write);
        tvLimit = itemView.findViewById(R.id.tv_limit);
        tvTitle = itemView.findViewById(R.id.tv_question_title);
    }


    public void bind(Step1Bean child, int parentPosition) {
        tvTitle.setText(child.getQuestName());
        tvContent.setText("this is a answer");
    }


}
